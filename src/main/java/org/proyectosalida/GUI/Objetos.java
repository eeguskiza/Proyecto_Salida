package org.proyectosalida.GUI;

import java.util.ArrayList;


import org.proyectosalida.Constructores.*;

public class Objetos {
    public static void main(String[] args) {
        System.out.println("Bienvenido al gestor base de PROYECTO_SALIDA");

        //LO PRIMERO SERA CREAR 4 USUARIOS, NOSOTROS POR EJEMPLO
        //CREAREMOS 2 DUEÑOS Y 2 USUARIOS

        Dueño Erik = new Dueño("erik_eguskiza", "Erik", "Eguskiza", "2004-08-04", "Contraseña", "687 322 612", "e.eguskiza@opendeusto.es", null);
        Dueño Eneko = new Dueño("enekoalvareez", "Eneko", "Alvarez", "2004-08-04", "Contraseña", "687 322 612", "ealvarez@opendeusto.es", null);

        Cliente Maialen = new Cliente("maialenblancoo","Maialen", "Blanco", "2004-05-04", "Contraseña", "687 322 612", "maialen.blanco@opendeusto.es", null);
        Cliente Alex = new Cliente("alexja_2", "Alex", "Jauregi", "2004-10-27", "Contraseña", "687 322 612", "alex.jauregui@opendeusto.es", null);

        //AQUI EMPEZAREMOS A CREAR LOCALES
        //CREAREMOS 2 LOCALES, UNO PARA ERIK Y OTRO PARA ENEKO

        String link1 = "https://www.tripadvisor.es/Restaurant_Review-g187454-d5615756-Reviews-Bar_Monty-Bilbao_Province_of_Vizcaya_Basque_Country.html";

        String link2 = "https://backandstage.com/";
        DJ dj1 = new DJ();
        DJ dj2 = new DJ();

        ArrayList<Horario> horariosMonty = new ArrayList<>();
        horariosMonty.add(new Horario("Lunes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Martes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Miercoles", "07:30", "23:30"));
        horariosMonty.add(new Horario("Jueves", "07:30", "23:30"));
        horariosMonty.add(new Horario("Viernes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Sabado", "07:30", "23:30"));
        horariosMonty.add(new Horario("Domingo", "07:30", "16:00"));

        ArrayList<Horario> horariosBack = new ArrayList<>();
        horariosBack.add(new Horario("Lunes - Martes", "12:00", "04:00"));
        horariosBack.add(new Horario("Martes - Miercoles", "12:00", "04:00"));
        horariosBack.add(new Horario("Miercoles - Jueves", "12:00", "04:00"));
        horariosBack.add(new Horario("Jueves - Viernes", "12:00", "04:00"));
        horariosBack.add(new Horario("Viernes - Sabado", "12:00", "06:00"));
        horariosBack.add(new Horario("Sabado - Domingo", "12:00", "06:00"));
        horariosBack.add(new Horario("Domingo - Lunes", "12:00", "04:00"));

        Bar Monty = new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, link1, horariosMonty, true);

        Discoteca Back = new Discoteca("Back&Stage", "Calle de la Ronda, 35, Bilbo, Bizkaia","48005",  200, "747 48 96 30", 0, 0, link2, horariosBack, dj1, dj2);

        ArrayList<Local> localesErik = new ArrayList<>();
        localesErik.add(Monty);

        ArrayList<Local> localesEneko = new ArrayList<>();
        localesEneko.add(Back);

        ArrayList<Visita> visitadosMaialen = new ArrayList<>();
        //visitadosMaialen.add(Monty); //TODO HE CAMBIADO ARRAYLIST<LOCAL> POR VISITA QUE ADENTRO DE VISITA ESTA EL LOCAL Y LLEVA MAS INFO
        //visitadosMaialen.add(Back);

        ArrayList<Visita> visitadosAlex = new ArrayList<>();
        //visitadosAlex.add(Monty);
        //visitadosAlex.add(Back);

        Erik.setLocales(localesErik);
        Eneko.setLocales(localesEneko);
        Maialen.setVisitas(visitadosMaialen);
        Alex.setVisitas(visitadosAlex);




        //Imprimimos Todo
        System.out.println(Erik);
        System.out.println(Eneko);
        System.out.println(Maialen);
        System.out.println(Alex);

        System.out.println(Monty);
        System.out.println(Back);

        System.out.println(dj1);
        System.out.println(dj2);



    }
}
