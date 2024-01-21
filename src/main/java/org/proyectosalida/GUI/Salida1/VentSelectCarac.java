package org.proyectosalida.GUI.Salida1;

import org.jdesktop.swingx.JXSearchField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Constructores.Salida;
import org.proyectosalida.Datos.AlmacenDeDatos;

public class VentSelectCarac extends JFrame {

    private List<Caracteristica> datos;
    private JTextArea textArea;
    private JPanel checkboxesPanel;
    private JPanel panel;
//private ArrayList<Caracteristica> caracteristicasSeleccionadas;
    private JXSearchField searchField;
    private JScrollPane sp;



    public VentSelectCarac(ArrayList<Caracteristica> caracteristicasSeleccionadas, Boolean editandoLocal, AlmacenDeDatos almacen, Salida salida) {
        super("Seleccion de Caracteristicas");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar la lista de datos con las características proporcionadas
        datos = Arrays.asList(Caracteristica.values());

        textArea = new JTextArea(){
            @Override
            public boolean isEditable() {
                return false;
            }
        };
        sp = new JScrollPane(textArea);

        checkboxesPanel = new JPanel();
        //caracteristicasSeleccionadas = caracSelec;
        checkboxesPanel.setPreferredSize(new Dimension(200, 50));


        JButton Aceptar=new JButton("Aceptar");

        JPanel panelabajo=new JPanel(new GridLayout(0,1));

        panel = new JPanel();
        panel.setLayout(new BorderLayout());



        searchField = new JXSearchField("Buscar...");
        searchField.setPreferredSize(new Dimension(200, 30));

        searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            buscarTexto(searchText);
            crearCheckboxes(searchText, caracteristicasSeleccionadas);
        });

        // Crear un hilo para imprimir las características seleccionadas
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Características seleccionadas: " + caracteristicasSeleccionadas);
                try {
                    Thread.sleep(2000); // Puedes ajustar el tiempo de espera en milisegundos según lo necesites
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciar el hilo
        //thread.start();

        Aceptar.addActionListener(e->{
            dispose();
            if(!editandoLocal){ //Significa que no estas modificando un local, asik se pasa a la busqueda relacionada con las caracteristicas
                //AÑADIMOS OTRO PANEL PARA SABER SI QUIERE IR A UN LOCAL O A MAS A DE UNO (RUTA)
                int opcion = JOptionPane.showOptionDialog(
                        new JFrame(),
                        "¿Quieres salir de ruta de establecimientos o a uno solo?",
                        "Tipo de salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Único", "Ruta"},
                        "Único"
                );

                if(opcion==0){
                    new VentLocalesRecomendados(caracteristicasSeleccionadas, almacen, salida);
                }else if(opcion==1){
                    int n  = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantos locales quieres recorrer?"));
                    new VentanaRuta(caracteristicasSeleccionadas, almacen, salida, n);
                }

            }
        });
        panel.add(searchField, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panelabajo.add(checkboxesPanel);
        panelabajo.add(Aceptar);
        panel.add(panelabajo, BorderLayout.SOUTH);


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



    private void crearCheckboxes(String searchText, ArrayList<Caracteristica> caracteristicasSeleccionadas) {
        checkboxesPanel.removeAll();
        for (Caracteristica resultado : datos) {
            if (resultado.name().toLowerCase().startsWith(searchText.toLowerCase())) {
                JCheckBox checkBox = new JCheckBox(resultado.name());

                for(Caracteristica caract : caracteristicasSeleccionadas){
                    if(caract.name().equals(resultado.name())){ //Si ya la has seleccionado antes
                        checkBox.setSelected(true);
                    }
                }

                checkBox.addActionListener((ActionEvent e) -> {
                    if (checkBox.isSelected()) {
                        caracteristicasSeleccionadas.add(resultado);
                        System.out.println("Has seleccionado: " + checkBox.getText());
                    } else {
                        caracteristicasSeleccionadas.remove(resultado);
                        System.out.println("Has deseleccionado: " + checkBox.getText());
                    }
                    System.out.println("Seleccionadas: "+caracteristicasSeleccionadas);
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
            VentSelectCarac ventana = new VentSelectCarac(new ArrayList<>(), false, new AlmacenDeDatos(), null);
            ventana.setVisible(true);

        });
    }



}
