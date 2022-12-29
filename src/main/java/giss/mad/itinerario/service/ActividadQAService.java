package giss.mad.marcocalidad.itinerariocalidad.service;

import giss.mad.marcocalidad.itinerariocalidad.model.ActividadQA;
import giss.mad.marcocalidad.itinerariocalidad.model.auxactiv.ActividadReduced;
import giss.mad.marcocalidad.itinerariocalidad.repo.ActividadQARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ActividadQAService {

    @Autowired
    private ActividadQARepository actividadQARepository;

    private static List<String> actividades = List.of("RevisiÃ³n Requisitos", "RevisiÃ³n de Historias de usuario" ,
            "DiseÃ±o planes prueba Funcional", "DiseÃ±o planes prueba Rendimiento", "AnÃ¡lisis de cÃ³digo estÃ¡tico" ,
            "Pruebas unitarias", "MOCS", "API Testing", "Pruebas de IntegraciÃ³n", "Pruebas de AceptaciÃ³n", "Pruebas Funcionales de ProgresiÃ³n" ,
            "Pruebas Funcionales de RegresiÃ³n", "Pruebas de Carga", "Pruebas de EstrÃ©s", "Pruebas de Picos", "Pruebas de Resistencia", "Pruebas de Escalabilidad", "Pruebas de Estabilidad",
            "Pruebas de Aislamiento", "Pruebas DAST",  "Pruebas IAST",  "Pen Testing",  "Pruebas de Contenedores", "Pruebas de Dependencias con terceros",
            "Pruebas de Accesibilidad", "EvaluaciÃ³n HeurÃ­stica", "Tests con usuarios", "OmniaUsability", "AutomatizaciÃ³n de Pruebas Funcionales", "AutomatizaciÃ³n de Pruebas de APIs", "AnÃ¡lisis de IA");

    private static Map<String, List<Integer>> umbralesIdeales = new HashMap<>();
    private static Map<Integer, Collection<Integer>> etapasYActividades = new HashMap<>();
    static{

        umbralesIdeales.put("Puntos lista de comprobaciÃ³n >= 80 % total", List.of(1, 2));
        umbralesIdeales.put("100 % en requisitos crÃ­ticos y 80 % en el resto", List.of(3));
        umbralesIdeales.put("95 % requisitos crÃ­ticos", List.of(4));
        umbralesIdeales.put("75 % total", List.of(5));
        umbralesIdeales.put("Funcionalidades crÃ­ticas 100 % OK, Resto de funcionalidades 80 % OK y SIN defectos bloqueantes", List.of(10));
        umbralesIdeales.put("Funcionalidades crÃ­ticas 100 % OK, Resto de funcionalidades 90 % OK y SIN defectos bloqueantes", List.of(11));
        umbralesIdeales.put( "Trans / seg un 15 % superior al nivel de accesos esperado y Porcentaje de errores < 5 % en funcionalidades crÃ­ticas", List.of(13,14,15,16,17,18));
        umbralesIdeales.put("Sin vulnerabilidades de criticidad 2 y 3 segÃºn criterios CSI, 100% crÃ­ticas y 80% resto", List.of(19,20,21,22,23));
        umbralesIdeales.put( "Nivel AA en pautas WCAG2  100% frontales crÃ­ticas y 80% del resto", List.of(24));
        umbralesIdeales.put("Cobertura del 75 % de funcionalidades crÃ­ticas y del 50 % en el resto", List.of(28));

        etapasYActividades.put(1, List.of(1, 2, 3, 4));
        etapasYActividades.put(2, List.of(5));
        etapasYActividades.put(3, List.of(6, 7));
        etapasYActividades.put(4, List.of(8, 9));
        etapasYActividades.put(5, List.of(10));
        etapasYActividades.put(6, List.of(11, 12));
        etapasYActividades.put(7, List.of(13,14,15,16,17,18,19));
        etapasYActividades.put(8, List.of(20,21,22,23,24));
        etapasYActividades.put(9, List.of(25));
        etapasYActividades.put(10, List.of(26,27,28));
        etapasYActividades.put(11, List.of(29,30));
        etapasYActividades.put(12, List.of(31));
    }

    public Collection<ActividadQA> getAll() {
        return this.actividadQARepository.findAllByDeletedIsNull(Sort.by(Sort.Order.asc("testingStageId")));
    }

    public ActividadQA get(Integer idActividadQA) {
        return this.actividadQARepository.findByIdAndDeletedIsNull(idActividadQA);
    }

    @Transactional
    public ActividadQA remove(Integer idActividadQA) {
        ActividadQA actividadQA = this.actividadQARepository.findByIdAndDeletedIsNull(idActividadQA);
        if (this.actividadQARepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
            this.actividadQARepository.delete(actividadQA);
        }
        return actividadQA;
    }

    @Transactional
    public ActividadQA save(ActividadQA actividadQA) {
        return this.actividadQARepository.save(actividadQA);
    }

    @Transactional
    public ActividadQA update(ActividadQA actividadQA) {
        if (this.actividadQARepository.findByIdAndDeletedIsNull(actividadQA.getId()) != null) {
            return this.actividadQARepository.save(actividadQA);
        }
        return null;
    }

    public Collection<ActividadReduced> getIdAndNameOfActivities() {
        Collection<ActividadReduced> listaActividadesReduced = new ArrayList<>();
        Collection<ActividadQA> all = getAll();
        for (ActividadQA actividadQA: all){
            ActividadReduced newAct = new ActividadReduced(actividadQA.getId(), actividadQA.getName());
            listaActividadesReduced.add(newAct);
        }
        return listaActividadesReduced;
    }

    @Transactional
    public void initializeDB() {
        int id = 1;
        for (String actividadName: actividades){
            ActividadQA actividadQA = new ActividadQA();
            actividadQA.setId(id);
            actividadQA.setName(actividadName);
            actividadQA.setHelp(actividadName);
            actividadQA.setDescription(actividadName);
            actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
            for (Integer idEtapa: etapasYActividades.keySet()){
                if (etapasYActividades.get(idEtapa).contains(id)){
                    actividadQA.setTestingStageId(idEtapa);
                    break;
                }
            }
            for (String umbralIdeal: umbralesIdeales.keySet()){
                if (umbralesIdeales.get(umbralIdeal).contains(id)){
                    actividadQA.setIdealThreshold(umbralIdeal);
                    break;
                }
            }
            this.save(actividadQA);
            id++;
        }
    }
}
