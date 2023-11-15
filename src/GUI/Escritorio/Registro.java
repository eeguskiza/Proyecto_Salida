package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

import Constructores.*;
import GUI.Movil.VentanaInicio;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

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
        tipoUsuario.add(dueño);

        JPanel panelTipoUsuario = new JPanel();
        panelTipoUsuario.add(cliente);
        panelTipoUsuario.add(dueño);
        panel.add(panelTipoUsuario);

        panel.add(new JLabel("ID:", JLabel.CENTER));
        panel.add(new JTextField("@",20));

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
            String id = ((JTextField) panel.getComponent(3)).getText();
            String nombre = ((JTextField) panel.getComponent(5)).getText();
            String apellido = ((JTextField) panel.getComponent(7)).getText();
            String fechaNacimiento = null;

            // Fecha check
            if (dateChooser.getDate() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                fechaNacimiento = dateFormat.format(dateChooser.getDate());
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de nacimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String contraseña = new String(((JPasswordField) panel.getComponent(11)).getPassword());
            String confirmarContraseña = new String(((JPasswordField) panel.getComponent(13)).getPassword());
            String telefono = ((JTextField) panel.getComponent(15)).getText();
            String correo = ((JTextField) panel.getComponent(17)).getText();


            if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                    contraseña.isEmpty() || !contraseña.equals(confirmarContraseña) ||
                    telefono.isEmpty() || correo.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Por favor, revise todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    if (dueño.isSelected()) {
                        Dueño nuevoUsuario = new Dueño(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo, new ArrayList<>());
                        System.out.println(nuevoUsuario.toString());
                    } else if (cliente.isSelected()) {
                        Cliente nuevoUsuario = new Cliente(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo, new ArrayList<>());
                        System.out.println(nuevoUsuario.toString());
                    }

                    JOptionPane.showMessageDialog(this, "Usuario creado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    MainMenu ventanaInicio = new MainMenu();
                    this.dispose();
                    padre.dispose();
                    ventanaInicio.setVisible(true);

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

    public static void main(String[] args) {
        new Registro(null);
    }

}
