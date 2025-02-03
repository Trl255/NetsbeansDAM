/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosCarrera;

/**
 *
 * @author josea
 */
public class HilosCarrera {

    public static void main(String[] args) {
        Thread h1 = new Thread(new Carrera(1, "hiloNum"));
        Thread h2 = new Thread(new Carrera(1, "hiloCarac"));

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrumpido "+ e.getMessage());
        }

        System.out.println("Hemos finalizado.");
    }

}
/*Ejercicio 6: Carrera de Hilos
Simula una carrera entre tres hilos:

Cada hilo debe imprimir "Hilo X avanzó al paso Y" hasta completar 10 pasos.
Usa un retraso aleatorio entre pasos (Thread.sleep) para simular la velocidad de cada hilo.
Indica qué hilo terminó primero.*/
