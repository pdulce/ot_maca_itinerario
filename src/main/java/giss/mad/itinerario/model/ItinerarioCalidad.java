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
import javax.persistence.Table;
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

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getCatalogueId() {
    return catalogueId;
  }

  public void setCatalogueId(final Integer deliveryOrElementId) {
    this.catalogueId = deliveryOrElementId;
  }

  public Boolean getDelivery() {
    return delivery;
  }

  public void setDelivery(final Boolean isDelivery) {
    this.delivery = isDelivery;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(final Integer isDeleted) {
    this.deleted = isDeleted;
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

  public List<ActividadItinerario> getActividadesDeItinerario() {
    return actividadesDeItinerario;
  }

  public void setActividadesDeItinerario(final List<ActividadItinerario> actividadesDeItinerario) {
    this.actividadesDeItinerario = actividadesDeItinerario;
  }
}
