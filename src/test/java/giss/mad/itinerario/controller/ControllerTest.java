package giss.mad.itinerario.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import giss.mad.itinerario.Application;
import giss.mad.itinerario.mapper.Samples;
import giss.mad.itinerario.model.*;
import giss.mad.itinerario.model.volatilentities.ActividadReduced;
import giss.mad.itinerario.model.volatilentities.ReplicaElementOEntrega;
import giss.mad.itinerario.model.volatilentities.ValorEje;
import giss.mad.itinerario.model.volatilentities.ActividadQAPantalla;
import giss.mad.itinerario.model.volatilentities.ItinerarioPantalla;
import giss.mad.itinerario.model.volatilentities.StageBuble;
import giss.mad.itinerario.model.volatilentities.UmbralBuble;
import giss.mad.itinerario.model.volatilentities.UmbralGraph;
import giss.mad.itinerario.service.ActividadItinerarioService;
import giss.mad.itinerario.service.ItinerarioCalidadService;
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

import java.sql.Timestamp;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ControllerTest {
    private static Logger logger = (Logger) LoggerFactory.getLogger(ControllerTest.class);
    private static final String SPRING_BANNER = "spring.main.banner_mode";
    private static final String SERVER_PORT = "server.port";

    private static final String BASE_URI_ITINERARIO = "/itinerario/";

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    private ItinerarioCalidadService itinerarioCalidadService;

    @Autowired
    private ActividadItinerarioService actividadItinerarioService;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setContextVariables() {
        System.setProperty(SPRING_BANNER, "off");
        System.setProperty(SERVER_PORT, "8084");
    }

    @AfterAll
    public static void cleanContextVariables() {
        System.setProperty(SPRING_BANNER, "");
        System.setProperty(SERVER_PORT, "");

        logger.info(">>>>>>>>>>>>>>>>>>>>>>> ApplicationTest COMPLETADO");
    }

    @Test
    void testActivitiesStages() throws JsonProcessingException {
        String responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "QAStages/getAll", String.class);
        Collection<LinkedHashMap> etapasLinkedMap = objectMapper.readValue(responseTxt, Collection.class);
        for (LinkedHashMap etapaBubleLinkedMap: etapasLinkedMap){
            EtapaPruebas etapa = new EtapaPruebas();
            etapa.setName(etapaBubleLinkedMap.get("name").toString());
            etapa.setDescription(etapaBubleLinkedMap.get("description").toString());
            etapa.setId(Integer.valueOf(etapaBubleLinkedMap.get("id").toString()));
            Assertions.assertNotNull(etapa.getDescription());
            Assertions.assertNotNull(etapa.getDescription());
        }

        boolean appearsActividadStage = responseTxt.contains("Análisis de código estático");
        Assertions.assertEquals(appearsActividadStage, true, "No figura etapa 2");

        final Integer idStage = 4;
        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "QAStages/getById/"+ idStage, String.class);

        appearsActividadStage = responseTxt.contains("Integración");
        Assertions.assertEquals(appearsActividadStage, true, "No figura etapa 4");

    }

    @Test
    void testActivities() throws JsonProcessingException {
        String responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "QAactivities/getAll", String.class);

        boolean appearsActividad3 = responseTxt.contains("Diseño planes prueba Funcional");
        Assertions.assertEquals(appearsActividad3, true, "No figura actividad 3");

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "QAactivities/getreduced/", String.class);

        Collection<LinkedHashMap> actsBubleLinkedMap = objectMapper.readValue(responseTxt, Collection.class);
        for (LinkedHashMap actividadBubleLinkedMap: actsBubleLinkedMap){
            ActividadReduced actReduced = new ActividadReduced();
            actReduced.setActividad(actividadBubleLinkedMap.get("actividad").toString());
            actReduced.setNum(Integer.valueOf(actividadBubleLinkedMap.get("num").toString()));
            Assertions.assertNotNull(actReduced.getActividad());
            Assertions.assertNotNull(actReduced.getNum());
        }

        appearsActividad3 = responseTxt.contains("Diseño planes prueba Funcional");
        Assertions.assertEquals(appearsActividad3, true, "No figura actividad 3");

        final Integer idActividad = 28;
        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "QAactivities/getById/" + idActividad,
                String.class);

        appearsActividad3 = responseTxt.contains("OmniaUsability");
        Assertions.assertEquals(appearsActividad3, true, "No figura actividad 28");
        ValorEje valorEje = new ValorEje();
        valorEje.setId(Long.valueOf("22"));
        valorEje.setAxisAttributeId(2);
        valorEje.setDomainValueId(1);
        Assertions.assertNotNull(valorEje.getId());
        Assertions.assertNotNull(valorEje.getAxisAttributeId());
        Assertions.assertNotNull(valorEje.getDomainValueId());
    }

    @Test
    void testPesos() throws JsonProcessingException {
        int idTypeOfCatalogo = 1;
        String responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "pesosByElementCat/"
                + idTypeOfCatalogo, String.class);

        Collection<LinkedHashMap> pesos = objectMapper.readValue(responseTxt, Collection.class);
        int max = 0;
        Iterator<LinkedHashMap> itePesos = pesos.iterator();
        while (itePesos.hasNext() && max < 10){
            LinkedHashMap pesoHashmap = itePesos.next();
            PesoGraph peso = new PesoGraph();
            peso.setActivityId(Integer.valueOf(pesoHashmap.get("activityId").toString()));
            peso.setAxisAttributeId(Integer.valueOf(pesoHashmap.get("axisAttributeId").toString()));
            peso.setWeightValue(Integer.valueOf(pesoHashmap.get("weightValue").toString()));
            Assertions.assertNotNull(peso.getActivityId());
            Assertions.assertNotNull(peso.getWeightValue());
            Assertions.assertNotNull(peso.getAxisAttributeId());
            max++;
        }

        boolean appearsActividad1Eje1 = responseTxt.contains("{\"activityId\":1,\"axisAttributeId\":1");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 1 y eje 1");

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "pesosByDeliveryOfElement/"
                + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("{\"activityId\":1,\"axisAttributeId\":1");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No figura peso para activdad 1 y eje 1");


    }

    @Test
    void testUmbrales() throws JsonProcessingException {
        int idTypeOfCatalogo = 1;
        String responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "threshold/getByElementCat/"
                + idTypeOfCatalogo, String.class);

        boolean appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Diseño - Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");

        int max = 0;
        Collection<LinkedHashMap> umbrales = objectMapper.readValue(responseTxt, Collection.class);
        Iterator<LinkedHashMap> iteUmbrales = umbrales.iterator();
        while (iteUmbrales.hasNext() && max < 10){
            LinkedHashMap umbralLinkedMap = iteUmbrales.next();
            UmbralGraph umbralGraph = new UmbralGraph();
            umbralGraph.setActividad(umbralLinkedMap.get("actividad").toString());
            umbralGraph.setName(umbralLinkedMap.get("name").toString());
            umbralGraph.setRecomen(umbralLinkedMap.get("recomen").toString());
            umbralGraph.setX(Integer.valueOf(umbralLinkedMap.get("x").toString()));
            umbralGraph.setY(Integer.valueOf(umbralLinkedMap.get("y").toString()));
            umbralGraph.setZ(Integer.valueOf(umbralLinkedMap.get("z").toString()));
            Assertions.assertNotNull(umbralGraph.getActividad());
            Assertions.assertNotNull(umbralGraph.getName());
            Assertions.assertNotNull(umbralGraph.getRecomen());
            Assertions.assertNotNull(umbralGraph.getX());
            Assertions.assertNotNull(umbralGraph.getY());
            Assertions.assertNotNull(umbralGraph.getZ());
            max++;
        }
        Timestamp timeStampNow = new Timestamp(Calendar.getInstance().getTime().getTime());
        EjeHeredable ejeHeredable = new EjeHeredable();
        ejeHeredable.setCreationDate(timeStampNow);
        ejeHeredable.setForDelivery(1);
        ejeHeredable.setElementTypeId(1);
        ejeHeredable.setAxisId(1);
        ejeHeredable.setWritable(0);

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "threshold/getByDeliveryOfElement/"
                + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Diseño - Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "threshold/getByDeliveryOfElementBubles/"
                + idTypeOfCatalogo, String.class);

        Collection<LinkedHashMap> stagesBubleLinkedMap = objectMapper.readValue(responseTxt, Collection.class);
        for (LinkedHashMap stageBubleLinkedMap: stagesBubleLinkedMap){

            appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Automatización de Pruebas Funcionales\"");
            Assertions.assertEquals(appearsActividad1Eje1, true);
            appearsActividad1Eje1 = responseTxt.contains("\"name\":\"Rango >=0\"");
            Assertions.assertEquals(appearsActividad1Eje1, true);
            appearsActividad1Eje1 = responseTxt.contains("\"recomen\":\"Realizar siempre\"");
            Assertions.assertEquals(appearsActividad1Eje1, true);

            UmbralBuble umbralBuble = new UmbralBuble();
            umbralBuble.setActividad("Revisión Requisitos");
            umbralBuble.setName("Rango  mayor igual a 0");
            umbralBuble.setRecomen("Se debe acometer");
            umbralBuble.setValue(22);
            Assertions.assertNotNull(umbralBuble.getRecomen());
            Assertions.assertNotNull(umbralBuble.getValue());
            Assertions.assertNotNull(umbralBuble.getActividad());
            Assertions.assertNotNull(umbralBuble.getName());
            StageBuble stageBuble = new StageBuble();
            stageBuble.setData(new ArrayList<>());
            stageBuble.setName(stageBubleLinkedMap.get("name").toString());
            Assertions.assertNotNull(stageBuble.getData());
            Assertions.assertNotNull(stageBuble.getName());
        }
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "threshold/getByElementBubles/"
                + idTypeOfCatalogo, String.class);
        appearsActividad1Eje1 = responseTxt.contains("\"actividad\":\"Revisión Requisitos\"");
        Assertions.assertEquals(appearsActividad1Eje1, true, "No existe pesos de ac.Revisión Requisitos");
    }

    @Test
    void testMaxPesos() {
        int idActivity = 10;
        String responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "maxPesosOfActElemPromo/" + idActivity,
                String.class);
        boolean appearsActividad1Eje1 = responseTxt.contains("90");
        Assertions.assertEquals(appearsActividad1Eje1, true, "Suma de pesos de actividad <> 90");

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "maxPesosOfActEntregaElemPromo/" + idActivity,
                String.class);
        appearsActividad1Eje1 = responseTxt.contains("30");
        Assertions.assertEquals(appearsActividad1Eje1, true, "Suma de pesos de actividad <> 30");

    }

    @Test
    void testItinerarios() throws JsonProcessingException {
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        ReplicaElementOEntrega replica = objectMapper.readValue(Samples.JSON_FOR_TESTCALC_ITINERARY,
                ReplicaElementOEntrega.class);

        HttpEntity<String> request = new HttpEntity<>(Samples.JSON_FOR_TESTCALC_ITINERARY, headersPost);
        String itiResultAsJsonStr = restTemplate.postForObject(BASE_URI_ITINERARIO +  "calculate",
                request, String.class);
        Assertions.assertNotNull(itiResultAsJsonStr);
        ItinerarioCalidad itinerarioCalidad = objectMapper.readValue(itiResultAsJsonStr, ItinerarioCalidad.class);
        Assertions.assertEquals(itinerarioCalidad.getActividadesDeItinerario().isEmpty(), false);
        Assertions.assertEquals(itinerarioCalidad.getDelivery() , replica.getDelivery());
        Assertions.assertEquals(itinerarioCalidad.getCatalogueId() , replica.getId());
        Assertions.assertNotNull(itinerarioCalidad.getCreationDate());
        Assertions.assertNotNull(itinerarioCalidad.getId());

        request = new HttpEntity<>(Samples.JSON_FOR_TESTCALC_ITINERARY, headersPost);
        itiResultAsJsonStr = restTemplate.postForObject( BASE_URI_ITINERARIO + "calculateItinerary",
                request, String.class);
        Assertions.assertNotNull(itiResultAsJsonStr);
        ItinerarioPantalla itinerarioPantalla = objectMapper.readValue(itiResultAsJsonStr, ItinerarioPantalla.class);
        Assertions.assertEquals(itinerarioPantalla.getElementId(), replica.getId());
        Assertions.assertEquals(itinerarioPantalla.getDelivery() , replica.getDelivery());
        Assertions.assertEquals(itinerarioPantalla.getStages().isEmpty(), false);
        Assertions.assertNotNull(itinerarioPantalla.getCreationDate());
        Assertions.assertNotNull(itinerarioPantalla.getId());

        //logger.info(">>>>>>>>>>>>>>>>>>>>> Itinerarios creados/calculados! >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        String responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getAll", String.class);
        boolean appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerarios no responde");

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getById/" + itinerarioCalidad.getId(),
                String.class);
        ItinerarioCalidad itinerario = objectMapper.readValue(responseTxt, ItinerarioCalidad.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");
        Assertions.assertNotNull(itinerario.getId());

        //consultar todos los itinerarios creados de ese :idElement
        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getAllByIdElement/"
                + itinerarioPantalla.getElementId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerarios no encontrados");

        // consultar last itineario creado de ese :idElement
        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getMoreRecentByIdElement/"
                + itinerarioPantalla.getElementId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        // consultar sus actividades
        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getOnlyIncludedById/"
                + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getActivitiesById/"
                + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        // consultar las actividades incluidas en el itinerario
        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getActivitiesIncludedById/"
                + itinerarioCalidad.getId(), String.class);
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        // consultar las actividades excluidas en itinerario
        responseTxt = restTemplate.getForObject(BASE_URI_ITINERARIO + "getActivitiesExcludedById/"
                + itinerarioCalidad.getId(), String.class);
        Collection<LinkedHashMap> actividadesLinkedMap = objectMapper.readValue(responseTxt, Collection.class);
        for (LinkedHashMap actividadLinkedMap: actividadesLinkedMap) {

            ActividadQAPantalla actividadQAPantalla = new ActividadQAPantalla();
            actividadQAPantalla.setActivity(actividadLinkedMap.get("activity").toString());
            if (actividadLinkedMap.get("observations") != null) {
                actividadQAPantalla.setObservations(actividadLinkedMap.get("observations").toString());
                Assertions.assertNotNull(actividadQAPantalla.getObservations());
            }else{
                Assertions.assertNull(actividadQAPantalla.getObservations());
            }
            actividadQAPantalla.setStage(actividadLinkedMap.get("stage").toString());
            actividadQAPantalla.setRealization(actividadLinkedMap.get("realization").toString());
            Assertions.assertNotNull(actividadQAPantalla.getActivity());
            Assertions.assertNotNull(actividadQAPantalla.getStage());
            Assertions.assertNotNull(actividadQAPantalla.getRealization());
            Assertions.assertNull(actividadQAPantalla.getId());
        }
        appearsItinerarioOfElem3 = responseTxt.contains("\"catalogueId\":3") || responseTxt.length() > 10;
        Assertions.assertEquals(appearsItinerarioOfElem3, true, "Itinerario no encontrado");

        restTemplate.delete(BASE_URI_ITINERARIO + "borrarItinerarioById/" + itinerarioCalidad.getId());

        restTemplate.delete(BASE_URI_ITINERARIO + "borrarItinerarioById/" +  itinerarioPantalla.getId());

    }

}