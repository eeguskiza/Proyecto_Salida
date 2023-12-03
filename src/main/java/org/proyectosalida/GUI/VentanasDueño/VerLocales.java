package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VerLocales extends JFrame {

    private ArrayList<Class> clasesDeLocales; //Se guardan las clases de los localen en el orden de la tabla para luego clasificar
    private AlmacenDeDatos almacen;

    public VerLocales(Dueño dueño, Frame padre, AlmacenDeDatos almcn){
        setTitle("Menú Personal: "+dueño.getNombre());
        setSize(1400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        padre.setVisible(false);
        almacen = almcn;

        /*
          Local(String nombre, String direccion,String CP, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, ArrayList<Horario> horarios, BOOLEAN TERRAZA, *DJ DJRESIDENTE*, *DJ DJINVITADO*, ArrayList<Caracteristica>caracteristicas)
          Mayus = Bar
          ** = Discoteca
         */

        JPanel mainPanel = new JPanel(new BorderLayout()); add(mainPanel);
        Object[] cabeceras = {"Nombre", "Direccion", "CP", "Aforo", "Tlf.", "Media de Edad", "Media de Precio", "WebSite", "Horario", "Terraza", "DJ (Residente)", "DJ (Invitado)", "Caracteristicas"};
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modelo);
        modelo.setColumnIdentifiers(cabeceras);
        JScrollPane scroll = new JScrollPane(tabla);
        mainPanel.add(scroll, BorderLayout.CENTER);

        JPanel botonera = new JPanel(new FlowLayout()); mainPanel.add(botonera, BorderLayout.SOUTH);
        JButton atras = new JButton("Volver Atras");
        botonera.add(atras);
        atras.addActionListener(e -> {
            dispose();
            padre.setVisible(true);
        });

        JButton registrarNuevo = new JButton("Registrar Nuevo");
        botonera.add(registrarNuevo);
        registrarNuevo.addActionListener(e -> {
            ModificarLocales vr = new ModificarLocales(almacen, this);
            vr.setVisible(true);
            dispose();
        });

        JButton refresh = new JButton("Refresh"); botonera.add(refresh);
        refresh.addActionListener(e -> {
            modelo.setRowCount(0);
            cargarLocalesATabla(dueño, modelo);
        });

        clasesDeLocales = new ArrayList<>();
        cargarLocalesATabla(dueño, modelo);

        //RENDERER DE LA CABECERA DE LA TABLA
        TableCellRenderer headerRendererPredeterminado = tabla.getTableHeader().getDefaultRenderer();
        tabla.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = headerRendererPredeterminado.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if(column == 9){ //Terraza
                    c.setBackground(new Color(186, 245, 188));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                    c.setForeground(new Color(4, 117, 13));
                }else if(column == 10 || column == 11){
                    c.setBackground(new Color(186, 191, 245));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                    c.setForeground(new Color(5, 22, 176));
                }else{
                    c.setBackground(Color.white);
                }

                return c;
            }
        });

        //RENDERER DEL CUERPO DE LA TABLA
        tabla.setDefaultRenderer( Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                Class tipo = clasesDeLocales.get(row);
                if(tipo.equals(Bar.class)){
                    if(column==10 || column == 11){
                        c.setBackground(new Color(147, 147, 147));
                    }else{
                        c.setBackground(new Color(186, 245, 188));
                    }
                }else{
                    if(column==9){
                        c.setBackground(new Color(147, 147, 147));
                    }else{
                        c.setBackground(new Color(186, 191, 245));
                    }
                }

                if(isSelected){
                    c.setForeground(Color.BLACK);
                }

                //Expandir texto
                if(column==1 || column==7|| column== 8|| column==12){
                    //setToolTipText(value.toString());
                }

                return c;
            }
        });


        tabla.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabla.rowAtPoint(e.getPoint());
                int column = tabla.columnAtPoint(e.getPoint());
                if (column == 1 ||column == 7 ||column == 8 ||column == 12) {
                    Object value = tabla.getValueAt(row, column);
                    if (value != null) {
                        String text = value.toString();
                        text = "<html>" + text.replaceAll("\n", "<br>") + "</html>";
                        tabla.setToolTipText(text);
                    }
                }
            }
        });

    }

    private void cargarLocalesATabla(Dueño dueño, DefaultTableModel modelo){
        for(Local local : dueño.getLocales()){
            String horario = "";
            for(Horario dia : local.getHorarios()){
                horario += dia + "\n";
            }

            String caracteristicas = "";
            for(Caracteristica c : local.getCaracteristicas()){
                caracteristicas += c + ", \n";
            }

            String terraza = "";

            if(local.getClass().equals(Bar.class)){
                if(((Bar) local).getTerraza()){
                    terraza = "SI";
                }else{
                    terraza = "NO";
                }
                Object[] nuevo = {
                        local.getNombre(),
                        local.getDireccion(),
                        local.getCP(),
                        local.getAforo(),
                        local.getTelefono(),
                        local.getMediaEdad(),
                        local.getPrecioMedio(),
                        local.getWeb(),
                        horario,
                        terraza,
                        "", //Los bares no tienen DJ's
                        "",
                        caracteristicas
                };
                modelo.addRow(nuevo);
                clasesDeLocales.add(Bar.class);
            }else if(local.getClass().equals(Discoteca.class)){
                Object[] nuevo = {
                        local.getNombre(),
                        local.getDireccion(),
                        local.getCP(),
                        local.getAforo(),
                        local.getTelefono(),
                        local.getMediaEdad(),
                        local.getPrecioMedio(),
                        local.getWeb(),
                        horario,
                        "", //Discotecas no tienen terraza
                        ((Discoteca) local).getDjResidente().getNombre(),
                        ((Discoteca) local).getDjInvitado().getNombre(),
                        caracteristicas
                };
                modelo.addRow(nuevo);
                clasesDeLocales.add(Discoteca.class);
            }else{
                System.out.println("No son ninguna class (??)");
            }
        }
    }
}
