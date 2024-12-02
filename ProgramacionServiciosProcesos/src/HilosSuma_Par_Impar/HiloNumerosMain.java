/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosSuma_Par_Impar;

/**
 *
 * @author josea
 */
public class HiloNumerosMain {

    public static void main(String[] args) throws InterruptedException {
        Thread miThreadImpar = generarHiloImpar();
        Thread miThreadPar = generarHiloPar();

        miThreadPar.start();
        miThreadImpar.start();

        System.out.println("Hilo principal finalizado."); // Mensaje al finalizar el hilo principal.
    }

    public static Thread generarHiloImpar() {
        // Crea una instancia de MiHilo con el nombre "#1".
        HiloImpar mh = new HiloImpar();
        // Crea un nuevo hilo a partir del objeto MiHilo creado.
        Thread nuevoh = new Thread(mh);
        // Inicia la ejecución del hilo.

        return nuevoh;
    }

    public static Thread generarHiloPar() {
        // Crea una instancia de MiHilo con el nombre "#1".
        HiloPar mh = new HiloPar();
        // Crea un nuevo hilo a partir del objeto MiHilo creado.
        Thread nuevoh = new Thread(mh);
        // Inicia la ejecución del hilo.

        return nuevoh;
    }
}
