/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package HiloHorarioTrabajador;

/**
 *
 * @author josea
 */
public class LlegadaTrabador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new VerificarPuntualidad("Persona1"));
        Thread hilo2 = new Thread(new VerificarPuntualidad("Persona2"));

        hilo1.start();
        hilo2.start();

    }

}
