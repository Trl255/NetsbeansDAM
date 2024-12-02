/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosSuma_Par_Impar;

/**
 *
 * @author josea
 */
public class HiloImpar implements Runnable {

    int numeroImpar;

    public HiloImpar() {
        this.numeroImpar = numeroImpar;
    }

    @Override
    public void run() {
        int suma = 0;
        System.out.println("Ejercicio 4 Números impar:");

        for (int i = 0; i < 10; i++) {

            if (i == 0) {

                System.out.println("La suma de los números Impar es: " + suma);
            }
            if (i % 2 != 0) {
                System.out.println("Numero: " + i);
                suma += i;
            }

        }
            System.out.println("La suma total de número Impares es: " + suma);

    }

}
