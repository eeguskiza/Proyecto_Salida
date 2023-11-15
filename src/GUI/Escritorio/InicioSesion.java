package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;

public class InicioSesion extends JFrame {

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

    private void checkCredentials(JTextField nombre, JPasswordField password){
        char[] passChar = password.getPassword();
        String passwordString = new String(passChar);
        if(nombre.getText().equals(passwordString)){ //todo esto hay que quitarlo una vez la base de datos esta establecida y se pueden comprobar credenciales
            new MainMenu(nombre.getText()); //en vez de nombre sería pasar la instancia de usuario
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectas!");
        }
    }
}
