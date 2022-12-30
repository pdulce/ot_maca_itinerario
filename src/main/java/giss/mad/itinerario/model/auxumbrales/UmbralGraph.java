package giss.mad.itinerario.model.auxumbrales;

public class UmbralGraph {

  private Integer x;// idActividad;
  private Integer y;
  private Integer z;
  private String actividad;

  private String name;
  private String recomen;

  public UmbralGraph(String nameOfactividad_, Integer idActividad_, Integer lowerRange,
      Integer upperRange, String recomendacion_) {
    this.x = idActividad_;
    if (upperRange == 9999) {
      int lowerRange_ = (String.valueOf(lowerRange).endsWith("1")) ? lowerRange - 1 : lowerRange;
      lowerRange_ = (String.valueOf(lowerRange_).endsWith("9")) ? lowerRange_ + 1 : lowerRange_;
      this.y = lowerRange_ + 80;
      this.name = "UMBRAL >=" + lowerRange_;
      this.z = 50;
    } else {
      int lowerRange_ = (String.valueOf(lowerRange).endsWith("1")) ? lowerRange - 1 : lowerRange;
      lowerRange_ = (String.valueOf(lowerRange_).endsWith("9")) ? lowerRange_ + 1 : lowerRange_;
      int upperRange_ = (String.valueOf(upperRange).endsWith("1")) ? upperRange - 1 : upperRange;
      upperRange_ = (String.valueOf(upperRange_).endsWith("9")) ? upperRange_ + 1 : upperRange_;
      this.y = (upperRange_ + lowerRange_) / 2;
      this.name = "UMBRAL [" + lowerRange_ + ", " + upperRange_ + "]";
      this.z = 25;
    }
    //this.z = //procesar(recomendacion_);
    this.actividad = nameOfactividad_;
    this.recomen = recomendacion_;
  }

  private Integer procesar(String recomendacion) {
    String[] parts = recomendacion.split("%");
    int numberOfPerc = parts.length;
    int numbers = 0;
    double percentage = 0.0;
    for (int i = 0; i < numberOfPerc; i++) {
      String[] subparts = parts[i].split(" ");
      String percentageStr = subparts[subparts.length - 1].trim();
      if (Character.isDigit(percentageStr.charAt(0))) {
        percentage += Integer.parseInt(percentageStr);
      } else {
        continue;
      }
      numbers++;
    }
    return Double.valueOf((percentage / numbers) * 0.8).intValue();
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getZ() {
    return z;
  }

  public void setZ(Integer z) {
    this.z = z;
  }

  public String getRecomen() {
    return recomen;
  }

  public void setRecomen(String recomen) {
    this.recomen = recomen;
  }

  public String getActividad() {
    return actividad;
  }

  public void setActividad(String actividad) {
    this.actividad = actividad;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

