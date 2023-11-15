package app;

public class Usuario {
        int cont=0;

        private int id;
        private String nombre;
        private String apellido;
        private String tlf;
        private String correo;
        private int edad;
        private String contraseña;

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return cont;
    }

    public void setId(int id) {
        this.id = cont+1;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTlf() {
        return tlf;
    }

    public String getCorreo() {
        return correo;
    }

    public int getEdad() {
        return edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario(String nombre, String apellido, String tlf, String correo, int edad, String contraseña) {
            this.id = cont+1;
            this.nombre = nombre;
            this.apellido = apellido;
            this.tlf = tlf;
            this.correo = correo;
            this.edad = edad;
            this.contraseña = contraseña;
        }

    public Usuario() {
        this.id = cont+1;
        this.nombre = "ususario_"+id;
        this.apellido = "apellido"+1;
        this.tlf = "000000000";
        this.correo = "@";
        this.edad = 18;
        this.contraseña = "contraseña";
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tlf='" + tlf + '\'' +
                ", correo='" + correo + '\'' +
                ", edad=" + edad +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}

