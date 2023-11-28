package org.proyectosalida.Constructores;

import java.util.ArrayList;
import java.util.HashMap;

//TODOS LOS DATOS TANTO ARRAYLIST COMO HASHMAPS DEBEN IR GUARDADOS AQUI Y ASI PODER ACCEDER A ESTA CLASE EN BUSCA DE INFO
public class AlmacenDeDatos {
    protected HashMap<String, Usuario> usuarios;

    public AlmacenDeDatos(){
        //Inicializamos hashmap (con bd no sería necesario creo)
        usuarios = new HashMap<>();

        //Esto es para tener algo ya añadido
        Cliente eneko = new Cliente("eneko", "Eneko", "Alvarez", "2004-06-23", "eneko04", "634556788", "eneko.alvarez@opendeusto.es", new ArrayList<>());
        usuarios.put(eneko.getId(), eneko);

        Dueño erik = new Dueño("erik", "Erik", "Eguskiza", "2004-08-04", "erik04", "3456788989", "e.eguskiza@opendeusto.es", new ArrayList<>());
        usuarios.put(erik.getId(), erik);
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario(String id){
        Usuario uBuscado = null;
        for(Usuario u : usuarios.values()){
            if(u.getId().equals(id)){
                uBuscado = u;
            }
        }
        return uBuscado;
    }
}
