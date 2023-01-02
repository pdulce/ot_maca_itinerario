package giss.mad.itinerario.model.auxitinerario;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

public class ActividadQAPantalla {

  @Id
  @JsonIgnore
  private Long id;

  private String stage;
  private String activity;

  private String realization;

  @JsonIgnore
  private String observations;

  private Boolean included;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getActivity() {
    return activity;
  }

  public void setActivity(final String activity) {
    this.activity = activity;
  }

  public String getRealization() {
    return realization;
  }

  public void setRealization(final String realization) {
    this.realization = realization;
  }

  public String getObservations() {
    return observations;
  }

  public void setObservations(final String observations) {
    this.observations = observations;
  }

  public Boolean getIncluded() {
    return included;
  }

  public void setIncluded(final Boolean included) {
    this.included = included;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(final String stage) {
    this.stage = stage;
  }
}
