package peces.rio;

import logica.IPezRio;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class SalmonChinook extends Canivoros implements IPezRio {

    private final PecesDatos datos = AlmacenPropiedades.SALMON_CHINOOK;

    public SalmonChinook(char sexo) {
        super(AlmacenPropiedades.SALMON_CHINOOK, sexo);
    }
}