/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService;
import java.util.Random;
/**
 *
 * @author josea
 */
public class TareaRandom implements Runnable {
	static Random rnd = new Random();
	private int n;

	public TareaRandom(int n) {
		this.n = n;
	}

	public void run() {
		int t = rnd.nextInt(3000) + 1;
		try {
			Thread.sleep(t);
			System.out.println("Tarea " + n + " ha tardado " + t + " millis");
		} catch (InterruptedException e) {
			System.out.println("Tarea " + n + " interrumpida");
		}
	}
}
