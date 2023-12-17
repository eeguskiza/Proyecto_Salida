package org.proyectosalida.Pruebas;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.Bienvenido;
import org.proyectosalida.GUI.VentanasCliente.VentanaVisitas;
import org.proyectosalida.GUI.VentanasDueño.ModificarLocales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPersonal extends JFrame {

    private Usuario usuario;
    private AlmacenDeDatos almacen;

    public MenuPersonal(Usuario u, AlmacenDeDatos almacenDeDatos, JFrame padre) {
        setTitle("Menú Personal: NOMBRE");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        almacen = almacenDeDatos;

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
        if(usuario.getClass().equals(Cliente.class)){
            panel.add(clickableLabel("Lista de Visitados", 3));
            panel.add(clickableLabel("Próximos Eventos", 4));
        }else{
            panel.add(clickableLabel("Añade Locales", 5));
            panel.add(clickableLabel("Configura Proximos Eventos", 6));
        }
        panel.add(clickableLabel("Ajustes", 1));
        panel.add(clickableLabel("Cerrar Sesión", 1));
        panel.add(new JPanel());


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




    public void editarPerfil(Usuario usuario, Boolean editable){
        EditarPerfil ventanaEditar = new EditarPerfil(almacen, editable, this);
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
                if(code==1){ //Cerrar Sesion
                    dispose();
                    Bienvenido vBienvenido = new Bienvenido();
                }else if (code==3){ //Lista de visitados
                    new VentanaVisitas(almacen, getPadre()).setVisible(true);
                    setVisible(false);
                }else if (code==6){ //Conf. Proximos eventos (Probando editar perfiil)
                    EditarPerfil ep = new EditarPerfil( almacen,false, getPadre());
                    setVisible(false);
                }else if(code==5){ //modifica locales
                    ModificarLocales modificarLocales = new ModificarLocales(almacen, getPadre());
                    modificarLocales.setVisible(true);
                    setVisible(false);

                /*}else if(code==8){ //Ver todos los locales en una jtable
                    System.out.println("Code:8");
                    VerLocales ventanaVerLocales = new VerLocales((Dueño) usuario, almacen);
                    ventanaVerLocales.setVisible(true);
                  */
                }else if (code==2){
                    abrirVentanaAjustes(usuario); //Mas facil si lo hago desde afuera del listener
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


    protected JFrame getPadre(){
        return this;
    }

    private void abrirVentanaAjustes(Usuario usuario){
        VentanaAjustes ventanaAjustes = new VentanaAjustes(usuario, almacen, this);
        ventanaAjustes.frame.setVisible(true);
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
            new MenuPersonal(almacen.getUsuariosPrueba().get(1), almacen, null).setVisible(true);
        });
    }
}
