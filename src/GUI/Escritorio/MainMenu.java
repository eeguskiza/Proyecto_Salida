package GUI.Escritorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

    private JSplitPane splitPane;
    private JSplitPane splitPane2;

    public MainMenu(String nombre) {
        setTitle("Main Menu");
        setSize(1000, 800);
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

        // Map panel
        JPanel panelMapa = new JPanel();
        panelMapa.setBackground(Color.BLUE);

        JPanel panelSalimos = new JPanel();

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.RED);

        // Initialize the splitPane
        splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelMapa, panelSalimos);
        splitPane2.setResizeWeight(0.9);
        splitPane2.setDividerSize(1);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane2, bottomPanel);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerSize(1);
        add(splitPane, BorderLayout.CENTER);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        bottomPanel.setLayout(new GridLayout(1,3));

        //PANEL 1 - ENCUESTAS
        JPanel panel1Encuesta = new JPanel(new BorderLayout());
        //panel1Encuesta.setBackground(Color.green);
        JPanel pEncuesta = new JPanel(new GridLayout(3,1)); panel1Encuesta.add(pEncuesta);
        JPanel panelStage = new JPanel(new FlowLayout(FlowLayout.LEFT)); JProgressBar pbStage = new JProgressBar(0,10); panelStage.add(new JLabel("Stage Live"));  panelStage.add(pbStage);
        JPanel panelBack = new JPanel(new FlowLayout(FlowLayout.LEFT)); JProgressBar pbBack = new JProgressBar(0,10); panelBack.add(new JLabel("Back Room")); panelBack.add(pbBack);
        JPanel panelMoma = new JPanel(new FlowLayout(FlowLayout.LEFT)); JProgressBar pbMoma = new JProgressBar(0,10); panelMoma.add(new JLabel("Moma"));  panelMoma.add(pbMoma);

        pbStage.setForeground(Color.green);
        pbBack.setForeground(Color.green);
        pbMoma.setForeground(Color.green); //si


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

    public static void main(String[] args) {
        new MainMenu("Prueba");
    }
}
