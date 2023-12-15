package org.proyectosalida.GUI.Salida1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;

public class VentLocalesRecomendados extends JFrame {

    private DefaultTableModel modeloTabla;
    private Integer selectedRow;
    protected ArrayList<Local> locales;
    private ArrayList<Caracteristica> caracteristicasABuscar;
    private static HashMap<Local, ArrayList<Caracteristica>> resultadoBusqueda; //Se mapea el local con las caracteristicas que tienen en comun
    private Calendar calendario;


    public VentLocalesRecomendados(ArrayList<Caracteristica>carcateristicasseleccionadas, AlmacenDeDatos almacen, Salida salida) {
        locales = almacen.getLocales();
        resultadoBusqueda = new HashMap<>();
        caracteristicasABuscar = carcateristicasseleccionadas;
        selectedRow = -1;
        calendario = Calendar.getInstance();

        // Crear el panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JButton aceptar=new JButton("Aceptar");

        // Crear el título "RECOMENDADOS" en la parte superior
        JLabel tituloLabel = new JLabel("LOCALES RECOMENDADOS  (" + salida.getFecha()+")");
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
        modeloTabla.addColumn("Horario");


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
                int collumn = tabla.columnAtPoint(e.getPoint());
                if(row==selectedRow && collumn==2){
                    selectedRow = -1; //Se desactiva
                }else{
                    if(collumn ==2){
                        selectedRow = row;
                    }
                }
            }
        });

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Local localRow = conseguirobjeto((String) table.getValueAt(row, 0));

                c.setBackground(Color.WHITE);
                if (isSelected) {
                    c.setBackground(Color.LIGHT_GRAY);
                }


                if(column==2){
                    if(row != selectedRow || selectedRow==-1){
                        JProgressBar pb = new JProgressBar(0, carcateristicasseleccionadas.size());
                        pb.setValue(resultadoBusqueda.get(localRow).size());

                        return pb;
                    }
                }

                if(column == 4){
                    JLabel texto = new JLabel("") {{ setHorizontalAlignment(SwingConstants.CENTER); }};

                    calendario.setTime(salida.getFecha()); //Por si decide salir otro dia
                    int diaSemanaSelec = calendario.get(Calendar.DAY_OF_WEEK)-2; //-1:Domingo 0:Lunes 1:Martes ...

                    if(diaSemanaSelec == -1){
                        diaSemanaSelec=6;
                    }
                    Horario horario = localRow.getHorarios().get(diaSemanaSelec);
                    int horaActual = calendario.get(Calendar.HOUR_OF_DAY); //Solo la hora en 24h
                    int minutoActual = calendario.get(Calendar.MINUTE);
                    String[] partesHoraInicio = horario.getHoraInicio().split(":");
                    int horaApertura = Integer.parseInt(partesHoraInicio[0]);
                    int minApertura = Integer.parseInt(partesHoraInicio[1]);
                    String[] partesHoraFin = horario.getHoraFin().split(":");
                    int horaCierre = Integer.parseInt(partesHoraFin[0]);
                    int minCierre = Integer.parseInt(partesHoraFin[1]);

                    boolean estaAbierto = false;
                    if ((horaApertura < horaActual && horaActual < horaCierre) || (horaActual > horaCierre && horaCierre < horaApertura)) {
                        estaAbierto = true;
                    }

                    if(estaAbierto){
                        texto.setText("<html><font color='green'>ABIERTO</font> (hasta " + horario.getHoraFin() + ")</html>");
                    }else{
                        texto.setText("<html><font color='red'>CERRADO</font> (" + horario.getHoraInicio() + ")</html>");
                    }

                    return texto;
                }

                if(isSelected){
                    c.setBackground(new Color(229, 229, 229));
                    c.setForeground(Color.BLACK);
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
            salida.setLocal(objeto); //Se registra el local seleccionado a la Salida
            if (objeto instanceof Bar) {

                System.out.println("abrir ventana" + objeto);
                dispose();
                //TODO FALTA AÑADIR EL VOTO A LOS POLLS DEL MAINMENU PRIMERO
                new MainMenuCliente(almacen, objeto.getWeb());
                new VentCaracBar(objeto);
            }else {
                new MainMenuCliente(almacen, objeto.getWeb());
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
                Salida salida = new Salida(null, null, new Date(), null);
                new VentLocalesRecomendados(caracts, new AlmacenDeDatos(), salida);
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