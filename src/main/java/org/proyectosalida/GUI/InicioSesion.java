package org.proyectosalida.GUI;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.Datos.Conexion;
import org.proyectosalida.Datos.Provider;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;
import org.proyectosalida.GUI.VentanasDueño.MainMenuDueño;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InicioSesion extends JFrame {

    private DefaultTableModel tableModel;
    private AlmacenDeDatos almacenDeDatos;
    Boolean viendoContraseña = false;
    JTextField textFieldContraseña = new JTextField();
    protected JTable tabla;
    protected JTable tabla2;


    public InicioSesion(JFrame padre){
        almacenDeDatos = new AlmacenDeDatos();
        tabla = new JTable();
        tabla2 = new JTable();
        Provider.cargarTablaDueño(tabla);
        Provider.cargarTablaCliente(tabla2);
        this.setTitle("Inicia Sesión");
        this.setSize(500, 200);
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

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        panelBotones.add(aceptar);
        panelBotones.add(cancelar);

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
                    System.out.println(2);
                }

                passPanel.add(verContraseña, BorderLayout.EAST);
                passPanel.revalidate();
                passPanel.repaint();
            }
        });

        aceptar.addActionListener(e -> {
            checkCredentials(tabla,tabla2,idField, passwordField);
            //checkCredentials2(idField, passwordField);
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
            Conexion.conectar();
            new InicioSesion(null).setVisible(true);
        });
    }

    private void checkCredentials(JTable tabla, JTable tabla2, JTextField ID, JPasswordField password) { //SOLO PARA DUEÑOS DE MOMENTO
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) tabla2.getModel();
        char[] passChar = password.getPassword();
        String passwordString = new String(passChar);
        String usuarioID = ID.getText();

        boolean credencialesValidas = false;
        boolean esDueño = false;
        int indice = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            String tablaID = modelo.getValueAt(i, 0).toString();
            String tablaPassword = modelo.getValueAt(i, 5).toString();

            if (tablaID.equals(usuarioID) && tablaPassword.equals(passwordString)) {
                esDueño = true;
                indice = i;
                break;
            }
        }

        for (int i = 0; i < modelo2.getRowCount(); i++) {
            String tablaID = modelo2.getValueAt(i, 0).toString();
            String tablaPassword = modelo2.getValueAt(i, 5).toString();

            if (tablaID.equals(usuarioID) && tablaPassword.equals(passwordString)) {
                credencialesValidas = true;
                indice = i;
                break;
            }
        }

        if(esDueño || credencialesValidas) {
            if (esDueño) {
                System.out.println("Credenciales válidas. Acceso concedido.");
                String tablaID = modelo.getValueAt(indice, 0).toString();
                String tablaNombre = modelo.getValueAt(indice, 1).toString();
                String tablaApellido = modelo.getValueAt(indice, 2).toString();
                String tablaTelefono = modelo.getValueAt(indice, 3).toString();
                String tablaCorreo = modelo.getValueAt(indice, 4).toString();
                String tablaPassword = modelo.getValueAt(indice, 5).toString();
                Double tablaEdad = Double.parseDouble(modelo.getValueAt(indice, 6).toString());
                ArrayList<Local> tablaLocales = (ArrayList<Local>) modelo.getValueAt(indice, 7);

                Dueño usuario = new Dueño(tablaID, tablaNombre, tablaApellido, new GregorianCalendar(2004, Calendar.AUGUST, 8).getTime(), tablaPassword, tablaTelefono, tablaCorreo, new ArrayList<Local>());
                System.out.println(usuario);
                almacenDeDatos.setDueño(usuario);
                almacenDeDatos.setEsDueño(true);
                new MainMenuDueño(almacenDeDatos);
                dispose();
            } else {
            }
            if (credencialesValidas) {
                System.out.println("Credenciales válidas. Acceso concedido.");
                String tablaID = modelo2.getValueAt(indice, 0).toString();
                String tablaNombre = modelo2.getValueAt(indice, 1).toString();
                String tablaApellido = modelo2.getValueAt(indice, 2).toString();
                String tablaTelefono = modelo2.getValueAt(indice, 3).toString();
                String tablaCorreo = modelo2.getValueAt(indice, 4).toString();
                String tablaPassword = modelo2.getValueAt(indice, 5).toString();
                Double tablaEdad = Double.parseDouble(modelo2.getValueAt(indice, 6).toString());
                ArrayList<Visita> tablaVisitas = (ArrayList<Visita>) modelo2.getValueAt(indice, 7);

                Cliente usuario = new Cliente(tablaID, tablaNombre, tablaApellido, new GregorianCalendar(2004, Calendar.AUGUST, 8).getTime(), tablaPassword, tablaTelefono, tablaCorreo, new ArrayList<Visita>());
                System.out.println(usuario);
                almacenDeDatos.setCliente(usuario);
                almacenDeDatos.setEsCliente(true);
                new MainMenuCliente(almacenDeDatos);
                dispose();
            } else {}
        } else {
            System.out.println("Credenciales inválidas. Acceso denegado.");
            JOptionPane.showMessageDialog(this, "Credenciales inválidas. Acceso denegado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



}
