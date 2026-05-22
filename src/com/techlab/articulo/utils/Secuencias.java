package com.techlab.articulo.utils;

public final class Secuencias {

    private static int codigoArticulo = 1;
    private static int codigoCategoria = 1;

    private Secuencias() {
    }

    public static synchronized int generarCodigoArticulo() {
        return codigoArticulo++;
    }

    public static synchronized int generarCodigoCategoria() {
        return codigoCategoria++;
    }
}
