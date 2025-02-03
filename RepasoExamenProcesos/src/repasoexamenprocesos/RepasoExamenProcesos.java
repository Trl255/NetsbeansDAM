/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package repasoexamenprocesos;

/**
 *
 * @author josea
 */
public class RepasoExamenProcesos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ContadorMas contM = new ContadorMas();
        ContadorMenos contMn = new ContadorMenos();

        Thread h1 = new Thread(contM);
        Thread h2 = new Thread(contMn);

        h1.start();
        h2.start();
        try {
            h1.join();
            h2.join();
        } catch (Exception e) {
        }

    }

}
/*Ejercicio 1: Contador con Runnable
Crea un programa que inicie dos hilos usando la interfaz Runnable.

El primer hilo debe contar del 1 al 10, imprimiendo un número por segundo.
El segundo hilo debe contar del 10 al 1, imprimiendo un número cada dos segundos.
Ambos hilos deben ejecutarse de manera concurrente.
 */
