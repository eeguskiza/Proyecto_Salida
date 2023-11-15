package app;
import java.security.KeyStore;
import java.util.List;
public class Local {
        private int id;
        private String direccion;
        private int aforo;
        private String rangoEdad;
        private String url;
        private String tlf;
        private List<String> listaHorario; // Puedes cambiar el tipo de datos si los horarios son más complejos
        private double puntuacion;
        private boolean accesibilidad;
        private List<Valoracion> valoraciones; // Suponiendo que hay una clase Valoracion definida



        // Getters y setters para acceder y modificar los atributos

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public int getAforo() {
            return aforo;
        }

        public void setAforo(int aforo) {
            this.aforo = aforo;
        }

        public String getRangoEdad() {
            return rangoEdad;
        }

        public void setRangoEdad(String rangoEdad) {
            this.rangoEdad = rangoEdad;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTlf() {
            return tlf;
        }

        public void setTlf(String tlf) {
            this.tlf = tlf;
        }

        public List<String> getListaHorario() {
            return listaHorario;
        }

        public void setListaHorario(List<String> listaHorario) {
            this.listaHorario = listaHorario;
        }

        public double getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(double puntuacion) {
            this.puntuacion = puntuacion;
        }

        public boolean isAccesibilidad() {
            return accesibilidad;
        }

        public void setAccesibilidad(boolean accesibilidad) {
            this.accesibilidad = accesibilidad;
        }

        public List<Valoracion> getValoraciones() {
            return valoraciones;
        }

        public void setValoraciones(List<Valoracion> valoraciones) {
            this.valoraciones = valoraciones;
        }
    public Local(int id, String direccion, int aforo, String rangoEdad, String url, String tlf, List<String> listaHorario, double puntuacion, boolean accesibilidad, List<Valoracion> valoraciones) {
        this.id = id;
        this.direccion = direccion;
        this.aforo = aforo;
        this.rangoEdad = rangoEdad;
        this.url = url;
        this.tlf = tlf;
        this.listaHorario = listaHorario;
        this.puntuacion = puntuacion;
        this.accesibilidad = accesibilidad;
        this.valoraciones = valoraciones;
    }
    public Local() {
        this.id = 0;
        this.direccion = "direccion";
        this.aforo = 0;
        this.rangoEdad = "";
        this.url = "url";
        this.tlf = "tlf";
        this.listaHorario = null;
        this.puntuacion = 0.0;
        this.accesibilidad = false;
        this.valoraciones = null;
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", aforo=" + aforo +
                ", rangoEdad='" + rangoEdad + '\'' +
                ", url='" + url + '\'' +
                ", tlf='" + tlf + '\'' +
                ", listaHorario=" + listaHorario +
                ", puntuacion=" + puntuacion +
                ", accesibilidad=" + accesibilidad +
                ", valoraciones=" + valoraciones +
                '}';
    }
}


