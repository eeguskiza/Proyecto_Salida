package org.proyectosalida.GUI.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Constructores.Dueño;

public class VentranaAddCaracteristicasLocal extends JFrame {
    protected Caracteristica caracteristica;
    public VentranaAddCaracteristicasLocal(JFrame padre, ArrayList<Caracteristica> caracteristicas) {
        JButton Aceptar=new JButton("ACEPTAR");

        JPanel panelcaracteristicas = new JPanel(new BorderLayout(0,1));

        // Usando GridLayout para organizar los gustos en múltiples columnas
        JPanel gustosPanel = new JPanel(new GridLayout(0, 3, 1, 1)); // 0 filas significa cualquier cantidad de filas, 2 columnas, y espacio entre elementos
        // aquí

        for (Caracteristica caracteristica : Caracteristica.values()) {
            JCheckBox checkBoxCaracteristica = new JCheckBox(caracteristica.name());
            gustosPanel.add(checkBoxCaracteristica);
            checkBoxCaracteristica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBoxCaracteristica.isSelected()) {
                        System.out.println("Has seleccionado " + caracteristica);
                        caracteristicas.add(caracteristica);
                    } else {
                        caracteristicas.remove(caracteristica);
                        System.out.println(caracteristica + " deseleccionado");
                    }
                }
            });
        }
        Aceptar.addActionListener(e ->{
            this.dispose();
            padre.setVisible(true);
        });

        panelcaracteristicas.add(gustosPanel, BorderLayout.CENTER);
        panelcaracteristicas.add(Aceptar, BorderLayout.SOUTH);
        this.add(panelcaracteristicas);
        this.setSize(800, 800); // Puede que necesites ajustar el tamaño para que todo quepa bien
        this.setVisible(true);
        this.setLocationRelativeTo(padre); // Establecer la ubicación relativa al padre
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
            JFrame padre = new JFrame();
            padre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new VentranaAddCaracteristicasLocal(padre, new ArrayList<>());
        });
    }
}
