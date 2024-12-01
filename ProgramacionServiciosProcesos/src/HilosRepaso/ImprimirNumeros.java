/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso;

/**
 *
 * @author josea
 */
public class ImprimirNumeros implements Runnable {

    @Override
    public synchronized void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println("Numero "+i);
        }
        try {
                
            Thread.sleep(500);
            
        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
        
        
    }


    
    
    
}
