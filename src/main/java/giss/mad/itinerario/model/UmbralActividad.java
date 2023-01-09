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
@Table(name = "UmbralActividad", schema = "MACA_ITINERARIO")
public class UmbralActividad {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UMBRAL_SEQ")
  @SequenceGenerator(sequenceName = "umbralactividad_seq", allocationSize = 1, name="UMBRAL_SEQ")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "lower_limit", nullable = false)
  private Integer lowerLimit;

  @Column(name = "upper_limit", nullable = false)
  private Integer upperLimit;

  @Column(name = "activity_id", nullable = false)
  private Integer activityId;

  @Column(name = "element_type_id", nullable = false)
  private Integer elemenTypeId;

  @Column(name = "threshold", nullable = false)
  private String threshold;

  @Column(name = "help", nullable = false)
  private String help;

  @Column(name = "is_for_delivery")
  private Integer forDelivery;

  @Column(name = "exclude_unreached_threshold")
  private Integer excludeUnreachedThreshold;

  @Column(name = "is_deleted")
  private Integer deleted;

  @Column(name = "creation_date", nullable = false)
  private Timestamp creationDate;

  @Column(name = "update_date")
  private Timestamp updateDate;


  public UmbralActividad() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getLowerLimit() {
    return lowerLimit;
  }

  public void setLowerLimit(final Integer lowerLimit) {
    this.lowerLimit = lowerLimit;
  }

  public Integer getUpperLimit() {
    return upperLimit;
  }

  public void setUpperLimit(final Integer upperLimit) {
    this.upperLimit = upperLimit;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(final Integer activityId) {
    this.activityId = activityId;
  }

  public Integer getElemenTypeId() {
    return elemenTypeId;
  }

  public void setElemenTypeId(final Integer elementTypeId) {
    this.elemenTypeId = elementTypeId;
  }

  public String getThreshold() {
    return threshold;
  }

  public void setThreshold(final String threshold) {
    this.threshold = threshold;
  }

  public String getHelp() {
    return help;
  }

  public void setHelp(final String help) {
    this.help = help;
  }

  public Integer isForDelivery() {
    return forDelivery;
  }

  public void setForDelivery(final Integer isForDelivery) {
    this.forDelivery = isForDelivery;
  }

  public Integer isExcludeUnreachedThreshold() {
    return excludeUnreachedThreshold;
  }

  public void setExcludeUnreachedThreshold(final Integer excludeUnreachedThreshold) {
    this.excludeUnreachedThreshold = excludeUnreachedThreshold;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(final Integer isDeleted) {
    this.deleted = isDeleted;
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
