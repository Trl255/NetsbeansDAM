/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LiebreYTortuga;

/**
 *
 * @author josea
 */

public class CarreraConPosiciones {
    public static void main(String[] args) throws InterruptedException {
        // Crear hilos para cada animal
        Carrera carrera = new Carrera();
        Thread liebre = new Thread(new Animal("Liebre", 200, 400, carrera));
        Thread tortuga = new Thread(new Animal("Tortuga", 250, 420, carrera));
        Thread guepardo = new Thread(new Animal("Guepardo", 150, 250, carrera));

        // Iniciar la carrera
        System.out.println("!La carrera comienza!");
        liebre.start();
        tortuga.start();
        guepardo.start();

        // Esperar a que todos los hilos terminen
        liebre.join();
        tortuga.join();
        guepardo.join();

        // Mostrar posiciones finales
        System.out.println("\n!La carrera ha terminado!");
        carrera.mostrarResultados();
    }
}

/*3.	Tres animales, una liebre, una tortuga y un guepardo van a realizar una carrera sobre 10 kilómetros. 
Cada uno de ellos tiene una velocidad diferente, aunque el camino a recorrer está plagado de obstáculos. 
Realiza diferentes códigos para que gane por lo menos la carrera cada vez uno de los animales. */