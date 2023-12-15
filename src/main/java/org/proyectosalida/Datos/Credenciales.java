package org.proyectosalida.Datos;

import java.sql.*;

public class Credenciales {
    public static String inicioSesion(String usuario, String contraseña) {
        String dbURL = "jdbc:oracle:thin:@proyectosalida_high?TNS_ADMIN=/Users/erikeguskiza/Documents/BaseDeDatos/Wallet_proyectoSalida";
        String dbUsuario = "Admin";
        String dbContraseña = "Oiogorta2023";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsuario, dbContraseña)) {
            // Consulta en la tabla Cliente
            String sqlCliente = "SELECT * FROM Cliente WHERE ID = ? AND Contraseña = ?";
            try (PreparedStatement pstmtCliente = conn.prepareStatement(sqlCliente)) {
                pstmtCliente.setString(1, usuario);
                pstmtCliente.setString(2, contraseña);
                ResultSet rsCliente = pstmtCliente.executeQuery();

                if (rsCliente.next()) {
                    return "Cliente";
                }
            }

            // Consulta en la tabla Dueño
            String sqlDueño = "SELECT * FROM Dueño WHERE ID = ? AND Contraseña = ?";
            try (PreparedStatement pstmtDueño = conn.prepareStatement(sqlDueño)) {
                pstmtDueño.setString(1, usuario);
                pstmtDueño.setString(2, contraseña);
                ResultSet rsDueño = pstmtDueño.executeQuery();

                if (rsDueño.next()) {
                    return "Dueño";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
