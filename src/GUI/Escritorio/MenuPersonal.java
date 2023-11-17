package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPersonal extends JFrame {

    public MenuPersonal(String nombreUsuario) {
        setTitle("Menú Personal - Usuario: " + nombreUsuario);
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel pEncabezado = new JPanel(new GridLayout(1,2)); add(pEncabezado, BorderLayout.NORTH);
        // Panel para la imagen a la izquierda
        JPanel panelIzquierdo = new JPanel(new FlowLayout());
        ImageIcon imagen = new ImageIcon("src/Recuros/default_profile.png"); // Reemplaza con la ruta de tu imagen
        Image imageScaled = imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajusta el tamaño de la imagen
        ImageIcon imagenEscalada = new ImageIcon(imageScaled);
        JLabel etiquetaImagen = new JLabel(imagenEscalada);
        panelIzquierdo.add(etiquetaImagen);

        // Panel para el nombre y apellidos a la derecha
        JPanel panelDerecho = new JPanel(new GridLayout(2,1));
        JLabel nombreLabel = new JLabel(nombreUsuario);
        JLabel apellidosLabel = new JLabel("Pérez González");
        panelDerecho.add(nombreLabel);
        panelDerecho.add(apellidosLabel);

        pEncabezado.add(panelIzquierdo);
        pEncabezado.add(panelDerecho);

        JLabel label1 = clickableLabel("Editar Perfil");
        JLabel label2 = clickableLabel("Ajustes");
        JLabel label3 = clickableLabel("Lista de Visitados");
        JLabel label4 = clickableLabel("Proximos Eventos");
        JLabel label5 = clickableLabel("Bares Nuevos");
        JLabel label6 = clickableLabel("MÁS");

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JLabel clickableLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK); // Cambia el color del texto para que se vea como un enlace
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambia el cursor al pasar por encima como feedback visual

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Has hecho click en: " + text);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambia el color del texto a azul cuando el cursor está encima
                label.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaura el color del texto a negro cuando el cursor sale de la etiqueta
                label.setForeground(Color.BLACK);
            }
        });


        return label;
    }

    //Main de prueba
    public static void main(String[] args) {
        MenuPersonal nuevo = new MenuPersonal(null);
        nuevo.setVisible(true);
    }

}
