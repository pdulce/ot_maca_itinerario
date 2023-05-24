package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.model.volatilentities.StageBuble;
import giss.mad.itinerario.model.volatilentities.UmbralGraph;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import giss.mad.itinerario.repository.PesoRepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},
        properties = {"spring.datasource.url:jdbc:h2:mem:testdb;INIT=create schema if not exists MACA_ITINERARIO"})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UmbralActividadServiceTest {
    @Autowired
    private UmbralActividadRepository umbralActividadRepository;
    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private ActividadQARepository actividadRepository;
    @Autowired
    private PesoRepository pesoRepository;
    @Autowired
    private UmbralActividadService umbralActividadService;
    @Autowired
    private ActividadQAService actividadQAService;
    @Autowired
    private PesoService pesoService;
    @Autowired
    private EtapaPruebasService etapaPruebasService;

    /*Objetos necesarios para las pruebas*/
    private static EtapaPruebas testStage;
    private static ActividadQA actividad;
    private static Peso peso1, peso2, peso3;
    private static UmbralActividad umbral;
    @BeforeAll
    public void setUp(){
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
    public void testUmbral_get() {
        UmbralActividad returnedThreshold = umbralActividadService.get(1);
        Assertions.assertEquals(umbral.getId(), returnedThreshold.getId());
    }

    @Test
    public void testUmbral_getUmbralesByStage() {
        Collection<StageBuble> thresholdsByStage = umbralActividadService.getUmbralesByStage(1, 0);
        assertFalse(thresholdsByStage.isEmpty());
    }
    @Test
    public void testUmbral_getUmbralesByTypeOfElement() {
        Collection<UmbralGraph> thresholdsByElemenType = umbralActividadService.getUmbralesByTypeOfElement(1, 0);
        assertFalse(thresholdsByElemenType.isEmpty());
    }
    @Test
    public void testUmbral_getMaximumOfWeights() {
        Integer maxWeight = umbralActividadService.getMaximumOfWeigths(3, 0, 1);
        Assertions.assertEquals(Integer.valueOf(3), maxWeight);
    }
}
