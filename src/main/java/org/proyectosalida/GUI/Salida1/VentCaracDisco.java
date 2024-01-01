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
        panelPrincipal.add(new JTextField(local.getNombre()){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("Dirección:"));
        panelPrincipal.add(new JTextField(local.getDireccion()){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("Código Postal:"));
        panelPrincipal.add(new JTextField(local.getCP()){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("Aforo:"));
        panelPrincipal.add(new JTextField(Integer.toString(local.getAforo())){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("Teléfono:"));
        panelPrincipal.add(new JTextField(local.getTelefono()){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("Media de Edad:"));
        panelPrincipal.add(new JTextField(Integer.toString(local.getMediaEdad())){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("Precio Medio:"));
        panelPrincipal.add(new JTextField(Integer.toString(local.getPrecioMedio())){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("Web:"));
        panelPrincipal.add(new JTextField(local.getWeb()){{
            setEditable(false);
        }});

        panelPrincipal.add(new JLabel("DJ Residente:"));
        String nombre1 = "";
        if(((Discoteca)local).getDjResidente() != null){
            nombre1 = ((Discoteca)local).getDjResidente().getNombre();
        }
        panelPrincipal.add(new JTextField(nombre1){{
            setEditable(false);
        }});


        panelPrincipal.add(new JLabel("DJ Invitado:"));
        String nombre2 = "";
        if(((Discoteca)local).getDjInvitado() != null){
            nombre2 = ((Discoteca)local).getDjInvitado().getNombre();
        }
        panelPrincipal.add(new JTextField(nombre2){{
            setEditable(false);
        }});


        panelPrincipal.add(new JLabel("Características:"));
        panelPrincipal.add(new JTextField(obtenerCaracteristicas(local.getCaracteristicas())){{
            setEditable(false);
        }});
        // Botón de Aceptar
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> dispose()); // Cierra la ventana al hacer clic en Aceptar
        panelPrincipal.add(btnAceptar);

        // Agregar el panel principal al JFrame
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
        setTitle("Vista Previa de Discoteca");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width - getSize().width - 20, (screenSize.height - getSize().height) /2 -50); //para poder abrir el mapa junto a esta ventana lo pongo en una esquina
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
            // Crear una instancia de Discoteca para probar la ventana
            new VentCaracDisco(new Discoteca());
        });
    }
}