/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos_Semaforos;

/**
 *
 * @author josea
 */
public class MainPasoDeCebra {

    public static void main(String[] args) {

        PasoDeCebra pasoDeCebra = new PasoDeCebra();

        Thread[] peatones = new Thread[4];

        for (int i = 0; i < peatones.length; i++) {
            int id = i + 1;

            peatones[i] = new Thread(() -> {

                if (id % 2 == 0) {
                    pasoDeCebra.cruzarDerechaIzquierda("Peaton" + id);
                } else {
                    pasoDeCebra.cruzarIquierdaDerecha("Peaton" + id);
                }

            });

        }

        for (Thread peaton : peatones) {
            peaton.start();
        }

        for (Thread peaton : peatones) {
            try {
                peaton.join();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }

        System.out.println("Han Cruzado todo los peatones.");
    
    }

}
