package org.proyectosalida.GUI.VentanasCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import com.toedter.calendar.JCalendar;
import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Constructores.Salida;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.Constructores.Cliente;
import com.formdev.flatlaf.*;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import org.proyectosalida.GUI.Salida2.VentSelectCarac;
import org.proyectosalida.Pruebas.MenuPersonal;


public class MainMenuCliente extends JFrame {

    private JSplitPane splitPane;
    private JSplitPane splitPane2;
    public AlmacenDeDatos almacen;
    private JLabel labelEncabezado;
    private ArrayList<Caracteristica> caracteristicasSeleccionadas;

    public MainMenuCliente(AlmacenDeDatos almacenDeDatos, String url) {
        setTitle("Main Menu");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        caracteristicasSeleccionadas = new ArrayList<>();
        almacen = almacenDeDatos;
        Cliente usuario = almacen.getCliente();

        // Panel para el botón del menú
        JPanel panelMenu = new JPanel(new BorderLayout());
        JButton menuButton = new JButton();
        JButton salimos = new JButton("¿Salimos?");

        // Configuración del botón menu
        ImageIcon menu_icon = new ImageIcon("src/main/resources/images/menu_bar.png"); Image menu_image = menu_icon.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        menuButton.setBackground(Color.decode("#B4D3B2")); // Color personalizado
        menuButton.setIcon(new ImageIcon(menu_image));
        menuButton.setOpaque(true);
        menuButton.setBorderPainted(true); // Sin borde
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(true);
        menuButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuButton.setPreferredSize(new Dimension(40, 40)); // Tamaño personalizado

        // Configuración del botón salimos
        salimos.setBackground(Color.decode("#B4D3B2")); // Color personalizado
        salimos.setOpaque(true);
        salimos.setBorderPainted(true); // Sin borde
        salimos.setFocusPainted(false);
        salimos.setContentAreaFilled(true);
        salimos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        salimos.setPreferredSize(new Dimension(150, 40)); // Tamaño personalizado

        //Este listene comun para todos, Alex escribe tu metodo y yo el mio de chill
        salimos.addActionListener(e -> {
            dispose();

            int opcion = JOptionPane.showOptionDialog(
                    new JFrame(),
                    "¿Quieres salir hoy o en otro día?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Hoy", "Otro día"},
                    "Hoy"
            );

            if (opcion == JOptionPane.YES_OPTION) {
                Date fechaHoy = new Date();
                System.out.println("El usuario quiere salir hoy: " + fechaHoy);

                Salida salida = new Salida(almacen.getCliente(), almacen.getCaracteristicas(), fechaHoy, null);
                System.out.println(salida.toString());
                VentSelectCarac v = new VentSelectCarac(caracteristicasSeleccionadas, false, almacenDeDatos, salida); //Le paso la salida para obtener los valores en la tabla de elecciones tambien
            } else if (opcion == JOptionPane.NO_OPTION) {
                JCalendar calendar = new JCalendar();
                int result = JOptionPane.showConfirmDialog(null, calendar, "Seleccionar fecha", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    Date fechaElegida = calendar.getDate();
                    System.out.println("El usuario quiere salir otro día: " + fechaElegida);

                    Salida salida = new Salida(almacen.getCliente(), almacen.getCaracteristicas(), fechaElegida, null);
                    System.out.println(salida.toString());
                    VentSelectCarac v = new VentSelectCarac(caracteristicasSeleccionadas, false, almacenDeDatos, salida);
                } else {
                    System.out.println("No se ha seleccionado una opción");
                }
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MenuPersonal(almacenDeDatos.getUsuarios().get(0), almacenDeDatos, MainMenuCliente.this);
            }
        });

        // Añadir el botón al panel del menú
        panelMenu.add(menuButton, BorderLayout.WEST);
        panelMenu.setBackground(Color.WHITE); // Fondo del panel

        panelMenu.add(salimos, BorderLayout.EAST);
        panelMenu.setBackground(Color.WHITE); // Fondo del panel

        add(panelMenu, BorderLayout.NORTH);



