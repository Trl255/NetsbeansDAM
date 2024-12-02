/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosReentrantLockTransferencias;

import java.util.concurrent.locks.Lock;

/**
 *
 * @author josea
 */
public class Banco {

    public void enviarTransferencia(Transferencia transferencia) {
        Cliente cOrigen = transferencia.getClienteOrigen();
        Cliente cDestino = transferencia.getClienteDestino();
        double cantidad = transferencia.getMonto();

        Lock bloqueoCuentaOrigen = cOrigen.getBloqueo();
        Lock bloqueoCuentaDestino = cDestino.getBloqueo();

        // Bloquear las cuentas
        bloqueoCuentaOrigen.lock();
        bloqueoCuentaDestino.lock();
        try {
            if (cOrigen.getSaldo() >= cantidad) {
                // Realizar la transferencia
                cOrigen.setSaldo(cOrigen.getSaldo() - cantidad);
                cDestino.setSaldo(cDestino.getSaldo() + cantidad);

                System.out.println("Transferencia de " + cantidad + " realizada de " + cOrigen.getNombre() +
                        " a " + cDestino.getNombre());
                System.out.println("Saldo final de " + cOrigen.getNombre() + ": " + cOrigen.getSaldo());
                System.out.println("Saldo final de " + cDestino.getNombre() + ": " + cDestino.getSaldo());
            } else {
                System.out.println("Saldo insuficiente en la cuenta de " + cOrigen.getNombre());
            }
        } finally {
            bloqueoCuentaOrigen.unlock();
            bloqueoCuentaDestino.unlock();
        }
    }
}