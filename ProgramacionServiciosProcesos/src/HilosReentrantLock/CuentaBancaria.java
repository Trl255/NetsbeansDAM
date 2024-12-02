/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosReentrantLock;

/**
 *
 * @author IFC_302
 */

//Importar las dos clases que necesitamos java.util.concurrent.locks.Lock y java.util.concurrent.locks.ReentrantLock

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Creamos la clase CuentaBancaria
public class CuentaBancaria {

    //Declaramos las dos variables de la clase, 1 como double que es saldo y la otra de tipo Lock que es bloqueo
    private double saldo;
    private Lock bloqueo = new ReentrantLock();

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // M�todo para depositar dinero en la cuenta de forma segura
    public void depositar(double cantidad) {
        bloqueo.lock(); // Adquirir el bloqueo
        try {
            saldo += cantidad;
            System.out.println("Deposito de " + cantidad + " realizado. Saldo actual: " + saldo);
        } finally {
            bloqueo.unlock(); // Liberar el bloqueo
        }
    }

    // M�todo para retirar dinero de la cuenta de forma segura
    public void retirar(double cantidad) {
        bloqueo.lock(); // Adquirir el bloqueo
        try {
            if (cantidad <= saldo) {
                saldo -= cantidad;
                System.out.println("Retiro de " + cantidad + " realizado. Saldo actual: " + saldo);
            } else {
                System.out.println("Saldo insuficiente para retirar " + cantidad);
            }
        } finally {
            bloqueo.unlock(); // Liberar el bloqueo
        }
    }

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(1000);

        // Crear dos hilos que intentar�n acceder a la cuenta al mismo tiempo
        Thread hilo1 = new Thread(() -> {
            cuenta.depositar(500);
            cuenta.retirar(200);
        });

        Thread hilo2 = new Thread(() -> {
            cuenta.retirar(300);
            cuenta.depositar(200);
        });

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
    }
}
