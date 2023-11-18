package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.*;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;

public class MainMenu extends JFrame {

    private JSplitPane splitPane;
    private JSplitPane splitPane2;

    public MainMenu(String nombre) {
        setTitle("Main Menu");
        setSize(1200, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Recuros/Menu.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon menuIcon = new ImageIcon(resizedImage);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu();
        menu.setIcon(menuIcon);

        JMenuItem menuItem1 = new JMenuItem("Preferencias");
        menu.add(menuItem1);
        JMenuItem menuItem2 = new JMenuItem("Item 2");
        menu.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Item 3");
        menu.add(menuItem3);
        menu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");

        //Logica de los botones del menu
        exitItem.addActionListener(e -> System.exit(0));
        menuItem1.addActionListener(e -> new MenuPersonal(nombre));
        menu.add(exitItem);
        menuBar.add(menu);

        // Set the menu bar for the JFrame
        setJMenuBar(menuBar);

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
        JPanel panel1Encuesta = new JPanel(new BorderLayout());
        panel1Encuesta.add(new JLabel("Hoy toca..."), BorderLayout.NORTH);
        //panel1Encuesta.setBackground(Color.green);
        JPanel pEncuesta = new JPanel(new GridLayout(3,1)); panel1Encuesta.add(pEncuesta);
        JPanel panelStage = new JPanel(new FlowLayout(FlowLayout.LEFT)); JProgressBar pbStage = new JProgressBar(0,10); panelStage.add(new JLabel("Stage Live"));  panelStage.add(pbStage);
        JPanel panelBack = new JPanel(new FlowLayout(FlowLayout.LEFT)); JProgressBar pbBack = new JProgressBar(0,10); panelBack.add(new JLabel("Back Room")); panelBack.add(pbBack);
        JPanel panelMoma = new JPanel(new FlowLayout(FlowLayout.LEFT)); JProgressBar pbMoma = new JProgressBar(0,10); panelMoma.add(new JLabel("Moma"));  panelMoma.add(pbMoma);

        //Tamaño y colores
        setupProgressBar(pbStage, new Dimension(150, 20)); // Cambia el 150 y 20 según tus necesidades
        setupProgressBar(pbBack, new Dimension(150, 20));
        setupProgressBar(pbMoma, new Dimension(150, 20));

        pEncuesta.add(panelStage);
        pEncuesta.add(panelBack);
        pEncuesta.add(panelMoma);

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

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción o dejar que se use el look and feel predeterminado
        }

        SwingUtilities.invokeLater(() -> {
            new MainMenu("Prueba");
        });
    }

}
