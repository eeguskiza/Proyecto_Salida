package Constructores;

import java.util.ArrayList;

public class Dueño extends Usuario{
    private ArrayList <Local> locales;

    public Dueño() {
        super();
        this.locales = new ArrayList<>();
    }

    public Dueño(String id, String Nombre, String Apellido, int Edad, String Contraseña, String Telefono, String Correo, ArrayList <Local> locales) {
        super(id, Nombre, Apellido, Edad, Contraseña, Telefono, Correo);
        this.locales = new ArrayList<>();
    }

    public ArrayList<Local> getLocales() {
        return locales;
    }

    public void setLocales(ArrayList<Local> locales) {
        this.locales = locales;
    }

    @Override
    public String toString() {
        return "Dueño{" +
                "locales=" + locales +
                ", id='" + id + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Edad=" + Edad +
                ", Contraseña='" + Contraseña + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Correo='" + Correo + '\'' +
                '}';
    }

}
