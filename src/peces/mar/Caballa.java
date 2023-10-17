package peces.mar;

import logica.IPezMar;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class Caballa extends Canivoros implements IPezMar  {

    private final PecesDatos datos = AlmacenPropiedades.CABALLA;

    public Caballa(char sexo) {
        super(AlmacenPropiedades.CABALLA, sexo);
    }
}
