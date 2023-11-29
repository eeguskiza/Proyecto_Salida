package org.proyectosalida.Constructores;

import java.util.ArrayList;

public class Discoteca extends Local {
    private DJ djResidente;
    private DJ djInvitado;

    public Discoteca() {
    }

    public Discoteca(String nombre, String direccion, String CP, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, ArrayList<Horario> horarios, DJ djResidente, DJ djInvitado,ArrayList<Caracteristica>caracteristicas) {
        super(nombre, direccion, CP, Aforo, telefono, MediaEdad, PrecioMedio, web,horarios,caracteristicas );
        this.djResidente = djResidente;
        this.djInvitado = djInvitado;
    }

    public DJ getDjResidente() {
        return djResidente;
    }

    public void setDjResidente(DJ djResidente) {
        this.djResidente = djResidente;
    }

    public DJ getDjInvitado() {
        return djInvitado;
    }

    public void setDjInvitado(DJ djInvitado) {
        this.djInvitado = djInvitado;
    }

    @Override
    public String toString() {
        return "Discoteca {" +
                "\n\tID: '" + id + '\'' +
                "\n\tNombre: '" + nombre + '\'' +
                "\n\tDirección: '" + direccion + '\'' +
                "\n\tAforo: " + Aforo +
                "\n\tTeléfono: '" + telefono + '\'' +
                "\n\tMedia de Edad: " + MediaEdad +
                "\n\tPrecio Medio: " + PrecioMedio +
                "\n\tWeb: '" + web + '\'' +
                "\n\tHorarios: " + horarios +
                "\n\tDJ Residente: '" + djResidente.getNombre() + '\'' +
                "\n\tDJ Invitado: '" + djInvitado.getNombre() + '\'' +
                "\n}"+"\n\tWeb: '" + web + '\''+"\n\tcarcateristicas: "+caracteristicas;
    }

    /*
    Se debe restringir acceso de edad, de lunes a viernes +18 y sabados y domingos +21
     */

}
