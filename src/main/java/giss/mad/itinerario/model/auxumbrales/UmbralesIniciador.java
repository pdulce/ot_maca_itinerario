package giss.mad.itinerario.model.auxumbrales;

import giss.mad.itinerario.model.auxumbrales.elementCat.*;

import giss.mad.itinerario.service.Constantes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmbralesIniciador {

  public static final int ELEMENTO_PROMOCIONABLE = Constantes.NUMBER_1;
  public static final int AGRUPACION_FUNCIONAL = Constantes.NUMBER_2;
  public static final int PROYECTO = Constantes.NUMBER_3;
  private static final int[] ELEMENT_OF_CATALOGUE = {Constantes.NUMBER_0, Constantes.NUMBER_1,
      Constantes.NUMBER_2, Constantes.NUMBER_3};

  public final Map<Map<Integer, Boolean>, Map<Integer, List<Umbral>>> getElementsOfCatalogo() {
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_ElementoPromocionable =
        new UmbralesElementoPromocionable().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], false);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_EntregaElementoPromocionable =
        new UmbralesEntregaElementoPromocionable().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], true);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_AgrupacionFuncional =
        new UmbralesAgrupacionFuncional().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/false);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_EntregaAgrupacionFuncional =
        new UmbralesEntregaAgrupacionFuncional().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/true);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_Proyecto =
        new UmbralesProyecto().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/false);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_EntregaProyecto =
        new UmbralesEntregaProyecto().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/true);
    Map<Map<Integer, Boolean>, Map<Integer, List<Umbral>>> elementsOfCatalogo = new HashMap<>();
    elementsOfCatalogo.put(
        Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], false),
        mapaDeActividadesConUmbrales_ElementoPromocionable);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], false),
        mapaDeActividadesConUmbrales_AgrupacionFuncional);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], false),
        mapaDeActividadesConUmbrales_Proyecto);
    elementsOfCatalogo.put(
        Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], true),
        mapaDeActividadesConUmbrales_EntregaElementoPromocionable);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], true),
        mapaDeActividadesConUmbrales_EntregaAgrupacionFuncional);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], true),
        mapaDeActividadesConUmbrales_EntregaProyecto);
    return elementsOfCatalogo;
  }
}
