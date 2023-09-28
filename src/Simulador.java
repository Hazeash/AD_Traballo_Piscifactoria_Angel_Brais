import java.util.ArrayList;
import java.util.Scanner;

public class Simulador {

    private int dia;
    private ArrayList<Object> arrPisRio;
    private ArrayList<Object> arrPisMar;
    private String nombreEmpresa;
    private static Scanner scanner;

    public Simulador() {
    }

    private void init() {   // Método para inicializar el sistema

        scanner = new Scanner(System.in);

        try {

            System.out.print("¿Como se va a llamar tu empresa?: ");
            String dato = scanner.nextLine();

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
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la Opción 3");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la Opción 4");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la Opción 5");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la Opción 6");
                        break;
                    case 7:
                        System.out.println("Has seleccionado la Opción 7");
                        break;
                    case 8:
                        System.out.println("Has seleccionado la Opción 8");
                        break;
                    case 9:
                        System.out.println("Has seleccionado la Opción 9");
                        break;
                    case 10:
                        System.out.println("Has seleccionado la Opción 10");
                        break;
                    case 11:
                        System.out.println("Has seleccionado la Opción 11");
                        break;
                    case 12:
                        System.out.println("Has seleccionado la Opción 12");
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

    public static void main(String[] args) {

    Simulador simulador = new Simulador();
    simulador.init();
    simulador.menu();

    }

}