package giss.mad.itinerario.model.auxumbrales;

import giss.mad.itinerario.service.Constantes;

public class UmbralBuble {

  private Integer value;
  private String actividad;
  private String name;
  private String recomen;

  public UmbralBuble(final String nameOfActividad, final Integer lowerRange,
      final Integer upperRange, final String recomendacion) {
    this.actividad = nameOfActividad;
    this.recomen = recomendacion;
    if (upperRange >= Constantes.NUMBER_500) {
      this.name = "Rango >=" + lowerRange;
      this.value = lowerRange + Constantes.NUMBER_80;
    } else {
      this.name = "Rango [" + lowerRange + ", " + upperRange + "]";
      this.value = upperRange - lowerRange;
    }
  }

  public UmbralBuble(final String nameOfactividad, final String recomendacion,
      final Integer sumOfWeights) {
    this.actividad = nameOfactividad;
    this.recomen = recomendacion;
    this.name = "Suma obtenida: " + sumOfWeights;
    this.value = sumOfWeights;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(final Integer value) {
    this.value = value;
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

  public String getRecomen() {
    return recomen;
  }

  public void setRecomen(final String recomen) {
    this.recomen = recomen;
  }
}

