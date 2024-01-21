package org.proyectosalida.GUI.Salida1;

import com.formdev.flatlaf.FlatLightLaf;
import org.apache.commons.collections4.map.HashedMap;
import org.proyectosalida.Constructores.Caracteristica;
import org.proyectosalida.Constructores.Local;
import org.proyectosalida.Constructores.Salida;
import org.proyectosalida.Datos.AlmacenDeDatos;
import org.proyectosalida.GUI.VentanasCliente.MainMenuCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import static org.proyectosalida.Datos.AlmacenDeDatos.logger;

public class VentanaRuta extends JFrame {

    ArrayList<ArrayList<Local>> itinerario;

    public VentanaRuta(ArrayList<Caracteristica> caracteristicasSeleccionadas, AlmacenDeDatos almacen, Salida salida, Integer nmax){
        setSize(700,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        itinerario = new ArrayList<>();

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tabla);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        Object[] encabezados = {"Orden", "Caracteristicas en Común", "Local"};
        modelo.setColumnIdentifiers(encabezados);

        //El metodo recursivo tiene en cuenta las caracteristicas que se encuentran en la lista de caracteristicasSeleccionadas y
        // teniendo en cuenta todos los locales y todas las caracteristicas que tienen, se hace una ruta (todas las combinaciones
        // posibles entre los locales con esas caracteristicas). El resultado se guardara en un hash map con el local y el numero
        // de caracteristicas comunes encontradas, es decir algun local tendra todas y algun otro tendra solo algunas. En la tabla
        // se ordenaran de mas a menos en comun encontradas.
        // Sino tambien se pueden ordenar por CP, es decir por cercania entre ellos. Si lo piensas tiene mas logica para una ruta
        // esto que lo de la cantidad de caracteristicas

        //Una vez la lista de locales seleccionados este completa es hacer un for para rellenar la tabla y registrar los locales en
        // la visita para que se registren

        ArrayList<Local> resutl = encontrarLocalesDefinitivos(almacen.getLocales(), caracteristicasSeleccionadas, nmax);

        for(Local local : resutl){
            System.out.println(local.getNombre());
        }


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

    // Método para calcular la distancia entre dos locales basándose en los códigos postales
    private int calcularDistancia(Local local1, Local local2) {
        // Supongamos que cada código postal es un número entero
        int codigoPostal1 = Integer.parseInt(local1.getCP());
        int codigoPostal2 = Integer.parseInt(local2.getCP());

        // Calcula la diferencia absoluta entre los códigos postales
        System.out.println("Distancia: "+Math.abs(codigoPostal1 - codigoPostal2));
        return Math.abs(codigoPostal1 - codigoPostal2);
    }

    // Método para encontrar la combinación definitiva de locales
    private ArrayList<Local> encontrarLocalesDefinitivos(ArrayList<Local> locales, ArrayList<Caracteristica> caracteristicasSeleccionadas, int max) {
        ArrayList<Local> combinacionDefinitiva = new ArrayList<>();
        ArrayList<Local> combinacionActual = new ArrayList<>();
        int distanciaMinima = Integer.MAX_VALUE;

        encontrarLocalAux(locales, caracteristicasSeleccionadas, max, new ArrayList<>(), combinacionActual);

        // Itera sobre todas las combinaciones encontradas y selecciona la que cumple con el criterio de distancia mínima
        int distanciaTotal;
        for (int i = 0; i < combinacionActual.size(); i += max) {
            ArrayList<Local> subCombinacion = new ArrayList<>(combinacionActual.subList(i, Math.min(i + max, combinacionActual.size())));

            // Verifica si hay locales duplicados en la subcombinación
            if (tieneDuplicados(subCombinacion)) {
                continue;  // Si hay duplicados, pasa a la siguiente subcombinación
            }

            distanciaTotal = calcularDistancia(subCombinacion.get(0), subCombinacion.get(subCombinacion.size() - 1));

            // Calcula la distancia total de la combinación actual
            for (int j = 0; j < subCombinacion.size() - 1; j++) {
                distanciaTotal += calcularDistancia(subCombinacion.get(j), subCombinacion.get(j + 1));
            }

            // Actualiza la combinación definitiva si cumple con los criterios
            if (distanciaTotal < distanciaMinima || combinacionDefinitiva.isEmpty()) {
                distanciaMinima = distanciaTotal;
                combinacionDefinitiva = new ArrayList<>(subCombinacion);
            }
        }

        return combinacionDefinitiva;
    }

    // Método para verificar si hay duplicados en una lista de locales
    private boolean tieneDuplicados(ArrayList<Local> listaLocales) {
        HashSet<Local> set = new HashSet<>(listaLocales);
        return set.size() < listaLocales.size();
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
