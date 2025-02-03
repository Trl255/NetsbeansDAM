/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosProductoresConsumidoresDeadLock_SinComentar;

/**

El "deadlock" ocurre cuando dos o m�s hilos est�n esperando 
indefinidamente que se liberen recursos que cada uno de ellos 
tiene bloqueados, y ninguno de los hilos puede avanzar porque 
est�n esperando a que el otro libere el recurso que necesita. 
En tu c�digo original, esto sucede porque:

Hilo 1 adquiere recurso1:
El primer hilo bloquea (adquiere) el recurso1.

Hilo 2 adquiere recurso2:
Al mismo tiempo, el segundo hilo bloquea (adquiere) el recurso2.

Hilo 1 intenta adquirir recurso2:
Despu�s de un tiempo, el primer hilo intenta adquirir (bloquear) 
el recurso2 para continuar. Pero este recurso ya est� bloqueado 
por el segundo hilo, por lo que el primer hilo se detiene y 
espera a que se libere recurso2.

Hilo 2 intenta adquirir recurso1:
Al mismo tiempo, el segundo hilo intenta adquirir (bloquear) 
el recurso1. Pero este recurso ya est� bloqueado por el primer 
hilo, por lo que el segundo hilo se detiene y espera a que se 
libere recurso1.

Ambos hilos est�n ahora esperando que el otro libere el recurso 
que necesita para avanzar, pero ninguno de ellos puede avanzar 
sin el recurso que el otro est� bloqueando. Como resultado, 
se produce un "deadlock" donde ambos hilos se bloquean 
mutuamente y el programa no puede continuar.

 * @author josea
 */

class Main {
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
            synchronized (recurso2) {
                System.out.println("Hilo 2 tiene el recurso 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Hilo 2 espera el recurso 1");
                synchronized (recurso1) {
                    System.out.println("Hilo 2 tiene los recursos 1 y 2");
                }
            }
        });

        hilo1.start();
        hilo2.start();
    }
}