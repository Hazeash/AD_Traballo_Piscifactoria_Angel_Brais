package peces.rio;

import logica.IPezRio;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class Pejerrey extends Canivoros implements IPezRio {

    private final PecesDatos datos = AlmacenPropiedades.PEJERREY;

    public Pejerrey(char sexo) {
        super(AlmacenPropiedades.PEJERREY, sexo);
    }
}