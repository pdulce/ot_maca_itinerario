package giss.mad.marcocalidad.itinerariocalidad.service;

import giss.mad.marcocalidad.itinerariocalidad.model.*;
import giss.mad.marcocalidad.itinerariocalidad.model.auxactiv.ReplicaElementOEntrega;
import giss.mad.marcocalidad.itinerariocalidad.model.auxactiv.ValorEje;
import giss.mad.marcocalidad.itinerariocalidad.model.auxitinerario.ActividadQAPantalla;
import giss.mad.marcocalidad.itinerariocalidad.model.auxitinerario.ItinerarioPantalla;
import giss.mad.marcocalidad.itinerariocalidad.model.auxitinerario.StagePantalla;
import giss.mad.marcocalidad.itinerariocalidad.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ActividadItinerarioService {

    @Autowired
    private ActividadItinerarioRepository actividadItinerarioRepository;

    @Autowired
    private ActividadQARepository qAactividadRepository;

    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private PesoRepository pesoRepository;

    @Autowired
    private EjeHeredableRepository ejeHeredableRepository;
    @Autowired
    private UmbralActividadRepository umbralActividadRepository;

    @Autowired
    private ItinerarioCalidadRepository itinerarioCalidadRepo;

    public Collection<ActividadItinerario> getByIdActividadesByItinerario(Integer qualityItineraryId) {
        return this.actividadItinerarioRepository.findAllByQualityItineraryIdAndDeletedIsNull(qualityItineraryId);
    }

    public ActividadItinerario getByIdActividadItinerario(Integer idActividadItinerario) {
        return this.actividadItinerarioRepository.findByIdAndDeletedIsNull(idActividadItinerario);
    }

    @Transactional
    public ActividadItinerario remove(Integer idActividadItinerario) {
        ActividadItinerario actividadItinerario = this.actividadItinerarioRepository.findByIdAndDeletedIsNull(idActividadItinerario);
        if (this.actividadItinerarioRepository.findByIdAndDeletedIsNull(actividadItinerario.getId()) != null) {
            this.actividadItinerarioRepository.delete(actividadItinerario);
        }
        return actividadItinerario;
    }

    @Transactional
    public ActividadItinerario save(ActividadItinerario actividadItinerario) {
        return this.actividadItinerarioRepository.save(actividadItinerario);
    }

    @Transactional
    public ActividadItinerario update(ActividadItinerario actividadItinerario) {
        if (this.actividadItinerarioRepository.findByIdAndDeletedIsNull(actividadItinerario.getId())!= null) {
            return this.actividadItinerarioRepository.save(actividadItinerario);
        }
        return null;
    }

    public ItinerarioPantalla calculateItinerary(ReplicaElementOEntrega elemOrDelivery){

        ItinerarioCalidad itinerarioDetallado = calcularActividadItinerarioWithDetailedInfo(elemOrDelivery);

        ItinerarioPantalla itinerario = new ItinerarioPantalla();
        itinerario.setId(Long.valueOf(itinerarioDetallado.getId()));
        itinerario.setCreationDate(itinerarioDetallado.getCreationDate());
        itinerario.setElementId(itinerarioDetallado.getCatalogueId());
        itinerario.setDelivery(elemOrDelivery.getDelivery());

        Map<String, List<ActividadQAPantalla>> stagesMap = new HashMap<>();
        for (ActividadItinerario actQA: itinerarioDetallado.getActividadesDeItinerario()){
            ActividadQAPantalla actividadResult = new ActividadQAPantalla();
            actividadResult.setId(Long.valueOf(actQA.getId()));
            ActividadQA actividadObject = this.qAactividadRepository.findByIdAndDeletedIsNull(actQA.getActivityId());
            actividadResult.setActivity(actividadObject.getName());
            actividadResult.setObservations(actQA.getObservations());
            actividadResult.setRealization(actQA.getInferredThreshold());
            actividadResult.setIncluded(actQA.getIncludedInItinerary());

            String stageOfActivity = this.etapaPruebasRepository.findByIdAndDeletedIsNull(actividadObject.getTestingStageId()).getName();
            if (stagesMap.get(stageOfActivity) == null){
                List<ActividadQAPantalla> listaSoloActividadesARealizar = new ArrayList<>();
                listaSoloActividadesARealizar.add(actividadResult);
                stagesMap.put(stageOfActivity, listaSoloActividadesARealizar);
            }else{
                List<ActividadQAPantalla> listaSoloActividadesARealizar = stagesMap.get(stageOfActivity);
                listaSoloActividadesARealizar.add(actividadResult);
                stagesMap.put(stageOfActivity, listaSoloActividadesARealizar);
            }
        }

        List<StagePantalla> stageListForPantalla = new ArrayList<>();
        //recorremos la hashmap... y...
        for (String stageKey: stagesMap.keySet()){
            StagePantalla stage = new StagePantalla();
            stage.setStage(stageKey);
            stage.setIdStage(this.etapaPruebasRepository.findByNameAndDeletedIsNull(stageKey).getId());
            stage.setActivities(stagesMap.get(stageKey));
            stageListForPantalla.add(stage);
        }
        Collections.sort(stageListForPantalla, new Comparator<StagePantalla>() {
            @Override
            public int compare(StagePantalla o1, StagePantalla o2) {
                if (o1.getIdStage() > o2.getIdStage()){
                    return 1;
                }else if (o1.getIdStage() < o2.getIdStage()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });

        itinerario.setStages(stageListForPantalla);

        return itinerario;
    }

    @Transactional
    public ItinerarioCalidad calcularActividadItinerarioWithDetailedInfo(ReplicaElementOEntrega elemOrDelivery){

        Integer elementInstanceId = elemOrDelivery.getId();
        Integer elementTypeId = elemOrDelivery.getCatalogElementTypeId();
        Boolean isDelivery = elemOrDelivery.getDelivery();

        List<ValorEje> valoresAttrsYejes = elemOrDelivery.getAttributeValuesCollection();
        //invocar al microservicio de /catalogo/ejes/get
        List<Integer> ejes = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22);//this.atributoEjeRepository.findByAxis(true);
        Map<Integer, Integer> mapOfAxisWithValuesDomain = new HashMap<>();
        for (ValorEje valorEjeOatributo: valoresAttrsYejes){
            Integer valueOfDomainId = valorEjeOatributo.getDomainValueId();
            Integer axisOrAttributeId = valorEjeOatributo.getAxisAttributeId();
            for (Integer ejeId: ejes) {
                if (ejeId == axisOrAttributeId) {
                    mapOfAxisWithValuesDomain.put(axisOrAttributeId, valueOfDomainId);
                }
            }
        }

        Timestamp fecCreacion= new Timestamp(Calendar.getInstance().getTime().getTime());

        ItinerarioCalidad itinerarioCalidad = new ItinerarioCalidad();
        itinerarioCalidad.setCatalogueId(elementInstanceId);
        itinerarioCalidad.setDelivery(isDelivery);
        itinerarioCalidad.setCreationDate(fecCreacion);
        itinerarioCalidad = this.itinerarioCalidadRepo.save(itinerarioCalidad);

        /****** DefiniciÃ³n del algoritmo ***/

        // Recorremos todas las actividades de QA existentes
        // Crear objeto ActividadItinerario_i, seteando el 'creation_date', 'activity_id' y 'itinerarioCalidadId'
        // Para cada una, hacemos esto
            // Inicializamos acumuladorSumaPesosActividad = 0
            // Inicializamos var Boolean includedInItinerary = TRUE;
            // 1.1: recorremos el keySet de mapOfAxisWithValuesDomain
                // 1.1.a: para cada entrada de ese mapa, obtenemos la colecciÃ³n de pesos existentes para esta combinaciÃ³n de idActividad-axisId-valueDomainId
                // 1.1.b: recorremos la colecciÃ³n de pesos y obtenemos el weigthValue que tiene el mismo valueDomainId que el recibido en el mapa de axis-valoresDominio pasado como argumento
                     // 1.1.b: SI (weigthValue == -1) THEN MARCAR PARA Excluir esta actividad del itinerario, ==> includedInItinerary = FALSE
                     // 1.1.C: ELSE THEN acumuladorSumaPesosActividad += weigthValue
            // 1.2: terminado de recorrer el bucle, seteamos en el objeto ActividadItinerario_i el atributo 'activity_sum_of_axes'

            // 1.3: Ahora, buscamos el umbral que encaje con este sumatorio, acudiendo a la entidad UmbralActividad
            // 1.4: Hacemos la consulta de Collection<UmbralActividad> getUmbralesByIdActividad, filtrando el objeto umbral seteado con
                    // elementTypeId, isDelivery y activity_id
            // Inicializar variable Boolean found = FALSE
            // 1.5: Recorrer esa colecciÃ³n de umbrales mientras includedInItinerary=TRUE AND found=FALSE, y accedo a los atributos lower_limit y upper_limit
            // 1.6: SI (acumuladorSumaPesosActividad >= lower_limit AND acumuladorSumaPesosActividad <= upper_limit) THEN
                    // 1.6.a: Recojo el atributo 'threshold', y lo seteo en atributo 'inferred_threshold' del objeto ActividadItinerario_i
                    // 1.6.b: Setear la variable found = TRUE
            // 1.7: Setear atributo 'exclude_unreached_threshold' del objeto ActividadItinerario_i con variable includedInItinerary
            // 1.8: save to table

        /****** Implementamos el algoritmo ***/

        List<ActividadItinerario> actividadesItinerario = new ArrayList<>();
        List<ActividadQA> actividadesQA = this.qAactividadRepository.findAllByDeletedIsNull(Sort.by(Sort.Order.asc("testingStageId")));
        Integer sumWeightsAllActivities = 0;
        for (ActividadQA actividadQA: actividadesQA){
            String observations = "";
            ActividadItinerario actividadItinerarioIesima = new ActividadItinerario();
            actividadItinerarioIesima.setCreationDate(fecCreacion);
            actividadItinerarioIesima.setQualityItineraryId(itinerarioCalidad.getId());
            actividadItinerarioIesima.setActivityId(actividadQA.getId());
            actividadItinerarioIesima.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
            String etapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(actividadQA.getTestingStageId()).getName();
            actividadItinerarioIesima.setHelp("Actividad--> " + etapa + " - " + actividadQA.getName());
            Integer acumuladorSumaPesosActividad = 0;
            Boolean includedInItinerary = true;
            for (Integer axisId: mapOfAxisWithValuesDomain.keySet()){
                Integer valorDominioId = mapOfAxisWithValuesDomain.get(axisId);
                //buscamos el peso que corresponde a ese valorDomainId para la lista de pesos de esta actividad-eje
                Collection<Peso> pesosFound =
                        this.pesoRepository.findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(elementTypeId, isDelivery, actividadQA.getId(), axisId);
                if (pesosFound.isEmpty()){
                    //miro si este eje es heredable, para tomar los pesos del padre , o si es entrega, del elemento asociado
                    EjeHeredable ejeHeredable = this.ejeHeredableRepository.findByElementTypeIdAndAxisIdAndForDeliveryAndDeletedIsNull(elementTypeId, axisId, isDelivery);

                    if (ejeHeredable != null && isDelivery) {//los proyectos son el raÃ­z en la jerarquÃ­a, no tienen elementos de nivel superior
                        //tomamos los pesos del elemento de catÃ¡logo asociado
                        pesosFound =
                                this. pesoRepository.findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(elementTypeId, false, actividadQA.getId(), axisId);
                    }else if (ejeHeredable != null && elementTypeId < 3){
                        //tomamos los pesos del elemento de catÃ¡logo padre en la jerarquÃ­a
                        pesosFound =
                                this.pesoRepository.findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(elementTypeId+1, false, actividadQA.getId(), axisId);
                    }
                }
                for (Peso peso: pesosFound){
                    if (peso.getDomainValueId() == valorDominioId){
                        if (peso.getWeightValue() == -1){
                            actividadItinerarioIesima.setInferredThreshold("Actividad excluida por valor de dominio existente");
                            observations = "Esta actividad no se incluye en el itinerario; valor -1 (EXLUYE) para el eje " + axisId + " para el valor de dominio existente";
                            includedInItinerary = false;// esta actividad queda exluida del itinerario, aunque se guarda en tabla a efectos de histÃ±orico y auditorÃ­as
                        }else {
                           acumuladorSumaPesosActividad += peso.getWeightValue();
                           sumWeightsAllActivities  += acumuladorSumaPesosActividad;
                        }
                        break;
                    }
                }//fin de recorrido de pesos

            }//fin de recorrido de ejes
            actividadItinerarioIesima.setActivitSumOfAxes(acumuladorSumaPesosActividad);
            //encajar el sumatorio en los rangos definidos por los umbrales de esta actividad
            Collection<UmbralActividad> umbralesActividad =
                    this.umbralActividadRepository.findAllByDeletedIsNullAndElemenTypeIdAndForDeliveryAndActivityId(elementTypeId, isDelivery, actividadQA.getId());

            if (includedInItinerary){//tto. si no ha sido excluida la actividad segÃºn el peso -1
                Boolean found = false;
                Iterator<UmbralActividad> umbralesIterator = umbralesActividad.iterator();
                while (umbralesIterator.hasNext() && !found){
                    UmbralActividad umbralActividad = umbralesIterator.next();
                    if (acumuladorSumaPesosActividad >= umbralActividad.getLowerLimit() && acumuladorSumaPesosActividad <= umbralActividad.getUpperLimit()){
                        actividadItinerarioIesima.setInferredThreshold(umbralActividad.getThreshold());
                        observations = "Actividad INCLUIDA en itinerario; sumatorio de pesos-ejes (" + acumuladorSumaPesosActividad +  ") ";
                        if (umbralActividad.getUpperLimit() < 9999){
                            observations  += " estÃ¡ dentro del rango ["+ umbralActividad.getLowerLimit() + " , " + umbralActividad.getUpperLimit()+ "]";
                        }else{
                            observations  += " es igual o mayor a "+ umbralActividad.getLowerLimit();
                        }
                        found = true;
                    }
                }
                if (!found){
                    includedInItinerary = false;
                    actividadItinerarioIesima.setInferredThreshold("Actividad excluida, no alcanza el umbral de realizaciÃ³n mÃ­nimo");
                    observations = "Esta actividad no se incluye en el itinerario; el sumatorio de pesos-ejes " + acumuladorSumaPesosActividad + " no se encuentra en los rangos definidos de umbrales";
                }
            }
            actividadItinerarioIesima.setObservations(observations);
            actividadItinerarioIesima.setIncludedInItinerary(includedInItinerary);
            this.actividadItinerarioRepository.save(actividadItinerarioIesima);
            actividadesItinerario.add(actividadItinerarioIesima);
        }
        if (sumWeightsAllActivities == 0){
            itinerarioCalidad.setActividadesDeItinerario(new ArrayList<>());
        }else {
            itinerarioCalidad.setActividadesDeItinerario(actividadesItinerario);
            this.itinerarioCalidadRepo.saveAndFlush(itinerarioCalidad);
        }

        return itinerarioCalidad;

    }

}
