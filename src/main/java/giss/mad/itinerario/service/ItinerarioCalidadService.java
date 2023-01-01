package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadItinerario;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.ItinerarioCalidad;
import giss.mad.itinerario.model.auxitinerario.ActividadQAPantalla;
import giss.mad.itinerario.model.auxumbrales.StageBuble;
import giss.mad.itinerario.model.auxumbrales.UmbralBuble;
import giss.mad.itinerario.model.repository.ActividadQARepository;
import giss.mad.itinerario.model.repository.EtapaPruebasRepository;
import giss.mad.itinerario.model.repository.ItinerarioCalidadRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ItinerarioCalidadService {

  @Autowired
  private ItinerarioCalidadRepository itinerarioCalidadRepository;

  @Autowired
  private ActividadQARepository actividadQARepository;

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;

  public Collection<ItinerarioCalidad> getAll() {
    return this.itinerarioCalidadRepository.findAll();
  }

  public Collection<ItinerarioCalidad> getAllItinerariosByIdElementOrEntrega(
      final Integer idElementoOrEntrega, final Boolean isDelivery) {
    return this.itinerarioCalidadRepository.findAllByCatalogueIdAndDelivery(idElementoOrEntrega,
        isDelivery, Sort.by(Sort.Order.desc("creationDate")));
  }

  public ItinerarioCalidad getItinerarioMasRecienteByIdElementOrEntrega(
      final Integer idElementoOrEntrega,final Boolean isDelivery) {

    List<ItinerarioCalidad> itList = new ArrayList<>(
        this.itinerarioCalidadRepository.findAllByCatalogueIdAndDelivery(idElementoOrEntrega,
            isDelivery, Sort.by(Sort.Order.desc("creationDate"))));

    return itList.get(0);
  }

  public ItinerarioCalidad getByIdItinerario(final Integer idItinerario) {
    return this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(idItinerario);
  }

  public Collection<ActividadQAPantalla> getActivitiesByItineraryId(final Integer idItinerario,
      Boolean included) {
    Collection<ActividadQAPantalla> collection_ = new ArrayList<>();
    ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(
        idItinerario);
    if (itinerarioCalidad != null && !itinerarioCalidad.getActividadesDeItinerario().isEmpty()) {
      for (ActividadItinerario actividadItinerario : itinerarioCalidad.getActividadesDeItinerario()) {
        if (actividadItinerario.getIncludedInItinerary() == included) {
          ActividadQA actividad = this.actividadQARepository.findByIdAndDeletedIsNull(
              actividadItinerario.getActivityId());
          EtapaPruebas etapaPruebas = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
              actividad.getTestingStageId());
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

  public Collection<ActividadQAPantalla> getActivitiesByItineraryId(final Integer idItinerario) {
    Collection<ActividadQAPantalla> collection_ = new ArrayList<>();
    ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(
        idItinerario);
    if (itinerarioCalidad != null && !itinerarioCalidad.getActividadesDeItinerario().isEmpty()) {
      for (ActividadItinerario actividadItinerario : itinerarioCalidad.getActividadesDeItinerario()) {
        ActividadQA actividad = this.actividadQARepository.findByIdAndDeletedIsNull(
            actividadItinerario.getActivityId());
        EtapaPruebas etapaPruebas = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
            actividad.getTestingStageId());
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

  public Collection<StageBuble> getByIdItinerarioOnlyIncluded(final Integer idItinerario) {
    ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(
        idItinerario);
    List<StageBuble> stages4Bubles = new ArrayList<>();
    for (ActividadItinerario actividadItinerario : itinerarioCalidad.getActividadesDeItinerario()) {
      if (!actividadItinerario.getIncludedInItinerary()) {
        continue;
      }
      ActividadQA actividad = this.actividadQARepository.findByIdAndDeletedIsNull(
          actividadItinerario.getActivityId());
      UmbralBuble umbralBuble = new UmbralBuble(actividad.getName(),
          actividadItinerario.getInferredThreshold(), actividadItinerario.getActivitSumOfAxes());
      List<UmbralBuble> data;
      String stageName = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
          actividad.getTestingStageId()).getName();
      StageBuble stageBuble = StageBuble.obtenerStageBuble(stageName, stages4Bubles);
      if (stageBuble != null) {
        //el umbral que creemos lo aÃ±adimos la lista del stageObject
        data = stageBuble.getData();
      } else {
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
      public int compare(final StageBuble o1, final StageBuble o2) {
        if (o1.getId() > o2.getId()) {
          return 1;
        } else if (o1.getId() < o2.getId()) {
          return -1;
        } else {
          return 0;
        }
      }
    });
    return stages4Bubles;
  }

  @Transactional
  public ItinerarioCalidad remove(final Integer idItinerarioCalidad) {
    ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findById(
        idItinerarioCalidad).orElse(null);
    if (this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(itinerarioCalidad.getId())
        != null) {
      this.itinerarioCalidadRepository.delete(itinerarioCalidad);
    }
    return itinerarioCalidad;
  }

  @Transactional
  public ItinerarioCalidad save(final ItinerarioCalidad itinerarioCalidad) {
    return this.itinerarioCalidadRepository.save(itinerarioCalidad);
  }

  @Transactional
  public ItinerarioCalidad update(final ItinerarioCalidad itinerarioCalidad) {
    if (this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(itinerarioCalidad.getId())
        != null) {
      return this.itinerarioCalidadRepository.save(itinerarioCalidad);
    }
    return null;
  }


}
