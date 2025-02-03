/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosSimulDatos;

/**
 *
 * @author josea
 */
public class ProcesandoDatos implements Runnable {

    private String mensaje = "Procesando datos";

    public ProcesandoDatos() {
    }

    @Override
    public void run() {
        int contador = 0;

        try {
            while (contador <= 5) {
                System.out.println(mensaje);
                Thread.sleep(1500);
                contador++;
            }

        } catch (Exception e) {
        }
    }

}
