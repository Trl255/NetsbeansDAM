/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso.CompartirListaNumeros;

import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author josea
 */
public class GenerarNumeros implements Runnable {

    //Atributos
    private Lock bloqueo;
    private List<Integer> listaNumeros;

    //Constructor
    public GenerarNumeros(Lock bloqueo, List<Integer> listaNumeros) {
        this.bloqueo = bloqueo;
        this.listaNumeros = listaNumeros;
    }

    @Override
    public void run() {
        //Bloqueamos, antes de empezar a ejecutar. Al utilizar Lock, no lanza excepciones, no necesitamos Catch
        bloqueo.lock();

        try {
            while (listaNumeros.size() < 10) {
                listaNumeros.add(generarNumeroAleatorio(1, 100));

            }
            //listaNumeros.forEach(e -> System.out.println(e));
        } finally {
            //Desbloqueamos
            bloqueo.unlock();
            System.out.println("\n");
        }

    }

    public static int generarNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

}
/*Ejercicio Intermedio 2: "Compartir una Lista de Números"
Crea un programa con tres hilos:

Uno genera 10 números aleatorios entre 1 y 100 y los guarda en una lista.
El segundo hilo imprime los números de la lista.
El tercer hilo calcula la suma de los números en la lista.
Utiliza synchronized para evitar conflictos al acceder a la lista.*/
