/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class FicheroBinarioEscrituraYlectura {

   // Método para escribir en el fichero binario
    public static void escribirFichero(String nombreFichero, String nombre, int edad, String ciudad) {
        try {
            // Abrir el archivo en modo "append" (true)
            // Si el archivo está vacío, escribir el encabezado (primera vez que se abre)
            boolean esNuevoFichero = new File(nombreFichero).length() == 0;
            
            // Creamos el ObjectOutputStream solo si es la primera vez
            ObjectOutputStream oos;
            if (esNuevoFichero) {
                oos = new ObjectOutputStream(new FileOutputStream(nombreFichero, true));
            } else {
                // Si el archivo no está vacío, tenemos que usar ObjectOutputStream
                // de modo que no sobrescriba el encabezado
                oos = new ObjectOutputStream(new FileOutputStream(nombreFichero, true)) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        // No escribir el encabezado, así evitamos el error
                    }
                };
            }

            // Escribir los objetos en el fichero
            oos.writeObject(nombre);
            oos.writeObject(edad);
            oos.writeObject(ciudad);
            oos.close(); // Cerrar el flujo de salida después de escribir

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    // Método para leer y mostrar los datos del fichero binario
    public static void mostrarFichero(String nombreFichero) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {
            while (true) {
                // Leer y mostrar los objetos del fichero (nombre, edad, ciudad)
                String nombre = (String) ois.readObject();
                int edad = (int) ois.readObject();
                String ciudad = (String) ois.readObject();
                System.out.println("Nombre: " + nombre + ", Edad: " + edad + ", Ciudad: " + ciudad);
            }
        } catch (EOFException e) {
            // Fin de archivo alcanzado
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir el nombre del fichero
        System.out.print("Introduce el nombre del fichero: ");
        String nombreFichero = sc.nextLine();

        // Crear el fichero vacío si no existe
        try {
            File file = new File(nombreFichero);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
            return;
        }

        // Mostrar el menú y manejar las opciones
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú:");
            System.out.println("1. Pedir datos y agregar al fichero.");
            System.out.println("2. Mostrar contenido del fichero.");
            System.out.println("3. Salir.");
            System.out.print("Selecciona una opción (1-3): ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Pedir datos al usuario
                    System.out.print("Introduce tu nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Introduce tu edad: ");
                    int edad = numberInteger(sc, "tu edad");
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Introduce tu ciudad de nacimiento: ");
                    String ciudad = sc.nextLine();

                    // Escribir los datos en el fichero
                    escribirFichero(nombreFichero, nombre, edad, ciudad);
                    System.out.println("Datos agregados al fichero.");
                    break;
                case 2:
                    // Mostrar los datos del fichero
                    System.out.println("Contenido del fichero:");
                    mostrarFichero(nombreFichero);
                    break;
                case 3:
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        sc.close();
    }
    
     public static int numberInteger(Scanner sc, String informacion) {
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
                System.out.println("Recuerde, Introduzca un numero entero");

            }

        }
        return value;
    }
}