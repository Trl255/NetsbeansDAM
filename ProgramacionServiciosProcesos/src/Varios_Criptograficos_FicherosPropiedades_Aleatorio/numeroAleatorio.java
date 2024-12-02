/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Varios_Criptograficos_FicherosPropiedades_Aleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class numeroAleatorio {

    private static final String NOMBRE_FICHERO = "numeros.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1) Añadir número al principio del fichero");
            System.out.println("2) Añadir número al final del fichero");
            System.out.println("3) Mostrar el fichero");
            System.out.println("4) Sustituir un número en el fichero");
            System.out.println("5) Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    añadirAlPrincipio();
                    break;
                case 2:
                    añadirAlFinal();
                    break;
                case 3:
                    mostrarFichero();
                    break;
                case 4:
                    sustituirNumero();
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private static void añadirAlPrincipio() {
        System.out.print("Ingrese el número a añadir al principio: ");
        int numero = scanner.nextInt();
        try ( RandomAccessFile fichero = new RandomAccessFile(NOMBRE_FICHERO, "rw")) {
            byte[] contenidoExistente = new byte[(int) fichero.length()];
            fichero.read(contenidoExistente); // Lee contenido actual
            fichero.seek(0); // Volver al inicio
            fichero.writeInt(numero); // Escribe nuevo número
            fichero.write(contenidoExistente); // Escribe el contenido antiguo
            System.out.println("Número añadido al principio exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al añadir número al principio: " + e.getMessage());
        }
    }

    private static void añadirAlFinal() {
        System.out.print("Ingrese el número a añadir al final: ");
        int numero = scanner.nextInt();
        try ( RandomAccessFile fichero = new RandomAccessFile(NOMBRE_FICHERO, "rw")) {
            fichero.seek(fichero.length()); // Mover al final del archivo
            fichero.writeInt(numero); // Añadir nuevo número
            System.out.println("Número añadido al final exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al añadir número al final: " + e.getMessage());
        }
    }

    private static void mostrarFichero() {
        try ( RandomAccessFile fichero = new RandomAccessFile(NOMBRE_FICHERO, "r")) {
            System.out.println("Contenido del fichero:");
            while (fichero.getFilePointer() < fichero.length()) {
                System.out.print(fichero.readInt() + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al mostrar el fichero: " + e.getMessage());
        }
    }

    private static void sustituirNumero() {
        System.out.print("Ingrese el número a sustituir: ");
        int numeroAntiguo = scanner.nextInt();
        System.out.print("Ingrese el nuevo número: ");
        int numeroNuevo = scanner.nextInt();
        try ( RandomAccessFile fichero = new RandomAccessFile(NOMBRE_FICHERO, "rw")) {
            while (fichero.getFilePointer() < fichero.length()) {
                int numeroActual = fichero.readInt();
                if (numeroActual == numeroAntiguo) {
                    fichero.seek(fichero.getFilePointer() - 4); // Retrocede 4 bytes
                    fichero.writeInt(numeroNuevo); // Reemplaza con el nuevo número
                }
            }
            System.out.println("Sustitución completada.");
        } catch (IOException e) {
            System.out.println("Error al sustituir número en el fichero: " + e.getMessage());
        }
    }
}
