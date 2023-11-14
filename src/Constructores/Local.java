package Constructores;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Local {
    protected String id;
    protected String nombre;
    protected String direccion;
    protected String CP; //Limitar tamaño a 5
    protected int Aforo;
    protected String telefono;
    protected int MediaEdad;
    protected int PrecioMedio;
    protected String web;
    protected ArrayList<Horario> horarios;

    /*
    Valoraciones
    Visitas
    Y mas por añadir
     */


    public Local() {
        this.id = "";
        this.nombre = "";
        this.direccion = "";
        this.CP = "";
        this.Aforo = 0;
        this.telefono = "";
        this.MediaEdad = 0;
        this.PrecioMedio = 0;
        this.web = "";
        this.horarios = new ArrayList<>();
    }

    public Local(String nombre, String direccion,String CP, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, ArrayList<Horario> horarios) {
        this.id = generarId(nombre, CP);
        this.nombre = nombre;
        this.direccion = direccion;
        this.CP = CP;
        this.Aforo = Aforo;
        this.telefono = telefono;
        this.MediaEdad = MediaEdad;
        this.PrecioMedio = PrecioMedio;
        this.web = web;
        this.horarios = horarios;

    }
    private static String generarId(String nombre, String CP) {
        Random random = new Random();
        int numeroAleatorio = 100 + random.nextInt(900);
        return (nombre.isEmpty() ? "X" : nombre.substring(0, 1).toUpperCase()) + CP.substring(3,5) + numeroAleatorio;
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

    public ArrayList<Horario> getHorarios() {
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

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

}
