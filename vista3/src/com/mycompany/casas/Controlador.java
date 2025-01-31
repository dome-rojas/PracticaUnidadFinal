package com.mycompany.casas;
import java.io.*;
import java.util.ArrayList;  // Importación de ArrayList
import java.util.List;       // Importación de List

public class Controlador {
    private List<Casa> casas;

    // Constructor del controlador
    public Controlador() {
        casas = new ArrayList<>();
        cargarCasasDesdeArchivo();  // Cargar las casas desde el archivo al iniciar
    }

    // Método para agregar una nueva casa
    public void agregarCasa(float largo, float ancho, float alto, int pisos) {
        Casa casa = new Casa(largo, ancho, alto, pisos);
        casas.add(casa);
        guardarCasasEnArchivo();  // Guardar las casas cada vez que se agregue una
    }

    // Método para guardar las casas en un archivo
    public void guardarCasasEnArchivo() {
        try (FileWriter writer = new FileWriter("casas.txt")) {
            for (Casa casa : casas) {
                writer.write(casa.getNombre() + "," + casa.getLargo() + "," + casa.getAncho() + "," + casa.getAlto() + "," + casa.getNumeroDePisos() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar las casas desde un archivo
    public void cargarCasasDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("casas.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String nombre = datos[0];
                    float largo = Float.parseFloat(datos[1]);
                    float ancho = Float.parseFloat(datos[2]);
                    float alto = Float.parseFloat(datos[3]);
                    int pisos = Integer.parseInt(datos[4]);

                    Casa casa = new Casa(largo, ancho, alto, pisos);
                    casas.add(casa);
                } else {
                    // Si la línea no tiene el formato correcto, mostrar un mensaje de advertencia
                    System.out.println("Línea mal formateada: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar una casa según el nombre
    public boolean actualizarCasa(String nombre, Float nuevoLargo, Float nuevoAncho, Float nuevoAlto, Integer nuevosPisos) {
        for (Casa casa : casas) {
            if (casa.getNombre().equals(nombre)) {
                // Actualizar solo los parámetros que no son valores por defecto
                if (nuevoLargo != null && nuevoLargo != 0.0f) casa.setLargo(nuevoLargo);
                if (nuevoAncho != null && nuevoAncho != 0.0f) casa.setAncho(nuevoAncho);
                if (nuevoAlto != null && nuevoAlto != 0.0f) casa.setAlto(nuevoAlto);
                if (nuevosPisos != null && nuevosPisos != 0) casa.setNumeroDePisos(nuevosPisos);

                guardarCasasEnArchivo();  // Guardar los cambios
                return true;  // Casa actualizada correctamente
            }
        }
        return false;  // No se encontró la casa
    }

    // Método para borrar una casa por nombre
    public boolean borrarCasa(String nombre) {
        for (Casa casa : casas) {
            if (casa.getNombre().equals(nombre)) {
                casas.remove(casa);
                guardarCasasEnArchivo();  // Guardar después de borrar
                return true;  // Casa eliminada correctamente
            }
        }
        return false;  // No se encontró la casa
    }

    public List<Casa> getCasas() {
        return casas;
    }

    // Método para mostrar las casas en formato texto
    public String mostrarCasas() {
        StringBuilder sb = new StringBuilder();
        for (Casa casa : casas) {
            sb.append(casa.toString()).append("\n");
        }
        return sb.toString();
    }
}
