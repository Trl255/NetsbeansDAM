/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService.Repartidor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josea
 */
public class Cliente {

    private String nombre;
    private List<Pedido> pedidos;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.pedidos = new ArrayList<>();

    }

    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);  // Método para agregar pedidos
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
