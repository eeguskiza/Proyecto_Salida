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
        panel.add(new JTextField(20)); //TODO EL @ DE ID HAY QUE PONERLO AFUERA DEL RECUADRO PERO COMO NO SE HACERLO PUES LO QUITO ERIK PONLO CUANDO LEAS ESTO

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
            System.out.println(1);
            String id = ((JTextField) panel.getComponent(1)).getText();
            String nombre = ((JTextField) panel.getComponent(3)).getText();
            String apellido = ((JTextField) panel.getComponent(5)).getText();
            Date fechaNacimiento = null;
            Boolean tipoUsuarioSeleccionado = false; //false = DUEÑO, true = CLIENTE

            if (seleccion == 0) {
                tipoUsuarioSeleccionado = true;
                System.out.println("cliente");
            } else if (seleccion == 1) {
                tipoUsuarioSeleccionado = false;
                System.out.println("dueño");
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
                System.out.println("Viendo Contraseña");
            }else{
                contraseña = String.valueOf(((JPasswordField) panelContraseña.getComponent(0)).getPassword());
                System.out.println("Contraseña Oculta");
            }
            System.out.println(contraseña);
            System.out.println(confirmarContraseña);

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
                        registrarDueño(nuevoUsuario);
                        System.out.println(nuevoUsuario.toString());
                        //Option pane para decidir si quiere añadir locales o no (gurada un boll para llamar a la ventana)
                        boolean loc = JOptionPane.showConfirmDialog(null, "¿Desea añadir locales?", "Añadir locales", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                        if (loc == true) {
                            VentanaAddLocales ventanaAddLocales = new VentanaAddLocales(nuevoUsuario, almacenDeDatos);
                            ventanaAddLocales.setVisible(true);
                            this.dispose();
                        }else {
                            dispose();
                            //REDIRIGE DIRECTAMENTE A INICIA SESION HABIENDO GUARDADO LOS DATOS EN LA NUBE
                            JOptionPane.showMessageDialog(this, "Usuario creado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            InicioSesion inicioSesion = new InicioSesion(this, almacenDeDatos);
                            inicioSesion.setVisible(true);
                        }
                    } else {
                        Cliente nuevoUsuario = new Cliente(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo, new ArrayList<>());
                        System.out.println(nuevoUsuario.toString());

                        JOptionPane.showMessageDialog(this, "Usuario creado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        InicioSesion inicioSesion = new InicioSesion(padre, almacenDeDatos);
                        this.dispose();
                        padre.dispose();
                        inicioSesion.setVisible(true);
                    }


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al crear el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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


    public static boolean registrarDueño(Dueño dueño) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        // Mostrar mensaje de "Creando dueño..."
        JOptionPane.showMessageDialog(null, "Creando dueño...", "Registro en progreso", JOptionPane.INFORMATION_MESSAGE);

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "INSERT INTO DUEÑO (ID, NOMBRE, APELLIDO, FECHANACIMIENTO, CONTRASEÑA, TELEFONO, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dueño.getId());
                pstmt.setString(2, dueño.getNombre());
                pstmt.setString(3, dueño.getApellido());
                pstmt.setDate(4, new java.sql.Date(dueño.getFechaNacimiento().getTime()));
                pstmt.setString(5, dueño.getContraseña());
                pstmt.setString(6, dueño.getTelefono());
                pstmt.setString(7, dueño.getCorreo());

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el dueño.", "Error de registro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el dueño: " + e.getMessage(), "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
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
