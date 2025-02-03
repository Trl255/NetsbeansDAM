/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExchangerTienda;

/**
 *
 * @author josea
 */
public class Tienda {

    private int stock;

    public Tienda(int stockInicial) {
        this.stock = stockInicial;
    }

    public synchronized void comprar(Cliente cliente, int cantidad) {
        int compra = 0;
        System.out.println("Actualmente hay " + stock + " cantidad en stock.");
        if (cantidad <= stock && cantidad > 0) {

            stock -= cantidad;
            System.out.println("El cliente: " + cliente.getNombre() + " se encuentra comprando"
                    + "la cantidad de: " + cantidad
                    + " actualmente queda " + stock + " final");

        } else if (cantidad >= stock && cantidad > 0) {
            int stockAntesCompra = stock;
            while (stock > 0) {
                stock--;
                compra++;
            }

            System.out.println("El cliente: " + cliente.getNombre() + " quiere comprar "
                    + "la cantidad de: " + cantidad
                    + (stockAntesCompra > 0 ? " pero no hay suficiente stock solo puede comprar " + compra + " cantidad que no se vendio: " + (cantidad-stockAntesCompra) : " no puede "
                            + " pero no hay stock " + stock + " realizar la compra de " + cantidad));
        } else {

            System.out.println(cliente.getNombre() + " No se puede realizar la compra, faltan " + (cantidad - stock) + " cantidad en stock para completar la compra de: " + cantidad);
        }

        System.out.println("----------------");
    }

}
