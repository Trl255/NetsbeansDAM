/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos_Semaforos;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author josea
 */
public class PasoDeCebra {

    private Semaphore semaforoIzquierda = new Semaphore(1);
    private Semaphore semaforoDerecha = new Semaphore(1);
    private int tiempoCruce = 0;

    public void cruzarIquierdaDerecha(String nombre) {

        try {
            semaforoIzquierda.acquire();
            System.out.println(nombre + " esta cruzando de izquierda a derecha");
            tiempoCruce = new Random().nextInt(2000) + 1000;
            Thread.sleep(tiempoCruce);

            System.out.println(nombre + " ha cruzado de izquierda a derecha.");
            semaforoIzquierda.release();

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    public void cruzarDerechaIzquierda(String nombre) {

        try {
            semaforoDerecha.acquire();

            System.out.println(nombre + " esta cruzando de derecha a izquierda---");
            tiempoCruce = new Random().nextInt(3000) + 1000;
            Thread.sleep(tiempoCruce);
            int tiempoCruce = 0;
            System.out.println(nombre + " ha cruzado de derecha a izquierda----");

            semaforoDerecha.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
