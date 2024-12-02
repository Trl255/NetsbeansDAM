/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class Metodos {

    static Scanner sc = new Scanner(System.in);

    public static String solicitudInformacion(Scanner sc, String informacion, int longitud) {
        boolean validacion = false;
        String respuesta = "";
        System.out.println("Intruduzca " + informacion);
        respuesta = sc.nextLine();
        while (!(validacion)) {
            try {

                if (respuesta.trim().isEmpty() || respuesta.length() >= longitud) {
                    validacion = true;
                } else {
                    System.out.println("Información incorrecta, por favor introuzca " + informacion + " con " + longitud + " como minimo");
                    respuesta = sc.nextLine();
                }

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(e.getMessage());

            }

        }
        return respuesta;
    }

    public static int numberInteger(Scanner sc, String informacion) {
        boolean numberOk = false;
        int value = 0;

        while (!(numberOk)) {
            try {
                System.out.println("Intruduzca " + informacion);
                value = sc.nextInt();
                sc.nextLine();

                numberOk = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Recuerde, Introduzca un numero entero");

            }

        }
        return value;
    }

}
