package org.proyectosalida.Pruebas;

import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;

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

    JFrame frame;

    private JPanel pPreferencias;
    private JPanel pPrincipal;
    private JPanel pCentro;
    private JPanel pTitulo;
    private JPanel pPermitir;
    private JPanel pFiltrar;
    private JPanel pBotonVolver;
    private JPanel pNorte;

    private JLabel lblGestorDe;
    private JLabel lblNombreCuenta;
    private JLabel lblPermitir;
    private JLabel lblFiltrar;
    private JLabel lblPreferencias;

    private JButton btnBotonVolver;
    private JFrame padre;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaGestionNotificaciones window = new VentanaGestionNotificaciones(null);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentanaGestionNotificaciones(JFrame p) {
        padre = p;
        initialize();



    }


    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 4));
        frame.setLocationRelativeTo(null);

        pPrincipal = new JPanel();
        frame.getContentPane().add(pPrincipal);
        pPrincipal.setLayout(new GridLayout(5, 1));

        pTitulo = new JPanel();
        pPrincipal.add(pTitulo);
        pTitulo.setLayout(new GridLayout(2, 1));

        lblGestorDe = new JLabel("      Gestor de");
        lblGestorDe.setVerticalAlignment(SwingConstants.BOTTOM);
        lblGestorDe.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pTitulo.add(lblGestorDe);

        lblNombreCuenta = new JLabel("    Notificaciones");
        lblNombreCuenta.setVerticalAlignment(SwingConstants.TOP);
        lblNombreCuenta.setFont(new Font("Tahoma", Font.BOLD, 20));
        pTitulo.add(lblNombreCuenta);

        pPermitir = new JPanel();
        pPrincipal.add(pPermitir);
        pPermitir.setLayout(new GridLayout(1, 2));

        lblPermitir = new JLabel("      Permitir notificaciones");
        lblPermitir.setFont(new Font("Tahoma", Font.BOLD, 13));
        pPermitir.add(lblPermitir);

        pFiltrar = new JPanel();
        pPrincipal.add(pFiltrar);
        pFiltrar.setLayout(new GridLayout(1, 0, 0, 0));

        lblFiltrar = new JLabel("      Filtrar");
        lblFiltrar.setFont(new Font("Tahoma", Font.BOLD, 13));
        pFiltrar.add(lblFiltrar);

        pPreferencias = new JPanel();
        pPrincipal.add(pPreferencias);
        pPreferencias.setLayout(new GridLayout(1, 2));

        lblPreferencias = new JLabel("      Preferencias");
        lblPreferencias.setFont(new Font("Tahoma", Font.BOLD, 13));
        pPreferencias.add(lblPreferencias);

        pBotonVolver = new JPanel();
        pPrincipal.add(pBotonVolver);
        pBotonVolver.setLayout(new BorderLayout(0, 0));

        pCentro = new JPanel();
        pBotonVolver.add(pCentro, BorderLayout.CENTER);

        btnBotonVolver = new JButton("Volver");
        btnBotonVolver.setVerticalAlignment(SwingConstants.BOTTOM);
        btnBotonVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        pCentro.add(btnBotonVolver);

        pNorte = new JPanel();
        pBotonVolver.add(pNorte, BorderLayout.NORTH);

        btnBotonVolver.addActionListener(e -> {
            padre.setVisible(true);
            frame.dispose();
        });

    }

}

