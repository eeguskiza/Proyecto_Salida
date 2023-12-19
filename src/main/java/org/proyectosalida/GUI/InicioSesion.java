package org.proyectosalida.GUI;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.Salida2.MainMenuDuenio;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;
import org.proyectosalida.GUI.VentanasDueño.VerLocales;
import org.proyectosalida.Datos.Credenciales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
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
                    System.out.println(2);
                }

                passPanel.add(verContraseña, BorderLayout.EAST);
                passPanel.revalidate();
                passPanel.repaint();
            }
        });

        aceptar.addActionListener(e -> {
            boolean esDueño = rbtnDueño.isSelected();

            // Muestra un mensaje de carga
            JOptionPane mensajeCarga = new JOptionPane("Verificando credenciales...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
            JDialog dialog = mensajeCarga.createDialog("Cargando");

            // SwingWorker para manejar la operación en segundo plano
            SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    if (esDueño) {
                        return inicioSesionDueño(idField.getText(), String.valueOf(passwordField.getPassword()), dueño, almacen);
                    } else {
                        return inicioSesionCliente(idField.getText(), String.valueOf(passwordField.getPassword()), cliente, almacen);
                    }
                }

                @Override
                protected void done() {
                    try {
                        boolean exito = get();
                        dialog.dispose();
                        if (exito) {
                            String mensaje = esDueño ? "Inicio de sesión exitoso como Dueño." : "Inicio de sesión exitoso como Cliente.";
                            JOptionPane.showMessageDialog(InicioSesion.this, mensaje);
                            if (esDueño) {
                                new VerLocales(dueño, almacenDeDatos).setVisible(true);
                            } else {
                                new MainMenuCliente(almacen, null).setVisible(true);
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
            dialog.setVisible(true);
        });



        cancelar.addActionListener(e -> {
            this.setVisible(false);
            padre.setVisible(true);
        });

        this.add(panel, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

    }

    public static boolean inicioSesionDueño(String usuario, String contraseña, Dueño dueño, AlmacenDeDatos almacen) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "SELECT * FROM DUEÑO WHERE ID = ? AND Contraseña = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, usuario);
                pstmt.setString(2, contraseña);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("ID");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDO");
                    Date fechaNacimiento = rs.getDate("FECHANACIMIENTO");
                    String contraseña2 = rs.getString("CONTRASEÑA");
                    String telefono = rs.getString("TELEFONO");
                    String email = rs.getString("EMAIL");

                    dueño.setId(id);
                    dueño.setNombre(nombre);
                    dueño.setApellido(apellido);
                    dueño.setFechaNacimiento(fechaNacimiento);
                    dueño.setContraseña(contraseña2);
                    dueño.setTelefono(telefono);
                    dueño.setCorreo(email);

                    //Metodo flexible tanto para usuario como dueño, ahora se usa para dueño
                    cargarLocales(conn, true, dueño, almacen);

                    // Imprimir los valores de cada fila si se encuentra un dueño
                    System.out.println("Dueño encontrado: ID: " + id + ", Nombre: " + nombre);
                    System.out.println(dueño);
                    almacen.getUsuarios().add(dueño);
                    return true;
                } else {
                    System.out.println("No se encontró el dueño con el ID y contraseña proporcionados.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean inicioSesionCliente(String usuario, String contraseña, Cliente cliente, AlmacenDeDatos almacen) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "SELECT * FROM CLIENTE WHERE ID = ? AND Contraseña = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, usuario);
                pstmt.setString(2, contraseña);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("ID");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDO");
                    Date fechaNacimiento = rs.getDate("FECHANACIMIENTO");
                    String contraseña2 = rs.getString("CONTRASEÑA");
                    String telefono = rs.getString("TELEFONO");
                    String email = rs.getString("EMAIL");

                    cliente.setId(id);
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setFechaNacimiento(fechaNacimiento);
                    cliente.setContraseña(contraseña2);
                    cliente.setTelefono(telefono);
                    cliente.setCorreo(email);

                    cargarLocales(conn, false, null, almacen);
                    // Imprimir los valores de cada fila si se encuentra un dueño
                    System.out.println("Cliente encontrado: ID: " + id + ", Nombre: " + nombre);
                    System.out.println(cliente);
                    almacen.getUsuarios().add(cliente);
                    return true;
                } else {
                    System.out.println("No se encontró el Cliente con el ID y contraseña proporcionados.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    private static void cargarLocales(Connection conn, Boolean isDueño, Dueño dueño, AlmacenDeDatos almacenDeDatos) {
        // Obtener todos los locales a su nombre en BAR o todos los bares dependiendo si es cliente o dueño el que inicia sesion
        String sqlLocalesBar = "";
        if(isDueño){
            sqlLocalesBar = "SELECT * FROM bar WHERE dueñoid = ?";
        }else{
            sqlLocalesBar = "SELECT * FROM bar";
        }

        try (PreparedStatement pstmtLocales = conn.prepareStatement(sqlLocalesBar)) {
            if(isDueño){
                pstmtLocales.setString(1, dueño.getId());
            }
            ResultSet rsLocales = pstmtLocales.executeQuery();

            // Procesar locales de tipo bar
            while (rsLocales.next()) {
                String idBar = rsLocales.getString("ID");
                String nombreBar = rsLocales.getString("NOMBRE");
                String direccion = rsLocales.getString("DIRECCION");
                String cp = rsLocales.getString("CODIGOPOSTAL");
                int aforo = rsLocales.getInt("AFORO");
                String telefonoBar = rsLocales.getString("TELEFONO");
                int mediaedad = rsLocales.getInt("MEDIAEDAD");
                int preciomedio = rsLocales.getInt("PRECIOMEDIO");
                String link = rsLocales.getString("LINKWEB");
                int terrazaNum = rsLocales.getInt("TIENETERRAZA");
                boolean terraza = (terrazaNum == 1);

                Bar bar = new Bar();
                bar.setId(idBar);
                bar.setNombre(nombreBar);
                bar.setDireccion(direccion);
                bar.setAforo(aforo);
                bar.setTelefono(telefonoBar);
                bar.setMediaEdad(mediaedad);
                bar.setPrecioMedio(preciomedio);
                bar.setWeb(link);
                bar.setTerraza(terraza);
                bar.setCP(cp);

                if(isDueño){
                    dueño.getLocales().add(bar);
                }else{
                    almacenDeDatos.getLocales().add(bar);
                }
                System.out.println("1 BAR añadido: "+bar.getNombre());
            }

            // Obtener todos los locales a su nombre en Discoteca o todas las Discotecas
            String sqlLocalesDisco = "";
            if(isDueño){
                sqlLocalesDisco = "SELECT * FROM discoteca WHERE dueñoid = ?";
            }else{
                sqlLocalesDisco = "SELECT * FROM discoteca";
            }
            try (PreparedStatement pstmtLocalesDisco = conn.prepareStatement(sqlLocalesDisco)) {
                if(isDueño){
                    pstmtLocalesDisco.setString(1, dueño.getId());
                }
                ResultSet rsLocalesDisco = pstmtLocalesDisco.executeQuery();

                // Procesar locales de tipo discoteca
                while (rsLocalesDisco.next()) {
                    String idDisco = rsLocalesDisco.getString("ID");
                    String nombreDisco = rsLocalesDisco.getString("NOMBRE");
                    String direccionDisco = rsLocalesDisco.getString("DIRECCION");
                    String cpDisco = rsLocalesDisco.getString("CODIGOPOSTAL");
                    int capacidad = rsLocalesDisco.getInt("CAPACIDAD");
                    String telefonoDisco = rsLocalesDisco.getString("TELEFONO");
                    int mediaEdadDisco = rsLocalesDisco.getInt("MEDIAEDAD");
                    int precioMedioDisco = rsLocalesDisco.getInt("PRECIOMEDIO");
                    String linkDisco = rsLocalesDisco.getString("LINKWEB");

                    Discoteca disco = new Discoteca();
                    disco.setId(idDisco);
                    disco.setNombre(nombreDisco);
                    disco.setDireccion(direccionDisco);
                    disco.setCP(cpDisco);
                    disco.setAforo(capacidad);
                    disco.setTelefono(telefonoDisco);
                    disco.setMediaEdad(mediaEdadDisco);
                    disco.setPrecioMedio(precioMedioDisco);
                    disco.setWeb(linkDisco);

                    if(isDueño){
                        dueño.getLocales().add(disco);
                    }else{
                        almacenDeDatos.getLocales().add(disco);
                    }
                    System.out.println("1 DISCOTECA añadida: "+disco.getNombre());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(!isDueño){
            almacenDeDatos.ininializarValoresEncuesta(); //Los valores de encuesta se ponen a 0 inicialmente
        }

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
