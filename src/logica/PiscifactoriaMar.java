package logica;

import java.util.ArrayList;

public class PiscifactoriaMar extends Piscifactoria{
    public PiscifactoriaMar(String nombre) {
        super(nombre);
        this.setComidaActual(100);
        this.setComidaMax(100);
        Tanque primerTanque = new Tanque(1,100);
        ArrayList<Tanque> arrTanques = new ArrayList<>();
        arrTanques.add(primerTanque);
        this.setTanques(arrTanques);
    }
}