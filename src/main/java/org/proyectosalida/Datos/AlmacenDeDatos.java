package org.proyectosalida.Datos;

import org.proyectosalida.Constructores.Cliente;
import org.proyectosalida.Constructores.Dueño;
import org.proyectosalida.Constructores.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class AlmacenDeDatos {


    protected ArrayList<JProgressBar> progressBarsVotaciones;
    protected ArrayList<Integer> valoresVotaciones;
    private boolean votoDiarioEncuesta; //El boolean va aqui ya que sino siempre que se habra y cierre la main ventana se va a restablecer el valor, aqui no. Solo se puede una vez así.

    private Cliente usuario;
    private Dueño dueño;

    public AlmacenDeDatos(){
        //Inicializamos hashmap (con bd no sería necesario creo)


        progressBarsVotaciones = new ArrayList<>();
        valoresVotaciones = new ArrayList<>();
        votoDiarioEncuesta = false;
        usuario = null;

        //Esto es para tener algo ya añadido
        //Cliente eneko = new Cliente("eneko", "Eneko", "Alvarez", new GregorianCalendar(2004, Calendar.AUGUST, 4).getTime(), "eneko04", "634556788", "eneko.alvarez@opendeusto.es", new ArrayList<>());
        //Dueño erik = new Dueño("erik", "Erik", "Eguskiza", new GregorianCalendar(2004, Calendar.JUNE, 23).getTime(), "erik04", "3456788989", "e.eguskiza@opendeusto.es", new ArrayList<>());


        //muestra de votaciones de encuesta
        valoresVotaciones.add(3);valoresVotaciones.add(8);valoresVotaciones.add(7);valoresVotaciones.add(4);valoresVotaciones.add(3);valoresVotaciones.add(5);

    }

/*
    public Usuario getUsuario(String id){
        Usuario uBuscado = null;
        for(Usuario u : usuarios.values()){
            if(u.getId().equals(id)){
                uBuscado = u;
            }
        }
        return uBuscado;
    }
    º
 */

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

    public Cliente getUsuario() {
        return usuario;
    }
    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public Dueño getDueño() {
        return dueño;
    }
    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }
}
