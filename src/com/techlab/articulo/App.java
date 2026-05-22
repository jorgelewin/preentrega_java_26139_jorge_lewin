package com.techlab.articulo;

import java.util.Scanner;

import com.techlab.articulo.menu.Menu;
import com.techlab.articulo.menu.MenuArticulos;
import com.techlab.articulo.menu.MenuCategorias;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Repositorio<Articulo> repositorioArticulos = new Repositorio<>();
        Repositorio<Categoria> repositorioCategorias = new Repositorio<>();

        Menu menuArticulos = new MenuArticulos(scanner, repositorioArticulos, repositorioCategorias);
        Menu menuCategorias = new MenuCategorias(scanner, repositorioCategorias, repositorioArticulos);

        int opcion;

        try {
            do {
                System.out.println("\n=== SISTEMA DE GESTIÓN DE ARTÍCULOS ===");
                System.out.println("1 - Menú de artículos");
                System.out.println("2 - Menú de categorías");
                System.out.println("0 - Salir");
                System.out.print("Seleccione una opción: ");

                String entrada = scanner.nextLine().trim();

                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException ex) {
                    opcion = -1;
                }

                switch (opcion) {
                    case 1:
                        menuArticulos.ejecutar();
                        break;
                    case 2:
                        menuCategorias.ejecutar();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 0);
        } finally {
            scanner.close();
        }
    }
}
