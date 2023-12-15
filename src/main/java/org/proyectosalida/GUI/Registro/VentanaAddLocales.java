package org.proyectosalida.GUI.Registro;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.InicioSesion;
import org.proyectosalida.GUI.Salida2.VentSelectCarac;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VentanaAddLocales extends JFrame {
    private ArrayList<Horario> horarios = new ArrayList<>();
    private ArrayList<Caracteristica> caracteristicasSelecionadas;
    private DJ djResidente;
    private DJ djInvitado;
    private String enlace;

    public VentanaAddLocales(Dueño dueño, AlmacenDeDatos almacenDeDatos) {
        setTitle("Añadir locales");
        setSize(500, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ejemplo local --> Bar Monty = new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, link1, horariosMonty, true,caracteristicasMonty);
        caracteristicasSelecionadas = new ArrayList<>();
        horarios = new ArrayList<>();
        djResidente = new DJ();
        djInvitado = new DJ();
        enlace = "";

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout para los campos y etiquetas

        JPanel pTipoLocal = new JPanel(new FlowLayout()); panel.add(new JLabel("Tipo:", JLabel.CENTER)); panel.add(pTipoLocal);
        JRadioButton bbar = new JRadioButton("BAR"); JRadioButton bdisco = new JRadioButton("DISCOTECA"); pTipoLocal.add(bbar); pTipoLocal.add(bdisco);
        ButtonGroup bg = new ButtonGroup(); bg.add(bbar); bg.add(bdisco);

        panel.add(new JLabel("Nombre:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Dirección:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Código Postal:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Aforo:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Teléfono:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Media de Edad:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Media de Precio:", JLabel.CENTER));
        panel.add(new JTextField(20));
        //panel.add(new JLabel("Página Web:", JLabel.CENTER));
        //panel.add(new JTextField(20));
        panel.add(new JLabel("Horarios:", JLabel.CENTER));
        JButton añadirH = new JButton("Añadir horarios");
        panel.add(añadirH);
        panel.add(new JLabel("Terraza:", JLabel.CENTER));
        //Si o no y se guarda boolean
        JPanel panel1 = new JPanel();
        JRadioButton checkboxSi = new JRadioButton("Si");
        JRadioButton checkboxNo = new JRadioButton("No");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(checkboxSi);
        grupo.add(checkboxNo);
        panel1.add(checkboxSi);
        panel1.add(checkboxNo);
        panel.add(panel1);
        panel.add(new JLabel("DJ Residente:", JLabel.CENTER));
        JButton añadirDjResidente = new JButton("Registrar DJ"); panel.add(añadirDjResidente);
        panel.add(new JLabel("DJ Invitado:", JLabel.CENTER));
        JButton añadirDjInvitado = new JButton("Registrar DJ"); panel.add(añadirDjInvitado);
        panel.add(new JLabel("Características:", JLabel.CENTER));
        JButton añadirC = new JButton("Añadir características");
        panel.add(añadirC);
        JButton botonGuardar = new JButton("Añadir local");
        panel.add(botonGuardar);
        JButton botonVolver = new JButton("Volver");
        panel.add(botonVolver);

        //LOGICA ENTRE BAR Y DISCOTECA
        bbar.addActionListener(e -> {
            añadirDjResidente.setEnabled(false);
            añadirDjInvitado.setEnabled(false);
            checkboxSi.setEnabled(true);
            checkboxNo.setEnabled(true);
        });
        bdisco.addActionListener(e -> {
            añadirDjResidente.setEnabled(true);
            añadirDjInvitado.setEnabled(true);
            checkboxSi.setEnabled(false);
            checkboxNo.setEnabled(false);
            grupo.clearSelection();
        });


        botonGuardar.addActionListener(e -> {

            String nombre = ((JTextField) panel.getComponent(3)).getText();
            String direccion = ((JTextField) panel.getComponent(5)).getText();
            String CP = ((JTextField) panel.getComponent(7)).getText();
            int aforo = Integer.parseInt(((JTextField) panel.getComponent(9)).getText());
            String telefono = ((JTextField) panel.getComponent(11)).getText();
            int mediaEdad = Integer.parseInt(((JTextField) panel.getComponent(13)).getText());
            int mediaPrecio = Integer.parseInt(((JTextField) panel.getComponent(15)).getText());
            //String enlace = ((JTextField) panel.getComponent(17)).getText();
            Boolean terraza = null;

            setVisible(false);
            enlace = JOptionPane.showInputDialog(null, "Introduce la URL que los clientes verán una vez seleccionen\n tu local para salir (P.E. GoogleMaps)", "Una ultima cosa...", JOptionPane.QUESTION_MESSAGE);

            if(bbar.isSelected()){
                if(checkboxSi.isSelected()){
                    terraza = true;
                }else if(checkboxNo.isSelected()){
                    terraza = false;
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione todos los campos.");

                }

                Bar bar = new Bar(nombre, direccion, CP, aforo, telefono, mediaEdad, mediaPrecio, enlace, horarios, terraza, caracteristicasSelecionadas);
                System.out.println(bar);
                dueño.getLocales().add(bar);
            }else if(bdisco.isSelected()){
                Discoteca disco = new Discoteca(nombre, direccion, CP, aforo, telefono, mediaEdad, mediaPrecio, enlace, horarios, djResidente, djInvitado, caracteristicasSelecionadas);
                dueño.getLocales().add(disco);
                System.out.println(disco);
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de local!");
            }

            //Preguntar si quiere añadirmas locales
            boolean pregunta  = JOptionPane.showConfirmDialog(null, "¿Quieres añadir más locales?", "Añadir locales", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            if (pregunta){
                dispose();
                VentanaAddLocales ventanaAddLocales = new VentanaAddLocales(dueño, almacenDeDatos);
                ventanaAddLocales.setVisible(true);
            }
            else{
                //GUARDAR EN LA NUBE EL DUEÑO --> AQUI UN METODO
                dispose();
                //REDIRIGE DIRECTAMENTE A INICIA SESION HABIENDO GUARDADO LOS DATOS EN LA NUBE
                JOptionPane.showMessageDialog(this, "Usuario creado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                InicioSesion inicioSesion = new InicioSesion(this, almacenDeDatos);
                inicioSesion.setVisible(true);
            }
        });

        botonVolver.addActionListener(e -> {
            //System.out.println(caracteristicas);
            dispose();
        });

        añadirH.addActionListener(e -> {
            VentanaAddHorarios ventanaAddHorarios = new VentanaAddHorarios(horarios, this,true);
            ventanaAddHorarios.setVisible(true);
            this.setVisible(false);

        });

        añadirC.addActionListener(e -> {
            VentSelectCarac vCaract = new VentSelectCarac(caracteristicasSelecionadas, true, almacenDeDatos, null);
        });

        añadirDjResidente.addActionListener(e -> {
           VentanaRegistrarDJ d1 = new VentanaRegistrarDJ(djResidente, this, true);
           d1.setVisible(true);
           setVisible(false);
        });

        añadirDjInvitado.addActionListener(e -> {
            VentanaRegistrarDJ d2 = new VentanaRegistrarDJ(djInvitado, this, true);
            d2.setVisible(true);
            setVisible(false);
        });


        add(panel);
    }


    private void guardarLocal() {


    }

    private void pedirURL(Dueño dueño, AlmacenDeDatos almacenDeDatos){
        JFrame frame = new JFrame();
        setLayout(new BorderLayout());
        setSize(700, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Una ultima cosa...");

        JPanel main = new JPanel(new BorderLayout()); add(main);
        main.add(new JLabel("Introduce la URL que los clientes verán una vez seleccionen\n tu local para salir (Por Ejemplo, GoogleMaps)"), BorderLayout.NORTH);
        JTextField textField = new JTextField(); main.add(textField, BorderLayout.CENTER);
        JButton guardarEnlace = new JButton("Registrar URL!"); main.add(guardarEnlace, BorderLayout.SOUTH);
        setVisible(true);

        guardarEnlace.addActionListener(e -> {
            enlace = textField.getText();
            frame.dispose();


        });

    } //Y guardar locales/Dueño añadidos tambien


    //Main de prueba
    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            VentanaAddLocales ventanaAddLocales = new VentanaAddLocales(new Dueño(), new AlmacenDeDatos());
            ventanaAddLocales.setVisible(true);
        });

    }
}
