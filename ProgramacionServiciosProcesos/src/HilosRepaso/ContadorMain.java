/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso;

/**
 *
 * @author josea
 */
public class ContadorMain {

    public static void main(String[] args) {

        Thread hiloImp = (new Thread(new Contador1()));
        Thread hiloPar = (new Thread(new Contador2()));

        hiloImp.start();
        hiloPar.start();

        try {
            hiloImp.join();
            hiloPar.join();
        } catch (InterruptedException e) {
            System.out.println("interrupcion "+e.getMessage());
        }
        
        System.out.println("Hemos terminado");
        
    }

}
