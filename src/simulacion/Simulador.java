package simulacion;

import estadisticas.Estadisticas;
import logica.*;
import peces.ambos.SalmonAtlantico;
import peces.ambos.TruchaArcoiris;
import peces.mar.*;
import peces.rio.*;
import propiedades.AlmacenPropiedades;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Simulador {

    /** Prueba de Javadoc */
    private int dia;
    private ArrayList<Piscifactoria> piscifactorias = new ArrayList<>();
    private String nombreEmpresa;
    private final Scanner scanner = new Scanner(System.in);
    private Cartera cartera;
    private String[] peixes = {AlmacenPropiedades.SALMON_ATLANTICO.getNombre(),AlmacenPropiedades.TRUCHA_ARCOIRIS.getNombre(),AlmacenPropiedades.BESUGO.getNombre(),AlmacenPropiedades.CABALLA.getNombre(),AlmacenPropiedades.LENGUADO_EUROPEO.getNombre(),AlmacenPropiedades.LUBINA_RAYADA.getNombre(),AlmacenPropiedades.ROBALO.getNombre(),AlmacenPropiedades.CARPA_PLATEADA.getNombre(),AlmacenPropiedades.LUCIO_NORTE.getNombre(),AlmacenPropiedades.PEJERREY.getNombre(),AlmacenPropiedades.SALMON_CHINOOK.getNombre(),AlmacenPropiedades.TILAPIA_NILO.getNombre()};
    public Estadisticas estadisticas = new Estadisticas(peixes);

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
            piscifactorias.add(new PiscifactoriaRio(dato));
            dia = 0;
            nombreEmpresa = dato;

        } catch (Exception e) {

            System.out.println("Algo ha fallado :(");

        }

    }

    public void menu() {    // Menú principal
        try {
            int opcion;
            boolean control = true;
            do {
                System.out.println("---- Menú ----");
                System.out.println(cartera.toString());
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

                opcion = obtenerEntero();

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
                        comprarPeces();
                        break;
                    case 9:
                        System.out.println("Has seleccionado la Opción 9");
                        sell();
                        break;
                    case 10:
                        System.out.println("Has seleccionado la Opción 10");
                        cleanTank(selectTank());
                        break;
                    case 11:
                        System.out.println("Has seleccionado la Opción 11");
                        emptyTank(selectTank());
                        break;
                    case 12:
                        upgrade();
                        break;
                    case 13:
                        System.out.println("Has seleccionado la Opción 13");
                        break;
                    case 14:
                        System.out.println("Saliendo del programa...");
                        control = false;
                        break;
                    case 98:

                        break;
                    case 99:
                        cartera.vender(1000);
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción del 1 al 14.");
                        break;
                }
            } while (control);
        } finally {
            try {
                scanner.close();
            } catch (Exception e) {
                System.err.println("Algo ha fallado :(");
            }
        }
    }

    public int obtenerEntero() {

        boolean control = true;
        int opcion = -1;

        do {
            try {

                System.out.print("Elige una opción: ");
                opcion = scanner.nextInt();
                control = false;
                return opcion;

            } catch (InputMismatchException e) {
                System.out.println("No has seleccionado una opción válida :(");
                scanner.next();
            }
        } while (control);
        return opcion;
    }

    public void menuPisc() {
        System.out.println("--------------------------- Piscifactorías ---------------------------");
        System.out.println("[Peces vivos / Peces totales / Espacio total]");
        for (int i = 0; i < piscifactorias.size(); i++) {
            System.out.println(i + 1 + ".- " + piscifactorias.get(i).getNombre() + "/" + piscifactorias.get(i).contarVivos() + "/" + piscifactorias.get(i).contarTotales() + "/" + piscifactorias.get(i).contarMaximos());
        }
        System.out.println("Selecciona una piscifactoria: ");
    }
    public void menuTanque(Piscifactoria pisc){
        List<Tanque> tanques = pisc.getTanques();
        System.out.println("--------------------------- Tanques ---------------------------");
        for (Tanque tanque : tanques) {
            if (tanque.getPeces().isEmpty()){
                System.out.println(tanque.getNumeroTanque() + ".-" + "Vacio");
            }else {
                System.out.println(tanque.getNumeroTanque() + ".-" + tanque.getPeces().get(0).pecesDatos.getNombre());
            }
        }
        System.out.println("Selecciona una Tanque: ");
    }
    public Piscifactoria selectPisc() {
        menuPisc();
        Piscifactoria pisc = null;
        boolean control = true;
        do {
            int eleccion = obtenerEntero();

            if (eleccion <= piscifactorias.size() && eleccion >= 0) {
                control = false;
                if (eleccion != 0) {
                    pisc = piscifactorias.get(eleccion - 1);
                }
            } else {
                System.out.println("No has seleccionado una opción válida :(");
            }
        } while (control);
        return pisc;
    }

    public Tanque selectTank() {
        Piscifactoria pisc = selectPisc();
        menuTanque(pisc);
        Tanque tanque = null;
        boolean control = true;

        do {
            int eleccion = obtenerEntero();
            if (eleccion <= pisc.getTanques().size() && eleccion >= 0) {
                control = false;
                if (eleccion != 0) {
                    tanque = pisc.getTanques().get(eleccion - 1);
                }
            } else {
                System.out.println("No has seleccionado una opción válida :(");
            }
        } while (control);
        return tanque;
    }

    public void showGeneralStatus() {
        for (Piscifactoria pisc:piscifactorias) {
            pisc.showStatus();
        }
        System.out.println("-----DIA : " + dia + " -----");
        System.out.println("-----MONEDAS : " + cartera.getDinero() + " -----");
        if (almacenCentral != null){
            almacenCentral.showDatos();
        }
        System.out.println("------------------------");
    }

    public void showSpecificStatus() {
        Piscifactoria pisc = selectPisc();
        pisc.showStatus();
    }

    public void showTankStatus() {
        Tanque tanque = selectTank();
        tanque.showStatus();
    }

    public void showStats() {
        //TODO ESTO DEVOLVEO ORCA.ESTADISTICAS ASI QUE HAI QUE IMPLEMENTAR TODOS OS METODOS DESO ANTES DESTE

    }

    public void showIctio() {
        //TODO ESTO TEN QUE ELGIR UNHA CLASE DE PEIXE E MOSTRAR OS DATOS DESTA CLASE , DAS QUE IMPLEMENTEMOS
    }

    public void nextDay() {
        for (Piscifactoria pisci:piscifactorias) {
            pisci.nextDay(this);

        }
        //TODO MOSTRAR DATOS PECES VENDIDOS
    }

    public void addFood() {

        Piscifactoria piscifactoria = selectPisc();
        boolean control = true;

        if (piscifactoria.getComidaActual() == piscifactoria.getComidaMax()) {

            System.out.println("Ya has alcanzado la cantidad máxima de comida.");

        } else {

            do {

                System.out.println("¿Cuanta comida quieres comprar?: ");
                System.out.println(cartera.toString());
                System.out.println("1.- Comprar 5: ");
                System.out.println("2.- Comprar 10: ");
                System.out.println("3.- Comprar 25: ");
                System.out.println("4.- Llenar almacen: ");
                System.out.println("0.- Salir: ");

                int eleccion = obtenerEntero();

                switch (eleccion) {

                    case 0:
                        control = false;
                        break;
                    case 1:
                        piscifactoria.comprarCinco(cartera);
                        break;
                    case 2:
                        piscifactoria.comprarDiez(cartera);
                        break;
                    case 3:
                        piscifactoria.comprarVeintiCinco(cartera);
                        break;
                    case 4:
                        piscifactoria.comprarEnchido(cartera);
                        break;
                }

            } while (control);
        }
    }

    public void addFish(Pez pez ,Piscifactoria piscifactoria){
        if(piscifactoria instanceof PiscifactoriaRio){
            boolean resultado =((PiscifactoriaRio) piscifactoria).addFish(pez);
            if (resultado){
                estadisticas.registrarNacimiento(pez.pecesDatos.getNombre());
                System.out.println("Añadido correctamente");
                System.out.println("---------------------------------------");
            }else {
                System.out.println("No se a añadido porque no hay espacio");
                System.out.println("---------------------------------------");
            }
        }else {
            boolean resultado = ((PiscifactoriaMar) piscifactoria).addFish(pez);
            if (resultado){
                estadisticas.registrarNacimiento(pez.pecesDatos.getNombre());
                System.out.println("Añadido correctamente");
                System.out.println("---------------------------------------");
            }else {
                System.out.println("No se a añadido porque no hay espacio");
                System.out.println("---------------------------------------");
            }
        }
    }

    public void sell() {
        int vendidos = 0;
        int monedas = 0;
        for (Piscifactoria prio : piscifactorias) {
            int[] datos =prio.sellFish(estadisticas);
            vendidos += datos[0];
            monedas += datos[1];
        }
        System.out.println(vendidos +" peces vendidos por un total de "+ monedas+" monedas");
        cartera.vender(monedas);
    }

    public void cleanTank(Tanque tanque) {
        tanque.cleanTank();
    }

    public void emptyTank(Tanque tanque) {
        tanque.emptyTank();
    }

    public void upgrade() {

        boolean control = true;

        do {

            System.out.println("¿Qué quieres mejorar?: ");
            System.out.println("1.- Comprar edificios: ");
            System.out.println("2.- Mejorar edificios: ");
            System.out.println("3.- Cancelar: ");

            int eleccion = obtenerEntero();

            switch (eleccion) {

                case 1:
                    boolean control_1 = true;
                    do {
                        System.out.println("1.- Piscifactoria: ");
                        System.out.println("2.- Almacén central: ");
                        System.out.println("3.- Volver: ");

                        int eleccion_1 = obtenerEntero();

                        switch (eleccion_1) {
                            case 1:
                                boolean control_2 = true;
                                do {
                                    System.out.println(cartera.toString());
                                    System.out.println("1.- Piscifactoria de río: ");
                                    System.out.println("2.- Piscifactoria de mar: ");
                                    System.out.println("3.- Volver: ");

                                    int rio = 0;
                                    int mar = 0;

                                    for (Piscifactoria pisc: piscifactorias) {
                                        if (pisc instanceof PiscifactoriaRio) {
                                            rio++;
                                        } else {
                                            mar++;
                                        }
                                    }

                                    int eleccion_2 = obtenerEntero();

                                    switch (eleccion_2) {
                                        case 1:
                                            if (cartera.comprar(500 * rio)) {
                                                System.out.print("Como quieres llamar a la piscifactoría: ");
                                                String nombre = scanner.next();
                                                piscifactorias.add(new PiscifactoriaRio(nombre));
                                            }
                                            break;
                                        case 2:
                                            if (cartera.comprar(500 * mar)) {
                                                System.out.print("Como quieres llamar a la piscifactoría: ");
                                                String nombre = scanner.next();
                                                piscifactorias.add(new PiscifactoriaMar(nombre));
                                            }
                                            break;
                                        case 3:
                                            control_2 = false;
                                            break;
                                        default:
                                            System.out.println("No has seleccionado una opción válida :(");
                                            break;
                                    }

                                } while (control_2);
                                break;
                            case 2:
                                if (almacenCentral == null) {
                                    if (cartera.comprar(2000)) {
                                        almacenCentral = new AlmacenCentral();
                                    }
                                } else {
                                    System.out.println("Ya tienes el almacén central :/");
                                }
                                break;
                            case 3:
                                control_1 = false;
                                break;
                        }
                    } while (control_1);
                    break;
                case 2:
                    boolean control_2 = true;
                    do {
                        System.out.println(cartera.toString());
                        System.out.println("1.- Piscifactoria: ");
                        System.out.println("2.- Almacén central (Necesitaras 100 monedas): ");
                        System.out.println("3.- Volver: ");

                        eleccion = obtenerEntero();

                        switch (eleccion) {

                            case 1:
                                boolean control_4 = true;
                                do {
                                    System.out.println(cartera.toString());
                                    System.out.println("1.- Comprar tanque: ");
                                    System.out.println("2.- Aumentar almacén de comida: ");
                                    System.out.println("3.- Volver: ");

                                    int rio = 0;
                                    int mar = 0;

                                    for (Piscifactoria pisc: piscifactorias) {
                                        if (pisc instanceof PiscifactoriaRio) {
                                            rio++;
                                        } else {
                                            mar++;
                                        }
                                    }

                                    int eleccion_2 = obtenerEntero();

                                    switch (eleccion_2) {
                                        case 1:
                                            Piscifactoria piscifactoria = selectPisc();
                                            if (piscifactoria.getTanques().size() < 10) {
                                                if (piscifactoria instanceof PiscifactoriaRio) {
                                                    if (piscifactoria.getTanques().isEmpty()) {
                                                        if (cartera.comprar(150)) {
                                                            piscifactoria.addTank();
                                                        }
                                                    }else {
                                                        if (cartera.comprar(piscifactoria.getTanques().size() * 150)) {
                                                            piscifactoria.addTank();
                                                        }
                                                    }
                                                } else {
                                                    if (piscifactoria.getTanques().isEmpty()) {
                                                        if (cartera.comprar(600)) {
                                                            piscifactoria.addTank();
                                                        }
                                                    }else {
                                                        if (cartera.comprar(piscifactoria.getTanques().size() * 600)) {
                                                            piscifactoria.addTank();
                                                        }
                                                    }
                                                }
                                            } else {
                                                System.out.println("Ya tienes la máxima capacidad :c");
                                            }
                                            break;
                                        case 2:
                                            break;
                                        case 3:
                                            control_4 = false;
                                            break;
                                    }
                                } while (control_4);
                                break;
                            case 2:
                                if (almacenCentral != null) {
                                    if (cartera.getDinero() >= 100) {
                                        almacenCentral.setCapacidad(almacenCentral.getCapacidad() + 50);
                                        cartera.setDinero(cartera.getDinero() - 100);

                                    } else {
                                        System.out.println("No tiene suficiente dinero :c");
                                    }
                                } else {
                                    System.out.println("Primero tienes que comprar el almacen central :(");
                                }
                                break;
                            case 3:
                                control_2 = false;
                                break;
                        }
                    } while (control_2);
                    break;
                case 3:
                    control = false;
                    break;
            }

        } while (control);
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
    public void comprarPeces(){
        Piscifactoria pisc = selectPisc();
        if (pisc instanceof  PiscifactoriaRio){
            boolean control = true;
            do{
                System.out.println("1.Carpa Plateada");
                System.out.println("2.Lucio del Norte");
                System.out.println("3.Pejerrey");
                System.out.println("4.Salmon Chinook");
                System.out.println("5.Tilapia Del Nilo");
                System.out.println("6.Salmon Atlantico");
                System.out.println("7.Trucha Arcoiris");
                System.out.println("8.Cancelar");
                int seleccion = obtenerEntero();
                switch (seleccion) {
                    case 1:
                        if(cartera.comprar(AlmacenPropiedades.CARPA_PLATEADA.getCoste())){
                            addFish(new CarpaPlateada('H'),pisc);
                        }
                        break;
                    case 2:
                        if (cartera.comprar(AlmacenPropiedades.LUCIO_NORTE.getCoste())){
                            addFish(new LucioDelNorte('H'),pisc);
                        }
                        break;
                    case 3:
                        if (cartera.comprar(AlmacenPropiedades.PEJERREY.getCoste())){
                            addFish(new Pejerrey('H'),pisc);
                        }
                        break;
                    case 4:
                        if (cartera.comprar(AlmacenPropiedades.SALMON_CHINOOK.getCoste())){
                            addFish(new SalmonChinook('H'),pisc);
                        }
                        break;
                    case 5:
                        if (cartera.comprar(AlmacenPropiedades.TILAPIA_NILO.getCoste())){
                            addFish(new TilapiaDelNilo('H'),pisc);
                        }
                        break;
                    case 6:
                        if (cartera.comprar(AlmacenPropiedades.SALMON_ATLANTICO.getCoste())){
                            addFish(new SalmonAtlantico('H'),pisc);
                        }
                        break;
                    case 7:
                        if (cartera.comprar(AlmacenPropiedades.TRUCHA_ARCOIRIS.getCoste())){
                            addFish(new TruchaArcoiris('H'),pisc);
                        }
                        break;
                    case 8:
                        control = false;
                        break;
                    default:
                        System.out.println("No has elegido una opción válida");
                        break;
                }
            }while(control);
        }else{
            boolean control = true;
            do{
                System.out.println("1.Besugo");
                System.out.println("2.Caballa");
                System.out.println("3.Lenguado Europeo");
                System.out.println("4.Lubina Rayada");
                System.out.println("5.Robalo");
                System.out.println("6.Salmon Atlantico");
                System.out.println("7.Trucha Arcoiris");
                System.out.println("8.Cancelar");
                int seleccion = obtenerEntero();
                switch (seleccion) {
                    case 1:
                        if (cartera.comprar(AlmacenPropiedades.BESUGO.getCoste())){
                            addFish(new Besugo('H'),pisc);
                        }
                        break;
                    case 2:
                        if (cartera.comprar(AlmacenPropiedades.CABALLA.getCoste())){
                            addFish(new Caballa('H'),pisc);
                        }
                        break;
                    case 3:
                        if (cartera.comprar(AlmacenPropiedades.LENGUADO_EUROPEO.getCoste())){
                            addFish(new LenguadoEuropeo('H'),pisc);
                        }
                        break;
                    case 4:
                        if (cartera.comprar(AlmacenPropiedades.LUBINA_RAYADA.getCoste())){
                            addFish(new LubinaRayada('H'),pisc);
                        }
                        break;
                    case 5:
                        if (cartera.comprar(AlmacenPropiedades.ROBALO.getCoste())){
                            addFish(new Robalo('H'),pisc);
                        }
                        break;
                    case 6:
                        if (cartera.comprar(AlmacenPropiedades.SALMON_ATLANTICO.getCoste())){
                            addFish(new SalmonAtlantico('H'),pisc);
                        }
                        break;
                    case 7:
                        if(cartera.comprar(AlmacenPropiedades.TRUCHA_ARCOIRIS.getCoste())){
                            addFish(new TruchaArcoiris('H'),pisc);
                        }
                        break;
                    case 8:
                        control = false;
                        break;
                    default:
                        System.out.println("No has elegido una opción válida");
                        break;
                }
            }while(control);
        }
    }

    public static void main(String[] args) {

    Simulador simulador = new Simulador();
    simulador.init();
    simulador.piscifactorias.get(0).setComidaMax(100);
    simulador.menu();

    }
}