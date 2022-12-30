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

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getActivity_id() {
    return activity_id;
  }

  public void setActivity_id(Integer activity_id) {
    this.activity_id = activity_id;
  }

  public Integer getAxis_attribute_id() {
    return axis_attribute_id;
  }

  public void setAxis_attribute_id(Integer axis_attribute_id) {
    this.axis_attribute_id = axis_attribute_id;
  }

  public Integer getWeight_value() {
    return weight_value;
  }

  public void setWeight_value(Integer weight_value) {
    this.weight_value = weight_value;
  }

}
