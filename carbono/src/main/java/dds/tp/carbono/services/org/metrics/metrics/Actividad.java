package dds.tp.carbono.services.org.metrics.metrics;

import dds.tp.carbono.utils.FormatString;

public enum Actividad {
    CombustionFija,
    CombustionMovil,
    ElectricidadAdquiridaConsumida,
    LogisticaDeProductosServicios;

    public static Actividad getBy(String nombre) throws Exception {

        switch (formatNombre(nombre)) {
            case "combustionfija" : return CombustionFija;
            case "combustionmovil" : return CombustionMovil;
            case "electricidadadquiridaconsumida" : return ElectricidadAdquiridaConsumida;
            case "logisticadeproductosservicios" : return LogisticaDeProductosServicios; 
                
            default: throw new Exception("Actividad no existente");
        }
   }

    private static String formatNombre(String nombre) {
        nombre = FormatString.toLower(nombre);
        nombre = FormatString.sinEspacios(nombre);
        nombre = FormatString.sinTildes(nombre);

        return nombre;
    }
}

