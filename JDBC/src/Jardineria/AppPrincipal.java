package Jardineria;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;

public class AppPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar las fechas de inicio y fin con validación utilizando FechaUtils
         String fechaInicioStr = FechaUtils.pedirFecha("Introduce la fecha de inicio (dd-MM-yyyy): ");
         String fechaFinStr = FechaUtils.pedirFecha("Introduce la fecha de fin (dd-MM-yyyy): ");
        // Usar SimpleDateFormat para convertir las fechas al formato correcto
        SimpleDateFormat dateFormatInput = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormatDB = new SimpleDateFormat("yyyy-MM-dd");

        try {
             java.util.Date parsedFechaInicio = dateFormatInput.parse(fechaInicioStr);
             java.util.Date parsedFechaFin = dateFormatInput.parse(fechaFinStr);

            // Convierte java.util.Date a java.sql.Date
             Date fechaInicio = new Date(parsedFechaInicio.getTime());
             Date fechaFin = new Date(parsedFechaFin.getTime());
            // Crear objeto de conexión a la base de datos
            JardineriaDB db = new JardineriaDB("jdbc:mysql://localhost:3307/jardineria", "root", "");

            //CONSULTA 1 Llamar al método para mostrar los pedidos entre las fechas
             db.mostrarPedidosEntreFechas(fechaInicio, fechaFin);
            //CONSULTA 2 Llamar al método para mostrar los puestos y solicitar la selección
            System.out.println("\n----------CONSULTA 2----------");
            db.mostrarPuestosDeEmpleados();

            //CONSULTA 3: Muestra, para cada uno de los pedidos.
            System.out.println("\n----------CONSULTA 3----------");
            db.mostrarPedidosPorCliente();

            //CONSULTA 4: Muestra la cantidad de pedidos que ha realizado cada cliente (LEFT JOIN)
            System.out.println("\n----------CONSULTA 4----------");
            System.out.println("Ordenados de mayor a menor");
            db.mostrarCantidadPedidosPorCliente();

            //CONSULTA 5: Número de empleados por puesto.
            System.out.println("\n----------CONSULTA 5----------");
            db.mostrarEmpleadosPorPuesto();

            //CONSULTA 6: Número de clientes por ciudad.
            System.out.println("\n----------CONSULTA 6----------");
            db.mostrarClientesPorCiudad();

            //CONSULTA 7: Producto más caro y más barato.
            System.out.println("\n----------CONSULTA 7----------");
            db.mostrarProductoMasCaroYBarato();

            //CONSULTA 8: Total de productos en cada pedido y costo total.
            System.out.println("\n----------CONSULTA 8----------");
            db.mostrarDetallePedidos();
           
            
            //CONSULTA 9: Pedidos con el producto más caro de la gama ‘Arom?ticas’.
            System.out.println("\n----------CONSULTA 9----------");
            db.mostrarPedidosConProductoAromaticasMasCaro();

            
            // Cerrar la conexión
            db.closeConnection();

        } catch (Exception e) {
            // Manejo de excepciones en caso de que las fechas no sean válidas
            System.out.println("Error al procesar las fechas.");
            e.printStackTrace();
        }

        scanner.close();
    }
}
