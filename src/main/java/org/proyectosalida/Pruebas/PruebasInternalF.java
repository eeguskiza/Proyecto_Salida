package org.proyectosalida.Pruebas;

import javax.swing.*;
import java.awt.*;

public class  PruebasInternalF extends JFrame {

    public PruebasInternalF() {
        super("PruebasInternalF");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creamos un JDesktopPane para contener los JInternalFrames
        JDesktopPane desktopPane = new JDesktopPane();
        setContentPane(desktopPane); // Establecemos el JDesktopPane como contenido del JFrame

        // Internal Frame de pruebas para botón "salir"
        JInternalFrame internalFrame = new JInternalFrame("Internal Frame", true, true, true, true);
        internalFrame.setSize(200, 200);
        internalFrame.setLocation(100, 100);
        internalFrame.setBackground(Color.RED);

        // Agregamos el InternalFrame al JDesktopPane
        desktopPane.add(internalFrame);
        internalFrame.setVisible(true);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PruebasInternalF();
        });
    }
}
