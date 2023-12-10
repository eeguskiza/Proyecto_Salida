package org.proyectosalida.Pruebas;

import org.proyectosalida.Constructores.Cliente;
import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Constructores.Usuario;
import org.proyectosalida.Datos.AlmacenDeDatos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class VentanaAjustes {

    public JFrame frame;

    private JPanel pPrincipal;
    private JPanel pTitulo;
    private JPanel pAccesoCuenta;
    private JPanel pNotificaciones;
    private JPanel pAyuda;
    private JPanel pBotonVolver;
    private JPanel pCentro;

    private JLabel lblTuCuenta;
    private JLabel lblNombreCuenta;
    private JLabel lblAccesoCuenta;
    private JLabel lblNotificaciones;
    private JLabel lblAyuda;

    private JButton btnAccesoCuenta;
    private JButton btnNotificaciones;
    private JButton btnAyuda;
    private JButton btnBotonVolver;
    private Usuario usuario;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Cliente cliente = new Cliente();
                    cliente.setNombre("Maialen");
                    cliente.setId("Maialenblancoo");
                    VentanaAjustes window = new VentanaAjustes(cliente, new AlmacenDeDatos());
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentanaAjustes(Usuario usuario, AlmacenDeDatos almacen) {
        initialize(usuario);

        btnAccesoCuenta.addActionListener(e ->{
            EditarPerfil vep = new EditarPerfil(almacen, false, getPadre());
            vep.setVisible(true);
        });


        btnNotificaciones.addActionListener(e -> {
            //VentanaGestionNotificaciones v = new VentanaGestionNotificaciones();
            //v.frame.setVisible(true);

        });

        btnBotonVolver.addActionListener(e -> {
           // MenuPersonal mp = new MenuPersonal();
            //mp.setVisible(true);
            frame.setVisible(false);
        });
    }


    private void initialize(Usuario usuario) {
        frame = new JFrame();
        frame.setSize(350, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 4));
        frame.setTitle("Ajustes");

        pPrincipal = new JPanel();
        frame.getContentPane().add(pPrincipal);
        pPrincipal.setLayout(new GridLayout(5, 1));

        pTitulo = new JPanel();
        pPrincipal.add(pTitulo);
        pTitulo.setLayout(new GridLayout(2, 1));

        lblTuCuenta = new JLabel("      Tu cuenta:");
        lblTuCuenta.setVerticalAlignment(SwingConstants.BOTTOM);
        pTitulo.add(lblTuCuenta);

        lblNombreCuenta = new JLabel("   nombreCuenta");
        lblNombreCuenta.setText("   " + usuario.getId());
        lblNombreCuenta.setVerticalAlignment(SwingConstants.TOP);
        lblNombreCuenta.setFont(new Font("Tahoma", Font.BOLD, 20));
        pTitulo.add(lblNombreCuenta);

        pAccesoCuenta = new JPanel();
        pPrincipal.add(pAccesoCuenta);
        pAccesoCuenta.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblAccesoCuenta = new JLabel("Informacion de la cuenta       ");
        lblAccesoCuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
        pAccesoCuenta.add(lblAccesoCuenta);

        btnAccesoCuenta = new JButton(">");
        btnAccesoCuenta.setToolTipText("");
        btnAccesoCuenta.setFont(new Font("Calibri", Font.BOLD, 18));
        pAccesoCuenta.add(btnAccesoCuenta);

        pNotificaciones = new JPanel();
        pPrincipal.add(pNotificaciones);

        lblNotificaciones = new JLabel("Gestionar notificaciones          ");
        lblNotificaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
        pNotificaciones.add(lblNotificaciones);

        btnNotificaciones = new JButton(">");
        btnNotificaciones.setToolTipText("");
        btnNotificaciones.setFont(new Font("Calibri", Font.BOLD, 18));
        pNotificaciones.add(btnNotificaciones);

        pAyuda = new JPanel();
        pPrincipal.add(pAyuda);

        lblAyuda = new JLabel("Preguntas frecuentes             ");
        lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 14));
        pAyuda.add(lblAyuda);

        btnAyuda = new JButton(">");
        btnAyuda.setToolTipText("");
        btnAyuda.setFont(new Font("Calibri", Font.BOLD, 18));
        pAyuda.add(btnAyuda);

        pBotonVolver = new JPanel();
        pPrincipal.add(pBotonVolver);
        pBotonVolver.setLayout(new BorderLayout(0, 0));

        pCentro = new JPanel();
        pBotonVolver.add(pCentro, BorderLayout.CENTER);

        btnBotonVolver = new JButton("Volver");
        btnBotonVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        pCentro.add(btnBotonVolver);
    }


    protected JFrame getPadre(){
        return frame;
    }

}

