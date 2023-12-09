package org.proyectosalida.GUI.Salida1;

import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Constructores.DJ;
import org.proyectosalida.Constructores.Discoteca;
import org.proyectosalida.Constructores.Local;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentCaracDisco extends JFrame {
    public VentCaracDisco(Local local){


        // Crear y configurar el panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(12, 2, 10, 10));

        // Labels y TextFields para cada característica específica de la discoteca
        panelPrincipal.add(new JLabel("Nombre:"));
        panelPrincipal.add(new JTextField(local.getNombre()));

        panelPrincipal.add(new JLabel("Dirección:"));
        panelPrincipal.add(new JTextField(local.getDireccion()));

        panelPrincipal.add(new JLabel("Código Postal:"));
        panelPrincipal.add(new JTextField(local.getCP()));

        panelPrincipal.add(new JLabel("Aforo:"));
        panelPrincipal.add(new JTextField(Integer.toString(local.getAforo())));

        panelPrincipal.add(new JLabel("Teléfono:"));
        panelPrincipal.add(new JTextField(local.getTelefono()));

        panelPrincipal.add(new JLabel("Media de Edad:"));
        panelPrincipal.add(new JTextField(Integer.toString(local.getMediaEdad())));

        panelPrincipal.add(new JLabel("Precio Medio:"));
        panelPrincipal.add(new JTextField(Integer.toString(local.getPrecioMedio())));

        panelPrincipal.add(new JLabel("Web:"));
        panelPrincipal.add(new JTextField(local.getWeb()));
/*
        panelPrincipal.add(new JLabel("DJ Residente:"));
        panelPrincipal.add(new JTextField(local.getDjResidente().getNombre()));

        panelPrincipal.add(new JLabel("DJ Invitado:"));
        panelPrincipal.add(new JTextField(local.getDjInvitado().getNombre()));


 */
        panelPrincipal.add(new JLabel("Características:"));
        panelPrincipal.add(new JTextField(obtenerCaracteristicas(local.getCaracteristicas())));
        // Botón de Aceptar
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> dispose()); // Cierra la ventana al hacer clic en Aceptar
        panelPrincipal.add(btnAceptar);

        // Agregar el panel principal al JFrame
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
        setTitle("Vista Previa de Discoteca");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private String obtenerCaracteristicas(ArrayList<Caracteristica> caracteristicas) {
        StringBuilder builder = new StringBuilder();
        for (Caracteristica caracteristica : caracteristicas) {
            builder.append(caracteristica.toString()).append(", ");
        }
        // Eliminar la coma y el espacio al final
        return builder.substring(0, builder.length() - 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear una instancia de Discoteca para probar la ventana
            new VentCaracDisco(null);
        });
    }
}