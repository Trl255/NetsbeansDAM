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
public class Cliente {
    private String nombre;
    private double saldo;
    private Lock bloqueo;

    public Cliente(String nombre, double saldo, Lock bloqueo) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.bloqueo = bloqueo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Lock getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Lock bloqueo) {
        this.bloqueo = bloqueo;
    }
}