package giss.mad.itinerario.model.auxpesos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Id;

public class PesoGraph {

  @Id
  @JsonIgnore
  private Long id;
  private Integer activityId;
  private Integer axisAttributeId;
  private Integer weightValue;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(final Integer activityId) {
    this.activityId = activityId;
  }

  public Integer getAxisAttributeId() {
    return axisAttributeId;
  }

  public void setAxisAttributeId(final Integer axisAttributeId) {
    this.axisAttributeId = axisAttributeId;
  }

  public Integer getWeightValue() {
    return weightValue;
  }

  public void setWeightValue(final Integer weightValue) {
    this.weightValue = weightValue;
  }

}
