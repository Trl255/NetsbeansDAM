package ProcesosPadreHijo;  // Declaración del paquete 'ejercicios'.

import java.util.Scanner;  // Importación de la clase Scanner para leer la entrada del usuario.

public class Ej47_1 {  // Definición de la clase Ej47_1.

    public static void main(String[] args) {  // Método principal, punto de entrada de la aplicación.
        Scanner sc = new Scanner(System.in);  // Crea un objeto Scanner para leer la entrada del usuario.
        System.out.println("Escribe tu nombre");  // Solicita al usuario que ingrese su nombre.
        String nombre = sc.nextLine();  // Lee la línea de entrada del usuario.
        System.out.println("Tu nombre es " + nombre);  // Imprime el nombre que el usuario ingresó.
    }
}
