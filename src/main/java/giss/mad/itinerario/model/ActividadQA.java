package giss.mad.itinerario.model;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ACTIVIDADQA", schema = "MACA_ITINERARIO")
public final class ActividadQA {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACTIVIDAD_SEQ")
  @SequenceGenerator(sequenceName = "ACTIVIDADQA_SEQ", allocationSize = 1, name = "ACTIVIDAD_SEQ")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "testing_stage_id", nullable = false)
  private Integer testingStageId;

  @Column(name = "name", unique = true, length = 50, nullable = false)
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

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getHelp() {
    return help;
  }

  public void setHelp(final String help) {
    this.help = help;
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

  public Integer getTestingStageId() {
    return testingStageId;
  }

  public void setTestingStageId(final Integer testingStageId) {
    this.testingStageId = testingStageId;
  }

  public String getIdealThreshold() {
    return idealThreshold;
  }

  public void setIdealThreshold(final String idealThreshold) {
    this.idealThreshold = idealThreshold;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(final Integer isDeleted) {
    this.deleted = isDeleted;
  }

  public List<Peso> getPesos() {
    return pesos;
  }

  public void setPesos(final List<Peso> pesos) {
    this.pesos = pesos;
  }

  public List<UmbralActividad> getUmbrales() {
    return umbrales;
  }

  public void setUmbrales(final List<UmbralActividad> umbrales) {
    this.umbrales = umbrales;
  }
}
