
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Varios_Criptograficos_FicherosPropiedades_Aleatorio;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 *
 * @author josea
 */
public class AlgoritmosCriptograficos {

    public static void main(String[] args) {
        try {
            // Generación de una clave AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // Tamaño de clave (128, 192 o 256 bits)
            SecretKey secretKey = keyGen.generateKey();

            String mensajeOriginal = "Mensaje secreto";
            System.out.println("Mensaje Original: " + mensajeOriginal);

            // Cifrado del mensaje
            String mensajeCifrado = cifrar(mensajeOriginal, secretKey);
            System.out.println("Mensaje Cifrado: " + mensajeCifrado);

            // Descifrado del mensaje
            String mensajeDescifrado = descifrar(mensajeCifrado, secretKey);
            System.out.println("Mensaje Descifrado: " + mensajeDescifrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String cifrar(String mensaje, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(mensajeCifrado); // Codificación a Base64
    }

    public static String descifrar(String mensajeCifrado, SecretKey clave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        byte[] mensajeDescifrado = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
        return new String(mensajeDescifrado);
    }
}
