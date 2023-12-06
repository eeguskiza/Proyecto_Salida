package org.proyectosalida.GUI.Salida2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JButton;

public class VentanaSalimos {

    private JFrame frame;
    private JTable tBaresRecomendados;
    private JMenuBar menuBarFiltro;
    private JMenu mnFiltro;
    private JRadioButtonMenuItem rdbtnmntmTodos;
    private JRadioButtonMenuItem rdbtnmntmAlMenosUno;

    private JScrollPane scrollPane;

    private JPanel pPrincipal;
    private JPanel pCarSeleccionadas;
    private JPanel pCentro;
    private JPanel pCOeste;
    private JPanel pCEste;
    private JPanel pTabla;
    private JPanel pNorte;
    private JButton btnVolver;

    private JComboBox<Object> cbCarSeleccionadas;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaSalimos window = new VentanaSalimos();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VentanaSalimos() {
        initialize();

        btnVolver.addActionListener(e -> {
            // Añadir logica para volver a la ventana de seleccion
            frame.setVisible(false);

        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBarFiltro = new JMenuBar();
        frame.getContentPane().add(menuBarFiltro, BorderLayout.NORTH);

        ButtonGroup bg = new ButtonGroup();

        mnFiltro = new JMenu("Filtrar");
        mnFiltro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        menuBarFiltro.add(mnFiltro);

        rdbtnmntmTodos = new JRadioButtonMenuItem("Todos");
        rdbtnmntmTodos.setHorizontalAlignment(SwingConstants.RIGHT);
        rdbtnmntmTodos.setFont(new Font("Segoe UI", Font.BOLD, 13));
        mnFiltro.add(rdbtnmntmTodos);
        bg.add(rdbtnmntmTodos);

        rdbtnmntmAlMenosUno = new JRadioButtonMenuItem("Al menos uno");
        rdbtnmntmAlMenosUno.setFont(new Font("Segoe UI", Font.BOLD, 13));
        mnFiltro.add(rdbtnmntmAlMenosUno);
        bg.add(rdbtnmntmAlMenosUno);

        pPrincipal = new JPanel();
        frame.getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new BorderLayout(0, 0));

        scrollPane = new JScrollPane();
        pPrincipal.add(scrollPane, BorderLayout.EAST);

        pCarSeleccionadas = new JPanel();
        pPrincipal.add(pCarSeleccionadas, BorderLayout.NORTH);

        cbCarSeleccionadas = new JComboBox<Object>();
        cbCarSeleccionadas.setModel(new DefaultComboBoxModel<Object>(new String[] {" CARACTERÍSTICAS SELECCIONADAS "}));
        cbCarSeleccionadas.setFont(new Font("Calibri", Font.BOLD, 12));
        cbCarSeleccionadas.setMaximumRowCount(5);
        cbCarSeleccionadas.setToolTipText("");
        pCarSeleccionadas.add(cbCarSeleccionadas);


        pCentro = new JPanel();
        pPrincipal.add(pCentro, BorderLayout.CENTER);
        pCentro.setLayout(new BorderLayout(0, 0));

        pCOeste = new JPanel();
        pCentro.add(pCOeste, BorderLayout.WEST);

        pCEste = new JPanel();
        pCentro.add(pCEste, BorderLayout.EAST);

        pTabla = new JPanel();
        pCentro.add(pTabla, BorderLayout.CENTER);

        tBaresRecomendados = new JTable();
        pTabla.add(tBaresRecomendados);

        pNorte = new JPanel();
        pPrincipal.add(pNorte, BorderLayout.SOUTH);

        btnVolver = new JButton("Volver a la selección");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
        pNorte.add(btnVolver);
    }

}
