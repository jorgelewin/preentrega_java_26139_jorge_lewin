package com.techlab.articulo.model;

import com.techlab.articulo.interfaces.Calculable;
import com.techlab.articulo.interfaces.Identificable;

public abstract class Articulo implements Calculable, Identificable {

    protected int codigo;
    protected String nombre;
    protected double precio;
    protected Categoria categoria;

    protected Articulo(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public abstract String getTipoArticulo();

    @Override
    public String toString() {
        return "Articulo{" +
                "codigo=" + codigo +
                ", tipo='" + getTipoArticulo() + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precioBase=" + precio +
                ", precioFinal=" + calcularPrecioFinal() +
                ", categoria=" + (categoria != null ? categoria.getNombre() + " (" + categoria.getCodigo() + ")" : "sin categoría") +
                '}';
    }
}
