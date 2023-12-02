package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.Bar;
import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Constructores.Usuario;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.Pruebas.MenuPersonal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.HashMap;

public class ModificarLocales extends JFrame{

    private AlmacenDeDatos almacen;
    private Dueño dueño;
    private Local localSelec;
    private HashMap<String, Local> locales;
    private HashMap<String, Class> localesPorClase;

    public ModificarLocales(AlmacenDeDatos almacn){
        setTitle("Modifica o añade un nuevo local, "+almacn.getUsuariosPrueba().get(0).getNombre());
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        locales = new HashMap<>();
        localesPorClase = new HashMap<>();
        localSelec = null;
        almacen = almacn;
        dueño = (Dueño) almacn.getUsuariosPrueba().get(0); //TODO CAMBIAR EL METODO AL GETUSUARIOS PARA CONECTAR CON BD

        //TREE --- IZQ
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tus Locales");
        DefaultTreeModel modelo = new DefaultTreeModel(root);
        JTree tree = new JTree(modelo);
        JScrollPane scroll = new JScrollPane(tree);
        scroll.setPreferredSize(new Dimension(180, 300));
        add(scroll, BorderLayout.WEST);
        cargarLocalesAlTree(dueño, root);

        //FORMULARIO -- CENTR/*
        //          Local(String nombre, String direccion,String CP, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, ArrayList<Horario> horarios, BOOLEAN TERRAZA, *DJ DJRESIDENTE*, *DJ DJINVITADO*, ArrayList<Caracteristica>caracteristicas)
        //          Mayus = Bar
        //          ** = Discoteca
        //         */

        JPanel panelFormulario = new JPanel(new GridLayout(13,2)); add(panelFormulario, BorderLayout.CENTER); panelFormulario.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelFormulario.add(new JLabel("Nombre")); JTextField tNombre = new JTextField(); panelFormulario.add(tNombre);
        panelFormulario.add(new JLabel("Direccion"));  JTextField tDireccion = new JTextField(); panelFormulario.add(tDireccion);
        panelFormulario.add(new JLabel("CP"));  JTextField tCp = new JTextField(); panelFormulario.add(tCp);
        panelFormulario.add(new JLabel("Aforo"));  JTextField tAforo = new JTextField(); panelFormulario.add(tAforo);
        panelFormulario.add(new JLabel("Tlf."));  JTextField tTelefono = new JTextField(); panelFormulario.add(tTelefono);
        panelFormulario.add(new JLabel("Media de Edad"));  JTextField tEdad = new JTextField(); panelFormulario.add(tEdad);
        panelFormulario.add(new JLabel("Media de Precio"));  JTextField tPrecio = new JTextField(); panelFormulario.add(tPrecio);
        panelFormulario.add(new JLabel("Horarios"));  JTextField tHorarios = new JTextField(); panelFormulario.add(tHorarios);
        panelFormulario.add(new JLabel("¿Terraza?"));  JTextField tTerraza = new JTextField(); panelFormulario.add(tTerraza);
        panelFormulario.add(new JLabel("DJ (Residente)"));  JTextField tResidente = new JTextField(); panelFormulario.add(tResidente);
        panelFormulario.add(new JLabel("DJ (Invitado)"));  JTextField tInvitado = new JTextField(); panelFormulario.add(tInvitado);
        panelFormulario.add(new JLabel("Caracteristicas"));  JTextField tCaracteristicas = new JTextField(); panelFormulario.add(tCaracteristicas);


        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode nodoSelec = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(nodoSelec != null && nodoSelec.isLeaf()){
                    String nombreLocalSelec = (String) nodoSelec.getUserObject();
                    localSelec = locales.get(nombreLocalSelec);

                    tNombre.setText(localSelec.getNombre());
                    tDireccion.setText(localSelec.getDireccion());
                    tCp.setText(localSelec.getCP());
                    tAforo.setText(String.valueOf(localSelec.getAforo()));
                    tTelefono.setText(localSelec.getTelefono());
                    tEdad.setText(String.valueOf(localSelec.getMediaEdad()));
                    tPrecio.setText(String.valueOf(localSelec.getPrecioMedio()));

                }
            }
        });
    }


    private void cargarLocalesAlTree(Dueño dueño, DefaultMutableTreeNode root){
        for(Local local : dueño.getLocales()){
            locales.put(local.getNombre(), local);
            localesPorClase.put(local.getNombre(), local.getClass());
            root.add(new DefaultMutableTreeNode(local.getNombre()));
        }
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
            ModificarLocales ventana = new ModificarLocales(almacen);
            ventana.setVisible(true);
        });
    }
}
