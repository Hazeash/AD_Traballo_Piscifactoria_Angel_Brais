package peces.rio;

import logica.IPezRio;
import logica.propiedades.Filtrador;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class CarpaPlateada extends Filtrador implements IPezRio {
    private final PecesDatos datos = AlmacenPropiedades.CARPA_PLATEADA;
    public CarpaPlateada(char sexo) {
        super(AlmacenPropiedades.CARPA_PLATEADA, sexo);
    }

}