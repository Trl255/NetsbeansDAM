/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ValidacionRegulares;
import java.util.Scanner;

/**
 *
 * @author josea
 */

public class ValidacionesRegulares {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese un número entero (positivo o negativo): ");
        String entero = scanner.nextLine();
        System.out.println(validarEntero(entero.trim()) ? "Válido" : "Inválido");
        
        System.out.print("Ingrese un DNI (8 números y una letra mayúscula): ");
        String dni = scanner.nextLine();
        System.out.println(validarDNI(dni.trim().toUpperCase()) ? "Válido" : "Inválido");
        
        System.out.print("Ingrese una matrícula de coche (formato 0000XXX): ");
        String matricula = scanner.nextLine();
        System.out.println(validarMatricula(matricula.trim().toUpperCase()) ? "Válida" : "Inválida");
        
        System.out.print("Ingrese un número binario: ");
        String binario = scanner.nextLine();
        System.out.println(validarBinario(binario.trim()) ? "Válido" : "Inválido");
        
        System.out.print("Ingrese un número octal: ");
        String octal = scanner.nextLine();
        System.out.println(validarOctal(octal.trim()) ? "Válido" : "Inválido");
        
        System.out.print("Ingrese un número hexadecimal: ");
        String hexadecimal = scanner.nextLine();
        System.out.println(validarHexadecimal(hexadecimal.trim()) ? "Válido" : "Inválido");
        
        System.out.print("Ingrese un número real (con decimales separados por coma, puede ser negativo): ");
        String real = scanner.nextLine();
        System.out.println(validarReal(real.trim()) ? "Válido" : "Inválido");
        
        System.out.print("Ingrese un nombre (incluye nombres compuestos): ");
        String nombre = scanner.nextLine();
        System.out.println(validarNombre(nombre) ? "Válido" : "Inválido");

        scanner.close();
    }
    
    private static boolean validarEntero(String numero) {
        return numero.matches("-?\\d+");
    }

    private static boolean validarDNI(String dni) {
        return dni.matches("\\d{8}[A-Z]"); // Solo mayúsculas para la letra final
    }

    private static boolean validarMatricula(String matricula) {
        return matricula.matches("\\d{4}[A-Z]{3}"); // Solo mayúsculas para las letras
    }

    private static boolean validarBinario(String binario) {
        return binario.matches("[01]+");
    }

    private static boolean validarOctal(String octal) {
        return octal.matches("[0-7]+");
    }

    private static boolean validarHexadecimal(String hexadecimal) {
        return hexadecimal.matches("[0-9A-Fa-f]+");
    }

    private static boolean validarReal(String real) {
        return real.matches("-?\\d+(,\\d+)?");
    }

    private static boolean validarNombre(String nombre) {
        return nombre.matches("([A-Z][a-z]+)(\\s[A-Z][a-z]+)?");
    }
}