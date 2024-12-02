/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso;

/**
 *
 * @author josea
 */
public class Contador2 implements Runnable {

    public Contador2() {
    }

    @Override
    public void run() {

        for (int i = 0; i <= 20; i++) {

            try {

                Thread.sleep(200);

                synchronized (System.out) {
                    System.out.println("Sumamos uno: " + i);

                }

            } catch (InterruptedException e) {
                System.out.println("Error" + e.getMessage());
            }
        }

    }

}
