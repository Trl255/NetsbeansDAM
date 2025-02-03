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
public class JuegoCartasLambda {
    public static void main(String[] args) {
        int numeroJugadores = 5;
        int rondas = 3;

        // Crear una barrera cíclica con acción definida como lambda
        CyclicBarrier barrera = new CyclicBarrier(numeroJugadores, () -> {
            System.out.println("Todos los jugadores han completado la ronda. Preparando la siguiente...");
        });

        // Crear y arrancar los hilos para cada jugador
        for (int i = 1; i <= numeroJugadores; i++) {
            int idJugador = i; // Variable final para usar en el hilo
            new Thread(() -> {
                for (int ronda = 1; ronda <= rondas; ronda++) {
                    try {
                        System.out.println("Jugador " + idJugador + " está jugando en la ronda " + ronda + ".");
                        // Simula la acción del jugador con un tiempo de espera aleatorio
                        Thread.sleep((long) (Math.random() * 3000));
                        System.out.println("Jugador " + idJugador + " ha terminado la ronda " + ronda + ".");
                        // Esperar a los demás jugadores
                        barrera.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}