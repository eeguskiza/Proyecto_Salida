package app;

import java.util.ArrayList;

public class Propietario extends Usuario{
    protected ArrayList<Local>locales;

    public ArrayList<Local> getLocales() {
        return locales;
    }

    public void setLocales(ArrayList<Local> locales) {
        this.locales = locales;
    }

    public Propietario(String nombre, String apellido, String tlf, String correo, int edad, String contraseña, ArrayList<Local> locales) {
        super(nombre, apellido, tlf, correo, edad, contraseña);
        this.locales = locales;
    }
    public Propietario() {
        super("nombre", "apellido", "tlf", "correo", 35, "contraseña");
        this.locales = null;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                  super.toString()+
                "}"+"locales="+ locales;
    }
}
