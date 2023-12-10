package org.proyectosalida.Pruebas;

import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Constructores.Usuario;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.Datos.Provider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EditarPerfil extends JFrame {
    private Boolean viewPassword = false;
    private JTextField contraTextField;
    private Boolean editable;
    private Usuario usuario;

    public EditarPerfil(AlmacenDeDatos almacen, Boolean editabl, JFrame padre){
        System.out.println(editabl);
        usuario = almacen.getUsuariosPrueba().get(0);
        setTitle("Menú Personal: " + usuario.getNombre());
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        editable = editabl;

        ImageIcon icon_hid = new ImageIcon("src/main/resources/images/password_hidden.jpg"); Image image_hid = icon_hid.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon icon_sho = new ImageIcon("src/main/resources/images/password_shown.jpg"); Image image_sho = icon_sho.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);

        int nRowsMenu = 7;
        /*if(usuario.getClass().equals(Dueño.class)){
            nRowsMenu = 8;
        }*/

        JPanel  main = new JPanel(new GridLayout(nRowsMenu,2,10,10));
        add(main);
        JPanel panelContraseña = new JPanel(new BorderLayout()); JButton verContraseña = new JButton(new ImageIcon(image_hid)); verContraseña.setBackground(Color.WHITE); JPasswordField passwordField = new JPasswordField(usuario.getContraseña()); panelContraseña.add(passwordField); panelContraseña.add(verContraseña, BorderLayout.EAST); passwordField.setEditable(editable); passwordField.setEnabled(editable);
        main.add(new JLabel("ID (@)", JLabel.CENTER)); JTextField idfield = new JTextField(usuario.getId()); main.add(idfield); idfield.setEditable(editable); idfield.setEnabled(editable);
        main.add(new JLabel("Nombre", JLabel.CENTER)); JTextField nombrefield = new JTextField(usuario.getNombre());main.add(nombrefield); nombrefield.setEditable(editable); nombrefield.setEnabled(editable);
        main.add(new JLabel("Apellido", JLabel.CENTER));JTextField apellidofield = new JTextField(usuario.getApellido()); main.add(apellidofield);apellidofield.setEditable(editable); apellidofield.setEnabled(editable);
        /*TODO CAMBIAR Y PONER LA DE BD*/main.add(new JLabel("Fecha de nacimiento", JLabel.CENTER));JTextField edadfield = new JTextField(/*usuario.getEdad(usuario.getFechaNacimiento())*/"01/01/2004"); main.add(edadfield); edadfield.setEditable(editable); edadfield.setEnabled(editable);
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
                String id = ((JTextField) main.getComponent(1)).getText();
                String nombre = ((JTextField) main.getComponent(3)).getText();
                String apellido = ((JTextField) main.getComponent(5)).getText();

                Date fechaNacimiento = null;
                try {
                    fechaNacimiento = dateFormat.parse(((JTextField) main.getComponent(7)).getText());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

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
                usuario.setId(id);
                usuario.setApellido(apellido);
                usuario.setFechaNacimiento(fechaNacimiento);
                usuario.setContraseña(contraseña);
                usuario.setTelefono(telefono);
                usuario.setCorreo(correo);

                System.out.println(usuario.toString());
                //actualizar(dueño);

                new EditarPerfil(almacen, false, padre);
                dispose();
            }else{ //Funciona como abilitar edicion
                new EditarPerfil(almacen, true, padre);
                dispose();
            }
        });

        atras.addActionListener(e ->{
            dispose();

        });
    }

    private void actualizar(Dueño dueño) {
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

            Provider.actualizarPersona("Dueño", id, datos);
            System.out.println("Usuario actualizado correctamente");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            System.out.println("Error" + e.getMessage());
        }
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
