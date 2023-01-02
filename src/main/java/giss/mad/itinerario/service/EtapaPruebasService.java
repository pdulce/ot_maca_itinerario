package giss.mad.itinerario.service;

import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.repository.EtapaPruebasRepository;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtapaPruebasService {

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
    EtapaPruebas etapaPruebas = this.etapaPruebasRepository.findByIdAndDeletedIsNull(
        idetapaPruebas);
    if (this.etapaPruebasRepository.findByIdAndDeletedIsNull(etapaPruebas.getId()) != null) {
      this.etapaPruebasRepository.delete(etapaPruebas);
    }
    return etapaPruebas;
  }

  @Transactional
  public EtapaPruebas save(final EtapaPruebas actividadQA) {
    return this.etapaPruebasRepository.save(actividadQA);
  }

  @Transactional
  public EtapaPruebas update(final EtapaPruebas etapaPruebas) {
    if (this.etapaPruebasRepository.findByIdAndDeletedIsNull(etapaPruebas.getId()) != null) {
      return this.etapaPruebasRepository.save(etapaPruebas);
    }
    return null;
  }

}
