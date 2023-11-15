package app;

import java.util.ArrayList;

public class Cliente extends Usuario{
    protected ArrayList<Visitas>visitas=new ArrayList<Visitas>();

    public ArrayList<Visitas> getVisitas() {
        return visitas;
    }

    public void setVisitas(ArrayList<Visitas> visitas) {
        this.visitas = visitas;
    }

    public Cliente(String nombre, String apellido, String tlf, String correo, int edad, String contraseña, ArrayList<Visitas> visitas) {
        super(nombre, apellido, tlf, correo, edad, contraseña);
        this.visitas = visitas;
    }

    public Cliente() {
        super("nombre", "apellido", "tlf", "correo", 18, "contraseña");
        this.visitas = null;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                 super.toString() +
                "} " +"visitas=" +visitas;
    }
}

