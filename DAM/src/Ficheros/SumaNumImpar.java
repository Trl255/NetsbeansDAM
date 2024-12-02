/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class SumaNumImpar {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Declaramos el Scanner
        Scanner sc = new Scanner(System.in);
        int num;
        int suma = 0;
        String fileName = "sumaNumeros.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            for (int i = 0; i < 10; i++) {
                sc.useDelimiter("\n");
                sc.useLocale(Locale.US);
                num = Metodos.Metodos.numberInteger(sc, "el numero " + (i + 1));

                // Sumamos solo si es impar
                if (num % 2 != 0) {
                    suma += num;

                }

                // Escribimos el número en el archivo
              // Convertir el número a cadena antes de escribirlo
                bw.write(String.valueOf(num));
                bw.newLine(); // Escribir una nueva línea
                
            }

            // Escribimos la suma al final
            bw.write("Suma de números impares: " + suma);
            bw.newLine();

            System.out.println("Se han añadido correctamente los números al archivo.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
        System.out.println("\nLeyendo la información desde el archivo:");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

    }
}
