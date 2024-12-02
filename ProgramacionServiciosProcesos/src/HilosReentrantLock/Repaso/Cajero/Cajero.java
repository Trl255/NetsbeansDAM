/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosReentrantLock.Repaso.Cajero;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author josea
 */
public class Cajero {

    private Lock bloqueo = new ReentrantLock();

    public Cajero() {

    }

    public void ingresarDinero(Personas persona, double cantIngresar) {
        bloqueo.lock();
        int tiempoBloqueo = generarNumeroAleatorio(100, 340);

        try {
            Thread.sleep(tiempoBloqueo);
            double saldoActual = persona.getSaldoActual();
            System.out.println(persona.getNombre() + " tiene un saldo inicial de: €" + persona.getSaldoActual());

            System.out.println(persona.getNombre() + " se encuentra ingresando €" + cantIngresar + " el cajero. Y tiene un saldo actual es de €" + saldoActual);

            persona.setSaldoActual(saldoActual += cantIngresar);

            System.out.println(persona.getNombre() + " ha ingresado €" + cantIngresar + " a su cuenta. Y tiene un saldoa actual es de €" + persona.getSaldoActual());

        } catch (InterruptedException e) {
            System.out.println("El proceso ha sido interrumpido " + e.getMessage());
        } finally {
            bloqueo.unlock();
            System.out.println("\n");
        }

    }

    public void sacarDinero(Personas persona, double cantidad) {

        bloqueo.lock();
        int tiempoBloqueo = generarNumeroAleatorio(1000, 3400);

        try {

            Thread.sleep(tiempoBloqueo);
            double saldoActual = persona.getSaldoActual();
            System.out.println(persona.getNombre() + " tiene un saldo inicial de: €" + persona.getSaldoActual());
            if (cantidad <= saldoActual) {

                System.out.println(persona.getNombre() + " esta sacando €" + cantidad);
                persona.setSaldoActual(saldoActual -= cantidad);

            } else {
                System.out.println("Ha intentado sacar €" + cantidad + ", pero sólo tienen en su cuenta :" + persona.getSaldoActual());
            }

            System.out.println(persona.getNombre() + " ha terminado de utilizar el cajero, tiene un saldoActual de: €" + persona.getSaldoActual());

        } catch (InterruptedException e) {
            System.out.println("El proceso ha sido interrumpido " + e.getMessage());
        } finally {
            bloqueo.unlock();
            System.out.println("\n");
        }

    }

    public int generarNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }
}
