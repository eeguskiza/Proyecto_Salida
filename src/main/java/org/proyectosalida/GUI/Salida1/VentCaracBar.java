package org.proyectosalida.GUI.Salida1;

import org.proyectosalida.Constructores.Bar;
import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Constructores.Local;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentCaracBar extends JFrame {
    public VentCaracBar(Local local) {
        // Configuración del JFrame


        // Crear y configurar el panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(10, 2, 10, 10));

        // Labels y TextFields para cada característica del local
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
        panelPrincipal.add(new JLabel("Terraza:"));
        panelPrincipal.add(new JTextField(Boolean.toString(local.isTerraza())));


 */
        panelPrincipal.add(new JLabel("Características:"));
        panelPrincipal.add(new JTextField(obtenerCaracteristicas(local.getCaracteristicas())));

        // Botón de Aceptar
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> dispose()); // Cierra la ventana al hacer clic en Aceptar
        panelPrincipal.add(new JPanel());
        panelPrincipal.add(btnAceptar);

        // Agregar el panel principal al JFrame
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
        setSize(400,600);
        setTitle("Características del Local");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width - getSize().width - 20, (screenSize.height - getSize().height) /2 -70); //para poder abrir el mapa junto a esta ventana lo pongo en una esquina
        setAlwaysOnTop(true);
    }

    private String obtenerCaracteristicas(ArrayList<Caracteristica> caracteristicas) {
        StringBuilder builder = new StringBuilder();
        for (Caracteristica caracteristica : caracteristicas) {
            builder.append(caracteristica.toString()).append(", ");
        }
        // Eliminar la coma y el espacio al final
        if(!builder.isEmpty()){
            return builder.substring(0, builder.length() - 2);
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            new VentCaracBar(new Bar());
        });
    }
}