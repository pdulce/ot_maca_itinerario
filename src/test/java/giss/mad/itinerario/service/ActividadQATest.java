package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
import giss.mad.itinerario.repository.ActividadQARepository;
import giss.mad.itinerario.repository.EjeHeredableRepository;
import giss.mad.itinerario.repository.EtapaPruebasRepository;
import giss.mad.itinerario.repository.PesoRepository;
import giss.mad.itinerario.repository.UmbralActividadRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ActividadQATest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ActividadQATest.class);
    @Autowired
    private ActividadQARepository actividadQARepository;
    @Autowired
    private ActividadQAService actividadQAService;

    @Autowired
    private UmbralActividadRepository umbralActividadRepository;
    @Autowired
    private EtapaPruebasRepository etapaPruebasRepository;
    @Autowired
    private PesoRepository pesoRepository;
    @Autowired
    private UmbralActividadService umbralActividadService;
    @Autowired
    private EtapaPruebasService etapaPruebasService;

    @Autowired
    private EjeHeredableRepository ejeHeredableRepository;

    @Autowired
    private PesoService pesoService;

    @BeforeAll
    public static void setContextVariables() {
    }

    @AfterAll
    public static void cleanContextVariables() {
    }

    @Test
    @Transactional
    public void actividadesOperaciones() {

       Timestamp timeStampNow = new Timestamp(Calendar.getInstance().getTime().getTime());
       Collection<ActividadQA> listaActividades = this.actividadQAService.getAll();
       Assertions.assertEquals(listaActividades.size(), 31,
               "Hay " + listaActividades.size() + " <> 31 activs.");

       ActividadQA newActividad = new ActividadQA();
       UmbralActividad newUmbral = new UmbralActividad();
        Peso newPeso = new Peso();
       try{
           EtapaPruebas etapaFirst = this.etapaPruebasService.getAll().iterator().next();

           newActividad.setName("actividad ficticia de test");
           newActividad.setHelp("Help de ctividad ficticia de test");
           newActividad.setDescription("description of actividad ficticia de test");
           newActividad.setCreationDate(timeStampNow);
           newActividad.setIdealThreshold("Al menos 70%");
           newActividad.setTestingStageId(etapaFirst.getId());
           newActividad.setPesos(new ArrayList<>());
           newActividad.setUmbrales(new ArrayList<>());
           newActividad = this.actividadQAService.save(newActividad);
           Assertions.assertEquals(newActividad.getName(), "actividad ficticia de test",
                   "Act. recuperada no es la expected");
           Assertions.assertEquals(newActividad.getId() > 31, true, "SEQ-next no ha funcionado");

           // Recuperamos pesos para ver si están en BBDD
           Assertions.assertEquals(2908, this.pesoService.getAll().size(), "No existen las 31 act.");
           // creamos un peso, y lo añadimos a una lista de pesos


           // Recuperamos umbrales para ver si están en BBDD
           Assertions.assertEquals(330, this.umbralActividadService.getAll().size(),
                   "No existen los 330 umbrales de act.");


       }catch (Exception exc){
            Assertions.assertEquals(0, 1, "Error en alguna operación con actividadQA");
       }finally{
           // borrar actividadQA, y sus dependencias
            if (newActividad.getId() != null){
                //this.umbralActividadService.removePhysical(newUmbral.getId());
                //this.pesoService.removePhysical(newPeso.getId());
                this.actividadQAService.removePhysical(newActividad.getId());
            }
       }
    }
}