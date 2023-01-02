package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadItinerario;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EjeHeredable;
import giss.mad.itinerario.model.ItinerarioCalidad;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.model.auxactiv.ReplicaElementOEntrega;
import giss.mad.itinerario.model.auxactiv.ValorEje;
import giss.mad.itinerario.model.auxitinerario.ActividadQAPantalla;
import giss.mad.itinerario.model.auxitinerario.ItinerarioPantalla;
import giss.mad.itinerario.model.auxitinerario.StagePantalla;
import giss.mad.itinerario.model.repository.ActividadItinerarioRepository;
import giss.mad.itinerario.model.repository.ActividadQARepository;
import giss.mad.itinerario.model.repository.EjeHeredableRepository;
import giss.mad.itinerario.model.repository.EtapaPruebasRepository;
import giss.mad.itinerario.model.repository.ItinerarioCalidadRepository;
import giss.mad.itinerario.model.repository.PesoRepository;
import giss.mad.itinerario.model.repository.UmbralActividadRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

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

  public Collection<ActividadItinerario> getByIdActividadesByItinerario(
      final Integer qualityItineraryId) {
    return this.actividadItinerarioRepository.findAllByQualityItineraryIdAndDeletedIsNull(
        qualityItineraryId);
  }

  public ActividadItinerario getByIdActividadItinerario(final Integer idActividadItinerario) {
    return this.actividadItinerarioRepository.findByIdAndDeletedIsNull(idActividadItinerario);
  }

  @Transactional
  public ActividadItinerario remove(final Integer idActividadItinerario) {
    ActividadItinerario actividadItinerario = this.actividadItinerarioRepository.findByIdAndDeletedIsNull(
        idActividadItinerario);
    if (this.actividadItinerarioRepository.findByIdAndDeletedIsNull(actividadItinerario.getId())
        != null) {
      this.actividadItinerarioRepository.delete(actividadItinerario);
    }
    return actividadItinerario;
  }

  @Transactional
  public ActividadItinerario save(final ActividadItinerario actividadItinerario) {
    return this.actividadItinerarioRepository.save(actividadItinerario);
  }

  @Transactional
  public ActividadItinerario update(final ActividadItinerario actividadItinerario) {
    if (this.actividadItinerarioRepository.findByIdAndDeletedIsNull(actividadItinerario.getId())
        != null) {
      return this.actividadItinerarioRepository.save(actividadItinerario);
    }
    return null;
  }

  public ItinerarioPantalla calculateItinerary(final ReplicaElementOEntrega elemOrDelivery) {

    ItinerarioCalidad itinerarioDetallado = calcularActividadItinerarioWithDetailedInfo(
        elemOrDelivery);

    ItinerarioPantalla itinerario = new ItinerarioPantalla();
    itinerario.setId(Long.valueOf(itinerarioDetallado.getId()));
    itinerario.setCreationDate(itinerarioDetallado.getCreationDate());
    itinerario.setElementId(itinerarioDetallado.getCatalogueId());
    itinerario.setDelivery(elemOrDelivery.getDelivery());

    Map<String, List<ActividadQAPantalla>> stagesMap = new HashMap<>();
    for (ActividadItinerario actQA : itinerarioDetallado.getActividadesDeItinerario()) {
      ActividadQAPantalla actividadResult = new ActividadQAPantalla();
      actividadResult.setId(Long.valueOf(actQA.getId()));
      ActividadQA actividadObject = this.qAactividadRepository.findByIdAndDeletedIsNull(
          actQA.getActivityId());
      actividadResult.setActivity(actividadObject.getName());
      actividadResult.setObservations(actQA.getObservations());
      actividadResult.setRealization(actQA.getInferredThreshold());
      actividadResult.setIncluded(actQA.getIncludedInItinerary());

      String stageOfActivity = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
          actividadObject.getTestingStageId()).getName();
      if (stagesMap.get(stageOfActivity) == null) {
        List<ActividadQAPantalla> listaSoloActividadesARealizar = new ArrayList<>();
        listaSoloActividadesARealizar.add(actividadResult);
        stagesMap.put(stageOfActivity, listaSoloActividadesARealizar);
      } else {
        List<ActividadQAPantalla> listaSoloActividadesARealizar = stagesMap.get(stageOfActivity);
        listaSoloActividadesARealizar.add(actividadResult);
        stagesMap.put(stageOfActivity, listaSoloActividadesARealizar);
      }
    }

    List<StagePantalla> stageListForPantalla = new ArrayList<>();
    for (String stageKey : stagesMap.keySet()) {
      StagePantalla stage = new StagePantalla();
      stage.setStage(stageKey);
      stage.setIdStage(this.etapaPruebasRepository.findByNameAndDeletedIsNull(stageKey).getId());
      stage.setActivities(stagesMap.get(stageKey));
      stageListForPantalla.add(stage);
    }
    Collections.sort(stageListForPantalla, new Comparator<StagePantalla>() {
      @Override
      public int compare(final StagePantalla o1, final StagePantalla o2) {
        if (o1.getIdStage() > o2.getIdStage()) {
          return 1;
        } else if (o1.getIdStage() < o2.getIdStage()) {
          return -1;
        } else {
          return 0;
        }
      }
    });

    itinerario.setStages(stageListForPantalla);

    return itinerario;
  }

  /****** Definición del algoritmo ***/

  // Recorremos todas las actividades de QA existentes
  // Crear objeto ActividadItinerario_i, seteando el 'creation_date', 'activity_id' e
  // 'itinerarioCalidadId'
  // Para cada una, hacemos esto
  // Inicializamos acumuladorSumaPesosActividad = 0
  // Inicializamos var Boolean includedInItinerary = TRUE;
  // 1.1: recorremos el keySet de mapOfAxisWithValuesDomain
  // 1.1.a: para cada entrada de ese mapa, obtenemos la colecciÃ³n de pesos existentes para esta
  // combinación de idActividad-axisId-valueDomainId
  // 1.1.b: recorremos la colecciÃ³n de pesos y obtenemos el weigthValue que tiene el mismo
  // valueDomainId que el recibido en el mapa de axis-valoresDominio pasado como argumento
  // 1.1.b: SI (weigthValue == -1) THEN MARCAR PARA Excluir esta actividad del itinerario, ==>
  // includedInItinerary = FALSE
  // 1.1.C: ELSE THEN acumuladorSumaPesosActividad += weigthValue
  // 1.2: terminado de recorrer el bucle, seteamos en el objeto ActividadItinerario_i el atributo
  // 'activity_sum_of_axes'

  // 1.3: Ahora, buscamos el umbral que encaje con este sumatorio, acudiendo a la entidad
  // UmbralActividad
  // 1.4: Hacemos la consulta de Collection<UmbralActividad> getUmbralesByIdActividad, filtrando
  // el objeto umbral seteado con
  // elementTypeId, isDelivery y activity_id
  // Inicializar variable Boolean found = FALSE
  // 1.5: Recorrer esa colecciÃ³n de umbrales mientras includedInItinerary=TRUE AND found=FALSE,
  // y accedo a los atributos lower_limit y upper_limit
  // 1.6: SI (acumuladorSumaPesosActividad >= lower_limit AND acumuladorSumaPesosActividad <=
  // upper_limit) THEN
  // 1.6.a: Recojo el atributo 'threshold', y lo seteo en atributo 'inferred_threshold' del
  // objeto ActividadItinerario_i
  // 1.6.b: Setear la variable found = TRUE
  // 1.7: Setear atributo 'exclude_unreached_threshold' del objeto ActividadItinerario_i con
  // variable includedInItinerary
  // 1.8: save to table

  /****** Implementamos el algoritmo ***/
  @Transactional
  public ItinerarioCalidad calcularActividadItinerarioWithDetailedInfo(
      final ReplicaElementOEntrega elemOrDelivery) {

    Integer elementInstanceId = elemOrDelivery.getId();
    Integer elementTypeId = elemOrDelivery.getCatalogElementTypeId();
    Boolean isDelivery = elemOrDelivery.getDelivery();
    List<ValorEje> valoresAttrsYejes = elemOrDelivery.getAttributeValuesCollection();
    List<Integer> ejes = List.of(Constantes.NUMBER_1, Constantes.NUMBER_2,
        Constantes.NUMBER_3, Constantes.NUMBER_4, Constantes.NUMBER_5, Constantes.NUMBER_6,
        Constantes.NUMBER_7, Constantes.NUMBER_8, Constantes.NUMBER_9, Constantes.NUMBER_10,
        Constantes.NUMBER_11, Constantes.NUMBER_12, Constantes.NUMBER_13, Constantes.NUMBER_14,
        Constantes.NUMBER_15, Constantes.NUMBER_16, Constantes.NUMBER_17, Constantes.NUMBER_18,
        Constantes.NUMBER_19, Constantes.NUMBER_20, Constantes.NUMBER_21, Constantes.NUMBER_22);
    Map<Integer, Integer> mapOfAxisWithValuesDomain = new HashMap<>();
    for (ValorEje valorEjeOatributo : valoresAttrsYejes) {
      Integer valueOfDomainId = valorEjeOatributo.getDomainValueId();
      Integer axisOrAttributeId = valorEjeOatributo.getAxisAttributeId();
      for (Integer ejeId : ejes) {
        if (ejeId == axisOrAttributeId) {
          mapOfAxisWithValuesDomain.put(axisOrAttributeId, valueOfDomainId);
        }
      }
    }
    Timestamp fecCreacion = new Timestamp(Calendar.getInstance().getTime().getTime());
    ItinerarioCalidad itinerarioCalidad = new ItinerarioCalidad();
    itinerarioCalidad.setCatalogueId(elementInstanceId);
    itinerarioCalidad.setDelivery(isDelivery);
    itinerarioCalidad.setCreationDate(fecCreacion);
    itinerarioCalidad = this.itinerarioCalidadRepo.save(itinerarioCalidad);
    List<ActividadItinerario> actividadesItinerario = new ArrayList<>();
    List<ActividadQA> actividadesQA = this.qAactividadRepository.findAllByDeletedIsNull(
        Sort.by(Sort.Order.asc("testingStageId")));
    Integer sumWeightsAllActivities = Constantes.NUMBER_0;
    for (ActividadQA actividadQA : actividadesQA) {
      String observations = "";
      ActividadItinerario actividadItinerarioIesima = new ActividadItinerario();
      actividadItinerarioIesima.setCreationDate(fecCreacion);
      actividadItinerarioIesima.setQualityItineraryId(itinerarioCalidad.getId());
      actividadItinerarioIesima.setActivityId(actividadQA.getId());
      actividadItinerarioIesima.setCreationDate(
          new Timestamp(Calendar.getInstance().getTime().getTime()));
      String etapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
          actividadQA.getTestingStageId()).getName();
      actividadItinerarioIesima.setHelp("Actividad--> " + etapa + " - " + actividadQA.getName());
      Integer acumuladorSumaPesosActividad = Constantes.NUMBER_0;
      Boolean includedInItinerary = true;
      for (Integer axisId : mapOfAxisWithValuesDomain.keySet()) {
        Integer valorDominioId = mapOfAxisWithValuesDomain.get(axisId);
        Collection<Peso> pesosFound =
            this.pesoRepository.
                findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
                elementTypeId, isDelivery, actividadQA.getId(), axisId);
        if (pesosFound.isEmpty()) {
          EjeHeredable ejeHeredable = this.ejeHeredableRepository.
              findByElementTypeIdAndAxisIdAndForDeliveryAndDeletedIsNull(
              elementTypeId, axisId, isDelivery);

          if (ejeHeredable != null
              && isDelivery) {
            pesosFound =
                this.pesoRepository.
                    findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
                    elementTypeId, false, actividadQA.getId(), axisId);
          } else if (ejeHeredable != null && elementTypeId < Constantes.NUMBER_3) {
            pesosFound =
                this.pesoRepository.
                    findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
                    elementTypeId + Constantes.NUMBER_1, false,
                        actividadQA.getId(), axisId);
          }
        }
        for (Peso peso : pesosFound) {
          if (peso.getDomainValueId() == valorDominioId) {
            if (peso.getWeightValue() == Constantes.NUMBER_MINUS_ONE) {
              actividadItinerarioIesima.setInferredThreshold(
                  "Actividad excluida por valor de dominio existente");
              observations =
                  "Esta actividad no se incluye en el itinerario; valor -1 (EXLUYE) para el eje "
                      + axisId + " para el valor de dominio existente";
              includedInItinerary = false;
            } else {
              acumuladorSumaPesosActividad += peso.getWeightValue();
              sumWeightsAllActivities += acumuladorSumaPesosActividad;
            }
            break;
          }
        }
      }
      actividadItinerarioIesima.setActivitSumOfAxes(acumuladorSumaPesosActividad);
      Collection<UmbralActividad> umbralesActividad =
          this.umbralActividadRepository.
              findAllByDeletedIsNullAndElemenTypeIdAndForDeliveryAndActivityId(
              elementTypeId, isDelivery, actividadQA.getId());

      if (includedInItinerary) {
        Boolean found = false;
        Iterator<UmbralActividad> umbralesIterator = umbralesActividad.iterator();
        while (umbralesIterator.hasNext() && !found) {
          UmbralActividad umbralActividad = umbralesIterator.next();
          if (acumuladorSumaPesosActividad >= umbralActividad.getLowerLimit()
              && acumuladorSumaPesosActividad <= umbralActividad.getUpperLimit()) {
            actividadItinerarioIesima.setInferredThreshold(umbralActividad.getThreshold());
            observations = "Actividad INCLUIDA en itinerario; sumatorio de pesos-ejes ("
                + acumuladorSumaPesosActividad + ") ";
            if (umbralActividad.getUpperLimit() < Constantes.NUMBER_9999) {
              observations += " estÃ¡ dentro del rango [" + umbralActividad.getLowerLimit() + " , "
                  + umbralActividad.getUpperLimit() + "]";
            } else {
              observations += " es igual o mayor a " + umbralActividad.getLowerLimit();
            }
            found = true;
          }
        }
        if (!found) {
          includedInItinerary = false;
          actividadItinerarioIesima.setInferredThreshold(
              "Actividad excluida, no alcanza el umbral de realización mínimo");
          observations =
              "Esta actividad no se incluye en el itinerario; el sumatorio de pesos-ejes "
                  + acumuladorSumaPesosActividad
                  + " no se encuentra en los rangos definidos de umbrales";
        }
      }
      actividadItinerarioIesima.setObservations(observations);
      actividadItinerarioIesima.setIncludedInItinerary(includedInItinerary);
      this.actividadItinerarioRepository.save(actividadItinerarioIesima);
      actividadesItinerario.add(actividadItinerarioIesima);
    }
    if (sumWeightsAllActivities == Constantes.NUMBER_0) {
      itinerarioCalidad.setActividadesDeItinerario(new ArrayList<>());
    } else {
      itinerarioCalidad.setActividadesDeItinerario(actividadesItinerario);
      this.itinerarioCalidadRepo.saveAndFlush(itinerarioCalidad);
    }

    return itinerarioCalidad;

  }

}
