package giss.mad.itinerario.model.auxumbrales;

import giss.mad.itinerario.model.auxumbrales.elementCat.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmbralesIniciador {

  public static final int ELEMENTO_PROMOCIONABLE = 1, AGRUPACION_FUNCIONAL = 2, PROYECTO = 3;
  private static final int[] ELEMENT_OF_CATALOGUE = {0, 1, 2, 3};

  public final Map<Map<Integer, Boolean>, Map<Integer, List<Umbral>>> getElementsOfCatalogo() {

    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_ElementoPromocionable = new UmbralesElementoPromocionable().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], /*is_delivery*/false);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_EntregaElementoPromocionable = new UmbralesEntregaElementoPromocionable().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], /*is_delivery*/true);

    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_AgrupacionFuncional = new UmbralesAgrupacionFuncional().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/false);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_EntregaAgrupacionFuncional = new UmbralesEntregaAgrupacionFuncional().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/true);

    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_Proyecto = new UmbralesProyecto().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/false);
    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales_EntregaProyecto = new UmbralesEntregaProyecto().getUmbralesPorActividades(
        ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/true);

    Map<Map<Integer, Boolean>, Map<Integer, List<Umbral>>> elementsOfCatalogo = new HashMap<>();

    elementsOfCatalogo.put(
        Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], /*is_delivery*/false),
        mapaDeActividadesConUmbrales_ElementoPromocionable);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/false),
        mapaDeActividadesConUmbrales_AgrupacionFuncional);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/false),
        mapaDeActividadesConUmbrales_Proyecto);

    elementsOfCatalogo.put(
        Map.of(ELEMENT_OF_CATALOGUE[ELEMENTO_PROMOCIONABLE], /*is_delivery*/true),
        mapaDeActividadesConUmbrales_EntregaElementoPromocionable);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[AGRUPACION_FUNCIONAL], /*is_delivery*/true),
        mapaDeActividadesConUmbrales_EntregaAgrupacionFuncional);
    elementsOfCatalogo.put(Map.of(ELEMENT_OF_CATALOGUE[PROYECTO], /*is_delivery*/true),
        mapaDeActividadesConUmbrales_EntregaProyecto);
    return elementsOfCatalogo;
  }
}
