package org.proyectosalida.Constructores;

import java.util.ArrayList;


public class Bar extends Local {
    private Boolean terraza;


    public Bar() {
        super();
    }

    public Bar(String nombre, String direccion, String CP, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, ArrayList <Horario> horarios, Boolean terraza,ArrayList <Caracteristica> caracteristicas) {
        super(nombre, direccion,CP, Aforo, telefono, MediaEdad, PrecioMedio, web, horarios,caracteristicas);
        this.terraza = terraza;
    }




    public Boolean getTerraza() {
        return terraza;
    }

    public void setTerraza(Boolean terraza) {
        this.terraza = terraza;
    }

    @Override
    public String toString() {
        String result = "Bar {" +
                "\n\tID: '" + id + '\'' +
                "\n\tNombre: '" + nombre + '\'' +
                "\n\tDirección: '" + direccion + '\'' +
                "\n\tAforo: " + Aforo +
                "\n\tTeléfono: '" + telefono + '\'' +
                "\n\tMedia de Edad: " + MediaEdad +
                "\n\tPrecio Medio: " + PrecioMedio +
                "\n\tWeb: '" + web + '\''+"\n\tcarcateristicas: "+caracteristicas;

        result += "\n\tHorarios:";
        for (Horario hora : horarios) {
            result += "\n\t\t" + hora.toString();
        }

        result += "\n\tTerraza: " + (terraza ? "Cuenta con terraza" : "No cuenta con terraza") +
                "\n}";
        return result;
    }



}
