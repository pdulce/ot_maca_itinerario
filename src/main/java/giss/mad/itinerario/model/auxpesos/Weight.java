package giss.mad.itinerario.model.auxpesos;

public class Weight {

  private Integer idOfAxis;
  private Integer idOfvalueDomain;
  private Integer weightValue;

  public Weight(final  Integer idOfAxis, final Integer idOfvalueDomain, final Integer weightValue) {
    this.idOfAxis = idOfAxis;
    this.idOfvalueDomain = idOfvalueDomain;
    this.weightValue = weightValue;
  }

  public Integer getWeightValue() {
    return weightValue;
  }

  public Integer getIdOfvalueDomain() {
    return idOfvalueDomain;
  }

  public Integer getIdOfAxis() {
    return idOfAxis;
  }

}
