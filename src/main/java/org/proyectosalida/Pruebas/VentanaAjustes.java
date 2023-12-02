package org.proyectosalida.Pruebas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.CardLayout;

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



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaAjustes window = new VentanaAjustes();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentanaAjustes() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 4));

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
}

