package Jardineria;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author josea
 */
public class FechaUtils {
    // Método para validar el formato de la fecha (dd-MM-yyyy)
    public static boolean esFechaValida(String fecha) {
        // Expresión regular para validar el formato dd-MM-yyyy
        Pattern pattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }

    // Método para pedir la fecha con validación
    public static String pedirFecha(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        String fecha;
        while (true) {
            System.out.print(mensaje);
            fecha = scanner.nextLine();
            // Verificar que la fecha tenga el formato correcto
            if (esFechaValida(fecha)) {
                // Intentar parsear la fecha con SimpleDateFormat para asegurar que es válida
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false); // Desactivar la tolerancia para fechas como "2023-02-30"
                try {
                    sdf.parse(fecha); // Intentar convertir la fecha
                    return fecha; // Fecha válida
                } catch (ParseException e) {
                    System.out.println("Fecha no válida. Asegúrese de que la fecha sea válida (por ejemplo, 01-12-2008).");
                }
            } else {
                System.out.println("Formato de fecha incorrecto. Debe ser en formato dd-MM-yyyy.");
            }
        }
    }
}