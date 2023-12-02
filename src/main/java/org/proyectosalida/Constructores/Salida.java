package org.proyectosalida.Constructores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Salida {

    private Cliente cliente;
    private ArrayList<Caracteristica> preferencias;
    private Date fecha;
    private Local local;

    public Salida(Cliente cliente, ArrayList<Caracteristica> preferencias, Date fecha, Local local) {
        this.cliente = cliente;
        this.preferencias = preferencias;
        this.fecha = fecha;
        this.local = local;
    }

    public Salida() {
        this.cliente = null;
        this.preferencias = null;
        this.fecha = null;
        this.local = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Caracteristica> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(ArrayList<Caracteristica> preferencias) {
        this.preferencias = preferencias;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salida)) return false;
        Salida salida = (Salida) o;
        return Objects.equals(getCliente(), salida.getCliente()) && Objects.equals(getPreferencias(), salida.getPreferencias()) && Objects.equals(getFecha(), salida.getFecha()) && Objects.equals(getLocal(), salida.getLocal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCliente(), getPreferencias(), getFecha(), getLocal());
    }

    @Override
    public String toString() {
        return "Salida{" +
                "cliente=" + cliente +
                ", preferencias=" + preferencias +
                ", fecha=" + fecha +
                ", local=" + local +
                '}';
    }

}
