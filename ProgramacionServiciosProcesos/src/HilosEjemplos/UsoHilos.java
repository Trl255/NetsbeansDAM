package HilosEjemplos;


class UsoHilos {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hilo principal iniciando."); // Mensaje de inicio del hilo principal.

        Thread miThread = generarHilo();
        miThread.start();

        // Bucle que se ejecuta 50 veces en el hilo principal.
        for (int i = 0; i < 50; i++) {
            System.out.print(" ."); // Imprime un punto para indicar el progreso.
            Thread.sleep(400); // Pausa de 400 ms entre cada iteración.
        }

        System.out.println("Hilo principal finalizado."); // Mensaje al finalizar el hilo principal.
    }

    public static Thread generarHilo() {
        // Crea una instancia de MiHilo con el nombre "#1".
        MiHilo mh = new MiHilo("#1");
        // Crea un nuevo hilo a partir del objeto MiHilo creado.
        Thread nuevoh = new Thread(mh);
        // Inicia la ejecución del hilo.
        nuevoh.start();

        // Crea una segunda instancia de MiHilo con el nombre "#2".
        MiHilo mh2 = new MiHilo("#2");
        // Crea otro hilo a partir del segundo objeto MiHilo.
        Thread nuevoh2 = new Thread(mh2);
        // Inicia la ejecución del segundo hilo.

        return nuevoh2;
    }
}
