package peces.ambos;

import logica.IPezRio;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class SalmonAtlantico extends Canivoros implements IPezRio {

    private final PecesDatos datos = AlmacenPropiedades.SALMON_ATLANTICO;

    public SalmonAtlantico(char sexo) {
        super(AlmacenPropiedades.SALMON_ATLANTICO, sexo);
    }
}