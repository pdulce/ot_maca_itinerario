package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Service
public class EtapaPruebasService {

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;

  @Autowired
  private ActividadQARepository actividadQARepository;

  public final void setEtapaPruebasRepository(final EtapaPruebasRepository etapaPruebasRepository) {
    this.etapaPruebasRepository = etapaPruebasRepository;
  }

  public final void setActividadQARepository(final ActividadQARepository actividadQARepository) {
    this.actividadQARepository = actividadQARepository;
  }

  public final Collection<EtapaPruebas> getAll() {
    return this.etapaPruebasRepository.findAllByDeletedIsNull(Sort.by("id"));
  }

  public final EtapaPruebas get(final Integer idActividadQA) {
    return this.etapaPruebasRepository.findByIdAndDeletedIsNull(idActividadQA);
  }

  @Transactional
  public final EtapaPruebas insertar(final EtapaPruebas etapaPruebas) {
    List<ActividadQA> listaActividades =  new ArrayList<>();
    listaActividades.addAll(etapaPruebas.getActividadesQA());

    etapaPruebas.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
    etapaPruebas.setActividadesQA(new ArrayList<>());
    EtapaPruebas updatedObject = this.etapaPruebasRepository.save(etapaPruebas);

    for (ActividadQA activityIn : listaActividades) {
      activityIn = this.actividadQARepository.findByIdAndDeletedIsNull(activityIn.getId());
      activityIn.setTestingStageId(updatedObject.getId());
      activityIn.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
    }
    if (!listaActividades.isEmpty()) {
      etapaPruebas.setActividadesQA(listaActividades);
      updatedObject = this.etapaPruebasRepository.save(etapaPruebas);
    }
    return updatedObject;
  }

  @Transactional
  public final EtapaPruebas actualizar(final EtapaPruebas etapaPruebas) {
    EtapaPruebas updatedObject = null;
    if (this.etapaPruebasRepository.findByIdAndDeletedIsNull(etapaPruebas.getId()) != null) {
      List<ActividadQA> listaActividades =  new ArrayList<>();
      listaActividades.addAll(etapaPruebas.getActividadesQA());

      etapaPruebas.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
      etapaPruebas.setActividadesQA(new ArrayList<>());
      etapaPruebas.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
      updatedObject = this.etapaPruebasRepository.save(etapaPruebas);

      List<ActividadQA> newListaActividades = new ArrayList<>();
      for (ActividadQA activityIn : listaActividades) {
        Integer activityInTestingId = activityIn.getTestingStageId();
        activityIn = this.actividadQARepository.findByIdAndDeletedIsNull(activityIn.getId());
        activityIn.setTestingStageId(activityInTestingId);
        activityIn.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        newListaActividades.add(activityIn);
      }
      if (!newListaActividades.isEmpty()) {
        etapaPruebas.setActividadesQA(newListaActividades);
        updatedObject = this.etapaPruebasRepository.save(etapaPruebas);
      }
    }
    return updatedObject;
  }

  @Transactional
  public final EtapaPruebas borradoLogico(final int idEtapaPruebas) {
    EtapaPruebas deletedObject = this.etapaPruebasRepository.findByIdAndDeletedIsNull(idEtapaPruebas);
    if (deletedObject != null) {
      ActividadQA filter = new ActividadQA();
      filter.setTestingStageId(idEtapaPruebas);
      List<ActividadQA> actividades = this.actividadQARepository.findAll(Example.of(filter));
      for (ActividadQA actividadQA: actividades) {
        actividadQA.setDeleted(1);
        actividadQA.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        this.actividadQARepository.save(actividadQA);
      }
      deletedObject.setDeleted(1);
      deletedObject.setUpdateDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
      this.etapaPruebasRepository.save(deletedObject);
    }
    return deletedObject;
  }

}
