package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.model.volatilentities.ActividadReduced;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import giss.mad.itinerario.repository.PesoRepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},
        properties = {"spring.datasource.url:jdbc:h2:mem:testdb-2;INIT=create schema if not exists MACA_ITINERARIO"})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActividadQAServiceTest {

    @Autowired
    private ActividadQARepository actividadQARepository;
    @Autowired
    private UmbralActividadRepository umbralActividadRepository;
    @Autowired
    private PesoRepository pesoRepository;
    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private ActividadQAService actividadQAService;

    /*Objetos necesarios para las pruebas*/
    private static EtapaPruebas testStage;
    private static ActividadQA actividad;
    private static Peso peso1, peso2, peso3;
    private static UmbralActividad umbral;

    @BeforeAll
    public void setUp() {
        testStage = new EtapaPruebas();
        testStage.setName("Dummy stage");
        testStage.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        actividad = new ActividadQA();
        actividad.setName("Actividad dummy");
        actividad.setTestingStageId(1);
        actividad.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        ArrayList<ActividadQA> actividades = new ArrayList<ActividadQA>();
        actividades.add(actividad);
        testStage.setActividadesQA(actividades);

        peso1 = new Peso();
        peso1.setWeightValue(1);
        peso1.setElementTypeId(3);
        peso1.setForDelivery(0);
        peso1.setActivityId(1);
        peso1.setAxisAttributeId(1);
        peso1.setDomainValueId(1);
        peso1.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        peso2 = new Peso();
        peso2.setWeightValue(2);
        peso2.setElementTypeId(3);
        peso2.setForDelivery(0);
        peso2.setActivityId(1);
        peso2.setAxisAttributeId(1);
        peso2.setDomainValueId(1);
        peso2.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        peso3 = new Peso();
        peso3.setWeightValue(3);
        peso3.setElementTypeId(3);
        peso3.setForDelivery(0);
        peso3.setActivityId(1);
        peso3.setAxisAttributeId(1);
        peso3.setDomainValueId(1);
        peso3.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        umbral = new UmbralActividad();
        umbral.setLowerLimit(1);
        umbral.setUpperLimit(5);
        umbral.setActivityId(1);
        umbral.setElemenTypeId(1);
        umbral.setThreshold("Umbral dummy");
        umbral.setHelp("Dummy help");
        umbral.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        umbral.setForDelivery(0);

        this.etapaPruebasRepository.save(testStage);
        this.pesoRepository.save(peso1);
        this.pesoRepository.save(peso2);
        this.pesoRepository.save(peso3);
        this.umbralActividadRepository.save(umbral);

    }

    @Test
    @Transactional
    public void testActividadQA_getIdAndNameOfActivities() {
        Collection<ActividadReduced> listaActividadesReduced = this.actividadQAService.getIdAndNameOfActivities();
        assertFalse(listaActividadesReduced.isEmpty());
    }
    @Test
    @Transactional
    public void testActividadQA_getAll() {
        Collection<ActividadQA> actividades = this.actividadQAService.getAll();
        assertFalse(actividades.isEmpty());
    }
    @Test
    public void testActividadQA_get() {
        ActividadQA returnedActivity = this.actividadQAService.get(actividad.getId());
        assertNotNull(returnedActivity);
    }
    @Test
    public void testActividadQA_borradoLogico() {
        ActividadQA actividadQA = new ActividadQA();
        actividadQA.setName("Dummy name");
        actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        actividadQA.setPesos(new ArrayList<>());
        actividadQA.setUmbrales(new ArrayList<>());
        this.actividadQARepository.save(actividadQA);

        ActividadQA deletedActivity = this.actividadQAService.borradoLogico(actividadQA.getId());
        assertNotNull(deletedActivity);
    }
    @Test
    public void testActividadQA_insertar() {
        ActividadQA newActivity = new ActividadQA();
        newActivity.setName("Dummy name");
        newActivity.setPesos(new ArrayList<>());
        newActivity.setUmbrales(new ArrayList<>());

        ActividadQA createdActivity = this.actividadQAService.insertar(newActivity);
        assertNotNull(createdActivity);

        this.actividadQARepository.delete(newActivity);
    }

    @Test
    public void testActividadQA_actualizar() {
        ActividadQA actividadQA = new ActividadQA();
        actividadQA.setName("Dummy name");
        actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        actividadQA.setPesos(new ArrayList<>());
        actividadQA.setUmbrales(new ArrayList<>());
        this.actividadQARepository.save(actividadQA);

        ActividadQA updatedActivity = this.actividadQAService.actualizar(actividadQA);
        assertNotNull(updatedActivity.getUpdateDate());

        this.actividadQARepository.delete(actividadQA);
    }

    @Test
    @Transactional
    public void testActividadQA_actualizarPesos() {
        ArrayList<Peso> pesos = new ArrayList<>();
        Peso peso = new Peso();
        peso.setWeightValue(1);
        peso.setElementTypeId(3);
        peso.setForDelivery(0);
        peso.setActivityId(1);
        peso.setAxisAttributeId(1);
        peso.setDomainValueId(1);
        peso.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        pesos.add(peso);

        ActividadQA actividadQA = new ActividadQA();
        actividadQA.setName("Dummy name");
        actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        actividadQA.setPesos(pesos);
        actividadQA.setUmbrales(new ArrayList<>());
        this.actividadQARepository.save(actividadQA);

        ActividadQA activityWithUpdatedWeight = this.actividadQAService.actualizarPesos(actividadQA);
        assertNotNull(activityWithUpdatedWeight.getPesos().get(0).getUpdateDate());

        this.actividadQARepository.delete(actividadQA);
    }

    @Test
    @Transactional
    public void testActividadQA_actualizarUmbrales(){
        ArrayList<UmbralActividad> umbrales = new ArrayList<>();
        UmbralActividad umbralActividad = new UmbralActividad();
        umbralActividad.setLowerLimit(1);
        umbralActividad.setUpperLimit(5);
        umbralActividad.setActivityId(1);
        umbralActividad.setElemenTypeId(1);
        umbralActividad.setThreshold("Umbral dummy");
        umbralActividad.setHelp("Dummy help");
        umbralActividad.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        umbralActividad.setForDelivery(0);
        umbrales.add(umbralActividad);

        ActividadQA actividadQA = new ActividadQA();
        actividadQA.setName("Dummy name");
        actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        actividadQA.setPesos(new ArrayList<>());
        actividadQA.setUmbrales(umbrales);
        this.actividadQARepository.save(actividadQA);

        ActividadQA activityWithUpdatedThreshold = this.actividadQAService.actualizarUmbrales(actividadQA);
        assertNotNull(activityWithUpdatedThreshold.getUmbrales().get(0).getUpdateDate());

        this.actividadQARepository.delete(actividadQA);
    }
}
