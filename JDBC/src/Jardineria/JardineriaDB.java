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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class JardineriaDB {

    private Connection connection;

    public JardineriaDB(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos.");
            e.printStackTrace();
        }
    }

    public void mostrarPedidosEntreFechas(Date fechaInicio, Date fechaFin) {
        String sql = "SELECT p.CodigoPedido, p.FechaPedido, c.NombreCliente, pr.Gama "
                + "FROM pedidos p "
                + "JOIN clientes c ON p.CodigoCliente = c.CodigoCliente "
                + "JOIN detallepedidos dp ON p.CodigoPedido = dp.CodigoPedido "
                + "JOIN productos pr on pr.CodigoProducto = dp.CodigoProducto "
                + "WHERE p.FechaPedido BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int codigoPedido = rs.getInt("CodigoPedido");
                Date fechaPedido = rs.getDate("FechaPedido");
                String nombreCliente = rs.getString("NombreCliente");
                String gama = rs.getString("Gama");

                // Mostrar resultados
                System.out.println("CodigoPedido: " + codigoPedido + ", FechaPedido: " + fechaPedido
                        + ", NombreCliente: " + nombreCliente + ", Gama: " + gama);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta.");
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }

    // Consulta 2: Diferentes puestos de empleados y datos
    public void mostrarPuestosDeEmpleados() {
        String query = "SELECT DISTINCT Puesto FROM Empleados";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            List<String> puestos = new ArrayList<>();
            int index = 1;
            System.out.println("Puestos disponibles:");

            // Mostrar los puestos numerados
            while (rs.next()) {
                String puesto = rs.getString("Puesto");
                puestos.add(puesto);
                System.out.println(index + ". " + puesto);
                index++;
            }

            // Solicitar al usuario que elija un puesto por su número
            Scanner scanner = new Scanner(System.in);
            int puestoSeleccionado = -1;

            // Validar que la selección sea un número válido dentro del rango
            while (puestoSeleccionado < 1 || puestoSeleccionado > puestos.size()) {
                System.out.print("Elige un puesto por número (1-" + puestos.size() + "): ");
                try {
                    puestoSeleccionado = Integer.parseInt(scanner.nextLine());
                    if (puestoSeleccionado < 1 || puestoSeleccionado > puestos.size()) {
                        System.out.println("Número fuera de rango. Inténtalo de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa un número válido.");
                }
            }

            // Obtener el puesto seleccionado y mostrar la información del empleado y la oficina
            String puestoElegido = puestos.get(puestoSeleccionado - 1);
            mostrarEmpleadoYOficinaPorPuesto(puestoElegido);

        } catch (SQLException e) {
            System.out.println("Error al obtener los puestos de empleados.");
            e.printStackTrace();
        }
    }

    // Método para mostrar el nombre, apellidos y ciudad de la oficina según el puesto
    public void mostrarEmpleadoYOficinaPorPuesto(String puesto) {
        String query = "SELECT e.Nombre, e.Apellido1, e.Apellido2, o.Ciudad "
                + "FROM Empleados e "
                + "JOIN Oficinas o ON e.CodigoOficina = o.CodigoOficina "
                + "WHERE e.Puesto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, puesto);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("Nombre");
                    String Apellido1 = rs.getString("Apellido1");
                    String Apellido2 = rs.getString("Apellido2");
                    String ciudad = rs.getString("Ciudad");

                    System.out.println("Empleado: " + nombre + " " + Apellido1 + " " + Apellido2);
                    System.out.println("Ciudad de la oficina: " + ciudad);
                } else {
                    System.out.println("No se encontraron empleados con ese puesto.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los detalles del empleado y la oficina.");
            e.printStackTrace();
        }
    }

    //Consulta 3: Muestra, para cada uno de los pedidos,, el nombre del cliente que ha realizado el pedido, la fecha del pedido,
    //, la fecha del pedido, cuál es el nombre de los productos que se han comprado y su precio por unidad.
    public void mostrarPedidosPorCliente() {
        String query = "SELECT p.CodigoPedido, c.NombreCliente, p.FechaPedido, pr.Nombre, pr.PrecioVenta\n"
                + "FROM pedidos p\n"
                + "JOIN detallepedidos dp ON dp.CodigoPedido = p.CodigoPedido\n"
                + "JOIN clientes c ON p.CodigoCliente = c.CodigoCliente\n"
                + "JOIN productos pr ON pr.CodigoProducto = dp.CodigoProducto";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.println("Código de pedido: " + resultSet.getString("CodigoPedido")
                        + "\n Nombre del cliente: " + resultSet.getString("NombreCliente")
                        + "\n Fecha de pedido: " + resultSet.getString("FechaPedido")
                        + "\n Nombre producto: " + resultSet.getString("Nombre")
                        + "\n Precio de venta: " + resultSet.getString("PrecioVenta")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Consulta 4: Cantidad de pedidos por cliente.
    public void mostrarCantidadPedidosPorCliente() {
        String query = "SELECT c.NombreCliente, COUNT(p.CodigoPedido) AS CantidadPedidos "
                + "FROM clientes c "
                + "LEFT JOIN pedidos p ON c.CodigoCliente = p.CodigoCliente "
                + "GROUP BY c.NombreCliente "
                + "ORDER BY COUNT(c.NombreCliente) DESC ";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("Cliente: " + resultSet.getString("NombreCliente")
                        + ", Cantidad de Pedidos: " + resultSet.getInt("CantidadPedidos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consulta 5: Número de empleados por puesto
    public void mostrarEmpleadosPorPuesto() {
        String query = "SELECT Puesto, COUNT(*) AS CantidadEmpleados "
                + "FROM empleados "
                + "GROUP BY Puesto "
                + "ORDER BY CantidadEmpleados DESC";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("Puesto: " + resultSet.getString("Puesto")
                        + ", Cantidad de Empleados: " + resultSet.getInt("CantidadEmpleados"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consulta 6: Número de clientes por ciudad
    public void mostrarClientesPorCiudad() {
        String query = "SELECT Ciudad, COUNT(*) AS CantidadClientes "
                + "FROM clientes "
                + "GROUP BY Ciudad "
                + "ORDER BY CantidadClientes DESC";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("Ciudad: " + resultSet.getString("Ciudad")
                        + ", Cantidad de Clientes: " + resultSet.getInt("CantidadClientes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consulta 7: Producto más caro y más barato
    public void mostrarProductoMasCaroYBarato() {
        boolean barato = true;
        String query = "(SELECT Nombre, PrecioVenta "
                + "FROM productos "
                + "ORDER BY PrecioVenta DESC LIMIT 1) UNION ALL "
                + "(SELECT Nombre, PrecioVenta "
                + "FROM productos "
                + "ORDER BY PrecioVenta ASC LIMIT 1)";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println(barato == true ? "Producto más barato: " + resultSet.getString("Nombre")
                        + ", Precio: " + resultSet.getDouble("PrecioVenta")
                        : "Producto más caro: " + resultSet.getString("Nombre")
                        + ", Precio: " + resultSet.getDouble("PrecioVenta"));

                barato = false;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consulta 8: Total de productos en cada pedido y costo total
    public void mostrarDetallePedidos() {
        String query = "SELECT p.CodigoPedido, COUNT(pr.CodigoProducto) AS CantidadProductos, SUM(pr.PrecioVenta * dp.Cantidad) AS CostoTotal "
                + "FROM pedidos p "
                + "JOIN detallepedidos dp ON p.CodigoPedido = dp.CodigoPedido "
                + "JOIN productos pr ON dp.CodigoProducto = pr.CodigoProducto "
                + "GROUP BY p.CodigoPedido "
                + "ORDER BY CostoTotal DESC";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("Código Pedido: " + resultSet.getString("CodigoPedido")
                        + ", Cantidad de Productos: " + resultSet.getInt("CantidadProductos")
                        + ", Costo Total: " + resultSet.getDouble("CostoTotal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consulta 9: Pedidos con el producto más caro de la gama ‘Aromáticas’
    public void mostrarPedidosConProductoAromaticasMasCaro() {
        String query = "SELECT p.CodigoPedido, pr.Nombre, pr.PrecioVenta "
                + "FROM pedidos p "
                + "JOIN detallepedidos dp ON p.CodigoPedido = dp.CodigoPedido "
                + "JOIN productos pr ON dp.CodigoProducto = pr.CodigoProducto "
                + "WHERE pr.Gama = 'Arom?ticas' "
                + "ORDER BY pr.PrecioVenta DESC LIMIT 1";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("Código Pedido: " + resultSet.getString("CodigoPedido") + " " + resultSet.getString("Nombre")
                        + " "+resultSet.getString("PrecioVenta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
/*Realiza las siguientes consultas sobre la base de datos de jardinería.

1.	Pide dos fechas por teclado y muestra, para aquellos pedidos entre las dos fechas, el código del pedido, la fecha, el nombre del cliente y el nombre o gama del producto.

2.	Muestra por pantalla los diferentes puestos de la tabla de Empleados que hay. Elige uno de ellos y muestra el nombre, los dos apellidos y la ciudad (Tabla oficina) en la que trabajan.

3.	Muestra, para cada uno de los pedidos, el nombre del cliente que ha realizado el pedido, la fecha del pedido, cuál es el nombre de los productos que se han comprado y su precio por unidad.

4.	Muestra la cantidad de pedidos que ha realizado cada cliente (LEFT JOIN).

5.	Muestra cuantos empleados hay por puesto

6.	Muestra cuantos clientes existen en cada una de las ciudades.

7.	Muestra el nombre y el precio de venta del producto más caro y el nombre el precio de venta del producto más barato

8.	Muestra el codigo de cada pedido, cuantos productos hay en el pedido y cuanto ha costado (suma de las cantidades*precio_unidad)

9.	Mostrar el código de los pedidos donde se haya vendido el producto de la gama ‘Aromáticas’ más caro.*/
