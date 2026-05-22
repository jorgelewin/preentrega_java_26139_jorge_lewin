package com.techlab.articulo.menu;

import java.util.Scanner;

import com.techlab.articulo.utils.Validaciones;

public abstract class Menu {

    protected Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void mostrarMenu();

    public abstract void ejecutar();

    protected String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();

            if (Validaciones.validarTextoNoVacio(valor)) {
                return valor;
            }

            System.out.println("El valor no puede estar vacío. Intente nuevamente.");
        }
    }

    protected String leerTextoOpcional(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    protected int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();

            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException ex) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    protected int leerEnteroNoNegativo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (Validaciones.validarNoNegativo(valor)) {
                return valor;
            }
            System.out.println("El valor no puede ser negativo.");
        }
    }

    protected double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim().replace(',', '.');

            try {
                return Double.parseDouble(valor);
            } catch (NumberFormatException ex) {
                System.out.println("Debe ingresar un número decimal válido.");
            }
        }
    }

    protected double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            double valor = leerDouble(mensaje);
            if (Validaciones.validarNoNegativo(valor)) {
                return valor;
            }
            System.out.println("El valor no puede ser negativo.");
        }
    }

    protected boolean leerSiNo(String mensaje) {
        while (true) {
            System.out.print(mensaje + " [s/n]: ");
            String valor = scanner.nextLine().trim().toLowerCase();

            if ("s".equals(valor) || "si".equals(valor)) {
                return true;
            }

            if ("n".equals(valor) || "no".equals(valor)) {
                return false;
            }

            System.out.println("Respuesta inválida. Ingrese 's' o 'n'.");
        }
    }
}
