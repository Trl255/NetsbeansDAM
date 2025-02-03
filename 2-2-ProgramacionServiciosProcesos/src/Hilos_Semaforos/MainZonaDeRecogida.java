/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos_Semaforos;

/**
 *
 * @author josea
 */
public class MainZonaDeRecogida {

    public static void main(String[] args) {
        ZonaDeRecogida zonaDeRecogida = new ZonaDeRecogida();

        Thread[] cocineros = new Thread[2];
        Thread[] camareros = new Thread[2];

        // Crear los hilos de los cocineros
        for (int i = 0; i < cocineros.length; i++) {
            int num = i + 1;
            cocineros[i] = new Thread(() -> zonaDeRecogida.colocarPlato("Cocinero" + num));
        }

        // Crear los hilos de los camareros
        for (int i = 0; i < camareros.length; i++) {
            int num = i + 1;
            camareros[i] = new Thread(() -> zonaDeRecogida.tomarPlato("Camarero" + num));
        }

        for (Thread cocinero : cocineros) {
            cocinero.start();
        }

        for (Thread camarero : camareros) {
            camarero.start();
        }
        try {
            for (Thread cocinero : cocineros) {
                cocinero.join();
            }
            for (Thread camarero : camareros) {
                camarero.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El proceso de servir y preparar platos ha terminado.");

    }
}
