package giss.mad.itinerario.model.auxactiv;

import javax.persistence.Id;
import java.util.List;

public class ReplicaElementOEntrega {

  @Id
  private Integer id;

  private Integer catalogElementTypeId;

  private Boolean delivery;

  private List<ValorEje> attributeValuesCollection;

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }


  public Integer getCatalogElementTypeId() {
    return catalogElementTypeId;
  }

  public void setCatalogElementTypeId(final Integer catalogElementTypeId) {
    this.catalogElementTypeId = catalogElementTypeId;
  }

  public Boolean getDelivery() {
    return delivery;
  }

  public void setDelivery(final Boolean delivery) {
    this.delivery = delivery;
  }

  public List<ValorEje> getAttributeValuesCollection() {
    return attributeValuesCollection;
  }

  public void setAttributeValuesCollection(final List<ValorEje> attributeValuesCollection) {
    this.attributeValuesCollection = attributeValuesCollection;
  }
}
