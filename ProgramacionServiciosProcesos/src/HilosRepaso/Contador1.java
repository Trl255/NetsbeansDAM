/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso;

/**
 *
 * @author josea
 */
public class Contador1 implements Runnable {

    public Contador1() {
    }

    @Override
    public void run() {

        for (int j = 20; j >= 0; j--) {
            try {

                synchronized (System.out) {

                    System.out.println("Restamos uno :" + j);
                }

                Thread.sleep(210);

            } catch (InterruptedException e) {
                System.out.println("Error " + e.getMessage());
            }

        }

    }

}
