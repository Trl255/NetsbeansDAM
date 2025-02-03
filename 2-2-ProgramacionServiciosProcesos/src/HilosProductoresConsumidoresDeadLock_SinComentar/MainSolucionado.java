/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosProductoresConsumidoresDeadLock_SinComentar;

/**
 *
 * @author josea
 */

public class MainSolucionado {

	 public static void main(String[] args) {
	        Object recurso1 = new Object();
	        Object recurso2 = new Object();
	        
	        Thread hilo1 = new Thread(() -> {
	            synchronized (recurso1) {
	                System.out.println("Hilo 1 tiene el recurso 1");
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                System.out.println("Hilo 1 espera el recurso 2");
	                synchronized (recurso2) {
	                    System.out.println("Hilo 1 tiene los recursos 1 y 2");
	                }
	            }
	        });

	        Thread hilo2 = new Thread(() -> {
	            synchronized (recurso1) {
	                System.out.println("Hilo 2 tiene el recurso 1");
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                System.out.println("Hilo 2 espera el recurso 2");
	                synchronized (recurso2) {
	                    System.out.println("Hilo 2 tiene los recursos 1 y 2");
	                }
	            }
	        });

	        hilo1.start();
	        hilo2.start();

	        try {
	            Thread.sleep(1000); // Espera un tiempo corto para ver si se produce un deadlock
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        if (hilo1.getState() == Thread.State.BLOCKED || hilo2.getState() == Thread.State.BLOCKED) {

	            System.out.println("Posible deadlock detectado");
	        }



	 }
	
}

