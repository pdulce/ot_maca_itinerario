package giss.mad.marcocalidad.itinerariocalidad.model.auxactiv;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import java.util.List;

public class ReplicaElementOEntrega {

    @Id
    private Integer id;

    private Integer catalogElementTypeId;

    private Boolean delivery;

    List<ValorEje> attributeValuesCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getCatalogElementTypeId() {
        return catalogElementTypeId;
    }

    public void setCatalogElementTypeId(Integer catalogElementTypeId) {
        this.catalogElementTypeId = catalogElementTypeId;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public List<ValorEje> getAttributeValuesCollection() {
        return attributeValuesCollection;
    }

    public void setAttributeValuesCollection(List<ValorEje> attributeValuesCollection) {
        this.attributeValuesCollection = attributeValuesCollection;
    }
}
