package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;

import javax.swing.*;

public class MainMenuDueño extends JFrame {
    public MainMenuDueño(AlmacenDeDatos almacenDeDatos){
        this.setTitle("Menú Principal");
        this.setSize(640, 360);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        add(new JLabel("Hola, "+ almacenDeDatos.getDueño().getNombre() + " (DUEÑO)"));
    }
}
