package org.proyectosalida.GUI.VentanasCliente;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.toedter.calendar.JCalendar;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import com.formdev.flatlaf.*;
import org.proyectosalida.GUI.Salida1.VentSelectCarac;
import org.proyectosalida.GUI.VentanasGenerales.MenuPersonal;

import static org.proyectosalida.Datos.AlmacenDeDatos.logger;


public class MainMenuCliente extends JFrame {

    private JSplitPane splitPane;
    private JSplitPane splitPane2;
    public AlmacenDeDatos almacen;
    private JLabel labelEncabezado;
    private ArrayList<Caracteristica> caracteristicasSeleccionadas;
    private Cliente usuario;
    private ArrayList<Visita> VSR;
    private DefaultTableModel modelo;
    private final JFXPanel jfxPanel = new JFXPanel();
    private Boolean ventanaIsVisible = true;

    public MainMenuCliente(AlmacenDeDatos almacenDeDatos, String url) {
        setTitle("Main Menu");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        caracteristicasSeleccionadas = new ArrayList<>();
        almacen = almacenDeDatos;
        usuario = (Cliente) almacen.getUsuarios().get(0);
        VSR = new ArrayList<>();

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

        //Este listener comun para todos, Alex escribe tu metodo y yo el mio de chill
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
                logger.info("El usuario quiere salir hoy: " + fechaHoy);

                Salida salida = new Salida((Cliente) almacen.getUsuarios().get(0), almacen.getCaracteristicas(), fechaHoy, null);
                //System.out.println(salida.toString());
                VentSelectCarac v = new VentSelectCarac(caracteristicasSeleccionadas, false, almacenDeDatos, salida); //Le paso la salida para obtener los valores en la tabla de elecciones tambien
                ventanaIsVisible = false;
            } else if (opcion == JOptionPane.NO_OPTION) {
                JCalendar calendar = new JCalendar();
                int result = JOptionPane.showConfirmDialog(null, calendar, "Seleccionar fecha", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    Date fechaElegida = calendar.getDate();
                    logger.info("El usuario quiere salir en otra fecha: " + fechaElegida);

                    Salida salida = new Salida((Cliente) almacen.getUsuarios().get(0), almacen.getCaracteristicas(), fechaElegida, null);
                    VentSelectCarac v = new VentSelectCarac(caracteristicasSeleccionadas, false, almacenDeDatos, salida);
                    ventanaIsVisible=false;
                } else {
                    logger.warning("El usuario ha cancelado la selección de fecha");
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
        JFXPanel jfxPanelMapa = cargarMapa(url); // Llamada al método modificado
        panelMapa.add(jfxPanelMapa, BorderLayout.CENTER); // Añadir JFXPanel a Swing


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


        Border bordeInterno = BorderFactory.createEmptyBorder(5, 15, 5, 15);
        Border bordeExterno = BorderFactory.createEmptyBorder(20, 10, 10, 10);
        Border bordeUltraextremo = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Color colorExterno = new Color(183, 183, 183);
        int sizeTitulos = 15;
        int sizeEncabezadoTexto = 12;


        //PANEL 1 - ENCUESTAS
        // Panel para las encuestas
        JPanel panel1Encuesta = new JPanel(new BorderLayout()); panel1Encuesta.setBorder(bordeUltraextremo);
        JPanel p1 = new JPanel(new BorderLayout()); panel1Encuesta.add(p1, BorderLayout.CENTER);
        p1.setBorder(bordeExterno);
        p1.setBackground(colorExterno);
        // Crear la etiqueta y configurar el texto centrado
        labelEncabezado = new JLabel("SAL Y DESCUBRE QUE HACE EL RESTO!");
        labelEncabezado.setFont(new Font("Arial", Font.BOLD, sizeTitulos));
        labelEncabezado.setHorizontalAlignment(SwingConstants.CENTER);

        // Añadir la etiqueta al panel en la parte superior (Norte)
        p1.add(labelEncabezado, BorderLayout.NORTH);

        // Panel para contener las encuestas con un GridLayout para 6 elementos
        int rows = 0;
        if(almacenDeDatos.getValoresVotaciones().size() > 6){
            rows = 6;
        }else{
            rows = almacenDeDatos.getValoresVotaciones().size();
        }
        JPanel pEncuesta = new JPanel(new GridLayout(rows, 1)); // 6 filas, 1 columna

        pEncuesta.setBorder(bordeInterno);


        int index =0;
        int max = -1;
        int posiblemax = almacen.encontrarValorMaximoVotacion();
        if( posiblemax != -1){
            max = posiblemax;
            logger.info("El valor máximo de votación es: " + max);
        }else{
            logger.warning("No se ha encontrado el valor máximo de votación");
        }

        JPanel panelSinVotaciones = new JPanel(new GridLayout(6, 1));
        for(int i=0; i<6;i++){
            crearPanelEncuesta(panelSinVotaciones, "**********", 0, 10);
        }

        if(almacen.getValoresVotaciones().size() == 0){
            p1.add(panelSinVotaciones, BorderLayout.CENTER);
        }else{
            p1.remove(0);

            p1.add(pEncuesta, BorderLayout.CENTER);
            for (Map.Entry<String, Integer> entry : almacen.valoresVotaciones.entrySet()) {
                String nombre = entry.getKey();
                int valor = entry.getValue();

                crearPanelEncuesta(pEncuesta, nombre, valor, max);
                index++;
            }
            p1.add(labelEncabezado, BorderLayout.NORTH);
            labelEncabezado.setText("LA GENTE EN CAMBIO...");
        }


        //PANEL 2 - REVIEWS
        JPanel panel2Reviews = new JPanel(new BorderLayout()); panel2Reviews.setBorder(bordeUltraextremo);
        JPanel p2 = new JPanel(new BorderLayout()); panel2Reviews.add(p2, BorderLayout.CENTER);
        p2.setBorder(bordeExterno);
        p2.setBackground(colorExterno);
        ArrayList<Visita> visitasConValoracion = new ArrayList<>();


        JLabel labelEncabezado2 = new JLabel("ÚLTIMAS REVIEWS PUBLICADAS:"); p2.add(labelEncabezado2, BorderLayout.NORTH);
        labelEncabezado2.setFont(new Font("Arial", Font.BOLD, sizeTitulos));
        labelEncabezado2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel panelGrid = new JPanel(new GridLayout(8,1)); p2.add(panelGrid, BorderLayout.CENTER);
        panelGrid.setBorder(bordeInterno);

        panelGrid.add(new JLabel("Cargando..."), BorderLayout.CENTER);


        Thread hilo = new Thread() {
            public void run() {
                while (ventanaIsVisible) {
                    SwingUtilities.invokeLater(() -> {
                        panelGrid.removeAll();
                        cargarUltimasReviews(visitasConValoracion, almacenDeDatos, sizeEncabezadoTexto, panelGrid);
                    });
                    try {
                        Thread.sleep(3000); // Esperar 15 segundos
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        hilo.start();




        //PANEL 3 - REVIEWS PENDIENTES
        JPanel panel3ReviewsPendientes = new JPanel(new BorderLayout()); panel3ReviewsPendientes.setBorder(bordeUltraextremo);
        JPanel p3 = new JPanel(new BorderLayout()); panel3ReviewsPendientes.add(p3, BorderLayout.CENTER);
        p3.setBorder(bordeExterno);
        p3.setBackground(colorExterno);

        modelo = new DefaultTableModel();
        JTable tablaReviewsPendientes = new JTable(modelo){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaReviewsPendientes.setBorder(bordeInterno);
        tablaReviewsPendientes.setRowHeight(35);
        JScrollPane scrollReviews = new JScrollPane(tablaReviewsPendientes);
        modelo.setColumnIdentifiers(new Object[]{"LOCAL", "FECHA"});


        int vsr = llenarTablaConReviewsPendientes();
        JLabel labelEncabezado3 = null;
        if(vsr>0){
            labelEncabezado3 = new JLabel("TUS REVIEWS PENDIENTES:");
            p3.add(scrollReviews, BorderLayout.CENTER);
        }else{
            labelEncabezado3 = new JLabel("NO TIENES REVIEWS PENDIENTES!");
        }
        p3.add(labelEncabezado3, BorderLayout.NORTH);
        labelEncabezado3.setFont(new Font("Arial", Font.BOLD, sizeTitulos));
        labelEncabezado3.setHorizontalAlignment(SwingConstants.CENTER);


        tablaReviewsPendientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() ==2){ //DOBLE CLICK ES PARA PODER RELLENAR LA REVIEW
                    int row = tablaReviewsPendientes.rowAtPoint(e.getPoint());
                    Visita visitaseleccionada = VSR.get(row);
                    new RegistrarValoracion(visitaseleccionada, almacen, getThis());
                }
            }
        });


        bottomPanel.add(panel1Encuesta); bottomPanel.add(panel2Reviews); bottomPanel.add(panel3ReviewsPendientes);
        //bottomPanel.setBorder(bordeUltraextremo);

        // Set the frame visible
        this.setVisible(true);
    }

    private void cargarUltimasReviews(ArrayList<Visita> visitasConValoracion, AlmacenDeDatos almacenDeDatos, int sizeEncabezadoTexto, JPanel panelGrid){
        //logger.info("Cargando las últimas reviews publicadas");
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        //Conexion tontorrona para sacar todas las visitas con reviews
        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            visitasConValoracion = almacen.cargarVisitasCliente(conn, null); //CLIENTE EN NULL == SE DESCARGA TODAS LAS VISITAS CON REVIEWS
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        if(visitasConValoracion.size()>0){
            for(int i=0; i<3; i++){
                Visita visita = visitasConValoracion.get(i);
                String userId = visita.getClienteID();
                Usuario usuarioDeLaVisita = almacenDeDatos.buscarUsuarioPorId(userId);
                JLabel labelenunciadoReview = new JLabel(usuarioDeLaVisita.getNombre()+" para "+visita.getLocal().getNombre().toUpperCase()+":");
                labelenunciadoReview.setFont(new Font("Arial", Font.BOLD, sizeEncabezadoTexto));
                panelGrid.add(labelenunciadoReview);
                panelGrid.add(new JLabel(visita.getValoracion()));
                if(i!=2){
                    panelGrid.add(new JPanel());
                }
                panelGrid.revalidate();
                panelGrid.repaint();
            }
        }
    }
    private Integer llenarTablaConReviewsPendientes(){

        int vsr=0;
        int vt =0;
        for(Visita visita : usuario.getVisitas()){
            vt++;
            if(visita.getValoracion() == null){
                Object[] row = {visita.getLocal().getNombre().toUpperCase(), visita.getFecha()};
                modelo.addRow(row);
                VSR.add(visita);
                vsr++;
            }
        }
        logger.info("Se han cargado "+vsr+" reviews pendientes de un total de "+vt+" visitas");
        logger.info("Se han cargado "+vsr+" reviews pendientes de un total de "+vt+" visitas");
        return vsr;
    }

    private MainMenuCliente getThis(){
        return this;
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

    private void crearPanelEncuesta(JPanel panelEnc, String nombre, int valorPB, int maxValor) { //CREA EL PANEL CON TITULO Y PROGRESSBAR CON SU CORRESP ACTION LISTENER
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel(nombre));
        JProgressBar progressBar = new JProgressBar(0, maxValor);
        progressBar.setValue(valorPB);

        progressBar.setPreferredSize(new Dimension(150, 20));
        progressBar.setMinimumSize(new Dimension(150, 20));

        panel.add(progressBar);
        panelEnc.add(panel);
    }


    private JFXPanel cargarMapa(String url) {
        final JFXPanel jfxPanel = new JFXPanel(); // Este panel es el que se integrará en Swing

        Platform.runLater(() -> {
            WebView webView = new WebView();
            webView.getEngine().load(url != null ? url : "https://www.openstreetmap.org/#map=12/43.3340/-2.9073");
            jfxPanel.setScene(new Scene(webView));
        });

        return jfxPanel;
    }




    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            logger.warning("El look and feel no está soportado");
            logger.warning(e.getMessage());
            // Aquí puedes manejar la excepción o dejar que se use el look and feel predeterminado
        }

        SwingUtilities.invokeLater(() -> {
            new MainMenuCliente(new AlmacenDeDatos(), null);
        });
    }

    public void recargarTablaReview() {
        //Borrar toda la tabla
        modelo.setRowCount(0);
        //Volver a cargar la tabla
        llenarTablaConReviewsPendientes();
    }
}
