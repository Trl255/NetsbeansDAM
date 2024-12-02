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
public class SumaListaNumeros implements Runnable {

    private List<Integer> listaNumeros;
    private Lock bloqueo;

    public SumaListaNumeros(Lock bloqueo, List<Integer> listaNumeros) {
        this.listaNumeros = listaNumeros;
        this.bloqueo = bloqueo;
    }

    @Override
    public void run() {
        //Utilizamos AtomicInteger para poder utilizarlo en Stream
        AtomicInteger suma = new AtomicInteger(0);
        //Bloqueamos, antes de empezar a ejecutar. Al utilizar Lock, no lanza excepciones, no necesitamos Catch
        bloqueo.lock();

        try {
            //una forma de sumar con Stream, suma es un AtomicInteger y tiene una funcion de obtener e ir sumando, retorna un entero.
            listaNumeros.forEach(e -> suma.addAndGet(e));

            System.out.println("La suma de los número son: " + suma.get());
        } finally {
            //Desbloqueamos
            bloqueo.unlock();
            System.out.println("\n");
        }

    }

}
