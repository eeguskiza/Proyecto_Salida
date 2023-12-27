package org.proyectosalida.GUI.VentanasCliente;

import org.proyectosalida.Constructores.Visita;
import org.proyectosalida.Datos.AlmacenDeDatos;

import javax.swing.*;
import java.awt.*;

public class RegistrarValoracion extends JFrame {

    public RegistrarValoracion(Visita visita, AlmacenDeDatos almacen, MainMenuCliente mm){
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new JLabel("Introduzca su valoración para "+visita.getLocal().getNombre().toUpperCase()+" ("+visita.getFecha()+")"), BorderLayout.NORTH);
        JTextField tvaloracion = new JTextField(); add(tvaloracion, BorderLayout.CENTER);
        JButton registrar = new JButton("Registrar Valoracion"); add(registrar, BorderLayout.SOUTH);

        registrar.addActionListener(e -> {
            String valoracion =tvaloracion.getText();
            visita.setValoracion(valoracion);
            almacen.actualizarValoracionVisita(visita.getId(), valoracion);
            dispose();
            //Recargar pantalla principal para ver cambios
            mm.recargarTablaReview();
        });

        setVisible(true);
    }
}
