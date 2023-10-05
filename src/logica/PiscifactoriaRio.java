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
    public void upgradeFood(){
        setComidaMax(getComidaMax() + 25);
        System.out.println("Almacén de comida de la piscifactoría "+ getNombre() +" mejorado. Su capacidad ha aumentado en 25 hasta un total de " + getComidaMax());
    }
    public void addFish(){
        for (Tanque tanque:getTanques()) {
            if(tanque.getPeces().size() < 25){
                try {
                    tanque.addFish();
                }catch (Exception e){
                    //TODO AÑDIR MENSAJE DE ERRON EN CASO DE FALLO
                    e.printStackTrace();
                }

            }

        }
    }
}
