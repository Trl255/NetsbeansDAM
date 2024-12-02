/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjecucionProcesos;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author josea
 */
public class LanzarProcesosMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res = "";
        AtomicBoolean salir = new AtomicBoolean(false);

        System.out.println("---------------Lanzar tareas---------------");
        String[] opcionesMenu = {
            "1-Abrir Word",
            "2-Abrir Paint",
            "3-Abrir Bloc de notas",
            "4-Introducir un comando en CMD",
            "5-Salir"
        };
        // System.out.println(opcionesMenu);
        while (!salir.get()) {
            // Definir las acciones (Runnable)
            Runnable[] acciones = {
                // Acción 1 Ejecución de Microsoft Word
                () -> Metodos.ejecucionMicrosoftWord(),
                // Acción 2 Ejecución de Paint
                () -> Metodos.ejecucionMicrosoftPaint(),
                // Acción 3 Bloc de Notas
                () -> Metodos.ejecucionMicrosoftBlocNotas(),
                // Acción 4 cualquier comando de cmd
                () -> {
                    Metodos.ejecucionComandoCMD();
                },
                () -> Metodos.salirAtomicBoolean(salir)// Acción para salir
            };

            // Llamar al método mostrarMenuRunnable
            Metodos.mostrarMenuRunnable(opcionesMenu.length, opcionesMenu, acciones);

        }

    }
}
