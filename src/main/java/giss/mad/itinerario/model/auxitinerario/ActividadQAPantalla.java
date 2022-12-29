package giss.mad.marcocalidad.itinerariocalidad.model.auxitinerario;

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getRealization() {
        return realization;
    }

    public void setRealization(String realization) {
        this.realization = realization;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Boolean getIncluded() {
        return included;
    }

    public void setIncluded(Boolean included_) {
        this.included = included_;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
