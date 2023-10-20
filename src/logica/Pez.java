package logica;

import propiedades.PecesDatos;
import java.util.Random;

public abstract class Pez implements IPezMar, IPezRio {
    protected int edad = 0;
    protected final char sexo;
    protected boolean fertil = false;
    protected boolean vivo = true;
    protected boolean alimentado = false;
    protected boolean adulto;
    protected boolean optimo;
    protected int ultimaPuesta;
    public PecesDatos pecesDatos;

    public Pez(PecesDatos datos, char sexo) {
        this.sexo = sexo;
        this.pecesDatos = datos;
    }

    // Método para hacer crecer un día al pez
    public void grow(Piscifactoria piscifactoria) {
        Random random = new Random();
        if (vivo) {
            if (piscifactoria.getComidaActual() > 0) {
                alimentado = true;
                piscifactoria.comidaActual(piscifactoria.getComidaActual() - 1);
            }else{
                vivo = random.nextBoolean();
            }
        }
        if (vivo){
            edad += 1;
            ultimaPuesta -=1;
            if (edad == pecesDatos.getMadurez()){
                adulto = true;
                ultimaPuesta =0;
            }
            if (ultimaPuesta ==0){
                fertil = true;
            }
            if(edad == pecesDatos.getOptimo()){
                optimo = true;
            }
        }
    }
    public void reproducirse(){
        this.fertil = false;
        this.ultimaPuesta = pecesDatos.getCiclo();
    }
    // Método para reiniciar el pez
    public void reset() {
        edad = 0;
        vivo = true;
        alimentado = false;
        adulto = false;
        fertil = false;
    }
    public void mostrarInformacion() {
        System.out.println("--------------- " + pecesDatos.getNombre() + " ---------------");
        System.out.println("Edad: " + edad + " días");
        System.out.println("Sexo: " + (sexo == 'H' ? "Hembra" : "Macho"));
        System.out.println("Vivo: " + (vivo ? "Sí" : "No"));
        System.out.println("Alimentado: " + (alimentado ? "Sí" : "No"));
        System.out.println("Adulto: " + (adulto ? "Sí" : "No"));
        System.out.println("Fértil: " + (fertil ? "Sí" : "No"));
    }

    @Override
    public String toString() {
        // Devuelve una representación de cadena de la información relevante del pez
        return "Nombre común: " + pecesDatos.getNombre() + "\n" +
               "Nombre científico: " + pecesDatos.getCientifico() + "\n" +
               "Edad: " + edad + " días" + "\n" +
               "Sexo: " + sexo + "\n" +
               "Vivo: " + (vivo ? "Sí" : "No") + "\n" +
               "Alimentado: " + (alimentado ? "Sí" : "No") + "\n" +
               "Adulto: " + (adulto ? "Sí" : "No") + "\n" +
               "Fértil: " + (fertil ? "Sí" : "No");
    }

    public char getSexo() {
        return sexo;
    }
    public boolean estaVivo() {
        return vivo;
    }
    public boolean haSidoAlimentado() {
        return alimentado;
    }
    public boolean esFertil() {
        return fertil;
    }
    public boolean esAdulto() {
        return adulto;
    }

    public boolean esOptimo() {
        return optimo;
    }
}