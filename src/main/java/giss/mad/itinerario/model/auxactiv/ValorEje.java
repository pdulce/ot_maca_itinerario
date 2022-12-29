package giss.mad.marcocalidad.itinerariocalidad.model.auxactiv;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

public class ValorEje {

    @Id
    @JsonIgnore
    private Long id;

    private Integer axisAttributeId;
    private Integer domainValueId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAxisAttributeId() {
        return axisAttributeId;
    }

    public void setAxisAttributeId(Integer axisAttributeId) {
        this.axisAttributeId = axisAttributeId;
    }

    public Integer getDomainValueId() {
        return domainValueId;
    }

    public void setDomainValueId(Integer domainValueId) {
        this.domainValueId = domainValueId;
    }
}
