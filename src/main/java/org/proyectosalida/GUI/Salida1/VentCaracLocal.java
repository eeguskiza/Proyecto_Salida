package org.proyectosalida.GUI.Salida1;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import org.proyectosalida.Constructores.Bar;
import org.proyectosalida.Constructores.Local;

import javax.swing.*;
import java.awt.*;

public class VentCaracLocal extends JFrame {
    public VentCaracLocal(Local local) {
        JPanel panelprincipal=new JPanel(new GridLayout(0,2));
        JLabel nombre=new JLabel("Nombre");
        panelprincipal.add(nombre);
        JTextField Nombre =new JTextField(local.getNombre());
        panelprincipal.add(Nombre);
        JLabel direccion=new JLabel("Direccion");
        panelprincipal.add(direccion);
        JPanel Direccion =new JPanel();
        BrowserView ubi = cargarMapa(local);
        Direccion.add(ubi);
        panelprincipal.add(Direccion);











        this.add(panelprincipal);
        this.setVisible(true);
        this.setSize(300,500);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentCaracLocal(new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, "https://www.tripadvisor.es/Restaurant_Review-g187454-d5615756-Reviews-Bar_Monty-Bilbao_Province_of_Vizcaya_Basque_Country.html", null, true,null));
            }
        });
    }
    private BrowserView cargarMapa(Local local){
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

        String url = "https://www.google.es/maps/"+local.getDireccion();
        // Cargando la URL en el navegador
        browser.navigation().loadUrl(url);
        return view;
    }
}
