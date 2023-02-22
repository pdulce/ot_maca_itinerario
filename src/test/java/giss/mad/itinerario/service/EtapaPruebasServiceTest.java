package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},
        properties = {"spring.jpa.properties.hibernate.default_schema:MACA_ITINERARIO",
        "spring.datasource.url:jdbc:h2:mem:testdb;INIT=create schema if not exists MACA_ITINERARIO"})
@ActiveProfiles("test")
public class EtapaPruebasServiceTest {
    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private EtapaPruebasService etapaPruebasService;
    private static EtapaPruebas etapaPruebas;
    private static ActividadQA actividadQA;
    private static List<EtapaPruebas> etapasPruebas;
    private static List<ActividadQA> actividadesQA;


    @BeforeAll
    public static void setUp() {

        actividadQA = new ActividadQA();
        actividadQA.setName("Actividad ficticia");
        actividadQA.setDescription("Descr. Actividad ficticia");
        actividadQA.setId(988);
        actividadQA.setHelp("help");
        //actividadQA.set
        //actividadQA.set

        etapaPruebas = new EtapaPruebas();
        etapaPruebas.setId(1099);
        etapaPruebas.setName("etapa Pruebas A");
        etapaPruebas.setDescription("Descripción etapa pruebas");
        etapaPruebas.setDeleted(null);
        etapaPruebas.setActividadesQA(actividadesQA);
        etapasPruebas = new ArrayList<>();
        etapasPruebas.add(etapaPruebas);
    }

    @Test
    public void testGetAllStages() {

        Collection<EtapaPruebas> allEtapas = etapaPruebasService.getAll();
        assertEquals(false, allEtapas.size() > 0);


    }



}