package org.proyectosalida.Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.proyectosalida.Constructores.Caracteristica;



import com.toedter.calendar.JCalendar;

import org.proyectosalida.Constructores.*;
public class VentanaGustos extends JFrame {
    protected Caracteristica caracteristica;
    protected ArrayList<Local>locales;



    public VentanaGustos() {
        JPanel panelPrincipal = new JPanel((new GridLayout(6, 1)));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        JButton Aceptar=new JButton("ACEPTAR");




        JLabel preguntaLabel = new JLabel("¿Cuándo salimos?");
        panelPrincipal.add(preguntaLabel);


        String[] opciones = {"Ahora", "Luego"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        Dimension comboBoxSize = new Dimension(100, 20);
        comboBox.setMaximumSize(comboBoxSize);
        comboBox.setMinimumSize(comboBoxSize);
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrincipal.add(comboBox);


        JCalendar calendar = new JCalendar();
        calendar.setVisible(false);
        panelPrincipal.add(calendar);


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.setVisible(comboBox.getSelectedItem().equals("Luego"));
                System.out.println(calendar.getDate());
                cambiarTamanoVentana(400,450);




            }
        });
        Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaGustos.this.setVisible(false);
            }
        });




        // Usando GridLayout para organizar los gustos en múltiples columnas
        JPanel gustosPanel = new JPanel(new GridLayout(0, 3, 1, 1)); // 0 filas significa cualquier cantidad de filas, 2 columnas, y espacio entre elementos
       // aqui
        ArrayList<String> caracteristicas = new ArrayList<>();


        for (Caracteristica caracteristica : Caracteristica.values()) {
            JCheckBox checkBoxCaracteristica = new JCheckBox(caracteristica.name());
            gustosPanel.add(checkBoxCaracteristica);
            checkBoxCaracteristica.addActionListener(new ActionListener() {// le damos funcion a cada checkbox
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBoxCaracteristica.isSelected()){
                        System.out.println("has seleccionado "+ checkBoxCaracteristica.getText());
                        caracteristicas.add(checkBoxCaracteristica.getText());
                    }else{
                        caracteristicas.remove(checkBoxCaracteristica.getText());
                        System.out.println(checkBoxCaracteristica.getText()+" deseleccionado");
                        caracteristicas.add(checkBoxCaracteristica.getText());
                    }
                }
            });
        }









        // Añadir el panel de gustos al panel principal y alinear a la izquierda
        gustosPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrincipal.add(gustosPanel);
        panelPrincipal.add(Aceptar, BorderLayout.CENTER);


        this.add(panelPrincipal);
        this.setSize(400, 300); // Puede que necesites ajustar el tamaño para que todo quepa bien
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void cambiarTamanoVentana(int ancho, int alto) {
        this.setSize(ancho, alto);
    }


    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(() -> {
            new VentanaGustos();
        });


    }
}

