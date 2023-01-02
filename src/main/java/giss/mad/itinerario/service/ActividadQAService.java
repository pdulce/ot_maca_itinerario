package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.auxactiv.ActividadReduced;
import giss.mad.itinerario.model.repository.ActividadQARepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ActividadQAService {

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
    ActividadQA actividadQA = this.actividadQARepository.findByIdAndDeletedIsNull(idActividadQA);
    if (this.actividadQARepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
      this.actividadQARepository.delete(actividadQA);
    }
    return actividadQA;
  }

  @Transactional
  public ActividadQA save(final ActividadQA actividadQA) {
    return this.actividadQARepository.save(actividadQA);
  }

  @Transactional
  public ActividadQA update(final ActividadQA actividadQA) {
    if (this.actividadQARepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
      return this.actividadQARepository.save(actividadQA);
    }
    return null;
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
