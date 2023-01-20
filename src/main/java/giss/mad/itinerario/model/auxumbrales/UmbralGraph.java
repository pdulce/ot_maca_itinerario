package giss.mad.itinerario.model.auxumbrales;

import giss.mad.itinerario.service.Constantes;

public final class UmbralGraph {

  private Integer x;
  private Integer y;
  private Integer z;
  private String actividad;
  private String name;
  private String recomen;

  public UmbralGraph() {

  }
  public UmbralGraph(final String nameOfactividad, final Integer idActividad,
      final Integer lowerRange, final Integer upperRange, final String recomendacion) {
    this.x = idActividad;
    if (upperRange == Constantes.NUMBER_9999) {
      int lowerRangeAux = (String.valueOf(lowerRange).endsWith("1"))
          ? lowerRange - Constantes.NUMBER_1 : lowerRange;
      lowerRangeAux = (String.valueOf(lowerRangeAux).endsWith("9"))
          ? lowerRangeAux + Constantes.NUMBER_1 : lowerRangeAux;
      this.y = lowerRangeAux + Constantes.NUMBER_80;
      this.name = "UMBRAL >=" + lowerRangeAux;
      this.z = Constantes.NUMBER_50;
    } else {
      int lowerRangeAux = (String.valueOf(lowerRange).endsWith("1"))
          ? lowerRange - Constantes.NUMBER_1 : lowerRange;
      lowerRangeAux = (String.valueOf(lowerRangeAux).endsWith("9"))
          ? lowerRangeAux + Constantes.NUMBER_1 : lowerRangeAux;
      int upperRangeAux = (String.valueOf(upperRange).endsWith("1"))
          ? upperRange - Constantes.NUMBER_1 : upperRange;
      upperRangeAux = (String.valueOf(upperRangeAux).endsWith("9"))
          ? upperRangeAux + Constantes.NUMBER_1 : upperRangeAux;
      this.y = (upperRangeAux + lowerRangeAux) / Constantes.NUMBER_2;
      this.name = "UMBRAL [" + lowerRangeAux + ", " + upperRangeAux + "]";
      this.z = Constantes.NUMBER_25;
    }
    this.actividad = nameOfactividad;
    this.recomen = recomendacion;
  }

  public Integer getX() {
    return x;
  }

  public void setX(final Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(final Integer y) {
    this.y = y;
  }

  public Integer getZ() {
    return z;
  }

  public void setZ(final Integer z) {
    this.z = z;
  }

  public String getRecomen() {
    return recomen;
  }

  public void setRecomen(final String recomen) {
    this.recomen = recomen;
  }

  public String getActividad() {
    return actividad;
  }

  public void setActividad(final String actividad) {
    this.actividad = actividad;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }
}

