package Jardineria;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class Metodos {

    static Connection connection;
    Scanner sc = new Scanner(System.in);

    static int numberInteger(Scanner sc, String informacion) {
        boolean numberOk = false;
        int value = 0;

        while (!(numberOk)) {
            try {
                System.out.println("Intruduzca " + informacion);
                value = sc.nextInt();
                sc.nextLine();

                numberOk = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Recuerde, Introduzca un número entero");

            }

        }
        return value;
    }

    static void connectionDDBB(String baseDeDatos) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + baseDeDatos, "root", "");

            String sql = "";
            sql += "SELECT NombreCliente ";
            sql += "FROM clientes";

            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery();

            while (rs.next()) {
                String nombreCliente = rs.getString("NombreCliente");
                System.out.println("Nombres clientes: " + nombreCliente);

            }

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error cnfe en connectionDDBB: " + cnfe);
        } catch (SQLException sqle) {
            System.out.println("Error sqle en connectionDDBB: " + sqle);
        }
    }

    static void showMenu() {

        System.out.println("*************MENÚ DEL PROGRAMA*************");
        System.out.println("\n1-Pide dos fechas por teclado y muestra, para aquellos pedidos entre las dos fechas, el código del pedido, "
                + "la fecha, el nombre del cliente y el nombre o gama del producto."
                + "\n2.	Muestra por pantalla los diferentes puestos de la tabla de Empleados que hay. Elige uno de ellos y muestra el nombre, "
                + "los dos apellidos y la ciudad (Tabla oficina) en la que trabajan.");

    }

}
