/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosReentrantLock.Repaso.Cajero;

/**
 *
 * @author josea
 */
public class MainCuenta {

    public static void main(String[] args) {

        Cajero cajeroCompartido = new Cajero(); // Cajero único para todos

        Personas p1 = new Personas(cajeroCompartido, "Patricia");
        Personas p2 = new Personas(cajeroCompartido, "Maria");
        Personas p3 = new Personas(cajeroCompartido, "Isabella");
        Personas p4 = new Personas(cajeroCompartido, "Fernanda");
        Personas p5 = new Personas(cajeroCompartido, "Pepe");

        Thread hiloP1 = new Thread(p1);
        Thread hiloP2 = new Thread(p2);
        Thread hiloP3 = new Thread(p3); 
        Thread hiloP4 = new Thread(p4);
        Thread hiloP5 = new Thread(p5);
        hiloP1.start();

        hiloP2.start();
        hiloP3.start();
        hiloP4.start();
        hiloP5.start();

    }

}
