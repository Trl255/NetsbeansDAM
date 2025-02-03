/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CajeroCorto;

/**
 *
 * @author josea
 */
public class MainCajero {

    public static void main(String[] args) {
        Thread usr1 = new Thread(new Cajero("Maria", 200));
        Thread usr2 = new Thread(new Cajero("Maria", 2000));
        Thread usr3 = new Thread(new Cajero("Maria", 1200));

        usr1.start();
        usr2.start();
        usr3.start();

        try {
            usr1.join();
            usr2.join();
            usr3.join();
        } catch (InterruptedException e) {
            System.out.println("Se ha interrumpido el proceso " + e.getMessage());
        }
        
        System.out.println("Hemos finalizado de realizar todas las operaciones.");

    }

}
