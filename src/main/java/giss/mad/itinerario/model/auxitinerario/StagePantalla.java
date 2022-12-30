package giss.mad.itinerario.model.auxitinerario;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import java.util.List;

public class StagePantalla {

  @Id
  @JsonIgnore
  private Integer id;

  @JsonIgnore
  private Integer idStage;

  private String stage;

  private List<ActividadQAPantalla> activities;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public Integer getIdStage() {
    return idStage;
  }

  public void setIdStage(Integer idStage) {
    this.idStage = idStage;
  }

  public List<ActividadQAPantalla> getActivities() {
    return activities;
  }

  public void setActivities(List<ActividadQAPantalla> activities) {
    this.activities = activities;
  }
}
