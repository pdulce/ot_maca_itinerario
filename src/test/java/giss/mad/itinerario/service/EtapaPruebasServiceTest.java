package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class},
        properties = {"spring.datasource.url:jdbc:h2:mem:testdb-3;INIT=create schema if not exists MACA_ITINERARIO"})
@ActiveProfiles("test")
public class EtapaPruebasServiceTest {

    @Autowired
    private ActividadQARepository actividadQARepository;
    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private EtapaPruebasService etapaPruebasService;
    private static EtapaPruebas etapaPruebas;
    private static ActividadQA actividadQA;
    private static List<EtapaPruebas> etapasPruebas;
    private static List<ActividadQA> actividadesQA;

    //private static Peso actividadQA;
    //private static List<ActividadQA> actividadesQA;


    @BeforeAll
    public static void setUp() {

        actividadQA = new ActividadQA();
        actividadQA.setName("Actividad ficticia");
        actividadQA.setDescription("Descr. Actividad ficticia");
        actividadQA.setId(988);
        actividadQA.setHelp("help");
        actividadQA.setIdealThreshold("80%");
        actividadesQA = new ArrayList<>();
        //actividadesQA.add(actividadQA);
        //actividadQA.setPesos(pesos);

        etapaPruebas = new EtapaPruebas();
        etapaPruebas.setId(1099);
        etapaPruebas.setName("etapa Pruebas A");
        etapaPruebas.setDescription("Descripcion etapa pruebas");
        etapaPruebas.setDeleted(null);
        etapaPruebas.setActividadesQA(actividadesQA);
        etapasPruebas = new ArrayList<>();
        //etapasPruebas.add(etapaPruebas);
    }

    @Test
    public void testCreateStage() {

        etapaPruebas = etapaPruebasService.insertar(etapaPruebas);
        assertEquals(etapaPruebas.getId() > 0, true);

        etapaPruebasService.borradoLogico(etapaPruebas.getId());
        Collection<EtapaPruebas> allEtapas = etapaPruebasService.getAll();
        assertEquals(0, allEtapas.size());

    }

    @Test
    public void testGetAllStages() {

        etapaPruebas = etapaPruebasService.insertar(etapaPruebas);
        assertEquals(etapaPruebas.getId() > 0, true);

        Collection<EtapaPruebas> allEtapas = etapaPruebasService.getAll();
        assertEquals(1, allEtapas.size());

        etapaPruebasService.borradoLogico(etapaPruebas.getId());
        allEtapas = etapaPruebasService.getAll();
        assertEquals(0, allEtapas.size());

    }

    @Test
    public void testUpdateStage() {
        etapaPruebas = etapaPruebasService.insertar(etapaPruebas);
        assertEquals(etapaPruebas.getId() > 0, true);
        etapaPruebasService.actualizar(etapaPruebas);
        assertEquals(etapaPruebas.getUpdateDate() != null, true);
        etapaPruebasService.borradoLogico(etapaPruebas.getId());
        Collection<EtapaPruebas> allEtapas = etapaPruebasService.getAll();
        assertEquals(0, allEtapas.size());
    }

    @Test
    public void testDeleteStage() {

        etapaPruebas = etapaPruebasService.insertar(etapaPruebas);
        assertEquals(etapaPruebas.getId() > 0, true);

        etapaPruebasService.borradoLogico(etapaPruebas.getId());
        Collection<EtapaPruebas> allEtapas = etapaPruebasService.getAll();
        assertEquals(0, allEtapas.size());

    }

}
