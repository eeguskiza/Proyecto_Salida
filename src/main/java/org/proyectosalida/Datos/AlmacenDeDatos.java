package org.proyectosalida.Datos;

import org.proyectosalida.Constructores.*;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class AlmacenDeDatos {

//OBJ
    private Cliente cliente;
    private Dueño dueño;
    private boolean votoDiarioEncuesta; //El boolean va aqui ya que sino siempre que se habra y cierre la main ventana se va a restablecer el valor, aqui no. Solo se puede una vez así.

//Booleans
    private boolean esDueño;
    private boolean esCliente;

//Listas
    public HashMap<String, Integer> valoresVotaciones; //id, num
    private static ArrayList<Caracteristica> caracteristicas; //todas las caracterisiticas

     private ArrayList<Usuario> usuarios;
     private ArrayList<Usuario> usuariosPrueba;
     private ArrayList<Local> locales; //Todos los locales en nuestra bd
    private ArrayList<Class> clasesDeLocales; //PARA LA TABLA DE DUEÑO EN MAINMENUDUEÑO


    public AlmacenDeDatos(){
        valoresVotaciones = new HashMap<>();
        votoDiarioEncuesta = false;
        caracteristicas = new ArrayList<>();
        usuarios = new ArrayList<>();
        usuariosPrueba = new ArrayList<>();
        clasesDeLocales = new ArrayList<>();
        locales = new ArrayList<>();

        esDueño = false;
        esCliente = false;

        System.out.println("ALMACEN CREADO");
        descargarCaracteristicas();

        //BORRAR ESTE METODO LUEGO
        //añadirEjemplos();

        //FALTA UN METODO PARA CARGAR TODOS LOS LOCALES DE LA BD A LOCALES. ARREGLADO: SE HACE EN INICIOSESION
        //Los valores de la encuesa se inicializan en InicioSesion


    }

    public HashMap<String, Integer> getValoresVotaciones() {
        return valoresVotaciones;
    }
    public void setValoresVotaciones(HashMap<String, Integer> valoresVotaciones) {
        this.valoresVotaciones = valoresVotaciones;
    }

    public boolean getVotoDiarioEncuesta() {
        return votoDiarioEncuesta;
    }
    public void setVotoDiarioEncuesta(boolean votoDiarioEncuesta) {
        this.votoDiarioEncuesta = votoDiarioEncuesta;
    }


    public ArrayList<Class> getClasesDeLocales() {
        return clasesDeLocales;
    }

    public void setClasesDeLocales(ArrayList<Class> clasesDeLocales) {
        this.clasesDeLocales = clasesDeLocales;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente usuario) {
        this.cliente = cliente;
    }

    public Dueño getDueño() {
        return dueño;
    }
    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }

    public boolean getEsDueño() {
        return esDueño;
    }
    public void setEsDueño(boolean esDueño) {
        this.esDueño = esDueño;
    }

    public boolean getEsCliente() {
        return esCliente;
    }

    public void setEsCliente(boolean esCliente) {
        this.esCliente = esCliente;
    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuario(ArrayList<Usuario> clientes) {
        this.usuarios = clientes;
    }

    public ArrayList<Caracteristica> getCaracteristicas() {
        for (Caracteristica c : Caracteristica.values()) {
            caracteristicas.add(c);
        }
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public ArrayList<Local> getLocales() {
        return locales;
    }

    public void setLocales(ArrayList<Local> locales) {
        this.locales = locales;
    }

    //ESTO SE PUEDE QUITAR UNA VEZ TERMINADO EL PROYECTO Y FUNCIONE CON BD COMPLETAMENTE
    public ArrayList<Usuario> getUsuariosPrueba() {
        return usuariosPrueba;
    }

    public void setUsuariosPrueba(ArrayList<Usuario> usuariosPrueba) {
        this.usuariosPrueba = usuariosPrueba;
    }


//COSAS QUE SIRVEN POR NOSOTROS:
    private void añadirEjemplos(){
        //Esto es para tener algo ya añadido ------- RELLENO --------------
        //Bar
        String linkStage = "https://www.google.com/maps/place/Stage+Live/@43.2641429,-2.9311399,16.1z/data=!4m6!3m5!1s0xd4e4fc54aa9e77b:0x73e67015ad689356!8m2!3d43.2643475!4d-2.9275594!16s%2Fg%2F11fy_6ght3?entry=ttu";
        String linkBack = "https://www.google.com/maps/place/Back%26Stage/@43.264306,-2.9302868,17z/data=!3m2!4b1!5s0xd4e4fda648c70af:0x6b8148661f92eaae!4m6!3m5!1s0xd4e4fda73f10843:0xfdf73bd3595b6008!8m2!3d43.2643021!4d-2.9277119!16s%2Fg%2F1ptx16dgd?entry=ttu";
        ArrayList<Horario> horariosMonty = new ArrayList<>();
        horariosMonty.add(new Horario("Lunes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Martes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Miercoles", "07:30", "23:30"));
        horariosMonty.add(new Horario("Jueves", "07:30", "23:30"));
        horariosMonty.add(new Horario("Viernes", "07:30", "23:30"));
        horariosMonty.add(new Horario("Sabado", "07:30", "23:30"));
        horariosMonty.add(new Horario("Domingo", "07:30", "16:00"));

        ArrayList<Horario> horariosDisco = new ArrayList<>();
        horariosDisco.add(new Horario("Lunes", "00:30", "05:30"));
        horariosDisco.add(new Horario("Martes", "00:30", "05:30"));
        horariosDisco.add(new Horario("Miercoles", "00:30", "05:30"));
        horariosDisco.add(new Horario("Jueves", "06:30", "16:30"));
        horariosDisco.add(new Horario("Viernes", "00:30", "05:30"));
        horariosDisco.add(new Horario("Sabado", "00:30", "05:30"));
        horariosDisco.add(new Horario("Domingo", "00:30", "05:00"));

        ArrayList<Caracteristica>caracteristicasMonty=new ArrayList<>();
        /*
        caracteristicasMonty.add(Caracteristica.PINTXOS);
        caracteristicasMonty.add(Caracteristica.TERRAZA);
        caracteristicasMonty.add(Caracteristica.CERVEZAS);
        caracteristicasMonty.add(Caracteristica.COMBINADOS);

        ArrayList<Caracteristica> c2 = new ArrayList<>();
        c2.add(Caracteristica.BAILE);
        c2.add(Caracteristica.PINTXOS);
        c2.add(Caracteristica.MUSICA);
         */

        Bar Monty = new Bar("Monty", "Heros Kalea, 16, Bilbo, Bizkaia", "48009", 75, "944 23 63 36", 0, 0, null, horariosMonty, true,caracteristicasMonty);

        dueño = new Dueño("enekoalvareez", "Eneko", "Alvarez", new GregorianCalendar(2004, Calendar.JUNE, 23).getTime(), "Contraseña", "687 322 612", "ealvarez@opendeusto.es", null);
        dueño.agregarLocal(Monty);

        //Discoteca añadir a dueño 1
        //    public Discoteca(String nombre, String direccion, String CP, int Aforo, String telefono, int MediaEdad, int PrecioMedio, String web, ArrayList<Horario> horarios, DJ djResidente, DJ djInvitado,ArrayList<Caracteristica>caracteristicas) {
        Discoteca Stage = new Discoteca("StageLive", "C/ Algo en Bilbo", "48005", 300, "784 348 357", 18, 15, linkStage, horariosDisco, new DJ("DJ Theo", "", "", "", 0, "", "", ""), new DJ("DJ 2", "", "", "", 0, "", "", ""), null);
        Discoteca Back = new Discoteca("BackRoom", "C/ Abando", "48005", 250, "678 439 389", 19, 15, linkBack, horariosMonty, new DJ("Almaba Ice", "Ice", "a", "España", 19, "Reggaeton", "Reggeaton", "@almava_ice"), new DJ("DJ 2", "", "", "", 0, "", "", ""), caracteristicasMonty);

        dueño.agregarLocal(Stage);
        //Repito pa rellenar mas
        dueño.agregarLocal(Monty);
        dueño.agregarLocal(Back);

        //--------- CLIENTE DE EJEMPLO ----------------

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Cliente cliente = new Cliente("maialenblancoo","Maialen", "Blanco", new GregorianCalendar(2004, Calendar.MAY, 4).getTime(), "Contraseña2", "687 322 612", "maialen.blanco@opendeusto.es", null);
        /*Visita v1 = new Visita(cliente, Stage,null , "01:21", "El establecimiento muy bien pero precios muy altos!");
        v1.setFecha("23/06/2024");
        Visita v2 = new Visita(cliente, Monty, null, "01:03", "Buen copeo por la tarde-noche");
        v2.setFecha("16/04/2024");
        ArrayList<Visita> visitas = new ArrayList<>();
        visitas.add(v1); visitas.add(v2);
        cliente.setVisitas(visitas); */

        usuariosPrueba.add(dueño); //todo Esto hay que quitarlo aunk no interfiere con bd ya que es otro array
        usuariosPrueba.add(cliente); //lo mismo
        locales.add(Monty);
        locales.add(Stage);
        locales.add(Back);
    }

    public Date transformarStringADate(String fecha){
        Date date = new Date();
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            date = formatoFecha.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String transformarDateAString(Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formatoFecha.format(fecha);
        return fechaString;
    }

    public void ininializarValoresEncuesta(){
        for(Local local : locales){
            valoresVotaciones.put(local.getId(), 0);
        }
    }


    //----------MANEJO DE BASE DE DATOS-----------

    public static boolean inicioSesionDueño(String usuario, String contraseña, Dueño dueño, AlmacenDeDatos almacen) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "SELECT * FROM DUEÑO WHERE ID = ? AND Contraseña = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, usuario);
                pstmt.setString(2, contraseña);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("ID");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDO");
                    java.sql.Date fechaNacimiento = rs.getDate("FECHANACIMIENTO");
                    String contraseña2 = rs.getString("CONTRASEÑA");
                    String telefono = rs.getString("TELEFONO");
                    String email = rs.getString("EMAIL");

                    dueño.setId(id);
                    dueño.setNombre(nombre);
                    dueño.setApellido(apellido);
                    dueño.setFechaNacimiento(fechaNacimiento);
                    dueño.setContraseña(contraseña2);
                    dueño.setTelefono(telefono);
                    dueño.setCorreo(email);

                    //Metodo flexible tanto para usuario como dueño, ahora se usa para dueño
                    cargarLocales(conn, true, dueño, almacen);

                    // Imprimir los valores de cada fila si se encuentra un dueño
                    System.out.println("Dueño encontrado: ID: " + id + ", Nombre: " + nombre);
                    System.out.println(dueño);
                    almacen.getUsuarios().add(dueño);
                    return true;
                } else {
                    System.out.println("No se encontró el dueño con el ID y contraseña proporcionados.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean inicioSesionCliente(String usuario, String contraseña, Cliente cliente, AlmacenDeDatos almacen) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "SELECT * FROM CLIENTE WHERE ID = ? AND Contraseña = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, usuario);
                pstmt.setString(2, contraseña);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String id = rs.getString("ID");
                    String nombre = rs.getString("NOMBRE");
                    String apellido = rs.getString("APELLIDO");
                    java.sql.Date fechaNacimiento = rs.getDate("FECHANACIMIENTO");
                    String contraseña2 = rs.getString("CONTRASEÑA");
                    String telefono = rs.getString("TELEFONO");
                    String email = rs.getString("EMAIL");

                    cliente.setId(id);
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setFechaNacimiento(fechaNacimiento);
                    cliente.setContraseña(contraseña2);
                    cliente.setTelefono(telefono);
                    cliente.setCorreo(email);

                    cargarLocales(conn, false, null, almacen); //No es optimo descargar todos los locales para la busqueda mas adelante pero bueno
                    System.out.println("LOCALES CARGADOS, PASANDO A LAS VISITAS");
                    cargarVisitasCliente(conn, cliente);
                    // Imprimir los valores de cada fila si se encuentra un dueño
                    System.out.println("Cliente encontrado: ID: " + id + ", Nombre: " + nombre);
                    //System.out.println(cliente);
                    almacen.getUsuarios().add(cliente);
                    return true;
                } else {
                    System.out.println("No se encontró el Cliente con el ID y contraseña proporcionados.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void cargarLocales(Connection conn, Boolean isDueño, Dueño dueño, AlmacenDeDatos almacenDeDatos) {
        // Obtener todos los locales a su nombre en BAR o todos los bares dependiendo si es cliente o dueño el que inicia sesion
        String sqlLocalesBar = "";
        if(isDueño){
            sqlLocalesBar = "SELECT * FROM bar WHERE dueñoid = ?";
        }else{
            sqlLocalesBar = "SELECT * FROM bar";
        }

        try (PreparedStatement pstmtLocales = conn.prepareStatement(sqlLocalesBar)) {
            if(isDueño){
                pstmtLocales.setString(1, dueño.getId());
            }
            ResultSet rsLocales = pstmtLocales.executeQuery();

            // Procesar locales de tipo bar
            while (rsLocales.next()) {
                String idBar = rsLocales.getString("ID");
                String nombreBar = rsLocales.getString("NOMBRE");
                String direccion = rsLocales.getString("DIRECCION");
                String cp = rsLocales.getString("CODIGOPOSTAL");
                int aforo = rsLocales.getInt("AFORO");
                String telefonoBar = rsLocales.getString("TELEFONO");
                int mediaedad = rsLocales.getInt("MEDIAEDAD");
                int preciomedio = rsLocales.getInt("PRECIOMEDIO");
                String link = rsLocales.getString("LINKWEB");
                int terrazaNum = rsLocales.getInt("TIENETERRAZA");
                boolean terraza = (terrazaNum == 1);

                Bar bar = new Bar();
                bar.setId(idBar);
                bar.setNombre(nombreBar);
                bar.setDireccion(direccion);
                bar.setAforo(aforo);
                bar.setTelefono(telefonoBar);
                bar.setMediaEdad(mediaedad);
                bar.setPrecioMedio(preciomedio);
                bar.setWeb(link);
                bar.setTerraza(terraza);
                bar.setCP(cp);

                try {
                    cargarCaracteristicasLocal(conn, bar);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(isDueño){
                    dueño.getLocales().add(bar);
                }else{
                    almacenDeDatos.getLocales().add(bar);
                }
                System.out.println("1 BAR añadido: "+bar.getNombre());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Obtener todos los locales a su nombre en Discoteca o todas las Discotecas
        String sqlLocalesDisco = "";
        if(isDueño){
            sqlLocalesDisco = "SELECT * FROM discoteca WHERE dueñoid = ?";
        }else{
            sqlLocalesDisco = "SELECT * FROM discoteca";
        }
        try (PreparedStatement pstmtLocalesDisco = conn.prepareStatement(sqlLocalesDisco)) {
            if(isDueño){
                pstmtLocalesDisco.setString(1, dueño.getId());
            }
            ResultSet rsLocalesDisco = pstmtLocalesDisco.executeQuery();

            // Procesar locales de tipo discoteca
            while (rsLocalesDisco.next()) {
                String idDisco = rsLocalesDisco.getString("ID");
                String nombreDisco = rsLocalesDisco.getString("NOMBRE");
                String direccionDisco = rsLocalesDisco.getString("DIRECCION");
                String cpDisco = rsLocalesDisco.getString("CODIGOPOSTAL");
                int capacidad = rsLocalesDisco.getInt("CAPACIDAD");
                String telefonoDisco = rsLocalesDisco.getString("TELEFONO");
                int mediaEdadDisco = rsLocalesDisco.getInt("MEDIAEDAD");
                int precioMedioDisco = rsLocalesDisco.getInt("PRECIOMEDIO");
                String linkDisco = rsLocalesDisco.getString("LINKWEB");

                Discoteca disco = new Discoteca();
                disco.setId(idDisco);
                disco.setNombre(nombreDisco);
                disco.setDireccion(direccionDisco);
                disco.setCP(cpDisco);
                disco.setAforo(capacidad);
                disco.setTelefono(telefonoDisco);
                disco.setMediaEdad(mediaEdadDisco);
                disco.setPrecioMedio(precioMedioDisco);
                disco.setWeb(linkDisco);

                cargarCaracteristicasLocal(conn, disco);

                if(isDueño){
                    dueño.getLocales().add(disco);
                }else{
                    almacenDeDatos.getLocales().add(disco);
                }
                System.out.println("1 DISCOTECA añadida: "+disco.getNombre());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(!isDueño){
            almacenDeDatos.ininializarValoresEncuesta(); //Los valores de encuesta se ponen a 0 inicialmente
        }

    }
    public static void cargarCaracteristicasLocal(Connection conn, Local local){
        String id = local.getId();
        System.out.println(id);
        String sql = "select * from caracteristicas_locales where id_local = ?";

        ArrayList<String> idCaracteristicas = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String idc = rs.getString("ID_CARACTERISTICA");
                idCaracteristicas.add(idc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(String value:idCaracteristicas){
            System.out.println(value);
        }

        for(String idCaracteristica : idCaracteristicas){
            String sql2 = "SELECT descripcion FROM caracteristicas WHERE ID = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                pstmt.setString(1, idCaracteristica);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Caracteristica nueva = Caracteristica.valueOf(rs.getString("descripcion"));
                    local.getCaracteristicas().add(nueva);
                    System.out.println("Caracteristica añadida: "+nueva);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean actualizarDatosLocalBD(Local local) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "";
            if(local.getClass().equals(Bar.class)){
                sql = "UPDATE BAR SET NOMBRE = ?, DIRECCION = ?, CODIGOPOSTAL = ?, AFORO = ?, TELEFONO = ?, MEDIAEDAD = ?, PRECIOMEDIO = ?, LINKWEB = ?, TIENETERRAZA = ? WHERE ID = ?";
            }else{
                sql = "UPDATE DISCOTECA SET NOMBRE = ?, DIRECCION = ?, CODIGOPOSTAL = ?, CAPACIDAD = ?, TELEFONO = ?, MEDIAEDAD = ?, PRECIOMEDIO = ?, LINKWEB = ? WHERE ID = ?";
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, local.getNombre());
                pstmt.setString(2, local.getDireccion());
                pstmt.setString(3, local.getCP());
                pstmt.setInt(4, local.getAforo());
                pstmt.setString(5, local.getTelefono());
                pstmt.setInt(6, local.getMediaEdad());
                pstmt.setInt(7, local.getPrecioMedio());
                pstmt.setString(8, local.getWeb());

                if(local.getClass().equals(Bar.class)){
                    pstmt.setInt(9, ((Bar) local).getTerraza() ? 1 : 0); // Convierte el booleano a numero para la bd
                    pstmt.setString(10, local.getId());
                }else{
                    pstmt.setString(9, local.getId());
                }


                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Datos del Local actualizados en BD.");
                    return true;
                } else {
                    System.out.println("No se pudo actualizar (BD).");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean guardarLocalNuevoBD(Local local, Dueño dueño) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "";
            if(local.getClass().equals(Discoteca.class)){
                sql = "INSERT INTO DISCOTECA (ID, NOMBRE, DIRECCION, CODIGOPOSTAL, CAPACIDAD, TELEFONO, MEDIAEDAD, PRECIOMEDIO, LINKWEB, DUEÑOID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }else{
                sql = "INSERT INTO BAR (ID, NOMBRE, DIRECCION, CODIGOPOSTAL, AFORO, TELEFONO, MEDIAEDAD, PRECIOMEDIO, LINKWEB, TIENETERRAZA, DUEÑOID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, local.getId());
                pstmt.setString(2, local.getNombre());
                pstmt.setString(3, local.getDireccion());
                pstmt.setString(4, local.getCP());
                pstmt.setInt(5, local.getAforo());
                pstmt.setString(6, local.getTelefono());
                pstmt.setInt(7, local.getMediaEdad());
                pstmt.setInt(8, local.getPrecioMedio());
                pstmt.setString(9, local.getWeb());
                if(local.getClass().equals(Discoteca.class)){
                    pstmt.setString(10, dueño.getId());
                }else{
                    pstmt.setInt(10, ((Bar) local).getTerraza() ? 1 : 0); // Convierte el booleano a num para bd
                    pstmt.setString(11, dueño.getId());
                }

                int filasInsertadas = pstmt.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Nuevo Local en BD.");
                    return true;
                } else {
                    System.out.println("No se pudo guardar el nuevo local.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean guardarVisitaBD(Visita nuevaVisita, Cliente cliente) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "INSERT INTO visita (ID, CLIENTEID, LOCALID, FECHA, VALORACION) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nuevaVisita.getId());
                pstmt.setString(2, cliente.getId());
                pstmt.setString(3, nuevaVisita.getLocal().getId());
                pstmt.setDate(4, new java.sql.Date(nuevaVisita.getFecha().getTime()));
                pstmt.setString(5, nuevaVisita.getValoracion());

                int filasInsertadas = pstmt.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Nueva visita guardada en la base de datos.");
                    return true;
                } else {
                    System.out.println("No se pudo guardar la nueva visita.");
                    return false;
                }
            }
            }catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static void cargarVisitasCliente(Connection conn, Cliente cliente) {
        String sql = "SELECT * FROM visita WHERE CLIENTEID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String idVisita = rs.getString("ID");
                String clienteId = rs.getString("CLIENTEID");
                String localId = rs.getString("LOCALID");
                Date fecha = rs.getDate("FECHA");
                String valoracion = rs.getString("VALORACION");

                Visita visita = new Visita();
                visita.setId(idVisita);
                visita.setClienteID(clienteId);
                visita.setFecha(fecha);
                visita.setHora("CORREGIR ESTO");
                visita.setValoracion(valoracion);


                Bar bar = buscarBarPorId(conn, localId);
                if (bar != null) {
                    visita.setLocal(bar);
                } else {
                    Discoteca disco = buscarDiscotecaPorId(conn, localId);
                    if (disco != null) {
                        visita.setLocal(disco);
                    } else {
                        System.out.println("Local no encontrado para ID: " + localId);
                    }
                }
                cliente.getVisitas().add(visita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Bar buscarBarPorId(Connection conn, String id) throws SQLException {
        String sqlBar = "SELECT * FROM bar WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlBar)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Bar bar = new Bar();
                String idBar = rs.getString("ID");
                String nombreBar = rs.getString("NOMBRE");
                String direccion = rs.getString("DIRECCION");
                String cp = rs.getString("CODIGOPOSTAL");
                int aforo = rs.getInt("AFORO");
                String telefonoBar = rs.getString("TELEFONO");
                int mediaedad = rs.getInt("MEDIAEDAD");
                int preciomedio = rs.getInt("PRECIOMEDIO");
                String link = rs.getString("LINKWEB");
                int terrazaNum = rs.getInt("TIENETERRAZA");
                boolean terraza = (terrazaNum == 1);

                bar.setId(idBar);
                bar.setNombre(nombreBar);
                bar.setDireccion(direccion);
                bar.setAforo(aforo);
                bar.setTelefono(telefonoBar);
                bar.setMediaEdad(mediaedad);
                bar.setPrecioMedio(preciomedio);
                bar.setWeb(link);
                bar.setTerraza(terraza);
                bar.setCP(cp);
                return bar;
            }
        }
        return null;
    }
    private static Discoteca buscarDiscotecaPorId(Connection conn, String id) throws SQLException {
        String sqlDisco = "SELECT * FROM discoteca WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlDisco)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Discoteca disco = new Discoteca();
                String idDisco = rs.getString("ID");
                String nombreDisco = rs.getString("NOMBRE");
                String direccionDisco = rs.getString("DIRECCION");
                String cpDisco = rs.getString("CODIGOPOSTAL");
                int capacidad = rs.getInt("CAPACIDAD");
                String telefonoDisco = rs.getString("TELEFONO");
                int mediaEdadDisco = rs.getInt("MEDIAEDAD");
                int precioMedioDisco = rs.getInt("PRECIOMEDIO");
                String linkDisco = rs.getString("LINKWEB");

                disco.setId(idDisco);
                disco.setNombre(nombreDisco);
                disco.setDireccion(direccionDisco);
                disco.setCP(cpDisco);
                disco.setAforo(capacidad);
                disco.setTelefono(telefonoDisco);
                disco.setMediaEdad(mediaEdadDisco);
                disco.setPrecioMedio(precioMedioDisco);
                disco.setWeb(linkDisco);
                return disco;
            }
        }
        return null;
    }

/*    public static void subirCaracteristicasaBD() {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "INSERT INTO Caracteristicas (id, descripcion) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Iterar sobre los valores de la enumeración y realizar las inserciones
            int id = 1; // ID inicial, puedes ajustarlo según tu lógica de asignación de IDs
            for (Caracteristica caracteristica : Caracteristica.values()) {
                pstmt.setInt(1, id);
                pstmt.setString(2, caracteristica.name());
                pstmt.addBatch(); // Agregar la operación de inserción al lote
                id++; // Incrementar el ID para el siguiente elemento
            }

            // Ejecutar el lote de inserciones
            pstmt.executeBatch();
            System.out.println("Inserción exitosa de todas las características.");
        } catch (SQLException e) {
            System.out.println("Error al insertar las características: " + e.getMessage());
        }
    }
 */
    private static void descargarCaracteristicas(){
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "SELECT descripcion FROM Caracteristicas";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                Caracteristica caracteristica = Caracteristica.valueOf(descripcion);
                caracteristicas.add(caracteristica);
            }
        } catch (SQLException e) {
            System.out.println("Error al descargar las características: " + e.getMessage());
        }
    }




}
