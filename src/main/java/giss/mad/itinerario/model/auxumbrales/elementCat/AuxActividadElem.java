package giss.mad.itinerario.model.auxumbrales.elementCat;

public class AuxActividadElem {

  private Integer elementTypeId;
  private Integer activityId;
  private Boolean isDelivery;

  public AuxActividadElem(Integer elementTypeId, Integer activityId, Boolean isDelivery) {
    this.elementTypeId = elementTypeId;
    this.activityId = activityId;
    this.isDelivery = isDelivery;
  }

  public Integer getElementTypeId() {
    return elementTypeId;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public Boolean getDelivery() {
    return isDelivery;
  }
}
