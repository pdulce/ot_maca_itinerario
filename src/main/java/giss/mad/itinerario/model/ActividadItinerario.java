package giss.mad.itinerario.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ActividadItinerario", schema = "itinerario")
public class ActividadItinerario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  private Boolean includedInItinerary = true;

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

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activity_id) {
    this.activityId = activity_id;
  }

  public Integer getQualityItineraryId() {
    return qualityItineraryId;
  }

  public void setQualityItineraryId(Integer quality_itinerary_id) {
    this.qualityItineraryId = quality_itinerary_id;
  }

  public String getInferredThreshold() {
    return inferredThreshold;
  }

  public void setInferredThreshold(String inferred_threshold) {
    this.inferredThreshold = inferred_threshold;
  }

  public String getObservations() {
    return observations;
  }

  public void setObservations(String observations) {
    this.observations = observations;
  }

  public String getHelp() {
    return help;
  }

  public void setHelp(String help) {
    this.help = help;
  }

  public Boolean getIncludedInItinerary() {
    return includedInItinerary;
  }

  public void setIncludedInItinerary(Boolean included_in_itinerary) {
    this.includedInItinerary = included_in_itinerary;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer is_deleted) {
    this.deleted = is_deleted;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creation_date) {
    this.creationDate = creation_date;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp update_date) {
    this.updateDate = update_date;
  }

  public Integer getActivitSumOfAxes() {
    return activitSumOfAxes;
  }

  public void setActivitSumOfAxes(Integer activity_sum_of_axes) {
    this.activitSumOfAxes = activity_sum_of_axes;
  }
}
