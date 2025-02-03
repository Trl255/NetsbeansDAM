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
public class MainJuegoCartasRunnable {
   public static void main(String[] args) {
        int numeroJugadores = 5;
        int rondas = 3;

        // Crear una acción de barrera usando una clase Runnable independiente
        Runnable accionBarrera = new AccionJuegoCartas();
        CyclicBarrier barrera = new CyclicBarrier(numeroJugadores, accionBarrera);

        // Crear y arrancar los hilos para cada jugador
        for (int i = 1; i <= numeroJugadores; i++) {
            new Thread(new Jugador(i, rondas, barrera)).start();
        }
    }
}

// Clase Runnable para definir la acción que se ejecuta cuando todos llegan a la barrera
class AccionJuegoCartas implements Runnable {
    @Override
    public void run() {
        System.out.println("Todos los jugadores han completado la ronda. Preparando la siguiente...");
    }
}
