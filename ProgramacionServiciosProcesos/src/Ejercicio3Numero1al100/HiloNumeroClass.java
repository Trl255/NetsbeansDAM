/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3Numero1al100;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josea
 */
public class HiloNumeroClass implements Runnable {

    String hiloNumeros;// Nombre del hilo, que se especifica al crear una instancia de MiHilo.

// Constructor que inicializa el nombre del hilo.
    public HiloNumeroClass(String nombreHilo) {
        this.hiloNumeros = hiloNumeros;
    }

    // Método run() que actúa como el punto de entrada del hilo.
    // Aquí se define el trabajo que realizará el hilo al ser iniciado.
    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {

            if (i < 51) {
                System.out.println("Número: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloNumeroClass.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                if (i == 51) {
                    System.out.println("Entramos a partir de " + i);

                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloNumeroClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Número: " + i);
            }

        }

    }

}
