package giss.mad.marcocalidad.itinerariocalidad.model.auxpesos;

import giss.mad.marcocalidad.itinerariocalidad.model.auxpesos.elementosCat.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class PesoEjeIniciador {

    public static final int ELEMENTO_PROMOCIONABLE = 1, AGRUPACION_FUNCIONAL = 2, PROYECTO = 3;
    private static final int[] ELEMENT_OF_CATALOGUE = {0,1,2,3};

    private RestTemplate restTemplate;
    public PesoEjeIniciador(RestTemplate restTemplate_){
        restTemplate = restTemplate_;
    }
    public final Map<Map<Integer,Boolean>, Map<Integer, List<Weight>>> getElementsOfCatalogo(){

        Map<Integer, List<Weight>> mapaDeActividadesConPesos_ElementoPromocionable = new PesosElementoPromocionable().getPesosPorActividades(restTemplate);
        Map<Integer, List<Weight>> mapaDeActividadesConPesos_EntregaElementoPromocionable = new PesosEntregaElementoPromocionable().getPesosPorActividades(restTemplate);

        Map<Integer, List<Weight>> mapaDeActividadesConPesos_AgrupacionFuncional = new PesosAgrupacionFuncional().getPesosPorActividades(restTemplate);
        Map<Integer, List<Weight>> mapaDeActividadesConPesos_EntregaAgrupacionFuncional = new PesosEntregaAgrupacionFuncional().getPesosPorActividades(restTemplate);

        Map<Integer, List<Weight>> mapaDeActividadesConPesos_Proyecto = new PesosProyecto().getPesosPorActividades(restTemplate);
        Map<Integer, List<Weight>> mapaDeActividadesConPesos_EntregaProyecto = new PesosEntregaProyecto().getPesosPorActividades(restTemplate);

        Map<Map<Integer,Boolean>, Map<Integer, List<Weight>>> elementsOfCatalogo = new HashMap<>();

        elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], /*is_delivery*/false), mapaDeActividadesConPesos_ElementoPromocionable);
        elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/false), mapaDeActividadesConPesos_AgrupacionFuncional);
        elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/false), mapaDeActividadesConPesos_Proyecto);

        elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], /*is_delivery*/true), mapaDeActividadesConPesos_EntregaElementoPromocionable);
        elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/true), mapaDeActividadesConPesos_EntregaAgrupacionFuncional);
        elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/true), mapaDeActividadesConPesos_EntregaProyecto);
        return elementsOfCatalogo;
    }


}


