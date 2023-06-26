package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.*;
import giss.mad.itinerario.model.volatilentities.DomainValue;
import giss.mad.itinerario.model.volatilentities.ItinerarioPantalla;
import giss.mad.itinerario.model.volatilentities.ReplicaElementOEntrega;
import giss.mad.itinerario.model.volatilentities.ValorEje;
import giss.mad.itinerario.repository.*;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},
        properties = {"spring.datasource.url:jdbc:h2:mem:testdb-1;INIT=create schema if not exists MACA_ITINERARIO"})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActividadItinerarioServiceTest {
    @Autowired
    private ActividadItinerarioRepository actividadItinerarioRepository;
    @Autowired
    private ActividadQARepository actividadQARepository;
    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private PesoRepository pesoRepository;
    @Autowired
    private EjeHeredableRepository ejeHeredableRepository;
    @Autowired
    private UmbralActividadRepository umbralActividadRepository;
    @Autowired
    private ItinerarioCalidadRepository itinerarioCalidadRepository;
    @Autowired
    private ActividadItinerarioService actividadItinerarioService;

    /*Objetos necesarios para las pruebas*/
    private static EtapaPruebas testStage;
    private static ActividadQA actividadQA;
    private static ActividadItinerario actividadItinerario;
    private static Peso peso;
    private static UmbralActividad umbral;
    private static ItinerarioCalidad itinerarioCalidad;
    private static ReplicaElementOEntrega replicaElementOEntrega;

    @BeforeAll
    public void setUp() {
        actividadItinerario = new ActividadItinerario();
        actividadItinerario.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        actividadItinerario.setQualityItineraryId(1);
        actividadItinerario.setActivityId(1);
        ArrayList<ActividadItinerario> actividadesItinerario = new ArrayList<>();
        actividadesItinerario.add(actividadItinerario);
        itinerarioCalidad = new ItinerarioCalidad();
        itinerarioCalidad.setCatalogueId(1);
        itinerarioCalidad.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        itinerarioCalidad.setActividadesDeItinerario(actividadesItinerario);

        ValorEje valorEje = new ValorEje();
        valorEje.setAxisAttributeId(1);
        DomainValue domainValue = new DomainValue();
        domainValue.setDomainValueId(1);
        List<DomainValue> domainValues = new ArrayList<>();
        domainValues.add(domainValue);
        valorEje.setDomainValues(domainValues);
        List<ValorEje> valoresEje = new ArrayList<>();
        valoresEje.add(valorEje);

        replicaElementOEntrega = new ReplicaElementOEntrega();
        replicaElementOEntrega.setId(1);
        replicaElementOEntrega.setCatalogElementTypeId(1);
        replicaElementOEntrega.setDelivery(1);
        replicaElementOEntrega.setAttributeValuesCollection(valoresEje);

        testStage = new EtapaPruebas();
        testStage.setName("Dummy stage");
        testStage.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

        actividadQA = new ActividadQA();
        actividadQA.setName("Actividad dummy");
        actividadQA.setTestingStageId(1);
        actividadQA.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        ArrayList<ActividadQA> actividadesQA = new ArrayList<ActividadQA>();
        actividadesQA.add(actividadQA);
        testStage.setActividadesQA(actividadesQA);

        peso = new Peso();
        peso.setWeightValue(1);
        peso.setElementTypeId(3);
        peso.setForDelivery(0);
        peso.setActivityId(1);
        peso.setAxisAttributeId(1);
        peso.setDomainValueId(1);
        peso.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

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
        this.pesoRepository.save(peso);
        this.umbralActividadRepository.save(umbral);
        //this.actividadItinerarioRepository.save(actividadItinerario);
        this.itinerarioCalidadRepository.save(itinerarioCalidad);
    }

    @Test
    public void testActividadItinerario_remove() {
        ActividadItinerario actividad = new ActividadItinerario();
        actividad.setCreationDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        actividad.setActivityId(1);
        this.actividadItinerarioRepository.save(actividad);

        ActividadItinerario removedActivity = this.actividadItinerarioService.remove(actividad.getActivityId());
        assertNotNull(removedActivity);
    }

    @Test
    public void testActividadItinerario_getItineraryActivitiesByParent() {
        Collection<ActividadItinerario> actividades = this.actividadItinerarioService.getItineraryActivitiesByParent(actividadItinerario.getId());
        assertFalse(actividades.isEmpty());
    }

    @Test
    public void testActividadItinerario_calcularActividadItinerarioWithDetailedInfo_1() {
        ItinerarioCalidad calculatedItinerary = this.actividadItinerarioService.calcularActividadItinerarioWithDetailedInfo(replicaElementOEntrega);
        assertNotNull(calculatedItinerary);
    }

    @Test
    public void testActividadItinerario_calcularActividadItinerarioWithDetailedInfo_2() {
        ReplicaElementOEntrega replica = new ReplicaElementOEntrega();
        replica.setId(1);
        replica.setCatalogElementTypeId(1);
        replica.setDelivery(1);

        ArrayList<ValorEje> axisValues = new ArrayList<>();
        ValorEje axisValue = new ValorEje();
        axisValue.setAxisAttributeId(1);
        ArrayList<DomainValue> domainValues = new ArrayList<>();
        DomainValue domainValue = new DomainValue();
        domainValue.setDomainValueId(1);
        axisValue.setDomainValues(domainValues);
        replica.setAttributeValuesCollection(axisValues);

        //TBC



    }

    @Test
    public void testActividadItinerario_calculateItinerary_1() {
        ItinerarioPantalla itinerarioPantalla = this.actividadItinerarioService.calculateItinerary(replicaElementOEntrega);
        assertNotNull(itinerarioPantalla);
    }

}
