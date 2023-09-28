public abstract class Pez {
    private String nombreComun;
    private String nombreCientifico;
    private int edad;
    private char sexo;
    private boolean vivo;
    private boolean alimentado;
    private boolean adulto;
    private boolean fertil;

    public Pez(String nombreComun, String nombreCientifico, char sexo) {
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.edad = 0;
        this.sexo = sexo;
        this.vivo = true;
        this.alimentado = false;
        this.adulto = false;
        this.fertil = false;
    }

    
    // Método para hacer crecer un día al pez
    public void grow() {
        if (vivo) {
            // Lógica de alimentación, verificación de madurez, fertilidad y envejecimiento
        }
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
        System.out.println("--------------- " + nombreComun + " ---------------");
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
        return "Nombre común: " + nombreComun + "\n" +
               "Nombre científico: " + nombreCientifico + "\n" +
               "Edad: " + edad + " días" + "\n" +
               "Sexo: " + sexo + "\n" +
               "Vivo: " + (vivo ? "Sí" : "No") + "\n" +
               "Alimentado: " + (alimentado ? "Sí" : "No") + "\n" +
               "Adulto: " + (adulto ? "Sí" : "No") + "\n" +
               "Fértil: " + (fertil ? "Sí" : "No");
    }


    public String getNombreComun() {
        return nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
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


    public boolean esAdulto() {
        return adulto;
    }


    public void setAdulto(boolean adulto) {
        this.adulto = adulto;
    }


    public boolean esFertil() {
        return fertil;
    }


    public void setFertil(boolean fertil) {
        this.fertil = fertil;
    }    
}

