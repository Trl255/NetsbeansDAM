/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExchanger;

/**
 *
 * @author josea
 */
public class Main {

    public static void main(String[] args) {
        Numeros num1 = new Numeros();
        Numeros num2 = new Numeros();

        num1.hNombre("Hilo 1 --> ");
        num2.hNombre("Hilo 2 --> ");

        Thread hilo1 = new Thread(() -> num1.impares());
        Thread hilo2 = new Thread(() -> num2.impares());

        hilo1.start();
        hilo2.start();
    }
}
