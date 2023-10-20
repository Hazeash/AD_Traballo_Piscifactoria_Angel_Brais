package logica;

import java.util.ArrayList;

public class PiscifactoriaMar extends Piscifactoria{
    public PiscifactoriaMar(String nombre) {
        super(nombre);
        this.comidaActual =100;
        this.comidaMax =100;
        Tanque primerTanque = new Tanque(1,100);
        this.tanques = new ArrayList<>();
        this.tanques.add(primerTanque);
    }
    public void setComidaMax() {
        this.comidaMax = 100;
    }

    public void addTank() {
        this.tanques.add(new Tanque(tanques.size()+1,100));

    }
    public void upgradeFood(){
        this.comidaMax += 100;
        System.out.println("Almacén de comida de la piscifactoría "+ getNombre() +" mejorado. Su capacidad ha aumentado en 100 hasta un total de " + this.comidaMax);
    }
    public boolean addFish(Pez pez){
        boolean añadido = false;
        for (Tanque tanque:getTanques()) {
            if (añadido){
                break;
            }
            if(!tanque.getPeces().isEmpty()){
                if(tanque.getPeces().size() < 100 && tanque.getPeces().get(0).getClass() == pez.getClass() ){
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