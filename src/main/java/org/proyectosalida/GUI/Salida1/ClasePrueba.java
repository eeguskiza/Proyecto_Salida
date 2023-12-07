package org.proyectosalida.GUI.Salida1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Pruebas.Objetos;

public class ClasePrueba extends JFrame {

    private DefaultTableModel modeloTabla;
    protected ArrayList<Caracteristica>carcateristicasseleccionadas;


    public ClasePrueba(ArrayList<Caracteristica>carcateristicasseleccionadas) {
        // Crear el panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JButton aceptar=new JButton("Aceptar");
       




        // Crear el título "RECOMENDADOS" en la parte superior
        JLabel tituloLabel = new JLabel("RECOMENDADOS");
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(tituloLabel, BorderLayout.NORTH);
        panelPrincipal.add(aceptar,BorderLayout.SOUTH);

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

        ArrayList<Horario> horariosMonty = new ArrayList<>();
        horariosMonty.add(new Horario("Lunes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Martes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Miercoles", "07:30", "23:30"));
        horariosMonty.add(new Horario("Jueves", "07:30", "23:30"));
        horariosMonty.add(new Horario("Viernes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Sabado", "07:30", "23:30"));
        horariosMonty.add(new Horario("Domingo", "07:30", "16:00"));


        // Back
        ArrayList<Caracteristica>CaracteristicasBack=new ArrayList<>();


        CaracteristicasBack.add(Caracteristica.CERVEZAS);
        CaracteristicasBack.add(Caracteristica.MUSICA);
        CaracteristicasBack.add(Caracteristica.COMBINADOS);
        CaracteristicasBack.add(Caracteristica.ZONA_PRIVADA);
//fff

        Objetos objetos = new Objetos();
        ArrayList<Local> locales = new ArrayList<>();
        locales.add(new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, "aa", horariosMonty, true,caracteristicasMonty));
        locales.add(new Discoteca("Back&Stage", "Calle de la Ronda, 35, Bilbo, Bizkaia","48005",  200, "747 48 96 30", 0, 0, "link2", null, null, null,CaracteristicasBack));



        
        
        JTable tabla = new JTable(modeloTabla);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);


        panelPrincipal.add(new JScrollPane(tabla), BorderLayout.CENTER);



        // Llenar la tabla con los locales

        for (Local local : locales) {

            JProgressBar progressBar = new JProgressBar(0, carcateristicasseleccionadas.size());
            progressBar.setValue(contarCaracteristicasEnComun(carcateristicasseleccionadas, local.getCaracteristicas()));
            /*
            tabla.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
                @Override

                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    c.setBackground(Color.WHITE);
                    if (isSelected) {
                        c.setBackground(Color.LIGHT_GRAY);
                    }
                    if (column == 3) {
                        JPanel progreso=new JPanel();
                        progreso.add(progressBar);
                        progreso.add(c);
                    }


                    return c;
                }


            });

                 */


            Object[] rowData = {local.getNombre(), local.getDireccion(),contarCaracteristicasEnComun(carcateristicasseleccionadas,local.getCaracteristicas()), local.getTelefono(), ""};
            modeloTabla.addRow(rowData);

        }




        // Crear el botón "ESTE" en la esquina inferior derecha
        JButton esteBoton = new JButton("ESTE");
        esteBoton.addActionListener(e -> {

        int fila=tabla.getSelectedRow();
        if (fila != -1) {
            String nombre = (String) modeloTabla.getValueAt(fila, 0);
            Local objeto = conseguirobjeto(nombre, locales);

                System.out.println("abrir ventana"+objeto);
                new VentCaracLocal(objeto);


        } else {
            // Manejar el caso en el que no hay ninguna fila seleccionada

        }});

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
                new ClasePrueba(null);
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
    private static Local conseguirobjeto(String nombre,ArrayList<Local>locales){
        for (Local local : locales) {
            if (nombre.equals(local.getNombre())){
                return local;
            }

        }

        return null;
    }

}
