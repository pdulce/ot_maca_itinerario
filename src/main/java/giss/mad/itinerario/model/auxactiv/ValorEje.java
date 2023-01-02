package giss.mad.itinerario.model.auxactiv;

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

  public void setId(final Long id) {
    this.id = id;
  }

  public Integer getAxisAttributeId() {
    return axisAttributeId;
  }

  public void setAxisAttributeId(final Integer axisAttributeId) {
    this.axisAttributeId = axisAttributeId;
  }

  public Integer getDomainValueId() {
    return domainValueId;
  }

  public void setDomainValueId(final Integer domainValueId) {
    this.domainValueId = domainValueId;
  }
}
