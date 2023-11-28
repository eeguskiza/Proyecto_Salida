package org.proyectosalida.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.proyectosalida.Constructores.AlmacenDeDatos;
import org.proyectosalida.Constructores.Cliente;
import org.proyectosalida.Constructores.Usuario;
import com.formdev.flatlaf.*;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;

public class MainMenu extends JFrame {

    private JSplitPane splitPane;
    private JSplitPane splitPane2;
    public AlmacenDeDatos almacen;

    public MainMenu(Usuario usuario, AlmacenDeDatos almacenDeDatos) {
        setTitle("org.Proyecto_Salida.Escritorio.Main Menu");
        setSize(1200, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        almacen = almacenDeDatos;

        // Panel para el botón del menú
        JPanel panelMenu = new JPanel(new BorderLayout());
        JButton menuButton = new JButton();
        JButton salimos = new JButton("¿Salimos?");

        // Configuración del botón menu
        menuButton.setBackground(Color.decode("#B4D3B2")); // Color personalizado
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



        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MenuPersonal(usuario, MainMenu.this);
            }
        });

        salimos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //VentanaGustos
                new VentanaGustos();

            }
        });

        // Añadir el botón al panel del menú
        panelMenu.add(menuButton, BorderLayout.WEST);
        panelMenu.setBackground(Color.WHITE); // Fondo del panel

        panelMenu.add(salimos, BorderLayout.EAST);
        panelMenu.setBackground(Color.WHITE); // Fondo del panel

        add(panelMenu, BorderLayout.NORTH);



        // Panel para el mapa
        JPanel panelMapa = new JPanel(new BorderLayout());
        panelMapa.setBackground(Color.BLUE);

        // Configuración de JxBrowser
        String LICENSE_KEY = "6P830J66YCEA9SQDHRL8EDTZK57189867HR8YUD4L7QAI5ZHGIZK21JI39COT5XFHX0V";
        EngineOptions options = EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED)
                .licenseKey(LICENSE_KEY)
                .build();

        // Inicializando el motor del navegador con la clave de licencia
        Engine engine = Engine.newInstance(options);

        // Creando el navegador
        Browser browser = engine.newBrowser();

        // Crear la vista del navegador para Swing
        BrowserView view = BrowserView.newInstance(browser);

        // Añadir la vista del navegador al panel del mapa
        panelMapa.add(view, BorderLayout.CENTER);


        // URL del mapa estático OpenStreetMap centrado en una ubicación específica
        String url = "https://www.google.es/maps/preview";
        // Cargando la URL en el navegador
        browser.navigation().loadUrl(url);


 
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
        JLabel labelEncabezado = new JLabel("Hoy se sale en:");
        labelEncabezado.setFont(new Font("Arial", Font.BOLD, 20));

        labelEncabezado.setHorizontalAlignment(SwingConstants.CENTER);

        // Añadir la etiqueta al panel en la parte superior (Norte)
        panel1Encuesta.add(labelEncabezado, BorderLayout.NORTH);


        // Panel para contener las encuestas con un GridLayout para 6 elementos
        JPanel pEncuesta = new JPanel(new GridLayout(6, 1)); // 6 filas, 1 columna
        panel1Encuesta.add(pEncuesta, BorderLayout.WEST);

        // Creación de los paneles de encuesta con barras de progreso
        JPanel panelStage = crearPanelEncuesta("Stage Live", 10);
        JProgressBar pbStage = (JProgressBar) panelStage.getComponent(1);
        setupProgressBar(pbStage, new Dimension(150, 20));

        JPanel panelBack = crearPanelEncuesta("Back Room", 10);
        JProgressBar pbBack = (JProgressBar) panelBack.getComponent(1);
        setupProgressBar(pbBack, new Dimension(150, 20));

        JPanel panelMoma = crearPanelEncuesta("Moma", 10);
        JProgressBar pbMoma = (JProgressBar) panelMoma.getComponent(1);
        setupProgressBar(pbMoma, new Dimension(150, 20));

        // Añadiendo tres paneles adicionales
        JPanel panelExtra1 = crearPanelEncuesta("Extra 1", 10);
        JProgressBar pbExtra1 = (JProgressBar) panelExtra1.getComponent(1);
        setupProgressBar(pbExtra1, new Dimension(150, 20));

        JPanel panelExtra2 = crearPanelEncuesta("Extra 2", 10);
        JProgressBar pbExtra2 = (JProgressBar) panelExtra2.getComponent(1);
        setupProgressBar(pbExtra2, new Dimension(150, 20));

        JPanel panelExtra3 = crearPanelEncuesta("Extra 3", 10);
        JProgressBar pbExtra3 = (JProgressBar) panelExtra3.getComponent(1);
        setupProgressBar(pbExtra3, new Dimension(150, 20));

        // Añadiendo todos los paneles al panel de encuestas
        pEncuesta.add(panelStage);
        pEncuesta.add(panelBack);
        pEncuesta.add(panelMoma);
        pEncuesta.add(panelExtra1);
        pEncuesta.add(panelExtra2);
        pEncuesta.add(panelExtra3);




        pbStage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentValue = pbStage.getValue();
                if (currentValue < pbStage.getMaximum()) {
                    pbStage.setValue(currentValue + 1);
                }
            }
        });

        pbBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentValue = pbBack.getValue();
                if (currentValue < pbBack.getMaximum()) {
                    pbBack.setValue(currentValue + 1);
                }
            }
        });

        pbMoma.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentValue = pbMoma.getValue();
                if (currentValue < pbMoma.getMaximum()) {
                    pbMoma.setValue(currentValue + 1);
                }
            }
        });

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

    private void setupProgressBar(JProgressBar progressBar, Dimension preferredSize) {
        progressBar.setPreferredSize(preferredSize);
        progressBar.setMinimumSize(preferredSize);
        progressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentValue = progressBar.getValue();
                if (currentValue < progressBar.getMaximum()) {
                    progressBar.setValue(currentValue + 1);
                    updateProgressBarColor(progressBar);
                }
            }
        });

        updateProgressBarColor(progressBar);
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

    // Método auxiliar para crear un panel de encuesta
    private JPanel crearPanelEncuesta(String titulo, int maxValor) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel(titulo));
        JProgressBar progressBar = new JProgressBar(0, maxValor);
        panel.add(progressBar);
        return panel;
    }

    public AlmacenDeDatos getAlmacen(){
        return almacen;
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
            new MainMenu(new Cliente(), new AlmacenDeDatos());
        });
    }

}