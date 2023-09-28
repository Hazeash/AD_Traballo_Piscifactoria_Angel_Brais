import java.util.ArrayList;
import java.util.List;

public class Tanque {
    private final int numeroTanque;
    private final int capacidadMaxima;
    private List<Pez> peces;

    public Tanque(int numeroTanque, int capacidadMaxima) {
        this.numeroTanque = numeroTanque;
        this.capacidadMaxima = capacidadMaxima;
        this.peces = new ArrayList<>();
    }

    public void showStatus() {
        int ocupacion = peces.size();
        double porcentajeOcupado = (ocupacion * 100.0) / capacidadMaxima;
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

        System.out.println("=============== Tanque " + numeroTanque + " ===============");
        System.out.println("Ocupación: " + ocupacion + " / " + capacidadMaxima + " (" + porcentajeOcupado + "%)");
        System.out.println("Peces vivos: " + vivos + " / " + ocupacion + " (" + ((vivos * 100.0) / ocupacion) + "%)");
        System.out.println("Peces alimentados: " + alimentados + " / " + vivos + " (" + ((alimentados * 100.0) / vivos) + "%)");
        System.out.println("Peces adultos: " + adultos + " / " + vivos + " (" + ((adultos * 100.0) / vivos) + "%)");
        System.out.println("Hembras / Machos: " + hembras + "/" + machos);
        System.out.println("Fértiles: " + fertiles + " / " + vivos);
    }

    public void showFishStatus() {
        for (Pez pez : peces) {
            pez.mostrarInformacion();
        }
    }

    public void showCapacity() {
        int ocupacion = peces.size();
        double porcentajeOcupado = (ocupacion * 100.0) / capacidadMaxima;
        System.out.println("Tanque #" + numeroTanque + " de la piscifactoría al " + porcentajeOcupado + "% de capacidad. [" + ocupacion + " peces/" + capacidadMaxima + "]");
    }

    public void nextDay() {
        for (Pez pez : peces) {
            pez.grow();
        }
        // Realizar aquí el proceso de reproducción según tus reglas específicas
    }
}