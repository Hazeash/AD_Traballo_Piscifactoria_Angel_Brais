package peces.mar;

import logica.IPezMar;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class LenguadoEuropeo extends Canivoros implements IPezMar  {

    private final PecesDatos datos = AlmacenPropiedades.LENGUADO_EUROPEO;

    public LenguadoEuropeo(char sexo) {
        super(AlmacenPropiedades.LENGUADO_EUROPEO, sexo);
    }
}
