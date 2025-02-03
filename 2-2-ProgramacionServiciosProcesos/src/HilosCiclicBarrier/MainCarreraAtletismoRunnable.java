/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosCiclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author josea
 */
public class MainCarreraAtletismoRunnable {
    public static void main(String[] args) {
        int numeroCorredores = 5;
        int vueltas = 2;

        // Crear una acción de barrera usando una clase Runnable independiente
        Runnable accionBarrera = new AccionBarrera();
        CyclicBarrier barrera = new CyclicBarrier(numeroCorredores, accionBarrera);

        // Crear y arrancar los hilos para cada corredor
        for (int i = 1; i <= numeroCorredores; i++) {
            new Thread(new Corredor(i, vueltas, barrera)).start();
        }
    }
}

// Clase Runnable para la acción de barrera
class AccionBarrera implements Runnable {
    @Override
    public void run() {
        System.out.println("Todos los corredores han terminado la vuelta. Preparándose para la siguiente vuelta...");
    }
}
