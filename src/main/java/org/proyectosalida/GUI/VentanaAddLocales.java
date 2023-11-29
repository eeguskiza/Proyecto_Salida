package org.proyectosalida.GUI;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.Provider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VentanaAddLocales extends JFrame {


    public VentanaAddLocales(Dueño dueño) {
        setTitle("Añadir locales");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ejemplo local --> Bar Monty = new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, link1, horariosMonty, true,caracteristicasMonty);
        /* Horarios
        ArrayList<Horario> horariosMonty = new ArrayList<>();
        horariosMonty.add(new Horario("Lunes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Martes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Miercoles", "07:30", "23:30"));
        horariosMonty.add(new Horario("Jueves", "07:30", "23:30"));
        horariosMonty.add(new Horario("Viernes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Sabado", "07:30", "23:30"));
        horariosMonty.add(new Horario("Domingo", "07:30", "16:00"));
         */
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout para los campos y etiquetas

        panel.add(new JLabel("Nombre:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Dirección:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Código Postal:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Aforo:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Teléfono:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Enlace:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Horarios:", JLabel.CENTER));
        panel.add(new JButton("Añadir horario"));
        panel.add(new JLabel("Terraza:", JLabel.CENTER));
        //Si o no y se guarda boolean
        JPanel panel1 = new JPanel();
        JRadioButton checkboxSi = new JRadioButton("Si");
        JRadioButton checkboxNo = new JRadioButton("No");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(checkboxSi);
        grupo.add(checkboxNo);
        panel1.add(checkboxSi);
        panel1.add(checkboxNo);
        panel.add(panel1);
        panel.add(new JLabel("Características:", JLabel.CENTER));
        panel.add(new JButton("Añadir características"));
        JButton botonGuardar = new JButton("Añadir local");
        panel.add(botonGuardar);
        JButton botonVolver = new JButton("Volver");
        panel.add(botonVolver);

        botonGuardar.addActionListener(e -> {
            String nombre = ((JTextField) panel.getComponent(1)).getText();
            String direccion = ((JTextField) panel.getComponent(3)).getText();
            String CP = ((JTextField) panel.getComponent(5)).getText();
            int aforo = Integer.parseInt(((JTextField) panel.getComponent(7)).getText());
            String telefono = ((JTextField) panel.getComponent(9)).getText();
            String enlace = ((JTextField) panel.getComponent(11)).getText();
            ArrayList<Horario> horarios = new ArrayList<>();
            Boolean terraza = true;
            ArrayList<Caracteristica> caracteristicas = new ArrayList<>();

            Bar bar = new Bar(nombre, direccion, CP, aforo, telefono, 0, 0, enlace, horarios, terraza, caracteristicas);
            System.out.println(bar);
            dueño.getLocales().add(bar);
            guardarDueño(dueño);
        });



        add(panel);
    }

    private void guardarDueño(Dueño dueño) {
        String id = dueño.getId();  // ID del documento

        try{
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", dueño.getNombre());
            datos.put("Apellido", dueño.getApellido());
            datos.put("Edad", dueño.getEdad());
            datos.put("Contraseña", dueño.getContraseña());
            datos.put("Teléfono", dueño.getTelefono());
            datos.put("Correo", dueño.getCorreo());
            datos.put("Locales", dueño.getLocales());

            Provider.guardarPersona("Dueño", id, datos);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            System.out.println("Error" + e.getMessage());
        }
    }

    private void guardarLocal() {


    }
    //Main de prueba
    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            VentanaAddLocales ventanaAddLocales = new VentanaAddLocales(new Dueño());
            ventanaAddLocales.setVisible(true);
        });

    }
}
