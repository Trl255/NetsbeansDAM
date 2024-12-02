
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author josea
 */
public class leerFicheroTextoTxt {

    public static void main(String[] args) {

        String fileName = "texto.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            // Si el archivo no existe, lo creamos y escribimos un poema
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                String poema = "Con las hojas moradas,\nde color amarillo,\nestaba el arbolito";
                bw.write(poema);
                System.out.println("El archivo no existía. Se ha creado y escrito el poema.");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
                return; // Si ocurre un error al escribir, terminamos el programa
            }
        }
        // Leer el archivo carácter por carácter
        try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {

            int c; // Variable para almacenar el carácter leído
            while ((c = br.read()) != -1) { // Leer carácter por carácter hasta el final del archivo
                char caracter = (char) c;
                // Comprobar si el carácter no es un espacio
                if (caracter != ' ') {
                    System.out.print(caracter); // Imprimir el carácter sin espacios
                }
            }
            System.out.println(); // Para saltar de línea después de mostrar todo el contenido

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
