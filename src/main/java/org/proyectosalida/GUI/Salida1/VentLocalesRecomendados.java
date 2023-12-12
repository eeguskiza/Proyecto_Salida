package org.proyectosalida.GUI.Salida1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;


import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;

public class VentLocalesRecomendados extends JFrame {

    private DefaultTableModel modeloTabla;
    private Integer selectedRow;
    protected ArrayList<Local> locales;
    private ArrayList<Caracteristica> caracteristicasABuscar;
    private static HashMap<Local, ArrayList<Caracteristica>> resultadoBusqueda; //Se mapea el local con las caracteristicas que tienen en comun


    public VentLocalesRecomendados(ArrayList<Caracteristica>carcateristicasseleccionadas, AlmacenDeDatos almacen) {
        locales = almacen.getLocales();
        resultadoBusqueda = new HashMap<>();
        caracteristicasABuscar = carcateristicasseleccionadas;
        selectedRow = -1;

        // Crear el panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JButton aceptar=new JButton("Aceptar");

        // Crear el título "RECOMENDADOS" en la parte superior
        JLabel tituloLabel = new JLabel("RECOMENDADOS");
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(tituloLabel, BorderLayout.NORTH);
        panelPrincipal.add(aceptar,BorderLayout.SOUTH);

        // Crear la tabla y su modelo
        modeloTabla = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Ubicación");
        modeloTabla.addColumn("Match");
        modeloTabla.addColumn("Telefono");
        //modeloTabla.addColumn("Foto");
        modeloTabla.addColumn("Ver Horario");


        JTable tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        JPanel panelTabla = new JPanel(new BorderLayout()); panelTabla.add(scroll, BorderLayout.CENTER);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(20, 15, 15, 15));

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(sorter);

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int row = tabla.rowAtPoint(e.getPoint());
                if(row==selectedRow){
                    selectedRow = -1; //Se desactiva
                }else{
                    selectedRow = row;
                }
            }
        });

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);
                if (isSelected) {
                    c.setBackground(Color.LIGHT_GRAY);
                }


                if(column==2){
                    if(row != selectedRow || selectedRow==-1){
                        JProgressBar pb = new JProgressBar(0, carcateristicasseleccionadas.size());
                        Local localRow = conseguirobjeto((String) table.getValueAt(row, 0));
                        pb.setValue(resultadoBusqueda.get(localRow).size());

                        return pb;
                    }
                }

                if(column == 4){
                    JPanel p = new JPanel(new BorderLayout());
                    p.add(new JLabel("Click para ver") {{ setHorizontalAlignment(SwingConstants.CENTER); }});

                    return p;
                }

                if(isSelected){
                    c.setBackground(new Color(229, 229, 229));
                }

                tabla.repaint();
                return c;
            }
        });
        //panelPrincipal.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        // Llenar la tabla con los locales
        seleccionarLocales();
        for (Local local : resultadoBusqueda.keySet()) {
            Object[] rowData = {local.getNombre(), local.getDireccion(),resultadoBusqueda.get(local) /*contarCaracteristicasEnComun(carcateristicasseleccionadas,local.getCaracteristicas())*/, local.getTelefono(), ""};
            modeloTabla.addRow(rowData);

        }


        // Crear el botón "ESTE" en la esquina inferior derecha
        JPanel botonera = new JPanel(new FlowLayout()); //me daba toc que el boton ocupara t0do el ancho de la ventana jajaja
        JButton esteBoton = new JButton("ESTE");
        botonera.add(esteBoton); panelPrincipal.add(botonera, BorderLayout.SOUTH);
        botonera.add(new JLabel("Doble click para ver más características..."));
        esteBoton.addActionListener(e -> {

        int fila=tabla.getSelectedRow();
        if (fila != -1) {
            String nombre = (String) modeloTabla.getValueAt(fila, 0);
            Local objeto = conseguirobjeto(nombre);
            if (objeto instanceof Bar) {

                System.out.println("abrir ventana" + objeto);
                dispose();
                new VentCaracBar(objeto);
            }else {
                new VentCaracDisco(objeto);
                dispose();
            }


        } else {
            // Manejar el caso en el que no hay ninguna fila seleccionada

        }});

        // Configuración de la ventana
        setContentPane(panelPrincipal);
        setVisible(true);
        setTitle("Lugares Recomendados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<Caracteristica> caracts = new ArrayList<>();
                caracts.add(Caracteristica.PINTXOS); caracts.add(Caracteristica.BAILE); caracts.add(Caracteristica.AFTERWORK);
                new VentLocalesRecomendados(caracts, new AlmacenDeDatos());
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
    private static Local conseguirobjeto(String nombre){

        for (Local local : resultadoBusqueda.keySet()) {
            if (nombre.equals(local.getNombre())){
                return local;
            }

        }

        return null;
    }

    //Lógica para encontrar locales dependiendo de las caracteristicas alex el tuyo no funcionaba del todo
    private void seleccionarLocales(){
        //Si tiene al menos una en comun se añade
        for (Local local : locales){
            ArrayList<Caracteristica> caracteristicasEnComun = new ArrayList<>();
            for (Caracteristica c : local.getCaracteristicas()){
                if(caracteristicasABuscar.contains(c)){
                    caracteristicasEnComun.add(c);
                    System.out.println(local.getNombre()+": "+c.name());
                }
            }
            if(caracteristicasEnComun.size()!=0){
                resultadoBusqueda.put(local, caracteristicasEnComun);
            }
        }
    }

}
