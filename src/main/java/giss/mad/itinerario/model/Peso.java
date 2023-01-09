package giss.mad.itinerario.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Peso", schema = "MACA_ITINERARIO")
public class Peso {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESO_SEQ")
  @SequenceGenerator(sequenceName = "peso_seq", allocationSize = 1, name="PESO_SEQ")
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
  private Integer forDelivery;

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

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getElementTypeId() {
    return elementTypeId;
  }

  public void setElementTypeId(final Integer elementTypeId) {
    this.elementTypeId = elementTypeId;
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

  public Integer getDomainValueId() {
    return domainValueId;
  }

  public void setDomainValueId(final Integer domainValueId) {
    this.domainValueId = domainValueId;
  }

  public Integer getWeightValue() {
    return weightValue;
  }

  public void setWeightValue(final Integer weightValue) {
    this.weightValue = weightValue;
  }

  public Integer getForDelivery() {
    return forDelivery;
  }

  public void setForDelivery(final Integer forDelivery) {
    this.forDelivery = forDelivery;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(final Integer deleted) {
    this.deleted = deleted;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(final Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(final Timestamp updateDate) {
    this.updateDate = updateDate;
  }
}
