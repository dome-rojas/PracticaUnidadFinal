package com.mycompany.casas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;

public class Vistas extends JFrame {
    private Controlador controlador;
    private JTextField txtLargo, txtAncho, txtAlto, txtPisos, txtNombreCasa;
    private JTextArea txtAreaCasas;
    private JButton btnAgregar, btnMostrar, btnVerificarHomominas, btnExportar, btnActualizar, btnBorrar;

    public Vistas(Controlador controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gestión de Casas");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblLargo = new JLabel("Largo (m):");
        lblLargo.setBounds(20, 20, 100, 25);
        add(lblLargo);

        txtLargo = new JTextField();
        txtLargo.setBounds(130, 20, 150, 25);
        add(txtLargo);

        JLabel lblAncho = new JLabel("Ancho (m):");
        lblAncho.setBounds(20, 60, 100, 25);
        add(lblAncho);

        txtAncho = new JTextField();
        txtAncho.setBounds(130, 60, 150, 25);
        add(txtAncho);

        JLabel lblAlto = new JLabel("Alto (m):");
        lblAlto.setBounds(20, 100, 100, 25);
        add(lblAlto);

        txtAlto = new JTextField();
        txtAlto.setBounds(130, 100, 150, 25);
        add(txtAlto);

        JLabel lblPisos = new JLabel("Número de Pisos:");
        lblPisos.setBounds(20, 140, 120, 25);
        add(lblPisos);

        txtPisos = new JTextField();
        txtPisos.setBounds(130, 140, 150, 25);
        add(txtPisos);

        JLabel lblNombreCasa = new JLabel("Nombre Casa a Actualizar:");
        lblNombreCasa.setBounds(20, 180, 160, 25);
        add(lblNombreCasa);

        txtNombreCasa = new JTextField();
        txtNombreCasa.setBounds(180, 180, 150, 25);
        add(txtNombreCasa);

        // Botón para agregar una casa
        btnAgregar = new JButton("Agregar Casa");
        btnAgregar.setBounds(20, 220, 150, 30);
        add(btnAgregar);

        // Botón para mostrar las casas
        btnMostrar = new JButton("Mostrar Casas");
        btnMostrar.setBounds(180, 220, 150, 30);
        add(btnMostrar);

        // Botón para verificar casas homónimas
        btnVerificarHomominas = new JButton("Verificar Homónimas");
        btnVerificarHomominas.setBounds(100, 260, 200, 30);
        add(btnVerificarHomominas);

        // Botón para exportar casas y homónimas
        btnExportar = new JButton("Exportar Casas y Homónimas");
        btnExportar.setBounds(100, 300, 200, 30);
        add(btnExportar);

        // Botón para actualizar casa
        btnActualizar = new JButton("Actualizar Casa");
        btnActualizar.setBounds(100, 340, 200, 30);
        add(btnActualizar);

        // Botón para borrar casa
        btnBorrar = new JButton("Borrar Casa");
        btnBorrar.setBounds(100, 380, 200, 30);
        add(btnBorrar);

        // Área de texto para mostrar las casas
        txtAreaCasas = new JTextArea();
        txtAreaCasas.setBounds(20, 420, 350, 100);
        txtAreaCasas.setEditable(false);
        add(txtAreaCasas);

        // Acción para agregar casa
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float largo = Float.parseFloat(txtLargo.getText());
                    float ancho = Float.parseFloat(txtAncho.getText());
                    float alto = Float.parseFloat(txtAlto.getText());
                    int pisos = Integer.parseInt(txtPisos.getText());

                    controlador.agregarCasa(largo, ancho, alto, pisos);
                    limpiarCampos();
                    mostrarCasas();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
                }
            }
        });

        // Acción para mostrar las casas
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCasas();
            }
        });

        // Acción para verificar casas homónimas
        btnVerificarHomominas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCasasHomonimas();
            }
        });

        // Acción para exportar casas y homónimas
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.exportarCasasYHomominas();
                JOptionPane.showMessageDialog(null, "Las casas y las homónimas han sido exportadas.");
            }
        });

        // Acción para actualizar casa
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreCasa = txtNombreCasa.getText().trim();
                    Float largo = txtLargo.getText().isEmpty() ? null : Float.parseFloat(txtLargo.getText());
                    Float ancho = txtAncho.getText().isEmpty() ? null : Float.parseFloat(txtAncho.getText());
                    Float alto = txtAlto.getText().isEmpty() ? null : Float.parseFloat(txtAlto.getText());
                    Integer pisos = txtPisos.getText().isEmpty() ? null : Integer.parseInt(txtPisos.getText());

                    boolean actualizado = controlador.actualizarCasa(nombreCasa, largo, ancho, alto, pisos);
                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Casa actualizada correctamente.");
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró la casa.");
                    }
                    mostrarCasas();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
                }
            }
        });

        // Acción para borrar casa
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCasa = txtNombreCasa.getText().trim();
                boolean borrado = controlador.borrarCasa(nombreCasa);
                if (borrado) {
                    JOptionPane.showMessageDialog(null, "Casa borrada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró la casa.");
                }
                limpiarCampos();
                mostrarCasas();
            }
        });
    }

    private void limpiarCampos() {
        txtLargo.setText("");
        txtAncho.setText("");
        txtAlto.setText("");
        txtPisos.setText("");
        txtNombreCasa.setText("");
    }

    private void mostrarCasas() {
        String casas = controlador.mostrarCasas();
        if (casas.isEmpty()) {
            txtAreaCasas.setText("No hay casas registradas.");
        } else {
            txtAreaCasas.setText(casas);
        }
    }

    private void verificarCasasHomonimas() {
        List<Casa> casas = controlador.getCasas();
        StringBuilder sb = new StringBuilder();
        boolean hayHomonimas = false;

        // Comparar cada casa con las demás
        for (int i = 0; i < casas.size(); i++) {
            for (int j = i + 1; j < casas.size(); j++) {
                if (casas.get(i).esHomonima(casas.get(j))) {
                    hayHomonimas = true;
                    sb.append("Las casas: \n")
                            .append(casas.get(i).toString()).append("\n")
                            .append("y\n")
                            .append(casas.get(j).toString())
                            .append(" son homónimas.\n\n");
                }
            }
        }

        if (hayHomonimas) {
            txtAreaCasas.setText(sb.toString());
        } else {
            txtAreaCasas.setText("No se encontraron casas homónimas.");
        }
    }
}
