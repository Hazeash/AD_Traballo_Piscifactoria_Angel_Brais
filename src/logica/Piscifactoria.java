package logica;

import estadisticas.Estadisticas;
import simulacion.Cartera;
import simulacion.Simulador;

import java.util.ArrayList;
import java.util.List;

public abstract class Piscifactoria {
    protected String nombre;
    protected ArrayList<Tanque> tanques;
    protected int comidaActual;
    protected int comidaMax;

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
    public int getComidaActual() {
        return comidaActual;
    }
    public void comidaActual(int comidaActual) {
        this.comidaActual = comidaActual;
    }
    public int getComidaMax() {
        return comidaMax;
    }
    public void setComidaMax(int comida) {this.comidaMax = comida;}

    public abstract void addTank() ;

    public void showStatus() {
        int[] arrDatosConjunto = {0,0,0,0,0,0,0};
        int capacidadMax = 0;
        for (Tanque tanque : tanques) {
            int[] datosTanque = tanque.calcularStatus(); // Obtener los datos del tanque
            for (int i = 0; i < datosTanque.length; i++) {
                arrDatosConjunto[i] += datosTanque[i];
            }
            capacidadMax += tanque.capacidadMaxima;
        }
        if(arrDatosConjunto[0] == 0){
            System.out.println("=============== " + nombre + " ===============");
            System.out.println("Tanques: " + tanques.size());
            System.out.println("Ocupación: 0 peces / 0 (0%)");
            System.out.println("Peces vivos: 0 / 0 (0%)");
            System.out.println("Peces alimentados: 0 / 0 (0%)");
            System.out.println("Peces adultos: 0 / 0 (0%)");
            System.out.println("Hembras / Machos: 0 / 0 (0%)");
            System.out.println("Fértiles: 0 / 0 (0%)");
            System.out.println("Almacén de comida: " + comidaActual + " / " + comidaMax + " (" + ((comidaActual * 100.0) / comidaMax) + "%)");
        }else{
            System.out.println("=============== " + nombre + " ===============");
            System.out.println("Tanques: " + tanques.size());
            System.out.println("Ocupación: " + arrDatosConjunto[0] + " peces / " + capacidadMax + " ("+ ((arrDatosConjunto[0]*100) / capacidadMax) + "%)");
            System.out.println("Peces vivos: " + arrDatosConjunto[1] + " / " + arrDatosConjunto[0] + " ("+ ((arrDatosConjunto[1]*100) / arrDatosConjunto[0]) + "%)");
            System.out.println("Peces alimentados: " + arrDatosConjunto[2] + " / " + arrDatosConjunto[0] + " ("+ ((arrDatosConjunto[2]*100) / arrDatosConjunto[0]) + "%)");
            System.out.println("Peces adultos: " + arrDatosConjunto[3] + " / " + arrDatosConjunto[0] + " ("+ ((arrDatosConjunto[3]*100) / arrDatosConjunto[0]) + "%)");
            System.out.println("Hembras / Machos: " + arrDatosConjunto[4] + "/" + arrDatosConjunto[5]);
            System.out.println("Fértiles: " + arrDatosConjunto[6] + " / " + arrDatosConjunto[0] + " ("+ ((arrDatosConjunto[3]*100) / arrDatosConjunto[0]) + "%)");
            System.out.println("Almacén de comida: " + comidaActual + " / " + comidaMax + " (" + ((comidaActual * 100.0) / comidaMax) + "%)");
        }

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
        System.out.println("Depósito de comida de la piscifactoría"+ getNombre()+ " al " + ((comidaActual *100 )/ comidaMax) + " de su capacidad. [" + comidaActual+" / " + comidaMax +"]");
    }
    public int[] nextDay(Simulador simulador){
        int vendidos = 0;
        int monedas = 0;
        if(simulador.getAlmacenCentral() != null){
            for (Tanque tanque : tanques) {
                if (tanque.getPeces().size() > comidaActual){
                    if(simulador.getAlmacenCentral() !=null){
                        AlmacenCentral almacenCentral = simulador.getAlmacenCentral();
                        int diff = tanque.getPeces().size() - comidaActual;
                        if(almacenCentral.getComidaActual() >= diff) {
                            almacenCentral.setComidaActual(almacenCentral.getComidaActual()-diff);
                            this.comidaActual(this.comidaActual+diff);
                        } else {
                            almacenCentral.setComidaActual(0);
                            this.comidaActual(this.comidaActual+almacenCentral.getComidaActual());
                        }
                    }
                }
                vendidos += tanque.nextDay(this,simulador.estadisticas);
                monedas += (vendidos * tanque.getPeces().get(0).pecesDatos.getMonedas());
            }
        }else {
            for (Tanque tanque :tanques) {
                vendidos += tanque.nextDay(this,simulador.estadisticas);
                monedas += (vendidos * tanque.getPeces().get(0).pecesDatos.getMonedas());
            }
        }
        int[] datos = {0,0};
        if(vendidos != 0){
            System.out.println("Piscifactoría "+ this.nombre+" : "+vendidos+" peces vendidos por "+monedas+" monedas");
            datos[0] = vendidos;
            datos[1] = monedas;
        }
        return datos;
    }
    public void cleanTank(){
        for (Tanque tanque :tanques) {
            tanque.cleanTank();
        }
    }
    public int[] sellFish( Estadisticas estadisticas){
        int vendidos = 0;
        int monedas = 0;
        for (Tanque tanque:tanques) {
            vendidos += tanque.sellFish(estadisticas);
            monedas += (vendidos * tanque.getPeces().get(0).pecesDatos.getMonedas());
        }
        System.out.println("Piscifactoría "+ this.nombre+" : "+vendidos+" peces vendidos por "+monedas+" monedas");
        int[] datos = {vendidos,monedas};
        return datos;
    }
    public int contarVivos() {
        int vivos = 0;
        for (Tanque tanque:tanques) {
            vivos += tanque.contarVivos();
        }
        return vivos;
    }

