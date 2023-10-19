package logica;

import java.util.ArrayList;

public class PiscifactoriaRio extends Piscifactoria {
    public PiscifactoriaRio(String nombre) {
        super(nombre);
        this.comidaActual = 25;
        this.comidaMax =25;
        Tanque primerTanque = new Tanque(1,25);
        this.tanques = new ArrayList<>();
        this.tanques.add(primerTanque);

    }
    public void setComidaMax() {
        this.comidaMax = 25;
    }

    public void addTank() {
        this.tanques.add(new Tanque(tanques.size()+1,25));
    }

    public void upgradeFood(){
        this.comidaMax += 25;
        System.out.println("Almacén de comida de la piscifactoría "+ getNombre() +" mejorado. Su capacidad ha aumentado en 25 hasta un total de " + this.comidaMax);
    }
    public boolean addFish(Pez pez){
        boolean añadido = false;
        for (Tanque tanque:getTanques()) {
            if (añadido){
                break;
            }
            if(!tanque.getPeces().isEmpty()){
                if(tanque.getPeces().size() < 25 && tanque.getPeces().get(0).getClass() == pez.getClass() ){
                    try {
                        tanque.addFish(pez);
                        añadido = true;
                    }catch (Exception e){
                        //TODO AÑDIR MENSAJE DE ERROR EN CASO DE FALLO
                        e.printStackTrace();
                    }
                }
            }else{
                try {
                    tanque.addFish(pez);
                    añadido = true;
                }catch (Exception e){
                    //TODO AÑDIR MENSAJE DE ERROR EN CASO DE FALLO
                    e.printStackTrace();
                }
            }
        }
        return añadido;
    }
}
