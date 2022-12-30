package giss.mad.itinerario.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EjeHeredable", schema = "itinerario")
public class EjeHeredable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "element_type_id", nullable = false)
  private Integer elementTypeId;

  @Column(name = "axis_attribute_id", nullable = false)
  private Integer axisId;

  @Column(name = "for_delivery", nullable = false)
  private Boolean forDelivery;

  @Column(name = "writable", nullable = false)
  private Boolean writable;

  @Column(name = "is_deleted")
  private Integer deleted;

  @Column(name = "creation_date", nullable = false)
  private Timestamp creationDate;

  @Column(name = "update_date")
  private Timestamp updateDate;

  public EjeHeredable() {

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

  public Integer getAxisId() {
    return axisId;
  }

  public void setAxisId(Integer axisId) {
    this.axisId = axisId;
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

  public Boolean getWritable() {
    return writable;
  }

  public void setWritable(Boolean writable) {
    this.writable = writable;
  }
}
