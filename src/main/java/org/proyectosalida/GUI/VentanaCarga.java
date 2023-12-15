package org.proyectosalida.GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class VentanaCarga extends JFrame {
    private JProgressBar progressBar;
    private JLabel labelPorcentaje;

    public VentanaCarga() {
        super("Ventana de Carga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 150);
        setLocationRelativeTo(null);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.ITALIC, 20));
        UIManager.put("ProgressBar.foreground", Color.gray); // Cambia el color de la barra de progreso
        progressBar.setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() {
                return Color.black; // Cambia el color de la barra de progreso
            }

            protected Color getSelectionForeground() {
                return Color.WHITE; // Cambia el color del texto
            }
        });



        labelPorcentaje = new JLabel("Cargando recursos...");
        labelPorcentaje.setFont(new Font("Arial", Font.BOLD, 13));
        labelPorcentaje.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(labelPorcentaje, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        simularCarga();
    }

    private void simularCarga() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int porcentaje = 0;

            @Override
            public void run() {
                if (porcentaje <= 100) {
                    progressBar.setValue(porcentaje);
                    porcentaje++;
                } else {
                    timer.cancel();
                    dispose();
                    Bienvenido nuevo = new Bienvenido();
                    nuevo.setVisible(true);
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 50);
    }

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            new VentanaCarga();
        });
    }
}