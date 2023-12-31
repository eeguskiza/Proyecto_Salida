package org.proyectosalida.GUI.VentanasCliente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.proyectosalida.Constructores.Caracteristica;
import com.toedter.calendar.JCalendar;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.Salida1.VentLocalesRecomendados;

import static org.proyectosalida.Datos.AlmacenDeDatos.logger;

public class VentanaGustos extends JFrame {
    private ArrayList<Caracteristica> caracteristicasSeleccionadas = new ArrayList<>();
    private JCalendar calendar;

    public VentanaGustos(AlmacenDeDatos almacen) {
        inicializarVentana();
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        JLabel preguntaLabel = new JLabel("¿Cuándo salimos?");
        panelPrincipal.add(preguntaLabel);

        String[] opciones = {"Ahora", "Luego"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBox.addActionListener(e -> {
            calendar.setVisible(comboBox.getSelectedItem().equals("Luego"));
            cambiarTamanoVentana(400, 450);
        });
        panelPrincipal.add(comboBox);

        calendar = new JCalendar();
        calendar.setVisible(false);
        panelPrincipal.add(calendar);

        agregarPanelGustos(panelPrincipal);

        JButton Aceptar = new JButton("ACEPTAR");
        Aceptar.addActionListener(e -> {
            setVisible(false);
            // Hacer algo con las características seleccionadas
            // Ejemplo: imprimir en consola
            logger.info("Características seleccionadas: " + Arrays.toString(caracteristicasSeleccionadas.toArray()));
        });
        panelPrincipal.add(Aceptar);

        add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Aceptar.addActionListener(e -> {
                    //new VentLocalesRecomendados(caracteristicasSeleccionadas, almacen, null);
                }
        );
    }

    private void inicializarVentana() {
        setTitle("Ventana de Gustos");
        setSize(400, 300);
    }

    private void cambiarTamanoVentana(int ancho, int alto) {
        setSize(ancho, alto);
    }

    private void agregarPanelGustos(JPanel panelPrincipal) {
        JPanel gustosPanel = new JPanel(new GridLayout(0, 3, 1, 1));
        for (Caracteristica caracteristica : Caracteristica.values()) {
            JCheckBox checkBoxCaracteristica = new JCheckBox(caracteristica.name());
            checkBoxCaracteristica.addActionListener(e -> {
                if (checkBoxCaracteristica.isSelected()) {
                    logger.info(caracteristica + " seleccionado");
                    caracteristicasSeleccionadas.add(caracteristica);
                } else {
                    caracteristicasSeleccionadas.remove(caracteristica);
                    logger.info(caracteristica + " deseleccionado");
                }
            });
            gustosPanel.add(checkBoxCaracteristica);
        }
        gustosPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPrincipal.add(gustosPanel);


    }


    public static void main(String[] args) {
        //SwingUtilities.invokeLater(VentanaGustos::new);
    }
}
