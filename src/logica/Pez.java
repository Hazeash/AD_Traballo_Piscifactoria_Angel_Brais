package logica;

import logica.IPezMar;
import logica.IPezRio;
import propiedades.PecesDatos;

import java.util.List;
import java.util.Random;

public abstract class Pez implements IPezMar, IPezRio {

    private int edad;
    private char sexo;
    private boolean fertil;
    private boolean vivo;
    private boolean alimentado;
    private boolean adulto;
    private int edadUltimaPuesta;
    private PecesDatos pecesDatos;

    public Pez(PecesDatos datos, char sexo) {
        this.edad = 0;
        this.sexo = sexo;
        this.fertil = false;
        this.vivo = true;
        this.alimentado = false;
    }

    // Método para hacer crecer un día al pez
    public void grow(int comida) {
        Random random = new Random();
        if (vivo) {
            if (comida > 0) {
                alimentado = true;
            } else {
                vivo = random.nextBoolean();
            }
            edad += 1;
            if (edad >= pecesDatos.getMadurez()){
                adulto = true;
            }
            if (edad == edadUltimaPuesta + pecesDatos.getCiclo()){
                fertil = true;
            }
            if (edad == pecesDatos.getOptimo()){
                //super.venta();

            }

        }
    }
    public void reproducirse(){
        fertil = false;
        edadUltimaPuesta = edad;
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

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public boolean estaVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    public boolean haSidoAlimentado() {
        return alimentado;
    }
    public void setAlimentado(boolean alimentado) {
        this.alimentado = alimentado;
    }
    public boolean esFertil() {
        return fertil;
    }
    public void setFertil(boolean fertil) {
        this.fertil = fertil;
    }
    public boolean esAdulto() {
        return adulto;
    }

    public int getEdadUltimaPuesta() {
        return edadUltimaPuesta;
    }
}