package giss.mad.itinerario.service;

import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.auxactiv.ActividadReduced;
import giss.mad.itinerario.model.repository.ActividadQARepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;


@Service
public class ActividadQAService {

  @Autowired
  private ActividadQARepository actividadQARepository;

  private static final List<String> ACTIVIDADES = List.of("Revisión Requisitos",
      "Revisión de Historias de usuario",
      "Diseño planes prueba Funcional", "Diseño planes prueba Rendimiento",
      "Análisis de código estático",
      "Pruebas unitarias", "MOCS", "API Testing", "Pruebas de Integración",
      "Pruebas de Aceptación", "Pruebas Funcionales de Progresión",
      "Pruebas Funcionales de Regresión", "Pruebas de Carga", "Pruebas de Estrés",
      "Pruebas de Picos", "Pruebas de Resistencia", "Pruebas de Escalabilidad",
      "Pruebas de Estabilidad",
      "Pruebas de Aislamiento", "Pruebas DAST", "Pruebas IAST", "Pen Testing",
      "Pruebas de Contenedores", "Pruebas de Dependencias con terceros",
      "Pruebas de Accesibilidad", "Evaluación Heurística", "Tests con usuarios",
      "OmniaUsability",
      "Automatización de Pruebas Funcionales", "Automatización de Pruebas de APIs",
      "Análisis de IA");

  private static final Map<String, List<Integer>> UMBRALES_IDEALES = new HashMap<>();
  private static final Map<Integer, Collection<Integer>> ETAPAS_Y_ACTIVIDADES = new HashMap<>();

  static {

    UMBRALES_IDEALES.put("Puntos lista de comprobación >= 80 % total", List.of(Constantes.NUMBER_1,
        Constantes.NUMBER_2));
    UMBRALES_IDEALES.put("100 % en requisitos críticos y 80 % en el resto",
        List.of(Constantes.NUMBER_3));
    UMBRALES_IDEALES.put("95 % requisitos críticos", List.of(Constantes.NUMBER_4));
    UMBRALES_IDEALES.put("75 % total", List.of(Constantes.NUMBER_5));
    UMBRALES_IDEALES.put("Funcionalidades críticas 100 % OK, Resto de funcionalidades 80 % OK y "
        + "Sin defectos bloqueantes",  List.of(Constantes.NUMBER_10));
    UMBRALES_IDEALES.put(
        "Funcionalidades críticas 100 % OK, Resto de funcionalidades 90 % OK y SIN defectos "
            + "bloqueantes", List.of(Constantes.NUMBER_11));
    UMBRALES_IDEALES.put(
        "Trans / seg un 15 % superior al nivel de accesos esperado y Porcentaje de errores < 5 % en"
            + " funcionalidades críticas", List.of(Constantes.NUMBER_13, Constantes.NUMBER_14,
            Constantes.NUMBER_15, Constantes.NUMBER_16, Constantes.NUMBER_17,
            Constantes.NUMBER_18));
    UMBRALES_IDEALES.put(
        "Sin vulnerabilidades de criticidad 2 y 3 según criterios CSI, 100% críticas y 80% resto",
        List.of(Constantes.NUMBER_19, Constantes.NUMBER_20, Constantes.NUMBER_21,
            Constantes.NUMBER_22, Constantes.NUMBER_23));
    UMBRALES_IDEALES.put("Nivel AA en pautas WCAG2  100% frontales críticas y 80% del resto",
        List.of(Constantes.NUMBER_24));
    UMBRALES_IDEALES.put("Cobertura del 75 % de funcionalidades críticas y del 50 % en el resto",
        List.of(Constantes.NUMBER_28));

    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_1, List.of(Constantes.NUMBER_1, Constantes.NUMBER_2,
        Constantes.NUMBER_3, Constantes.NUMBER_4));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_2, List.of(Constantes.NUMBER_5));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_3, List.of(Constantes.NUMBER_6,
        Constantes.NUMBER_7));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_4, List.of(Constantes.NUMBER_8,
        Constantes.NUMBER_9));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_5, List.of(Constantes.NUMBER_10));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_6, List.of(Constantes.NUMBER_11,
        Constantes.NUMBER_12));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_7, List.of(Constantes.NUMBER_13,
        Constantes.NUMBER_14, Constantes.NUMBER_15, Constantes.NUMBER_16, Constantes.NUMBER_17,
        Constantes.NUMBER_18, Constantes.NUMBER_19));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_8, List.of(Constantes.NUMBER_20,
        Constantes.NUMBER_21, Constantes.NUMBER_22, Constantes.NUMBER_23, Constantes.NUMBER_24));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_9, List.of(Constantes.NUMBER_25));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_10, List.of(Constantes.NUMBER_26,
        Constantes.NUMBER_27, Constantes.NUMBER_28));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_11, List.of(Constantes.NUMBER_29,
        Constantes.NUMBER_30));
    ETAPAS_Y_ACTIVIDADES.put(Constantes.NUMBER_12, List.of(Constantes.NUMBER_31));
  }

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

  @Transactional
  public void initializeDB() {
    int id = Constantes.NUMBER_1;
    for (String actividadName : ACTIVIDADES) {
      ActividadQA actividadQA = new ActividadQA();
      actividadQA.setId(id);
      actividadQA.setName(actividadName);
      actividadQA.setHelp(actividadName);
      actividadQA.setDescription(actividadName);
      actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
      for (Integer idEtapa : ETAPAS_Y_ACTIVIDADES.keySet()) {
        if (ETAPAS_Y_ACTIVIDADES.get(idEtapa).contains(id)) {
          actividadQA.setTestingStageId(idEtapa);
          break;
        }
      }
      for (String umbralIdeal : UMBRALES_IDEALES.keySet()) {
        if (UMBRALES_IDEALES.get(umbralIdeal).contains(id)) {
          actividadQA.setIdealThreshold(umbralIdeal);
          break;
        }
      }
      this.save(actividadQA);
      id++;
    }
  }
}
