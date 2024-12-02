/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CifradosAES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 *
 * @author josea
 */
public class cifradoAES {
// Método para generar una clave AES de 128 bits

    private static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Configura el tamaño de la clave a 128 bits
        return keyGen.generateKey();
    }

    // Método para cifrar el mensaje
    public static String encrypt(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES"); // Usa el algoritmo AES
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Configura el modo de cifrado
        byte[] encryptedBytes = cipher.doFinal(message.getBytes()); // Cifra el mensaje
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convierte a Base64 para facilitar la lectura
    }

    // Método para descifrar el mensaje
    public static String decrypt(String encryptedMessage, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES"); // Usa el algoritmo AES
        cipher.init(Cipher.DECRYPT_MODE, secretKey); // Configura el modo de descifrado
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedMessage); // Convierte desde Base64
        byte[] decryptedBytes = cipher.doFinal(decodedBytes); // Descifra el mensaje
        return new String(decryptedBytes); // Devuelve el mensaje original
    }

    public static void main(String[] args) {
        try {
            // Genera la clave secreta
            SecretKey secretKey = generateKey();

            // Mensaje a cifrar
            String message = "Mensaje secreto";

            // Cifra el mensaje
            String encryptedMessage = encrypt(message, secretKey);
            System.out.println("Mensaje cifrado: " + encryptedMessage);

            // Descifra el mensaje
            String decryptedMessage = decrypt(encryptedMessage, secretKey);
            System.out.println("Mensaje descifrado: " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
