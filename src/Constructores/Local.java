package Constructores;

import java.awt.*;
import java.util.ArrayList;

public abstract class Local {
    protected String id;
    protected String nombre;
    protected String direccion;
    protected int Aforo;
    protected String telefono;
    protected int MediaEdad;
    protected int PrecioMedio;
    protected String web;
    protected Horario[] horarios;

    /*
    Valoraciones
    Visitas
    Y mas por añadir
     */


    public Local() {
        this.id = "";
        this.nombre = "";
        this.direccion = "";
        this.Aforo = 0;
        this.telefono = "";
        this.MediaEdad = 0;
        this.PrecioMedio = 0;
        this.web = "";
        this.horarios = new Horario[7];
    }

    public Local(String id, String nombre, String direccion, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, Horario[] horarios) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.Aforo = Aforo;
        this.telefono = telefono;
        this.MediaEdad = MediaEdad;
        this.PrecioMedio = PrecioMedio;
        this.web = web;
        this.horarios = horarios;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getAforo() {
        return Aforo;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getMediaEdad() {
        return MediaEdad;
    }

    public int getPrecioMedio() {
        return PrecioMedio;
    }

    public String getWeb() {
        return web;
    }

    public Horario[] getHorarios() {
        return horarios;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAforo(int aforo) {
        Aforo = aforo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMediaEdad(int mediaEdad) {
        MediaEdad = mediaEdad;
    }

    public void setPrecioMedio(int precioMedio) {
        PrecioMedio = precioMedio;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setHorarios(Horario[] horarios){
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "Local{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", Aforo=" + Aforo +
                ", telefono='" + telefono + '\'' +
                ", MediaEdad=" + MediaEdad +
                ", PrecioMedio=" + PrecioMedio +
                ", web='" + web + '\'' +
                ", horarios=" + horarios +
                '}';
    }
}
