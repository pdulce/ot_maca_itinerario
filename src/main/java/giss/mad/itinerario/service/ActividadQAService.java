package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.volatilentities.ActividadReduced;
import giss.mad.itinerario.repository.ActividadQARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;


@Service
public class ActividadQAService {

  @Autowired
  private ActividadQARepository actividadQARepository;

  public final void setActividadQARepository(final ActividadQARepository actividadQARepository) {
    this.actividadQARepository = actividadQARepository;
  }

  public final Collection<ActividadQA> getAll() {
    return this.actividadQARepository.findAllByDeletedIsNull(
        Sort.by(Sort.Order.asc("testingStageId")));
  }

  public final ActividadQA get(final Integer idActividadQA) {
    return this.actividadQARepository.findByIdAndDeletedIsNull(idActividadQA);
  }

  @Transactional
  public final ActividadQA removePhysical(final Integer idActividadQA) {
    ActividadQA actividad = this.get(idActividadQA);
    this.actividadQARepository.delete(actividad);
    return actividad;
  }

  @Transactional
  public final ActividadQA save(final ActividadQA actividadQA) {
    return this.actividadQARepository.save(actividadQA);
  }

  @Transactional
  public final ActividadQA update(final ActividadQA actividadQA) {
    ActividadQA updatedObject = null;
    if (this.actividadQARepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
      updatedObject = this.actividadQARepository.save(actividadQA);
    }
    return updatedObject;
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

}
