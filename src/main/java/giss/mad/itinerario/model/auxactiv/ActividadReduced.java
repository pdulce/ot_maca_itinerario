package giss.mad.itinerario.model.auxactiv;

public class ActividadReduced {

  private Integer num;
  private String actividad;

  public ActividadReduced(Integer id_, String name_) {
    this.num = id_;
    this.actividad = name_;
  }

  public ActividadReduced() {

  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer id) {
    this.num = id;
  }

  public String getActividad() {
    return actividad;
  }

  public void setActividad(String name) {
    this.actividad = name;
  }
}
