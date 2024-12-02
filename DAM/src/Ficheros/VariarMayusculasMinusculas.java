/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class VariarMayusculasMinusculas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Paso 1: Pedir el nombre del fichero y el texto que se quiere escribir
        System.out.print("Introduce el nombre del archivo: ");
        String fileName = sc.nextLine();

        System.out.print("Introduce el texto a escribir en el archivo: ");
        String texto = sc.nextLine();

        // Paso 2: Crear y escribir en el archivo variando entre mayúsculas y minúsculas
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            String resultado = convertirTexto(texto);
            bw.write(resultado);
            System.out.println("El texto ha sido escrito correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para convertir el texto variando entre mayúsculas y minúsculas
    public static String convertirTexto(String texto) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // Si es una letra, alterna entre mayúscula y minúscula
            if (Character.isLetter(c)) {
                if (i % 2 == 0) {
                    sb.append(Character.toLowerCase(c)); // Minuscula en posiciones pares
                } else {
                    sb.append(Character.toUpperCase(c)); // Mayuscula en posiciones impares
                }
            } else {
                // Si no es una letra, se mantiene igual
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
