package logica;

import java.util.ArrayList;

public class PiscifactoriaRio extends Piscifactoria{
    public PiscifactoriaRio(String nombre) {
        super(nombre);
        this.setComidaActual(25);
        this.setComidaMax(25);
        Tanque primerTanque = new Tanque(1,25);
        ArrayList<Tanque> arrTanques = new ArrayList<>();
        arrTanques.add(primerTanque);
        this.setTanques(arrTanques);
    }
}
