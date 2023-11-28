package org.proyectosalida.GUI;

import org.proyectosalida.Constructores.Cliente;
import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Constructores.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPersonal extends JFrame {

    private Usuario u;
    private Boolean viewPassword = false;
    private MainMenu ventanaPadre;
    private JTextField contraTextField;

    public MenuPersonal(Usuario usuario, MainMenu padre) {
        setTitle("Menú Personal: " + usuario.getNombre());
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        ventanaPadre = padre;
        u = usuario;

        // Panel principal con bordes y disposición de cuadrícula
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Encabezado con la imagen del usuario y sus datos
        JPanel pEncabezado = new JPanel(new BorderLayout(10, 0));
        add(pEncabezado, BorderLayout.NORTH);

        // Panel para la imagen del usuario
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        ImageIcon imagen = new ImageIcon("src/Recuros/default_profile.png"); // Ruta de tu imagen
        Image imageScaled = imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon imagenEscalada = new ImageIcon(imageScaled);
        JLabel etiquetaImagen = new JLabel(imagenEscalada);
        panelIzquierdo.add(etiquetaImagen);

        // Panel para el nombre y apellidos del usuario
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

        // Creación de etiquetas clickeables
        panel.add(clickableLabel("Editar Perfil", 1));
        panel.add(clickableLabel("Ajustes", 2));
        panel.add(clickableLabel("Lista de Visitados", 3));
        panel.add(clickableLabel("Próximos Eventos", 4));
        panel.add(clickableLabel("Bares Nuevos", 5));
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

    private JLabel clickableLabel(String text, int code) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(245, 245, 245));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(code==1){ //editar perfil
                    editarPerfil(u);
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

    private void editarPerfil(Usuario usuario){
        JFrame frame = new JFrame();
        frame.setTitle("Menú Personal: " + usuario.getNombre());
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        ImageIcon icon_hid = new ImageIcon("src/Recuros/password_hidden.jpg"); Image image_hid = icon_hid.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        ImageIcon icon_sho = new ImageIcon("src/Recuros/password_shown.jpg"); Image image_sho = icon_sho.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);


        JPanel main = new JPanel(new GridLayout(7,2,10,10)); frame.add(main);
        JPanel panelContraseña = new JPanel(new BorderLayout()); JButton verContraseña = new JButton(new ImageIcon(image_hid)); verContraseña.setBackground(Color.WHITE); panelContraseña.add(new JPasswordField(u.getContraseña())); panelContraseña.add(verContraseña, BorderLayout.EAST);
        main.add(new JLabel("ID (@)", JLabel.CENTER)); main.add(new JTextField(u.getId()));
        main.add(new JLabel("Nombre", JLabel.CENTER)); main.add(new JTextField(u.getNombre()));
        main.add(new JLabel("Apellido", JLabel.CENTER)); main.add(new JTextField(u.getApellido()));
        main.add(new JLabel("Edad", JLabel.CENTER)); main.add(new JTextField(u.getEdad()));
        main.add(new JLabel("Contraseña", JLabel.CENTER)); main.add(panelContraseña);
        main.add(new JLabel("Tlf.", JLabel.CENTER)); main.add(new JTextField(u.getTelefono()));
        main.add(new JLabel("Correo", JLabel.CENTER)); main.add(new JTextField(u.getCorreo()));

        JButton guardarCambios = new JButton("Actualizar");
        frame.add(guardarCambios, BorderLayout.SOUTH);
        frame.setVisible(true);

        //METODOS DEL PANEL EDITAR DATOS
        verContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPassword = !viewPassword;
                if(viewPassword){
                    verContraseña.setIcon(new ImageIcon(image_sho));
                    panelContraseña.remove(panelContraseña.getComponent(0));
                    contraTextField = new JTextField(u.getContraseña());
                    panelContraseña.add(contraTextField);
                }else{
                    verContraseña.setIcon(new ImageIcon(image_hid));
                    panelContraseña.removeAll();
                    panelContraseña.add(new JPasswordField(u.getContraseña()));
                    panelContraseña.add(verContraseña, BorderLayout.EAST);
                }
                frame.revalidate();
                frame.repaint();

            }
        });

        guardarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass="";

                if(viewPassword){
                    pass = contraTextField.getText();
                    System.out.println(pass);
                }else{
                    pass = new String(((JPasswordField) panelContraseña.getComponent(0)).getPassword());
                    System.out.println(pass);
                }

                if(u.getClass().equals(Dueño.class)){
                    Dueño d = (Dueño) u;
                    Dueño nuevo = new Dueño( ((JTextField) main.getComponent(1)).getText(), ((JTextField) main.getComponent(3)).getText(), ((JTextField) main.getComponent(5)).getText(), /*((JTextField) main.getComponent(7)).getText()*/ "2000-01-01", pass, ((JTextField) main.getComponent(11)).getText(), ((JTextField) main.getComponent(13)).getText(), d.getLocales());
                    ventanaPadre.getAlmacen().getUsuarios().remove(u.getId()); //elimina el antiguo
                    ventanaPadre.getAlmacen().getUsuarios().put(nuevo.getId(), nuevo); // añade el nuevo
                    frame.dispose();
                    setVisible(true);
                }else{
                    Cliente c =(Cliente) u;
                    Cliente nuevo = new Cliente( ((JTextField) main.getComponent(1)).getText(), ((JTextField) main.getComponent(3)).getText(), ((JTextField) main.getComponent(5)).getText(), /*((JTextField) main.getComponent(7)).getText()*/ "2000-01-01", pass, ((JTextField) main.getComponent(11)).getText(), ((JTextField) main.getComponent(13)).getText(), c.getVisitas());
                    ventanaPadre.getAlmacen().getUsuarios().remove(u.getId());
                    ventanaPadre.getAlmacen().getUsuarios().put(nuevo.getId(), nuevo);
                    frame.dispose();
                    setVisible(true);
                }
                for(Usuario u : ventanaPadre.getAlmacen().getUsuarios().values()){
                    System.out.println(u.toString());
                }
            }
        });

    }
    public static void main(String[] args) {
        // Configuración del look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MenuPersonal(new Cliente(), null).setVisible(true));
    }
}
