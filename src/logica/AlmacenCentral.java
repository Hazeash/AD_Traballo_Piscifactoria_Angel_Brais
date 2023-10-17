package logica;

public class AlmacenCentral {
    private int capacidad = 200;
    private int comidaActual;

    public int getComidaActual() {
        return comidaActual;
    }

    public void setComidaActual(int comidaActual) {
        this.comidaActual = comidaActual;
    }
    public void showDatos(){
        System.out.println("[Actual/Maximo/Porcentaje]");
        System.out.println("["+comidaActual+"/"+capacidad+"/"+comidaActual/capacidad +"%]");
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
}