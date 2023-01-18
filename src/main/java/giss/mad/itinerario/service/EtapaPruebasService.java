package giss.mad.itinerario.service;

import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class EtapaPruebasService {

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;

  public final void setEtapaPruebasRepository(final EtapaPruebasRepository etapaPruebasRepository) {
    this.etapaPruebasRepository = etapaPruebasRepository;
  }

  public final Collection<EtapaPruebas> getAll() {
    return this.etapaPruebasRepository.findAllByDeletedIsNull();
  }

  public final EtapaPruebas get(final Integer idActividadQA) {
    return this.etapaPruebasRepository.findByIdAndDeletedIsNull(idActividadQA);
  }

  @Transactional
  public final EtapaPruebas update(final EtapaPruebas etapaPruebas) {
    EtapaPruebas updatedObject = null;
    if (this.etapaPruebasRepository.findByIdAndDeletedIsNull(etapaPruebas.getId()) != null) {
      updatedObject = this.etapaPruebasRepository.save(etapaPruebas);
    }
    return updatedObject;
  }

}
