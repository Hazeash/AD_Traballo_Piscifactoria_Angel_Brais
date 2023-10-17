package peces.mar;

import logica.IPezMar;
import logica.propiedades.Canivoros;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class LubinaRayada extends Canivoros implements IPezMar  {

    private final PecesDatos datos = AlmacenPropiedades.LUBINA_RAYADA;

    public LubinaRayada(char sexo) {
        super(AlmacenPropiedades.LUBINA_RAYADA, sexo);
    }
}
