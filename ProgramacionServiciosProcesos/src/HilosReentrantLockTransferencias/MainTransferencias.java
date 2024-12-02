/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosReentrantLockTransferencias;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author josea
 */
public class MainTransferencias {

    public static void main(String[] args) {
        // Crear los bloqueos para las cuentas
        Lock bloqueo1 = new ReentrantLock();
        Lock bloqueo2 = new ReentrantLock();

        // Crear los clientes
        Cliente maria = new Cliente("Maria", 500, bloqueo1);
        Cliente jose = new Cliente("Jose", 300, bloqueo2);

        // Crear el banco
        Banco banco = new Banco();

        // Crear las transferencias
        Transferencia transferencia1 = new Transferencia(maria, jose, 50);
        Transferencia transferencia2 = new Transferencia(jose, maria, 30);

        // Crear hilos para ejecutar las transferencias
        Thread hilo1 = new Thread(() -> banco.enviarTransferencia(transferencia1));
        Thread hilo2 = new Thread(() -> banco.enviarTransferencia(transferencia2));

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
    }
}
