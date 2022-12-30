package giss.mad.itinerario.model.auxpesos;

public class Weight {

  private Integer idOfAxis;
  private Integer idOfvalueDomain;
  private Integer weightValue;

  public Weight(Integer idOfAxis_, Integer idOfvalueDomain_, Integer weightValue) {
    this.idOfAxis = idOfAxis_;
    this.idOfvalueDomain = idOfvalueDomain_;
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
