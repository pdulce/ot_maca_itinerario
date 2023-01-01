package giss.mad.itinerario.service;

import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.repository.EtapaPruebasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Service
public class EtapaPruebasService {

  @Autowired
  private EtapaPruebasRepository etapaPruebasRepository;

  private static final List<String> ETAPAS = List.of("Diseño",
      "Análisis de código estático", "Desarrollo", "Integración",
      "Pruebas de Aceptación", "Funcionales", "Rendimiento", "Seguridad", "Accesibilidad",
      "Usabilidad", "Automatización de Pruebas", "Análisis de IA");

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

  @Transactional
  public void initializeDB() {
    int id = Constantes.NUMBER_1;
    for (String etapaName : ETAPAS) {
      EtapaPruebas etapaPruebas = new EtapaPruebas();
      etapaPruebas.setId(id);
      etapaPruebas.setName(etapaName);
      etapaPruebas.setDescription(etapaName);
      etapaPruebas.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
      this.save(etapaPruebas);
      id++;
    }
  }
}
