import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPreguntasFrecuentes {

    private JFrame frame;

    private JPanel pTitulo;
    private JPanel pPrincipal;
    private JPanel pBotonVolver;
    private JPanel pTituloRelleno;
    private JPanel pPrincipalRelleno;

    private JLabel lblTitulo;
    private JLabel lblTituloDescripcion;

    private JButton btnBotonVolver;

    private JScrollPane sp;

    private JLabel[] preguntas;
    private JPanel[] respuestaPanels;
    private JLabel[] respuestas;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaPreguntasFrecuentes window = new VentanaPreguntasFrecuentes();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaPreguntasFrecuentes() {
        initialize();
        initializePreguntas();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle(" Preguntas frecuentes");
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pTitulo = new JPanel();
        frame.getContentPane().add(pTitulo, BorderLayout.NORTH);
        pTitulo.setLayout(new GridLayout(3, 1));

        pTituloRelleno = new JPanel();
        pTitulo.add(pTituloRelleno);

        lblTitulo = new JLabel("   Preguntas frecuentes");
        lblTitulo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        pTitulo.add(lblTitulo);

        lblTituloDescripcion = new JLabel("     Aquí encontrarás algunas de las preguntas más frecuentes de los usuarios que podrían serte de utilidad.");
        lblTituloDescripcion.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
        pTitulo.add(lblTituloDescripcion);

        pPrincipal = new JPanel();
        frame.getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new GridLayout(14, 1));

        pPrincipalRelleno = new JPanel();
        pPrincipal.add(pPrincipalRelleno);

        sp = new JScrollPane();
        frame.getContentPane().add(sp, BorderLayout.EAST);

        pBotonVolver = new JPanel();
        frame.getContentPane().add(pBotonVolver, BorderLayout.SOUTH);

        btnBotonVolver = new JButton("Volver");
        btnBotonVolver.setVerticalAlignment(SwingConstants.BOTTOM);
        btnBotonVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        pBotonVolver.add(btnBotonVolver);
    }

    private void initializePreguntas() {
        preguntas = new JLabel[6];
        respuestaPanels = new JPanel[6];
        respuestas = new JLabel[6];

        // Crea preguntas y respuestas
        preguntas[0] = new JLabel(" + ¿Puedo cambiar mis preferencias en cualquier momento?");
        preguntas[1] = new JLabel(" + ¿La aplicación tiene en cuenta las restricciones dietéticas o alergias alimentarias en las recomendaciones de restaurantes?");
        preguntas[2] = new JLabel(" + ¿Puedo personalizar la frecuencia de las recomendaciones, como recibir sugerencias diarias o semanales?");
        preguntas[3] = new JLabel(" + ¿La aplicación incluye opciones para grupos o eventos especiales, como reservas para cumpleaños o reuniones?");
        preguntas[4] = new JLabel(" + ¿Cómo puedo filtrar las recomendaciones por precio, tipo de cocina o ambiente del lugar?");
        preguntas[5] = new JLabel(" + ¿Hay algún costo asociado con el uso de la aplicación o es gratuita?");

        for (JLabel pregunta : preguntas) {
            pregunta.setFont(new Font("Arial Nova 13", Font.BOLD, 14));
        }

        respuestas[0] = new JLabel("       Añadir respuesta explicando el funcionamiento de la ventana y del editar perfil");
        respuestas[1] = new JLabel("       Añadir respuesta sobre que los locales tienen características como comida vegetariana, celíaca, vegana, etc. para poder buscarlos teniendo en cuenta esa necesidad del usuario");
        respuestas[2] = new JLabel("       Añadir respuesta explicando el funcionamiento de la ventanaGestionNotificaciones y sus utilidades de cara a personalizar las preferencias de cada usuario sobre las notificaciones.");
        respuestas[3] = new JLabel("       Aquí explicamos que la aplicación no es para hacer reservas, pero facilitamos los datos de contacto del establecimiento para poder hacerlo");
        respuestas[4] = new JLabel("       Aquí volvemos a explicar la ventana de características (como en la pregunta de las restricciones alimentarias)");
        respuestas[5] = new JLabel("       Aquí explicamos que la aplicación no tiene ningún tipo de costo y es gratuita (para hacernos los chulos)");

        for (JLabel respuesta : respuestas) {
            respuesta.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
        }

        for (int i = 0; i < preguntas.length; i++) {
            int finalI = i;
            preguntas[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toggleRespuesta(finalI);
                }
            });

            respuestaPanels[i] = new JPanel();
            respuestaPanels[i].setLayout(new BorderLayout());
            respuestaPanels[i].add(respuestas[i], BorderLayout.CENTER);

            pPrincipal.add(preguntas[i]);
            pPrincipal.add(respuestaPanels[i]);
            respuestaPanels[i].setVisible(false);  // Oculta el contenido adicional inicialmente
        }
    }

    private void toggleRespuesta(int index) {
        // Obtiene el estado actual de visibilidad del contenido adicional
        boolean isVisible = respuestaPanels[index].isVisible();

        // Cambia la visibilidad del contenido adicional
        respuestaPanels[index].setVisible(!isVisible);

        // Cambia el texto del JLabel según la visibilidad actual
        if (isVisible) {
            preguntas[index].setText(preguntas[index].getText().replaceFirst("-", "+"));
        } else {
            preguntas[index].setText(preguntas[index].getText().replaceFirst("\\+", "-"));
        }

        // Vuelve a validar y repintar la interfaz gráfica
        frame.revalidate();
        frame.repaint();
    }
}
