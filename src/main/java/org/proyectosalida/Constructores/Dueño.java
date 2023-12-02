package org.proyectosalida.Constructores;

import java.util.ArrayList;
import java.util.Date;

public class Dueño extends Usuario{
    private ArrayList <Local> locales;

    public Dueño() {
        super();
        this.locales = new ArrayList<>();
    }

    public Dueño(String id, String Nombre, String Apellido, Date fechaNacimiento, String Contraseña, String Telefono, String Correo, ArrayList <Local> locales) {
        super(id, Nombre, Apellido, fechaNacimiento, Contraseña, Telefono, Correo);
        this.locales = new ArrayList<>();
    }

    public ArrayList<Local> getLocales() {
        return locales;
    }

    public void agregarLocal(Local local) {
        if (locales == null) {
            locales = new ArrayList<>();
        }
        locales.add(local);
    }


    @Override
    public String toString() {
        String result = "Dueño {" +
                "\n\tID: '" + id + '\'' +
                "\n\tNombre: '" + Nombre + '\'' +
                "\n\tApellido: '" + Apellido + '\'' +
                "\n\tFecha Nacimiento: " + getFechaNacimiento() +
                "\n\tContraseña: '" + Contraseña + '\'' +
                "\n\tTeléfono: '+34 " + Telefono + '\'' +
                "\n\tCorreo: '" + Correo + '\'' +
                "\n\tLocales: ";

        for (Local local : locales) {
            result += local.getNombre() + ", ";
        }

        // Remover la última coma y espacio si la lista no está vacía
        if (!locales.isEmpty()) {
            result = result.substring(0, result.length() - 2);
        }

        result += "\n}";
        return result;
    }



}
