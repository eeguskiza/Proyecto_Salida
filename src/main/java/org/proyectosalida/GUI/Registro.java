package org.proyectosalida.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import com.toedter.calendar.JDateChooser;
import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.Conexion;
import org.proyectosalida.Datos.Provider;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Registro extends JFrame {

    public Registro(JFrame padre, AlmacenDeDatos almacenDeDatos) {

        Object[] opciones = {"Cliente", "Dueño"};
        int seleccion = JOptionPane.showOptionDialog(null,
                "Seleccione el tipo de usuario:",
                "Tipo de Usuario",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                opciones, opciones[0]);

        if (seleccion == JOptionPane.CLOSED_OPTION) {
            return;
        }

        this.setTitle("Registro");
        this.setSize(500, 500);
        this.setLocationRelativeTo(padre);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout para los campos y etiquetas


        panel.add(new JLabel("ID:", JLabel.CENTER));
        panel.add(new JTextField(20)); //TODO EL @ DE ID HAY QUE PONERLO AFUERA DEL RECUADRO PERO COMO NO SE HACERLO PUES LO QUITO ERIK PONLO CUANDO LEAS ESTO

        panel.add(new JLabel("Nombre:", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Apellido:", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Fecha de Nacimiento:", JLabel.CENTER));
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        panel.add(dateChooser);

        panel.add(new JLabel("Contraseña:", JLabel.CENTER));
        panel.add(new JPasswordField(20));

        panel.add(new JLabel("Comfirmar contraseña:", JLabel.CENTER));
        panel.add(new JPasswordField(20));

        panel.add(new JLabel("Teléfono:", JLabel.CENTER));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Correo:", JLabel.CENTER));
        panel.add(new JTextField(20));

        JPanel panelBotones = new JPanel();
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");

        aceptar.addActionListener(e -> {
            String id = ((JTextField) panel.getComponent(1)).getText();
            String nombre = ((JTextField) panel.getComponent(3)).getText();
            String apellido = ((JTextField) panel.getComponent(5)).getText();
            String fechaNacimiento = null;

            Boolean tipoUsuarioSeleccionado = false; //false = DUEÑO, true = CLIENTE

            if (seleccion == 0) {
                tipoUsuarioSeleccionado = true;
                System.out.println("cliente");
            } else if (seleccion == 1) {
                tipoUsuarioSeleccionado = false;
                System.out.println("dueño");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un tipo de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Fecha check
            if (dateChooser.getDate() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                fechaNacimiento = dateFormat.format(dateChooser.getDate());
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de nacimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String contraseña = new String(((JPasswordField) panel.getComponent(9)).getPassword());
            String confirmarContraseña = new String(((JPasswordField) panel.getComponent(11)).getPassword());
            String telefono = ((JTextField) panel.getComponent(13)).getText();
            String correo = ((JTextField) panel.getComponent(15)).getText();


            if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                    contraseña.isEmpty() || !contraseña.equals(confirmarContraseña) ||
                    telefono.isEmpty() || correo.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Por favor, revise todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    if (tipoUsuarioSeleccionado.equals(false)) {

                        ArrayList<Horario> horariosMonty = new ArrayList<>();
                        horariosMonty.add(new Horario("Lunes", "07:30", "23:30"));
                        horariosMonty.add(new Horario("Martes", "07:30", "23:30"));
                        horariosMonty.add(new Horario("Miercoles", "07:30", "23:30"));
                        horariosMonty.add(new Horario("Jueves", "07:30", "23:30"));
                        horariosMonty.add(new Horario("Viernes", "07:30", "23:30"));
                        horariosMonty.add(new Horario("Sabado", "07:30", "23:30"));
                        horariosMonty.add(new Horario("Domingo", "07:30", "16:00"));
                        Bar Monty = new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, "", horariosMonty, true,null);

                        ArrayList<Local> localesErik = new ArrayList<>();
                        localesErik.add(Monty);


                        Dueño nuevoUsuario = new Dueño(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo, localesErik);
                        System.out.println(nuevoUsuario.toString());

                        //Ventana para guardar locales
                        VentanaAddLocales ventanaAddLocales = new VentanaAddLocales(nuevoUsuario);
                        almacenDeDatos.getUsuarios().put(nuevoUsuario.getId(), nuevoUsuario);
                    } else {
                        Cliente nuevoUsuario = new Cliente(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo, new ArrayList<>());
                        System.out.println(nuevoUsuario.toString());
                        guardarCliente(nuevoUsuario);
                        almacenDeDatos.getUsuarios().put(nuevoUsuario.getId(), nuevoUsuario);


                    }

                    JOptionPane.showMessageDialog(this, "Usuario creado con éxito! (Local)", "Éxito", JOptionPane.INFORMATION_MESSAGE);


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al crear el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                InicioSesion inicioSesion = new InicioSesion(padre, almacenDeDatos);
                this.dispose();
                padre.dispose();
                inicioSesion.setVisible(true);

            }
        });


        cancelar.addActionListener(e -> {
            this.dispose();
            padre.setVisible(true);
                });

        panelBotones.add(aceptar);
        panelBotones.add(cancelar);

        // Agregar el panel a la ventana
        this.add(panel);
        this.add(panelBotones, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void guardarCliente(Cliente cliente) {
        String id = cliente.getId();  // ID del documento

        try{
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", cliente.getNombre());
            datos.put("Apellido", cliente.getApellido());
            datos.put("Edad", cliente.getEdad());
            datos.put("Contraseña", cliente.getContraseña());
            datos.put("Teléfono", cliente.getTelefono());
            datos.put("Correo", cliente.getCorreo());
            datos.put("Visitas", cliente.getVisitas());

            Provider.guardarPersona("Cliente", id, datos);


        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción como prefieras
        }

        SwingUtilities.invokeLater(() -> {
            Conexion.conectar();
            new Registro(null, new AlmacenDeDatos());
        });

    }

}
