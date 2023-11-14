import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class VentanaInicio extends JFrame {
    public VentanaInicio() {
        super("Proyecto Salida");
        this.setSize(500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JFXPanel jfxPanel = new JFXPanel();
        this.add(jfxPanel);

        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            // URL de OpenStreetMap centrada en una ubicación específica (por ejemplo, la Casa Blanca)
            webView.getEngine().load("https://www.openstreetmap.org/#map=17/43.27063/-2.93807");
        });

        this.setVisible(true);
    }

    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaInicio();
        });
    }
}