        // --------------- MAPA ------------------
        JPanel panelMapa = new JPanel(new BorderLayout());
        panelMapa.setBackground(Color.BLUE);
        BrowserView view = cargarMapa(url);
        panelMapa.add(view, BorderLayout.CENTER);


 
        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.RED);

        // Initialize the splitPane
        splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelMapa, bottomPanel);
        splitPane2.setResizeWeight(0.7);
        splitPane2.setDividerSize(1);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane2, bottomPanel);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerSize(1);
        add(splitPane, BorderLayout.CENTER);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        bottomPanel.setLayout(new GridLayout(1,3));

        //PANEL 1 - ENCUESTAS
        // Panel para las encuestas
        JPanel panel1Encuesta = new JPanel(new BorderLayout());
        // Crear la etiqueta y configurar el texto centrado
        labelEncabezado = new JLabel("¿A dónde sales hoy tú?");
        labelEncabezado.setFont(new Font("Arial", Font.BOLD, 20));

        labelEncabezado.setHorizontalAlignment(SwingConstants.CENTER);

        // Añadir la etiqueta al panel en la parte superior (Norte)
        panel1Encuesta.add(labelEncabezado, BorderLayout.NORTH);


        // Panel para contener las encuestas con un GridLayout para 6 elementos
        int rows = 0;
        if(almacenDeDatos.getLocales().size() > 6){
            rows = 6;
        }else{
            rows = almacenDeDatos.getLocales().size();
        }
        JPanel pEncuesta = new JPanel(new GridLayout(rows, 1)); // 6 filas, 1 columna
        panel1Encuesta.add(pEncuesta, BorderLayout.WEST);




       //TODO FALTA SABER ESCOGER LOS 6 LOCALES CON NUMEROS MAS GRANDES DEL HASHMAP YA QUE EL LIMITE SON 6 LOCALES


        ArrayList<Local> localesSeleccionadosParaPoll = new ArrayList<>();
        for(int valorPoll : almacenDeDatos.getValoresVotaciones().values()){

        }
        for(int i=0; i<=rows; i++){
            pEncuesta.add(crearPanelEncuesta(localesSeleccionadosParaPoll.get(i), 10));
        }



        //PANEL 2 - REVIEWS
        JPanel panel2Reviews = new JPanel();
        panel2Reviews.setBackground(Color.pink);

        //PANEL 3 - PROMOCIONES
        JPanel panel3Promociones = new JPanel();
        panel3Promociones.setBackground(Color.yellow);

        bottomPanel.add(panel1Encuesta); bottomPanel.add(panel2Reviews); bottomPanel.add(panel3Promociones);

        // Set the frame visible
        this.setVisible(true);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            splitPane.setDividerLocation(0.6);
        }
    }


    private void updateProgressBarColor(JProgressBar progressBar) {
        int value = progressBar.getValue();
        int maximum = progressBar.getMaximum();

        if (value < maximum / 3) {
            progressBar.setForeground(new Color(76, 175, 80)); // Verde
        } else if (value < 2 * maximum / 3) {
            progressBar.setForeground(new Color(255, 193, 7)); // Amarillo
        } else {
            progressBar.setForeground(new Color(244, 67, 54)); // Rojo
        }
    }

    private JPanel crearPanelEncuesta(Local local, int maxValor) { //CREA EL PANEL CON TITULO Y PROGRESSBAR CON SU CORRESP ACTION LISTENER
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String nombre = local.getNombre();
        String id = local.getId();
        panel.add(new JLabel(nombre));
        JProgressBar progressBar = new JProgressBar(0, maxValor);
        progressBar.setValue(almacen.getValoresVotaciones().get(id));

        progressBar.setPreferredSize(new Dimension(150, 20));
        progressBar.setMinimumSize(new Dimension(150, 20));

        panel.add(progressBar);
        return panel;
    }


    private BrowserView cargarMapa(String url){
        // Configuración de JxBrowser
        String LICENSE_KEY = "1BNDHFSC1G8G98OJJHDTF78DD0CDDPDLQF4P63L618XNXGIMI8JN9NMU9C1PKN6NQLIHWK";
        EngineOptions options = EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED)
                .licenseKey(LICENSE_KEY)
                .build();

        // Inicializando el motor del navegador con la clave de licencia
        Engine engine = Engine.newInstance(options);

        // Creando el navegador
        Browser browser = engine.newBrowser();

        // Crear la vista del navegador para Swing
        BrowserView view = BrowserView.newInstance(browser);

        if(url == null){
            url = "https://www.google.es/maps/preview";
        }
        // Cargando la URL en el navegador
        browser.navigation().loadUrl(url);
        return view;
    }

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción o dejar que se use el look and feel predeterminado
        }

        SwingUtilities.invokeLater(() -> {
            new MainMenuCliente(new AlmacenDeDatos(), null);
        });
    }

}
