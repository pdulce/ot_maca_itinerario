package giss.mad.marcocalidad.itinerariocalidad.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ActividadQA", schema = "itinerario")
public class ActividadQA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "testing_stage_id", nullable = false)
    private Integer testingStageId;

    @Column(name = "name", unique=true, length = 50, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "help")
    private String help;

    @Column(name = "ideal_threshold")
    private String idealThreshold;

    @Column(name = "is_deleted")
    private Integer deleted;

    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Peso.class)
    @JoinColumn(name = "activity_id")
    private List<Peso> pesos;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = UmbralActividad.class)
    @JoinColumn(name = "activity_id")
    private List<UmbralActividad> umbrales;


    public ActividadQA() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
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

    public Integer getTestingStageId() {
        return testingStageId;
    }
    public void setTestingStageId(Integer testing_stage_id) {
        this.testingStageId = testing_stage_id;
    }

    public String getIdealThreshold() {
        return idealThreshold;
    }

    public void setIdealThreshold(String ideal_threshold) {
        this.idealThreshold = ideal_threshold;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer is_deleted) {
        this.deleted = is_deleted;
    }

    public List<Peso> getPesos() {
        return pesos;
    }

    public void setPesos(List<Peso> pesos) {
        this.pesos = pesos;
    }

    public List<UmbralActividad> getUmbrales() {
        return umbrales;
    }

    public void setUmbrales(List<UmbralActividad> umbrales) {
        this.umbrales = umbrales;
    }
}
