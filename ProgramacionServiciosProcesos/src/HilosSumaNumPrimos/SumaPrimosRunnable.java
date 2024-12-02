
package HilosSumaNumPrimos;
import java.util.Random;

/**
 *
 * @author IFC_302
 */
public class SumaPrimosRunnable {
    
    public static void main(String[] args) {
        int limite = 100000; // Cambia este valor para ajustar el límite superior.

        // Generar aleatoriamente el número de hilos (entre 2 y 4)
        int numHilos = new Random().nextInt(3) + 2;
        System.out.println("Número de hilos: " + numHilos);

        // Dividir el rango entre los hilos
            int rango = limite / numHilos;

        // Contenedor para almacenar la suma total
        SumaCompartida sumaCompartida = new SumaCompartida();

        // Crear y lanzar los hilos
        Thread[] hilos = new Thread[numHilos];
        for (int i = 0; i < numHilos; i++) {
            int inicio = i * rango + 1;
            int fin = (i == numHilos - 1) ? limite : (i + 1) * rango;

            Runnable tarea = new CalculadorPrimosRunnable(inicio, fin, sumaCompartida);
            hilos[i] = new Thread(tarea);
            hilos[i].start(); // Iniciar el hilo
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprimir el resultado final
        System.out.println("Suma total de números primos hasta " + limite + ": " + sumaCompartida.getSuma());
    }
}

// Clase que actúa como contenedor mutable para la suma total
class SumaCompartida {
    private long suma = 0;

    // Método sincronizado para agregar valores a la suma
    public synchronized void agregar(long valor) {
        suma += valor;
    }

    // Método para obtener la suma total
    public synchronized long getSuma() {
        return suma;
    }
}

/*1.	Escribe un programa en Java que calcule la suma de números primos hasta un límite dado usando múltiples hilos.*/