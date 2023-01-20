package giss.mad.itinerario.model.volatilentities;

public final class ActividadReduced {

  private Integer num;
  private String actividad;

  public ActividadReduced(final Integer id, final String name) {
    this.num = id;
    this.actividad = name;
  }

  public ActividadReduced() {

  }

  public Integer getNum() {
    return num;
  }

  public void setNum(final Integer id) {
    this.num = id;
  }

  public String getActividad() {
    return actividad;
  }

  public void setActividad(final String name) {
    this.actividad = name;
  }
}
