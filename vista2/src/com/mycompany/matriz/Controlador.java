package com.mycompany.matriz;

public class Controlador {
    private Matriz matriz;
    private Vistas vistas;

    public Controlador() {
        matriz = new Matriz();
        vistas = new Vistas(matriz); // Pasamos la instancia de Matriz a Vistas
    }

    // Iniciamos el controlador, pero ya no pedimos datos de entrada aquí
    // ya que todo se maneja desde la vista (JFrame)
    public void iniciarPrograma() {
        // Aquí no solicitamos la entrada de datos con JOptionPane
        // Ahora, la creación de la matriz y las operaciones dependen de los eventos
        // en los botones de la interfaz gráfica (en la clase Vistas)
        
        // Ya no es necesario hacer nada aquí, ya que todo depende de la interacción gráfica.
    }
    
    // Este método no es necesario ya que las operaciones también se gestionan por los eventos gráficos.
}
