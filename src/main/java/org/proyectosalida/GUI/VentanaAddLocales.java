package org.proyectosalida.GUI;

import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Datos.Provider;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class VentanaAddLocales extends JFrame {


    public VentanaAddLocales(Dueño dueño) {
        setTitle("Añadir locales");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void guardarDueño(Dueño dueño) {
        String id = dueño.getId();  // ID del documento

        try{
            Map<String, Object> datos = new HashMap<>();
            datos.put("Nombre", dueño.getNombre());
            datos.put("Apellido", dueño.getApellido());
            datos.put("Edad", dueño.getEdad());
            datos.put("Contraseña", dueño.getContraseña());
            datos.put("Teléfono", dueño.getTelefono());
            datos.put("Correo", dueño.getCorreo());
            datos.put("Locales", dueño.getLocales());

            Provider.guardarPersona("Dueño", id, datos);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al registrar usuario");
            System.out.println("Error" + e.getMessage());
        }
    }
}
