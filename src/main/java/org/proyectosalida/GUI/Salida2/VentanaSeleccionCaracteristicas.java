package org.proyectosalida.GUI.Salida2;

import org.jdesktop.swingx.JXSearchField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Datos.AlmacenDeDatos;

public class VentanaSeleccionCaracteristicas extends JFrame {

    private List<Caracteristica> datos;
    private JTextArea textArea;
    private JPanel checkboxesPanel;
    private ArrayList<Caracteristica> caracteristicasSeleccionadas;

    public VentanaSeleccionCaracteristicas(AlmacenDeDatos almacenDeDatos) {
        super("Seleccion de Caracteristicas");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar la lista de datos con las características proporcionadas
        datos = Arrays.asList(Caracteristica.values());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        checkboxesPanel = new JPanel();
        caracteristicasSeleccionadas = new ArrayList<>(almacenDeDatos.getCaracteristicas());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JXSearchField searchField = new JXSearchField("Buscar...");
        searchField.setPreferredSize(new Dimension(200, 30));

        searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            buscarTexto(searchText);
            crearCheckboxes(searchText);
        });

        panel.add(searchField, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(checkboxesPanel, BorderLayout.SOUTH);
        add(panel);

        setVisible(true);
    }

    private void buscarTexto(String texto) {
        textArea.setText("");

        List<Caracteristica> resultados = datos.stream()
                .filter(item -> item.name().toLowerCase().startsWith(texto.toLowerCase()))
                .collect(Collectors.toList());

        // Mostrar los resultados en el JTextArea
        textArea.append("Resultados de la búsqueda:\n");
        for (Caracteristica resultado : resultados) {
            textArea.append(resultado + "\n");
        }
    }



    private void crearCheckboxes(String searchText) {
        checkboxesPanel.removeAll();
        for (Caracteristica resultado : datos) {
            if (resultado.name().toLowerCase().startsWith(searchText.toLowerCase())) {
                JCheckBox checkBox = new JCheckBox(resultado.name());
                checkBox.setSelected(caracteristicasSeleccionadas.contains(resultado));

                checkBox.addActionListener((ActionEvent e) -> {
                    if (checkBox.isSelected()) {
                        caracteristicasSeleccionadas.add(resultado);
                        System.out.println("Has seleccionado: " + checkBox.getText());
                    } else {
                        caracteristicasSeleccionadas.remove(resultado);
                        System.out.println("Has deseleccionado: " + checkBox.getText());
                    }
                });
                checkboxesPanel.add(checkBox);
            }
        }
        checkboxesPanel.revalidate();
        checkboxesPanel.repaint();
    }

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            VentanaSeleccionCaracteristicas ventana = new VentanaSeleccionCaracteristicas(new AlmacenDeDatos());
            ventana.setVisible(true);
        });
    }
}
