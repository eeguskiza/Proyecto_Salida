package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;
import org.proyectosalida.Pruebas.MenuPersonal;

import javax.swing.*;

public class MainMenuDueño extends JFrame {
    public MainMenuDueño(AlmacenDeDatos almacenDeDatos){
        this.setTitle("Menú Principal");
        this.setSize(640, 360);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JButton menuPersonal = new JButton("Menu Personal");
        menuPersonal.addActionListener(e -> {
            setVisible(false);
            new MenuPersonal(almacenDeDatos.getUsuarios().get(0),almacenDeDatos, this);
        });
        add(menuPersonal);

        //add(new JLabel("Hola, "+ almacenDeDatos.getDueño().getNombre() + " (DUEÑO)"));
    }
}
