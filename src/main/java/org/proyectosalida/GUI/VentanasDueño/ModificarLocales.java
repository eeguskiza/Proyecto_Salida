package org.proyectosalida.GUI.VentanasDueño;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.Registro.VentanaAddHorarios;
import org.proyectosalida.GUI.Registro.VentanaRegistrarDJ;
import org.proyectosalida.GUI.Salida2.VentSelectCarac;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ModificarLocales extends JFrame{

    private AlmacenDeDatos almacen;
    private Dueño dueño;
    private DefaultMutableTreeNode root;
    private Local localSelec;
    private HashMap<String, Local> locales;
    //private HashMap<String, Class> localesPorClase;
    private ArrayList<Horario> horariosSelec;
    private ArrayList<Caracteristica> caracteristicasSelec;
    private Boolean localSeleccionadoEnTree;
    private DefaultTreeModel modelo;

    private DJ djResidente;
    private DJ djInvitado;

    private String instagramAntiguoDJ;
    public ModificarLocales(AlmacenDeDatos almacn, JFrame padre){
        localSeleccionadoEnTree = false;
        //setTitle("Modifica o añade un nuevo local, "+almacn.getUsuarios().get(0).getNombre());
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        locales = new HashMap<>();
        //localesPorClase = new HashMap<>();
        localSelec = null;
        almacen = almacn;
        instagramAntiguoDJ = "";

        //Esto para hacer pruebas sin BD
        if(almacn.getUsuarios().size()!= 0){
            dueño = (Dueño) almacn.getUsuarios().get(0);
        }else{
            dueño = (Dueño) almacn.getUsuariosPrueba().get(0);
        }

        horariosSelec = new ArrayList<>();
        caracteristicasSelec = new ArrayList<>();
        djResidente = new DJ();
        djInvitado = new DJ();

        //TREE --- IZQ
        root = new DefaultMutableTreeNode("Tus Locales");
        modelo = new DefaultTreeModel(root);
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

        JPanel panelFormulario = new JPanel(new GridLayout(14,2)); add(panelFormulario, BorderLayout.CENTER); panelFormulario.setBorder(new EmptyBorder(10, 150, 10, 30));
        panelFormulario.add(new JLabel("Tipo de Establecimiento"));
        JRadioButton bbar = new JRadioButton("Bar"); JRadioButton bdiscoteca = new JRadioButton("Discoteca"); ButtonGroup tipoLocal = new ButtonGroup(); tipoLocal.add(bbar); tipoLocal.add(bdiscoteca);
        JPanel panelTipoLocal = new JPanel(new FlowLayout()); panelTipoLocal.add(bbar); panelTipoLocal.add(bdiscoteca); panelFormulario.add(panelTipoLocal);
        panelFormulario.add(new JLabel("Nombre")); JTextField tNombre = new JTextField(); panelFormulario.add(tNombre);
        panelFormulario.add(new JLabel("Direccion"));  JTextField tDireccion = new JTextField(); panelFormulario.add(tDireccion);
        panelFormulario.add(new JLabel("CP"));  JSpinner sCp = new JSpinner(); sCp.getModel().setValue(48000); panelFormulario.add(sCp);
        panelFormulario.add(new JLabel("Aforo"));  JSpinner sAforo = new JSpinner(); sAforo.getModel().setValue(0); panelFormulario.add(sAforo);
        panelFormulario.add(new JLabel("Tlf."));  JTextField tTelefono = new JTextField(); panelFormulario.add(tTelefono);
        panelFormulario.add(new JLabel("Media de Edad"));  JSpinner sEdad = new JSpinner(); sEdad.getModel().setValue(0); panelFormulario.add(sEdad);
        panelFormulario.add(new JLabel("Media de Precio"));  JSpinner sPrecio = new JSpinner(); sPrecio.getModel().setValue(0); panelFormulario.add(sPrecio);
        panelFormulario.add(new JLabel("Pagina Web")); JTextField tWeb = new JTextField(); panelFormulario.add(tWeb);
        panelFormulario.add(new JLabel("Horarios"));  JButton bHorarios = new JButton("Agregar Horarios"); panelFormulario.add(bHorarios);
        panelFormulario.add(new JLabel("¿Terraza?"));
        JRadioButton bTerrazaSi = new JRadioButton("Si"); JRadioButton bTerrazaNo = new JRadioButton("No"); JPanel panelBotonesTerraza = new JPanel(new FlowLayout()); panelBotonesTerraza.add(bTerrazaSi); panelBotonesTerraza.add(bTerrazaNo); panelFormulario.add(panelBotonesTerraza);
        ButtonGroup botoneraTerraza = new ButtonGroup(); botoneraTerraza.add(bTerrazaSi); botoneraTerraza.add(bTerrazaNo);
        panelFormulario.add(new JLabel("DJ (Residente)"));  JButton bResidente = new JButton("Agregar Datos"); panelFormulario.add(bResidente);
        panelFormulario.add(new JLabel("DJ (Invitado)"));  JButton bInvitado = new JButton("Agregar Datos"); panelFormulario.add(bInvitado);
        panelFormulario.add(new JLabel("Caracteristicas"));  JButton bCaracteristicas = new JButton("Agregar Características"); panelFormulario.add(bCaracteristicas);

        //---------BOTONERA ----------------
        JPanel botonera = new JPanel(new FlowLayout()); add(botonera, BorderLayout.SOUTH);
        JButton atras = new JButton("Atrás"); botonera.add(atras);
        JButton guardar = new JButton("Registrar Nuevo"); botonera.add(guardar);



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
                        djResidente = disco.getDjResidente();
                        djInvitado = disco.getDjInvitado();

                        bdiscoteca.setSelected(true);

                        //desactivar lo correrspondiente
                        botoneraTerraza.clearSelection(); bTerrazaSi.setEnabled(false); bTerrazaNo.setEnabled(false);
                    }

                    tNombre.setText(localSelec.getNombre());
                    tDireccion.setText(localSelec.getDireccion());
                    ((SpinnerNumberModel) sCp.getModel()).setValue(Integer.parseInt(localSelec.getCP()));
                    ((SpinnerNumberModel) sAforo.getModel()).setValue(localSelec.getAforo());
                    tTelefono.setText(localSelec.getTelefono());
                    ((SpinnerNumberModel) sEdad.getModel()).setValue(localSelec.getMediaEdad());
                    ((SpinnerNumberModel) sPrecio.getModel()).setValue(localSelec.getPrecioMedio());
                    tWeb.setText(String.valueOf(localSelec.getWeb()));
                    horariosSelec = localSelec.getHorarios();
                    caracteristicasSelec = localSelec.getCaracteristicas();


                    bHorarios.setText("Ver Horarios");
                    bInvitado.setText("Ver Invitado");
                    bResidente.setText("Ver Residente");
                    bCaracteristicas.setText("Ver Características");
                    guardar.setText("Guardar Cambios");


                }
            }
        });


        bHorarios.addActionListener(e -> {
            if(localSeleccionadoEnTree){
                VentanaAddHorarios ventanaAddHorarios = new VentanaAddHorarios(horariosSelec, this, false);
            }else{
                VentanaAddHorarios ventanaAddHorarios = new VentanaAddHorarios(horariosSelec, this, true);
            }
            setVisible(false);
        });

        bCaracteristicas.addActionListener(e -> {
            VentSelectCarac ventanaCaracterist = new VentSelectCarac(caracteristicasSelec, true, almacn, null);
        });

        bResidente.addActionListener(e -> {
            if(localSeleccionadoEnTree){
                VentanaRegistrarDJ v1 = new VentanaRegistrarDJ(djResidente, this, false);
                v1.setVisible(true);
                instagramAntiguoDJ = djResidente.getInstagram();
            }else{
                VentanaRegistrarDJ v1 = new VentanaRegistrarDJ(djResidente, this, true);
                v1.setVisible(true);
            }
            setVisible(false);
        });

        bInvitado.addActionListener(e -> {
            if(localSeleccionadoEnTree){
                VentanaRegistrarDJ v2 = new VentanaRegistrarDJ(djInvitado, this, false);
                v2.setVisible(true);
                instagramAntiguoDJ = djInvitado.getInstagram();
            }else{
                VentanaRegistrarDJ v2 = new VentanaRegistrarDJ(djInvitado, this, true);
                v2.setVisible(true);
            }
            setVisible(false);
        });

        //GUARDAR CAMBIOS - REGISTRAR NUEVO
        guardar.addActionListener(e -> {
            if(bbar.isSelected()){
                Boolean terraza = false;
                if(bTerrazaSi.isSelected()){
                    terraza = true;
                }
                Bar nuevo = new Bar(tNombre.getText(), tDireccion.getText(), ((SpinnerNumberModel) sCp.getModel()).getNumber().toString(), ((SpinnerNumberModel) sAforo.getModel()).getNumber().intValue(), tTelefono.getText(), ((SpinnerNumberModel) sEdad.getModel()).getNumber().intValue(), ((SpinnerNumberModel) sPrecio.getModel()).getNumber().intValue(), tWeb.getText(), horariosSelec, terraza, caracteristicasSelec);
                actualizarLocalUsuario(nuevo, instagramAntiguoDJ);
                almacen.getClasesDeLocales().add(nuevo.getClass());
                clearForm(tNombre, tDireccion, tTelefono, tWeb, sCp,  sAforo,  sPrecio,  sEdad);
                //TODO FALTA GUARDARLO CORRRECTAMENTE EN BD?
                if(localSeleccionadoEnTree){
                    //GUARDAR EN BD EL LOCAL NUEVO
                }else{
                    //actualizarDatosLocal()
                }
            }else if(bdiscoteca.isSelected()){
                Discoteca nueva = new Discoteca(tNombre.getText(), tDireccion.getText(), ((SpinnerNumberModel) sCp.getModel()).getNumber().toString(), ((SpinnerNumberModel) sAforo.getModel()).getNumber().intValue(), tTelefono.getText(), ((SpinnerNumberModel) sEdad.getModel()).getNumber().intValue(), ((SpinnerNumberModel) sPrecio.getModel()).getNumber().intValue(), tWeb.getText(), horariosSelec, djResidente, djInvitado, caracteristicasSelec);
                actualizarLocalUsuario(nueva, instagramAntiguoDJ);
                almacen.getClasesDeLocales().add(nueva.getClass());
                clearForm(tNombre, tDireccion, tTelefono, tWeb,  sCp,  sAforo,  sPrecio,  sEdad);
                //TODO LO MISMO VAYA
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de establecimiento!");
            }
        });


        atras.addActionListener(e -> {
            dispose();
            //padre.setVisible(true);
            VerLocales newVerLocales = new VerLocales(dueño, almacn); //Asi se recarga automaticamente la tabla
            newVerLocales.setVisible(true);
        });

    }


    private void cargarLocalesAlTree(Dueño dueño, DefaultMutableTreeNode root){
        for(Local local : dueño.getLocales()){
            locales.put(local.getNombre(), local);
            //localesPorClase.put(local.getNombre(), local.getClass());
            root.add(new DefaultMutableTreeNode(local.getNombre()));
        }
    }

    private void actualizarLocalUsuario(Local nuevo, String instagramAntiguoDJ){
        for(int i=0; i<dueño.getLocales().size(); i++){
            Local local = dueño.getLocales().get(i);
            if(localSelec != null){
                if(local.getId().equals(localSelec.getId())){ //Es el mismo local al que estamos editando
                    nuevo.setId(local.getId());
                    dueño.getLocales().remove(i);
                    almacen.getClasesDeLocales().remove(i);
                    System.out.println("Eliminado el anterior");
                    break;
                }
            }
        }
        System.out.println("Añadiendo el nuevo...");
        dueño.getLocales().add(nuevo);
        System.out.println(nuevo);

        //Actualizamos en bd tambien
        if(localSeleccionadoEnTree){ //Editando
            almacen.actualizarDatosLocalBD(nuevo, instagramAntiguoDJ);
        }else{
            almacen.guardarLocalNuevoBD(nuevo, dueño);
        }

        //Actualizar JTree
        root.removeAllChildren();
        modelo.reload();
        cargarLocalesAlTree(dueño, root);
    }

    private void clearForm(JTextField tNombre, JTextField tDireccion, JTextField tTelefono, JTextField tWeb, JSpinner sCp, JSpinner sAforo, JSpinner sPrecio, JSpinner sEdad){

        tNombre.setText("");
        tDireccion.setText("");
        tTelefono.setText("");
        tWeb.setText("");
        sCp.getModel().setValue(0);
        sAforo.getModel().setValue(0);
        sEdad.getModel().setValue(0);
        sPrecio.getModel().setValue(0);


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
            ModificarLocales ventana = new ModificarLocales(almacen, null);
            ventana.setVisible(true);
        });
    }
}
