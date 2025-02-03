/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepartoRegalos;

/**
 *
 * @author josea
 */
public class MainReparto {

    public static void main(String[] args) {
        Thread repartidor1 = new Thread(new Repartidor("Jose Miguel", new String[]{"TV", "Bombilla", "Movil"}));
        Thread repartidor2 = new Thread(new Repartidor("Mari Piedad", new String[]{"Mesa", "Sofa", "Cama"}));
        Thread repartidor3 = new Thread(new Repartidor("Carlos Pomares", new String[]{"Silla", "Escritorio", "Barra de sonido"}));
        Thread repartidor4= new Thread(new Repartidor("Alejandra Quintanilla", new String[]{"Lampara","Chandal"}));

        repartidor1.start();
        repartidor2.start();
        repartidor3.start();
        repartidor4.start();
        
        try {
            repartidor1.join();
            repartidor2.join();
            repartidor3.join();
            repartidor4.join();
        } catch (InterruptedException e) {
            System.out.println("Error en la ejecución de hilo "+e.getMessage());            
        }
        System.out.println("Hemos terminado de repartir todos los regalos.");
    }

}
