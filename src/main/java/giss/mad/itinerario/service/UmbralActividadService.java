package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.model.auxumbrales.StageBuble;
import giss.mad.itinerario.model.auxumbrales.Umbral;
import giss.mad.itinerario.model.auxumbrales.UmbralBuble;
import giss.mad.itinerario.model.auxumbrales.UmbralGraph;
import giss.mad.itinerario.model.auxumbrales.UmbralesIniciador;
import giss.mad.itinerario.model.auxumbrales.elementCat.AuxActividadElem;
import giss.mad.itinerario.model.repository.ActividadQARepository;
import giss.mad.itinerario.model.repository.EtapaPruebasRepository;
import giss.mad.itinerario.model.repository.PesoRepository;
import giss.mad.itinerario.model.repository.UmbralActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
public class UmbralActividadService {

  public static final int[] AXE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
      19, 20, 21, 22, 23};

  @Autowired
  private UmbralActividadRepository umbralActividadRepository;

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;
  @Autowired
  private ActividadQARepository actividadRepository;
  @Autowired
  private PesoRepository pesoRepository;

  public Collection<UmbralActividad> getAll() {
    return this.umbralActividadRepository.findAllByDeletedIsNull();
  }

  public Collection<UmbralActividad> getUmbralesByIdActividad(Integer idElementType,
      Boolean isDelivery, Integer idActividad) {
    return this.umbralActividadRepository.findAllByDeletedIsNullAndElemenTypeIdAndForDeliveryAndActivityId(
        idElementType, isDelivery, idActividad);
  }

  public Collection<UmbralGraph> getUmbralesByTypeOfElement(Integer idElementType,
      Boolean isDelivery) {

    Collection<UmbralActividad> c = this.umbralActividadRepository.findAllByDeletedIsNullAndElemenTypeIdAndForDelivery(
        idElementType, isDelivery);

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


  public Collection<StageBuble> getUmbralesByStage(Integer idElementType, Boolean isDelivery) {

    Collection<UmbralActividad> c = this.umbralActividadRepository.findAllByDeletedIsNullAndElemenTypeIdAndForDelivery(
        idElementType, isDelivery);

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
      public int compare(StageBuble o1, StageBuble o2) {
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


  public UmbralActividad get(Integer idUmbral) {
    return this.umbralActividadRepository.findByIdAndDeletedIsNull(idUmbral);
  }

  @Transactional
  public UmbralActividad remove(Integer idUmbralActividad) {
    UmbralActividad umbralActividad = this.umbralActividadRepository.findByIdAndDeletedIsNull(
        idUmbralActividad);
    if (this.umbralActividadRepository.findByIdAndDeletedIsNull(umbralActividad.getId()) != null) {
      this.umbralActividadRepository.delete(umbralActividad);
    }
    return umbralActividad;
  }

  @Transactional
  public UmbralActividad save(UmbralActividad umbralActividad) {
    return this.umbralActividadRepository.save(umbralActividad);
  }

  @Transactional
  public UmbralActividad update(UmbralActividad umbralActividad) {
    if (this.umbralActividadRepository.findByIdAndDeletedIsNull(umbralActividad.getId()) != null) {
      return this.umbralActividadRepository.save(umbralActividad);
    }
    return null;
  }

  private Integer maxOf(List<Peso> pesosDeEje) {
    Integer max = 0;
    for (Peso peso : pesosDeEje) {
      if (max < peso.getWeightValue()) {
        max = peso.getWeightValue();
      }
    }
    return max;
  }

  public Integer getMaximumOfWeigths(Integer elementType, Boolean isDelivery, Integer idActivity) {
    Integer sumaOfMaxAxisPesosForActivity = 0;
    for (int i = 1; i < AXE.length - 1; i++) {
      sumaOfMaxAxisPesosForActivity += maxOf(
          this.pesoRepository.findAllByDeletedIsNullAndElementTypeIdAndForDeliveryAndActivityIdAndAxisAttributeId(
              elementType, isDelivery, idActivity, AXE[i]));
    }
    return sumaOfMaxAxisPesosForActivity;
  }

  @Transactional
  public void initializeDB() {
    this.umbralActividadRepository.deleteAll();
    //TODO

    Map<AuxActividadElem, Integer> maxPuntuacionesPesosActividades = new HashMap<>();
    Collection<ActividadQA> actividades = this.actividadRepository.findAllByDeletedIsNull(
        Sort.by(Sort.Order.asc("testingStageId")));
    for (ActividadQA actividad : actividades) {
      Integer sumaPesosActividad_i_Promo = getMaximumOfWeigths(1, false, actividad.getId());
      AuxActividadElem aux1 = new AuxActividadElem(1, actividad.getId(), false);
      maxPuntuacionesPesosActividades.put(aux1, sumaPesosActividad_i_Promo);
      Integer sumaPesosActividad_i_EntregaPromo = getMaximumOfWeigths(1, false, actividad.getId());
      AuxActividadElem aux2 = new AuxActividadElem(1, actividad.getId(), true);
      maxPuntuacionesPesosActividades.put(aux2, sumaPesosActividad_i_EntregaPromo);

      Integer sumaPesosActividad_i_AgrupFunc = getMaximumOfWeigths(2, false, actividad.getId());
      AuxActividadElem aux3 = new AuxActividadElem(2, actividad.getId(), false);
      maxPuntuacionesPesosActividades.put(aux3, sumaPesosActividad_i_AgrupFunc);
      Integer sumaPesosActividad_i_EntregaAgrupFunc = getMaximumOfWeigths(2, false,
          actividad.getId());
      AuxActividadElem aux4 = new AuxActividadElem(2, actividad.getId(), true);
      maxPuntuacionesPesosActividades.put(aux4, sumaPesosActividad_i_EntregaAgrupFunc);

      Integer sumaPesosActividad_i_Proyecto = getMaximumOfWeigths(3, false, actividad.getId());
      AuxActividadElem aux5 = new AuxActividadElem(3, actividad.getId(), false);
      maxPuntuacionesPesosActividades.put(aux5, sumaPesosActividad_i_Proyecto);
      Integer sumaPesosActividad_i_EntregaProyecto = getMaximumOfWeigths(3, false,
          actividad.getId());
      AuxActividadElem aux6 = new AuxActividadElem(3, actividad.getId(), false);
      maxPuntuacionesPesosActividades.put(aux6, sumaPesosActividad_i_EntregaProyecto);
    }

    Map<Map<Integer, Boolean>, Map<Integer, List<Umbral>>> elementsOfCatalogo = new UmbralesIniciador().getElementsOfCatalogo();
    int id = 1;
    for (Map<Integer, Boolean> idElement_withIsDelivery : elementsOfCatalogo.keySet()) {
      Integer idOfElement = idElement_withIsDelivery.keySet().iterator().next();
      Boolean isDelivery = idElement_withIsDelivery.get(idOfElement);
      Map<Integer, List<Umbral>> activitiesMap = elementsOfCatalogo.get(idElement_withIsDelivery);
      for (Integer idOfActivitiy : activitiesMap.keySet()) {
        String nombreActividad = this.actividadRepository.findByIdAndDeletedIsNull(idOfActivitiy)
            .getName();
        List<Umbral> listaUmbrales = activitiesMap.get(idOfActivitiy);
        for (Umbral umbralObject : listaUmbrales) {
          /*** creamos el umbral ***/
          UmbralActividad umbralActividad = new UmbralActividad();
          umbralActividad.setId(id++);
          umbralActividad.setForDelivery(isDelivery);
          umbralActividad.setElemenTypeId(idOfElement);
          umbralActividad.setActivityId(idOfActivitiy);
          umbralActividad.setThreshold(umbralObject.getThreshold());
          umbralActividad.setHelp(
              "RecomendaciÃ³n de % realizaciÃ³n mÃ­nima de consecuciÃ³n en las tareas de calidad de la actividad <<"
                  + nombreActividad + ">>");
          umbralActividad.setLowerLimit(umbralObject.getLowerRange());
          umbralActividad.setUpperLimit(umbralObject.getUpperRange());
          umbralActividad.setExcludeUnreachedThreshold(
              umbralObject.isExcluded_unreached_threshold());
          umbralActividad.setCreationDate(
              new Timestamp(Calendar.getInstance().getTime().getTime()));
          this.save(umbralActividad);
          /** fin grabacion umbral ***/
        }//for de umbral-actividad
      }//mapa actividades del elemento catalogo
    }//for elementos de catalogo
  }
}
