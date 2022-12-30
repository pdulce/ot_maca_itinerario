package giss.mad.itinerario.model.auxumbrales.elementCat;

import giss.mad.itinerario.model.auxumbrales.Umbral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmbralesEntregaProyecto implements IUmbralesActividades {

  @Override
  public Map<Integer, List<Umbral>> getUmbralesPorActividades(Integer elementType,
      Boolean isDelivery) {

    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales = new HashMap<>();

    List<List<Umbral>> listaUmbralesActividades = new ArrayList<>();
    for (int i = 0; i < ACTIVITY.length - 1; i++) {
      listaUmbralesActividades.add(new ArrayList<>());
    }

    int number_activity = 0;
    mapaDeActividadesConUmbrales.put(ACTIVITY[1], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[2], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[3], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity)
        .add(new Umbral(20, 9999, "Realizar un 95% sobre requisitos crÃ­ticos", false));
    listaUmbralesActividades.get(number_activity)
        .add(new Umbral(0, 19, "Realizar en un 80% sobre los requisitos crÃ­ticos", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[4], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity)
        .add(new Umbral(30, 9999, "Realizar un 80%", false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 29, "Realizar un 60%", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[5], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity)
        .add(new Umbral(40, 9999, "Realizar al menos un 75%", false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(15, 39, "Realizar un 50%", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[6], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity)
        .add(new Umbral(20, 9999, "Realizar un 75%", false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(10, 19, "Realizar un 60%", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[7], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 9999,
        "Realizar el 100% sobre funcionalidades crÃ­ticas, y al menos un 80% en el resto", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[8], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(80, 9999,
        "Realizar el 100% sobre funcionalidades crÃ­ticas, y al menos un 80% en el resto", false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(50, 79,
        "Realizar el 80% sobre funcionalidades crÃ­ticas, y al menos un 60% en el resto", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[9], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[10], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[11], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[12], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Trans / seg un 15 % superior al nivel de accesos esperado, Porcentaje de errores < 5 % en funcionalidades crÃ­ticas",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 19,
        "Trans / seg un 5% superior al nivel de accesos esperado, Errores < 10 %  crÃ­ticas",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[13], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Trans / seg un 15 % superior al nivel de accesos esperado, Porcentaje de errores < 5 % en funcionalidades crÃ­ticas",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 19,
        "Trans / seg un 5% superior al nivel de accesos esperado, Errores < 10 %  crÃ­ticas",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[14], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Trans / seg un 15 % superior al nivel de accesos esperado, Porcentaje de errores < 5 % en funcionalidades crÃ­ticas",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 19,
        "Trans / seg un 5% superior al nivel de accesos esperado, Errores < 10 %  crÃ­ticas",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[15], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Trans / seg un 15 % superior al nivel de accesos esperado, Porcentaje de errores < 5 % en funcionalidades crÃ­ticas",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 19,
        "Trans / seg un 5% superior al nivel de accesos esperado, Errores < 10 %  crÃ­ticas",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[16], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Trans / seg un 15 % superior al nivel de accesos esperado, Porcentaje de errores < 5 % en funcionalidades crÃ­ticas",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 19,
        "Trans / seg un 5% superior al nivel de accesos esperado, Errores < 10 %  crÃ­ticas",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[17], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Trans / seg un 15 % superior al nivel de accesos esperado, Porcentaje de errores < 5 % en funcionalidades crÃ­ticas",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 19,
        "Trans / seg un 5% superior al nivel de accesos esperado, Errores < 10 %  crÃ­ticas",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[18], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Trans / seg un 15 % superior al nivel de accesos esperado, Porcentaje de errores < 5 % en funcionalidades crÃ­ticas",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(0, 19,
        "Trans / seg un 5% superior al nivel de accesos esperado, Errores < 10 %  crÃ­ticas",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[19], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(40, 9999,
        "Sin vulnerabilidades de criticidad 2 y 3 segÃºn criterios CS, 100% crÃ­ticas, 80% resto",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(15, 39,
        "Sin vulnerabilidades de criticidad  3 segÃºn criterios CSI, 100% crÃ­ticas, 80% resto",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[20], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(40, 9999,
        "Sin vulnerabilidades de criticidad 2 y 3 segÃºn criterios CS, 100% crÃ­ticas, 80% resto",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(15, 39,
        "Sin vulnerabilidades de criticidad  3 segÃºn criterios CSI, 100% crÃ­ticas, 80% resto",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[21], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(40, 9999,
        "Sin vulnerabilidades de criticidad 2 y 3 segÃºn criterios CS, 100% crÃ­ticas, 80% resto",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(15, 39,
        "Sin vulnerabilidades de criticidad  3 segÃºn criterios CSI, 100% crÃ­ticas, 80% resto",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[22], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(40, 9999,
        "Sin vulnerabilidades de criticidad 2 y 3 segÃºn criterios CS, 100% crÃ­ticas, 80% resto",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(15, 39,
        "Sin vulnerabilidades de criticidad  3 segÃºn criterios CSI, 100% crÃ­ticas, 80% resto",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[23], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(40, 9999,
        "Sin vulnerabilidades de criticidad 2 y 3 segÃºn criterios CS, 100% crÃ­ticas, 80% resto",
        false));
    listaUmbralesActividades.get(number_activity).add(new Umbral(15, 39,
        "Sin vulnerabilidades de criticidad  3 segÃºn criterios CSI, 100% crÃ­ticas, 80% resto",
        false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[24], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[25], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[26], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[27], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[28], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(20, 9999,
        "Cobertura del 75 % de funcionalidades crÃ­ticas y del 50 % en el resto", false));
    listaUmbralesActividades.get(number_activity)
        .add(new Umbral(10, 19, "40 %  crÃ­ticas, 20 % resto", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[29], listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(new Umbral(50, 9999,
        "Cobertura del 75 % de funcionalidades crÃ­ticas y del 50 % en el resto", false));
    listaUmbralesActividades.get(number_activity)
        .add(new Umbral(10, 49, "40 %  crÃ­ticas, 20 % resto", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[30], listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[31], listaUmbralesActividades.get(number_activity));

    return mapaDeActividadesConUmbrales;
  }

}
