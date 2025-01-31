 
package vistas;

import controlador.VentasGastos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.io.File;          
import java.io.FileWriter;    
import java.io.IOException;   


public class VentasGastoVista extends javax.swing.JFrame {

    private JComboBox<String> comboAnios;
    private JTextField campoVentas;
    private JTextField campoGastos;
    private JTable tablaInforme;
    private DefaultTableModel model;
    private VentasGastos controlador;
    private int mesActual = 0;
    private JLabel labelMes;
    
        public VentasGastoVista() {
        // Configura la apariencia de la interfaz
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicializa la interfaz
        configurarVista();
    }

    private void configurarVista() {
        setTitle("Gestión de Ventas y Gastos");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        controlador = new VentasGastos();

        // Panel de fondo
        JPanel panelFondo = new JPanel();
        panelFondo.setLayout(null);
        panelFondo.setBackground(new java.awt.Color(240, 240, 240)); // Color suave para el fondo
        panelFondo.setBounds(0, 0, 600, 800);
        add(panelFondo);

        // Etiqueta para seleccionar el año
        JLabel labelAnios = new JLabel("Selecciona el año:");
        labelAnios.setBounds(20, 20, 150, 30);
        labelAnios.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        labelAnios.setForeground(new java.awt.Color(52, 58, 64)); // Color oscuro
        panelFondo.add(labelAnios);

        // ComboBox para seleccionar el año
        comboAnios = new JComboBox<>();
        for (int i = 2020; i <= 2030; i++) {
            comboAnios.addItem(String.valueOf(i));
        }
        comboAnios.setBounds(180, 20, 100, 30);
        comboAnios.setBackground(new java.awt.Color(255, 255, 255));
        panelFondo.add(comboAnios);

        // Etiqueta para ventas y gastos
        JLabel labelMeses = new JLabel("Ingrese ventas y gastos por mes:");
        labelMeses.setBounds(20, 60, 300, 30);
        labelMeses.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        labelMeses.setForeground(new java.awt.Color(52, 58, 64));
        panelFondo.add(labelMeses);

        // Inicializamos la etiqueta para mostrar el mes actual
        labelMes = new JLabel(controlador.obtenerNombreMes(mesActual + 1));
        labelMes.setBounds(20, 100, 250, 30);
        labelMes.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        labelMes.setForeground(new java.awt.Color(52, 58, 64));
        panelFondo.add(labelMes);

        // Etiqueta para "Ventas"
        JLabel labelVentas = new JLabel("Ventas:");
        labelVentas.setBounds(20, 140, 100, 30);
        labelVentas.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        labelVentas.setForeground(new java.awt.Color(52, 58, 64));
        panelFondo.add(labelVentas);

        // Campo de texto para ventas
        campoVentas = new JTextField();
        campoVentas.setBounds(80, 180, 100, 30);
        campoVentas.setBackground(new java.awt.Color(255, 255, 255));
        campoVentas.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        panelFondo.add(campoVentas);

        // Etiqueta para "Gastos"
        JLabel labelGastos = new JLabel("Gastos:");
        labelGastos.setBounds(200, 140, 100, 30);
        labelGastos.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        labelGastos.setForeground(new java.awt.Color(52, 58, 64));
        panelFondo.add(labelGastos);

        // Campo de texto para gastos
        campoGastos = new JTextField();
        campoGastos.setBounds(260, 180, 100, 30);
        campoGastos.setBackground(new java.awt.Color(255, 255, 255));
        campoGastos.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        panelFondo.add(campoGastos);

        // Botón "Siguiente Mes"
        JButton siguienteMes = new JButton("Siguiente Mes");
        siguienteMes.setBounds(20, 220, 150, 40);
        siguienteMes.setBackground(new java.awt.Color(85, 139, 47));  // Verde similar al anterior
        siguienteMes.setForeground(java.awt.Color.WHITE);
        siguienteMes.setBorderPainted(false);
        siguienteMes.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        siguienteMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mesActual < 11) {
                    mesActual++;
                    actualizarVistaMes();
                } else {
                    JOptionPane.showMessageDialog(VentasGastoVista.this, "Ya has ingresado todos los meses.");
                }
            }
        });
        panelFondo.add(siguienteMes);

        // Botón para guardar los datos
        JButton botonGuardar = new JButton("Guardar Datos");
        botonGuardar.setBounds(200, 220, 150, 40);
        botonGuardar.setBackground(new java.awt.Color(0, 123, 255)); // Azul para botón de guardar
        botonGuardar.setForeground(java.awt.Color.WHITE);
        botonGuardar.setBorderPainted(false);
        botonGuardar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
        panelFondo.add(botonGuardar);

        // Botón para generar el informe
        JButton botonInforme = new JButton("Generar Informe");
        botonInforme.setBounds(380, 220, 150, 40);
        botonInforme.setBackground(new java.awt.Color(52, 58, 64)); // Gris oscuro
        botonInforme.setForeground(java.awt.Color.WHITE);
        botonInforme.setBorderPainted(false);
        botonInforme.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        botonInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInforme();
            }
        });
        panelFondo.add(botonInforme);

        // Crear la tabla para mostrar el informe
        String[] columnNames = {"Mes", "Ventas", "Gastos"};
        model = new DefaultTableModel(columnNames, 0);
        tablaInforme = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaInforme);
        scrollPane.setBounds(20, 280, 550, 400);
        panelFondo.add(scrollPane);
    }

    private void actualizarVistaMes() {
        campoVentas.setText("");  // Limpiar los campos anteriores
        campoGastos.setText("");
        labelMes.setText(controlador.obtenerNombreMes(mesActual + 1));
        revalidate();
        repaint();
    }


       private void guardarDatos() {
        try {
            Double ventas = Double.valueOf(campoVentas.getText());
            Double gastos = Double.valueOf(campoGastos.getText());

            if (ventas < 0 || gastos < 0) {
                JOptionPane.showMessageDialog(this, "Las ventas y los gastos deben ser valores positivos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controlador.registrarMes(mesActual + 1, ventas, gastos);
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores válidos para ventas y gastos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarInforme() {
        try {
            int anio = Integer.parseInt((String) comboAnios.getSelectedItem());

            model.setRowCount(0);
            DecimalFormat formato = new DecimalFormat("#,##0.00");

            Double maxVentas = -Double.MAX_VALUE, minVentas = Double.MAX_VALUE;
            int mesMaxVentas = 0, mesMinVentas = 0;

            for (int i = 1; i <= 12; i++) {
                Double ventas = controlador.obtenerDatosMes(i)[0] != null ? controlador.obtenerDatosMes(i)[0] : 0.0;
                Double gastos = controlador.obtenerDatosMes(i)[1] != null ? controlador.obtenerDatosMes(i)[1] : 0.0;

                if (ventas > maxVentas) {
                    maxVentas = ventas;
                    mesMaxVentas = i;
                }
                if (ventas < minVentas) {
                    minVentas = ventas;
                    mesMinVentas = i;
                }

                model.addRow(new Object[] {
                    controlador.obtenerNombreMes(i),
                    formato.format(ventas),
                    formato.format(gastos)
                });
            }

            model.addRow(new Object[]{"Total Ventas", formato.format(controlador.totalVentas()), ""});
            model.addRow(new Object[]{"Total Gastos", formato.format(controlador.totalGastos()), ""});
            model.addRow(new Object[]{"Promedio Ventas", formato.format(controlador.promedioVentas()), ""});
            model.addRow(new Object[]{"Mes con más ventas", controlador.obtenerNombreMes(mesMaxVentas), formato.format(maxVentas)});
            model.addRow(new Object[]{"Mes con menos ventas", controlador.obtenerNombreMes(mesMinVentas), formato.format(minVentas)});

            JButton botonGuardarTXT = new JButton("Guardar Informe como TXT");
            botonGuardarTXT.setBounds(380, 650, 200, 40);
            botonGuardarTXT.setBackground(new java.awt.Color(0, 123, 255));
            botonGuardarTXT.setForeground(java.awt.Color.WHITE);
            botonGuardarTXT.setBorderPainted(false);
            botonGuardarTXT.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
            botonGuardarTXT.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guardarInformeComoTXT(anio);
                }
            });
            add(botonGuardarTXT);
            revalidate();
            repaint();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarInformeComoTXT(int anio) {
        String informe = controlador.generarInforme(anio);
        try {
            String fileName = "Informe de ventas " + anio + ".txt";
            File archivo = new File(fileName);
            FileWriter writer = new FileWriter(archivo);
            writer.write(informe);
            writer.close();
            JOptionPane.showMessageDialog(this, "Informe guardado correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Hubo un error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasGastoVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


 
