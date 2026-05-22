package com.techlab.articulo.menu;

import java.util.List;
import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.utils.Secuencias;

public class MenuCategorias extends Menu {

    private final Repositorio<Categoria> repositorioCategorias;
    private final Repositorio<Articulo> repositorioArticulos;

    public MenuCategorias(Scanner scanner) {
        this(scanner, new Repositorio<>(), new Repositorio<>());
    }

    public MenuCategorias(Scanner scanner, Repositorio<Categoria> repositorioCategorias, Repositorio<Articulo> repositorioArticulos) {
        super(scanner);
        this.repositorioCategorias = repositorioCategorias;
        this.repositorioArticulos = repositorioArticulos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ CATEGORÍAS ---");
        System.out.println("1 - Ingresar categoría");
        System.out.println("2 - Listar categorías");
        System.out.println("3 - Consultar categoría");
        System.out.println("4 - Modificar categoría");
        System.out.println("5 - Eliminar categoría");
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
                    ingresarCategoria();
                    break;
                case 2:
                    listarCategorias();
                    break;
                case 3:
                    consultarCategoria();
                    break;
                case 4:
                    modificarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void ingresarCategoria() {
        String nombre = pedirNombreCategoria(null);
        String descripcion = pedirDescripcionCategoria();
        int codigo = Secuencias.generarCodigoCategoria();

        Categoria categoria = new Categoria(codigo, nombre, descripcion);
        repositorioCategorias.agregar(categoria);

        System.out.println("Categoría registrada correctamente:");
        System.out.println(categoria);
    }

    private void listarCategorias() {
        List<Categoria> categorias = repositorioCategorias.listar();

        if (categorias.isEmpty()) {
            System.out.println("No hay categorías cargadas.");
            return;
        }

        System.out.println("\n--- LISTADO DE CATEGORÍAS ---");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    private void consultarCategoria() {

        Categoria categoria = pedirCategoriaExistente("Ingrese el código de la categoría: ");

        if (categoria != null) {

            System.out.println("\n--- CATEGORÍA ENCONTRADA ---");
            System.out.println(categoria);
        }

    }

    private void modificarCategoria() {

        Categoria categoria = pedirCategoriaExistente("Ingrese el código de la categoría a modificar: ");

        if (categoria == null) {
            return;
        }

        System.out.println("Categoría actual:");
        System.out.println(categoria);

        String nombre = pedirNombreCategoria(categoria.getCodigo());
        String descripcion = pedirDescripcionCategoria();

        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        System.out.println("Categoría modificada correctamente:");
        System.out.println(categoria);
    }

    private void eliminarCategoria() {

        Categoria categoria = pedirCategoriaExistente("Ingrese el código de la categoría a eliminar: ");

        if (categoria == null) {
            return;
        }

        if (categoriaTieneArticulosAsociados(categoria)) {
            System.out.println("No se puede eliminar la categoría porque tiene artículos asociados.");
            return;
        }

        if (leerSiNo("¿Desea eliminar la categoría '" + categoria.getNombre() + "'?") ) {
            repositorioCategorias.eliminar(categoria);
            System.out.println("Categoría eliminada correctamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private boolean categoriaTieneArticulosAsociados(Categoria categoria) {
        for (Articulo articulo : repositorioArticulos.listar()) {
            if (articulo.getCategoria() != null && articulo.getCategoria().getCodigo() == categoria.getCodigo()) {
                return true;
            }
        }

        return false;
    }

    private String pedirNombreCategoria(Integer codigoCategoriaActual) {
        while (true) {
            String nombre = leerTextoNoVacio("Ingrese el nombre de la categoría: ");

            if (!categoriaExistePorNombre(nombre, codigoCategoriaActual)) {
                return nombre;
            }

            System.out.println("Ya existe una categoría con ese nombre.");
        }
    }

    private String pedirDescripcionCategoria() {
        return leerTextoNoVacio("Ingrese la descripción de la categoría: ");
    }

    private Categoria pedirCategoriaExistente(String mensaje){
        int codigo = leerEntero(mensaje);
        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("No se encontró una categoría con ese código.");
        }
        return categoria;
    }


    private boolean categoriaExistePorNombre(String nombre, Integer codigoCategoriaActual) {
        String nombreNormalizado = normalizar(nombre);

        for (Categoria categoria : repositorioCategorias.listar()) {
            if (codigoCategoriaActual != null && categoria.getCodigo() == codigoCategoriaActual) {
                continue;
            }

            if (normalizar(categoria.getNombre()).equals(nombreNormalizado)) {
                return true;
            }
        }

        return false;
    }

    private String normalizar(String texto) {
        return texto == null ? "" : texto.trim().toLowerCase();
    }
}
