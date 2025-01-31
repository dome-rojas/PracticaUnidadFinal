
package com.mycompany.casas;

public class Main {
    public static void main(String[] args) {
        // Crear el controlador
        Controlador controlador = new Controlador();
        
        // Crear la vista y pasarle el controlador
        Vistas vista = new Vistas(controlador);
        
        // Mostrar la ventana de la vista
        vista.setVisible(true);
    }
}
