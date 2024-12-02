/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LiebreYTortuga;

import java.util.Random;

/**
 *
 * @author josea
 */
class Carrera {

    private final StringBuilder posiciones = new StringBuilder();
    private int posicionActual = 1;

    public synchronized void registrarLlegada(String nombre) {
        posiciones.append(posicionActual).append(" lugar: ").append(nombre).append("\n");
        posicionActual++;
    }

    public void mostrarResultados() {
        System.out.println(posiciones);
    }
}

class Animal implements Runnable {

    private final String nombre;
    private final int minSleep; // Tiempo mínimo de sleep
    private final int maxSleep; // Tiempo máximo de sleep
    private final int distanciaTotal = 10; // Distancia total en kilómetros
    private final Carrera carrera; // Referencia a la clase que gestiona la carrera
    private final Random random = new Random();

    public Animal(String nombre, int minSleep, int maxSleep, Carrera carrera) {
        this.nombre = nombre;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
        this.carrera = carrera;
    }

    @Override
    public void run() {
        int distanciaRecorrida = 0;

        while (distanciaRecorrida < distanciaTotal) {
            try {
                // Tiempo de espera aleatorio dentro del rango definido
                int tiempo = random.nextInt(maxSleep - minSleep + 1) + minSleep;

                // Simular obstáculos para guepardo y liebre
                if (("Liebre".equals(nombre) || "Guepardo".equals(nombre)) && random.nextInt(100) < 20) {
                    System.out.println(nombre + " encuentra un obstaculo y se detiene un poco.");
                    tiempo += random.nextInt(200); // Obstáculo aumenta el tiempo de espera
                }

                // Simular ventajas ocasionales para la tortuga
                if ("Tortuga".equals(nombre) && random.nextInt(100) < 10) {
                    System.out.println(nombre + " avanza rapido por un terreno favorable.");
                    tiempo -= random.nextInt(200); // La tortuga reduce el tiempo de espera
                }

                Thread.sleep(tiempo);

                // Avanzar un kilómetro
                distanciaRecorrida++;
                System.out.println(nombre + " ha recorrido " + distanciaRecorrida + " km.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " ha terminado la carrera.");
        carrera.registrarLlegada(nombre);
    }
}
