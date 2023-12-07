package org.proyectosalida.GUI.Salida2;

import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasDueño.ModificarLocales;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuDuenio {

    private JFrame frame;
    private JTable table;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtDireccion;
    private JTextField txtCP;
    private JTextField txtDuenio;
    private JButton btnEditar, btnVolver, btnGuardar;



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    MainMenuDuenio window = new MainMenuDuenio(new AlmacenDeDatos());
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public MainMenuDuenio(AlmacenDeDatos almacen) {
        initialize(almacen);
        //Hacer actionlistener de btnEditar y cuando se este editando inhabilitar btnVolver y añadir un boton btnGuardar
        //Y que los campos solo sean editables al pulsar en btnEditar
        //btnGuardar esta hecho solo falta ver como añadirlo o habilitarlo

        btnEditar.addActionListener(e ->{

        });

        btnVolver.addActionListener(e ->{
            frame.dispose();

        });

        btnGuardar.addActionListener(e ->{

        });

    }


    private void initialize(AlmacenDeDatos almacen) {
        frame = new JFrame();
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Generamos un usuario de prueba para poder probar el programa
        Dueño nuevo = almacen.getDueño();

        JPanel pTitulo = new JPanel();
        frame.getContentPane().add(pTitulo, BorderLayout.NORTH);
        pTitulo.setLayout(new GridLayout(4,1));

        JLabel lblRelleno = new JLabel("");
        pTitulo.add(lblRelleno);

        JLabel lblTitulo = new JLabel("   Menu de locales");
        lblTitulo.setVerticalAlignment(SwingConstants.BOTTOM);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        pTitulo.add(lblTitulo);

        JLabel lblNewLabel = new JLabel("     Aquí encontrarás información acerca de tus locales");
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
        pTitulo.add(lblNewLabel);

        JPanel pLocales = new JPanel();
        frame.getContentPane().add(pLocales, BorderLayout.WEST);
        pLocales.setLayout(new BorderLayout(0, 0));

        JLabel lblListaLocales = new JLabel("    Lista de locales:           ");
        pLocales.add(lblListaLocales, BorderLayout.NORTH);
        lblListaLocales.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));

        /*
        DefaultTableModel modelo = new DefaultTableModel();
        JTable table = new JTable(modelo);
        modelo.addColumn("Nombre");

        // Obtener los nombres de los locales del dueño y agregarlos a la tabla
        for (int i = 0; i < nuevo.getLocales().size(); i++) {
            String nombreLocal = nuevo.getLocales().get(i).getNombre();
            modelo.addRow(new Object[]{nombreLocal});
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtener la fila y la columna en la que se hizo clic
                int fila = table.getSelectedRow();
                int columna = table.getSelectedColumn();

                // Obtener el valor de la celda en la que se hizo clic
                Object valorCelda = table.getValueAt(fila, columna);


                txtNombre.setText(nuevo.getLocales().get(0).getId());
                txtCodigo.setText(nuevo.getLocales().get(0).getNombre());
                txtNomPropietario.setText(nuevo.getNombre());
                txtDireccion.setText(nuevo.getLocales().get(0).getDireccion());
                txtCP.setText(nuevo.getLocales().get(0).getCP());


                // Ejecutar la acción deseada (en este caso, imprimir el valor de la celda)
                System.out.println("Valor de la celda seleccionada: " + valorCelda);
            }
        });



        pLocales.add(table, BorderLayout.CENTER);

         */

        JTree tree = new JTree();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Locales");
        for (Local local : nuevo.getLocales()) {
            DefaultMutableTreeNode localNode = new DefaultMutableTreeNode(local.getNombre());
            rootNode.add(localNode);
        }

        // Establecer el modelo del JTree con el nodo raíz
        tree.setModel(new javax.swing.tree.DefaultTreeModel(rootNode));

        pLocales.add(new JScrollPane(tree), BorderLayout.CENTER);

        //-------------------------------PANEL DE INFORMACIÓN DE LOS LOCALES------------------------------------

        JPanel pInformacion = new JPanel();
        frame.getContentPane().add(pInformacion, BorderLayout.CENTER);
        pInformacion.setLayout(new GridLayout(9,2));

        JLabel lblCodigo = new JLabel("      Código del local");
        lblCodigo.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblCodigo);

        JPanel p1 = new JPanel();
        pInformacion.add(p1);
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        txtCodigo = new JTextField();
        txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(txtCodigo);
        txtCodigo.setColumns(20);


        JLabel lblNombre = new JLabel("      Nombre del local");
        lblNombre.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblNombre);

        JPanel p2 = new JPanel();
        pInformacion.add(p2);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
        p2.add(txtNombre);
        txtNombre.setColumns(20);

        JLabel lblDuenio = new JLabel("      Dueño");
        lblDuenio.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblDuenio);

        JPanel p3 = new JPanel();
        pInformacion.add(p3);

        txtDuenio = new JTextField();
        txtDuenio.setFont(new Font("Tahoma", Font.PLAIN, 12));
        p3.add(txtDuenio);
        txtDuenio.setColumns(20);

        JLabel lblDireccion = new JLabel("      Direccion");
        lblDireccion.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblDireccion);

        JPanel p4 = new JPanel();
        pInformacion.add(p4);

        txtDireccion = new JTextField();
        txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        p4.add(txtDireccion);
        txtDireccion.setColumns(20);

        JLabel lblCP = new JLabel("      Código postal");
        lblCP.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblCP);

        JPanel p5 = new JPanel();
        pInformacion.add(p5);

        txtCP = new JTextField();
        txtCP.setFont(new Font("Tahoma", Font.PLAIN, 12));
        p5.add(txtCP);
        txtCP.setColumns(20);

        JLabel lblAforo = new JLabel("      Aforo");
        lblAforo.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblAforo);

        JPanel p6 = new JPanel();
        pInformacion.add(p6);

        JSpinner spAforo = new JSpinner();
        spAforo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        p6.add(spAforo);

        JLabel lblValoracion = new JLabel("      Valoracion media");
        lblValoracion.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblValoracion);

        JPanel p7 = new JPanel();
        pInformacion.add(p7);

        JProgressBar pbValoracion = new JProgressBar();
        pbValoracion.setValue(44);
        pbValoracion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        p7.add(pbValoracion);

        JLabel lblDescripcion = new JLabel("      Descripción del local");
        lblDescripcion.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblDescripcion);

        JPanel p8 = new JPanel();
        pInformacion.add(p8);

        JTextPane textPane = new JTextPane();
        textPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
        p8.add(textPane);

        JLabel lblCaracteristicas = new JLabel("      Características del local");
        lblCaracteristicas.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        pInformacion.add(lblCaracteristicas);

        JPanel p9 = new JPanel();
        pInformacion.add(p9);

        JPanel pNorte = new JPanel();
        frame.getContentPane().add(pNorte, BorderLayout.SOUTH);

        btnEditar = new JButton("Editar características");
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pNorte.add(btnEditar);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        //pNorte.add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pNorte.add(btnVolver);


        //Setear valores de prueba
        txtCodigo.setText(nuevo.getLocales().get(0).getId());
        txtNombre.setText(nuevo.getLocales().get(0).getNombre());
        txtDuenio.setText(nuevo.getNombre());
        txtDireccion.setText(nuevo.getLocales().get(0).getDireccion());
        txtCP.setText(nuevo.getLocales().get(0).getCP());
        spAforo.setValue(nuevo.getLocales().get(0).getAforo());
        pbValoracion.setValue(almacen.getValoresVotaciones().get(3)*10);




    }

}

