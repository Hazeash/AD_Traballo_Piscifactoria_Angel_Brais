package simulacion;

public class Cartera {
    private int dinero;

    public Cartera(int dinero) {
        this.dinero = dinero;
    }

    public boolean comprar(int precio) {
        if (getDinero() >= precio) {
            setDinero(getDinero() - precio);
            return true;
        } else {
            System.out.println("No tiene suficiente dinero :c");
            return false;
        }
    }

    public boolean vender(int precio) {
        setDinero(getDinero() + precio);
        return true;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    @Override
    public String toString() {
        return "Cartera{" +
                "dinero=" + dinero +
                '}';
    }
}