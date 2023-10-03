package logica;

import java.util.ArrayList;
import java.util.List;

public abstract class Piscifactoria {
    private String nombre;
    private List<Tanque> tanques;
    private int comidaActual;
    private int comidaMax;

    public Piscifactoria(String nombre) {
        this.nombre = nombre;
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

    public void addTank(int capacidad) {
        tanques.add(new Tanque(tanques.size() + 1, capacidad));
    }

    public void showStatus() {
        int[] arrDatosConjunto = {0,0,0,0,0,0,0};
        int capacidadMax = 0;
        for (Tanque tanque : tanques) {
            int[] datosTanque = tanque.calcularStatus(); // Obtener los datos del tanque
            for (int i = 0; i < datosTanque.length; i++) {
                arrDatosConjunto[i] += datosTanque[i];
            }
            capacidadMax += capacidadMax;
        }
            System.out.println("=============== " + nombre + " ===============");
            System.out.println("Tanques: " + tanques.size());
            System.out.println("Ocupación: " + arrDatosConjunto[0] + " peces / " + capacidadMax + " ("+ ((arrDatosConjunto[0]*100) / capacidadMax) + "%)");
            System.out.println("Peces vivos: " + arrDatosConjunto[1] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[1]*100) / capacidadMax) + "%)");
            System.out.println("Peces alimentados: " + arrDatosConjunto[2] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[2]*100) / capacidadMax) + "%)");
            System.out.println("Peces adultos: " + arrDatosConjunto[3] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[3]*100) / capacidadMax) + "%)");
            System.out.println("Hembras / Machos: " + arrDatosConjunto[4] + "/" + arrDatosConjunto[5]);
            System.out.println("Fértiles: " + arrDatosConjunto[6] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[3]*100) / capacidadMax) + "%)");
            System.out.println("Almacén de comida: " + comidaActual + " / " + comidaMax + " (" + ((comidaActual * 100.0) / comidaMax) + "%)");
    }
    public void showTankStatus(){
        for (Tanque tanque:tanques) {
            tanque.showStatus();
            System.out.print("\n");
        }
    }
    public void showFishStatus(Tanque tanque){
        tanque.showFishStatus();
    }
    public void showCapacity(Tanque tanque){
        tanque.showCapacity(this);
    }
    public void showFood(){
        System.out.println("Depósito de comida de la piscifactoría"+ getNombre()+ " al " + ((getComidaActual() *100 )/getComidaMax()) + " de su capacidad. [" + getComidaActual()+" / " + getComidaMax() +"]");
    }
    @Override
    public String toString() {

        int[] arrDatosConjunto = {0,0,0,0,0,0,0};
        int capacidadMax = 0;
        for (Tanque tanque : tanques) {
            int[] datosTanque = tanque.calcularStatus(); // Obtener los datos del tanque
            for (int i = 0; i < datosTanque.length; i++) {
                arrDatosConjunto[i] += datosTanque[i];
            }
            capacidadMax += tanque.getCapacidadMaxima();
        }
       return "=============== " + nombre + " ===============" + "\n" +
        "Tanques: " + tanques.size() + "\n" +
        "Ocupación: " + arrDatosConjunto[0] + " peces / " + capacidadMax + " ("+ ((arrDatosConjunto[0]*100) / capacidadMax) + "%)" + "\n" +
        "Peces vivos: " + arrDatosConjunto[1] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[1]*100) / capacidadMax) + "%)" + "\n" +
        "Peces alimentados: " + arrDatosConjunto[2] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[2]*100) / capacidadMax) + "%)"+ "\n" +
        "Peces adultos: " + arrDatosConjunto[3] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[3]*100) / capacidadMax) + "%)"+ "\n" +
        "Hembras / Machos: " + arrDatosConjunto[4] + " / " + arrDatosConjunto[5] + "\n" +
        "Fértiles: " + arrDatosConjunto[6] + " / " + capacidadMax + " ("+ ((arrDatosConjunto[3]*100) / capacidadMax) + "%)" + "\n" +
        "Almacén de comida: " + comidaActual + " / " + comidaMax + " (" + ((comidaActual * 100.0) / comidaMax) + "%)";

    }
}

//System.out.println("x.- Nombre [vivos/total/espacio]");