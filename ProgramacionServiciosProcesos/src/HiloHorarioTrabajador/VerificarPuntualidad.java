/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HiloHorarioTrabajador;

import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class VerificarPuntualidad implements Runnable {

    private String posicionPersona;

    public VerificarPuntualidad(String nombrePersona) {
        this.posicionPersona = nombrePersona;
    }

    @Override
    public void run() {
        synchronized (System.in) {
            Scanner sc = new Scanner(System.in);
            String nombreTrabajador = generarNombresAleatorio();
            boolean horaValida = false;
            LocalTime horaLlegada = null;

            System.out.println("Introduzca el día de llegada de  al trabajo " + posicionPersona + " con nombre " + nombreTrabajador);
            String dia = sc.nextLine();

            while (!horaValida) {
                System.out.println("Introduzca la hora de llegada de  (formato 24h, ej. 07:30): " + posicionPersona + " con nombre " + nombreTrabajador);
                String hora = sc.nextLine();

                try {
                    horaLlegada = LocalTime.parse(hora);
                    horaValida = true;
                } catch (Exception e) {
                    System.out.println("Hora no válida. Asegúrese de usar el formato HH:mm (ej. 07:30).");

                }

            }
            LocalTime horaEntrada = LocalTime.of(0, 8);
            String mensaje = horaLlegada.isBefore(horaEntrada)
                    ? "El trabajador ha llegado TEMPRANO"
                    : "El trabajador llego TARDE";

            System.out.println("" + posicionPersona + " " + mensaje);

        }

    }

    public static String generarNombresAleatorio() {
        String[] nombres = {"Pedro", "María", "Jesús", "Catalina", "Santi"};
        Random random = new Random();
        int indiceAleatorio = random.nextInt(nombres.length);
        String nombre = nombres[indiceAleatorio];

        return nombre;
    }

}
