package Constructores;

public abstract class Usuario {
    // Atributos
    protected String id;
    protected String Nombre;
    protected String Apellido;
    protected int Edad;
    protected String Contraseña;
    protected String Telefono;
    protected String Correo;

    //Constructor vacio
    public Usuario (){
        this.id = "0000000000";
        this.Nombre = "";
        this.Apellido = "";
        this.Edad = 0;
        this.Contraseña = "";
        this.Telefono = "";
        this.Correo = "";
    }
    //Constructor con parametros
    public Usuario (String id, String Nombre, String Apellido, int Edad, String Contraseña, String Telefono, String Correo){
        this.id = id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Edad = Edad;
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

    public void setEdad(int edad) {
        Edad = edad;
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

    //Metodo toString
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Edad=" + Edad + ", Contrase\u00f1a=" + Contraseña + ", Telefono=" + Telefono + ", Correo=" + Correo + '}';
    }
}
