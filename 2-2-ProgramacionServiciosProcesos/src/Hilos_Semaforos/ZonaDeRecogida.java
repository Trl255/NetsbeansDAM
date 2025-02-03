/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos_Semaforos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author josea
 */
public class ZonaDeRecogida {

    private Semaphore semaforoCamarero = new Semaphore(0); // Inicialmente no hay platos
    private Semaphore semaforoCocinero = new Semaphore(1); // Solo un cocinero puede colocar platos

    public void tomarPlato(String camarero) {
        try {
            semaforoCamarero.acquire();  // Espera hasta que haya un plato disponible
            System.out.println(camarero + " ha tomado un plato de la zona de recogida.");
            Thread.sleep(500);  // Simula el servir el plato
            System.out.println(camarero + " ha servido un plato.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void colocarPlato(String cocinero) {

        try {
            semaforoCocinero.acquire();  // Espera hasta que la zona de recogida tenga espacio
            System.out.println(cocinero + " ha colocado un plato en la zona de recogida.");
            semaforoCamarero.release();  // Señaliza que hay un plato disponible
            Thread.sleep(500);  // Simula el tiempo de preparación
            semaforoCocinero.release();  // Libera para que otro cocinero pueda colocar un plato
        } catch (InterruptedException e) {
        }

    }

}
