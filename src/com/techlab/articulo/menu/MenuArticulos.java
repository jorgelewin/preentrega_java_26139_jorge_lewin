package com.techlab.articulo.menu;

import java.util.List;
import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.utils.Secuencias;

public class MenuArticulos extends Menu {

    private final Repositorio<Articulo> repositorioArticulos;
    private final Repositorio<Categoria> repositorioCategorias;

    public MenuArticulos(Scanner scanner) {
        this(scanner, new Repositorio<>(), new Repositorio<>());
    }

    public MenuArticulos(Scanner scanner, Repositorio<Articulo> repositorioArticulos, Repositorio<Categoria> repositorioCategorias) {
        super(scanner);
        this.repositorioArticulos = repositorioArticulos;
        this.repositorioCategorias = repositorioCategorias;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ ARTÍCULOS ---");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar artículo");
        System.out.println("4 - Modificar artículo");
        System.out.println("5 - Eliminar artículo");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo();
                    break;
                case 2:
                    listarArticulos();
                    break;
                case 3:
                    consultarArticulo();
                    break;
                case 4:
                    modificarArticulo();
                    break;
                case 5:
                    eliminarArticulo();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void ingresarArticulo() {
        if (repositorioCategorias.estaVacio()) {
            System.out.println("No se pueden crear artículos sin categorías cargadas.");
            return;
        }

        int tipo = pedirTipoArticulo();
        String nombre = pedirNombreArticulo();
        double precio = pedirPrecioArticulo();
        Categoria categoria = pedirCategoriaExistente();
        int codigo = Secuencias.generarCodigoArticulo();

        Articulo articulo;

        if (tipo == 1) {
            int garantiaMeses = pedirGarantia();
            articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
        } else {
            int diasParaVencimiento = pedirDiasParaVencimiento();
            articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasParaVencimiento);
        }

        repositorioArticulos.agregar(articulo);
        System.out.println("Artículo registrado correctamente:");
        System.out.println(articulo);
    }

    private void listarArticulos() {
        List<Articulo> articulos = repositorioArticulos.listar();

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }
    }

    private void consultarArticulo() {

        Articulo articulo = pedirArticuloExistente("Ingrese el código del artículo: ");

        if (articulo != null) {

            System.out.println("\n--- ARTÍCULO ENCONTRADO ---");
            System.out.println(articulo);
        }

    }

    private void modificarArticulo() {

        Articulo articulo = pedirArticuloExistente("Ingrese el código del artículo a modificar: ");

        if (articulo == null) {
            return;
        }

        System.out.println("Artículo actual:");
        System.out.println(articulo);

        String nombre = pedirNombreArticulo();
        double precio = pedirPrecioArticulo();
        Categoria categoria = pedirCategoriaExistente();

        articulo.setNombre(nombre);
        articulo.setPrecio(precio);
        articulo.setCategoria(categoria);

        if (articulo instanceof ArticuloElectronico) {
            int garantiaMeses = pedirGarantia();
            ((ArticuloElectronico) articulo).setGarantiaMeses(garantiaMeses);
        } else if (articulo instanceof ArticuloAlimenticio) {
            int diasParaVencimiento = pedirDiasParaVencimiento();
            ((ArticuloAlimenticio) articulo).setDiasParaVencimiento(diasParaVencimiento);
        }

        System.out.println("Artículo modificado correctamente:");
        System.out.println(articulo);
    }

    private void eliminarArticulo() {

        Articulo articulo = pedirArticuloExistente("Ingrese el código del artículo a eliminar: ");

        if (articulo == null) {
            return;
        }

        if (leerSiNo("¿Desea eliminar el artículo '" + articulo.getNombre() + "'?") ) {
            repositorioArticulos.eliminar(articulo);
            System.out.println("Artículo eliminado correctamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private Categoria pedirCategoriaExistente() {
        while (true) {
            int codigoCategoria = leerEntero("Ingrese el código de la categoría: ");
            Categoria categoria = repositorioCategorias.buscarPorCodigo(codigoCategoria);

            if (categoria != null) {
                return categoria;
            }

            System.out.println("No existe una categoría con ese código.");
        }
    }

    private Articulo pedirArticuloExistente(String mensaje) {
        int codigo = leerEntero(mensaje);
        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("No se encontró un artículo con ese código.");
        }

        return articulo;
    }

    private String pedirNombreArticulo() {
        return leerTextoNoVacio("Ingrese el nombre del artículo: ");
    }

    private double pedirPrecioArticulo() {
        return leerDoubleNoNegativo("Ingrese el precio del artículo: ");
    }

    private int pedirGarantia() {
        return leerEnteroNoNegativo("Ingrese la garantía en meses: ");
    }

    private int pedirDiasParaVencimiento() {
        return leerEnteroNoNegativo("Ingrese los días para vencimiento: ");
    }

    private int pedirTipoArticulo() {
        while (true) {
            System.out.println("\nTipo de artículo:");
            System.out.println("1 - Electrónico");
            System.out.println("2 - Alimenticio");

            int opcion = leerEntero("Seleccione el tipo: ");

            if (opcion == 1 || opcion == 2) {
                return opcion;
            }

            System.out.println("Debe seleccionar 1 o 2.");
        }
    }
}