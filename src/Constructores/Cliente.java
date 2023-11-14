package Constructores;

import java.util.ArrayList;

public class Cliente extends Usuario{
    private ArrayList<Local> visitas;

    // Constructor vacio
    public Cliente() {
        super();
        this.visitas = new ArrayList<>();
    }

    // Constructor con parametros
    public Cliente(String id, String nombre, String apellido, String fechaNacimiento, String contraseña, String telefono, String correo, ArrayList<Visita> visitas) {
        super(id, nombre, apellido, fechaNacimiento, contraseña, telefono, correo);
        this.visitas = new ArrayList<>();
    }

    // Getters y Setters
    public ArrayList<Local> getVisitas() {
        return visitas;
    }

    public void setVisitas(ArrayList<Local> visitas) {
        this.visitas = visitas;
    }

    @Override
    public String toString() {
        String result = "Cliente {" +
                "\n\tID: '" + id + '\'' +
                "\n\tNombre: '" + Nombre + '\'' +
                "\n\tApellido: '" + Apellido + '\'' +
                "\n\tEdad: " + Edad +
                "\n\tContraseña: '" + Contraseña + '\'' +
                "\n\tTeléfono: '+34 " + Telefono + '\'' +
                "\n\tCorreo: '" + Correo + '\'' +
                "\n\tVisitas: ";

        for (Local local : visitas) {
            result += local.getNombre() + ", ";
        }

        // Remover la última coma y espacio si la lista no está vacía
        if (!visitas.isEmpty()) {
            result = result.substring(0, result.length() - 2);
        }

        result += "\n}";
        return result;
    }


}
