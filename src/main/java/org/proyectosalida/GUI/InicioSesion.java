package org.proyectosalida.GUI;

import org.proyectosalida.Constructores.AlmacenDeDatos;
import org.proyectosalida.Constructores.Cliente;
import org.proyectosalida.Constructores.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class InicioSesion extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private AlmacenDeDatos almacenDeDatos;

    public InicioSesion(JFrame padre, AlmacenDeDatos almacen){
        this.setTitle("Inicia Sesión");
        this.setSize(400, 200);
        this.setLocationRelativeTo(padre);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        almacenDeDatos = almacen;

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // Layout para los campos y etiquetas
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("ID:                                       @");
        JTextField idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(200, 25));

        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField(20);
        passField.setPreferredSize(new Dimension(200, 25));

        panel.add(idLabel);
        panel.add(idField);
        panel.add(passLabel);
        panel.add(passField);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        panelBotones.add(aceptar);
        panelBotones.add(cancelar);

        aceptar.addActionListener(e -> {
            checkCredentials2(idField, passField);
        });

        cancelar.addActionListener(e -> {
            this.setVisible(false);
            padre.setVisible(true);
        });

        this.add(panel, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

    }

    //org.Proyecto_Salida.Escritorio.Main de prueba
    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            new InicioSesion(null, new AlmacenDeDatos()).setVisible(true);
        });
    }

    private void checkCredentials2(JTextField nombre, JPasswordField password){
        char[] passChar = password.getPassword();
        String passwordString = new String(passChar);
        for(Usuario u : almacenDeDatos.getUsuarios().values()){
            if(u.getId().equals(nombre.getText())){
                if(u.getContraseña().equals(passwordString)){
                    new MainMenu(u, almacenDeDatos); //en vez de nombre sería pasar la instancia de usuario
                    dispose();
                    break;
                }else{
                    JOptionPane.showMessageDialog(null, "Contraseña incorrectas!");
                }
            }
        }
    }


}
