package org.proyectosalida.Pruebas;

import org.proyectosalida.Constructores.Cliente;
import org.proyectosalida.Constructores.Usuario;
import org.proyectosalida.Datos.AlmacenDeDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditarPerfil extends JFrame {
    private Boolean viewPassword = false;
    private JTextField contraTextField;
    private Boolean editable;
    private Usuario usuario;
    private AlmacenDeDatos almacenDeDatos;

    public EditarPerfil(AlmacenDeDatos almacen, Boolean editabl, JFrame padre){
        System.out.println(editabl);
        usuario = almacen.getUsuarios().get(0);
        setTitle("Menú Personal: " + usuario.getNombre());
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        editable = editabl;
        almacenDeDatos = almacen;

        ImageIcon icon_hid = new ImageIcon("src/main/resources/images/password_hidden.jpg"); Image image_hid = icon_hid.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon icon_sho = new ImageIcon("src/main/resources/images/password_shown.jpg"); Image image_sho = icon_sho.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);

        int nRowsMenu = 7;
        /*if(usuario.getClass().equals(Dueño.class)){
            nRowsMenu = 8;
        }*/

        JPanel  main = new JPanel(new GridLayout(nRowsMenu,2,10,10));
        add(main);
        JPanel panelContraseña = new JPanel(new BorderLayout()); JButton verContraseña = new JButton(new ImageIcon(image_hid)); verContraseña.setBackground(Color.WHITE); JPasswordField passwordField = new JPasswordField(usuario.getContraseña()); panelContraseña.add(passwordField); panelContraseña.add(verContraseña, BorderLayout.EAST); passwordField.setEditable(editable); passwordField.setEnabled(editable);
        main.add(new JLabel("ID (@)", JLabel.CENTER)); JTextField idfield = new JTextField(usuario.getId()); main.add(idfield); idfield.setEditable(false); idfield.setEnabled(false); //De momento mejor si no editamos los ids que sino se lia mucho
        main.add(new JLabel("Nombre", JLabel.CENTER)); JTextField nombrefield = new JTextField(usuario.getNombre());main.add(nombrefield); nombrefield.setEditable(editable); nombrefield.setEnabled(editable);
        main.add(new JLabel("Apellido", JLabel.CENTER));JTextField apellidofield = new JTextField(usuario.getApellido()); main.add(apellidofield);apellidofield.setEditable(editable); apellidofield.setEnabled(editable);
        main.add(new JLabel("Fecha de nacimiento", JLabel.CENTER));JTextField edadfield = new JTextField(almacen.transformarDateAString(usuario.getFechaNacimiento())); main.add(edadfield); edadfield.setEditable(editable); edadfield.setEnabled(editable);
        main.add(new JLabel("Contraseña", JLabel.CENTER)); main.add(panelContraseña);
        main.add(new JLabel("Tlf.", JLabel.CENTER)); JTextField tlffield = new JTextField(usuario.getTelefono()); main.add(tlffield); tlffield.setEditable(editable); tlffield.setEnabled(editable);
        main.add(new JLabel("Correo", JLabel.CENTER)); JTextField correofield = new JTextField(usuario.getCorreo());main.add(correofield); correofield.setEditable(editable); correofield.setEnabled(editable);
        /*if(usuario.getClass().equals(Dueño.class)){
            main.add(new JLabel("Locales", JLabel.CENTER));
            if(editable){
                main.add(clickableLabel("Modificar Locales", 7)); //MODIFICAR -> UN JTREE CON UN PANEL INDIVIDUAL AL LADO PARA SELECCIONAR UNO Y EDITARLO
            }else{
                main.add(clickableLabel("Ver todos", 8)); //VER LOCALES -> JTABLE CON TODOS ENLISTADOS
            }
        }

         */

        JPanel botonera = new JPanel(new FlowLayout());
        JButton atras = new JButton("Atzerakarga");
        JButton guardarCambios = new JButton("Actualizar");
        botonera.add(atras);botonera.add(guardarCambios);
        if(editable==false){
            guardarCambios.setText("Editar");
        }
        add(botonera, BorderLayout.SOUTH);
        setVisible(true);

        //METODOS DEL PANEL EDITAR DATOS
        verContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPassword = !viewPassword;

                if(viewPassword){
                    String contraseña = new String(((JPasswordField) panelContraseña.getComponent(0)).getPassword());
                    System.out.println(contraseña);
                    verContraseña.setIcon(new ImageIcon(image_sho));
                    panelContraseña.remove(panelContraseña.getComponent(0));
                    contraTextField = new JTextField(contraseña);
                    panelContraseña.add(contraTextField);
                    contraTextField.setEditable(editable); contraTextField.setEnabled(editable);
                }else{
                    String contraseña = new String(((JTextField) panelContraseña.getComponent(1)).getText());
                    System.out.println(contraseña);
                    verContraseña.setIcon(new ImageIcon(image_hid));
                    panelContraseña.removeAll();
                    JPasswordField pf = new JPasswordField(contraseña); pf.setEditable(editable); pf.setEnabled(editable);
                    panelContraseña.add(pf);
                    panelContraseña.add(verContraseña, BorderLayout.EAST);
                }
                revalidate();
                repaint();

            }
        });

        guardarCambios.addActionListener(e -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if(editable){ //Funciona como guardar cambios

                //ACTUALIZAR CAMBIOS EN LOCAL
                //String id = ((JTextField) main.getComponent(1)).getText();
                String nombre = ((JTextField) main.getComponent(3)).getText();
                String apellido = ((JTextField) main.getComponent(5)).getText();

                Date fechaNacimiento = almacen.transformarStringADate(((JTextField) main.getComponent(7)).getText());

                String contraseña = "";
                if(viewPassword){
                    contraseña = ((JTextField) panelContraseña.getComponent(1)).getText();
                }else{
                    contraseña = new String(((JPasswordField) panelContraseña.getComponent(0)).getPassword());
                }
                String telefono = ((JTextField) main.getComponent(11)).getText();
                String correo = ((JTextField) main.getComponent(13)).getText();

                System.out.println(usuario.toString());

                usuario.setNombre(nombre);
                //usuario.setId(id);
                usuario.setApellido(apellido);
                usuario.setFechaNacimiento(fechaNacimiento);
                usuario.setContraseña(contraseña);
                usuario.setTelefono(telefono);
                usuario.setCorreo(correo);

                System.out.println(usuario.toString());

                //ACTUALIZAR EN BASE DE DATOS
                actualizarDatosUsuarioBD(usuario, nombre, apellido, fechaNacimiento, contraseña, telefono, correo);

                new EditarPerfil(almacen, false, padre);
                dispose();
            }else{ //Funciona como abilitar edicion
                new EditarPerfil(almacen, true, padre);
                dispose();
            }
        });

        atras.addActionListener(e ->{
            dispose();
            padre.setVisible(true);
        });
    }

    public static boolean actualizarDatosUsuarioBD(Usuario usuario, String nombre, String apellido, Date fechaNacimiento, String contraseña, String tlf, String email) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "";
            if(usuario.getClass().equals(Cliente.class)){
                sql = "UPDATE CLIENTE SET NOMBRE = ?, APELLIDO = ?, FECHANACIMIENTO = ?, CONTRASEÑA = ?, TELEFONO = ?, EMAIL = ? WHERE ID = ?";
            }else{
                sql = "UPDATE DUEÑO SET NOMBRE = ?, APELLIDO = ?, FECHANACIMIENTO = ?, CONTRASEÑA = ?, TELEFONO = ?, EMAIL = ? WHERE ID = ?";

            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, apellido);
                pstmt.setDate(3, new java.sql.Date(fechaNacimiento.getTime()));
                pstmt.setString(4, contraseña);
                pstmt.setString(5, tlf);
                pstmt.setString(6, email);
                pstmt.setString(7, usuario.getId());

                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Datos del cliente actualizados en la base de datos.");
                    return true;
                } else {
                    System.out.println("No se pudo actualizar los datos del cliente.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
            AlmacenDeDatos almacen = new AlmacenDeDatos();
            EditarPerfil v = new EditarPerfil( almacen, false, null);
        });
    }

}
