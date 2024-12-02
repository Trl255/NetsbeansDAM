/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso.CompartirListaNumeros;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author josea
 */
public class HilosDe3_Numeros {

    public static void main(String[] args) {
        //Declaramos las variables
        Lock bloqueo = new ReentrantLock();
        List<Integer> listaNumeros = new ArrayList<>();
        long tiempoInicio = System.currentTimeMillis();

        //Generamos las 3 clases
        GenerarNumeros generaNum = new GenerarNumeros(bloqueo, listaNumeros);
        MostrarListaNumeros mostrarNum = new MostrarListaNumeros(bloqueo, listaNumeros);
        SumaListaNumeros sumNum = new SumaListaNumeros(bloqueo, listaNumeros);

        Thread hiloGenerar = new Thread(generaNum);
        System.out.println("Iniciamos en: " + tiempoInicio);
        hiloGenerar.start();
        try {
            hiloGenerar.join();
        } catch (InterruptedException e) {
            System.out.println("Interrumpido " + e.getMessage());
        }
        Thread hiloMostrar = new Thread(mostrarNum);
        Thread hiloSumar = new Thread(sumNum);

        hiloMostrar.start();
        hiloSumar.start();
        long tiempoFin=System.currentTimeMillis();
        long tiempoTranscurrido=tiempoFin-tiempoInicio;
        System.out.println("Finalizamos "+tiempoTranscurrido+" ms");
    }

}
