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

        // Top panel
        JPanel topPanel = new JPanel();



        topPanel.setBackground(Color.BLUE);
        topPanel.setPreferredSize(new Dimension(getWidth(), 50));
        add(topPanel, BorderLayout.NORTH);

        // Map panel
        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            // URL de OpenStreetMap centrada en una ubicación específica
            webView.getEngine().load("https://www.openstreetmap.org/#map=17/43.27063/-2.93807");
        });

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.RED);

        // Initialize the splitPane
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jfxPanel, bottomPanel);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerSize(1);
        add(splitPane, BorderLayout.CENTER);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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
