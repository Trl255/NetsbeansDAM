/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repasoexamenprocesos;

/**
 *
 * @author josea
 */
public class ContadorMas implements Runnable {

    @Override
    public void run() {

        try {
            for (int i = 10; i > 0; i--) {
                Thread.sleep(1000);

                System.out.println(i == 1 ? "Solo falta uno " +i: "Restando 1: " + i);

            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
