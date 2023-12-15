package org.proyectosalida.Pruebas;

import javax.swing.*;
import java.awt.*;

public class Tabla extends JFrame {
    private JTable tablaDueño, tablaCliente;

    public Tabla() {
        this.setTitle("Tabla");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        // Inicialización de la tabla
        tablaDueño = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaDueño);
        this.add(scrollPane);

        tablaCliente = new JTable();
        JScrollPane scrollPane2 = new JScrollPane(tablaCliente);
        this.add(scrollPane2);

        // Carga de datos en la tabla


        /*

        DefaultTableModel modelo = (DefaultTableModel) tablaDueño.getModel();
        Dueño dueño = new Dueño();
        System.out.println(dueño);
        String tablaID = "";
        String tablaNombre = "";
        String tablaApellido = "";
        String tablaTelefono = "";
        String tablaCorreo = "";
        String tablaPassword = "";
        double tablaEdad = 0.0;
        ArrayList<Local> tablaLocales = null;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            tablaID = modelo.getValueAt(i, 0).toString();
            tablaNombre = modelo.getValueAt(i, 1).toString();
            tablaApellido = modelo.getValueAt(i, 2).toString();
            tablaTelefono = modelo.getValueAt(i, 3).toString();
            tablaCorreo = modelo.getValueAt(i, 4).toString();
            tablaPassword = modelo.getValueAt(i, 5).toString();
            tablaEdad = Double.parseDouble(modelo.getValueAt(i, 6).toString());
            tablaLocales = (ArrayList<Local>) modelo.getValueAt(i, 7);
            //System.out.println(tablaLocales);
        }

        dueño.setId(tablaID);
        dueño.setNombre(tablaNombre);
        dueño.setApellido(tablaApellido);
        dueño.setTelefono(tablaTelefono);
        dueño.setCorreo(tablaCorreo);
        dueño.setContraseña(tablaPassword);
        /*
        for(Local local : tablaLocales){
            dueño.agregarLocal(local);
        }

         */


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
            new Tabla();
        });
    }
}
