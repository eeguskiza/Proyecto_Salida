package app;

public class Visitas {
protected Usuario usuario;
protected Local local;
protected String fecha;
protected double valoracion;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public Visitas(Usuario usuario, Local local, String fecha, double valoracion) {
        this.usuario = usuario;
        this.local = local;
        this.fecha = fecha;
        this.valoracion = valoracion;
    }
    public Visitas(){
        this.usuario = null;
        this.local = null;
        this.fecha ="27/10/2004";
        this.valoracion = 9.9;
    }

    @Override
    public String toString() {
        return "Visitas{" +
                "usuario=" + usuario +
                ", local=" + local +
                ", fecha='" + fecha + '\'' +
                ", valoracion=" + valoracion +
                '}';
    }
}
