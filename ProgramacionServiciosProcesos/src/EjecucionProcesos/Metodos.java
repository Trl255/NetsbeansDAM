/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjecucionProcesos;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author josea
 */
public class Metodos {

    static Scanner sc = new Scanner(System.in);

    public static String solicitudInformacion(Scanner sc, String informacion, int longitud) {
        boolean validacion = false;
        String respuesta = "";
        System.out.println("Intruduzca " + informacion);
        respuesta = sc.nextLine();
        while (!(validacion)) {
            try {

                if (respuesta.trim().isEmpty() || respuesta.length() >= longitud) {
                    validacion = true;
                } else {
                    System.out.println("Información incorrecta, por favor introuzca " + informacion + " con " + longitud + " como minimo");
                    respuesta = sc.nextLine();
                }

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(e.getMessage());

            }

        }
        return respuesta;
    }

// Método para mostrar el menú y obtener la opción
    // Método para mostrar el menú y ejecutar acciones dinámicas
    public static void mostrarMenuRunnable(int numOpciones, String[] nombresOpciones, Runnable[] acciones) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1; // Valor inicial inválido para validar entrada

        // Validar que los parámetros sean coherentes
        if (nombresOpciones == null || nombresOpciones.length != numOpciones) {
            throw new IllegalArgumentException("El numero de opciones y los nombres no coinciden.");
        }
        if (acciones == null || acciones.length != numOpciones) {
            throw new IllegalArgumentException("El numero de opciones y las acciones no coinciden.");
        }

        // Ciclo para mostrar el menú y ejecutar la acción correspondiente
        while (opcion < 1 || opcion > numOpciones) {
            System.out.println("-------------- MENU ----------------");
            for (int i = 0; i < numOpciones; i++) {
                System.out.println(nombresOpciones[i]);
            }
            System.out.println("------------------------------------");

            try {
                System.out.print("Selecciona una opcion: ");
                opcion = sc.nextInt();

                if (opcion < 1 || opcion > numOpciones) {
                    System.out.println("Por favor, selecciona una opción válida (1-" + numOpciones + ").");
                } else {
                    acciones[opcion - 1].run(); // Ejecutar la acción correspondiente
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un numero.");
                sc.next(); // Limpiar la entrada no válida
            }
        }
    }

    public static void salirAtomicBoolean(AtomicBoolean salir) {
        System.out.println("Saliendo del programa.");
        salir.set(true); // Cambia el estado para salir del bucle
    }

    public static void ejecucionMicrosoftWord() {
        lanzarProceso("C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
    }

    public static void ejecucionMicrosoftPaint() {
        lanzarProceso("C:\\Windows\\System32\\mspaint.exe"); // Ruta estándar
    }

    public static void ejecucionMicrosoftBlocNotas() {
        lanzarProceso("C:\\Windows\\System32\\notepad.exe"); // Ruta estándar
    }

    public static void ejecucionComandoCMD() {

        String comando = solicitudInformacion(sc, "un comando para ejecutar en CMD", 5);
        lanzarComandoCMD(comando);
    }

    public static void lanzarProceso(String programa) {
        try {
            ProcessBuilder pb = new ProcessBuilder(programa);
            pb.start();
            System.out.println("Se ha iniciado el programa " + programa);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    public static void lanzarComandoCMD(String comando) {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
            pb.start();
            System.out.println("Se ha ejecutado el comando \"" + comando + "\"");
        } catch (Exception e) {
            System.out.println("No se ha podido ejecutar el comando " + comando + " " + e.getMessage());
        }
    }

}
