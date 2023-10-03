package logica;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Tanque {
    private final int numeroTanque;
    private final int capacidadMaxima;
    private List<Pez> peces;

    public Tanque(int numeroTanque, int capacidadMaxima) {
        this.numeroTanque = numeroTanque;
        this.capacidadMaxima = capacidadMaxima;
        this.peces = new ArrayList<>();
    }

    public int[] showStatus() {
        int ocupacion = peces.size();
        double porcentajeOcupado = (ocupacion * 100.0) / capacidadMaxima;
        int vivos = 0;
        int alimentados = 0;
        int adultos = 0;
        int hembras = 0;
        int machos = 0;
        int fertiles = 0;

        for (Pez pez : peces) {
            if (pez.estaVivo()) {
                vivos++;
                if (pez.haSidoAlimentado()) {
                    alimentados++;
                }
                if (pez.esAdulto()) {
                    adultos++;
                }
                if (pez.getSexo() == 'H' ) {
                    hembras++;
                } else {
                    machos++;
                }
                if (pez.esFertil()) {
                    fertiles++;
                }
            }
        }
        int[] arrDatosTanque ={ocupacion,vivos,alimentados,adultos,hembras,machos,fertiles};

        System.out.println("=============== logica.Tanque " + numeroTanque + " ===============");
        System.out.println("Ocupación: " + ocupacion + " / " + capacidadMaxima + " (" + porcentajeOcupado + "%)");
        System.out.println("Peces vivos: " + vivos + " / " + ocupacion + " (" + ((vivos * 100.0) / ocupacion) + "%)");
        System.out.println("Peces alimentados: " + alimentados + " / " + vivos + " (" + ((alimentados * 100.0) / vivos) + "%)");
        System.out.println("Peces adultos: " + adultos + " / " + vivos + " (" + ((adultos * 100.0) / vivos) + "%)");
        System.out.println("Hembras / Machos: " + hembras + "/" + machos);
        System.out.println("Fértiles: " + fertiles + " / " + vivos);
        return arrDatosTanque;
    }

    public void showFishStatus() {
        for (Pez pez : peces) {
            pez.mostrarInformacion();
        }
    }

    public void showCapacity(Piscifactoria piscifactoria) {
        int ocupacion = peces.size();
        double porcentajeOcupado = (ocupacion * 100.0) / capacidadMaxima;
        System.out.println("Tanque " + numeroTanque + " de la piscifactoría"+ piscifactoria.getNombre() + " al " + porcentajeOcupado + "% de capacidad. [" + ocupacion + " /" + capacidadMaxima + "]");
    }

    public void nextDay(int comida) {
        for (Pez pez : peces) {
            pez.grow(comida);
        }
        reproducir();
    }
    public void reproducir(){
        ArrayList<Pez> machos= new ArrayList<>();
        ArrayList<Pez> hembras= new ArrayList<>();
        for (Pez pez : peces) {
            if (pez.estaVivo()){
                if (pez.getSexo() == 'M'){
                    machos.add(pez);
                }else {
                    hembras.add(pez);
                }
            }
        }
        //Recorre todos os machos e si topa femia fertil reproduce.
        for (Pez macho :machos) {
            if (macho.esFertil()){
                for (int i =0;i< hembras.size();i++) {
                    if (hembras.get(i).esFertil()){
                      i= hembras.size();
                        try{
                            macho.reproducirse();
                            hembras.get(i).reproducirse();
                            nuevaCria(macho.getClass());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
            //Comprobar si el macho sigue siendo fertil , lo que significa que no hay hembras fertiles en el tanque , por lo cual no se comprueba con el resto de machos.
            if(macho.esFertil()){
                break;
            }
        }
    }
    public void nuevaCria(Class<? extends Pez> tipoPez) throws NoSuchMethodException,InstantiationException,IllegalAccessException, InvocationTargetException {
        ArrayList<Pez> machos = new ArrayList<>();
        ArrayList<Pez> hembras = new ArrayList<>();
        for (Pez pez : peces) {
            if (pez.getSexo() == 'M') {
                machos.add(pez);
            } else {
                hembras.add(pez);
            }
        }
        Pez nuevoPez ;
        if (machos.size() > hembras.size()){
            nuevoPez = tipoPez.getDeclaredConstructor(char.class).newInstance('H');
        }else{
            nuevoPez = tipoPez.getDeclaredConstructor(char.class).newInstance('M');
        }
            peces.add(nuevoPez);

    }
    public int getNumeroTanque() {
        return numeroTanque;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public List<Pez> getPeces() {
        return peces;
    }

    public void setPeces(List<Pez> peces) {
        this.peces = peces;
    }
}