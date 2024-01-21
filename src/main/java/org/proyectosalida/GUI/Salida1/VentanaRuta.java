package org.proyectosalida.GUI.Salida1;

import com.formdev.flatlaf.FlatLightLaf;
import org.apache.commons.collections4.map.HashedMap;
import org.proyectosalida.Constructores.*;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import static org.proyectosalida.Datos.AlmacenDeDatos.logger;

public class VentanaRuta extends JFrame {

    ArrayList<Local> resultado = new ArrayList<>();

    public VentanaRuta(ArrayList<Caracteristica> caracteristicasSeleccionadas, AlmacenDeDatos almacen, Salida salida, Integer nmax){
        setSize(700,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Ruta de locales para hoy, "+salida.getFecha()); titulo.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(titulo, BorderLayout.NORTH);

        JPanel paneltabla = new JPanel(new BorderLayout());
        DefaultTableModel modelo = new DefaultTableModel();

        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        paneltabla.add(scrollPane, BorderLayout.CENTER);
        paneltabla.setBorder(new EmptyBorder(20, 10, 10, 10));
        getContentPane().add(paneltabla, BorderLayout.CENTER);

        Object[] encabezados = {"Orden", "Local", "Dirección"};
        modelo.setColumnIdentifiers(encabezados);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40);

        resultado = encontrarLocalesDefinitivos(almacen.getLocales(), caracteristicasSeleccionadas, nmax);


        int index = 1;
        for(Local local : resultado){
            Object[] row = {index, local.getNombre(), local.getDireccion()};
            modelo.addRow(row);
            index++;
        }

        registarVisitas(resultado, salida, almacen);

        JButton volver = new JButton("Volver a inicio"); getContentPane().add(volver, BorderLayout.SOUTH);
        volver.addActionListener(e -> {
            dispose();
            new MainMenuCliente(almacen, null);
        });

        setVisible(true);
    }


    private void encontrarLocalAux(ArrayList<Local> locales, ArrayList<Caracteristica> caracteristicasSeleccionadas, int max, ArrayList<Local> seleccionados, ArrayList<Local> combinacionActual) {
        if (seleccionados.size() > max) {
            return;
        }

        for (Local local : locales) {
            if (!seleccionados.contains(local)) {
                seleccionados.add(local);
                encontrarLocalAux(locales, caracteristicasSeleccionadas, max, seleccionados, combinacionActual);
                seleccionados.remove(local);  // Backtracking: eliminar el último local para probar otras combinaciones
            }
        }

        // Al final de cada llamada recursiva, agrega la combinación actual a la lista de combinaciones
        combinacionActual.addAll(seleccionados);
    }

    private int calcularDistancia(Local local1, Local local2) {
        int codigoPostal1 = Integer.parseInt(local1.getCP());
        int codigoPostal2 = Integer.parseInt(local2.getCP());

        return Math.abs(codigoPostal1 - codigoPostal2);
    }

    private ArrayList<Local> encontrarLocalesDefinitivos(ArrayList<Local> locales, ArrayList<Caracteristica> caracteristicasSeleccionadas, int max) {
        ArrayList<Local> combinacionDefinitiva = new ArrayList<>();
        ArrayList<Local> combinacionActual = new ArrayList<>();
        int distanciaMinima = Integer.MAX_VALUE;

        encontrarLocalAux(locales, caracteristicasSeleccionadas, max, new ArrayList<>(), combinacionActual);

        int distanciaTotal;
        for (int i = 0; i < combinacionActual.size(); i += max) {
            ArrayList<Local> subCombinacion = new ArrayList<>(combinacionActual.subList(i, Math.min(i + max, combinacionActual.size())));

            if (tieneDuplicados(subCombinacion)) {
                continue;
            }

            distanciaTotal = calcularDistancia(subCombinacion.get(0), subCombinacion.get(subCombinacion.size() - 1));

            for (int j = 0; j < subCombinacion.size() - 1; j++) {
                distanciaTotal += calcularDistancia(subCombinacion.get(j), subCombinacion.get(j + 1));
            }

            if (distanciaTotal < distanciaMinima || combinacionDefinitiva.isEmpty()) {
                distanciaMinima = distanciaTotal;
                combinacionDefinitiva = new ArrayList<>(subCombinacion);
            }
        }

        return combinacionDefinitiva;
    }

    private boolean tieneDuplicados(ArrayList<Local> listaLocales) {
        HashSet<Local> set = new HashSet<>(listaLocales);
        return set.size() < listaLocales.size();
    }




    private void registarVisitas(ArrayList<Local> resultado, Salida salida, AlmacenDeDatos almacenDeDatos){
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        String horaFormateada = formatoHora.format(salida.getFecha());
        for (Local local : resultado){
            Visita nueva = new Visita(almacenDeDatos.getUsuarios().get(0).getId(), local, salida.getFecha(), horaFormateada, "");
            ((Cliente) almacenDeDatos.getUsuarios().get(0)).getVisitas().add(nueva);
        }
    }
    public static void main(String[] args) {
        try {
            // Establecer el look and feel de Nimbus
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            logger.warning("El look and feel no está soportado");
            logger.warning(e.getMessage());
            // Aquí puedes manejar la excepción o dejar que se use el look and feel predeterminado
        }

        SwingUtilities.invokeLater(() -> {
            new VentanaRuta(new ArrayList<Caracteristica>(), new AlmacenDeDatos(), new Salida(), 2);
        });
    }
}
