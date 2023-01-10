package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.model.auxumbrales.StageBuble;
import giss.mad.itinerario.model.auxumbrales.UmbralBuble;
import giss.mad.itinerario.model.auxumbrales.UmbralGraph;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import giss.mad.itinerario.repository.PesoRepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UmbralActividadService {

  public static final int[] AXE = {Constantes.NUMBER_0, Constantes.NUMBER_1, Constantes.NUMBER_2,
      Constantes.NUMBER_3, Constantes.NUMBER_4, Constantes.NUMBER_5, Constantes.NUMBER_6,
      Constantes.NUMBER_7, Constantes.NUMBER_8, Constantes.NUMBER_9, Constantes.NUMBER_10,
      Constantes.NUMBER_11, Constantes.NUMBER_12, Constantes.NUMBER_13, Constantes.NUMBER_14,
      Constantes.NUMBER_15, Constantes.NUMBER_16, Constantes.NUMBER_17, Constantes.NUMBER_18,
      Constantes.NUMBER_19, Constantes.NUMBER_20, Constantes.NUMBER_21, Constantes.NUMBER_22,
      Constantes.NUMBER_23};

  @Autowired
  private UmbralActividadRepository umbralActividadRepository;
  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;
  @Autowired
  private ActividadQARepository actividadRepository;
  @Autowired
  private PesoRepository pesoRepository;

  public final Collection<UmbralActividad> getAll() {
    return this.umbralActividadRepository.findAllByDeletedIsNull();
  }

  public final Collection<UmbralActividad> getUmbralesByIdActividad(final Integer idElementType,
      final Integer isDelivery, final Integer idActividad) {
    return this.umbralActividadRepository.
        findAllByDeletedIsNullAndElemenTypeIdAndForDeliveryAndActivityId(
        idElementType, isDelivery, idActividad);
  }

  public final Collection<UmbralGraph> getUmbralesByTypeOfElement(final Integer idElementType,
      final Integer isDelivery) {
    Collection<UmbralActividad> c = this.umbralActividadRepository.
        findAllByDeletedIsNullAndElemenTypeIdAndForDelivery(idElementType, isDelivery);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    Collection<UmbralGraph> umbrales = new ArrayList<>();
    for (UmbralActividad umbral : c) {
      ActividadQA actividad = this.actividadRepository.findByIdAndDeletedIsNull(
          umbral.getActivityId());
      EtapaPruebas etapaPruebas = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
          actividad.getTestingStageId());
      UmbralGraph umbralGraph = new UmbralGraph(
          etapaPruebas.getName() + " - " + actividad.getName(),
          umbral.getActivityId(), umbral.getLowerLimit(), umbral.getUpperLimit(),
          umbral.getThreshold());
      umbrales.add(umbralGraph);
    }
    return umbrales;
  }

  public final Collection<StageBuble> getUmbralesByStage(final Integer idElementType,
      final Integer isDelivery) {
    Collection<UmbralActividad> c = this.umbralActividadRepository.
        findAllByDeletedIsNullAndElemenTypeIdAndForDelivery(idElementType, isDelivery);
    if (c == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    List<StageBuble> stages4Bubles = new ArrayList<>();
    for (UmbralActividad umbral : c) {
      ActividadQA actividad = this.actividadRepository.findByIdAndDeletedIsNull(
          umbral.getActivityId());
      UmbralBuble umbralBuble = new UmbralBuble(actividad.getName(), umbral.getLowerLimit(),
          umbral.getUpperLimit(), umbral.getThreshold());
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

  public final UmbralActividad get(final Integer idUmbral) {
    return this.umbralActividadRepository.findByIdAndDeletedIsNull(idUmbral);
  }

  @Transactional
  public final UmbralActividad remove(final Integer idUmbralActividad) {
    UmbralActividad removedObject = null;
    UmbralActividad umbralBBDD = this.umbralActividadRepository.findByIdAndDeletedIsNull(idUmbralActividad);
    if (umbralBBDD != null) {
      Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
      umbralBBDD.setUpdateDate(timeStamp);
      umbralBBDD.setDeleted(1);
      removedObject = this.umbralActividadRepository.saveAndFlush(umbralBBDD);
    }
    return removedObject;
  }

  @Transactional
  public final UmbralActividad save(final UmbralActividad umbralActividad) {
    return this.umbralActividadRepository.save(umbralActividad);
  }

  @Transactional
  public final UmbralActividad update(final UmbralActividad umbralActividad) {
    UmbralActividad updatedObject = null;
    if (this.umbralActividadRepository.findByIdAndDeletedIsNull(umbralActividad.getId()) != null) {
      updatedObject = this.umbralActividadRepository.save(umbralActividad);
    }
    return updatedObject;
  }

  private Integer maxOf(final List<Peso> pesosDeEje) {
    Integer max = Constantes.NUMBER_0;
    for (Peso peso : pesosDeEje) {
      if (max < peso.getWeightValue()) {
        max = peso.getWeightValue();
      }
    }
    return max;
  }

  public final Integer getMaximumOfWeigths(final Integer elementType, final Integer isDelivery,
      final Integer idActivity) {
    Integer sumaOfMaxAxisPesosForActivity = Constantes.NUMBER_0;
    for (int i = Constantes.NUMBER_1; i < AXE.length - Constantes.NUMBER_1; i++) {
      sumaOfMaxAxisPesosForActivity += maxOf(
          this.pesoRepository.findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
              elementType, isDelivery, idActivity, AXE[i]));
    }
    return sumaOfMaxAxisPesosForActivity;
  }

}
