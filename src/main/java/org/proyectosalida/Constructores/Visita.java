package org.proyectosalida.Constructores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Visita {
    private String clienteID;
    private Local local;
    private Date fecha;
    private String hora;
    private String valoracion; //A futuros esto un objeto nuevo
    private String id;

    public Visita() {
        this.id = "";
        this.clienteID = "";
        this.local = null;
        this.fecha = new Date();
        this.hora = "";
        this.valoracion = "";
    }


    public Visita(String clienteID, Local local, Date fecha, String hora, String valoracion) {
        this.id = generarId(local.getNombre(), clienteID);
        this.clienteID = clienteID;
        this.local = local;
        this.fecha = fecha;
        this.hora = hora;
        this.valoracion = valoracion;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Date getFecha() {
        return fecha;
    }
    public String getFechaFormatoString() {
        if (fecha != null) {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            return formatoFecha.format(fecha);
        } else {
            return null;
        }
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setFecha(String fecha) { // Transforma de string dd/mm/yyy a fecha
        try{
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            this.fecha = formatoFecha.parse(fecha);
        }catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "Visita{" +
                "clienteID=" + clienteID +
                ", local=" + local +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", valoracion='" + valoracion + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    private static String generarId(String nombre, String apellido) {
        Random random = new Random();
        int numeroAleatorio = 100 + random.nextInt(800);
        return (nombre.isEmpty() ? "X" : nombre.substring(0, 2).toUpperCase()) + numeroAleatorio + apellido.substring(1,3).toUpperCase();
    }


}
