package giss.mad.marcocalidad.itinerariocalidad.model.auxumbrales;

public class UmbralBuble {
    private Integer value;
    private String actividad;
    private String name;
    private String recomen;

    public UmbralBuble(String nameOfactividad_, Integer lowerRange_, Integer upperRange_, String recomendacion_) {
        this.actividad = nameOfactividad_;
        this.recomen = recomendacion_;
        if (upperRange_ >= 500) {
            this.name = "Rango >=" + lowerRange_;
            this.value = lowerRange_ + 80;
        }else{
            this.name = "Rango [" + lowerRange_ + ", " + upperRange_ + "]";
            this.value = upperRange_ - lowerRange_;
        }
    }

    public UmbralBuble(String nameOfactividad_, String recomendacion_, Integer sumOfWeights) {
        this.actividad = nameOfactividad_;
        this.recomen = recomendacion_;
        this.name = "Suma obtenida: " + sumOfWeights;
        this.value = sumOfWeights;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public String getRecomen() {
        return recomen;
    }

    public void setRecomen(String recomen) {
        this.recomen = recomen;
    }
}

