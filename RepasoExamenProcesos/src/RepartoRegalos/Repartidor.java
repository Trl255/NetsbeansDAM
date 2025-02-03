/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RepartoRegalos;

/**
 *
 * @author josea
 */
public class Repartidor implements Runnable {

    private String nombre;
    private String[] paquetes;

    public Repartidor(String nombre, String[] paquetes) {
        this.nombre = nombre;
        this.paquetes = paquetes;
    }

    @Override
    public void run() {
        long tInicio = System.currentTimeMillis();

        try {
            System.out.println(this.nombre + " empieza a repartir");
            for (int i = 0; i < paquetes.length; i++) {
                System.out.println(this.nombre + " se encuentra repartiendo el paquete " + paquetes[i]);
            Thread.sleep((int) (Math.random() * 3000));
            }
            long tFin = System.currentTimeMillis();
            long tTotal = tFin - tInicio;
            System.out.println(this.nombre + " ha terminado de repartir!!!!" + "\n En " + tTotal + " ms\n");
            
        } catch (Exception e) {
        }

    }

}
