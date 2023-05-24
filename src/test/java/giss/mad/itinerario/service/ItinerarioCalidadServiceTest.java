package giss.mad.itinerario.service;
import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.*;
import giss.mad.itinerario.model.volatilentities.ActividadQAPantalla;
import giss.mad.itinerario.model.volatilentities.StageBuble;
import giss.mad.itinerario.repository.*;
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
        properties = {"spring.datasource.url:jdbc:h2:mem:testdbIti;INIT=create schema if not exists MACA_ITINERARIO"})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItinerarioCalidadServiceTest {

    @Autowired
    private ItinerarioCalidadRepository itinerarioCalidadRepository;
    @Autowired
    private ActividadQARepository actividadQARepository;
    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private ActividadItinerarioRepository actividadItinerarioRepository;
    @Autowired
    private ItinerarioCalidadService itinerarioCalidadService;

    /*Objetos necesarios para las pruebas*/
    private static ItinerarioCalidad itinerario;
    private static EtapaPruebas etapaPruebas;
    private static ActividadQA actividad;
    private static ActividadItinerario actividadItinerario;

    @BeforeAll
    public void setUp() {
        actividadItinerario = new ActividadItinerario();
        actividadItinerario.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        actividadItinerario.setQualityItineraryId(1);
        actividadItinerario.setActivityId(1);
        ArrayList<ActividadItinerario> actividades = new ArrayList<ActividadItinerario>();
        actividades.add(actividadItinerario);
        itinerario = new ItinerarioCalidad();
        itinerario.setCatalogueId(1);
        itinerario.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        itinerario.setActividadesDeItinerario(actividades);

        etapaPruebas = new EtapaPruebas();
        etapaPruebas.setName("Dummy stage");
        etapaPruebas.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        actividad = new ActividadQA();
        actividad.setName("Actividad dummy");
        actividad.setTestingStageId(1);
        actividad.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        this.etapaPruebasRepository.save(etapaPruebas);
        this.actividadQARepository.save(actividad);
        this.itinerarioCalidadRepository.save(itinerario);

    }

    @Test
    @Transactional
    public void testItinerario_getActivitiesByItineraryId_1() {
        Collection<ActividadQAPantalla> actividadQAPantallas = itinerarioCalidadService.getActivitiesByItineraryId(1);
        assertFalse(actividadQAPantallas.isEmpty());
    }

    @Test
    @Transactional
    public void testItinerario_getActivitiesByItineraryId_2() {
        Collection<ActividadQAPantalla> actividadQAPantallas = itinerarioCalidadService.getActivitiesByItineraryId(1, 1);
        assertFalse(actividadQAPantallas.isEmpty());
    }
    @Test
    public void testItinerario_getAllItinerariosByIdElementOrEntrega() {
        Collection<ItinerarioCalidad> itinerarios = itinerarioCalidadService.getAllItinerariosByIdElementOrEntrega(1, null);
        assertFalse(itinerarios.isEmpty());
    }
    @Test
    public void testItinerario_getItinerarioMasRecienteByIdElementOrEntrega() {
        ItinerarioCalidad itinerario = itinerarioCalidadService.getItinerarioMasRecienteByIdElementOrEntrega(1, null);
        assertNotNull(itinerario);
    }
    @Test
    public void testItinerario_getByIdItinerario() {
        ItinerarioCalidad itinerario = itinerarioCalidadService.getByIdItinerario(1);
        assertNotNull(itinerario);
    }
    @Test
    public void testItinerario_delete() {
        ItinerarioCalidad itinerary = new ItinerarioCalidad();
        itinerary.setCatalogueId(1);
        itinerary.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        itinerary.setActividadesDeItinerario(new ArrayList<>());
        this.itinerarioCalidadRepository.save(itinerary);

        ItinerarioCalidad deletedItinerary = this.itinerarioCalidadService.delete(2);
        assertNotNull(deletedItinerary);
    }
    @Test
    @Transactional
    public void testItinerario_getByIdItinerarioOnlyIncluded() {
        Collection<StageBuble> stageBubles = this.itinerarioCalidadService.getByIdItinerarioOnlyIncluded(1);
        assertFalse(stageBubles.isEmpty());
    }
}
