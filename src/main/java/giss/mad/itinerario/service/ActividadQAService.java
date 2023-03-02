package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.model.volatilentities.ActividadReduced;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import giss.mad.itinerario.repository.PesoRepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


@Service
public class ActividadQAService {

  @Autowired
  private ActividadQARepository actividadQARepository;

  @Autowired
  private UmbralActividadRepository umbralActividadRepository;

  @Autowired
  private PesoRepository pesoRepository;

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;

  public final void setActividadQARepository(final ActividadQARepository actividadQARepository) {
    this.actividadQARepository = actividadQARepository;
  }
  public final void setEtapaPruebasRepository(final EtapaPruebasRepository etapaPruebasRepository) {
    this.etapaPruebasRepository = etapaPruebasRepository;
  }
  public final void setPesoRepository(final PesoRepository pesoRepository) {
    this.pesoRepository = pesoRepository;
  }
  public final void setUmbralActividadRepository(final UmbralActividadRepository umbralActividadRepository) {
    this.umbralActividadRepository = umbralActividadRepository;
  }

  public final Collection<ActividadReduced> getIdAndNameOfActivities() {
    Collection<ActividadReduced> listaActividadesReduced = new ArrayList<>();
    Collection<ActividadQA> all = getAll();
    for (ActividadQA actividadQA : all) {
      ActividadReduced newAct = new ActividadReduced(actividadQA.getId(), actividadQA.getName());
      listaActividadesReduced.add(newAct);
    }
    return listaActividadesReduced;
  }
  public final Collection<ActividadQA> getAll() {
    Collection<ActividadQA> c = this.actividadQARepository.findAllByDeletedIsNull(
        Sort.by(Sort.Order.asc("testingStageId")));
    for (ActividadQA actividadQA : c) {
      String nameOfEtapa = "";
      if (actividadQA.getTestingStageId() != null) {
        nameOfEtapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(actividadQA.getTestingStageId()).getName();
      }
      actividadQA.setStageQAName(nameOfEtapa);
    }
    return c;
  }

  public final ActividadQA get(final Integer idActividadQA) {
    return this.actividadQARepository.findByIdAndDeletedIsNull(idActividadQA);
  }

  @Transactional
  public final ActividadQA borradoLogico(final Integer idActividadQA) {
    ActividadQA actividad = this.get(idActividadQA);
    if (actividad != null) {
      Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
      actividad.setUpdateDate(timeStamp);
      actividad.setName(actividad.getName() + "[deleted at " + timeStamp.getTime() + "]");
      actividad.setDeleted(1);
      this.actividadQARepository.save(actividad);
      //borramos sus pesos y umbrales
      Peso pesoFilter = new Peso();
      pesoFilter.setActivityId(idActividadQA);
      Collection<Peso> pesos = this.pesoRepository.findAll(Example.of(pesoFilter));
      this.pesoRepository.deleteAll(pesos);

      UmbralActividad umbralFilter = new UmbralActividad();
      umbralFilter.setActivityId(idActividadQA);
      Collection<UmbralActividad> umbrales = this.umbralActividadRepository.findAll(Example.of(umbralFilter));
      this.umbralActividadRepository.deleteAll(umbrales);
    }
    return actividad;
  }

  @Transactional
  public final ActividadQA insertar(final ActividadQA actividadQA) {
    actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
    String nameOfEtapa = "";
    if (actividadQA.getTestingStageId() != null) {
      nameOfEtapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(actividadQA.getTestingStageId()).getName();
    }
    actividadQA.setStageQAName(nameOfEtapa);
    return this.actividadQARepository.save(actividadQA);
  }

  @Transactional
  public final ActividadQA actualizar(final ActividadQA actividadQAInput) {
    ActividadQA updatedObject = this.actividadQARepository.findByIdAndDeletedIsNull(actividadQAInput.getId());
    if (updatedObject != null) {
      actividadQAInput.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
      actividadQAInput.setCreationDate(updatedObject.getCreationDate());
      actividadQAInput.setUmbrales(updatedObject.getUmbrales());
      actividadQAInput.setPesos(updatedObject.getPesos());

      updatedObject = this.actividadQARepository.save(actividadQAInput);
      String nameOfEtapa = "";
      if (actividadQAInput.getTestingStageId() != null) {
        nameOfEtapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(updatedObject.getTestingStageId()).getName();
      }
      updatedObject.setStageQAName(nameOfEtapa);
    }
    return updatedObject;
  }

  @Transactional
  public final ActividadQA actualizarPesos(final ActividadQA actividadQAInput) {
    ActividadQA updatedObject = this.actividadQARepository.findByIdAndDeletedIsNull(actividadQAInput.getId());
    if (updatedObject != null) {
      String nameOfEtapa = "";
      if (updatedObject.getTestingStageId() != null) {
        nameOfEtapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(updatedObject.getTestingStageId()).getName();
      }
      updatedObject.setStageQAName(nameOfEtapa);
      //tomamos los pesos de la entrada
      for (Peso peso: actividadQAInput.getPesos()) {
        Peso pesoBBDD = this.pesoRepository.findByIdAndDeletedIsNull(peso.getId());
        pesoBBDD.setWeightValue(peso.getWeightValue());
        pesoBBDD.setForDelivery(peso.getForDelivery());
        pesoBBDD.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        this.pesoRepository.save(pesoBBDD);
      }
    }
    return updatedObject;
  }

  @Transactional
  public final ActividadQA actualizarUmbrales(final ActividadQA actividadQAInput) {
    ActividadQA updatedObject = this.actividadQARepository.findByIdAndDeletedIsNull(actividadQAInput.getId());
    if (updatedObject != null) {
      String nameOfEtapa = "";
      if (updatedObject.getTestingStageId() != null) {
        nameOfEtapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(updatedObject.getTestingStageId()).getName();
      }
      updatedObject.setStageQAName(nameOfEtapa);
      //tomamos los umbrales de la entrada
      for (UmbralActividad umbralActividad: actividadQAInput.getUmbrales()) {
        UmbralActividad umbralActividadBBDD = this.umbralActividadRepository.findByIdAndDeletedIsNull(
                umbralActividad.getId());
        umbralActividadBBDD.setLowerLimit(umbralActividad.getLowerLimit());
        umbralActividadBBDD.setUpperLimit(umbralActividad.getUpperLimit());
        umbralActividadBBDD.setThreshold(umbralActividad.getThreshold());
        umbralActividadBBDD.setForDelivery(umbralActividad.getForDelivery());
        umbralActividadBBDD.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        this.umbralActividadRepository.save(umbralActividadBBDD);
      }
    }
    return updatedObject;
  }



}
