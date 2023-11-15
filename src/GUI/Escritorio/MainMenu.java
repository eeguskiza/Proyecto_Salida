package GUI.Escritorio;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private JSplitPane splitPane;

    public MainMenu() {
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

        JMenuItem menuItem1 = new JMenuItem("Item 1");
        menu.add(menuItem1);
        JMenuItem menuItem2 = new JMenuItem("Item 2");
        menu.add(menuItem2);
        JMenuItem menuItem3 = new JMenuItem("Item 3");
        menu.add(menuItem3);
        menu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);
        menuBar.add(menu);

        // Set the menu bar for the JFrame
        setJMenuBar(menuBar);

        // Map panel
        JPanel panelMapa = new JPanel();
        panelMapa.setBackground(Color.BLUE);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.RED);

        // Initialize the splitPane
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelMapa, bottomPanel);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerSize(1);
        add(splitPane, BorderLayout.CENTER);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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
        new MainMenu();
    }
}
