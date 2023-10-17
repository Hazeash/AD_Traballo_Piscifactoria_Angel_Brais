package peces.rio;

import logica.IPezRio;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class LucioDelNorte extends Canivoros implements IPezRio {

    private final PecesDatos datos = AlmacenPropiedades.LUCIO_NORTE;

    public LucioDelNorte(char sexo) {
        super(AlmacenPropiedades.LUCIO_NORTE, sexo);
    }
}
