package peces.mar;

import logica.IPezMar;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class Robalo extends Canivoros implements IPezMar  {

    private final PecesDatos datos = AlmacenPropiedades.ROBALO;

    public Robalo(char sexo) {
        super(AlmacenPropiedades.ROBALO, sexo);
    }
}
