package com.techlab.articulo.utils;

public final class Validaciones {

    private Validaciones() {
    }

    public static boolean validarTextoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarLongitudMaxima(String texto, int maximo) {
        return texto != null && maximo >= 0 && texto.trim().length() <= maximo;
    }

    public static boolean validarNoNegativo(int valor) {
        return valor >= 0;
    }

    public static boolean validarNoNegativo(double valor) {
        return valor >= 0.0d;
    }
}
