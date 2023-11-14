package GUI;

import GUI.Escritorio.Registro;

import java.awt.*;
import javax.swing.*;

public class Bienvenido extends JFrame {

    private static final long serialVersionUID = 1L;

    public Bienvenido() {
        this.setTitle("Inicio");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal personalizado con imagen de fondo
        JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/Recuros/Bilbao.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("¡Bienvenid@!", JLabel.CENTER);
        titleLabel.setFont(new Font("Cambria", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE); // Establecer color de texto en blanco

        // Botónes
        JButton inicioSesion = new JButton("Inicio de sesión");
        JButton registrarse = new JButton("Registrarse");
        inicioSesion.setFont(new Font("Cambria", Font.BOLD, 20));
        registrarse.setFont(new Font("Cambria", Font.BOLD, 20));
        Dimension buttonSize = new Dimension(200, 50);
        registrarse.setPreferredSize(buttonSize);
        inicioSesion.setPreferredSize(buttonSize);

        //Logica botones
        registrarse.addActionListener(e -> {
            new Registro(this);
            this.setVisible(false);
        });


        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // El título ocupará 2 columnas
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1; // Restablecer gridwidth a 1 para los botones
        gbc.gridy = 1; // Fila 1
        gbc.gridx = 0; // Columna 0 para "Inicio de sesión"
        panel.add(inicioSesion, gbc);

        gbc.gridx = 1; // Columna 1 para "Registrarse"
        panel.add(registrarse, gbc);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(panel, BorderLayout.CENTER);

        backgroundPanel.add(mainPanel, BorderLayout.SOUTH);

        this.setContentPane(backgroundPanel);
        this.setVisible(true);
    }

    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bienvenido();
        });
    }
}