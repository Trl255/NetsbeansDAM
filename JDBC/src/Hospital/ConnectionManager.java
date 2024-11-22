/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josea
 */
public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3307/hospital";
    private static final String USER = "root"; // Cambia esto si tu usuario es diferente
    private static final String PASSWORD = ""; // Cambia esto si tienes contraseña

    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }



    
}
