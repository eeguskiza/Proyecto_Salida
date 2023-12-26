package org.proyectosalida.Constructores;

public class Horario {
    private Integer dia;
    private String horaInicio;
    private String horaFin;

    public Horario() {
    }

    public Horario(Integer dia, String horaInicio, String horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String toLetters(Integer dia){
        if (dia.equals(1)) {
            return "Lunes";
        } else if (dia.equals(2)) {
            return "Martes";
        } else if (dia.equals(3)) {
            return  "Miercoles";
        } else if (dia.equals(4)) {
            return "Jueves";
        } else if (dia.equals(5)) {
            return "Viernes";
        } else if (dia.equals(6)) {
            return  "Sabado";
        } else if (dia.equals(7)) {
            return  "Domigo";
        }
        return "";
    }

    @Override
    public String toString() {
        return toLetters(dia) + " --> " + horaInicio + " - " + horaFin;
    }
}
