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
    public void upgradeFood(){
        setComidaMax(getComidaMax() + 100);
        System.out.println("Almacén de comida de la piscifactoría "+ getNombre() +" mejorado. Su capacidad ha aumentado en 100 hasta un total de " + getComidaMax());
    }
    public void addFish(){
        for (Tanque tanque:getTanques()) {
            if(tanque.getPeces().size() < 100){
                try {
                    tanque.addFish();
                }catch (Exception e){
                    //TODO AÑDIR MENSAJE DE ERROR EN CASO DE FALLO
                    e.printStackTrace();
                }

            }

        }
    }
}