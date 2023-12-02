package org.proyectosalida.GUI.Salida2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Choice;
import java.awt.BorderLayout;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

import org.ietf.jgss.ChannelBinding;

import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

public class VentanaSalimos {

    private JFrame frame;
    private JTable tBaresRecomendados;

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
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBarFiltro = new JMenuBar();
        frame.getContentPane().add(menuBarFiltro, BorderLayout.NORTH);

        JRadioButtonMenuItem rdbtnmntmFiltro = new JRadioButtonMenuItem("Filtrar");
        rdbtnmntmFiltro.setFont(new Font("Segoe UI", Font.BOLD, 13));
        rdbtnmntmFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
        menuBarFiltro.add(rdbtnmntmFiltro);

        JPanel pPrincipal = new JPanel();
        frame.getContentPane().add(pPrincipal, BorderLayout.CENTER);
        pPrincipal.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        pPrincipal.add(scrollPane, BorderLayout.EAST);

        JPanel pCarSeleccionadas = new JPanel();
        pPrincipal.add(pCarSeleccionadas, BorderLayout.NORTH);

        JComboBox cbCarSeleccionadas = new JComboBox();
        cbCarSeleccionadas.setModel(new DefaultComboBoxModel(new String[] {"CARACTERÍSTICAS SELECCIONADAS"}));
        cbCarSeleccionadas.setFont(new Font("Calibri", Font.BOLD, 12));
        cbCarSeleccionadas.setMaximumRowCount(5);
        cbCarSeleccionadas.setToolTipText("");
        pCarSeleccionadas.add(cbCarSeleccionadas);

        cbCarSeleccionadas.addItem("CARACTERÍSTICAS SELECCIONADAS");
        cbCarSeleccionadas.setSelectedItem(0);

        JPanel pCentro = new JPanel();
        pPrincipal.add(pCentro, BorderLayout.CENTER);
        pCentro.setLayout(new BorderLayout(0, 0));

        JPanel pCOeste = new JPanel();
        pCentro.add(pCOeste, BorderLayout.WEST);

        JPanel pCEste = new JPanel();
        pCentro.add(pCEste, BorderLayout.EAST);

        JPanel pTabla = new JPanel();
        pCentro.add(pTabla, BorderLayout.CENTER);

        tBaresRecomendados = new JTable();
        pTabla.add(tBaresRecomendados);
    }

}

