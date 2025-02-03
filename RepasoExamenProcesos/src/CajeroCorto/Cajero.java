/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CajeroCorto;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author josea
 */
public class Cajero implements Runnable {

   private static double saldo = 1000; // Recurso compartido
    private static ReentrantLock bloqueo = new ReentrantLock(); // Bloqueo compartido

    private String nombre;
    private double cantRetirar;

    public Cajero(String nombre, double cantRetirar) {
        this.nombre = nombre;
        this.cantRetirar = cantRetirar;
    }

    @Override
    public void run() {
        bloqueo.lock(); // Bloquea el acceso al recurso compartido
        try {
            System.out.println(nombre + " tiene un saldo inicial de: " + saldo);
            if (saldo >= cantRetirar) {
                saldo -= cantRetirar;
                System.out.println(nombre + " ha retirado " + cantRetirar + ". Saldo actual: " + saldo);
            } else {
                System.out.println(nombre + " no tiene suficiente saldo para retirar " + cantRetirar);
            }
            Thread.sleep(500); // Simula tiempo de operación
        } catch (InterruptedException e) {
            System.out.println("Operación interrumpida: " + e.getMessage());
        } finally {
            bloqueo.unlock(); // Libera el bloqueo
        }
    }
}