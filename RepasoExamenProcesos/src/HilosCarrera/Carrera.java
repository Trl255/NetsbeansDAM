/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosCarrera;

/**
 *
 * @author josea
 */
public class Carrera implements Runnable {

    private int tipo;
    private String nombre;

    public Carrera(int tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        if (tipo == 1) {
            for (int i = 0; i < 10; i++) {

                System.out.println("El hilo " + nombre + " lleva " + i);

            }
            System.out.println("El hilo " + nombre + "  ha finalizado.");

        } else if (tipo == 2) {
            for (char c = 'A'; c < 'Z'; c++) {
                System.out.println("El hilo " + nombre + " lleva " + c);
            }
            System.out.println("El hilo " + nombre + "  ha finalizado.");
        }
        try {
            Thread.sleep((int) (Math.random() * 1000));

        } catch (InterruptedException e) {
        }

    }
}
