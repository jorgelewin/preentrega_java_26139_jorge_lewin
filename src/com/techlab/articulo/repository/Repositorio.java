package com.techlab.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import com.techlab.articulo.interfaces.Identificable;

public class Repositorio<T extends Identificable> {

    private final ArrayList<T> lista = new ArrayList<>();

    public boolean agregar(T objeto) {
        if (objeto == null) {
            return false;
        }

        return lista.add(objeto);
    }

    public List<T> listar() {
        return new ArrayList<>(lista);
    }

    public T buscarPorCodigo(int codigo) {
        for (T objeto : lista) {
            if (objeto.getCodigo() == codigo) {
                return objeto;
            }
        }

        return null;
    }

    public boolean eliminar(T objeto) {
        return objeto != null && lista.remove(objeto);
    }

    public boolean eliminarPorCodigo(int codigo) {
        T objeto = buscarPorCodigo(codigo);
        return objeto != null && lista.remove(objeto);
    }

    public boolean estaVacio() {
        return lista.isEmpty();
    }
}
