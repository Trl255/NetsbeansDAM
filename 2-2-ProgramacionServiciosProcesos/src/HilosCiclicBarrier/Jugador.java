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
public class Jugador implements Runnable {
    private final int idJugador;
    private final int rondas;
    private final CyclicBarrier barrera;

    public Jugador(int idJugador, int rondas, CyclicBarrier barrera) {
        this.idJugador = idJugador;
        this.rondas = rondas;
        this.barrera = barrera;
    }

    @Override
    public void run() {
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
    }
}