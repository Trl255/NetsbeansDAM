/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodosvarios;

/**
 *
 * @author josea
 */
public class Aleatorio {

    public static void main(String[] args) {

        generarCaracter();

    }

    static void generarCaracter() {
        String codigo = null, cod = null;

        for (int i = 0; i < 20; i++) {
            char num = (char) Math.floor(Math.random() * (65 - 90 + 1) + (90));

            Integer numero = (int) Math.floor(Math.random() * (10005 - 20000 + 1) + (20000));

            codigo = numero + "";
            cod = num + codigo;
            System.out.println(cod);

        }

    }

    static int generarNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

}
