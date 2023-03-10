package giss.mad.itinerario.model.volatilentities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import java.util.List;

public final class ValorEje {

  @Id
  @JsonIgnore
  private Long id;

  private Integer axisAttributeId;
  private List<DomainValue> domainValues;


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

  public List<DomainValue> getDomainValues() {
    return domainValues;
  }
  public void setDomainValues(final List<DomainValue> domainValues) {
    this.domainValues = domainValues;
  }

}
