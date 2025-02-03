/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package HilosCiclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author josea
 */
public class CarreraAtletismoLambda {
    public static void main(String[] args) {
        int numeroCorredores = 5;
        int vueltas = 2;

        // Crear una barrera cíclica con acción de barrera definida como lambda
        CyclicBarrier barrera = new CyclicBarrier(numeroCorredores, () -> {
            System.out.println("Todos los corredores han terminado la vuelta. Preparandose para la siguiente vuelta...");
        });

        // Crear y arrancar los hilos para cada corredor
        for (int i = 1; i <= numeroCorredores; i++) {
            int idCorredor = i; // Variable final para usar en el hilo
           
            
            new Thread(() -> {
                for (int vuelta = 1; vuelta <= vueltas; vuelta++) {
                    try {
                        System.out.println("Corredor " + idCorredor + " comienza la vuelta " + vuelta + ".");
                        // Simula el tiempo de la vuelta
                        Thread.sleep((long) (Math.random() * 3000));
                        System.out.println("Corredor " + idCorredor + " finaliza la vuelta " + vuelta + ".");
                        // Esperar a los demás corredores
                        barrera.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}