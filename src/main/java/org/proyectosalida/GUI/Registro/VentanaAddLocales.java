package org.proyectosalida.GUI.Registro;

import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasGenerales.InicioSesion;
import org.proyectosalida.GUI.Salida1.VentSelectCarac;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaAddLocales extends JFrame {
    private ArrayList<Horario> horarios = new ArrayList<>();
    private ArrayList<Caracteristica> caracteristicasSelecionadas;
    private DJ djResidente;
    private DJ djInvitado;
    private String enlace;

    public VentanaAddLocales(Dueño dueño, AlmacenDeDatos almacenDeDatos) {
        setTitle("Añadir locales");
        setSize(550, 750);
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
        JSpinner sCp = new JSpinner(); sCp.getModel().setValue(48000);
        panel.add(sCp);
        panel.add(new JLabel("Aforo:", JLabel.CENTER));
        JSpinner sAforo = new JSpinner(); sAforo.getModel().setValue(50);
        panel.add(sAforo);
        panel.add(new JLabel("Teléfono:", JLabel.CENTER));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Media de Edad:", JLabel.CENTER));
        JSpinner sEdad = new JSpinner(); sEdad.getModel().setValue(20);
        panel.add(sEdad);
        panel.add(new JLabel("Media de Precio:", JLabel.CENTER));
        JSpinner sPrecio = new JSpinner(); sPrecio.getModel().setValue(48000);
        panel.add(sPrecio);
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
            try {
                String nombre = ((JTextField) panel.getComponent(3)).getText();
                String direccion = ((JTextField) panel.getComponent(5)).getText();
                String CP = ((SpinnerNumberModel) sCp.getModel()).getNumber().toString();
                int aforo = Integer.parseInt(((SpinnerNumberModel) sAforo.getModel()).getNumber().toString());
                String telefono = ((JTextField) panel.getComponent(11)).getText();
                int mediaEdad = Integer.parseInt(((SpinnerNumberModel) sEdad.getModel()).getNumber().toString());
                int mediaPrecio = Integer.parseInt(((SpinnerNumberModel) sPrecio.getModel()).getNumber().toString());
                Boolean terraza = null;
                Local local;

                if (bbar.isSelected()) {
                    if (checkboxSi.isSelected()) {
                        terraza = true;
                    } else if (checkboxNo.isSelected()) {
                        terraza = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione si el local tiene terraza o no.");
                        return;
                    }
                    local = new Bar(nombre, direccion, CP, aforo, telefono, mediaEdad, mediaPrecio, enlace, horarios, terraza, caracteristicasSelecionadas);
                } else if (bdisco.isSelected()) {
                    local = new Discoteca(nombre, direccion, CP, aforo, telefono, mediaEdad, mediaPrecio, enlace, horarios, djResidente, djInvitado, caracteristicasSelecionadas);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de local.");
                    return;
                }

                boolean exito = almacenDeDatos.guardarLocalNuevoBD(local, dueño);

                if (exito) {
                    int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea añadir más locales?", "Añadir más locales", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        this.dispose();
                        new VentanaAddLocales(dueño, almacenDeDatos).setVisible(true);
                    } else {
                        this.dispose();
                        JOptionPane.showMessageDialog(this, "Proceso completado.", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
                        new InicioSesion(null, almacenDeDatos, false).setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo agregar el local.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos numéricos ingresados.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

