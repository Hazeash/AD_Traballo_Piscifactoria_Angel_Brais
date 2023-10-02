package peces;

import logica.IPezMar;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class Besugo extends Canivoros implements IPezMar  {

    private final PecesDatos datos = AlmacenPropiedades.BESUGO;

    public Besugo(char sexo) {
        super(AlmacenPropiedades.BESUGO, sexo);
    }
}