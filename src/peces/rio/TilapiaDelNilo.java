package peces.rio;

import logica.IPezRio;
import logica.propiedades.Filtrador;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class TilapiaDelNilo extends Filtrador implements IPezRio {
    private final PecesDatos datos = AlmacenPropiedades.TILAPIA_NILO;
    public TilapiaDelNilo(char sexo) {
        super(AlmacenPropiedades.TILAPIA_NILO, sexo);
    }
}
