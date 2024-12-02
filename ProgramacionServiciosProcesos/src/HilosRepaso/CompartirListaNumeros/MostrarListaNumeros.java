/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso.CompartirListaNumeros;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author josea
 */
public class MostrarListaNumeros implements Runnable {
    
    //Atributos
    private List<Integer> listaNumeros;
    private Lock bloqueo;
    
    //Constructor
    public MostrarListaNumeros(Lock bloqueo, List<Integer> listaNumeros) {
        this.listaNumeros = listaNumeros;
        this.bloqueo = bloqueo;
    }

    //Metodo sobreescrito Run.
    @Override
    public void run() {
        //Utilizamos AtomicInteger para poder utilizarlo en Stream
        AtomicInteger cont = new AtomicInteger(1);

        //Bloqueamos, antes de empezar a ejecutar. Al utilizar Lock, no lanza excepciones, no necesitamos Catch
        bloqueo.lock();

        try {

            listaNumeros.forEach(e -> System.out.print("\n" + cont.getAndIncrement() + "-" + e));

        } finally {
            bloqueo.unlock();
            System.out.println("\n");
        }

    }

}
