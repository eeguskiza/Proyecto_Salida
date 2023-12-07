package org.proyectosalida.GUI.Salida1;

import org.proyectosalida.Constructores.Local;

import javax.swing.*;

public class VentCaracLocal extends JFrame {
    public VentCaracLocal(Local local) {


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentCaracLocal(null);
            }
        });
    }
}
