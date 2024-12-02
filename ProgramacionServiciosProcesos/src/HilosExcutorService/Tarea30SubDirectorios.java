/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 *
 * @author josea
 */
public class Tarea30SubDirectorios {
       public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contFileCopy = 0;
        String respuesta = "";
        boolean pedirDirOrigen = true;
        boolean pedirCopiarCarpeta = true;

        String dirOrigen = "prueba";

        try {
            // List to hold tasks
            List<Tarea30> listaTareas = new ArrayList<>();
            ExecutorService pool = Executors.newFixedThreadPool(5);

            // Request the original directory from the user
            while (true) { // Loop to allow navigation between directories
                if (pedirDirOrigen) {
                    dirOrigen = Metodos.solicitudInformacion(sc, "Ingrese la ruta del directorio origen (o 'salir' para terminar)", 4);
                    if (dirOrigen.equalsIgnoreCase("salir")) {
                        System.out.println("Saliendo del programa.");
                        break;
                    }
                }

                File origen = new File(dirOrigen);

                // If the directory exists, proceed
                if (!origen.exists()) {
                    System.out.println("Directorio no encontrado. Inténtelo de nuevo.");
                    continue;
                }

                // Show current directory and files
                System.out.println("Directorio actual: " + origen.getAbsolutePath());
                File[] archivos = origen.listFiles();
                if (archivos != null && archivos.length > 0) {
                    System.out.println("Contenido:");
                    for (File archivo : archivos) {
                        System.out.println((archivo.isDirectory() ? "[Carpeta] " : "[Archivo] ") + archivo.getName());
                    }
                } else {
                    System.out.println("El directorio está vacío.");
                }

                // Ask the user if they want to navigate to the parent directory
                respuesta = Metodos.solicitudInformacion(sc, "¿Desea navegar a la carpeta anterior? (si/no)", 1);
                if (respuesta.equalsIgnoreCase("si")) {
                    dirOrigen = origen.getParent();
                    if (dirOrigen == null) {
                        System.out.println("No hay un directorio padre. Está en la raíz.");
                        dirOrigen = origen.getAbsolutePath(); // Keep current path
                    }
                } else {
                    break; // Exit the navigation loop
                }
            }

            if (!dirOrigen.equalsIgnoreCase("salir")) {
                // Continue with the existing logic to copy files
                // Example: Call Copiar(...) or any other operations
                System.out.println("Continuando con la copia de archivos desde: " + dirOrigen);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    public static Future<List<String>> Copiar(File fOrigen, String dirDestino, ExecutorService pool) {
        Tarea30 tarea = new Tarea30(fOrigen, dirDestino);
        return pool.submit(tarea);
    }
}