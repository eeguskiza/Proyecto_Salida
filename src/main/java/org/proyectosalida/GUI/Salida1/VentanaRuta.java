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

import static org.proyectosalida.Datos.AlmacenDeDatos.logger;

public class VentanaRuta extends JFrame {

    HashedMap<Local, Integer> itinerario;

    public VentanaRuta(ArrayList<Caracteristica> caracteristicasSeleccionadas, AlmacenDeDatos almacen, Salida salida, Integer nmax){
        setSize(700,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        itinerario = new HashedMap<>();

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo); add(tabla, BorderLayout.CENTER);
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



        setVisible(true);
    }

    private void encontrarLocalAux(ArrayList<Local> locales, ArrayList<Caracteristica> caracteristicasSeleccionadas, Integer max, ArrayList<Local> seleccionados){
        if(seleccionados.size() > max){
            return;
        }

        for(Local local : locales){ //Todos los que hay en nuestra bd
            for(Caracteristica caracteristica : local.getCaracteristicas()){
                if(caracteristicasSeleccionadas.contains(caracteristica) && !seleccionados.contains(local)){
                    seleccionados.add(local);
                    encontrarLocalAux(locales, caracteristicasSeleccionadas, max, seleccionados);
                }
                //Le falta algo por aqui para poder sacar todas las combinaciones posibles creo...
            }
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
