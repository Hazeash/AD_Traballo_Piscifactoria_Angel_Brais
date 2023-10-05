package logica;

public class AlmacenCentral {
    private int capacidad;
    private int comidaActual;

    public int getComidaActual() {
        return comidaActual;
    }

    public void setComidaActual(int comidaActual) {
        this.comidaActual = comidaActual;
    }

    public AlmacenCentral() {
        this.capacidad = 200;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
}