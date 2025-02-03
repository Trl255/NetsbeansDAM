/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExchangerTienda;

/**
 *
 * @author josea
 */
public class MainTienda {

    public static void main(String[] args) {
        Tienda tiendaColumpio = new Tienda(12);

        Cliente cliente1 = new Cliente(tiendaColumpio, "Patricia", getRandom(3, 5));
        Cliente cliente2 = new Cliente(tiendaColumpio, "Jose", getRandom(3, 6));
        Cliente cliente3 = new Cliente(tiendaColumpio, "Isabella", getRandom(3, 9));
        Cliente cliente4 = new Cliente(tiendaColumpio, "Maryfer", getRandom(3, 3));
        Cliente cliente5 = new Cliente(tiendaColumpio, "Teo", getRandom(3, 11));

        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();
        
    }

    public static int getRandom(int min, int max) {

        return (int) ((Math.random() * (max - min + 1) + min));
    }

}
