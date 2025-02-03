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
class Corredor implements Runnable {
    private final int idCorredor;
    private final int vueltas;
    private final CyclicBarrier barrera;

    public Corredor(int idCorredor, int vueltas, CyclicBarrier barrera) {
        this.idCorredor = idCorredor;
        this.vueltas = vueltas;
        this.barrera = barrera;
    }

    @Override
    public void run() {
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
    }
}