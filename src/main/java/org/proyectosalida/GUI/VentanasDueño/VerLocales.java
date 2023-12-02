package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.*;

import javax.swing.*;

public class VerLocales extends JFrame {

    public VerLocales(Dueño dueño){

        for (Local local : dueño.getLocales()){
            System.out.println(local.getNombre());
        }
    }
}
