
package HilosSumaNumPrimos;

/**
 *
 * @author IFC_302
 */
// Clase que implementa la tarea de cálculo de primos usando Runnable
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

class CalculadorPrimosRunnable implements Runnable {

    private final int inicio;
    private final int fin;
    private final SumaCompartida sumaCompartida;

    public CalculadorPrimosRunnable(int inicio, int fin, SumaCompartida sumaCompartida) {
        this.inicio = inicio;
        this.fin = fin;
        this.sumaCompartida = sumaCompartida;
    }

    @Override
    public void run() {
        long sumaParcial = 0;
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                sumaParcial += i;
            }
        }
        System.out.println("Suma parcial del rango [" + inicio + " - " + fin + "] = " + sumaParcial);
        sumaCompartida.agregar(sumaParcial); // Agregar la suma parcial al contenedor compartido
    }

    private boolean esPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
