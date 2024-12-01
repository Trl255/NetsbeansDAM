/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso;

/**
 *
 * @author josea
 */
public class ImprimirLetras implements Runnable {

    @Override
    public void run() {
        for (char c = 'A'; c < 'J'; c++) {
            System.out.println(c);

        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

}
