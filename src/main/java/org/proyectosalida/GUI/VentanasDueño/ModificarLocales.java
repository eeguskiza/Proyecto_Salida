package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.Registro.VentanaAddHorarios;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ModificarLocales extends JFrame{

    private AlmacenDeDatos almacen;
    private Dueño dueño;
    private Local localSelec;
    private HashMap<String, Local> locales;
    //private HashMap<String, Class> localesPorClase;
    private ArrayList<Horario> horariosSelec;
    private ArrayList<Horario> horariosNuevos;
    private Boolean localSeleccionadoEnTree;

    public ModificarLocales(AlmacenDeDatos almacn){
        localSeleccionadoEnTree = false;
        setTitle("Modifica o añade un nuevo local, "+almacn.getUsuariosPrueba().get(0).getNombre());
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        locales = new HashMap<>();
        //localesPorClase = new HashMap<>();
        localSelec = null;
        almacen = almacn;
        dueño = (Dueño) almacn.getUsuariosPrueba().get(0); //TODO CAMBIAR EL METODO AL GETUSUARIOS PARA CONECTAR CON BD
        horariosSelec = new ArrayList<>();
        horariosNuevos = new ArrayList<>();

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

        JPanel panelFormulario = new JPanel(new GridLayout(13,2)); add(panelFormulario, BorderLayout.CENTER); panelFormulario.setBorder(new EmptyBorder(10, 150, 10, 30));
        panelFormulario.add(new JLabel("Tipo de Establecimiento"));
        JRadioButton bbar = new JRadioButton("Bar"); JRadioButton bdiscoteca = new JRadioButton("Discoteca"); ButtonGroup tipoLocal = new ButtonGroup(); tipoLocal.add(bbar); tipoLocal.add(bdiscoteca);
        JPanel panelTipoLocal = new JPanel(new FlowLayout()); panelTipoLocal.add(bbar); panelTipoLocal.add(bdiscoteca); panelFormulario.add(panelTipoLocal);
        panelFormulario.add(new JLabel("Nombre")); JTextField tNombre = new JTextField(); panelFormulario.add(tNombre);
        panelFormulario.add(new JLabel("Direccion"));  JTextField tDireccion = new JTextField(); panelFormulario.add(tDireccion);
        panelFormulario.add(new JLabel("CP"));  JTextField tCp = new JTextField(); panelFormulario.add(tCp);
        panelFormulario.add(new JLabel("Aforo"));  JTextField tAforo = new JTextField(); panelFormulario.add(tAforo);
        panelFormulario.add(new JLabel("Tlf."));  JTextField tTelefono = new JTextField(); panelFormulario.add(tTelefono);
        panelFormulario.add(new JLabel("Media de Edad"));  JTextField tEdad = new JTextField(); panelFormulario.add(tEdad);
        panelFormulario.add(new JLabel("Media de Precio"));  JTextField tPrecio = new JTextField(); panelFormulario.add(tPrecio);
        panelFormulario.add(new JLabel("Horarios"));  JButton bHorarios = new JButton("Agregar Horarios"); panelFormulario.add(bHorarios);
        panelFormulario.add(new JLabel("¿Terraza?"));
        JRadioButton bTerrazaSi = new JRadioButton("Si"); JRadioButton bTerrazaNo = new JRadioButton("No"); JPanel panelBotonesTerraza = new JPanel(new FlowLayout()); panelBotonesTerraza.add(bTerrazaSi); panelBotonesTerraza.add(bTerrazaNo); panelFormulario.add(panelBotonesTerraza);
        ButtonGroup botoneraTerraza = new ButtonGroup(); botoneraTerraza.add(bTerrazaSi); botoneraTerraza.add(bTerrazaNo);
        panelFormulario.add(new JLabel("DJ (Residente)"));  JButton bResidente = new JButton("Agregar Datos"); panelFormulario.add(bResidente);
        panelFormulario.add(new JLabel("DJ (Invitado)"));  JButton bInvitado = new JButton("Agregar Datos"); panelFormulario.add(bInvitado);
        panelFormulario.add(new JLabel("Caracteristicas"));  JButton bCaracteristicas = new JButton("Agregar Características"); panelFormulario.add(bCaracteristicas);

        bbar.addActionListener(e -> {
            bResidente.setEnabled(false);
            bInvitado.setEnabled(false);
            bTerrazaSi.setEnabled(true);
            bTerrazaNo.setEnabled(true);
        });

        bdiscoteca.addActionListener(e -> {
            bResidente.setEnabled(true);
            bInvitado.setEnabled(true);
            bTerrazaSi.setEnabled(false);
            bTerrazaNo.setEnabled(false);
            botoneraTerraza.clearSelection();
        });

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                localSeleccionadoEnTree = true;
                DefaultMutableTreeNode nodoSelec = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(nodoSelec != null && nodoSelec.isLeaf()){
                    String nombreLocalSelec = (String) nodoSelec.getUserObject();
                    localSelec = locales.get(nombreLocalSelec);
                    if(localSelec.getClass().equals(Bar.class)){
                        //activar lo correspondiente
                        bTerrazaSi.setEnabled(true); bTerrazaNo.setEnabled(true);
                        Bar local = (Bar) localSelec;

                        bbar.setSelected(true);
                        if(local.getTerraza()){
                            bTerrazaSi.setSelected(true);
                        }else{
                            bTerrazaNo.setSelected(true);
                        }

                        //Desactivar lo correspondiente (botones de DJ)
                        bResidente.setEnabled(false);
                        bInvitado.setEnabled(false);

                    }else{
                        bResidente.setEnabled(true);
                        bInvitado.setEnabled(true);
                        Discoteca disco = (Discoteca) localSelec;

                        bdiscoteca.setSelected(true);

                        //desactivar lo correrspondiente
                        botoneraTerraza.clearSelection(); bTerrazaSi.setEnabled(false); bTerrazaNo.setEnabled(false);
                    }

                    tNombre.setText(localSelec.getNombre());
                    tDireccion.setText(localSelec.getDireccion());
                    tCp.setText(localSelec.getCP());
                    tAforo.setText(String.valueOf(localSelec.getAforo()));
                    tTelefono.setText(localSelec.getTelefono());
                    tEdad.setText(String.valueOf(localSelec.getMediaEdad()));
                    tPrecio.setText(String.valueOf(localSelec.getPrecioMedio()));
                    horariosSelec = localSelec.getHorarios();


                    bHorarios.setText("Ver Horarios");
                    bInvitado.setText("Ver Invitado");
                    bResidente.setText("Ver Residente");
                    bCaracteristicas.setText("Ver Características");


                }
            }
        });


        bHorarios.addActionListener(e -> {
            if(localSeleccionadoEnTree){
                VentanaAddHorarios ventanaAddHorarios = new VentanaAddHorarios(horariosSelec, this);
            }else{
                VentanaAddHorarios ventanaAddHorarios = new VentanaAddHorarios(horariosNuevos, this);
            }
            setVisible(false);
        });

    }


    private void cargarLocalesAlTree(Dueño dueño, DefaultMutableTreeNode root){
        for(Local local : dueño.getLocales()){
            locales.put(local.getNombre(), local);
            //localesPorClase.put(local.getNombre(), local.getClass());
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
