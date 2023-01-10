package giss.mad.itinerario.service;

import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.repository.EtapaPruebasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;

@Service
public final class EtapaPruebasService {

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;

  public Collection<EtapaPruebas> getAll() {
    return this.etapaPruebasRepository.findAllByDeletedIsNull();
  }

  public EtapaPruebas get(final Integer idActividadQA) {
    return this.etapaPruebasRepository.findByIdAndDeletedIsNull(idActividadQA);
  }

  @Transactional
  public EtapaPruebas remove(final Integer idetapaPruebas) {
    EtapaPruebas removedObject = null;
    EtapaPruebas etapaBBDD = this.etapaPruebasRepository.findByIdAndDeletedIsNull(idetapaPruebas);
    if (etapaBBDD != null) {
      Timestamp timeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
      etapaBBDD.setUpdateDate(timeStamp);
      etapaBBDD.setDeleted(1);
      removedObject = this.etapaPruebasRepository.saveAndFlush(etapaBBDD);
    }
    return removedObject;
  }

  @Transactional
  public EtapaPruebas save(final EtapaPruebas actividadQA) {
    return this.etapaPruebasRepository.save(actividadQA);
  }

  @Transactional
  public EtapaPruebas update(final EtapaPruebas etapaPruebas) {
    EtapaPruebas updatedObject = null;
    if (this.etapaPruebasRepository.findByIdAndDeletedIsNull(etapaPruebas.getId()) != null) {
      updatedObject = this.etapaPruebasRepository.save(etapaPruebas);
    }
    return updatedObject;
  }

}
