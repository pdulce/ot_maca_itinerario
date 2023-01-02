package giss.mad.itinerario.model.auxpesos;

import giss.mad.itinerario.model.auxpesos.elementosCat.*;
import giss.mad.itinerario.service.Constantes;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class PesoEjeIniciador {

  public static final int ELEMENTO_PROMOCIONABLE = Constantes.NUMBER_1;
  public static final int AGRUPACION_FUNCIONAL = Constantes.NUMBER_2;
  public static final int PROYECTO = Constantes.NUMBER_3;
  private static final int[] ELEMENT_OF_CATALOGUE = {Constantes.NUMBER_0, Constantes.NUMBER_1,
      Constantes.NUMBER_2, Constantes.NUMBER_3};

  private RestTemplate restTemplate;

  public PesoEjeIniciador(final RestTemplate restTemplate_) {
    restTemplate = restTemplate_;
  }

  public final Map<Map<Integer, Boolean>, Map<Integer, List<Weight>>> getElementsOfCatalogo() {

    Map<Integer, List<Weight>> mapaDeActividadesConPesos_ElementoPromocionable =
        new PesosElementoPromocionable().getPesosPorActividades(
        restTemplate);
    Map<Integer, List<Weight>> mapaDeActividadesConPesos_EntregaElementoPromocionable =
        new PesosEntregaElementoPromocionable().getPesosPorActividades(
        restTemplate);

    Map<Integer, List<Weight>> mapaDeActividadesConPesos_AgrupacionFuncional =
        new PesosAgrupacionFuncional().getPesosPorActividades(
        restTemplate);
    Map<Integer, List<Weight>> mapaDeActividadesConPesos_EntregaAgrupacionFuncional =
        new PesosEntregaAgrupacionFuncional().getPesosPorActividades(
        restTemplate);

    Map<Integer, List<Weight>> mapaDeActividadesConPesos_Proyecto =
        new PesosProyecto().getPesosPorActividades(
        restTemplate);
    Map<Integer, List<Weight>> mapaDeActividadesConPesos_EntregaProyecto =
        new PesosEntregaProyecto().getPesosPorActividades(
        restTemplate);

    Map<Map<Integer, Boolean>, Map<Integer, List<Weight>>> elementsOfCatalogo = new HashMap<>();

    elementsOfCatalogo.put(
        Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], false),
        mapaDeActividadesConPesos_ElementoPromocionable);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], false),
        mapaDeActividadesConPesos_AgrupacionFuncional);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], false),
        mapaDeActividadesConPesos_Proyecto);

    elementsOfCatalogo.put(
        Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], true),
        mapaDeActividadesConPesos_EntregaElementoPromocionable);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], true),
        mapaDeActividadesConPesos_EntregaAgrupacionFuncional);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], true),
        mapaDeActividadesConPesos_EntregaProyecto);
    return elementsOfCatalogo;
  }


}


