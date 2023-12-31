package org.proyectosalida.Datos;

import org.proyectosalida.Constructores.*;

import javax.swing.*;
import java.io.ObjectStreamClass;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.Timestamp;

public class AlmacenDeDatos {

    //CONEXION BASE DE DATOS
    private static Connection conn;

//OBJ
    private Cliente cliente;
    private Dueño dueño;
    private boolean votoDiarioEncuesta; //El boolean va aqui ya que sino siempre que se habra y cierre la main ventana se va a restablecer el valor, aqui no. Solo se puede una vez así.

//Booleans
    private boolean esDueño;
    private boolean esCliente;

//Listas
    public static HashMap<String, Integer> valoresVotaciones; //id, num
    private static ArrayList<Caracteristica> caracteristicas; //todas las caracterisiticas

     private ArrayList<Usuario> usuarios;
     private ArrayList<Usuario> usuariosPrueba;
     private static ArrayList<Local> locales; //Todos los locales en nuestra bd
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

        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";
        try{
            conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la Base de Datos. \nCompruebe la conexión a Internet y vuelva a intentarlo.");
        }

        System.out.println("ALMACEN CREADO");
        //descargarCaracteristicas();

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
        ArrayList<Horario> horariosDisco = new ArrayList<>();

        /*
        horariosMonty.add(new Horario(1, "07:30", "23:30"));
        horariosMonty.add(new Horario(2, "07:30", "23:30"));
        horariosMonty.add(new Horario(3, "07:30", "23:30"));
        horariosMonty.add(new Horario(4, "07:30", "23:30"));
        horariosMonty.add(new Horario(5, "07:30", "23:30"));
        horariosMonty.add(new Horario(6, "07:30", "23:30"));
        horariosMonty.add(new Horario(7, "07:30", "16:00"));


        horariosDisco.add(new Horario(1, "00:30", "05:30"));
        horariosDisco.add(new Horario(2, "00:30", "05:30"));
        horariosDisco.add(new Horario(3, "00:30", "05:30"));
        horariosDisco.add(new Horario(4, "06:30", "16:30"));
        horariosDisco.add(new Horario(5, "00:30", "05:30"));
        horariosDisco.add(new Horario(6, "00:30", "05:30"));
        horariosDisco.add(new Horario(7, "00:30", "05:00"));

         */

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

    public static void inicializarValoresEncuesta(){
        for(Local local : locales){
            valoresVotaciones.put(local.getId(), 0);
        }
    }


    //----------MANEJO DE BASE DE DATOS-----------
    public static boolean registrarDueño(Dueño dueño) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        // Mostrar mensaje de "Creando dueño..."
        //JOptionPane.showMessageDialog(null, "Creando dueño...", "Registro en progreso", JOptionPane.INFORMATION_MESSAGE);

            String sql = "INSERT INTO USUARIO (TIPO, ID, NOMBRE, APELLIDO, FECHANACIMIENTO, CONTRASEÑA, TELEFONO, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "dueño");
                pstmt.setString(2, dueño.getId());
                pstmt.setString(3, dueño.getNombre());
                pstmt.setString(4, dueño.getApellido());
                pstmt.setDate(5, new java.sql.Date(dueño.getFechaNacimiento().getTime()));
                pstmt.setString(6, dueño.getContraseña());
                pstmt.setString(7, dueño.getTelefono());
                pstmt.setString(8, dueño.getCorreo());

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el dueño.", "Error de registro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el dueño: " + e.getMessage(), "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static boolean registrarCliente(Cliente cliente) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        // Mostrar mensaje de "Creando cliente..."
        JOptionPane.showMessageDialog(null, "Creando cliente...", "Registro en progreso", JOptionPane.INFORMATION_MESSAGE);

