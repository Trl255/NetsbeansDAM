/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosSincronizador;

/**
 *
 * @author josea
 */
public class SincronizadorHilos implements Runnable {

    private int contador;

    public SincronizadorHilos() {
        this.contador = contador;
    }

    @Override
    public synchronized void run() {
        System.out.println("Ahora el contador es: " + contador);
        contador = sumContador();
        System.out.println("Hemos incrementado: " + contador);

    }

    public static int sumContador() {
        return 10;
    }

}
