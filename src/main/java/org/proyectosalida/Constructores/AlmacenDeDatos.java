package org.proyectosalida.Constructores;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AlmacenDeDatos {
    protected HashMap<String, Usuario> usuarios;
    protected ArrayList<JProgressBar> progressBarsVotaciones;
    protected ArrayList<Integer> valoresVotaciones;
    private boolean votoDiarioEncuesta; //El boolean va aqui ya que sino siempre que se habra y cierre la main ventana se va a restablecer el valor, aqui no. Solo se puede una vez así.

    public AlmacenDeDatos(){
        //Inicializamos hashmap (con bd no sería necesario creo)
        usuarios = new HashMap<>();
        progressBarsVotaciones = new ArrayList<>();
        valoresVotaciones = new ArrayList<>();
        votoDiarioEncuesta = false;

        //Esto es para tener algo ya añadido
        Cliente eneko = new Cliente("eneko", "Eneko", "Alvarez", "2004-06-23", "eneko04", "634556788", "eneko.alvarez@opendeusto.es", new ArrayList<>());
        usuarios.put(eneko.getId(), eneko);

        Dueño erik = new Dueño("erik", "Erik", "Eguskiza", "2004-08-04", "erik04", "3456788989", "e.eguskiza@opendeusto.es", new ArrayList<>());
        usuarios.put(erik.getId(), erik);

        //muestra de votaciones de encuesta
        valoresVotaciones.add(10);valoresVotaciones.add(8);valoresVotaciones.add(6);valoresVotaciones.add(4);valoresVotaciones.add(2);valoresVotaciones.add(0);

    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(HashMap<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario(String id){
        Usuario uBuscado = null;
        for(Usuario u : usuarios.values()){
            if(u.getId().equals(id)){
                uBuscado = u;
            }
        }
        return uBuscado;
    }

    public ArrayList<JProgressBar> getProgressBarsVotaciones() {
        return progressBarsVotaciones;
    }
    public void setProgressBarsVotaciones(ArrayList<JProgressBar> progressBarsVotaciones) {
        this.progressBarsVotaciones = progressBarsVotaciones;
    }

    public boolean getVotoDiarioEncuesta() {
        return votoDiarioEncuesta;
    }
    public void setVotoDiarioEncuesta(boolean votoDiarioEncuesta) {
        this.votoDiarioEncuesta = votoDiarioEncuesta;
    }

    public ArrayList<Integer> getValoresVotaciones() {
        return valoresVotaciones;
    }
    public void setValoresVotaciones(ArrayList<Integer> valoresVotaciones) {
        this.valoresVotaciones = valoresVotaciones;
    }
}
