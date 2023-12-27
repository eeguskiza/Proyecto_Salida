package org.proyectosalida.Constructores;

import java.util.ArrayList;
import java.util.Map;

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
        String djresi = "";
        if(djResidente != null){
            djresi = djResidente.getNombre();
        }
        String djinvi = "";
        if(djInvitado != null){
            djinvi = djInvitado.getNombre();
        }
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
                "\n\tDJ Residente: '" + djresi + '\'' +
                "\n\tDJ Invitado: '" + djinvi + '\'' +
                "\n}"+"\n\tWeb: '" + web + '\''+"\n\tcarcateristicas: "+caracteristicas;
    }


    public static Discoteca fromMap(Map<String, Object> localData) {
        String nombre = (String) localData.get("Nombre");
        String direccion = (String) localData.get("Direccion");
        String CP = (String) localData.get("CP");
        int Aforo = ((Long) localData.get("Aforo")).intValue();
        String telefono = (String) localData.get("Telefono");
        int MediaEdad = ((Long) localData.get("MediaEdad")).intValue();
        int PrecioMedio = ((Long) localData.get("PrecioMedio")).intValue();
        String web = (String) localData.get("Web");

        ArrayList<Horario> horarios = new ArrayList<>();
        ArrayList<Map<String, Object>> horariosData = (ArrayList<Map<String, Object>>) localData.get("horarios");
        if (horariosData != null) {
            for (Map<String, Object> horarioData : horariosData) {
                // FALTARIA UN .FROMMAP PARA LOS HORARIOS TAMBIEN de momento vacio haber si esto funciona va
                Horario horario = new Horario(/* pasar los datos del mapa al constructor de Horario */);
                horarios.add(horario);
            }
        }

        DJ djResidente = new DJ();
        //FALTA OTRA OSTIA PARA RELLENAR ESTO

        DJ djInvitado = new DJ();
        //LO MISMO

        ArrayList<Caracteristica> caracteristicas = new ArrayList<>();
        ArrayList<Map<String, Object>> caracteristicasData = (ArrayList<Map<String, Object>>) localData.get("caracteristicas");
        if (caracteristicasData != null) {
            for (Map<String, Object> caracteristicaData : caracteristicasData) {
                // FALTA .FROMMAP PARA CARACTERISTICAS TMB
                //caracteristicas.add();
            }
        }

        // Devolver un nuevo objeto Local creado con los datos del mapa
        return new Discoteca(nombre, direccion, CP, Aforo, telefono, MediaEdad, PrecioMedio, web, horarios, djResidente, djInvitado, caracteristicas);
    }

    /*
    Se debe restringir acceso de edad, de lunes a viernes +18 y sabados y domingos +21
     */

}
