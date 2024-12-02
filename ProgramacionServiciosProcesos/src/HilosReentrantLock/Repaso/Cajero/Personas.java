/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosReentrantLock.Repaso.Cajero;

/**
 *
 * @author josea
 */
public class Personas implements Runnable {

    private Cajero cajero;
    private String nombre;
    private double saldoActual;

    public Personas(Cajero cajero, String nombre) {
        this.cajero = cajero;
        this.nombre = nombre;
        this.saldoActual = cajero.generarNumeroAleatorio(0, 1000);
    }

    @Override
    public void run() {
        double dineroIngresar = cajero.generarNumeroAleatorio(0, 1000);
        double dineroSacar = cajero.generarNumeroAleatorio(0, 500);

        cajero.ingresarDinero(this, dineroIngresar); // Pasamos la instancia completa
        cajero.sacarDinero(this, dineroSacar);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public synchronized double getSaldoActual() {
        return saldoActual;
    }

    public synchronized void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    @Override
    public String toString() {
        return "Persona{" + "cajero=" + cajero + ", nombre=" + nombre + '}';
    }

}
