package giss.mad.itinerario.model.auxumbrales;

public class Umbral {

  private Integer idActividad;
  private Integer lowerRange;
  private Integer upperRange;
  private String threshold;
  private Boolean excludeUnreachedThreshold;

  public Umbral(final Integer lowerRange, final Integer upperRange, final String threshold,
      final Boolean excludeUnreachedThreshold) {
    this.lowerRange = lowerRange;
    this.upperRange = upperRange;
    this.threshold = threshold;
    this.excludeUnreachedThreshold = excludeUnreachedThreshold;
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
    return excludeUnreachedThreshold;
  }

  public Integer getIdActividad() {
    return idActividad;
  }

  public void setIdActividad(final Integer idActividad) {
    this.idActividad = idActividad;
  }
}
