package org.proyectosalida.GUI.Registro;

import org.proyectosalida.Constructores.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.MaskFormatter;


/* Horarios
        ArrayList<Horario> horariosMonty = new ArrayList<>();
        horariosMonty.add(new Horario("Lunes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Martes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Miercoles", "07:30", "23:30"));
        horariosMonty.add(new Horario("Jueves", "07:30", "23:30"));
        horariosMonty.add(new Horario("Viernes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Sabado", "07:30", "23:30"));
        horariosMonty.add(new Horario("Domingo", "07:30", "16:00"));
         */
public class VentanaAddHorarios extends JFrame {

    public VentanaAddHorarios(ArrayList<Horario> horarios, JFrame ventanaAnterior) {
        setTitle("Añadir horarios");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10)); // Layout para los campos y etiquetas

        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        try {
            // Formato de hora para el JFormattedTextField
            MaskFormatter formatter = new MaskFormatter("##:##");
            formatter.setPlaceholderCharacter('_'); // Carácter para el espacio en blanco

            for (String dia : diasSemana) {
                panel.add(new JLabel(dia + ":", JLabel.RIGHT));

                // Crear un SpinnerDateModel con la hora de apertura predeterminada (8:00 AM)
                SpinnerDateModel modelHoraInicio = new SpinnerDateModel();
                JSpinner horaInicioSpinner = new JSpinner(modelHoraInicio);
                horaInicioSpinner.setValue(getDateWithTime(8, 0)); // Establecer el valor inicial a las 8:00 AM

                // Crear un SpinnerDateModel con la hora de cierre predeterminada (11:00 PM)
                SpinnerDateModel modelHoraFin = new SpinnerDateModel();
                JSpinner horaFinSpinner = new JSpinner(modelHoraFin);
                horaFinSpinner.setValue(getDateWithTime(23, 0)); // Establecer el valor inicial a las 11:00 PM

                // Editores para el formato de hora
                JSpinner.DateEditor horaInicioEditor = new JSpinner.DateEditor(horaInicioSpinner, "HH:mm");
                JSpinner.DateEditor horaFinEditor = new JSpinner.DateEditor(horaFinSpinner, "HH:mm");

                horaInicioSpinner.setEditor(horaInicioEditor);
                horaFinSpinner.setEditor(horaFinEditor);

                panel.add(horaInicioSpinner);
                panel.add(horaFinSpinner);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JButton añadir = new JButton("Guardar horarios");
        JButton volver = new JButton("Volver");

        añadir.addActionListener(e -> {
            // Lógica para guardar los horarios
            for (int i = 0; i < panel.getComponentCount(); i += 3) {
                String dia = ((JLabel) panel.getComponent(i)).getText().replace(":", "");
                JSpinner horaInicioSpinner = (JSpinner) panel.getComponent(i + 1);
                JSpinner horaFinSpinner = (JSpinner) panel.getComponent(i + 2);

                String horaInicio = formatSpinnerValue(horaInicioSpinner);
                String horaFin = formatSpinnerValue(horaFinSpinner);

                horarios.add(new Horario(dia, horaInicio, horaFin));

            }
            System.out.println(horarios);
            dispose();
            ventanaAnterior.setVisible(true);
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                ventanaAnterior.setVisible(true);
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(añadir);
        panelBotones.add(volver);

        // Agregar paneles al frame
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
    private Date getDateWithTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    private String formatSpinnerValue(JSpinner spinner) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedValue = formatter.format(spinner.getValue());
        return formattedValue;
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
            VentanaAddHorarios ventana = new VentanaAddHorarios(null, null);
            ventana.setVisible(true);
        });
    }
}

