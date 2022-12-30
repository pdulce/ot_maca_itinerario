package giss.mad.itinerario.model.auxumbrales.elementCat;

import giss.mad.itinerario.model.auxumbrales.Umbral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmbralesEntregaElementoPromocionable implements IUmbralesActividades {

  @Override
  public Map<Integer, List<Umbral>> getUmbralesPorActividades(Integer elementType,
      Boolean isDelivery) {

    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales = new HashMap<>();

    List<List<Umbral>> listaUmbralesActividades = new ArrayList<>();
    for (int i = 0; i < ACTIVITY.length - 1; i++) {
      listaUmbralesActividades.add(new ArrayList<>());
    }

    int number_activity = 0;
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 9999, "Se debe acometer, excepto en los casos en los que no haya requisitos",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[1], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 9999, "Se debe acometer, excepto en los casos en los que no haya requisitos",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[2], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(11, 9999, "Exhaustivos", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 10, "Poco exhaustivos", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[3], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(23, 9999, "Realizar de forma exhaustiva", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(15, 22, "Realizar sÃ³lo las actividades automÃ¡ticas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[4], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 9999, "Realizar en el 100% del cÃ³digo entregado en la versiÃ³n", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[5], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(31, 9999, "Realizar baterÃ­a bÃ¡sica de pruebas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(20, 30, "Realizar baterÃ­a bÃ¡sica de pruebas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[6], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(31, 9999, "Exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 30, "Realizar pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[7], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(80, 9999, "Exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(51, 79, "Realizar pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[8], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 9999, "Se debe acometer", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[9], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(30, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(13, 30, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[10], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(31, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 30, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[11], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(36, 9999, "Necesaria su realizaciÃ³n", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(10, 35, "Realizar sobre funcionalidad bÃ¡sica", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[12], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(76, 9999, "Necesaria su realizaciÃ³n", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(26, 75, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[13], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(66, 9999, "Necesaria su realizaciÃ³n", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(24, 65, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[14], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(66, 9999, "Necesaria su realizaciÃ³n", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(24, 65, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[15], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(60, 9999, "Necesaria su realizaciÃ³n", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(21, 59, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[16], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(68, 9999, "Necesaria su realizaciÃ³n", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(23, 67, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[17], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 9999, "Realizar siempre", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[18], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(60, 9999, "Necesaria su realizaciÃ³n", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(21, 59, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[19], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(155, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(56, 154, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[20], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(165, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(61, 164, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[21], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(123, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(41, 122, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[22], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(145, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(42, 144, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[23], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(180, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(65, 179, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[24], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(68, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(17, 67, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[25], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(50, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(21, 49, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[26], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(50, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(21, 49, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[27], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(45, 9999, "Realizar pruebas exhaustivas", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(21, 44, "Realizar conjunto de pruebas bÃ¡sicas", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[28], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 9999, "Realizar siempre", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[29], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(0, 9999, "Realizar siempre", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[30], listaUmbralesActividades.get(number_activity));
    //number_activity++;

    //TODO

    return mapaDeActividadesConUmbrales;

  }
}