    public int contarTotales() {
        int totales = 0;
        for (Tanque tanque:tanques) {
            totales += tanque.getPeces().size();
        }
        return totales;
    }

    public int contarMaximos() {
        int totales = 0;
        for (Tanque tanque:tanques) {
            totales += tanque.getCapacidadMaxima();
        }
        return totales;
    }

    public void comprarCinco(Cartera cartera) {

        if (this.comidaActual != this.comidaMax) {
            if (this.comidaMax - this.comidaActual < 5) {
                if (cartera.comprar(this.comidaMax - this.comidaActual)) {
                    System.out.println("Añadida: " + (this.comidaMax - this.comidaActual) + " de comida :D" );
                    System.out.println("Depósito de comida de la piscifactoría " + this.nombre + " al " + (this.comidaActual*100)/this.comidaMax + "% de su capacidad." + " ["+ this.comidaActual + "/"+ this.comidaMax+ "]");
                }
            } else {
                if (cartera.comprar(5)) {
                    this.comidaActual += 5;
                    System.out.println("Añadida: 5 de comida :D" );
                    System.out.println("Depósito de comida de la piscifactoría " + this.nombre + " al " + (this.comidaActual*100)/this.comidaMax + "% de su capacidad." + " ["+ this.comidaActual + "/"+ this.comidaMax+ "]");
                }
            }
        } else {
            System.out.println("Ya has alcanzado la cantidad máxima de comida.");
        }
    }

    public void comprarDiez(Cartera cartera) {

        if (this.comidaActual != this.comidaMax) {
            if (this.comidaMax - this.comidaActual < 10) {
                if (cartera.comprar(this.comidaMax - this.comidaActual)) {
                    System.out.println("Añadida: " + (this.comidaMax - this.comidaActual) + " de comida :D" );
                    System.out.println("Depósito de comida de la piscifactoría " + this.nombre + " al " + (this.comidaActual*100)/this.comidaMax + "% de su capacidad." + " ["+ this.comidaActual + "/"+ this.comidaMax+ "]");
                }
            } else {
                if (cartera.comprar(10)) {
                    this.comidaActual += 10;
                    System.out.println("Añadida: 10 de comida :D" );
                    System.out.println("Depósito de comida de la piscifactoría " + this.nombre + " al " + (this.comidaActual*100)/this.comidaMax + "% de su capacidad." + " ["+ this.comidaActual + "/"+ this.comidaMax+ "]");
                }
            }
        } else {
            System.out.println("Ya has alcanzado la cantidad máxima de comida.");
        }
    }

    public void comprarVeintiCinco(Cartera cartera) {

        if (this.comidaActual != this.comidaMax) {
            if (this.comidaMax - this.comidaActual < 25) {
                if (cartera.comprar(this.comidaMax - this.comidaActual)) {
                    System.out.println("Añadida: " + (this.comidaMax - this.comidaActual) + " de comida :D" );
                    System.out.println("Depósito de comida de la piscifactoría " + this.nombre + " al " + (this.comidaActual*100)/this.comidaMax + "% de su capacidad." + " ["+ this.comidaActual + "/"+ this.comidaMax+ "]");
                }
            } else {
                if (cartera.comprar(20)) {
                    this.comidaActual += 25;
                    System.out.println("Añadida: 25 de comida :D" );
                    System.out.println("Depósito de comida de la piscifactoría " + this.nombre + " al " + (this.comidaActual*100)/this.comidaMax + "% de su capacidad." + " ["+ this.comidaActual + "/"+ this.comidaMax+ "]");
                }
            }
        } else {
            System.out.println("Ya has alcanzado la cantidad máxima de comida.");
        }
    }

    public void comprarEnchido(Cartera cartera) {

       int comidaFaltante = this.comidaMax - this.comidaActual;
       int nVeces = (int) comidaFaltante / 25;
       if (comidaFaltante%25 == 0) {
           for (int i = 0; i < nVeces ;i++) {
               comprarVeintiCinco(cartera);
           }
       } else {
           for (int i = 0; i < nVeces + 1; i++) {
               comprarVeintiCinco(cartera);
           }
       }
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