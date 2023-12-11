package org.proyectosalida.Constructores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Visita {
    private Cliente cliente;
    private Local local;
    private Date fecha;
    private String hora;
    private String valoracion; //A futuros esto un objeto nuevo

    public Visita() {
        this.cliente = new Cliente();
        this.local = null;
        this.fecha = new Date();
        this.hora = "";
        this.valoracion = "";
    }

    public Visita(Cliente cliente, Local local, Date fecha, String hora, String valoracion) {
        this.cliente = cliente;
        this.local = local;
        this.fecha = fecha;
        this.hora = hora;
        this.valoracion = valoracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "Visita{" + "cliente=" + cliente + ", local=" + local + ", fecha=" + fecha + ", hora=" + hora + ", valoracion=" + valoracion + '}';
    }


}
