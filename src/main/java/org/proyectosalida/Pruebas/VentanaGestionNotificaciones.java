package org.proyectosalida.Pruebas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class VentanaGestionNotificaciones {

    private JFrame frame;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaGestionNotificaciones window = new VentanaGestionNotificaciones();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentanaGestionNotificaciones() {
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

        JLabel lblGestorDe = new JLabel("      Gestor de");
        lblGestorDe.setVerticalAlignment(SwingConstants.BOTTOM);
        lblGestorDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pTitulo.add(lblGestorDe);

        JLabel lblNombreCuenta = new JLabel("    Notificaciones");
        lblNombreCuenta.setVerticalAlignment(SwingConstants.TOP);
        lblNombreCuenta.setFont(new Font("Tahoma", Font.BOLD, 20));
        pTitulo.add(lblNombreCuenta);

        JPanel pPermitir = new JPanel();
        pPrincipal.add(pPermitir);
        pPermitir.setLayout(new GridLayout(1, 2));

        JLabel lblPermitir = new JLabel("      Permitir notificaciones");
        lblPermitir.setFont(new Font("Tahoma", Font.BOLD, 13));
        pPermitir.add(lblPermitir);

        JPanel pFiltrar = new JPanel();
        pPrincipal.add(pFiltrar);
        pFiltrar.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel lblFiltrar = new JLabel("      Filtrar");
        lblFiltrar.setFont(new Font("Tahoma", Font.BOLD, 13));
        pFiltrar.add(lblFiltrar);

        JPanel pPreferencias = new JPanel();
        pPrincipal.add(pPreferencias);
        pPreferencias.setLayout(new GridLayout(1, 2));

        JLabel lblNewLabel = new JLabel("      Preferencias");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        pPreferencias.add(lblNewLabel);

        JPanel pBotonVolver = new JPanel();
        pPrincipal.add(pBotonVolver);
        pBotonVolver.setLayout(new BorderLayout(0, 0));

        JPanel pCentro = new JPanel();
        pBotonVolver.add(pCentro, BorderLayout.CENTER);

        JButton btnBotonVolver = new JButton("Volver");
        btnBotonVolver.setVerticalAlignment(SwingConstants.BOTTOM);
        btnBotonVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
        pCentro.add(btnBotonVolver);

    }

}

