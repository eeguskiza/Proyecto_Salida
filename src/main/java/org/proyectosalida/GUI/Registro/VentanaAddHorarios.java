package org.proyectosalida.GUI.Registro;

import com.teamdev.jxbrowser.deps.org.checkerframework.checker.units.qual.A;
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

    public VentanaAddHorarios(ArrayList<Horario> horarios, JFrame ventanaAnterior, Boolean esNuevoElHorario) { //Si es false significa que estamos editando un horario de un arraylist
        setTitle("Añadir horarios");
        setSize(500, 300);
        setLocationRelativeTo(null);


        ArrayList<Integer> horasAperturas = new ArrayList<>();
        ArrayList<Integer> minutosAperturas = new ArrayList<>();
        ArrayList<Integer> horasCierre = new ArrayList<>();
        ArrayList<Integer> minutosCierre = new ArrayList<>();

        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10)); // Layout para los campos y etiquetas

        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

        try {
            // Formato de hora para el JFormattedTextField
            MaskFormatter formatter = new MaskFormatter("##:##");
            formatter.setPlaceholderCharacter('_'); // Carácter para el espacio en blanco

            //Miramos si el arraylist esta vacio en el caso de no haberlos iniciado en el registro
            if(horarios.isEmpty()){
                inicializarArrayHorarios(horarios); //ESto no deberia surgir ya que en registro es obligatorio hacerlo creo
            }

            //Saco los horarios en caso de haberlos
            if(!esNuevoElHorario){
                for(Horario horario : horarios){
                    String apertura = horario.getHoraInicio();
                    String[] horarioAperturaDividido = apertura.split(":");
                    horasAperturas.add(Integer.parseInt(horarioAperturaDividido[0]));
                    minutosAperturas.add(Integer.parseInt(horarioAperturaDividido[1]));

                    String cierre = horario.getHoraFin();
                    String[] horarioCierreDividido = cierre.split(":");
                    horasCierre.add(Integer.parseInt(horarioCierreDividido[0]));
                    minutosCierre.add(Integer.parseInt(horarioCierreDividido[1]));
                }
            }

            for (int numDia=0; numDia<diasSemana.length; numDia++) {
                String dia = diasSemana[numDia];
                panel.add(new JLabel(dia + ":", JLabel.RIGHT));

                // Crear un SpinnerDateModel con la hora de apertura predeterminada (8:00 AM)
                SpinnerDateModel modelHoraInicio = new SpinnerDateModel();
                JSpinner horaInicioSpinner = new JSpinner(modelHoraInicio);
                if(esNuevoElHorario){
                    horaInicioSpinner.setValue(getDateWithTime(8, 0)); // Establecer el valor inicial a las 8:00 AM
                }else{
                    horaInicioSpinner.setValue(getDateWithTime(horasAperturas.get(numDia), minutosAperturas.get(numDia))); // Establecer el valor inicial a el del ArrayList original
                }

                // Crear un SpinnerDateModel con la hora de cierre predeterminada (11:00 PM)
                SpinnerDateModel modelHoraFin = new SpinnerDateModel();
                JSpinner horaFinSpinner = new JSpinner(modelHoraFin);
                if(esNuevoElHorario){
                    horaFinSpinner.setValue(getDateWithTime(23, 0)); // Establecer el valor inicial a las 11:00 PM
                }else{
                    horaFinSpinner.setValue(getDateWithTime(horasCierre.get(numDia), minutosCierre.get(numDia)));
                }

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
            if(!esNuevoElHorario){
                horarios.clear();
            }
            for (int i = 0; i < panel.getComponentCount(); i += 3) {
                String dia = ((JLabel) panel.getComponent(i)).getText().replace(":", "");
                int ndia = -1;
                if (dia.equals("Lunes")) {
                    ndia = 1;
                } else if (dia.equals("Martes")) {
                    ndia = 2;
                } else if (dia.equals("Miércoles")) {
                    ndia = 3;
                } else if (dia.equals("Jueves")) {
                    ndia = 4;
                } else if (dia.equals("Viernes")) {
                    ndia = 5;
                } else if (dia.equals("Sábado")) {
                    ndia = 6;
                } else if (dia.equals("Domingo")) {
                    ndia = 7;
                }

                JSpinner horaInicioSpinner = (JSpinner) panel.getComponent(i + 1);
                JSpinner horaFinSpinner = (JSpinner) panel.getComponent(i + 2);

                String horaInicio = formatSpinnerValue(horaInicioSpinner);
                String horaFin = formatSpinnerValue(horaFinSpinner);

                horarios.add(new Horario(ndia, horaInicio, horaFin));

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

    private void inicializarArrayHorarios(ArrayList<Horario> horarios){
        for(int i=1; i<8; i++){
            Horario nuevo = new Horario(i, "12:00", "23:00");
            horarios.add(nuevo);
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
            VentanaAddHorarios ventana = new VentanaAddHorarios(null, null, false);
            ventana.setVisible(true);
        });
    }
}

