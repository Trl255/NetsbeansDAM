package HilosEjemplos;

// Clase MiHilo que implementa Runnable para que sus instancias puedan ejecutarse en hilos independientes.
class MiHilo implements Runnable {

    String nombreHilo; // Nombre del hilo, que se especifica al crear una instancia de MiHilo.

    // Constructor que inicializa el nombre del hilo.
    MiHilo(String nombre) {
        nombreHilo = nombre;
    }

    // Método run() que actúa como el punto de entrada del hilo.
    // Aquí se define el trabajo que realizará el hilo al ser iniciado.
    public void run() {
        System.out.println("Comenzando " + nombreHilo); // Mensaje que indica el inicio del hilo.

        try {
            // Captura el tiempo de inicio en milisegundos.
            long inicio = System.currentTimeMillis();

            // Bucle que se ejecuta 10 veces, simulando una tarea en el hilo.
            for (int contar = 0; contar < 10; contar++) {
                System.out.println("En " + nombreHilo + ", "
                        + "el recuento " + contar); // Muestra el nombre del hilo y el conteo actual.
            }

            // Calcula el tiempo total de ejecución restando el tiempo actual al tiempo de inicio.
            long tiempo_total = ((System.currentTimeMillis() - inicio));
            System.out.println(tiempo_total + " milisegundos"); // Muestra el tiempo total de ejecución en ms.

        } catch (Exception exc) {
            System.out.println(nombreHilo + " Interrumpido."); // Mensaje si el hilo es interrumpido durante su ejecución.
        }

        System.out.println("Terminando " + nombreHilo); // Indica que el hilo ha terminado su ejecución.
    }
}