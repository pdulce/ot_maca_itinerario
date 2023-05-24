package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.volatilentities.PesoGraph;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.PesoRepository;
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
import java.util.Calendar;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},
        properties = {"spring.datasource.url:jdbc:h2:mem:testdb;INIT=create schema if not exists MACA_ITINERARIO"})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PesoServiceTest {
    @Autowired
    private PesoService pesoService;
    @Autowired
    private PesoRepository pesoRepository;
    @Autowired
    private ActividadQARepository actividadRepository;

    /*Objetos necesarios para las pruebas*/
    private static Peso peso;
    private static ActividadQA actividad;

    @BeforeAll
    public void setUp() {
        actividad = new ActividadQA();
        actividad.setName("Actividad dummy");
        actividad.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        peso = new Peso();
        peso.setWeightValue(1);
        peso.setElementTypeId(3);
        peso.setForDelivery(0);
        peso.setActivityId(1);
        peso.setAxisAttributeId(1);
        peso.setDomainValueId(1);
        peso.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        this.actividadRepository.save(actividad);
        this.pesoRepository.save(peso);
    }

    @Test
    public void testPeso_getAllByElement() {
        Collection<PesoGraph> weightsByElement = this.pesoService.getAllByElement(3, 0);
        assertFalse(weightsByElement.isEmpty());
    }

    @Test
    public void testPeso_get() {
        Peso returnedWeight = this.pesoService.get(1);
        Assertions.assertEquals(Integer.valueOf(1), returnedWeight.getId());
    }

}
