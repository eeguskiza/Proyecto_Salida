package org.proyectosalida.Constructores;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Usuario {
    // Atributos
    protected String id;
    protected String Nombre;
    protected String Apellido;
    protected int Edad;  // Representa la edad calculada
    protected String Contraseña;
    protected String Telefono;
    protected String Correo;

    // Constructor vacío
    public Usuario() {
        this.id = "";
        this.Nombre = "";
        this.Apellido = "";
        this.Edad = 0;
        this.Contraseña = "";
        this.Telefono = "";
        this.Correo = "";
    }

    // Constructor con parámetros
    public Usuario(String id, String Nombre, String Apellido, String fechaNacimiento, String Contraseña, String Telefono, String Correo) {
        this.id = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.setEdad(fechaNacimiento);  // Usa el método setEdad para establecer la edad
        this.Contraseña = Contraseña;
        this.Telefono = Telefono;
        this.Correo = Correo;
    }

    //Getters y Setters
    public String getId(){
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public int getEdad() {
        return Edad;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
        LocalDate ahora = LocalDate.now();
        this.Edad = Period.between(fechaNac, ahora).getYears();
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
