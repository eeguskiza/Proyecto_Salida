package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;

public class Registro extends JFrame {

    public Registro(JFrame padre) {
        this.setTitle("Registro");
        this.setSize(500, 500);
        this.setLocationRelativeTo(padre);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout para los campos y etiquetas

        // Crear campos de texto y etiquetas para cada atributo del Usuario
        panel.add(new JLabel("Tipo de Usuario:", JLabel.CENTER));
        ButtonGroup tipoUsuario = new ButtonGroup();
        JRadioButton cliente = new JRadioButton("Cliente");
        JRadioButton dueño = new JRadioButton("Dueño");
        tipoUsuario.add(cliente);
        JPanel panelTipoUsuario = new JPanel();
        panelTipoUsuario.add(cliente);
        panelTipoUsuario.add(dueño);
        panel.add(panelTipoUsuario);

        panel.add(new JLabel("ID:", JLabel.CENTER));
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
        panel.add(new JPasswordField(20));

        panel.add(new JLabel("Comfirmar contraseña:", JLabel.CENTER));
        panel.add(new JPasswordField(20));

        panel.add(new JLabel("Teléfono:", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Correo:", JLabel.CENTER));
        panel.add(new JTextField(20));

        JPanel panelBotones = new JPanel();
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        aceptar.addActionListener(e -> {

                });

        cancelar.addActionListener(e -> {
            this.dispose();
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
        new Registro(null);
    }

}
