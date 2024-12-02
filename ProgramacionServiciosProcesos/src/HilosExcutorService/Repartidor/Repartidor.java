/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService.Repartidor;

import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author josea
 */
public class Repartidor implements Runnable {

    private String nombre;
    private  List<Pedido> pedido;
    private long tiempoReparto;
    private Cliente cliente;

    public Repartidor(String nombre, List<Pedido> pedido) {
        this.nombre = nombre;
        this.pedido = pedido;
    }

    public Repartidor(String nombre, List<Pedido> pedido, Cliente cliente) {
        this.nombre = nombre;
        this.pedido = pedido;
        this.cliente = cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        long tiempoInicio = System.currentTimeMillis();

        for (Pedido p : pedido) {  // Cambiar la iteración para usar el array 'pedido' de la clase Repartidor
            System.out.println(nombre + " empieza a repartir el pedido " + p.toString());

            try {
                Thread.sleep(50);  // Simula el tiempo de reparto
            } catch (InterruptedException e) {
                System.out.println("Ha habido un problema a la hora de ejecutar " + e.getMessage());
            }
        }

        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println("Ha tardado " + tiempoTotal + " ms en repartir todos los pedidos.");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pedido> getPedidos() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    public long getTiempoReparto() {
        return tiempoReparto;
    }

    public void setTiempoReparto(long tiempoReparto) {
        this.tiempoReparto = tiempoReparto;
    }

    @Override
    public String toString() {
        return "Repartidor" + "\nNombre=" + nombre + "\nPedidos=" + pedido + "\nTiempoReparto=" + tiempoReparto;
    }

}
