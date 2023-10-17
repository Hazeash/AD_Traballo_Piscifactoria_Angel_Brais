package peces.ambos;

import logica.IPezMar;
import logica.IPezRio;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class TruchaArcoiris extends Canivoros implements IPezRio, IPezMar {

    private final PecesDatos datos = AlmacenPropiedades.TRUCHA_ARCOIRIS;

    public TruchaArcoiris(char sexo) {
        super(AlmacenPropiedades.TRUCHA_ARCOIRIS, sexo);
    }
}
