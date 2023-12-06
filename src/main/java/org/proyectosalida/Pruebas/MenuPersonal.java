package org.proyectosalida.Pruebas;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.Datos.Provider;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;
import org.proyectosalida.GUI.VentanasDueño.VerLocales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class MenuPersonal extends JFrame {

    private Usuario usuario;
    private Boolean viewPassword = false;
    private JTextField contraTextField;
    private JFrame frame; //Es para volver de la tabla a ver perfil

    public MenuPersonal(Usuario u, AlmacenDeDatos almacenDeDatos, JFrame padre) {
        setTitle("Menú Personal: NOMBRE");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        /* NO ME FUNCIONABA CORRECTAMENTE LO HE TENIDO QUE QUITAR
        //Definimos si el usuario usando la ventana es dueño o cliente
        if(almacenDeDatos.getEsCliente()){ //Significa que el usuario es Dueño
            usuario = (Cliente) almacenDeDatos.getUsuarios().get(0);
        }else if(almacenDeDatos.getEsDueño()){
            usuario = (Dueño) almacenDeDatos.getUsuarios().get(0);
        }

         */
        usuario = u;

        // Panel principal con bordes y disposición de cuadrícula
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Encabezado con la imagen del usuario y sus datos
        JPanel pEncabezado = new JPanel(new BorderLayout(10, 0));
        add(pEncabezado, BorderLayout.NORTH);

        // PANEL IMAGEN DEL USUARIO
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        ImageIcon imagen = new ImageIcon("src/main/resources/images/default_profile.png"); // Ruta de tu imagen
        Image imageScaled = imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenEscalada = new ImageIcon(imageScaled);
        JLabel etiquetaImagen = new JLabel(imagenEscalada);
        panelIzquierdo.add(etiquetaImagen);

        // PANEL NOMBRE APELLIDOS DEL USUARIO
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel nombreLabel = new JLabel(usuario.getNombre(), SwingConstants.CENTER);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel apellidosLabel = new JLabel(usuario.getApellido(), SwingConstants.CENTER);
        apellidosLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panelDerecho.add(nombreLabel);
        panelDerecho.add(apellidosLabel);

        pEncabezado.add(panelIzquierdo, BorderLayout.WEST);
        pEncabezado.add(panelDerecho, BorderLayout.CENTER);

        // ------------- APARTADOS DEL MENU - ETIQUETAS CLICKEABLES ---------------------
        panel.add(clickableLabel("Ver Perfil", 1));
        panel.add(clickableLabel("Ajustes", 2));
        if(usuario.getClass().equals(Cliente.class)){
            panel.add(clickableLabel("Lista de Visitados", 3));
            panel.add(clickableLabel("Próximos Eventos", 4));
        }else{
            panel.add(clickableLabel("Añade Locales", 5));
            panel.add(clickableLabel("Configura Proximos Eventos", 6));
        }

        //Boton Atras
        JButton atras = new JButton("Atras");
        atras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                padre.setVisible(true);
            }
        });
        panel.add(atras);


        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }



    //METODOS PARA EDITAR PERFIL (OPCION 1)
    private void editarPerfil(Usuario usuario, Boolean editable){
        System.out.println(editable);
        frame = new JFrame();
        frame.setTitle("Menú Personal: " + usuario.getNombre());
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        ImageIcon icon_hid = new ImageIcon("src/main/resources/images/password_hidden.jpg"); Image image_hid = icon_hid.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon icon_sho = new ImageIcon("src/main/resources/images/password_shown.jpg"); Image image_sho = icon_sho.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);

        int nRowsMenu = 7;
        if(usuario.getClass().equals(Dueño.class)){
            nRowsMenu = 8;
        }

        JPanel  main = new JPanel(new GridLayout(nRowsMenu,2,10,10));
        frame.add(main);
        JPanel panelContraseña = new JPanel(new BorderLayout()); JButton verContraseña = new JButton(new ImageIcon(image_hid)); verContraseña.setBackground(Color.WHITE); JPasswordField passwordField = new JPasswordField(this.usuario.getContraseña()); panelContraseña.add(passwordField); panelContraseña.add(verContraseña, BorderLayout.EAST); passwordField.setEditable(editable); passwordField.setEnabled(editable);
        main.add(new JLabel("ID (@)", JLabel.CENTER)); JTextField idfield = new JTextField(this.usuario.getId()); main.add(idfield); idfield.setEditable(editable); idfield.setEnabled(editable);
        main.add(new JLabel("Nombre", JLabel.CENTER)); JTextField nombrefield = new JTextField(this.usuario.getNombre());main.add(nombrefield); nombrefield.setEditable(editable); nombrefield.setEnabled(editable);
        main.add(new JLabel("Apellido", JLabel.CENTER));JTextField apellidofield = new JTextField(this.usuario.getApellido()); main.add(apellidofield);apellidofield.setEditable(editable); apellidofield.setEnabled(editable);
        main.add(new JLabel("Edad", JLabel.CENTER));JTextField edadfield = new JTextField(this.usuario.getEdad(this.usuario.getFechaNacimiento())); main.add(edadfield); edadfield.setEditable(editable); edadfield.setEnabled(editable);
        main.add(new JLabel("Contraseña", JLabel.CENTER)); main.add(panelContraseña);
        main.add(new JLabel("Tlf.", JLabel.CENTER)); JTextField tlffield = new JTextField(this.usuario.getTelefono()); main.add(tlffield); tlffield.setEditable(editable); tlffield.setEnabled(editable);
        main.add(new JLabel("Correo", JLabel.CENTER)); JTextField correofield = new JTextField(this.usuario.getCorreo());main.add(correofield); correofield.setEditable(editable); correofield.setEnabled(editable);
        main.add(new JLabel("Locales", JLabel.CENTER));
        if(usuario.getClass().equals(Dueño.class)){
            if(editable){
                main.add(clickableLabel("Modificar Locales", 7)); //MODIFICAR -> UN JTREE CON UN PANEL INDIVIDUAL AL LADO PARA SELECCIONAR UNO Y EDITARLO
            }else{
                main.add(clickableLabel("Ver todos", 8)); //VER LOCALES -> JTABLE CON TODOS ENLISTADOS
            }
        }

        JPanel botonera = new JPanel(new FlowLayout());
        JButton atras = new JButton("Atzerakarga");
        JButton guardarCambios = new JButton("Actualizar");
        botonera.add(atras);
        if(editable==true){
            botonera.add(guardarCambios);
        }
        frame.add(botonera, BorderLayout.SOUTH);
        frame.setVisible(true);

        //METODOS DEL PANEL EDITAR DATOS
        verContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPassword = !viewPassword;
                if(viewPassword){
                    verContraseña.setIcon(new ImageIcon(image_sho));
                    panelContraseña.remove(panelContraseña.getComponent(0));
                    contraTextField = new JTextField(MenuPersonal.this.usuario.getContraseña());
                    panelContraseña.add(contraTextField);
                    contraTextField.setEditable(editable); contraTextField.setEnabled(editable);
                }else{
                    verContraseña.setIcon(new ImageIcon(image_hid));
                    panelContraseña.removeAll();
                    JPasswordField pf = new JPasswordField(MenuPersonal.this.usuario.getContraseña()); pf.setEditable(editable); pf.setEnabled(editable);
                    panelContraseña.add(pf);
                    panelContraseña.add(verContraseña, BorderLayout.EAST);
                }
                frame.revalidate();
                frame.repaint();

            }
        });

        guardarCambios.addActionListener(e -> {
            String id = ((JTextField) main.getComponent(1)).getText();
            String nombre = ((JTextField) main.getComponent(3)).getText();
            String apellido = ((JTextField) main.getComponent(5)).getText();
            int edad = Integer.parseInt(((JTextField) main.getComponent(7)).getText());
            String contraseña = "";
            String telefono = ((JTextField) main.getComponent(11)).getText();
            String correo = ((JTextField) main.getComponent(13)).getText();
            ArrayList<Local> locales = new ArrayList<>();
            Dueño dueño = new Dueño(id, nombre, apellido, null, contraseña, telefono, correo, locales);
            System.out.println(dueño);
            actualizar(dueño);

        });

        atras.addActionListener(e ->{
            frame.dispose();
            this.setVisible(true);
        });

    }

   // private void

    private void actualizar(Dueño dueño) {
        String id = dueño.getId();  // ID del documento

        try{
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", dueño.getNombre());
            datos.put("Apellido", dueño.getApellido());
            datos.put("Edad", dueño.getEdad());
            datos.put("Contraseña", dueño.getContraseña());
            datos.put("Teléfono", dueño.getTelefono());
            datos.put("Correo", dueño.getCorreo());
            datos.put("Locales", dueño.getLocales());

            Provider.actualizarPersona("Dueño", id, datos);
            System.out.println("Usuario actualizado correctamente");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            System.out.println("Error" + e.getMessage());
        }
    }



    private JLabel clickableLabel(String text, int codigo) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(245, 245, 245));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        final int code = codigo;
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(code==1){ //editar perfil
                    editarPerfil(usuario, false);
                    setVisible(false);
                }else if(code==7){ //modifica locales
                    System.out.println("Code:7");

                }else if(code==8){ //Ver todos los locales en una jtable
                    System.out.println("Code:8");
                    VerLocales ventanaVerLocales = new VerLocales((Dueño) usuario, frame);
                    ventanaVerLocales.setVisible(true);
                }else if (code==2){
                    VentanaAjustes ventanaAjustes = new VentanaAjustes();
                    ventanaAjustes.frame.setVisible(true);
                    setVisible(false);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(new Color(245, 245, 245));
            }
        });

        return label;
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
            new MenuPersonal(almacen.getUsuarios().get(0), almacen, null).setVisible(true);
        });
    }
}
