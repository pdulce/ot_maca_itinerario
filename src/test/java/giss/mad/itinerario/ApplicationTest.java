package giss.mad.itinerario;

import com.fasterxml.jackson.databind.ObjectMapper;
import giss.mad.itinerario.mapper.Samples;
import giss.mad.itinerario.model.ItinerarioCalidad;
import giss.mad.itinerario.model.auxactiv.ReplicaElementOEntrega;
import giss.mad.itinerario.model.auxitinerario.ItinerarioPantalla;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ApplicationTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ApplicationTest.class);
    private static final String SPRING_BANNER = "spring.main.banner_mode";
    private static final String SERVER_PORT = "server.port";



    @Autowired
    public ObjectMapper objectMapper;

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
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 1 y eje 1");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/pesosByDeliveryOfElement/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("{\"activityId\":1,\"axisAttributeId\":1");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 1 y eje 1");

    }

    @Test
    void testUmbrales() {
        int idTypeOfCatalogo = 1;
        String responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByElementCat/" + idTypeOfCatalogo, String.class);

        boolean appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Diseño - Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByDeliveryOfElement/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Diseño - Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByDeliveryOfElementBubles/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/threshold/getByElementBubles/" + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");
    }

    @Test
    void testMaxPesos() {
        int idActivity = 10;
        String responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/maxPesosOfActElemPromo/" + idActivity, String.class);
        boolean appearsActividad1Eje1 = responseTxt.contains("90");
        Assertions.assertEquals(appearsActividad1Eje1, true, "Suma de pesos de actividad <> 90");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/maxPesosOfActEntregaElemPromo/" + idActivity, String.class);
        appearsActividad1Eje1 = responseTxt.contains("30");
        Assertions.assertEquals(appearsActividad1Eje1, true, "Suma de pesos de actividad <> 30");

    }

    @Test
    void testItinerarios() throws IOException, JSONException {

        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        ReplicaElementOEntrega replica = objectMapper.readValue(Samples.JSON_FOR_TESTCALC_ITINERARY,
                ReplicaElementOEntrega.class);
        String baseUrlCalculateRest = "http://localhost:" + System.getProperty(SERVER_PORT) + "/itinerario/";
        HttpEntity<String> request = new HttpEntity<>(Samples.JSON_FOR_TESTCALC_ITINERARY, headersPost);
        String itiResultAsJsonStr = restTemplate.postForObject(baseUrlCalculateRest +  "calculate",
                request, String.class);
        Assertions.assertNotNull(itiResultAsJsonStr);
        ItinerarioCalidad itinerarioCalidad = objectMapper.readValue(itiResultAsJsonStr, ItinerarioCalidad.class);
        Assertions.assertEquals(itinerarioCalidad.getActividadesDeItinerario().isEmpty(), false);
        Assertions.assertEquals(itinerarioCalidad.getDelivery() , replica.getDelivery());
        Assertions.assertEquals(itinerarioCalidad.getCatalogueId() , replica.getId());

        request = new HttpEntity<>(Samples.JSON_FOR_TESTCALC_ITINERARY, headersPost);
        itiResultAsJsonStr = restTemplate.postForObject( baseUrlCalculateRest + "calculateItinerary",
                request, String.class);
        Assertions.assertNotNull(itiResultAsJsonStr);
        ItinerarioPantalla itinerarioPantalla = objectMapper.readValue(itiResultAsJsonStr, ItinerarioPantalla.class);
        Assertions.assertEquals(itinerarioPantalla.getElementId(), replica.getId());
        Assertions.assertEquals(itinerarioPantalla.getDelivery() , replica.getDelivery());
        Assertions.assertEquals(itinerarioPantalla.getStages().isEmpty(), false);

        //logger.info(">>>>>>>>>>>>>>>>>>>>> Itinerarios creados/calculados! >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        String responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getAll", String.class);
        boolean appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerarios no responde");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getById/" + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        //consultar todos los itinerarios creados de ese :idElement
        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getAllByIdElement/" + itinerarioPantalla.getElementId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerarios no encontrados");

        // consultar last itineario creado de ese :idElement
        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getMoreRecentByIdElement/" + itinerarioPantalla.getElementId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        // consultar sus actividades
        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getOnlyIncludedById/" + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getActivitiesById/" + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        // consultar las actividades incluidas en el itinerario
        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getActivitiesIncludedById/" + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        // consultar las actividades excluidas en itinerario
        responseTxt = restTemplate.getForObject("http://localhost:" + System.getProperty(SERVER_PORT)
                + "/itinerario/getActivitiesExcludedById/" + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

    }


    }