package org.proyectosalida.Pruebas;

import org.proyectosalida.Datos.Conexion;
import org.proyectosalida.Datos.Provider;
import org.proyectosalida.GUI.Bienvenido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JFrame {
    private JTable tabla;

    public Tabla() {
        this.setTitle("Tabla");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialización de la tabla
        tabla = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabla);
        this.add(scrollPane);

        // Carga de datos en la tabla
        Provider.cargarTablaDueño(tabla);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            Conexion.conectar();
            new Tabla();
        });
    }
}
