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
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;


public class InicioSesion extends JFrame {

    private DefaultTableModel tableModel;
    private AlmacenDeDatos almacenDeDatos;
    Boolean viendoContraseña = false;
    JTextField textFieldContraseña = new JTextField();
    protected JTable tabla;
    protected JTable tabla2;



    public InicioSesion(JFrame padre, AlmacenDeDatos almacen){
        almacenDeDatos = almacen;
        Dueño dueño = new Dueño();
        Cliente cliente = new Cliente();

        this.setTitle("Inicia Sesión");
        this.setSize(500, 300);
        this.setLocationRelativeTo(padre);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // Layout para los campos y etiquetas
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        panel.add(idLabel);
        panel.add(idField);
        panel.add(passLabel);
        panel.add(passPanel);

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

        this.add(panelRadioButtons, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
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

            // Muestra un mensaje de carga
            /*
            JOptionPane mensajeCarga = new JOptionPane("Verificando credenciales...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
            JDialog dialog = mensajeCarga.createDialog("Cargando");

             */

            CustomOptionPaneLogin customOptionPaneLogin = new CustomOptionPaneLogin("Iniciando sesión...");

            // SwingWorker para manejar la operación en segundo plano
            SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    if (esDueño) {
                        return almacen.inicioSesionDueño(idField.getText(), String.valueOf(passwordField.getPassword()), dueño, almacen);
                    } else {
                        return almacen.inicioSesionCliente(idField.getText(), String.valueOf(passwordField.getPassword()), cliente, almacen);
                    }
                }

                @Override
                protected void done() {
                    try {
                        boolean exito = get();
                        //dialog.dispose();
                        customOptionPaneLogin.dispose();
                        if (exito) {
                            //String mensaje = esDueño ? "Inicio de sesión exitoso como Dueño." : "Inicio de sesión exitoso como Cliente.";
                            //JOptionPane.showMessageDialog(InicioSesion.this, mensaje);
                            if (esDueño) {
                                new VerLocales(dueño, almacenDeDatos).setVisible(true);
                            } else {
                                new MainMenuCliente(almacen, "https://www.openstreetmap.org/#map=17/43.27063/-2.93807").setVisible(true);
                            }
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(InicioSesion.this, "Inicio de sesión fallido. Verifique sus credenciales.");
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        ex.printStackTrace();
                    }
                }
            };

            worker.execute();
            //dialog.setVisible(true);
            customOptionPaneLogin.show();
        });



        cancelar.addActionListener(e -> {
            this.setVisible(false);
            padre.setVisible(true);
        });

        this.add(panel, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

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
