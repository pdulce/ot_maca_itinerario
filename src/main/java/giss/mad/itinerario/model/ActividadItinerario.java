package giss.mad.itinerario.model;

import giss.mad.itinerario.service.Constantes;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ActividadItinerario", schema = "MACA_ITINERARIO")
public final class ActividadItinerario {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACTIVIDADITI_SEQ")
  @SequenceGenerator(sequenceName = "actividaditinerario_seq", allocationSize = 1, name = "ACTIVIDADITI_SEQ")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "activity_id")
  private Integer activityId;

  @Column(name = "quality_itinerary_id")
  private Integer qualityItineraryId;

  @Column(name = "inferred_threshold")
  private String inferredThreshold;

  @Column(name = "observations")
  private String observations;

  @Column(name = "help")
  private String help;

  @Column(name = "activity_sum_of_axes")
  private Integer activitSumOfAxes;

  @Column(name = "included_in_itinerary")
  private Integer includedInItinerary = Constantes.NUMBER_1;

  @Column(name = "is_deleted")
  private Integer deleted;

  @Column(name = "creation_date", nullable = false)
  private Timestamp creationDate;

  @Column(name = "update_date")
  private Timestamp updateDate;

  public ActividadItinerario() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(final Integer activityId) {
    this.activityId = activityId;
  }

  public Integer getQualityItineraryId() {
    return qualityItineraryId;
  }

  public void setQualityItineraryId(final Integer qualityItineraryId) {
    this.qualityItineraryId = qualityItineraryId;
  }

  public String getInferredThreshold() {
    return inferredThreshold;
  }

  public void setInferredThreshold(final String inferredThreshold) {
    this.inferredThreshold = inferredThreshold;
  }

  public String getObservations() {
    return observations;
  }

  public void setObservations(final String observations) {
    this.observations = observations;
  }

  public String getHelp() {
    return help;
  }

  public void setHelp(final String help) {
    this.help = help;
  }

  public Integer getIncludedInItinerary() {
    return includedInItinerary;
  }

  public void setIncludedInItinerary(final Integer includedInItinerary) {
    this.includedInItinerary = includedInItinerary;
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

  public Integer getActivitSumOfAxes() {
    return activitSumOfAxes;
  }

  public void setActivitSumOfAxes(final Integer activitySumOfAxes) {
    this.activitSumOfAxes = activitySumOfAxes;
  }
}
