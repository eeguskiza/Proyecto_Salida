package org.proyectosalida.Pruebas;

import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Constructores.Usuario;
import org.proyectosalida.Datos.Provider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditarPerfil extends JFrame {
    private Boolean viewPassword = false;
    private JTextField contraTextField;

    public EditarPerfil(Usuario usuario, Boolean editable){
        System.out.println(editable);
        JFrame frame = new JFrame(); add(frame);
        frame.setTitle("Menú Personal: " + usuario.getNombre());
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        ImageIcon icon_hid = new ImageIcon("src/main/resources/images/password_hidden.jpg"); Image image_hid = icon_hid.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon icon_sho = new ImageIcon("src/main/resources/images/password_shown.jpg"); Image image_sho = icon_sho.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);

        int nRowsMenu = 7;
        if(usuario.getClass().equals(Dueño.class)){
            nRowsMenu = 8;
        }

        JPanel  main = new JPanel(new GridLayout(nRowsMenu,2,10,10));
        frame.add(main);
        JPanel panelContraseña = new JPanel(new BorderLayout()); JButton verContraseña = new JButton(new ImageIcon(image_hid)); verContraseña.setBackground(Color.WHITE); JPasswordField passwordField = new JPasswordField(usuario.getContraseña()); panelContraseña.add(passwordField); panelContraseña.add(verContraseña, BorderLayout.EAST); passwordField.setEditable(editable); passwordField.setEnabled(editable);
        main.add(new JLabel("ID (@)", JLabel.CENTER)); JTextField idfield = new JTextField(usuario.getId()); main.add(idfield); idfield.setEditable(editable); idfield.setEnabled(editable);
        main.add(new JLabel("Nombre", JLabel.CENTER)); JTextField nombrefield = new JTextField(usuario.getNombre());main.add(nombrefield); nombrefield.setEditable(editable); nombrefield.setEnabled(editable);
        main.add(new JLabel("Apellido", JLabel.CENTER));JTextField apellidofield = new JTextField(usuario.getApellido()); main.add(apellidofield);apellidofield.setEditable(editable); apellidofield.setEnabled(editable);
        main.add(new JLabel("Edad", JLabel.CENTER));JTextField edadfield = new JTextField(usuario.getEdad(usuario.getFechaNacimiento())); main.add(edadfield); edadfield.setEditable(editable); edadfield.setEnabled(editable);
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
        botonera.add(atras);
        if(editable==true){
            botonera.add(guardarCambios);
        }
        frame.add(botonera, BorderLayout.SOUTH);
        frame.setVisible(true);

        //METODOS DEL PANEL EDITAR DATOS
        verContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPassword = !viewPassword;
                if(viewPassword){
                    verContraseña.setIcon(new ImageIcon(image_sho));
                    panelContraseña.remove(panelContraseña.getComponent(0));
                    contraTextField = new JTextField(usuario.getContraseña());
                    panelContraseña.add(contraTextField);
                    contraTextField.setEditable(editable); contraTextField.setEnabled(editable);
                }else{
                    verContraseña.setIcon(new ImageIcon(image_hid));
                    panelContraseña.removeAll();
                    JPasswordField pf = new JPasswordField(usuario.getContraseña()); pf.setEditable(editable); pf.setEnabled(editable);
                    panelContraseña.add(pf);
                    panelContraseña.add(verContraseña, BorderLayout.EAST);
                }
                frame.revalidate();
                frame.repaint();

            }
        });

        guardarCambios.addActionListener(e -> {
            String id = ((JTextField) main.getComponent(1)).getText();
            String nombre = ((JTextField) main.getComponent(3)).getText();
            String apellido = ((JTextField) main.getComponent(5)).getText();
            int edad = Integer.parseInt(((JTextField) main.getComponent(7)).getText());
            String contraseña = "";
            String telefono = ((JTextField) main.getComponent(11)).getText();
            String correo = ((JTextField) main.getComponent(13)).getText();
            ArrayList<Local> locales = new ArrayList<>();
            Dueño dueño = new Dueño(id, nombre, apellido, null, contraseña, telefono, correo, locales);
            System.out.println(dueño);
            actualizar(dueño);

        });

        atras.addActionListener(e ->{
            frame.dispose();
            this.setVisible(true);
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

}
