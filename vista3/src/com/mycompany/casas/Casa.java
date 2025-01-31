package com.mycompany.casas;

public class Casa {
    private static int contador = 1; // Contador para generar nombres únicos
    private String nombre;
    private float largo;
    private float ancho;
    private float alto;
    private int numeroDePisos;

    // Constructor
    public Casa(float largo, float ancho, float alto, int numeroDePisos) {
        this.nombre = String.format("%03d", contador); // Genera un nombre como "001", "002", etc.
        contador++;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.numeroDePisos = numeroDePisos;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public int getNumeroDePisos() {
        return numeroDePisos;
    }

    public void setNumeroDePisos(int numeroDePisos) {
        this.numeroDePisos = numeroDePisos;
    }

    @Override
    public String toString() {
        return "Casa " + nombre + " [Largo: " + largo + ", Ancho: " + ancho + ", Alto: " + alto + ", Pisos: " + numeroDePisos + "]";
    }
}
