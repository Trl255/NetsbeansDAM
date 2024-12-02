/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Varios_Criptograficos_FicherosPropiedades_Aleatorio;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author josea
 */

public class GestionFicheroPropiedades {

    private static final String RUTA_FICHERO = "config.properties";
    private static Properties propiedades = new Properties();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1) Crear fichero de propiedades");
            System.out.println("2) Leer fichero de propiedades");
            System.out.println("3) Buscar valor de una propiedad");
            System.out.println("4) Reemplazar valor de una propiedad");
            System.out.println("5) Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearFichero();
                    break;
                case 2:
                    leerFichero();
                    break;
                case 3:
                    buscarPropiedad();
                    break;
                case 4:
                    reemplazarPropiedad();
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private static void crearFichero() {
        try ( OutputStream salida = new FileOutputStream(RUTA_FICHERO)) {
            System.out.print("Ingrese el nombre de la propiedad: ");
            String clave = scanner.nextLine();
            System.out.print("Ingrese el valor de la propiedad: ");
            String valor = scanner.nextLine();
            propiedades.setProperty(clave, valor);
            propiedades.store(salida, "Archivo de propiedades");
            System.out.println("Fichero de propiedades creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }

    private static void leerFichero() {
        try ( InputStream entrada = new FileInputStream(RUTA_FICHERO)) {
            propiedades.load(entrada);
            propiedades.forEach((clave, valor) -> System.out.println(clave + ": " + valor));
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    private static void buscarPropiedad() {
        System.out.print("Ingrese el nombre de la propiedad a buscar: ");
        String clave = scanner.nextLine();
        String valor = propiedades.getProperty(clave);
        if (valor != null) {
            System.out.println("Valor de '" + clave + "': " + valor);
        } else {
            System.out.println("Propiedad no encontrada.");
        }
    }

    private static void reemplazarPropiedad() {
        try ( OutputStream salida = new FileOutputStream(RUTA_FICHERO)) {
            System.out.print("Ingrese el nombre de la propiedad a reemplazar: ");
            String clave = scanner.nextLine();
            if (propiedades.containsKey(clave)) {
                System.out.print("Ingrese el nuevo valor: ");
                String nuevoValor = scanner.nextLine();
                propiedades.setProperty(clave, nuevoValor);
                propiedades.store(salida, "Archivo de propiedades actualizado");
                System.out.println("Propiedad actualizada exitosamente.");
            } else {
                System.out.println("Propiedad no encontrada.");
            }
        } catch (IOException e) {
            System.out.println("Error al reemplazar la propiedad: " + e.getMessage());
        }
    }
}
