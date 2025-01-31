package com.mycompany.matriz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Vistas {
    private JFrame frame;
    private JTextField filasField, columnasField, numeroField;
    private JTextArea resultArea;
    private JButton crearButton, eliminarMultiplosButton, eliminarPrimosButton, guardarButton;
    private Matriz matriz;

    private boolean operacionRealizada; // Flag para saber si alguna operación fue realizada

    // Constructor de la clase Vistas
    public Vistas(Matriz matriz) {
        this.matriz = matriz; // Inicializamos el atributo matriz
        this.operacionRealizada = false; // Ninguna operación realizada al principio

        frame = new JFrame("Matriz - Interfaz Gráfica");
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Componentes de la interfaz
        frame.add(new JLabel("Filas:"));
        filasField = new JTextField(5);
        frame.add(filasField);
        
        frame.add(new JLabel("Columnas:"));
        columnasField = new JTextField(5);
        frame.add(columnasField);
        
        crearButton = new JButton("Crear Matriz");
        frame.add(crearButton);
        
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        frame.add(new JScrollPane(resultArea));
        
        eliminarMultiplosButton = new JButton("Eliminar Múltiplos");
        frame.add(eliminarMultiplosButton);
        
        numeroField = new JTextField(5);
        frame.add(new JLabel("Número para múltiplos:"));
        frame.add(numeroField);
        
        eliminarPrimosButton = new JButton("Eliminar Números Primos");
        frame.add(eliminarPrimosButton);
        
        guardarButton = new JButton("Guardar en Archivo");
        frame.add(guardarButton);

        // Muestra la ventana
        frame.setVisible(true);

        // Lógica de botones
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int filas = Integer.parseInt(filasField.getText());
                    int columnas = Integer.parseInt(columnasField.getText());
                    matriz.crearMatriz(filas, columnas);
                    mostrarMatriz(matriz.getMatrizOriginal(), matriz.getMatrizResultante(), "Matriz creada:");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor ingresa valores válidos para filas y columnas.");
                }
            }
        });
        
        eliminarMultiplosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(numeroField.getText());
                    matriz.eliminarMultiplos(numero);
                    operacionRealizada = true; // Marca que se ha realizado una operación
                    mostrarMatriz(matriz.getMatrizOriginal(), matriz.getMatrizResultante(), "Matriz después de eliminar múltiplos:");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor ingresa un número válido.");
                }
            }
        });

        eliminarPrimosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                matriz.eliminarPrimos();
                operacionRealizada = true; // Marca que se ha realizado una operación
                mostrarMatriz(matriz.getMatrizOriginal(), matriz.getMatrizResultante(), "Matriz después de eliminar números primos:");
            }
        });
        
        // Lógica para guardar la matriz y las operaciones en un archivo
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEnArchivo();
            }
        });
    }

    // Método para mostrar las dos matrices en el área de texto
    public void mostrarMatriz(int[][] matrizOriginal, int[][] matrizResultante, String mensaje) {
        StringBuilder sb = new StringBuilder(mensaje + "\n");
        
        // Mostrar la matriz original
        sb.append("Matriz Original:\n");
        for (int i = 0; i < matrizOriginal.length; i++) {
            for (int j = 0; j < matrizOriginal[i].length; j++) {
                sb.append(matrizOriginal[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        sb.append("\n");

        // Mostrar la matriz después de la operación
        sb.append("Matriz Resultante:\n");
        for (int i = 0; i < matrizResultante.length; i++) {
            for (int j = 0; j < matrizResultante[i].length; j++) {
                sb.append(matrizResultante[i][j]).append(" ");
            }
            sb.append("\n");
        }

        resultArea.setText(sb.toString());
    }

    // Método para guardar la matriz y las operaciones en un archivo
    public void guardarEnArchivo() {
        try {
            // Definir el archivo de salida
            File archivo = new File("matriz_operaciones.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));

            // Escribir la matriz original en el archivo
            writer.write("Matriz Original:\n");
            int[][] matrizOriginal = matriz.getMatrizOriginal();
            for (int i = 0; i < matrizOriginal.length; i++) {
                for (int j = 0; j < matrizOriginal[i].length; j++) {
                    writer.write(matrizOriginal[i][j] + " ");
                }
                writer.write("\n");
            }

            // Escribir la matriz resultante después de las operaciones
            writer.write("\nMatriz Resultante:\n");
            int[][] matrizResultante = matriz.getMatrizResultante();
            for (int i = 0; i < matrizResultante.length; i++) {
                for (int j = 0; j < matrizResultante[i].length; j++) {
                    writer.write(matrizResultante[i][j] + " ");
                }
                writer.write("\n");
            }

            // Escribir las operaciones realizadas, solo si se realizaron
            writer.write("\nOperaciones realizadas:\n");
            if (operacionRealizada) {
                // Agregar solo las operaciones que realmente se hayan realizado
                if (!numeroField.getText().isEmpty()) {
                    writer.write("Operación: Eliminar múltiplos de " + numeroField.getText() + "\n");
                }
                writer.write("Operación: Eliminar números primos\n");
            }

            // Cerrar el archivo
            writer.close();
            JOptionPane.showMessageDialog(frame, "Matriz y operaciones guardadas en 'matriz_operaciones.txt'");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Ocurrió un error al guardar el archivo.");
        }
    }
}
