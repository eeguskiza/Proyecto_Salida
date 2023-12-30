package org.proyectosalida.Constructores;

import java.net.MalformedURLException;
import java.net.URL;

public class DJ{
    private String nombre;
    private String apellido;
    private String nombreMusical;
    private String nacionalidad;
    private int edad;
    private String generoMusical;
    private String estiloMusical;
    private String instagram;
    private Integer id;

    /*
    Precio
    Valoraciones
    Visitas
    Y mas por añadir
     */

    public DJ() {
        this.nombre = "";
        this.apellido = "";
        this.nombreMusical = "";
        this.nacionalidad = "";
        this.edad = 0;
        this.generoMusical = "";
        this.estiloMusical = "";
        this.instagram = "";
        this.id = null;
    }

    public DJ(String nombre, String apellido, String nombreMusical, String nacionalidad, int edad, String generoMusical, String estiloMusical, String instagram) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreMusical = nombreMusical;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.generoMusical = generoMusical;
        this.estiloMusical = estiloMusical;
        this.instagram = instagram;
        this.id = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreMusical() {
        return nombreMusical;
    }

    public void setNombreMusical(String nombreMusical) {
        this.nombreMusical = nombreMusical;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getEstiloMusical() {
        return estiloMusical;
    }

    public void setEstiloMusical(String estiloMusical) {
        this.estiloMusical = estiloMusical;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DJ{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombreMusical='" + nombreMusical + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", edad=" + edad +
                ", generoMusical='" + generoMusical + '\'' +
                ", estiloMusical='" + estiloMusical + '\'' +
                ", instagram='" + instagram + '\'' +
                '}';
    }
}
