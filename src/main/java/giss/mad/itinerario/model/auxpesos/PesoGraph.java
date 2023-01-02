package giss.mad.itinerario.model.auxpesos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class PesoGraph {

  @Id
  @JsonIgnore
  private Long id;
  private Integer activity_id;
  private Integer axis_attribute_id;
  private Integer weight_value;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Integer getActivity_id() {
    return activity_id;
  }

  public void setActivity_id(final Integer activityId) {
    this.activity_id = activityId;
  }

  public Integer getAxis_attribute_id() {
    return axis_attribute_id;
  }

  public void setAxis_attribute_id(final Integer axisAttributeId) {
    this.axis_attribute_id = axisAttributeId;
  }

  public Integer getWeight_value() {
    return weight_value;
  }

  public void setWeight_value(final Integer weightValue) {
    this.weight_value = weightValue;
  }

}
