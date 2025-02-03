/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExchangerTienda;

/**
 *
 * @author josea
 */
public class Cliente extends Thread {

    private Tienda tienda;
    private String nombre;
    private int cantProductos;

    public Cliente() {
    }

    public Cliente(Tienda tienda, String nombre, int cantProductos) {
        this.tienda = tienda;
        this.nombre = nombre;
        this.cantProductos = cantProductos;
    }

    @Override
    public void run() {
        tienda.comprar(this, cantProductos);
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantProductos;
    }

    public void setCantidad(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    @Override
    public String toString() {
        return "El cliente: " + this.nombre + "tienda: " + tienda + ", nombre del cliente: " + nombre + ", cantProductos=" + cantProductos + '}';
    }

}
