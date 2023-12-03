package org.proyectosalida.GUI.Salida1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Pruebas.Objetos;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Constructores.Bar;
import org.proyectosalida.Constructores.Discoteca;
import org.proyectosalida.GUI.VentanasCliente.VentanaGustos;

public class ClasePrueba extends JFrame {

    private DefaultTableModel modeloTabla;
    protected ArrayList<Caracteristica>carcateristicasseleccionadas;


    public ClasePrueba() {
        // Crear el panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());




        // Crear el título "RECOMENDADOS" en la parte superior
        JLabel tituloLabel = new JLabel("RECOMENDADOS");
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(tituloLabel, BorderLayout.NORTH);

        // Crear la tabla y su modelo
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Ubicación");
        modeloTabla.addColumn("Match");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Foto");

        // ejemplos que se quitan cuaando aprenda a puto importar cosas


        //Monty
        ArrayList<Caracteristica>caracteristicasMonty=new ArrayList<>();
        caracteristicasMonty.add(Caracteristica.PINTXOS);
        caracteristicasMonty.add(Caracteristica.TERRAZA);
        caracteristicasMonty.add(Caracteristica.CERVEZAS);
        caracteristicasMonty.add(Caracteristica.COMBINADOS);
        caracteristicasMonty.add(Caracteristica.COCINA_LOCAL);
        caracteristicasMonty.add(Caracteristica.COPAS);
        caracteristicasMonty.add(Caracteristica.TARTAS_CASERAS);
        caracteristicasMonty.add(Caracteristica.RACIONES);
        caracteristicasMonty.add(Caracteristica.CARTA_VARIADA);
        // Back
        ArrayList<Caracteristica>CaracteristicasBack=new ArrayList<>();
        CaracteristicasBack.add(Caracteristica.CERVEZAS);
        CaracteristicasBack.add(Caracteristica.MUSICA);
        CaracteristicasBack.add(Caracteristica.COMBINADOS);
        CaracteristicasBack.add(Caracteristica.ZONA_PRIVADA);
//fff

        Objetos objetos = new Objetos();
        ArrayList<Local> locales = new ArrayList<>();
        locales.add(new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, "aa", null, true,caracteristicasMonty));
        locales.add(new Discoteca("Back&Stage", "Calle de la Ronda, 35, Bilbo, Bizkaia","48005",  200, "747 48 96 30", 0, 0, "link2", null, null, null,CaracteristicasBack));
        carcateristicasseleccionadas = new ArrayList<>();
        carcateristicasseleccionadas.add(Caracteristica.PINTXOS);
        carcateristicasseleccionadas.add(Caracteristica.COMBINADOS);
        carcateristicasseleccionadas.add(Caracteristica.ENSALADAS);
        carcateristicasseleccionadas.add(Caracteristica.TAPAS);
        carcateristicasseleccionadas.add(Caracteristica.TARTAS_CASERAS);



        // Llenar la tabla con los locales

        for (Local local : locales) {

            JProgressBar progressBar = new JProgressBar(0, carcateristicasseleccionadas.size());
            progressBar.setValue(contarCaracteristicasEnComun(carcateristicasseleccionadas, local.getCaracteristicas()));



            Object[] rowData = {local.getNombre(), local.getDireccion(),progressBar, local.getTelefono(), ""}; // Puedes completar el Match y la Foto según tu lógica
            modeloTabla.addRow(rowData);
        }

        JTable tabla = new JTable(modeloTabla);
        panelPrincipal.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Crear el botón "ESTE" en la esquina inferior derecha
        JButton esteBoton = new JButton("ESTE");
        esteBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica a realizar al hacer clic en el botón
                JOptionPane.showMessageDialog(ClasePrueba.this, "Botón 'ESTE' presionado");
            }
        });
        panelPrincipal.add(esteBoton, BorderLayout.SOUTH);

        // Configuración de la ventana
        setContentPane(panelPrincipal);
        setVisible(true);
        setTitle("Lugares Recomendados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClasePrueba();
            }
        });
    }
    // metdod para ver cunatas carcateristicas hay en comun
    private static int contarCaracteristicasEnComun(ArrayList<Caracteristica> caracteristicasSeleccionadas, ArrayList<Caracteristica> caracteristicasLocal) {
        int count = 0;

        for (Caracteristica caracteristica : caracteristicasLocal) {
            if (caracteristicasSeleccionadas.contains(caracteristica)) {
                count++;
            }
        }

        return count;
    }

}
