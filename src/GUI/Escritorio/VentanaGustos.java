import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import com.toedter.calendar.JCalendar;


public class VentanaGustos extends JFrame {


    public VentanaGustos() {
        JPanel panelPrincipal = new JPanel((new GridLayout(6, 1)));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));


        JLabel preguntaLabel = new JLabel("¿Cuándo salimos?");
        panelPrincipal.add(preguntaLabel);


        String[] opciones = {"Ahora", "Luego"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        Dimension comboBoxSize = new Dimension(100, 20);
        comboBox.setMaximumSize(comboBoxSize);
        comboBox.setMinimumSize(comboBoxSize);
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrincipal.add(comboBox);


        JCalendar calendar = new JCalendar();
        calendar.setVisible(false);
        panelPrincipal.add(calendar);


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.setVisible(comboBox.getSelectedItem().equals("Luego"));
                System.out.println(calendar.getDate());
                cambiarTamanoVentana(400,450);




            }
        });




        // Usando GridLayout para organizar los gustos en múltiples columnas
        JPanel gustosPanel = new JPanel(new GridLayout(0, 3, 1, 1)); // 0 filas significa cualquier cantidad de filas, 2 columnas, y espacio entre elementos
        String[] gustos = {"Cerveza", "Música", "Dulce", "Terraza", "Música en vivo",
                "Zona infantil", "Ambiente íntimo", "Vista panorámica", "Espacio para eventos",
                "Decoración artística", "Cocina gourmet", "Barra de cócteles", "Música electrónica",
                "Karaoke", "Pista de baile", "Juegos de mesa", "Transmisión de deportes", "Wifi gratuito",
                "Aire acondicionado", "Zona de fumadores", "Accesibilidad para discapacitados",
                "Estacionamiento privado", "Pet friendly"};
        ArrayList<String>gustosos=new ArrayList<String>();


        for (String gusto : gustos) {// mira gusto a gusto
            JCheckBox checkBoxGusto = new JCheckBox(gusto);//crea un checkbox para cada gusto y lo añade al panel
            gustosPanel.add(checkBoxGusto);
            checkBoxGusto.addActionListener(new ActionListener() {// le damos funcion a cada checkbox
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBoxGusto.isSelected()){
                        System.out.println("has seleccionado "+ checkBoxGusto.getText());
                        gustosos.add(checkBoxGusto.getText());
                    }else{
                        gustosos.remove(checkBoxGusto.getText());
                        System.out.println(checkBoxGusto.getText()+" deseleccionado");
                        gustosos.add(checkBoxGusto.getText());
                    }
                }
            });
        }








        // Añadir el panel de gustos al panel principal y alinear a la izquierda
        gustosPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrincipal.add(gustosPanel);


        this.add(panelPrincipal);
        this.setSize(400, 300); // Puede que necesites ajustar el tamaño para que todo quepa bien
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void cambiarTamanoVentana(int ancho, int alto) {
        this.setSize(ancho, alto);
    }


    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(() -> {
            new VentanaGustos();
        });


    }
}

