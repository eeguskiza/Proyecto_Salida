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

    private JFrame frame;


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

        JPanel pPrincipal = new JPanel();
        frame.getContentPane().add(pPrincipal);
        pPrincipal.setLayout(new GridLayout(5, 1));

        JPanel pTitulo = new JPanel();
        pPrincipal.add(pTitulo);
        pTitulo.setLayout(new GridLayout(2, 1));

        JLabel lblTuCuenta = new JLabel("      Tu cuenta:");
        lblTuCuenta.setVerticalAlignment(SwingConstants.BOTTOM);
        pTitulo.add(lblTuCuenta);

        JLabel lblNombreCuenta = new JLabel("   nombreCuenta");
        lblNombreCuenta.setVerticalAlignment(SwingConstants.TOP);
        lblNombreCuenta.setFont(new Font("Tahoma", Font.BOLD, 20));
        pTitulo.add(lblNombreCuenta);

        JPanel pAccesoCuenta = new JPanel();
        pPrincipal.add(pAccesoCuenta);
        pAccesoCuenta.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblAccesoCuenta = new JLabel("Informacion de la cuenta       ");
        lblAccesoCuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
        pAccesoCuenta.add(lblAccesoCuenta);

        JButton btnAccesoCuenta = new JButton(">");
        btnAccesoCuenta.setToolTipText("");
        btnAccesoCuenta.setFont(new Font("Calibri", Font.BOLD, 18));
        pAccesoCuenta.add(btnAccesoCuenta);

        JPanel pNotificaciones = new JPanel();
        pPrincipal.add(pNotificaciones);

        JLabel lblNotificaciones = new JLabel("Notificaciones                          ");
        lblNotificaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
        pNotificaciones.add(lblNotificaciones);

        JButton btnNotificaciones = new JButton(">");
        btnNotificaciones.setToolTipText("");
        btnNotificaciones.setFont(new Font("Calibri", Font.BOLD, 18));
        pNotificaciones.add(btnNotificaciones);

        JPanel pAyuda = new JPanel();
        pPrincipal.add(pAyuda);

        JLabel lblAyuda = new JLabel("Preguntas frecuentes             ");
        lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 14));
        pAyuda.add(lblAyuda);

        JButton btnAyuda = new JButton(">");
        btnAyuda.setToolTipText("");
        btnAyuda.setFont(new Font("Calibri", Font.BOLD, 18));
        pAyuda.add(btnAyuda);

        JPanel pBotonVolver = new JPanel();
        pPrincipal.add(pBotonVolver);
        pBotonVolver.setLayout(new BorderLayout(0, 0));

        JPanel pCentro = new JPanel();
        pBotonVolver.add(pCentro, BorderLayout.CENTER);

        JButton btnBotonVolver = new JButton("Volver");
        btnBotonVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        pCentro.add(btnBotonVolver);
    }
}

