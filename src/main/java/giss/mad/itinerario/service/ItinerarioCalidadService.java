package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadItinerario;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.ItinerarioCalidad;
import giss.mad.itinerario.model.auxitinerario.ActividadQAPantalla;
import giss.mad.itinerario.model.auxumbrales.StageBuble;
import giss.mad.itinerario.model.auxumbrales.UmbralBuble;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import giss.mad.itinerario.repository.ItinerarioCalidadRepository;

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

  public final void setItinerarioCalidadRepository(final ItinerarioCalidadRepository itinerarioCalidadRepository) {
    this.itinerarioCalidadRepository = itinerarioCalidadRepository;
  }

  public final void setActividadQARepository(final ActividadQARepository actividadQARepository) {
    this.actividadQARepository = actividadQARepository;
  }

  public final void setEtapaPruebasRepository(final EtapaPruebasRepository etapaPruebasRepository) {
    this.etapaPruebasRepository = etapaPruebasRepository;
  }

  public final Collection<ItinerarioCalidad> getAll() {
    return this.itinerarioCalidadRepository.findAll();
  }

  public final Collection<ItinerarioCalidad> getAllItinerariosByIdElementOrEntrega(
      final Integer idElementoOrEntrega, final Integer isDelivery) {
    return this.itinerarioCalidadRepository.findAllByCatalogueIdAndDelivery(idElementoOrEntrega,
        isDelivery, Sort.by(Sort.Order.desc("creationDate")));
  }

  public final ItinerarioCalidad getItinerarioMasRecienteByIdElementOrEntrega(
      final Integer idElementoOrEntrega, final Integer isDelivery) {
    List<ItinerarioCalidad> itList = new ArrayList<>(
        this.itinerarioCalidadRepository.findAllByCatalogueIdAndDelivery(idElementoOrEntrega,
            isDelivery, Sort.by(Sort.Order.desc("creationDate"))));
    return itList.get(0);
  }

  public final ItinerarioCalidad getByIdItinerario(final Integer idItinerario) {
    return this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(idItinerario);
  }

  public final ItinerarioCalidad delete(final Integer idItinerario) {
    ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(idItinerario);
    this.itinerarioCalidadRepository.delete(itinerarioCalidad);
    return itinerarioCalidad;
  }


  public final Collection<ActividadQAPantalla> getActivitiesByItineraryId(final Integer idItinerario,
      final Integer included) {
    Collection<ActividadQAPantalla> actividadQAPantallaArrayList = new ArrayList<>();
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
          actividadQAPantallaArrayList.add(act4Screen);
        }
      }
    }
    return actividadQAPantallaArrayList;
  }

  public final Collection<ActividadQAPantalla> getActivitiesByItineraryId(final Integer idItinerario) {
    Collection<ActividadQAPantalla> actividadQAPantallaArrayList = new ArrayList<>();
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
        actividadQAPantallaArrayList.add(act4Screen);
      }
    }
    return actividadQAPantallaArrayList;
  }

  public final Collection<StageBuble> getByIdItinerarioOnlyIncluded(final Integer idItinerario) {
    ItinerarioCalidad itinerarioCalidad = this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(
        idItinerario);
    List<StageBuble> stages4Bubles = new ArrayList<>();
    for (ActividadItinerario actividadItinerario : itinerarioCalidad.getActividadesDeItinerario()) {
      if (actividadItinerario.getIncludedInItinerary() == Constantes.NUMBER_0) {
        ActividadQA actividad = this.actividadQARepository.findByIdAndDeletedIsNull(
                actividadItinerario.getActivityId());
        UmbralBuble umbralBuble = new UmbralBuble(actividad.getName(),
                actividadItinerario.getInferredThreshold(), actividadItinerario.getActivitSumOfAxes());
        List<UmbralBuble> data;
        String stageName = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
                actividad.getTestingStageId()).getName();
        StageBuble stageBuble = StageBuble.obtenerStageBuble(stageName, stages4Bubles);
        if (stageBuble != null) {
          data = stageBuble.getData();
        } else {
          data = new ArrayList<>();
          stageBuble = new StageBuble();
          stageBuble.setId(actividad.getTestingStageId());
          stageBuble.setName(stageName);
          stageBuble.setData(data);
          stages4Bubles.add(stageBuble);
        }
        data.add(umbralBuble);
      }
    }

    Collections.sort(stages4Bubles, new Comparator<StageBuble>() {
      @Override
      public int compare(final StageBuble o1, final StageBuble o2) {
        int retorno = 0;
        if (o1.getId() > o2.getId()) {
          retorno = 1;
        } else if (o1.getId() < o2.getId()) {
          retorno = -1;
        }
        return retorno;
      }
    });
    return stages4Bubles;
  }

  @Transactional
  public final ItinerarioCalidad update(final ItinerarioCalidad itinerarioCalidad) {
    ItinerarioCalidad updatedObject = null;
    if (this.itinerarioCalidadRepository.findByIdAndDeletedIsNull(itinerarioCalidad.getId())
        != null) {
      updatedObject = this.itinerarioCalidadRepository.save(itinerarioCalidad);
    }
    return updatedObject;
  }


}
