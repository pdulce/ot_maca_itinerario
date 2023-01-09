package giss.mad.itinerario.model.auxitinerario;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;


public final class ItinerarioPantalla {

  @Id
  private Long id;

  private Integer elementId;

  private Integer delivery;

  private Timestamp creationDate;

  private List<StagePantalla> stages;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Integer getElementId() {
    return elementId;
  }

  public void setElementId(final Integer elementId) {
    this.elementId = elementId;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(final Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  public List<StagePantalla> getStages() {
    return stages;
  }

  public void setStages(final List<StagePantalla> stages) {
    this.stages = stages;
  }

  public Integer getDelivery() {
    return delivery;
  }

  public void setDelivery(final Integer delivery) {
    this.delivery = delivery;
  }

}
