package giss.mad.itinerario.service;

import giss.mad.itinerario.Application;
import giss.mad.itinerario.model.Peso;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class PesosQATest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(PesosQATest.class);

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
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void actividadesOperaciones() {

       Timestamp timeStampNow = new Timestamp(Calendar.getInstance().getTime().getTime());

       Peso newPeso = new Peso();
       try{
           Collection<Peso> listaPesos = this.pesoService.getAll();
           Assertions.assertEquals(!listaPesos.isEmpty(), true, "No se cargaron los pesos");

           newPeso.setCreationDate(timeStampNow);
           newPeso.setForDelivery(1);
           newPeso.setActivityId(this.actividadQAService.getAll().iterator().next().getId());
           newPeso.setAxisAttributeId(1);
           newPeso.setDomainValueId(50);
           newPeso.setElementTypeId(2);
           newPeso.setWeightValue(20);
           Peso newPesoSaved = this.pesoService.save(newPeso);
           Assertions.assertEquals(newPeso.getId() > 2908, true, "Peso no pudo ser grabado");
           Assertions.assertEquals(newPeso.getActivityId(), newPesoSaved.getActivityId(),
                   "Peso no pudo ser grabado, error en idOfActivity");

           newPesoSaved.setForDelivery(0);
           newPesoSaved = this.pesoService.update(newPeso);
           Assertions.assertEquals(newPeso.getId() > 2908, true, "Peso no pudo ser grabado");
           Assertions.assertEquals(newPeso.getForDelivery(), newPesoSaved.getForDelivery(),
                   "Peso no pudo ser updated");

           newPesoSaved = this.pesoService.get(newPesoSaved.getId());
           Assertions.assertNull(newPeso.getDeleted());
           Assertions.assertEquals(newPeso.getWeightValue(), newPesoSaved.getWeightValue(),
                   "Peso no pudo ser recuperado, weigth no coincide");
           Assertions.assertEquals(newPeso.getAxisAttributeId(), newPesoSaved.getAxisAttributeId(),
                   "Peso no pudo ser recuperado, axisAttributeId");

           /*Collection<Peso> pesos = this.pesoService.getAllByElement(newPesoSaved.getElementTypeId(),
                   newPesoSaved.getForDelivery());*/

       }catch (Exception exc) {
            Assertions.assertEquals(0, 1, "Error en alguna operación con actividadQA");
       }finally{
           // borrar actividadQA, y sus dependencias
           if (newPeso.getId() != null) {
               this.pesoService.removePhysical(newPeso.getId());
           }
       }
    }
}