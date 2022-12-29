package giss.mad.marcocalidad.itinerariocalidad.model.auxitinerario;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;


public class ItinerarioPantalla {

    @Id
    private Long id;

    private Integer elementId;

    private Boolean delivery;

    private Timestamp creationDate;

    private List<StagePantalla> stages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public List<StagePantalla> getStages() {
        return stages;
    }

    public void setStages(List<StagePantalla> stages) {
        this.stages = stages;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

}
