package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;

public class InicioSesion extends JFrame {

    public InicioSesion(JFrame padre) {
        this.setTitle("Registro");
        this.setSize(500, 500);
        this.setLocationRelativeTo(padre);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout para los campos y etiquetas

        panel.add(new JLabel("ID:", JLabel.CENTER));
        panel.add(new JTextField("@",20));

        panel.add(new JLabel("Contraseña:", JLabel.CENTER));
        panel.add(new JPasswordField(20));

        JPanel panelBotones = new JPanel();
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        panelBotones.add(aceptar);
        panelBotones.add(cancelar);

        aceptar.addActionListener(e -> {
            new MainMenu();
            this.setVisible(false);
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
}
