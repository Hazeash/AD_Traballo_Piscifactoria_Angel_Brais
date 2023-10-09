package logica;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Tanque {
    protected final int numeroTanque;
    protected final int capacidadMaxima;
    protected ArrayList<Pez> peces= new ArrayList<>();

    public Tanque(int numeroTanque, int capacidadMaxima) {
        this.numeroTanque = numeroTanque;
        this.capacidadMaxima = capacidadMaxima;
    }
    public int[] calcularStatus(){
        int ocupacion = peces.size();

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
        return  arrDatosTanque;
    }

    public void showStatus() {
        int[] arrDatosTanque = calcularStatus();
        double porcentajeOcupado = (arrDatosTanque[0] * 100.0) / capacidadMaxima;
        if(arrDatosTanque[0] == 0){
            System.out.println("=============== Tanque " + numeroTanque + " ===============");
            System.out.println("Ocupación: 0 peces / 0 (0%)");
            System.out.println("Peces vivos: 0 / 0 (0%)");
            System.out.println("Peces alimentados: 0 / 0 (0%)");
            System.out.println("Peces adultos: 0 / 0 (0%)");
            System.out.println("Hembras / Machos: 0 / 0 (0%)");
            System.out.println("Fértiles: 0 / 0 (0%)");
        }else{
            System.out.println("=============== Tanque " + numeroTanque + " ===============");
            System.out.println("Ocupación: " + arrDatosTanque[0] + " / " + capacidadMaxima + " (" + porcentajeOcupado + "%)");
            System.out.println("Peces vivos: " + arrDatosTanque[1] + " / " + arrDatosTanque[0] + " (" + ((arrDatosTanque[1] * 100.0) / arrDatosTanque[0]) + "%)");
            System.out.println("Peces alimentados: " + arrDatosTanque[2] + " / " + arrDatosTanque[1] + " (" + ((arrDatosTanque[2] * 100.0) / arrDatosTanque[1]) + "%)");
            System.out.println("Peces adultos: " + arrDatosTanque[3] + " / " + arrDatosTanque[1] + " (" + ((arrDatosTanque[3] * 100.0) / arrDatosTanque[1]) + "%)");
            System.out.println("Hembras / Machos: " + arrDatosTanque[4] + " / " + arrDatosTanque[5]);
            System.out.println("Fértiles: " + arrDatosTanque[6] + " / " + arrDatosTanque[1]);
        }

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

    public void nextDay(Piscifactoria piscifactoria) {
        for (Pez pez : peces) {
            pez.grow(piscifactoria);
        }
        reproducir();
        sellFish();
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
                            nuevaCria();
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
    public void nuevaCria() throws NoSuchMethodException,InstantiationException,IllegalAccessException, InvocationTargetException {
        Class<? extends Pez> tipoPez = peces.get(0).getClass();
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
        //TODO AÑADIR REGISTRO NACIMIENTO ORCA.LIB

    }
    public boolean addFish(Pez pez) throws NoSuchMethodException,InstantiationException,IllegalAccessException, InvocationTargetException {
        ArrayList<Pez> machos = new ArrayList<>();
        ArrayList<Pez> hembras = new ArrayList<>();
        if (!peces.isEmpty()){
            if (peces.get(0).getClass() == pez.getClass()){
                for (Pez p : peces) {
                    if (p.getSexo() == 'M') {
                        machos.add(pez);
                    } else {
                        hembras.add(pez);
                    }
                }
                Pez nuevoPez ;
                if (machos.size() > hembras.size()){
                    nuevoPez = pez.getClass().getDeclaredConstructor(char.class).newInstance('H');
                }else{
                    nuevoPez = pez.getClass().getDeclaredConstructor(char.class).newInstance('M');
                }
                peces.add(nuevoPez);
                return true;
            } else {
                return false;
            }
        } else {
            peces = new ArrayList<Pez>();
            Pez nuevoPez = pez.getClass().getDeclaredConstructor(char.class).newInstance('H');
            peces.add(nuevoPez);
            //TODO AÑADIR REGISTRO COMPRA PEZ ORCA.LIB
            return true;
        }
    }

    public int contarVivos() {

        int vivos = 0;
        for (Pez pez : peces) {
            if (pez.estaVivo()) {
                vivos++;
            }
        }
        return vivos;
    }

    public void sellFish(){
        for (Pez pez: peces) {
            if (pez.estaVivo() && pez.esAdulto()){
                peces.remove(pez);
                //TODO AÑADIR METODO REGISTRO VENTA ORCA.LIB
                //TODO AÑADIR MONEDAS, MOSTRAR TODOS LOS PECES VENDIDOS
            }
        }
    }
    public void cleanTank(){
        for (Pez pez :peces) {
            if (!pez.estaVivo()){
                peces.remove(pez);
            }
        }
    }
    public void emptyTank(){
        peces = new ArrayList<Pez>();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public ArrayList<Pez> getPeces() {
        return peces;
    }
}