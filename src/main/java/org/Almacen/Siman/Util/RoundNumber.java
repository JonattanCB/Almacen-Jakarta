package org.Almacen.Siman.Util;

public class RoundNumber {
    public static double redondear(double valor, int decimales) {
        double factor = Math.pow(10, decimales);
        return Math.round(valor * factor) / factor;
    }
}
