/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CodificacionXOR;

/**
 *
 * @author josea
 */
public class XOR {

    public static void main(String[] args) {
        String texto = "HELLO WORLD";  // Texto original
        String clave = "PASSWORDDDD";   // Contraseña
        StringBuilder resultado = new StringBuilder();

        System.out.println("Texto original: " + texto);
        System.out.println("Contraseña: " + clave);

        // Recorrer cada letra del texto
        for (int i = 0; i < texto.length(); i++) {
            char letraTexto = texto.charAt(i);
            // Si es un espacio, añadir espacio al resultado

            if (letraTexto == ' ') {
                resultado.append('O');
                continue;
            }

            // Obtener el valor de la letra en el texto (0-25)
            int valorTexto = letraTexto - 'A';  // Posición en el alfabeto

            // Obtener el carácter de clave correspondiente, ajustando el índice
            char letraClave = clave.charAt(i % clave.length());
            
            int valorClave = letraClave - 'A';  // Posición en el alfabeto

            // Sumar el valor de texto y clave
            int valorCifrado = valorTexto + valorClave;

            valorCifrado++;
            // Si la suma es mayor o igual a 26, ajustar restando 26
            if (valorCifrado >= 26) {
                valorCifrado -= 26; // Comenzar desde 0 después de 25
            }

            // Convertir a letra cifrada
            char letraCifrada = (char) +(valorCifrado + 'A');  // Convertir de nuevo a carácter
            resultado.append(letraCifrada);  // Añadir al resultado
        }

        System.out.println("Texto cifrado: " + resultado.toString());
    }
}
