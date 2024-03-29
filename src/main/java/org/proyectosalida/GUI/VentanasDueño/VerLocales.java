package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasGenerales.MenuPersonal;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerLocales extends JFrame {

    private AlmacenDeDatos almacen;

    public VerLocales(Dueño dueño, AlmacenDeDatos almcn){
        setTitle("Menú Principal: "+dueño.getNombre());
        setSize(1400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        almacen = almcn;
        almcn.getClasesDeLocales(); //Se guardan las clases de los localen en el orden de la tabla para luego clasificar

        /*
          Local(String nombre, String direccion,String CP, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, ArrayList<Horario> horarios, BOOLEAN TERRAZA, *DJ DJRESIDENTE*, *DJ DJINVITADO*, ArrayList<Caracteristica>caracteristicas)
          Mayus = Bar
          ** = Discoteca
         */

        JPanel panelEnunciado = new JPanel(new BorderLayout()); add(panelEnunciado, BorderLayout.NORTH);
        panelEnunciado.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 30));
        JLabel textoBienvenida = new JLabel("Bienvenid@ de vuelta, "+dueño.getNombre()+"!"); textoBienvenida.setFont(new Font(textoBienvenida.getFont().getName(), Font.BOLD, 13));
        panelEnunciado.add(textoBienvenida, BorderLayout.EAST);

        ImageIcon menu_icon = new ImageIcon("src/main/resources/images/menu_bar.png"); Image menu_image = menu_icon.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        JButton bMenuPersonal = new JButton(new ImageIcon(menu_image)); panelEnunciado.add(bMenuPersonal, BorderLayout.WEST);
        bMenuPersonal.setBorderPainted(true);

        bMenuPersonal.addActionListener(e -> {
            MenuPersonal vMenuPersonal =  new MenuPersonal(dueño, almacen, this);
            vMenuPersonal.setVisible(true);
            setVisible(false);
        });



        JPanel mainPanel = new JPanel(new BorderLayout()); add(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
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


        JPanel indicacionesColores = new JPanel(new FlowLayout()); mainPanel.add(indicacionesColores, BorderLayout.NORTH); indicacionesColores.setBackground(new Color(204, 204, 204));
        JLabel l1 = new JLabel("Bares"); l1.setForeground(new Color(4, 117, 13)); l1.setFont(new Font(l1.getFont().getName(), Font.BOLD, 13));
        JLabel l2 = new JLabel("Discotecas"); l2.setForeground(new Color(5, 22, 176)); l2.setFont(new Font(l2.getFont().getName(), Font.BOLD, 13));
        indicacionesColores.add(l1); indicacionesColores.add(new JLabel("&")); indicacionesColores.add(l2);

        JPanel botonera = new JPanel(new FlowLayout()); mainPanel.add(botonera, BorderLayout.SOUTH);
        JButton atras = new JButton("Volver Atras");
        //botonera.add(atras);
        atras.addActionListener(e -> {
            //dispose();
            //TODO - QUE SE ABRA INICIO DE SESION?
        });

        JButton registrarNuevo = new JButton("Registrar/Actualizar");
        botonera.add(registrarNuevo);
        registrarNuevo.addActionListener(e -> {
            ModificarLocales vr = new ModificarLocales(almacen, this, null);
            vr.setVisible(true);
            dispose();
        });

        JButton refresh = new JButton("Refresh"); //botonera.add(refresh);
        refresh.addActionListener(e -> {
            modelo.setRowCount(0);
            cargarLocalesATabla(dueño, modelo);
        });

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

                Class tipo = almcn.getClasesDeLocales().get(row);
                c.setForeground(Color.black);
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
                    if(tipo.equals(Bar.class)){
                        c.setBackground(new Color(125, 255, 125));
                    }else{
                        c.setBackground(new Color(154, 162, 255));
                    }
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

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
               if(e.getClickCount() == 2){
                   int fila = tabla.rowAtPoint(e.getPoint());
                   String nombreLocal = (String) tabla.getValueAt(fila, 0);
                   String tlfLocal = (String) tabla.getValueAt(fila, 4);

                   for(Local local : dueño.getLocales()){
                       if(local.getNombre().equals(nombreLocal) && local.getTelefono().equals(tlfLocal)){ //Comprobacion por num por si el nombre es repetido
                           getpadre().setVisible(false);
                           new ModificarLocales(almacen, getpadre(), local).setVisible(true);
                       }
                   }
               }
            }
        });

    }

    private void cargarLocalesATabla(Dueño dueño, DefaultTableModel modelo){
        System.out.println("LOCALES: " +dueño.getLocales().size());
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
                System.out.println(local.getWeb());
                modelo.addRow(nuevo);
                almacen.getClasesDeLocales().add(Bar.class);
            }else if(local.getClass().equals(Discoteca.class)){
                String nombreInvitado = "";
                String nombreResidente = "";
                if(((Discoteca) local).getDjInvitado() != null){
                    nombreInvitado = ((Discoteca) local).getDjInvitado().getNombre();
                }
                if(((Discoteca) local).getDjResidente() != null){
                    nombreResidente = ((Discoteca) local).getDjResidente().getNombre();
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
                        "", //Discotecas no tienen terraza
                        nombreResidente,
                        nombreInvitado,
                        caracteristicas
                };
                modelo.addRow(nuevo);
                almacen.getClasesDeLocales().add(Discoteca.class);
            }else{
                System.out.println("No son ninguna class (??)");
            }
        }
    }

    private JFrame getpadre(){
        return this;
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
            VerLocales v = new VerLocales((Dueño) almacen.getUsuariosPrueba().get(0), almacen);
            v.setVisible(true);
        });
    }
}
