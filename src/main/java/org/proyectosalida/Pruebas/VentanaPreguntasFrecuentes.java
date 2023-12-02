package org.proyectosalida.Pruebas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class VentanaPreguntasFrecuentes {

    JFrame frame;

    private JPanel pTitulo;
    private JPanel pPrincipal;
    private JPanel pBotonVolver;
    private JPanel pTituloRelleno;
    private JPanel pPrincipalRelleno;

    private JLabel lblTitulo;
    private JLabel lblTituloDescripcion;

    private JButton btnBotonVolver;

    private JScrollPane sp;
    private JFrame padre;



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPreguntasFrecuentes window = new VentanaPreguntasFrecuentes(null);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentanaPreguntasFrecuentes(JFrame p ) {
        padre = p;
        initialize();
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

        lblTituloDescripcion = new JLabel("     Aquí encontrarás algunas de las preguntas mas frecuentes de los usuarios que podrían serte de utilidad.");
        lblTituloDescripcion.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
        pTitulo.add(lblTituloDescripcion);

        pPrincipal = new JPanel();
        frame.getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new GridLayout(6, 1));

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

        btnBotonVolver.addActionListener(e -> {
            frame.dispose();
            padre.setVisible(true);
        });
    }

}

/*
 * 	POTENCIALES PREGUNTAS PARA EL APARTADO DE PREGUNTAS FRECUENTES (habría que añadir respuestas)
 * 	(las respuestas con un tabulado de mas son buenas opciones)
 *
 *	¿Cómo creo mi perfil personalizado en la aplicación?
 *
 *	¿Cómo puedo añadir mis preferencias y gustos para obtener recomendaciones más precisas?
 *
 *		¿Puedo cambiar mis preferencias en cualquier momento?
 *		(aquí explicamos el funcionamiento de las ventanas
 *
 *		¿La aplicación tiene en cuenta las restricciones dietéticas o alergias alimentarias en las recomendaciones de restaurantes?
 *
 *	¿Cómo se generan las recomendaciones de lugares para salir?
 *
 *	¿La aplicación también sugiere eventos o actividades específicas en Bilbao, además de lugares para salir?
 *
 *	¿Puedo ver reseñas de otros usuarios sobre los lugares recomendados?
 *
 *	¿La aplicación ofrece descuentos o promociones especiales en los lugares recomendados?
 *
 *		¿Puedo guardar mis lugares favoritos o lugares que quiero visitar más adelante?
 *
 *		¿La aplicación está disponible en varios idiomas? (SOLO SI AL FINAL SE DECIDE HACER ESTO)
 *
 *	¿Cómo puedo compartir mis experiencias o reseñas sobre los lugares que visité a través de la aplicación?
 *
 *	¿Hay alguna función para buscar lugares cercanos en tiempo real, en caso de que cambie de ubicación en la ciudad?
 *
 *	¿Cómo puedo reportar problemas o errores en la aplicación?
 *
 *		¿La aplicación tiene alguna función de navegación integrada para llegar a los lugares recomendados?
 *
 *		¿Puedo personalizar la frecuencia de las recomendaciones, como recibir sugerencias diarias o semanales?
 *
 *	¿Cómo se protege la privacidad de mi información en la aplicación?
 *
 *		¿La aplicación incluye opciones para grupos o eventos especiales, como reservas para cumpleaños o reuniones?
 *		(aqui explicamos que la aplicación no es para hacer reservas pero que facilitamos los datos de contecto del establecimiento para poder hacerlo)
 *
 *		¿Cómo puedo filtrar las recomendaciones por precio, tipo de cocina o ambiente del lugar?
 *		(aqui se explica el funcionamiento del menú de filtrado en ventanaSalimos)
 *
 *		¿Hay algún costo asociado con el uso de la aplicación o es gratuita?
 *		(de esta no estoy segura pero para hacernos los chulos de que es gratuita???)
 *
 *	¿La aplicación está conectada a redes sociales para compartir planes con amigos o ver las recomendaciones de amigos?
 *
 */
