package giss.mad.marcocalidad.itinerariocalidad.service;

import giss.mad.marcocalidad.itinerariocalidad.model.*;
import giss.mad.marcocalidad.itinerariocalidad.model.auxitinerario.ActividadQAPantalla;
import giss.mad.marcocalidad.itinerariocalidad.model.auxumbrales.StageBuble;
import giss.mad.marcocalidad.itinerariocalidad.model.auxumbrales.UmbralBuble;
import giss.mad.marcocalidad.itinerariocalidad.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ItinerarioCalidadService {

    @Autowired
    private ItinerarioCalidadRepository itinerarioCalidadRepository;

    @Autowired
    private ActividadQARepository actividadQARepository;

    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;

    public Collection<ItinerarioCalidad> getAll(){
        return this.itinerarioCalidadRepository.findAll();
    }

    public Collection<ItinerarioCalidad> getAllItinerariosByIdElementOrEntrega(Integer idElementoOrEntrega, Boolean isDelivery) {
        return this.itinerarioCalidadRepository.findAllByCatalogueIdAndDelivery(idElementoOrEntrega, isDelivery, Sort.by(Sort.Order.desc("creationDate")));
    }

    public ItinerarioCalidad getItinerarioMasRecienteByIdElementOrEntrega(Integer idElementoOrEntrega, Boolean isDelivery) {

        List<ItinerarioCalidad> itList = new ArrayList<>(this.itinerarioCalidadRepository.findAllByCatalogueIdAndDelivery(idElementoOrEntrega, isDelivery, Sort.by(Sort.Order.desc("creationDate"))));

        return itList.get(0);
    }

    public ItinerarioCalidad getByIdItinerario(Integer idItinerario) {
        return this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(idItinerario);
    }

    public Collection<ActividadQAPantalla> getActivitiesByItineraryId(Integer idItinerario, Boolean included) {
        Collection<ActividadQAPantalla> collection_ = new ArrayList<>();
        ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(idItinerario);
        if (itinerarioCalidad != null && !itinerarioCalidad.getActividadesDeItinerario().isEmpty()){
            for (ActividadItinerario actividadItinerario: itinerarioCalidad.getActividadesDeItinerario()){
                if (actividadItinerario.getIncludedInItinerary() == included) {
                    ActividadQA actividad = this.actividadQARepository.findByIdAndDeletedIsNull(actividadItinerario.getActivityId());
                    EtapaPruebas etapaPruebas = this.etapaPruebasRepository.findByIdAndDeletedIsNull(actividad.getTestingStageId());
                    ActividadQAPantalla act4Screen = new ActividadQAPantalla();
                    act4Screen.setIncluded(actividadItinerario.getIncludedInItinerary());
                    act4Screen.setObservations(actividadItinerario.getObservations());
                    act4Screen.setRealization(actividadItinerario.getInferredThreshold());
                    act4Screen.setActivity(actividad.getName());
                    act4Screen.setStage(etapaPruebas.getName());
                    collection_.add(act4Screen);
                }
            }
        }
        return collection_;
    }

    public Collection<ActividadQAPantalla> getActivitiesByItineraryId(Integer idItinerario) {
        Collection<ActividadQAPantalla> collection_ = new ArrayList<>();
        ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(idItinerario);
        if (itinerarioCalidad != null && !itinerarioCalidad.getActividadesDeItinerario().isEmpty()){
            for (ActividadItinerario actividadItinerario: itinerarioCalidad.getActividadesDeItinerario()){
                ActividadQA actividad = this.actividadQARepository.findByIdAndDeletedIsNull(actividadItinerario.getActivityId());
                EtapaPruebas etapaPruebas = this.etapaPruebasRepository.findByIdAndDeletedIsNull(actividad.getTestingStageId());
                ActividadQAPantalla act4Screen = new ActividadQAPantalla();
                act4Screen.setIncluded(actividadItinerario.getIncludedInItinerary());
                act4Screen.setObservations(actividadItinerario.getObservations());
                act4Screen.setRealization(actividadItinerario.getInferredThreshold());
                act4Screen.setActivity(actividad.getName());
                act4Screen.setStage(etapaPruebas.getName());
                collection_.add(act4Screen);
            }
        }
        return collection_;
    }

    public Collection<StageBuble> getByIdItinerarioOnlyIncluded(Integer idItinerario) {
        ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(idItinerario);

        List<StageBuble> stages4Bubles = new ArrayList<>();
        for (ActividadItinerario actividadItinerario: itinerarioCalidad.getActividadesDeItinerario()){
            if (!actividadItinerario.getIncludedInItinerary()) {
                continue;
            }
            ActividadQA actividad = this.actividadQARepository.findByIdAndDeletedIsNull(actividadItinerario.getActivityId());
            UmbralBuble umbralBuble = new UmbralBuble(actividad.getName(), actividadItinerario.getInferredThreshold(), actividadItinerario.getActivitSumOfAxes());
            List<UmbralBuble> data;
            String stageName = this.etapaPruebasRepository.findByIdAndDeletedIsNull(actividad.getTestingStageId()).getName();
            StageBuble stageBuble = StageBuble.obtenerStageBuble(stageName, stages4Bubles);
            if ( stageBuble != null){
                //el umbral que creemos lo aÃ±adimos la lista del stageObject
                data = stageBuble.getData();
            }else{
                //creamos el state, y su lista desde cero
                data = new ArrayList<>();
                stageBuble = new StageBuble();
                stageBuble.setId(actividad.getTestingStageId());
                stageBuble.setName(stageName);
                stageBuble.setData(data);
                stages4Bubles.add(stageBuble);
            }
            data.add(umbralBuble);
        }
        Collections.sort(stages4Bubles, new Comparator<StageBuble>() {
            @Override
            public int compare(StageBuble o1, StageBuble o2) {
                if (o1.getId() > o2.getId()){
                    return 1;
                }else if (o1.getId() < o2.getId()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        return stages4Bubles;
    }

    @Transactional
    public ItinerarioCalidad remove(Integer idItinerarioCalidad) {
        ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findById(idItinerarioCalidad).orElse(null);
        if (this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(itinerarioCalidad.getId())!=null) {
            this.itinerarioCalidadRepository.delete(itinerarioCalidad);
        }
        return itinerarioCalidad;
    }

    @Transactional
    public ItinerarioCalidad save(ItinerarioCalidad itinerarioCalidad) {
        return this.itinerarioCalidadRepository.save(itinerarioCalidad);
    }

    @Transactional
    public ItinerarioCalidad update(ItinerarioCalidad itinerarioCalidad) {
        if (this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(itinerarioCalidad.getId())!=null) {
            return this.itinerarioCalidadRepository.save(itinerarioCalidad);
        }
        return null;
    }



}
