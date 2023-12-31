package org.proyectosalida.GUI.VentanasGenerales;
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

    public VentanaPreguntasFrecuentes(JFrame padre){
        initialize(padre);
        initializePreguntas();
    }


    private void initialize(JFrame padre) {
        frame = new JFrame();
        frame.setTitle(" Preguntas frecuentes");
        frame.setSize(1400, 700);
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

        sp = new JScrollPane(pPrincipal);
        frame.getContentPane().add(sp, BorderLayout.CENTER);


        pBotonVolver = new JPanel();
        frame.getContentPane().add(pBotonVolver, BorderLayout.SOUTH);

        btnBotonVolver = new JButton("Volver");
        btnBotonVolver.setVerticalAlignment(SwingConstants.BOTTOM);
        btnBotonVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        pBotonVolver.add(btnBotonVolver);

        btnBotonVolver.addActionListener(e -> {
            frame.dispose();
            padre.setVisible(true);
        });
        frame.setVisible(true);
    }

    private void initializePreguntas() {
        preguntas = new JLabel[6];
        respuestaPanels = new JPanel[6];
        respuestas = new JLabel[6];

        // Crea preguntas y respuestas
        preguntas[0] = new JLabel(" + ¿Cómo registro un local en Proyecto Salido?");
        preguntas[1] = new JLabel(" + ¿Cómo pueden los clientes encontrar locales adecuados para salir?");
        preguntas[2] = new JLabel(" + ¿Cómo puedo editar la información de mi local registrado?");
        preguntas[3] = new JLabel(" + ¿Qué son las \"visitas\" en Proyecto Salido?");
        preguntas[4] = new JLabel(" + ¿Cómo edito mis datos personales en Proyecto Salido?");
        preguntas[5] = new JLabel(" + ¿Cómo se manejan los horarios de funcionamiento de los locales en Proyecto Salido?");

        for (JLabel pregunta : preguntas) {
            pregunta.setFont(new Font("Arial Nova 13", Font.BOLD, 14));
        }

        respuestas[0] = new JLabel("       Los dueños pueden registrar un local en Proyecto Salido proporcionando detalles como nombre, tipo de local (bar, discoteca), horarios, características especiales (terrazas, DJs, etc.) y palabras clave relevantes (Pintxos, Servicio de Terraza, Cocteles,etc.).");
        respuestas[1] = new JLabel("       Los clientes pueden buscar locales utilizando filtros como tipo de local, palabras clave, días y horarios disponibles. El sistema recomendará una lista de locales en función de estas preferencias.");
        respuestas[2] = new JLabel("       Los dueños pueden acceder a la información de su local y realizar ediciones en horarios, características especiales o detalles generales a través de la opción de 'Añadir/Modificar' para los locales.");
        respuestas[3] = new JLabel("       Las \"visitas\" son registros que los clientes crean cuando eligen un local para salir. Estos registros se almacenan para proporcionar datos sobre los lugares más visitados y preferidos por los usuarios.");
        respuestas[4] = new JLabel("       Tanto los dueños como los clientes pueden editar sus datos personales accediendo a la opción de ajustes de perfil, donde pueden modificar información como nombre, dirección, contraseña, etc.");
        respuestas[5] = new JLabel("       Los dueños pueden establecer y modificar los horarios de apertura y cierre de sus locales para cada día de la semana a través de la plataforma. Los clientes pueden acceder a esta información al buscar locales disponibles para planificar sus salidas.");

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
