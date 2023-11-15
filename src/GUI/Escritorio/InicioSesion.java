package GUI.Escritorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class InicioSesion extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;

    public InicioSesion(JFrame padre) {
        this.setTitle("Inicia Sesión");
        this.setSize(400, 200);
        this.setLocationRelativeTo(padre);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // Layout para los campos y etiquetas
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField("@",20);
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
            checkCredentials(idField, passField);
            //checkCredentials2(idField, passField);
        });

        cancelar.addActionListener(e -> {
            this.setVisible(false);
            padre.setVisible(true);
        });

        this.add(panel, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

    }

    //Main de prueba
    public static void main(String[] args) {
        new InicioSesion(null).setVisible(true);
    }

    private void checkCredentials2(JTextField nombre, JPasswordField password){
        char[] passChar = password.getPassword();
        String passwordString = new String(passChar);
        if(nombre.getText().equals(passwordString)){ //todo esto hay que quitarlo una vez la base de datos esta establecida y se pueden comprobar credenciales
            new MainMenu(nombre.getText()); //en vez de nombre sería pasar la instancia de usuario
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectas!");
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
            // Considera usar PreparedStatement para evitar la inyección de SQL
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM clientes WHERE id = '" + userId + "' AND contraseña = '" + password + "'");

            if (rs.next()) {
                // Usuario y contraseña correctos
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                new MainMenu();
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
