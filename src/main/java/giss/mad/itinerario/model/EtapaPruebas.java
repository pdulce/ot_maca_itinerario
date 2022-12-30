package giss.mad.itinerario.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "EtapaPruebas", schema = "itinerario")
public class EtapaPruebas {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "name", unique = true, length = 50, nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "is_deleted")
  private Integer deleted;

  @Column(name = "creation_date", nullable = false)
  private Timestamp creationDate;

  @Column(name = "update_date")
  private Timestamp updateDate;


  @OneToMany(cascade = CascadeType.ALL, targetEntity = ActividadQA.class)
  @JoinColumn(name = "testing_stage_id")
  private List<ActividadQA> actividadesQA;


  public EtapaPruebas() {

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

  public List<ActividadQA> getActividadesQA() {
    return actividadesQA;
  }

  public void setActividadesQA(List<ActividadQA> actividadesQA) {
    this.actividadesQA = actividadesQA;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer is_deleted) {
    this.deleted = is_deleted;
  }
}
