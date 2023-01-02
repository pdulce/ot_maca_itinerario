package giss.mad.itinerario.model.auxumbrales;

import giss.mad.itinerario.service.Constantes;

public class UmbralGraph {

  private Integer x;
  private Integer y;
  private Integer z;
  private String actividad;
  private String name;
  private String recomen;

  public UmbralGraph(final String nameOfactividad, final Integer idActividad,
      final Integer lowerRange, final Integer upperRange, final String recomendacion) {
    this.x = idActividad;
    if (upperRange == Constantes.NUMBER_9999) {
      int lowerRange_ = (String.valueOf(lowerRange).endsWith("1"))
          ? lowerRange - Constantes.NUMBER_1 : lowerRange;
      lowerRange_ = (String.valueOf(lowerRange_).endsWith("9"))
          ? lowerRange_ + Constantes.NUMBER_1 : lowerRange_;
      this.y = lowerRange_ + Constantes.NUMBER_80;
      this.name = "UMBRAL >=" + lowerRange_;
      this.z = Constantes.NUMBER_50;
    } else {
      int lowerRange_ = (String.valueOf(lowerRange).endsWith("1"))
          ? lowerRange - Constantes.NUMBER_1 : lowerRange;
      lowerRange_ = (String.valueOf(lowerRange_).endsWith("9"))
          ? lowerRange_ + Constantes.NUMBER_1 : lowerRange_;
      int upperRange_ = (String.valueOf(upperRange).endsWith("1"))
          ? upperRange - Constantes.NUMBER_1 : upperRange;
      upperRange_ = (String.valueOf(upperRange_).endsWith("9"))
          ? upperRange_ + Constantes.NUMBER_1 : upperRange_;
      this.y = (upperRange_ + lowerRange_) / Constantes.NUMBER_2;
      this.name = "UMBRAL [" + lowerRange_ + ", " + upperRange_ + "]";
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

