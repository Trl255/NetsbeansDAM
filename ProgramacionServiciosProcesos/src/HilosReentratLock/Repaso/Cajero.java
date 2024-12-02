    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package HilosReentratLock.Repaso;

    import java.util.concurrent.locks.Lock;
    import java.util.concurrent.locks.ReentrantLock;

    /**
     *
     * @author josea
     */
    public class Cajero {

        private Lock bloqueo = new ReentrantLock();

        public Cajero() {

        }

        public void ingresarDinero(String persona, double cantIngresar, double saldoActual) {
            bloqueo.lock();
            int tiempoBloqueo = generarNumeroAleatorio(1000, 3400);

            try {
                Thread.sleep(tiempoBloqueo);
                System.out.println(persona + " se encuentra ingresando " + cantIngresar + " el cajero. Y tiene un saldo actual es de " + saldoActual);
                System.out.println();

                saldoActual += cantIngresar;

                System.out.println(persona + " ha ingresado " + cantIngresar + " a su cuenta. Y tiene un saldoa actual es de " + saldoActual);

            } catch (InterruptedException e) {
                System.out.println("El proceso ha sido interrumpido " + e.getMessage());
            } finally {
                bloqueo.unlock();
            }

        }

        public void sacarDinero(String persona, double cantidad, double saldoActual) {

            bloqueo.lock();
            int tiempoBloqueo = generarNumeroAleatorio(1000, 3400);

            try {

                Thread.sleep(tiempoBloqueo);

                if (cantidad <= saldoActual) {

                    System.out.println(persona + " esta sacando " + cantidad + " en el cajero, saldo actual "+saldoActual);
                    saldoActual -= cantidad;

                } else {
                    System.out.println("Ha intentado sacar " + cantidad + ", pero sólo tienen en su cuenta :" + saldoActual);
                }

                System.out.println(persona + " ha terminado de utilizar el cajero, tiene un saldoActual de: " + saldoActual);

            } catch (InterruptedException e) {
                System.out.println("El proceso ha sido interrumpido " + e.getMessage());
            } finally {
                bloqueo.unlock();
            }

        }

        public int generarNumeroAleatorio(int minimo, int maximo) {
            int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
            return num;
        }
    }
