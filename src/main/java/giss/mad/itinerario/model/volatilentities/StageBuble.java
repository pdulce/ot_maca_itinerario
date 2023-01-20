package giss.mad.itinerario.model.volatilentities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.List;

public final class StageBuble {

  @JsonIgnore
  private Integer id;
  private String name;
  private List<UmbralBuble> data;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<UmbralBuble> getData() {
    return data;
  }

  public void setData(final List<UmbralBuble> data) {
    this.data = data;
  }

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public static StageBuble obtenerStageBuble(final String stageName,
      final Collection<StageBuble> lista) {
    StageBuble retorno = null;
    for (StageBuble stage : lista) {
      if (stage.getName().contentEquals(stageName)) {
        retorno = stage;
      }
    }
    return retorno;
  }
}

