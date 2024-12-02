/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class buscarLetraEn5Palabras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fileName = "palabras.txt";

        // Paso 1: Escribir 5 palabras en un archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Introduce 5 palabras:");
            for (int i = 1; i <= 5; i++) {
                System.out.print("Palabra " + i + ": ");
                String palabra = sc.nextLine();
                bw.write(palabra);
                bw.newLine();
            }
            System.out.println("Las palabras se han guardado en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return; // Salimos si hay error al escribir
        }

        // Paso 2: Pedir una letra al usuario
        System.out.print("\nIntroduce una letra para buscar en el archivo: ");
        char letra = sc.nextLine().charAt(0);

        // Paso 3: Leer el archivo y contar las apariciones de la letra
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    if (c == letra) {
                        contador++;
                    }
                }
            }
            System.out.println("\nLa letra '" + letra + "' aparece " + contador + " veces en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
