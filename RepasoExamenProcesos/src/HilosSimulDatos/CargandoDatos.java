/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosSimulDatos;

/**
 *
 * @author josea
 */
public class CargandoDatos implements Runnable {
    
    private String mensaje = "Cargando datos";
    
    public CargandoDatos() {
        
    }
    
    @Override
    public void run() {
        int cont = 0;
        
        try {
            while (cont <= 5) {
                Thread.sleep(1000);
                System.out.println(mensaje);
                cont++;
            }
            
        } catch (InterruptedException e) {
            System.out.println("Interrupcion de hilo "+e.getMessage());
        }
    }
    
}
