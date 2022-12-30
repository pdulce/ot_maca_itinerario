package giss.mad.itinerario.model.auxumbrales;

public class Umbral {

  private Integer idActividad;
  private Integer lowerRange;
  private Integer upperRange;
  private String threshold;
  private Boolean exclude_unreached_threshold;

  public Umbral(Integer lowerRange, Integer upperRange, String threshold,
      Boolean exclude_unreached_threshold) {
    this.lowerRange = lowerRange;
    this.upperRange = upperRange;
    this.threshold = threshold;
    this.exclude_unreached_threshold = exclude_unreached_threshold;
  }

  public Integer getLowerRange() {
    return lowerRange;
  }

  public Integer getUpperRange() {
    return upperRange;
  }

  public String getThreshold() {
    return threshold;
  }

  public Boolean isExcluded_unreached_threshold() {
    return exclude_unreached_threshold;
  }

  public Integer getIdActividad() {
    return idActividad;
  }

  public void setIdActividad(Integer idActividad) {
    this.idActividad = idActividad;
  }
}
