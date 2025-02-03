/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExchanger;

import static java.lang.Thread.sleep;

/**
 *
 * @author josea
 */
public class Numeros {
   public int a;
    private String hNombre;

    
    public void hNombre(String hNombre) {
        this.hNombre = hNombre;
    }

    public void run() {
        for (a = 1; a < 11; a++) {
            System.out.println(a + " ");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    public synchronized void impares() {
        for (a = 1; a < 10; a++) {
            System.out.println(hNombre + " " + a);
            a++;
        }
        System.out.println(hNombre + " terminó de imprimir impares.");
    }

   
}
