/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosSimulDatos;

/**
 *
 * @author josea
 */
public class Main3Hilos {

    public static void main(String[] args) {
        CargandoDatos cargandoDat = new CargandoDatos();

        Thread h1 = new Thread(cargandoDat);
        h1.start();

        ProcesandoDatos procDat = new ProcesandoDatos();
        Thread h2 = new Thread(procDat);
        h2.start();
        
        try {
            h1.join();
            h2.join();
               
        } catch (InterruptedException e) {
            System.out.println("Interrumpido "+e.getMessage());
        }

    }

}
/*Implementa un programa que simule dos tareas concurrentes usando Runnable:

Una tarea que imprima "Cargando datos..." 5 veces con un intervalo de 1 segundo.
Otra tarea que imprima "Procesando datos..." 5 veces con un intervalo de 1.5 segundos.*/
