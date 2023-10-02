package logica;

import java.util.List;

public class Piscifactoria {
    private String nombre;
    private List<Tanque> tanques;
    private int comidaActual;
    private int comidaMax;
    
    public Piscifactoria(String nombre, List<Tanque> tanques, int comidaActual, int comidaMax) {
        this.nombre = nombre;
        this.tanques = tanques;
        this.comidaActual = comidaActual;
        this.comidaMax = comidaMax;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Tanque> getTanques() {
        return tanques;
    }
    public void setTanques(List<Tanque> tanques) {
        this.tanques = tanques;
    }
    public int getComidaActual() {
        return comidaActual;
    }
    public void setComidaActual(int comidaActual) {
        this.comidaActual = comidaActual;
    }
    public int getComidaMax() {
        return comidaMax;
    }
    public void setComidaMax(int comidaMax) {
        this.comidaMax = comidaMax;
    }

    public void showStatus(){
        int[] arrDatosConjunto = {0,0,0,0,0,0,0};
        for (Tanque tanque : tanques) {
            int[] datosTanque = tanque.showStatus(); // Obtener los datos del tanque
            for (int i = 0; i < datosTanque.length; i++) {
                arrDatosConjunto[i] += datosTanque[i]; 
            }  
            System.out.println("=============== " + nombre + " ===============");
            System.out.println("Tanques: " + tanques.size());
            System.out.println("Ocupación: " + arrDatosConjunto[0] + " peces / " + tanque.getCapacidadMaxima() + " ("+ ((arrDatosConjunto[0]*100) / tanque.getCapacidadMaxima()) + "%)");
            System.out.println("Peces vivos: " + arrDatosConjunto[1] + " / " + tanque.getCapacidadMaxima() + " ("+ ((arrDatosConjunto[1]*100) / tanque.getCapacidadMaxima()) + "%)");
            System.out.println("Peces alimentados: " + arrDatosConjunto[2] + " / " + tanque.getCapacidadMaxima() + " ("+ ((arrDatosConjunto[2]*100) / tanque.getCapacidadMaxima()) + "%)");
            System.out.println("Peces adultos: " + arrDatosConjunto[3] + " / " + tanque.getCapacidadMaxima() + " ("+ ((arrDatosConjunto[3]*100) / tanque.getCapacidadMaxima()) + "%)");
            System.out.println("Hembras / Machos: " + arrDatosConjunto[4] + "/" + arrDatosConjunto[5]);
            System.out.println("Fértiles: " + arrDatosConjunto[6] + " / " + tanque.getCapacidadMaxima() + " ("+ ((arrDatosConjunto[3]*100) / tanque.getCapacidadMaxima()) + "%)");
            System.out.println("Almacén de comida: " + comidaActual + " / " + comidaMax + " (" + ((comidaActual * 100.0) / comidaMax) + "%)");
        }
    }
    
}
