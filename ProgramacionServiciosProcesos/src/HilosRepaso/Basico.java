/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosRepaso;

/**
 *
 * @author josea
 */
public class Basico {

    public static void main(String[] args) {
        ImprimirNumeros numeros = new ImprimirNumeros();
        ImprimirLetras letras = new ImprimirLetras();

        Thread hiloNumeros=new Thread(numeros);
        Thread hiloLetras=new Thread(letras);
        
        hiloNumeros.start();
        hiloLetras.start();
        
        
        try {
            
            hiloNumeros.join();
            
            
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
        System.out.println("Hemos terminado el Hilo ");
        
        
    }
}
