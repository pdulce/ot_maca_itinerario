package giss.mad.itinerario.model.auxumbrales.elementCat;

import giss.mad.itinerario.model.auxumbrales.Umbral;

import giss.mad.itinerario.service.Constantes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmbralesAgrupacionFuncional implements IUmbralesActividades {

  @Override
  public Map<Integer, List<Umbral>> getUmbralesPorActividades(final Integer elementType,
      final Boolean isDelivery) {

    Map<Integer, List<Umbral>> mapaDeActividadesConUmbrales = new HashMap<>();
    List<List<Umbral>> listaUmbralesActividades = new ArrayList<>();
    for (int i = Constantes.NUMBER_0; i < ACTIVITY.length - Constantes.NUMBER_1; i++) {
      listaUmbralesActividades.add(new ArrayList<>());
    }

    int number_activity = Constantes.NUMBER_0;
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_30, Constantes.NUMBER_9999,
            "Realizar revisión al menos en un 80%", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_10, Constantes.NUMBER_29,
            "Realizar revisión al menos en un 70%", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_1],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_31, Constantes.NUMBER_9999, "Realizar entre un 70%"
            + " y un 80% sobre "
            + "todos los requisitos", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_10, Constantes.NUMBER_29, "Realizar en un 70% sobre "
            + "todos los "
            + "requisitos", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_2],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_70, Constantes.NUMBER_9999,
            "Realizar el 100% sobre requisitos críticos, y al menos un 80 % en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_10, Constantes.NUMBER_69, "Realizar en un 80% req. "
            + "Criticos y al menos un 70% del resto", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_3], listaUmbralesActividades.
        get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_60, Constantes.NUMBER_9999, "Realizar un 95% sobre "
            + "requisitos críticos", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_25, Constantes.NUMBER_59, "Realizar en un 80% sobre "
            + "los requisitos críticos", false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_4], listaUmbralesActividades.
        get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_80, Constantes.NUMBER_9999, "Realizar un 80%",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_0, Constantes.NUMBER_79, "Realizar un 60%",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_5], listaUmbralesActividades.
        get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_9999, "Realizar al menos un "
            + "75%", false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_60, Constantes.NUMBER_99, "Realizar un 50%",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_6], listaUmbralesActividades.
        get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_20, Constantes.NUMBER_9999, "Realizar un 75%",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_10, Constantes.NUMBER_19, "Realizar un 60%",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_7], listaUmbralesActividades.
        get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_9999,
            "Realizar el 100% sobre funcionalidades críticas, y al menos un 80% "
                + "en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_50, Constantes.NUMBER_99,
            "Realizar el 80% sobre funcionalidades críticas, "
                + "y al menos un 60% en el resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_8],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_80, Constantes.NUMBER_9999,
            "Realizar el 100% sobre funcionalidades críticas, "
                + "y al menos un 80% en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_20, Constantes.NUMBER_79,
            "Realizar el 80% sobre funcionalidades críticas, "
                + "y al menos un 60% en el resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_9],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_50, Constantes.NUMBER_9999,
            "Realizar el 100% sobre funcionalidades críticas, "
                + "y al menos un 80% en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_30, Constantes.NUMBER_49,
            "Realizar el 80% sobre funcionalidades críticas, "
                + "y al menos un 60% en el resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_10],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_9999,
            "Realizar el 100% sobre funcionalidades críticas, "
                + "y al menos un 80% en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_60, Constantes.NUMBER_99,
            "Realizar el 80% sobre funcionalidades críticas, "
                + "y al menos un 60% en el resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_11],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_120, Constantes.NUMBER_9999,
            "Realizar el 100% sobre funcionalidades críticas, "
                + "y al menos un 90% en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_70, Constantes.NUMBER_119,
            "Realizar el 80% sobre funcionalidades críticas, "
                + "y al menos un 70% en el resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_12],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_90, Constantes.NUMBER_9999,
            "Trans / seg un 15 % superior al nivel de accesos esperado, "
                + "Porcentaje de errores < 5 % en funcionalidades críticas",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_50, Constantes.NUMBER_89,
            "Trans / seg un 5% superior al nivel de accesos esperado, "
                + "Errores < 10 %  críticas",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_13],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_90, Constantes.NUMBER_9999,
            "Trans / seg un 15 % superior al nivel de accesos esperado, "
                + "Porcentaje de errores < 5 % en funcionalidades críticas",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_50, Constantes.NUMBER_89,
            "Trans / seg un 5% superior al nivel de accesos esperado, "
                + "Errores < 10 %  críticas",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_14],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_9999,
            "Trans / seg un 15 % superior al nivel de accesos esperado, "
                + "Porcentaje de errores < 5 % en funcionalidades críticas",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_50, Constantes.NUMBER_99,
            "Trans / seg un 5% superior al nivel de accesos esperado, "
                + "Errores < 10 %  críticas",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_15],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_70, Constantes.NUMBER_9999,
            "Trans / seg un 15 % superior al nivel de accesos esperado, "
                + "Porcentaje de errores < 5 % en funcionalidades críticas",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_30, Constantes.NUMBER_69,
            "Trans / seg un 5% superior al nivel de accesos esperado, "
                + "Errores < 10 %  críticas",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_16],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_9999,
            "Trans / seg un 15 % superior al nivel de accesos esperado, "
                + "Porcentaje de errores < 5 % en funcionalidades críticas",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_60, Constantes.NUMBER_99,
            "Trans / seg un 5% superior al nivel de accesos esperado, "
                + "Errores < 10 %  críticas",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_17],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_40, Constantes.NUMBER_9999,
            "Trans / seg un 15 % superior al nivel de accesos esperado, "
                + "Porcentaje de errores < 5 % en funcionalidades críticas",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_30, Constantes.NUMBER_39,
            "Trans / seg un 5% superior al nivel de accesos esperado, "
                + "Errores < 10 %  críticas",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_18],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_70, Constantes.NUMBER_9999,
            "Trans / seg un 15 % superior al nivel de accesos esperado, "
                + "Porcentaje de errores < 5 % en funcionalidades críticas",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_30, Constantes.NUMBER_69,
            "Trans / seg un 5% superior al nivel de accesos esperado, "
                + "Errores < 10 %  críticas",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_19],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_200, Constantes.NUMBER_9999,
            "Sin vulnerabilidades de criticidad 2 y 3 según criterios CS, "
                + "100% críticas, 80% resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_199,
            "Sin vulnerabilidades de criticidad  3 según criterios CSI, "
                + "100% críticas, 80% resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_20],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_200, Constantes.NUMBER_9999,
            "Sin vulnerabilidades de criticidad 2 y 3 según criterios CS, "
                + "100% críticas, 80% resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_199,
            "Sin vulnerabilidades de criticidad  3 según criterios CSI, "
                + "100% críticas, 80% resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_21],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_200, Constantes.NUMBER_9999,
            "Sin vulnerabilidades de criticidad 2 y 3 según criterios CS, "
                + "100% críticas, 80% resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_199,
            "Sin vulnerabilidades de criticidad  3 según criterios CSI, "
                + "100% críticas, 80% resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_22],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_160, Constantes.NUMBER_9999,
            "Sin vulnerabilidades de criticidad 2 y 3 según criterios CS, "
                + "100% críticas, 80% resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_80, Constantes.NUMBER_159,
            "Sin vulnerabilidades de criticidad  3 según criterios CSI, "
                + "100% críticas, 80% resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_23],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_120, Constantes.NUMBER_9999,
            "Sin vulnerabilidades de criticidad 2 y 3 según criterios CS, 100% críticas,"
                + " 80% resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_80, Constantes.NUMBER_119,
            "Sin vulnerabilidades de criticidad  3 según criterios CSI, 100% críticas, "
                + "80% resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_24],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_9999,
            "Nivel AA en pautas WCAG2  100% frontales críticas, 80% del resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_60, Constantes.NUMBER_99,
            "Nivel AA en pautas WCAG2 80% frontales críticas, 60% del resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_25],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_90, Constantes.NUMBER_9999, "80% puntos lista OK",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_70, Constantes.NUMBER_89, "60% puntos lista OK",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_26],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_90, Constantes.NUMBER_9999, "80% puntos lista OK",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_70, Constantes.NUMBER_89, "60% puntos lista OK",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_27],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_90, Constantes.NUMBER_9999, "80% puntos lista OK",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_70, Constantes.NUMBER_89, "60% puntos lista OK",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_28],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_140, Constantes.NUMBER_9999,
            "Cobertura del 75 % de funcionalidades críticas y del 50 % en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_60, Constantes.NUMBER_139, "40 %  críticas, "
            + "20 % resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_29],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_170, Constantes.NUMBER_9999,
            "Cobertura del 75 % de funcionalidades críticas y del 50 % en el resto",
            false));
    listaUmbralesActividades.get(number_activity).add(
        new Umbral(Constantes.NUMBER_100, Constantes.NUMBER_169, "40 %  críticas, 20 % resto",
            false));
    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_30],
        listaUmbralesActividades.get(number_activity));
    number_activity++;

    mapaDeActividadesConUmbrales.put(ACTIVITY[Constantes.NUMBER_31],
        listaUmbralesActividades.get(number_activity));

    return mapaDeActividadesConUmbrales;
  }

}
