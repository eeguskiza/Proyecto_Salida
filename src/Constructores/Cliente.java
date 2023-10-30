package Constructores;

import java.util.ArrayList;

public class Cliente extends Usuario{
    private ArrayList<Visita> visitas;

    // Constructor vacio
    public Cliente() {
        super();
        this.visitas = new ArrayList<>();
    }

    // Constructor con parametros
    public Cliente(String id, String nombre, String apellido, int edad, String contraseña, String telefono, String correo, ArrayList<Visita> visitas) {
        super(id, nombre, apellido, edad, contraseña, telefono, correo);
        this.visitas = new ArrayList<>();
    }

    // Getters y Setters
    public ArrayList<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(ArrayList<Visita> visitas) {
        this.visitas = visitas;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Cliente{" + "visitas=" + visitas + '}';
    }
}
