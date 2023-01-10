package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.auxactiv.ActividadReduced;
import giss.mad.itinerario.model.repository.ActividadQARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


@Service
public final class ActividadQAService {

  @Autowired
  private ActividadQARepository actividadQARepository;

  public Collection<ActividadQA> getAll() {
    return this.actividadQARepository.findAllByDeletedIsNull(
        Sort.by(Sort.Order.asc("testingStageId")));
  }

  public ActividadQA get(final Integer idActividadQA) {
    return this.actividadQARepository.findByIdAndDeletedIsNull(idActividadQA);
  }

  @Transactional
  public ActividadQA remove(final Integer idActividadQA) {
    ActividadQA removedObject = null;
    ActividadQA actividadBBDD = this.actividadQARepository.findByIdAndDeletedIsNull(idActividadQA);
    if (actividadBBDD != null) {
      Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
      actividadBBDD.setUpdateDate(timeStamp);
      actividadBBDD.setDeleted(1);
      removedObject = this.actividadQARepository.saveAndFlush(actividadBBDD);
    }
    return removedObject;
  }

  @Transactional
  public ActividadQA save(final ActividadQA actividadQA) {
    return this.actividadQARepository.save(actividadQA);
  }

  @Transactional
  public ActividadQA update(final ActividadQA actividadQA) {
    ActividadQA updatedObject = null;
    if (this.actividadQARepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
      updatedObject = this.actividadQARepository.save(actividadQA);
    }
    return updatedObject;
  }

  public Collection<ActividadReduced> getIdAndNameOfActivities() {
    Collection<ActividadReduced> listaActividadesReduced = new ArrayList<>();
    Collection<ActividadQA> all = getAll();
    for (ActividadQA actividadQA : all) {
      ActividadReduced newAct = new ActividadReduced(actividadQA.getId(), actividadQA.getName());
      listaActividadesReduced.add(newAct);
    }
    return listaActividadesReduced;
  }

}
