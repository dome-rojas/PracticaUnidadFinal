package vistas;

import javax.swing.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.IOException;


public class VerInformesVista extends javax.swing.JFrame {
  public VerInformesVista() {
        setTitle("Informes Guardados");
        setSize(600, 500);  // Ajustamos el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Llamamos a nuestro método personalizado para configurar la UI
        crearUI();

        setVisible(true);  // Aseguramos que la ventana se haga visible
    }

    private void crearUI() {
        // Agregar un panel con un fondo de color suave
        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new java.awt.Color(240, 240, 240));
        panelFondo.setLayout(null);

        // Scroll panel para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(panelFondo);
        scrollPane.setBounds(0, 0, 600, 460);  // Dejamos un espacio para los botones
        add(scrollPane);

        // Directorio donde guardamos los informes
        File directorioInformes = new File("./");
        File[] archivos = directorioInformes.listFiles((dir, name) -> name.endsWith(".txt"));

        int y = 20;
        for (File archivo : archivos) {
            String nombreArchivo = archivo.getName();

            // Etiqueta con el nombre del archivo
            JLabel labelArchivo = new JLabel(nombreArchivo);
            labelArchivo.setBounds(20, y, 400, 30);
            labelArchivo.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
            panelFondo.add(labelArchivo);

            // Botón para editar el archivo
            JButton botonEditar = new JButton("Editar");
            botonEditar.setBounds(430, y, 100, 30);
            botonEditar.setBackground(new java.awt.Color(85, 139, 47));
            botonEditar.setForeground(java.awt.Color.WHITE);
            botonEditar.setBorderPainted(false);
            botonEditar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editarArchivo(archivo);  // Función para editar el archivo
                }
            });
            panelFondo.add(botonEditar);

            // Botón para eliminar el archivo
            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.setBounds(540, y, 100, 30);
            botonEliminar.setBackground(new java.awt.Color(220, 76, 36));
            botonEliminar.setForeground(java.awt.Color.WHITE);
            botonEliminar.setBorderPainted(false);
            botonEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminarArchivo(archivo);  // Función para eliminar el archivo
                }
            });
            panelFondo.add(botonEliminar);

            y += 40;  // Espacio para los siguientes archivos
        }

        // Botón "Regresar"
        JButton botonRegresar = new JButton("Regresar");
        botonRegresar.setBounds(20, 420, 120, 30);
        botonRegresar.setBackground(new java.awt.Color(0, 123, 255));
        botonRegresar.setForeground(java.awt.Color.WHITE);
        botonRegresar.setBorderPainted(false);
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra la ventana actual
                new VistaPrincipal().setVisible(true);  // Regresa al menú principal
            }
        });
        panelFondo.add(botonRegresar);

        // Botón "Recargar"
        JButton botonRecargar = new JButton("Recargar");
        botonRecargar.setBounds(160, 420, 120, 30);
        botonRecargar.setBackground(new java.awt.Color(52, 58, 64));
        botonRecargar.setForeground(java.awt.Color.WHITE);
        botonRecargar.setBorderPainted(false);
        botonRecargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra la ventana actual
                new VerInformesVista().setVisible(true);  // Recarga la ventana de informes
            }
        });
        panelFondo.add(botonRecargar);
    }

    // Función para editar el archivo
    private void editarArchivo(File archivo) {
        try {
            Desktop.getDesktop().edit(archivo);  // Esto abrirá el archivo en el editor predeterminado
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se puede editar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Función para eliminar el archivo
    private void eliminarArchivo(File archivo) {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este archivo?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            if (archivo.delete()) {
                JOptionPane.showMessageDialog(this, "Archivo eliminado correctamente.");
                this.dispose();  // Cierra la ventana actual
                new VerInformesVista().setVisible(true);  // Recarga la lista de informes
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
 public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VerInformesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerInformesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerInformesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerInformesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerInformesVista().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


