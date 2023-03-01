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
    /*for (Peso peso: actividadQA.getPesos()) {
      peso = pesoRepository.findByIdAndDeletedIsNull(peso.getId());
    }
    for (UmbralActividad umbral: actividadQA.getUmbrales()) {
      umbral = umbralActividadRepository.findByIdAndDeletedIsNull(umbral.getId());
    }*/
    return this.actividadQARepository.save(actividadQA);
  }

  @Transactional
  public final ActividadQA actualizar(final ActividadQA actividadQAInput) {
    ActividadQA updatedObject = this.actividadQARepository.findByIdAndDeletedIsNull(actividadQAInput.getId());
    if (updatedObject != null) {
      actividadQAInput.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
      actividadQAInput.setCreationDate(updatedObject.getCreationDate());
      actividadQAInput.setPesos(actividadQAInput.getPesos());
      actividadQAInput.setUmbrales(actividadQAInput.getUmbrales());
      updatedObject = this.actividadQARepository.save(actividadQAInput);
      String nameOfEtapa = "";
      if (actividadQAInput.getTestingStageId() != null) {
        nameOfEtapa = this.etapaPruebasRepository.findByIdAndDeletedIsNull(updatedObject.getTestingStageId()).getName();
      }
      updatedObject.setStageQAName(nameOfEtapa);
      /*for (Peso peso: actividadQA.getPesos()) {
        peso = pesoRepository.findByIdAndDeletedIsNull(peso.getId());
      }
      for (UmbralActividad umbral: actividadQA.getUmbrales()) {
        umbral = umbralActividadRepository.findByIdAndDeletedIsNull(umbral.getId());
      }*/
    }
    return updatedObject;
  }



}
