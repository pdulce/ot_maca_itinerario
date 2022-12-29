package giss.mad.marcocalidad.itinerariocalidad.service;

import giss.mad.marcocalidad.itinerariocalidad.model.EtapaPruebas;
import giss.mad.marcocalidad.itinerariocalidad.repo.EtapaPruebasRepository;
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

    private static final List<String> etapas = List.of("DiseÃ±o", "AnÃ¡lisis de cÃ³digo estÃ¡tico", "Desarrollo", "IntegraciÃ³n",
            "Pruebas de AceptaciÃ³n", "Funcionales", "Rendimiento", "Seguridad", "Accesibilidad", "Usabilidad", "AutomatizaciÃ³n de Pruebas",
            "AnÃ¡lisis de IA");

    public Collection<EtapaPruebas> getAll() {
        return this.etapaPruebasRepository.findAllByDeletedIsNull();
    }

    public EtapaPruebas get(Integer idActividadQA) {
        return this.etapaPruebasRepository.findByIdAndDeletedIsNull(idActividadQA);
    }

    @Transactional
    public EtapaPruebas remove(Integer idetapaPruebas) {
        EtapaPruebas etapaPruebas = this.etapaPruebasRepository.findByIdAndDeletedIsNull(idetapaPruebas);
        if (this.etapaPruebasRepository.findByIdAndDeletedIsNull(etapaPruebas.getId()) != null) {
            this.etapaPruebasRepository.delete(etapaPruebas);
        }
        return etapaPruebas;
    }

    @Transactional
    public EtapaPruebas save(EtapaPruebas actividadQA) {
        return this.etapaPruebasRepository.save(actividadQA);
    }

    @Transactional
    public EtapaPruebas update(EtapaPruebas etapaPruebas) {
        if (this.etapaPruebasRepository.findByIdAndDeletedIsNull(etapaPruebas.getId()) != null) {
            return this.etapaPruebasRepository.save(etapaPruebas);
        }
        return null;
    }

    @Transactional
    public void initializeDB() {
        int id = 1;
        for (String etapaName: etapas){
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
