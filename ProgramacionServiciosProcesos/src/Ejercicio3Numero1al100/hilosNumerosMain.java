/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio3Numero1al100;

/**
 *
 * @author josea
 */
public class hilosNumerosMain {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hilo principal iniciando."); // Mensaje de inicio del hilo principal.       

        // Crea una instancia de MiHilo con el nombre "#1".
        HiloNumeroClass hilo1 = new HiloNumeroClass("1");
        // Crea un nuevo hilo a partir del objeto MiHilo creado.
        Thread tNumero1 = new Thread(hilo1);

        tNumero1.start();

        

    }
}
