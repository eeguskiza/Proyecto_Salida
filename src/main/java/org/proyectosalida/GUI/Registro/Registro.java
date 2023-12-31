package org.proyectosalida.GUI.Registro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import com.toedter.calendar.JDateChooser;
import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.InicioSesion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registro extends JFrame {
    boolean viendoContraseña;
    JButton botonVerContraseña;
    JTextField textFieldContraseña = null;

    public Registro(JFrame padre, AlmacenDeDatos almacenDeDatos) {

        viendoContraseña=false;
        botonVerContraseña = new JButton();

        Object[] opciones = {"Cliente", "Dueño"};
        int seleccion = JOptionPane.showOptionDialog(null,
                "Seleccione el tipo de usuario:",
                "Tipo de Usuario",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                opciones, opciones[0]);

        if (seleccion == JOptionPane.CLOSED_OPTION) {
            return;
        }

        this.setTitle("Registro");
        this.setSize(500, 500);
        this.setLocationRelativeTo(padre);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout para los campos y etiquetas

        ImageIcon icon_hid = new ImageIcon("src/main/resources/images/password_hidden.jpg"); Image image_hid = icon_hid.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon icon_sho = new ImageIcon("src/main/resources/images/password_shown.jpg"); Image image_sho = icon_sho.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);

        panel.add(new JLabel("ID (@):", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Nombre:", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Apellido:", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Fecha de Nacimiento:", JLabel.CENTER));
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        panel.add(dateChooser);

        panel.add(new JLabel("Contraseña:", JLabel.CENTER));
        JPanel panelContraseña = new JPanel(new BorderLayout());JPasswordField passwordField = new JPasswordField(20); panelContraseña.add(passwordField); botonVerContraseña = new JButton(new ImageIcon(image_hid)); panelContraseña.add(botonVerContraseña, BorderLayout.EAST); botonVerContraseña.setBackground(Color.white);
        panel.add(panelContraseña);

        panel.add(new JLabel("Comfirmar contraseña:", JLabel.CENTER));
        panel.add(new JPasswordField(20));

        panel.add(new JLabel("Teléfono:", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Correo:", JLabel.CENTER));
        panel.add(new JTextField(20));

        JPanel panelBotones = new JPanel();
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        botonVerContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contraseña = String.valueOf(passwordField.getPassword());

                if (viendoContraseña) {
                    contraseña = textFieldContraseña.getText();
                    botonVerContraseña.setIcon(new ImageIcon(image_hid));
                    panelContraseña.removeAll();
                    passwordField.setText(contraseña); // Restablece el texto en el JPasswordField
                    panelContraseña.add(passwordField);
                    viendoContraseña = false;
                } else {
                    botonVerContraseña.setIcon(new ImageIcon(image_sho));
                    panelContraseña.removeAll();
                    textFieldContraseña = new JTextField(contraseña);
                    panelContraseña.add(textFieldContraseña);
                    viendoContraseña = true;
                }

                panelContraseña.add(botonVerContraseña, BorderLayout.EAST);
                panelContraseña.revalidate();
                panelContraseña.repaint();
            }
        });

        aceptar.addActionListener(e -> {
            String id = ((JTextField) panel.getComponent(1)).getText();
            String nombre = ((JTextField) panel.getComponent(3)).getText();
            String apellido = ((JTextField) panel.getComponent(5)).getText();
            Date fechaNacimiento = null;
            Boolean tipoUsuarioSeleccionado = false; //false = DUEÑO, true = CLIENTE

            if (seleccion == 0) {
                tipoUsuarioSeleccionado = true;
                almacenDeDatos.logger.info("Cliente seleccionado");
            } else if (seleccion == 1) {
                tipoUsuarioSeleccionado = false;
                almacenDeDatos.logger.info("Dueño seleccionado");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un tipo de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Fecha check
            if (dateChooser.getDate() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                fechaNacimiento = dateChooser.getDate();

            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de nacimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //String contraseña = new String(((JPasswordField) panel.getComponent(9)).getPassword());
            String confirmarContraseña = new String(((JPasswordField) panel.getComponent(11)).getPassword());
            String contraseña = null;
            if(viendoContraseña){
                contraseña = ((JTextField) panelContraseña.getComponent(0)).getText();
                almacenDeDatos.logger.info("Contraseña Visible");
            }else{
                contraseña = String.valueOf(((JPasswordField) panelContraseña.getComponent(0)).getPassword());
                almacenDeDatos.logger.info("Contraseña Oculta");
            }

            String telefono = ((JTextField) panel.getComponent(13)).getText();
            String correo = ((JTextField) panel.getComponent(15)).getText();


            if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                    contraseña.isEmpty() || !contraseña.equals(confirmarContraseña) ||
                    telefono.isEmpty() || correo.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Por favor, revise todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {

                    if (tipoUsuarioSeleccionado.equals(false)) {
                        ArrayList<Local> locales = new ArrayList<>();
                        Dueño nuevoUsuario = new Dueño(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo, locales);
                        //Llama al metodo que lo añade en sql
                        almacenDeDatos.registrarDueño(nuevoUsuario);
                        //Option pane para decidir si quiere añadir locales o no (gurada un boll para llamar a la ventana)
                        boolean loc = JOptionPane.showConfirmDialog(null, "¿Desea añadir locales?", "Añadir locales", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                        if (loc == true) {
                            VentanaAddLocales ventanaAddLocales = new VentanaAddLocales(nuevoUsuario, almacenDeDatos);
                            ventanaAddLocales.setVisible(true);
                            this.dispose();
                        }else {
                            dispose();
                            //REDIRIGE DIRECTAMENTE A INICIA SESION HABIENDO GUARDADO LOS DATOS EN LA NUBE
                            InicioSesion inicioSesion = new InicioSesion(this, almacenDeDatos);
                            inicioSesion.setVisible(true);
                        }
                    } else {
                        Cliente nuevoUsuario = new Cliente(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo, new ArrayList<>());
                        //Llama al metodo que lo añade en sql
                        almacenDeDatos.registrarCliente(nuevoUsuario);
                        InicioSesion inicioSesion = new InicioSesion(padre, almacenDeDatos);
                        this.dispose();
                        padre.dispose();
                        inicioSesion.setVisible(true);
                    }


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al crear el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    almacenDeDatos.logger.warning("Error al crear el usuario: " + ex.getMessage());
                }



            }
        });


        cancelar.addActionListener(e -> {
            this.dispose();
            padre.setVisible(true);
                });

        panelBotones.add(aceptar);
        panelBotones.add(cancelar);

        // Agregar el panel a la ventana
        this.add(panel);
        this.add(panelBotones, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            //Conexion.conectar();
            new Registro(null, new AlmacenDeDatos());
        });

    }

}
