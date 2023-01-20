package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.ActividadQA;
import giss.mad.itinerario.model.EtapaPruebas;
import giss.mad.itinerario.model.Peso;
import giss.mad.itinerario.model.UmbralActividad;
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
    private ActividadQAService actividadQAService;

    @Autowired
    private UmbralActividadService umbralActividadService;
    @Autowired
    private EtapaPruebasService etapaPruebasService;

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
           Assertions.assertEquals(newActividad.getId() > 31, true,
                   "SEQ-next actividadQA no ha funcionado");

           // Recuperamos pesos para ver si están en BBDD
           Assertions.assertEquals(2908, this.pesoService.getAll().size(), "No existen las 2908 pesos");
           // creamos un peso, y lo añadimos a una lista de pesos
           newPeso.setCreationDate(timeStampNow);
           newPeso.setActivityId(newActividad.getId());
           newPeso.setForDelivery(0);
           newPeso.setAxisAttributeId(1);
           newPeso.setDomainValueId(55);
           newPeso.setElementTypeId(1);
           newPeso.setWeightValue(120);
           newPeso = this.pesoService.save(newPeso);
           Assertions.assertEquals(newPeso.getId() > 2908, true,
                   "SEQ-next Pesos no ha funcionado");

           // Recuperamos umbrales para ver si están en BBDD
           Assertions.assertEquals(330, this.umbralActividadService.getAll().size(),
                   "No existen los 330 umbrales de act.");
           newUmbral.setCreationDate(timeStampNow);
           newUmbral.setHelp("help new umbral");
           newUmbral.setActivityId(newActividad.getId());
           newUmbral.setElemenTypeId(1);
           newUmbral.setExcludeUnreachedThreshold(0);
           newUmbral.setThreshold("Umbral test");
           newUmbral.setForDelivery(0);
           newUmbral.setLowerLimit(30);
           newUmbral.setUpperLimit(80);
           newUmbral = this.umbralActividadService.save(newUmbral);
           Assertions.assertEquals(newUmbral.getId() > 330, true,
                   "SEQ-next Umbral no ha funcionado");

           EtapaPruebas second= new EtapaPruebas();
           second.setName("borrar");
           second.setDescription("description");
           second.setCreationDate(timeStampNow);
           second = this.etapaPruebasService.alta(second);
           Assertions.assertEquals(second.getId() > 0, true);
           second = this.etapaPruebasService.update(second);
           Assertions.assertEquals(second.getUpdateDate() != null, true);
           second = this.etapaPruebasService.borrar(second.getId());
           Assertions.assertEquals(second.getUpdateDate() != null, true);

       }catch (Exception exc) {
            Assertions.assertEquals(0, 1, "Error en alguna operación con actividadQA");
       }finally{
           // borrar actividadQA, y sus dependencias
           if (newPeso.getId() != null) {
               this.pesoService.removePhysical(newPeso.getId());
           }
           if (newUmbral.getId() != null) {
               this.umbralActividadService.removePhysical(newUmbral.getId());
           }
           if (newActividad.getId() != null) {
               this.actividadQAService.removePhysical(newActividad.getId());
           }

       }
    }
}