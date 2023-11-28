package org.proyectosalida.Constructores;

public class Visita {
    private Cliente cliente;
    private Local local;
    private String fecha;
    private String hora;
    private String valoracion; //A futuros esto un objeto nuevo

    public Visita() {
        this.cliente = new Cliente();
        this.local = null;
        this.fecha = "";
        this.hora = "";
        this.valoracion = "";
    }

    public Visita(Cliente cliente, Local local, String fecha, String hora, String valoracion) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
