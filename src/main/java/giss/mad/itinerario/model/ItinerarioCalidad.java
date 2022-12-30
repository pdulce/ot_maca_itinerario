package giss.mad.itinerario.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ItinerarioCalidad", schema = "itinerario")
public class ItinerarioCalidad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "delivery_or_element_id", nullable = false)
  private Integer catalogueId;

  @Column(name = "is_delivery")
  private Boolean delivery;

  @Column(name = "is_deleted")
  private Integer deleted;

  @Column(name = "creation_date", nullable = false)
  private Timestamp creationDate;

  @Column(name = "update_date")
  private Timestamp updateDate;

  @OneToMany(cascade = CascadeType.ALL, targetEntity = ActividadItinerario.class)
  @JoinColumn(name = "quality_itinerary_id")
  private List<ActividadItinerario> actividadesDeItinerario;

  public ItinerarioCalidad() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCatalogueId() {
    return catalogueId;
  }

  public void setCatalogueId(Integer delivery_or_element_id) {
    this.catalogueId = delivery_or_element_id;
  }

  public Boolean getDelivery() {
    return delivery;
  }

  public void setDelivery(Boolean is_delivery) {
    this.delivery = is_delivery;
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

  public List<ActividadItinerario> getActividadesDeItinerario() {
    return actividadesDeItinerario;
  }

  public void setActividadesDeItinerario(List<ActividadItinerario> actividadesDeItinerario) {
    this.actividadesDeItinerario = actividadesDeItinerario;
  }
}
