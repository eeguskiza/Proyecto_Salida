package org.proyectosalida.GUI.Registro;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasDueño.ModificarLocales;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistrarDJ extends JFrame {

    public VentanaRegistrarDJ(DJ dj, JFrame padre, Boolean esUnDJNuevo){
        setTitle("Registra un nuevo DJ");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new GridLayout(8, 2)); add(main, BorderLayout.CENTER); main.setBorder(new EmptyBorder(10, 30, 10, 30));
        main.add(new JLabel("Nombre")); JTextField tNombre = new JTextField(); main.add(tNombre);
        main.add(new JLabel("Apellido")); JTextField tApellido = new JTextField(); main.add(tApellido);
        main.add(new JLabel("Nombre Artistico")); JTextField tNombreArtistico = new JTextField(); main.add(tNombreArtistico);
        main.add(new JLabel("Nacionalidad")); JTextField tNacionalidad = new JTextField(); main.add(tNacionalidad);
        main.add(new JLabel("Edad")); JSpinner sEdad = new JSpinner(); main.add(sEdad); sEdad.getModel().setValue(18);
        main.add(new JLabel("Género Musical")); JTextField tGeneroMusical = new JTextField(); main.add(tGeneroMusical);
        main.add(new JLabel("Estilo Musical")); JTextField tEstiloMusical = new JTextField(); main.add(tEstiloMusical);
        main.add(new JLabel("Instagram (@)")); JTextField tInstagram = new JTextField(); main.add(tInstagram);

        JPanel botonera = new JPanel(new FlowLayout()); add(botonera, BorderLayout.SOUTH);
        JButton atras = new JButton("Atrás"); botonera.add(atras);
        JButton guardar  = new JButton("Guardar"); botonera.add(guardar);

        //LÓGICA...
        //    public DJ(String nombre, String apellido, String nombreMusical, String nacionalidad, int edad, String generoMusical, String estiloMusical, String instagram) {
        if(!esUnDJNuevo){
            tNombre.setText(dj.getNombre());
            tApellido.setText(dj.getApellido());
            tNombreArtistico.setText(dj.getNombreMusical());
            tNacionalidad.setText(dj.getNacionalidad());
            sEdad.getModel().setValue(dj.getEdad());
            tGeneroMusical.setText(dj.getGeneroMusical());
            tEstiloMusical.setText(dj.getEstiloMusical());
            tInstagram.setText(dj.getInstagram());
        }

        //Guardar datos
        guardar.addActionListener(e -> {
            dj.setNombre(tNombre.getText());
            dj.setApellido(tApellido.getText());
            dj.setNombreMusical(tNombreArtistico.getText());
            dj.setNacionalidad(tNacionalidad.getText());
            dj.setEdad((Integer) sEdad.getModel().getValue());
            dj.setGeneroMusical(tGeneroMusical.getText());
            dj.setEstiloMusical(tEstiloMusical.getText());
            dj.setInstagram(tInstagram.getText());

            dispose();
            padre.setVisible(true);
            System.out.println(dj.toString());
        });

        atras.addActionListener(e -> {
            dispose();
            padre.setVisible(true);
        });

    }


    public static void main(String[] args) {
        // Configuración del look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            //Conexion.conectar();
            DJ dj = new DJ();
            VentanaRegistrarDJ vdj = new VentanaRegistrarDJ(dj, null,true);
            vdj.setVisible(true);
        });
    }
}
