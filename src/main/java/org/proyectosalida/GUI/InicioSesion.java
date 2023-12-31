package org.proyectosalida.GUI;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;
import org.proyectosalida.GUI.VentanasDueño.VerLocales;
import org.slf4j.ILoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;


public class InicioSesion extends JFrame {

    private DefaultTableModel tableModel;
    private AlmacenDeDatos almacenDeDatos;
    Boolean viendoContraseña = false;
    JTextField textFieldContraseña = new JTextField();
    protected JTable tabla;
    protected JTable tabla2;
    private Boolean recordarSesion;
    private Properties prop;



    public InicioSesion(JFrame padre, AlmacenDeDatos almacen){
        almacenDeDatos = almacen;
        Properties properties = almacen.cargarPropiedades();

        Dueño dueño = new Dueño();
        Cliente cliente = new Cliente();
        recordarSesion = false;

        prop = almacen.cargarPropiedades();

        this.setTitle("Inicia Sesión");
        this.setSize(500, 300);
        this.setLocationRelativeTo(padre);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //MIrar si hay inicio de sesion guardado y si eso iniciar sesion directamente:
        if(!prop.isEmpty()){
            Boolean isdueño = new Boolean(prop.getProperty("esdueño"));
            String usuario = prop.getProperty("usuario");
            String contraseña = prop.getProperty("contraseña");

            iniciarSesion(isdueño, almacen, usuario, contraseña, dueño, cliente);
        }


        JPanel panelIDContraseña = new JPanel(new GridLayout(3, 2, 10, 10)); // Layout para los campos y etiquetas
        panelIDContraseña.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("ID:                                       @");
        JTextField idField = new JTextField(20);
        idField.setPreferredSize(new Dimension(200, 25));

        ImageIcon icon_hid = new ImageIcon("src/main/resources/images/password_hidden.jpg"); Image image_hid = icon_hid.getImage().getScaledInstance(23,16, Image.SCALE_SMOOTH);
        ImageIcon icon_sho = new ImageIcon("src/main/resources/images/password_shown.jpg"); Image image_sho = icon_sho.getImage().getScaledInstance(23,16, Image.SCALE_SMOOTH);


        JLabel passLabel = new JLabel("Contraseña:");
        JPanel passPanel = new JPanel(new BorderLayout());
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 25));
        JButton verContraseña = new JButton(); verContraseña.setBackground(Color.white); verContraseña.setIcon(new ImageIcon(image_hid));
        passPanel.add(passwordField); passPanel.add(verContraseña, BorderLayout.EAST);

        panelIDContraseña.add(idLabel);
        panelIDContraseña.add(idField);
        panelIDContraseña.add(passLabel);
        panelIDContraseña.add(passPanel);

        // Crear radio buttons
        JRadioButton rbtnCliente = new JRadioButton("Cliente", true); // Seleccionado por defecto
        JRadioButton rbtnDueño = new JRadioButton("Dueño");

        // Agrupar los radio buttons
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbtnCliente);
        grupo.add(rbtnDueño);

        // Panel para los radio buttons
        JPanel panelRadioButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelRadioButtons.add(rbtnCliente);
        panelRadioButtons.add(rbtnDueño);

        // Panel para los botones de aceptar y cancelar
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        panelBotones.add(aceptar);
        panelBotones.add(cancelar);

        //Boton si quiere recordar sesion
        JPanel panelCheck = new JPanel(new FlowLayout());
        JCheckBox checkRecordad = new JCheckBox("Recordar Sesión"); panelCheck.add(checkRecordad);
        checkRecordad.addActionListener(e -> {
            if(checkRecordad.isSelected()){
                recordarSesion = true;
                System.out.println(recordarSesion);
            }else{
                recordarSesion = false;
                System.out.println(recordarSesion);
            }
        });


        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.add(panelIDContraseña, BorderLayout.CENTER);
        panelMain.add(panelCheck, BorderLayout.SOUTH);

        this.add(panelRadioButtons, BorderLayout.NORTH);
        this.add(panelMain, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);


        verContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contraseña = String.valueOf(passwordField.getPassword());

                if (viendoContraseña) {
                    contraseña = textFieldContraseña.getText();
                    verContraseña.setIcon(new ImageIcon(image_hid));
                    passPanel.removeAll();
                    passwordField.setText(contraseña); // Restablece el texto en el JPasswordField
                    passPanel.add(passwordField);
                    viendoContraseña = false;
                } else {
                    verContraseña.setIcon(new ImageIcon(image_sho));
                    passPanel.removeAll();
                    textFieldContraseña = new JTextField(contraseña);
                    passPanel.add(textFieldContraseña);
                    viendoContraseña = true;
                    almacen.logger.info("Contraseña mostrada");
                }

                passPanel.add(verContraseña, BorderLayout.EAST);
                passPanel.revalidate();
                passPanel.repaint();
            }
        });

        aceptar.addActionListener(e -> {
            boolean esDueño = rbtnDueño.isSelected();
            iniciarSesion(esDueño, almacen, idField.getText(), new String(passwordField.getPassword()), dueño, cliente);
        });


        cancelar.addActionListener(e -> {
            this.setVisible(false);
            padre.setVisible(true);
        });

        this.add(panelBotones, BorderLayout.SOUTH);

    }


    private void iniciarSesion(Boolean esDueño, AlmacenDeDatos almacen, String id, String password, Dueño dueño, Cliente cliente){

        // Ocultar la ventana actual
        InicioSesion.this.setVisible(false);

        CustomOptionPaneLogin customOptionPaneLogin = new CustomOptionPaneLogin("Iniciando sesión...");


        // SwingWorker para manejar la operación en segundo plano
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                if (esDueño) {
                    return almacen.inicioSesionDueño(id, password, dueño, almacen);
                } else {
                    return almacen.inicioSesionCliente(id, password, cliente, almacen);
                }
            }

            @Override
            protected void done() {
                try {
                    boolean exito = get();
                    customOptionPaneLogin.dispose();
                    if (exito) {
                        if(recordarSesion){
                            System.out.println("Guardando propiedades?");
                            almacen.guardarPropiedades(id, password, esDueño);
                        }
                        if (esDueño) {
                            new VerLocales(dueño, almacenDeDatos).setVisible(true);
                        } else {
                            new MainMenuCliente(almacen, "https://www.openstreetmap.org/#map=17/43.27063/-2.93807").setVisible(true);
                        }
                        dispose();
                    } else {
                        // Mostrar la ventana de inicio de sesión nuevamente si el inicio de sesión es fallido
                        InicioSesion.this.setVisible(true);
                        JOptionPane.showMessageDialog(InicioSesion.this, "Inicio de sesión fallido. Verifique sus credenciales.");
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    // En caso de error, también mostrar la ventana de inicio de sesión
                    InicioSesion.this.setVisible(true);
                    ex.printStackTrace();
                }
            }
        };

        worker.execute();
        customOptionPaneLogin.show();
    }

    //org.Proyecto_Salida.Escritorio.Main de prueba
    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            new InicioSesion(null, new AlmacenDeDatos()).setVisible(true);
        });
    }



}
