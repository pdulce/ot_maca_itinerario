package giss.mad.itinerario;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ApplicationTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ApplicationTest.class);
    private static final String SPRING_BANNER = "spring.main.banner_mode";
    private static final String SERVER_PORT = "server.port";

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setContextVariables() {
        System.setProperty(SPRING_BANNER, "off");
        System.setProperty(SERVER_PORT, "8080");
    }

    @AfterAll
    public static void cleanContextVariables() {
        System.setProperty(SPRING_BANNER, "");
        System.setProperty(SERVER_PORT, "");

        logger.info(">>>>>>>>>>>>>>>>>>>>>>> ApplicationTest COMPLETADO");
    }


    @Test
    void testActivitiesStages() {
        String responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario//QAStages/getAll", String.class);
        //logger.info("RECIBIDO: " + msg);

        boolean appearsActividadStage = responseTxt.contains("Análisis de código estático");
        Assertions.assertEquals(appearsActividadStage, true, "No figura etapa 2");

        final Integer idStage = 4;
        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/QAStages/getById/"+ idStage, String.class);

        appearsActividadStage = responseTxt.contains("Integración");
        Assertions.assertEquals(appearsActividadStage, true, "No figura etapa 4");

    }

    @Test
    void testActivities() {
        String responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/QAactivities/getAll", String.class);

        boolean appearsActividad3 = responseTxt.contains("Diseño planes prueba Funcional");
        Assertions.assertEquals(appearsActividad3, true, "No figura actividad 3");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/QAactivities/getreduced/", String.class);

        appearsActividad3 = responseTxt.contains("Diseño planes prueba Funcional");
        Assertions.assertEquals(appearsActividad3, true, "No figura actividad 3");

        final Integer idActividad = 28;
        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/QAactivities/getById/" + idActividad, String.class);

        appearsActividad3 = responseTxt.contains("OmniaUsability");
        Assertions.assertEquals(appearsActividad3, true, "No figura actividad 28");

    }

    @Test
    void testPesos() {
        int idTypeOfCatalogo = 1;
        String responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/pesosByElementCat/" + idTypeOfCatalogo, String.class);

        boolean appearsActividad1Eje1 = responseTxt.contains("{\"activityId\":1,\"axisAttributeId\":1");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 21 y eje 1");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/pesosByDeliveryOfElement/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("{\"activityId\":1,\"axisAttributeId\":1");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 21 y eje 1");

    }

    @Test
    void testUmbrales() {
        int idTypeOfCatalogo = 1;
        String responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByElementCat/" + idTypeOfCatalogo, String.class);

        boolean appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Diseño - Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 21 y eje 1");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByDeliveryOfElement/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Diseño - Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 21 y eje 1");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByDeliveryOfElementBubles/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 21 y eje 1");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByElementBubles/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 21 y eje 1");

    }




}