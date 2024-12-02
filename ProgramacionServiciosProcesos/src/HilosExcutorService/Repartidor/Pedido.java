/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService.Repartidor;

/**
 *
 * @author josea
 */
public class Pedido {

    private Cliente cliente;
    private int cantidad;
    private String articulo;

    public Pedido(int cantidad, String articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public Pedido(Cliente cliente, int cantidad, String articulo) {
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    // Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Pedido [Cliente: " + cliente.getNombre() + ", Cantidad: " + cantidad + "]";
    }
}
