/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repasoexamenprocesos;

/**
 *
 * @author josea
 */
public class ContadorMenos implements Runnable {

    public ContadorMenos() {
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {

                Thread.sleep(1000);
                System.out.println(i == 9 ? "A por el ultimo: " + i : "Sumando 1: " + i);

            }
        } catch (Exception e) {
        }
    }

}
