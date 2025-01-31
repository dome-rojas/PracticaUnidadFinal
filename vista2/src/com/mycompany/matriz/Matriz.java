package com.mycompany.matriz;

public class Matriz {
    private int[][] matrizOriginal;
    private int[][] matrizResultante; // Matriz independiente para manipular

    // Crear la matriz y llenar tanto la original como la resultante con los mismos valores
    public void crearMatriz(int filas, int columnas) {
        matrizOriginal = new int[filas][columnas];
        matrizResultante = new int[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int valor = (int) (Math.random() * 100); // Número aleatorio entre 0 y 99
                matrizOriginal[i][j] = valor;
                matrizResultante[i][j] = valor; // Copiar el mismo valor en la matriz resultante
            }
        }
    }

    // Obtener la matriz original
    public int[][] getMatrizOriginal() {
        return matrizOriginal;
    }

    // Obtener la matriz resultante
    public int[][] getMatrizResultante() {
        return matrizResultante;
    }

    // Eliminar múltiplos de un número de la matriz resultante
    public void eliminarMultiplos(int numero) {
        for (int i = 0; i < matrizResultante.length; i++) {
            for (int j = 0; j < matrizResultante[i].length; j++) {
                if (matrizResultante[i][j] % numero == 0) {
                    matrizResultante[i][j] = 0; // Se pone 0 en lugar del múltiplo
                }
            }
        }
    }

    // Eliminar números primos de la matriz resultante
    public void eliminarPrimos() {
        for (int i = 0; i < matrizResultante.length; i++) {
            for (int j = 0; j < matrizResultante[i].length; j++) {
                if (esPrimo(matrizResultante[i][j])) {
                    matrizResultante[i][j] = 0; // Se pone 0 en lugar del número primo
                }
            }
        }
    }

    // Verificar si un número es primo
    private boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
