package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPersonal extends JFrame {

    public MenuPersonal(String nombreUsuario, MainMenu padre) {
        setTitle("Menú Personal - Usuario: " + nombreUsuario);
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel principal con bordes y disposición de cuadrícula
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Encabezado con la imagen del usuario y sus datos
        JPanel pEncabezado = new JPanel(new BorderLayout(10, 0));
        add(pEncabezado, BorderLayout.NORTH);

        // Panel para la imagen del usuario
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        ImageIcon imagen = new ImageIcon("src/Recuros/default_profile.png"); // Ruta de tu imagen
        Image imageScaled = imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenEscalada = new ImageIcon(imageScaled);
        JLabel etiquetaImagen = new JLabel(imagenEscalada);
        panelIzquierdo.add(etiquetaImagen);

        // Panel para el nombre y apellidos del usuario
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel nombreLabel = new JLabel(nombreUsuario, SwingConstants.CENTER);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel apellidosLabel = new JLabel("Pérez González", SwingConstants.CENTER);
        apellidosLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelDerecho.add(nombreLabel);
        panelDerecho.add(apellidosLabel);

        pEncabezado.add(panelIzquierdo, BorderLayout.WEST);
        pEncabezado.add(panelDerecho, BorderLayout.CENTER);

        // Creación de etiquetas clickeables
        panel.add(clickableLabel("Editar Perfil"));
        panel.add(clickableLabel("Ajustes"));
        panel.add(clickableLabel("Lista de Visitados"));
        panel.add(clickableLabel("Próximos Eventos"));
        panel.add(clickableLabel("Bares Nuevos"));
        //Boton Atras
        JButton atras = new JButton("Atras");
        atras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                padre.setVisible(true);
            }
        });
        panel.add(atras);


        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JLabel clickableLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(245, 245, 245));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Has hecho click en: " + text);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(new Color(245, 245, 245));
            }
        });

        return label;
    }

    public static void main(String[] args) {
        // Configuración del look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MenuPersonal("Nombre de Usuario", null).setVisible(true));
    }
}
