package org.proyectosalida.GUI;

import javax.swing.*;
import java.awt.*;

public class CustomOptionPaneLogin extends JFrame {

    private JDialog dialog;

    public CustomOptionPaneLogin(String texto) {
        createDialog(texto); // Método para crear el diálogo
    }

    private void createDialog(String texto) {
        // Crear un nuevo JDialog
        dialog = new JDialog();
        dialog.setTitle("Cargando");

        // Crear un panel para colocar el mensaje y la barra de progreso
        JPanel panel = new JPanel(new BorderLayout());

        // Crear la barra de progreso
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Barra de progreso indeterminada
        panel.add(progressBar, BorderLayout.SOUTH);

        // Agregar el mensaje al panel
        JPanel panelPanelTexto = new JPanel(new GridLayout(3, 1));
        panelPanelTexto.add(new JPanel());
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel panelTexto = new JPanel(new FlowLayout());
        panelTexto.add(label);
        panelPanelTexto.add(panelTexto);
        panelPanelTexto.add(new JPanel());
        panel.add(panelPanelTexto, BorderLayout.CENTER);

        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centrar en la pantalla
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(null);
    }

    public void show() {
        dialog.setVisible(true); // Método para mostrar el diálogo
    }

    public void dispose() {
        dialog.dispose(); // Método para cerrar el diálogo
    }

    public static void main(String[] args) {
        CustomOptionPaneLogin customOptionPaneLogin = new CustomOptionPaneLogin("");
        customOptionPaneLogin.show(); // Mostrar el diálogo desde la clase misma
    }
}
