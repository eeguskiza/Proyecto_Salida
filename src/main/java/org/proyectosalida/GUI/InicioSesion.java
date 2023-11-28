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
            //checkCredentials(idField, passField);
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

    private void checkCredentials(JTextField idField, JPasswordField passField) {
        String userId = idField.getText();
        String password = new String(passField.getPassword());

        // Verificar que los campos no estén vacíos
        if (userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID de usuario y la contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Proyecto_Salida", "erik", "0977"    );
            // Considerar usar PreparedStatement para evitar la inyección de SQL --> Erik acuerdate de mirarlo
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM clientes WHERE id = '" + userId + "' AND contraseña = '" + password + "'");

            if (rs.next()) {
                // Usuario y contraseña correctos
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                //TODO ANTES ERA INICIO DE SESION CON EL ID PERO AHORA HAY QUE CREAR EL OBJETO USUARIO PARA PASARLE TODA LA INFORMACION A
                // LA VENTANA. HASTA CORREGIR ESTO VOY A PONER QUE SE LE PASE UN USUARIO VACIO YA QUE EL METODO EN FUNCIONAMINETO ES
                // EL MIO SIMPLE CON EL ARRAYLIST EN VEZ DE EL DE LA BASE DE DATOS. LA LINEA DE ABAJO ES LA QUE HAY QUE CAMBIAR.
                new MainMenu(new Cliente(), new AlmacenDeDatos());
                this.dispose();
            } else {
                // Usuario o contraseña incorrectos
                JOptionPane.showMessageDialog(this, "Usuario o Contraseña incorrectas!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectarse a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
