package org.proyectosalida.Datos;

import org.proyectosalida.Constructores.*;

import javax.swing.*;
import java.util.*;

public class AlmacenDeDatos {


    protected ArrayList<JProgressBar> progressBarsVotaciones;
    protected ArrayList<Integer> valoresVotaciones;
    private boolean votoDiarioEncuesta; //El boolean va aqui ya que sino siempre que se habra y cierre la main ventana se va a restablecer el valor, aqui no. Solo se puede una vez así.

    private Cliente cliente;
    private Dueño dueño;
     private ArrayList<Usuario> usuarios;

    private boolean esDueño;
    private boolean esCliente;

    public AlmacenDeDatos(){
        //Inicializamos hashmap (con bd no sería necesario creo)


        progressBarsVotaciones = new ArrayList<>();
        valoresVotaciones = new ArrayList<>();
        votoDiarioEncuesta = false;

        usuarios = new ArrayList<>();

        esDueño = false;
        esCliente = false;

        //Esto es para tener algo ya añadido ------- RELLENO --------------
        String link1 = "https://www.tripadvisor.es/Restaurant_Review-g187454-d5615756-Reviews-Bar_Monty-Bilbao_Province_of_Vizcaya_Basque_Country.html";
        ArrayList<Horario> horariosMonty = new ArrayList<>();
        horariosMonty.add(new Horario("Lunes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Martes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Miercoles", "07:30", "23:30"));
        horariosMonty.add(new Horario("Jueves", "07:30", "23:30"));
        horariosMonty.add(new Horario("Viernes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Sabado", "07:30", "23:30"));
        horariosMonty.add(new Horario("Domingo", "07:30", "16:00"));
        ArrayList<Caracteristica>caracteristicasMonty=new ArrayList<>();
        caracteristicasMonty.add(Caracteristica.PINTXOS);
        caracteristicasMonty.add(Caracteristica.TERRAZA);
        caracteristicasMonty.add(Caracteristica.CERVEZAS);
        caracteristicasMonty.add(Caracteristica.COMBINADOS);
        Bar Monty = new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, link1, horariosMonty, true,caracteristicasMonty);

        ArrayList<Local> locales = new ArrayList<>();
        locales.add(Monty);

        dueño = new Dueño("enekoalvareez", "Eneko", "Alvarez", new GregorianCalendar(2004, Calendar.JUNE, 23).getTime(), "Contraseña", "687 322 612", "ealvarez@opendeusto.es", locales);
        Cliente m = new Cliente("maialenblancoo","Maialen", "Blanco", new GregorianCalendar(2004, Calendar.MAY, 4).getTime(), "Contraseña2", "687 322 612", "maialen.blanco@opendeusto.es", null);


        //muestra de votaciones de encuesta
        valoresVotaciones.add(3);valoresVotaciones.add(8);valoresVotaciones.add(7);valoresVotaciones.add(4);valoresVotaciones.add(3);valoresVotaciones.add(5);

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

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente usuario) {
        this.cliente = cliente;
    }

    public Dueño getDueño() {
        return dueño;
    }
    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }

    public boolean getEsDueño() {
        return esDueño;
    }
    public void setEsDueño(boolean esDueño) {
        this.esDueño = esDueño;
    }

    public boolean getEsCliente() {
        return esCliente;
    }

    public void setEsCliente(boolean esCliente) {
        this.esCliente = esCliente;
    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuario(ArrayList<Usuario> clientes) {
        this.usuarios = clientes;
    }
}
