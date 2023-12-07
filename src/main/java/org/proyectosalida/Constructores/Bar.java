package org.proyectosalida.Constructores;

import java.util.ArrayList;
import java.util.Map;


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

    public static Bar fromMap(Map<String, Object> localData) {
        String nombre = (String) localData.get("Nombre");
        String direccion = (String) localData.get("Direccion");
        String CP = (String) localData.get("CP");
        int Aforo = ((Long) localData.get("Aforo")).intValue();
        String telefono = (String) localData.get("Telefono");
        int MediaEdad = ((Long) localData.get("MediaEdad")).intValue();
        int PrecioMedio = ((Long) localData.get("PrecioMedio")).intValue();
        String web = (String) localData.get("Web");
        Boolean terraza = (Boolean) localData.get("Terraza");

        ArrayList<Horario> horarios = new ArrayList<>();
        ArrayList<Map<String, Object>> horariosData = (ArrayList<Map<String, Object>>) localData.get("horarios");
        if (horariosData != null) {
            for (Map<String, Object> horarioData : horariosData) {
                // FALTARIA UN .FROMMAP PARA LOS HORARIOS TAMBIEN
                Horario horario = new Horario(/* pasar los datos del mapa al constructor de Horario */);
                horarios.add(horario);
            }
        }

        ArrayList<Caracteristica> caracteristicas = new ArrayList<>();
        ArrayList<Map<String, Object>> caracteristicasData = (ArrayList<Map<String, Object>>) localData.get("caracteristicas");
        if (caracteristicasData != null) {
            for (Map<String, Object> caracteristicaData : caracteristicasData) {
                // FALTA .FROMMAP PARA CARACTERISTICAS TMB
                //caracteristicas.add();
            }
        }

        return new Bar(nombre, direccion, CP, Aforo, telefono, MediaEdad, PrecioMedio, web, horarios, terraza, caracteristicas);
    }



}
