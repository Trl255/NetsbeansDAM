/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosSincronizador;

/**
 *
 * @author josea
 */
public class MainSincronizador {

    public static void main(String[] args) {
        SincronizadorHilos sd1 = new SincronizadorHilos();
        Thread h1 = new Thread(sd1);

        SincronizadorHilos sd2 = new SincronizadorHilos();
        Thread h2 = new Thread(sd2);

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupcion de hilo: " + e.getMessage());
        }

    }

}
/*Ejercicio 5: Sincronización de Contador
Escribe un programa que use un contador compartido entre dos hilos:

Implementa una clase con un atributo contador y un método para incrementarlo.
Lanza dos hilos que incrementen el contador 10 veces cada uno.
Usa synchronized para evitar condiciones de carrera y asegúrate de que el contador alcance el valor esperado.*/
