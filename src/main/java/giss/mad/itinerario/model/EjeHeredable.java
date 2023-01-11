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
@Table(name = "EJEHEREDABLE", schema = "MACA_ITINERARIO")
public final class EjeHeredable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EJEHEREDABLE_SEQ")
  @SequenceGenerator(sequenceName = "EJEHEREDABLE_SEQ", allocationSize = 1, name = "EJEHEREDABLE_SEQ")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "element_type_id", nullable = false)
  private Integer elementTypeId;

  @Column(name = "axis_attribute_id", nullable = false)
  private Integer axisId;

  @Column(name = "for_delivery", nullable = false)
  private Integer forDelivery;

  @Column(name = "writable", nullable = false)
  private Integer writable;

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

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getElementTypeId() {
    return elementTypeId;
  }

  public void setElementTypeId(final Integer elementTypeId) {
    this.elementTypeId = elementTypeId;
  }

  public Integer getAxisId() {
    return axisId;
  }

  public void setAxisId(final Integer axisId) {
    this.axisId = axisId;
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

  public Integer getWritable() {
    return writable;
  }

  public void setWritable(final Integer writable) {
    this.writable = writable;
  }
}
