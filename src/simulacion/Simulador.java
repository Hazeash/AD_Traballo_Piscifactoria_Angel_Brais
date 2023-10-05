package simulacion;

import logica.AlmacenCentral;
import logica.Piscifactoria;
import logica.PiscifactoriaMar;
import logica.PiscifactoriaRio;

import java.util.ArrayList;
import java.util.Scanner;

public class Simulador {

    /** Prueba de Javadoc */
    private int dia;
    private ArrayList<PiscifactoriaRio> arrPisRio = new ArrayList<>();
    private ArrayList<PiscifactoriaMar> arrPisMar = new ArrayList<>();
    private String nombreEmpresa;
    private static final Scanner scanner = new Scanner(System.in);
    private static Cartera cartera;

    private AlmacenCentral almacenCentral = null;

    public AlmacenCentral getAlmacenCentral() {
        return almacenCentral;
    }

    public void setAlmacenCentral(AlmacenCentral almacenCentral) {
        this.almacenCentral = almacenCentral;
    }

    public Simulador() {
    }

    private void init() {   // Método para inicializar el sistema

        try {

            cartera = new Cartera(100);
            System.out.print("¿Como se va a llamar tu empresa?: ");
            String dato = scanner.nextLine();
            arrPisRio.add(new PiscifactoriaRio(dato));
            arrPisRio.get(0).addTank(25);
            dia = 0;
            nombreEmpresa = dato;

        } catch (Exception e) {

            System.out.println("Algo ha fallado :(");

        }

    }

    public void menu() {    // Menú principal

        try {

            int opcion;

            do {
                System.out.println("---- Menú ----");
                System.out.println("1. Estado general");
                System.out.println("2. Estado piscifactoría");
                System.out.println("3. Estado tanques");
                System.out.println("4. Informes");
                System.out.println("5. Ictiopedia");
                System.out.println("6. Pasar día");
                System.out.println("7. Comprar comida");
                System.out.println("8. Comprar peces");
                System.out.println("9. Vender peces");
                System.out.println("10. Limpiar tanques");
                System.out.println("11. Vaciar tanque");
                System.out.println("12. Mejorar");
                System.out.println("13. Pasar varios días");
                System.out.println("14. Salir");
                System.out.print("Elige una opción: ");

                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la Opción 1");
                        showGeneralStatus();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2");
                        menuPisc();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la Opción 3");
                        showTankStatus();
                        break;
                    case 4:
                        System.out.println("Has seleccionado la Opción 4");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la Opción 5");
                        showIctio();
                        break;
                    case 6:
                        nextDay();
                        break;
                    case 7:
                        addFood();
                        break;
                    case 8:
                        System.out.println("Has seleccionado la Opción 8");
                        //TODO PREGUNTAR PISCIFACTORIA
                        addFishRio();
                        break;
                    case 9:
                        System.out.println("Has seleccionado la Opción 9");
                        sell();
                        break;
                    case 10:
                        System.out.println("Has seleccionado la Opción 10");
                        cleanTank();
                        break;
                    case 11:
                        System.out.println("Has seleccionado la Opción 11");
                        emptyTank();
                        break;
                    case 12:
                        upgrade();
                        break;
                    case 13:
                        System.out.println("Has seleccionado la Opción 13");
                        break;
                    case 14:
                        System.out.println("Saliendo del programa...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción del 1 al 14.");
                        break;
                }
            } while (opcion >= 1 && opcion <= 14);

        } finally {

            try {

                scanner.close();

            } catch (Exception e) {

                System.out.println("Algo ha fallado :(");

            }

        }

    }

    public void menuPisc() {

        ArrayList<Piscifactoria> piscinas = new ArrayList<>();

        if (!arrPisRio.isEmpty()) {
            piscinas.addAll(arrPisRio);
        } else if (!arrPisMar.isEmpty()) {
            piscinas.addAll(arrPisMar);
        }

        System.out.println("Seleccione una opción: ");
        System.out.println("--------------------------- Piscifactorías ---------------------------");



    }


    public void selectPisc() {

    }

    public void selectTank() {

    }

    public void showGeneralStatus() {

    }

    public void showSpecificStatus() {

    }

    public void showTankStatus() {

    }

    public void showStats() {

    }

    public void showIctio() {

    }

    public void nextDay() {
        for (PiscifactoriaRio pisci:arrPisRio) {
            pisci.nextDay(this);

        }
        for (PiscifactoriaMar pisci:arrPisMar) {
            pisci.nextDay(this);

        }
        //TODO MOSTRAR DATOS PECES VENDIDOS
    }

    public void addFood() {

    }

    public void addFishRio(PiscifactoriaRio piscifactoria) {
        try {
            piscifactoria.addFish();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void addFishMar(PiscifactoriaMar piscifactoria) {
        try {
            piscifactoria.addFish();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void sell() {

    }

    public void cleanTank() {

    }

    public void emptyTank() {

    }

    public void upgrade() {

    }

    public void contador() {

        int segundos = 5;

        for (int i = segundos; i > 0; i--) {
            System.out.println("Regresando al menú en: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    Simulador simulador = new Simulador();
    simulador.init();
    simulador.menu();

    }
}