            String sql = "INSERT INTO USUARIO (TIPO, ID, NOMBRE, APELLIDO, FECHANACIMIENTO, CONTRASEÑA, TELEFONO, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "cliente");
                pstmt.setString(2, cliente.getId());
                pstmt.setString(3, cliente.getNombre());
                pstmt.setString(4, cliente.getApellido());
                pstmt.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
                pstmt.setString(6, cliente.getContraseña());
                pstmt.setString(7, cliente.getTelefono());
                pstmt.setString(8, cliente.getCorreo());

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el dueño.", "Error de registro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el dueño: " + e.getMessage(), "Error de registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean actualizarDatosUsuarioBD(Usuario usuario, String nombre, String apellido, Date fechaNacimiento, String contraseña, String tlf, String email) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String tipo = "";
            if(usuario.getClass().equals(Cliente.class)){
                tipo = "cliente";
            }else{
                tipo = "dueño";
            }
            String sql = "UPDATE USUARIO SET NOMBRE = ?, APELLIDO = ?, FECHANACIMIENTO = ?, CONTRASEÑA = ?, TELEFONO = ?, EMAIL = ? WHERE ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, apellido);
                pstmt.setDate(3, new java.sql.Date(fechaNacimiento.getTime()));
                pstmt.setString(4, contraseña);
                pstmt.setString(5, tlf);
                pstmt.setString(6, email);
                pstmt.setString(7, usuario.getId());

                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Datos del cliente actualizados en la base de datos.");
                    return true;
                } else {
                    System.out.println("No se pudo actualizar los datos del cliente.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static boolean inicioSesionDueño(String usuario, String contraseña, Dueño dueño, AlmacenDeDatos almacen) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

            String sql = "SELECT * FROM USUARIO WHERE ID = ? AND Contraseña = ? AND TIPO='dueño'";
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean inicioSesionCliente(String usuario, String contraseña, Cliente cliente, AlmacenDeDatos almacen) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

            String sql = "SELECT * FROM USUARIO WHERE ID = ? AND Contraseña = ? AND TIPO = 'cliente'";
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
                    //cargarValoresVotaciones(conn); lo he puesto en cuando se registra una salida para que antes no lo vean

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void cargarLocales(Connection conn, Boolean isDueño, Dueño dueño, AlmacenDeDatos almacenDeDatos) {
        String sqlLocal = "";
        if(isDueño){
            sqlLocal = "SELECT * FROM LOCAL WHERE dueñoid = ?";
        }else{
            sqlLocal = "SELECT * FROM LOCAL";
        }

        try (PreparedStatement pstmtLocales = conn.prepareStatement(sqlLocal)) {
            if(isDueño){
                pstmtLocales.setString(1, dueño.getId());
            }
            ResultSet rsLocales = pstmtLocales.executeQuery();

            if(!rsLocales.next()){
                System.out.println("La tabla de LOCALES esta VACIA para esta busqueda.");
            }

            while (rsLocales.next()) {
                String tipo = rsLocales.getString("TIPO");
                String id = rsLocales.getString("ID");
                String nombre = rsLocales.getString("NOMBRE");
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
                Discoteca disco = new Discoteca();
                if("bar".equals(tipo)){
                    bar.setId(id);
                    bar.setNombre(nombre);
                    bar.setDireccion(direccion);
                    bar.setAforo(aforo);
                    bar.setTelefono(telefonoBar);
                    bar.setMediaEdad(mediaedad);
                    bar.setPrecioMedio(preciomedio);
                    bar.setWeb(link);
                    bar.setTerraza(terraza);
                    bar.setCP(cp);

                    ArrayList<Caracteristica> caracteristicas = cargarCaracteristicasLocal(conn, bar);
                    bar.setCaracteristicas(caracteristicas);
                    ArrayList<Horario> horariosLocal = cargarHorariosLocal(conn, bar);
                    bar.setHorarios(horariosLocal);

                }else if("discoteca".equals(tipo)){
                    disco.setId(id);
                    disco.setNombre(nombre);
                    disco.setDireccion(direccion);
                    disco.setAforo(aforo);
                    disco.setTelefono(telefonoBar);
                    disco.setMediaEdad(mediaedad);
                    disco.setPrecioMedio(preciomedio);
                    disco.setWeb(link);
                    disco.setCP(cp);

                    ArrayList<Caracteristica> caracteristicas = cargarCaracteristicasLocal(conn, disco);
                    disco.setCaracteristicas(caracteristicas);
                    ArrayList<Horario> horariosLocal = cargarHorariosLocal(conn, disco);
                    disco.setHorarios(horariosLocal);
                    disco.setDjResidente(cargarDjBD(conn, true, disco.getId()));
                    disco.setDjInvitado(cargarDjBD(conn, false, disco.getId()));
                }else{
                    System.out.println("Falla encontrar el tipo correctamente aqui");
                }


                if(isDueño){
                    if("bar".equals(tipo)){
                        dueño.getLocales().add(bar);
                    }else if("discoteca".equals(tipo)){
                        dueño.getLocales().add(disco);
                    }else{
                        System.out.println("fallo 1");
                    }
                    System.out.println("1 BAR añadido: "+bar.getNombre());
                }else{
                    if("bar".equals(tipo)){
                        almacenDeDatos.getLocales().add(bar);
                    }else if("discoteca".equals(tipo)){
                        almacenDeDatos.getLocales().add(disco);
                    }else{
                        System.out.println("Fallo 2");
                    }
                    System.out.println("1 DSICO añadido: "+disco.getNombre());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static boolean guardarLocalNuevoBD(Local local, Dueño dueño) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "INSERT INTO LOCAL (TIPO, ID, NOMBRE, DIRECCION, CODIGOPOSTAL, AFORO, TELEFONO, MEDIAEDAD, PRECIOMEDIO, LINKWEB, TIENETERRAZA, DUEÑOID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String tipo;
            if(local.getClass().equals(Discoteca.class)){
                tipo = "discoteca";
            }else{
                tipo = "bar";
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, tipo);
                pstmt.setString(2, local.getId());
                pstmt.setString(3, local.getNombre());
                pstmt.setString(4, local.getDireccion());
                pstmt.setString(5, local.getCP());
                pstmt.setInt(6, local.getAforo());
                pstmt.setString(7, local.getTelefono());
                pstmt.setInt(8, local.getMediaEdad());
                pstmt.setInt(9, local.getPrecioMedio());
                pstmt.setString(10, local.getWeb());
                if(local.getClass().equals(Discoteca.class)){
                    pstmt.setString(11, null);
                }else{
                    pstmt.setInt(11, ((Bar) local).getTerraza() ? 1 : 0); // Convierte el booleano a num para bd
                }
                pstmt.setString(12, dueño.getId());


                guardarHorariosEnBD(conn, local, local.getHorarios());
                guardarCaracteristicasLocal(conn, local);
                if(local.getClass().equals(Discoteca.class)){
                    guardarDjBD(true, ((Discoteca) local).getDjResidente(), local.getId());
                    guardarDjBD(false, ((Discoteca) local).getDjInvitado(), local.getId());
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
    public static boolean actualizarDatosLocalBD(Local local, String instagramAntiguoDJ) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "UPDATE LOCAL SET TIPO = ?, NOMBRE = ?, DIRECCION = ?, CODIGOPOSTAL = ?, AFORO = ?, TELEFONO = ?, MEDIAEDAD = ?, PRECIOMEDIO = ?, LINKWEB = ?, TIENETERRAZA = ? WHERE ID = ?";

            String tipo = "";
            if(local.getClass().equals(Bar.class)){
                tipo = "bar";
            }else{
                tipo = "discoteca";
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, tipo);
                pstmt.setString(2, local.getNombre());
                pstmt.setString(3, local.getDireccion());
                pstmt.setString(4, local.getCP());
                pstmt.setInt(5, local.getAforo());
                pstmt.setString(6, local.getTelefono());
                pstmt.setInt(7, local.getMediaEdad());
                pstmt.setInt(8, local.getPrecioMedio());
                pstmt.setString(9, local.getWeb());
                if(local.getClass().equals(Bar.class)){
                    pstmt.setInt(10, ((Bar) local).getTerraza() ? 1 : 0); // Convierte el booleano a numero para la bd
                }else{
                    pstmt.setString(10, null);
                }
                pstmt.setString(11, local.getId());

                actualizarHorariosLocal(conn, local, local.getHorarios());
                actualizarCaracteristicasLocal(conn, local);
                if(local.getClass().equals(Discoteca.class)){
                    System.out.println("-----------EMPIEZAN LOS DJ'S--------------");
                    actualizarDj(conn, ((Discoteca) local).getDjResidente(), true, local.getId());
                    actualizarDj(conn, ((Discoteca) local).getDjInvitado(), false, local.getId());
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

    public static ArrayList<Caracteristica> cargarCaracteristicasLocal(Connection conn, Local local){
        String id = local.getId();
        System.out.println(id);

        ArrayList<Caracteristica> caracteristicas = new ArrayList<>();

        String sql = "select * from caracteristicaslocales where idlocal = ?";

        ArrayList<String> idCaracteristicas = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String idc = rs.getString("IDCARACTERISTICA");
                idCaracteristicas.add(idc);
            }
            System.out.println("ID's conseguidos!");
        } catch (SQLException e) {
            System.out.println("No se pueden conseguir los id's de las Caract.");
            System.out.println(e.getMessage());
        }


        for(String idCaracteristica : idCaracteristicas){
            String sql2 = "SELECT descripcion FROM caracteristicas WHERE ID = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                pstmt.setString(1, idCaracteristica);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Caracteristica nueva = Caracteristica.valueOf(rs.getString("descripcion"));
                    caracteristicas.add(nueva);
                    System.out.println("Caracteristica añadida: "+nueva);
                }

                System.out.println("Caracteristicas añadidas al local!");
            } catch (SQLException e) {
                System.out.println("No se pueden añadir las caracteristicas...");
                System.out.println(e.getMessage());
            }
        }
        return caracteristicas;
    }
    public static void guardarCaracteristicasLocal(Connection conn, Local local){
        String sqlGetID = "SELECT id FROM CARACTERISTICAS where DESCRIPCION = ?";
        ArrayList<String> IDCaracts = new ArrayList<>();

        for(Caracteristica caracteristica:local.getCaracteristicas()){
            System.out.println(caracteristica.toString());
            try (PreparedStatement pstmt = conn.prepareStatement(sqlGetID)) {
                pstmt.setString(1, caracteristica.toString());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    IDCaracts.add(rs.getString("ID"));
                }

                System.out.println("ID's encontrados...");
            } catch (SQLException e) {
                System.out.println("No se pueden encontrar los ID's de las caracteristicas seleccionadas");
                System.out.println(e.getMessage());
            }
        }

        String sqlInsert = "INSERT into CARACTERISTICASLOCALES (idlocal, idcaracteristica) VALUES (?, ?)";
        for(String idc : IDCaracts){
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                pstmt.setString(1, local.getId());
                pstmt.setString(2, idc);

                int filasInsertadas = pstmt.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Caracteristica añadida en BD.");
                } else {
                    System.out.println("No se pudo añadir la nueva caracteristica en BD.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void actualizarCaracteristicasLocal(Connection conn, Local local) {
        //BORRAR TODAS LAS CARACT EN TABLA CON ID DEL LOCAL
        String sqlBorrar = "DELETE from CARACTERISTICASLOCALES where IDLOCAL=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlBorrar)){
            pstmt.setString(1, local.getId());

            int filasInsertadas = pstmt.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Caracteristica antigua Borrada.");
            } else {
                System.out.println("No se pudo borrar la caracteristica antigua.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //AÑADIR LAS NUEVAS
        try{
            guardarCaracteristicasLocal(conn, local);
        }catch(Exception e){
            System.out.println("No se pueden actualizar: "+ e.getMessage());
        }
    }


    public static void guardarHorariosEnBD(Connection conn, Local local, List<Horario> horarios) {

        String sqlInsert = "INSERT INTO HORARIOS (IDLOCAL, DIA, HORAINICIO, HORAFIN) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            for (Horario horario : horarios) {
                pstmt.setString(1, local.getId());
                pstmt.setString(3, horario.getHoraInicio());
                pstmt.setString(4, horario.getHoraFin());
                pstmt.setString(2, String.valueOf(horario.getDia()));

                pstmt.executeUpdate();
            }
            System.out.println("Horarios guardados exitosamente en la base de datos para Local "+ local.getNombre());
        } catch (SQLException e) {
            System.out.println("No se han podido guardar los horarios");
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Horario> cargarHorariosLocal(Connection conn, Local local) {

        ArrayList<Horario> horariosLocal = new ArrayList<>();
        String sqlInsert = "SELECT * FROM HORARIOS WHERE IDLOCAL = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, local.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int dia = Integer.parseInt(rs.getString("DIA"));
                String horaInicio = rs.getString("HORAINICIO");
                String horaFin = rs.getString("HORAFIN");

                Horario horario = new Horario(dia, horaInicio, horaFin);
                horariosLocal.add(horario);
            }
            System.out.println("Horarios Cargados correctamente!");
        } catch (SQLException e) {
            System.out.println("No se han podido cargar los horarios");
            System.out.println(e.getMessage());
        }
        return horariosLocal;
    }
    public static void actualizarHorariosLocal(Connection conn, Local local, ArrayList<Horario> horariosNuevos){
        String sqlDelete = "DELETE from HORARIOS where IDLOCAL=?";
        String sqlInsert = "INSERT into HORARIOS (idlocal, dia, horainicio, horafin) values (?, ?, ?, ?)";

        try{
            //Eliminamos primero
            System.out.println(local.getId());
            PreparedStatement deleteStmt = conn.prepareStatement(sqlDelete);
            deleteStmt.setString(1, local.getId());
            deleteStmt.executeUpdate();
            System.out.println("Eliminado correctamente!");

            //Insertamos nuevos
            PreparedStatement insertstmt = conn.prepareStatement(sqlInsert);
            for(Horario horario : horariosNuevos){
                insertstmt.setString(1, local.getId());
                insertstmt.setString(2, String.valueOf(horario.getDia()));
                insertstmt.setString(3, horario.getHoraInicio());
                insertstmt.setString(4, horario.getHoraFin());

                insertstmt.executeUpdate();
            }

            System.out.println("Horarios actualizados para "+local.getId()+ " ("+local.getNombre()+")");

        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar HORARIOS");
            System.out.println(e.getMessage());
        }
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
            System.out.println(e.getMessage());
        }

        return false;
    }
    public static ArrayList<Visita> cargarVisitasCliente(Connection conn, Cliente cliente) {
       String sql = "";
        if(cliente == null){
            sql = "SELECT * FROM VISITA WHERE VALORACION IS NOT NULL"; //Para pillar todas las visitas
        }else{
            sql = "SELECT * FROM visita WHERE CLIENTEID = ?";
        }

        ArrayList<Visita> visitasConValoracion = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if(cliente != null){
                pstmt.setString(1, cliente.getId());
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String idVisita = rs.getString("ID");
                String clienteId = rs.getString("CLIENTEID");
                String localId = rs.getString("LOCALID");
                Date fecha = rs.getDate("FECHA");
                String valoracion = rs.getString("VALORACION");

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String hora = sdf.format(fecha);

                Visita visita = new Visita();
                visita.setId(idVisita);
                visita.setClienteID(clienteId);
                visita.setFecha(fecha);
                visita.setHora(hora);
                visita.setValoracion(valoracion);


                Bar bar = buscarBarPorId(conn, localId);
                if (bar != null) {
                    visita.setLocal(bar);
                    if(cliente != null){
                        cliente.getVisitas().add(visita);
                    }else{
                        visitasConValoracion.add(visita);
                    }
                } else {
                    Discoteca disco = buscarDiscotecaPorId(conn, localId);
                    if (disco != null) {
                        visita.setLocal(disco);
                        if(cliente != null){
                            cliente.getVisitas().add(visita);
                        }else{
                            visitasConValoracion.add(visita);
                        }
                    } else {
                        System.out.println("Local no encontrado para ID: " + localId + ". Evitandolo...");
                        //visita.setLocal(new Bar("NotFound/Removed", "", "", 0, "", 0,0, "", null, false, null));
                    }
                }

            }
            System.out.println("Visitas descargadas!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(cliente == null){
            return visitasConValoracion;
        }else{
            return null;
        }

    }
    public static boolean actualizarValoracionVisita(String idVisita, String valoracion) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "UPDATE VISITA SET VALORACION = ? WHERE ID = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, valoracion);
                    pstmt.setString(2, idVisita);

                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Valoracion actualizada!");
                    return true;
                } else {
                    System.out.println("No se pudo actualizar la valoracion");
                    return false;
                }

                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean guardarDjBD(Boolean residente, DJ dj, String idLocal){ //Si residente es false entonces es invitado
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {

            //GUARDAMOS EL DJ EN SU TABLA
            String sql = "INSERT INTO DJ (INSTAGRAM, NOMBRE, APELLIDO, NOMBREMUSICAL, NACIONALIDAD, EDAD, GENEROMUSICAL, ESTILOMUSICAL) VALUES (?,?,?,?,?,?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dj.getInstagram());
                pstmt.setString(2, dj.getNombre());
                pstmt.setString(3, dj.getApellido());
                pstmt.setString(4, dj.getNombreMusical());
                pstmt.setString(5, dj.getNacionalidad());
                pstmt.setInt(6, dj.getEdad());
                pstmt.setString(7, dj.getGeneroMusical());
                pstmt.setString(8, dj.getEstiloMusical());

                int filasInsertadas = pstmt.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("DJ registrado en BD!.");
                } else {
                    System.out.println("No se pudo guardar el nuevo dj.");
                    return false;
                }
            }

            //SACO EL ID DEL DJ QUE ACABO DE GUARDAR
            int idDJ =0;
            String sqlid = "SELECT * FROM DJ WHERE INSTAGRAM =?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlid)) {
                pstmt.setString(1, dj.getInstagram());

                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    idDJ = rs.getInt("ID");
                }
            }
            System.out.println("ID del DJ que se acaba de crear: "+idDJ);

            //GUARDAMOS LA REFERENCIA DEL ID UNIENDOLO AL LOCAL CORRESP.
            //Primero van los residentes, asi que se crearia la nueva linea en la tabla, luego con el invitado seria editar la linea y añadir el nuevo id(invitado)
            if(residente){
                String sqlPrimero = "INSERT INTO DJLOCALES (IDLOCAL, IDRESIDENTE, IDINVITADO) VALUES (?,?,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlPrimero)) {
                    pstmt.setString(1, idLocal);
                    pstmt.setInt(2, idDJ);
                    pstmt.setString(3, null);

                    int filasInsertadas = pstmt.executeUpdate();

                    if (filasInsertadas > 0) {
                        System.out.println("DJ RESIDENTE añadido a la tabla con el local.");
                    } else {
                        System.out.println("No se pudo guardar la referencia del local con dj RESIDENTE");
                        return false;
                    }
                }
            }else{
                String sqlSegundo = "UPDATE DJLOCALES SET IDINVITADO = ? WHERE IDLOCAL = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlSegundo)) {
                    pstmt.setInt(1, idDJ);
                    pstmt.setString(2, idLocal);

                    int filasActualizadas = pstmt.executeUpdate();

                    if (filasActualizadas > 0) {
                        System.out.println("INVITADO AÑADIDO!");
                        return true;
                    } else {
                        System.out.println("INVITADO NO SE PUEDE ACTUALIZAR!");
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
    public static DJ cargarDjBD(Connection conn, Boolean residente, String idlocal){
        //ENCONTRAR LOS ID'S DE LOS DJ'S CON EL ID DEL LOCAL
        int idresidente = -1;
        int idinvitado = -1;
        String sql = "SELECT * FROM DJLOCALES WHERE IDLOCAL = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idlocal);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                idresidente = rs.getInt("IDRESIDENTE");
                idinvitado = rs.getInt("IDINVITADO");
                System.out.println("id's encontrados para los DJ's!");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        //CREAR LOS CONSTRUCTORES DEL DJ CON EL ID OBTENIDO
        DJ nuevo = new DJ();

        int idActual = residente ? idresidente : idinvitado;

        String sql1 = "SELECT * FROM DJ WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setInt(1, idActual);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nuevo.setNombre(rs.getString("nombre"));
                nuevo.setApellido(rs.getString("apellido"));
                nuevo.setNombreMusical(rs.getString("nombremusical"));
                nuevo.setNacionalidad(rs.getString("nacionalidad"));
                nuevo.setEdad(rs.getInt("edad"));
                nuevo.setGeneroMusical(rs.getString("generomusical"));
                nuevo.setEstiloMusical(rs.getString("estilomusical"));
                nuevo.setInstagram(rs.getString("INSTAGRAM"));
                nuevo.setId(idActual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return nuevo;

    }
    public static boolean actualizarDj(Connection conn, DJ nuevo, Boolean residente, String idlocal){
        //ACTUALIZAR DATOS DEL DJ
        String sql = "UPDATE DJ SET INSTAGRAM = ?, NOMBRE = ?, APELLIDO = ?, NOMBREMUSICAL =?, NACIONALIDAD=?, EDAD=?, GENEROMUSICAL=?, ESTILOMUSICAL=? WHERE ID =?";

        if(nuevo.getId() != null){
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nuevo.getInstagram());
                pstmt.setString(2, nuevo.getNombre());
                pstmt.setString(3, nuevo.getApellido());
                pstmt.setString(4, nuevo.getNombreMusical());
                pstmt.setString(5, nuevo.getNacionalidad());
                pstmt.setInt(6, nuevo.getEdad());
                pstmt.setString(7, nuevo.getGeneroMusical());
                pstmt.setString(8, nuevo.getEstiloMusical());
                pstmt.setInt(9, nuevo.getId());

                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("DATOS DJ ACTUALIZADOS!");
                } else {
                    System.out.println("No se pudo actualizar LOS DATOS DEL DJ");
                    return false;
                }
            }catch (SQLException e) {
                System.out.println("en actualizar datos dj: "+e.getMessage());
            }
        }else{
            guardarDjBD(residente, nuevo, idlocal);
        }


        return true;
    }

    public static void cargarValoresVotaciones(){
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {

            //RECORRER LOCALES PARA SI ALGUNO NO ESTA METIO EN LA TABLA AÑADIRLO
            String sqlCheckLocal = "SELECT * FROM VOTACION WHERE idlocal=?";
            for(Local local : locales){
                try (PreparedStatement pstmt = conn.prepareStatement(sqlCheckLocal)) {
                    pstmt.setString(1, local.getId());

                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.next()) {
                        System.out.println("El local no esta en la tabla de valores (BD). Añadiendolo...");
                        inicializarLocalEnVotacion(conn, local);
                    }

                }
            }

            //PILLAR LOS 6 MAS GRANDES
            String sql = "SELECT * FROM VOTACION ORDER BY VALOR DESC";
            int index = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                ResultSet rs = pstmt.executeQuery();
                while (rs.next() && index < 6) {
                    String idlocal = rs.getString("IDLOCAL");
                    Local local = buscarLocalPorId(conn, idlocal);
                    String nombre = local.getNombre();
                    int valor = rs.getInt("VALOR");

                    valoresVotaciones.put(nombre, valor);
                    index++;
                    System.out.println("------Valor en Votacion (" + local.getId() + "): " + valor);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean inicializarLocalEnVotacion(Connection conn, Local local){
        String sql = "INSERT INTO VOTACION (IDLOCAL, VALOR) VALUES (?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, local.getId());
            pstmt.setInt(2, 0);

            int filasInsertadas = pstmt.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Local inicializado en tabla VOTACION.");
                return true;
            } else {
                System.out.println("NO se pudo inicializar el local en VOTACION.");
                return false;
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
    public static boolean actualizarValorVotacion(Local local){
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {

            //GET VALOR EN BD
            int valorActual = 0;
            String sqlGetValor = "SELECT * FROM VOTACION WHERE IDLOCAL = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlGetValor)) {
                pstmt.setString(1, local.getId());

                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    valorActual = rs.getInt("VALOR");
                }
            }

            //ACTUALIZAR CON +1
            String sql = "UPDATE VOTACION SET VALOR =? WHERE IDLOCAL =?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, valorActual+1);
                pstmt.setString(2, local.getId());

                int filasInsertadas = pstmt.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("VOTACION ACTUALIZADO EN BD.");
                    return true;
                } else {
                    System.out.println("NO se pudo actualizar la VOTACION.");
                    return false;
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static int encontrarValorMaximoVotacion(){
        String sql  = "SELECT VALOR FROM VOTACION ORDER BY VALOR DESC";
        int fila = 1;
        int valor = -1;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while(rs.next() && fila ==1){ //Solo coge la primera fila asi
                valor = rs.getInt("VALOR");
                fila ++;
            }

        }catch (SQLException e){

        }

        return valor;

    }


    public static Local buscarLocalPorId(Connection conn, String id){
        Local local = null;
        try{
            local = buscarBarPorId(conn, id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if(local == null){
            try{
                local = buscarDiscotecaPorId(conn, id);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if(local == null){
            System.out.println("NO se ha ENCONTRADO el LOCAL en la BD");
        }
        return local;
    }
    private static Bar buscarBarPorId(Connection conn, String id) throws SQLException {
        String sqlBar = "SELECT * FROM LOCAL WHERE ID = ? AND TIPO = 'bar'";
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
        String sqlDisco = "SELECT * FROM LOCAL WHERE ID = ? AND TIPO = 'discoteca'";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlDisco)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Discoteca disco = new Discoteca();
                String idDisco = rs.getString("ID");
                String nombreDisco = rs.getString("NOMBRE");
                String direccionDisco = rs.getString("DIRECCION");
                String cpDisco = rs.getString("CODIGOPOSTAL");
                int capacidad = rs.getInt("AFORO");
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
    public static Usuario buscarUsuarioPorId(String id){
        String dbURL = "jdbc:oracle:thin:@proyectosalida_tpurgent?TNS_ADMIN=src/main/resources/Wallet_proyectoSalida";

        Usuario usuario = null;
        try (Connection conn = DriverManager.getConnection(dbURL, "Admin", "Oiogorta2023")) {
            String sql = "SELECT * FROM USUARIO WHERE ID = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                ResultSet rs = pstmt.executeQuery();

                if(rs.next()){
                    String tipo = rs.getString("tipo");
                    if("cliente".equals(tipo)){
                        usuario = new Cliente();
                    }else if ("dueño".equals(tipo)){
                        usuario = new Dueño();
                    }else{
                        System.out.println("Usuario mal introducido para cargar usuario por ID: "+tipo);
                    }

                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    Date fechanacimiento = rs.getDate("fechanacimiento");
                    String contraseña = rs.getString("contraseña");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");

                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setFechaNacimiento(fechanacimiento);
                    usuario.setContraseña(contraseña);
                    usuario.setTelefono(telefono);
                    usuario.setCorreo(email);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return usuario;
    }




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
    } //En array Caracteristicas se ponen todas las  que hay en BD




        }
