package giss.mad.itinerario.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Peso", schema = "itinerario")
public class Peso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "element_type_id", nullable = false)
  private Integer elementTypeId;

  @Column(name = "activity_id", nullable = false)
  private Integer activityId;

  @Column(name = "axis_attribute_id", nullable = false)
  private Integer axisAttributeId;

  @Column(name = "domain_value_id", nullable = false)
  private Integer domainValueId;

  @Column(name = "weight_value", nullable = false)
  private Integer weightValue;

  @Column(name = "is_for_delivery", nullable = false)
  private Boolean forDelivery;

  @Column(name = "is_deleted")
  private Integer deleted;

  @Column(name = "creation_date", nullable = false)
  private Timestamp creationDate;

  @Column(name = "update_date")
  private Timestamp updateDate;


  public Peso() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getElementTypeId() {
    return elementTypeId;
  }

  public void setElementTypeId(Integer elementTypeId) {
    this.elementTypeId = elementTypeId;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }

  public Integer getAxisAttributeId() {
    return axisAttributeId;
  }

  public void setAxisAttributeId(Integer axisAttributeId) {
    this.axisAttributeId = axisAttributeId;
  }

  public Integer getDomainValueId() {
    return domainValueId;
  }

  public void setDomainValueId(Integer domainValueId) {
    this.domainValueId = domainValueId;
  }

  public Integer getWeightValue() {
    return weightValue;
  }

  public void setWeightValue(Integer weightValue) {
    this.weightValue = weightValue;
  }

  public Boolean getForDelivery() {
    return forDelivery;
  }

  public void setForDelivery(Boolean forDelivery) {
    this.forDelivery = forDelivery;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }
}
