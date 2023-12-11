package org.proyectosalida.GUI.VentanasCliente;

import org.proyectosalida.Constructores.Bar;
import org.proyectosalida.Constructores.Cliente;
import org.proyectosalida.Constructores.Visita;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.Pruebas.MenuPersonal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class VentanaVisitas extends JFrame {

    public VentanaVisitas(AlmacenDeDatos almacen, JFrame padre){
        Cliente usuario = (Cliente) almacen.getUsuariosPrueba().get(1);
        setTitle("Tus visitas registradas");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel main = new JPanel(new BorderLayout()); add(main);
        main.setBorder(new EmptyBorder(15, 70, 25, 70));

        JLabel titulo = new JLabel("TUS VISITAS RECIENTES"); titulo.setFont(new Font(titulo.getFont().getName(), Font.BOLD, 15));
        JPanel pTitulo = new JPanel(new FlowLayout()); pTitulo.add(titulo); main.add(pTitulo, BorderLayout.NORTH);
        pTitulo.setBorder(new EmptyBorder(0, 0, 15, 0));

        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Date.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        JTable table = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(table);
        main.add(scroll);

        Object[] titulos = {"Tipo", "Nombre del Local", "Fecha",  "Hora", "Valoracion"};
        modelo.setColumnIdentifiers(titulos);

        //COMPARADOR DE FECHAS PARA ORDENAR LA TABLA
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        table.setRowSorter(sorter);
        sorter.setComparator(2, Comparator.nullsLast(Comparator.naturalOrder()));
        sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(2, SortOrder.ASCENDING))); // Aparece el icono automaticamente

        /*
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
         */
        for(Visita v : usuario.getVisitas()){
            String tipo = "";
            if(v.getLocal().getClass().equals(Bar.class)){
                tipo = "Bar";
            }else{
                tipo = "Discoteca";
            }
            Object[] visitaNueva = {tipo, v.getLocal().getNombre(), v.getFecha(), v.getHora(), v.getValoracion()};

            modelo.addRow(visitaNueva);
        }

        table.setDefaultRenderer( Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String tipo = (String) table.getValueAt(row, 0);
                if(tipo.equals("Bar")){
                    c.setBackground(new Color(186, 245, 188));
                }else{
                    c.setBackground(new Color(186, 191, 245));
                }

                if(column==2){
                    setText(almacen.transformarDateAString((Date) table.getValueAt(row, 2)));
                }

                if(isSelected){
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        JPanel botonera = new JPanel(new FlowLayout()); add(botonera, BorderLayout.SOUTH);
        JButton atras = new JButton("Atrás"); botonera.add(atras);

        atras.addActionListener(e -> {
            dispose();
            padre.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Configuración del look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            //Conexion.conectar();
            AlmacenDeDatos almacen = new AlmacenDeDatos();
            new VentanaVisitas(almacen, null).setVisible(true);
        });
    }
}
