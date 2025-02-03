/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author josea
 */
public class Metodos {

    static Scanner sc = new Scanner(System.in);

    public static int numberInteger(Scanner sc, String informacion) {
        boolean numberOk = false;
        int value = 0;

        while (!(numberOk)) {
            try {
                System.out.println("Intruduzca " + informacion);
                value = sc.nextInt();
                sc.nextLine();

                numberOk = true;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Recuerde, Introduzca un numero entero");

            }

        }
        return value;
    }

    public static double numberDouble(Scanner sc, String informacion) {
        boolean numberOk = false;
        double value = 0.0;

        while (!numberOk) {
            try {
                System.out.println("Introduzca " + informacion);
                String input = sc.nextLine();

                // Intentar convertir el valor ingresado a un número decimal
                value = Double.parseDouble(input);  // Usamos parseDouble para manejar la conversión manual

                // Si el valor es válido, podemos marcar la validación como exitosa
                numberOk = true;

            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número decimal válido.");
            }
        }
        return value;
    }

    public static double solicitudDoubleRango(Scanner sc, String informacion, double min, double max) {
        boolean numberOk = false;
        double value = 0.0;

        while (!numberOk) {
            try {
                System.out.println("Introduzca " + informacion);
                String input = sc.nextLine();

                // Intentar convertir el valor ingresado a un número decimal
                value = Double.parseDouble(input);  // Usamos parseDouble para manejar la conversión manual

                if (value > min && value < max) {
                    // Si el valor es válido, podemos marcar la validación como exitosa
                    numberOk = true;

                } else {
                    System.out.println(value < min ? "valor demasiado bajo" : "valor demasiado alto ");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número decimal válido.");
            }
        }
        return value;
    }

    public static String solicitudInformacion(Scanner sc, String informacion, int longitud) {
        boolean validacion = false;
        String respuesta = "";
        System.out.println("Intruduzca " + informacion);
        respuesta = sc.nextLine();
        while (!(validacion)) {
            try {

                if (respuesta.trim().isEmpty() || respuesta.length() >= longitud) {
                    validacion = true;
                } else {
                    System.out.println("Información incorrecta, por favor introuzca " + informacion + " con " + longitud + " como minimo");
                    respuesta = sc.nextLine();
                }

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(e.getMessage());

            }

        }
        return respuesta;
    }

    public static java.sql.Date solicitudFecha(Scanner sc, String informacion) {
        boolean validacion = false;
        java.sql.Date respuesta = null;  // Devolveremos un java.sql.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Formato esperado
        sdf.setLenient(false); // Para evitar fechas como el 31/02/2025

        while (!validacion) {
            System.out.println("Introduzca " + informacion + " (formato YYYY-MM-DD):");
            String fechaStr = sc.nextLine();

            try {
                // Intentar parsear la fecha con el formato esperado
                java.util.Date utilDate = sdf.parse(fechaStr);  // Parseamos como java.util.Date
                respuesta = new java.sql.Date(utilDate.getTime());  // Convertimos a java.sql.Date

                // Si llegamos aquí, significa que la fecha es válida
                validacion = true;
            } catch (Exception e) {
                // Si ocurre algún error en el parseo, le pedimos que ingrese el formato correcto
                System.out.println("Formato incorrecto. Por favor, ingrese la fecha en el formato YYYY-MM-DD.");
            }
        }
        return respuesta;
    }

    public static int calcularNumerosprimos(int numero) {
        int i, j;
        boolean esPrimo;
        int rInicial = 2;//Rango inicial, este debe ser mayor de 1.
        int suma = 0;

        for (i = rInicial; i <= numero; i++) {
            //recorro ciclo tantas veces como necesite(<= es para incluir el valor de numero).
            esPrimo = true;// i es primo hasta que se demuestre lo contrario, jejejejeje.
            for (j = 2; j < i; j++) {
                //no coloque j = 1 porque ya sabemos que todo numero es divisible por 1.
                /*j < i es para no incluir el numero a evaluar, pues todo numero es divisible
                por si mismo.*/
                if (i % j == 0) {//Si además del 1 y el mismo hay otro divisor, ya no es primo.
                    //% devuelve el residuo de i/j
                    esPrimo = false;//se demostró que i no es primo.
                }
            }
            if (esPrimo) {//Si es primo lo imprimo.
                System.out.println(i);

                suma = suma + i;
            }

        }
        return suma;
    }

    public static int numerosAleatorios(int numero) {
        if (numero <= 1) {
            numero = 10;
        }
        int numGenerado = (int) Math.floor(Math.random() * numero + 1);
        return numGenerado;
    }

    public static int[] CargaVector(int numMax) {
        int[] v = new int[numMax];
        System.out.println("Inicio de la carga del vector.");
        for (int f = 0; f < v.length; f++) {
            v[f] = Metodos.calcularNumerosprimos(numMax);
        }
        System.out.println("Fin de la carga del vector.");

        return v;
    }

// Método para mostrar el menú y obtener la opción
    // Método para mostrar el menú y ejecutar acciones dinámicas
    public static void mostrarMenuRunnable(int numOpciones, String[] nombresOpciones, Runnable[] acciones) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1; // Valor inicial inválido para validar entrada

        // Validar que los parámetros sean coherentes
        if (nombresOpciones == null || nombresOpciones.length != numOpciones) {
            throw new IllegalArgumentException("El numero de opciones y los nombres no coinciden.");
        }
        if (acciones == null || acciones.length != numOpciones) {
            throw new IllegalArgumentException("El numero de opciones y las acciones no coinciden.");
        }

        // Ciclo para mostrar el menú y ejecutar la acción correspondiente
        while (opcion < 1 || opcion > numOpciones) {
            System.out.println("-------------- MENU ----------------");
            for (int i = 0; i < numOpciones; i++) {
                System.out.println(nombresOpciones[i]);
            }
            System.out.println("------------------------------------");

            try {
                System.out.print("Selecciona una opcion: ");
                opcion = sc.nextInt();

                if (opcion < 1 || opcion > numOpciones) {
                    System.out.println("Por favor, selecciona una opción válida (1-" + numOpciones + ").");
                } else {
                    acciones[opcion - 1].run(); // Ejecutar la acción correspondiente
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un numero.");
                sc.next(); // Limpiar la entrada no válida
            }
        }
    }

    // Métodos para las acciones de ejemplo
    public static void accion1() {
        System.out.println("Ejecutando acción de la Opción 1.");
    }

    public static void accion2() {
        System.out.println("Ejecutando acción de la Opción 2.");
    }

    public static void accion3() {
        System.out.println("Ejecutando acción de la Opción 3.");
    }

    public static void accion4() {
        System.out.println("Ejecutando acción de la Opción 4.");
    }

    public static void salir() {
        System.out.println("Saliendo del programa. ¡Buen día!");
        System.exit(0); // Salir del programa

    }

    public static void salirAtomicBoolean(AtomicBoolean salir) {
        System.out.println("Saliendo del programa.");
        salir.set(true); // Cambia el estado para salir del bucle
    }

    /*// Método principal
    public static void main(String[] args) {
        // Crear instancia de la clase (porque los métodos ya no son estáticos)
        MenuDemo menuDemo = new MenuDemo();

        // Configuración del menú
        String[] opcionesMenu = {
            "Ver datos",
            "Agregar datos",
            "Salir"
        };

        // Ciclo para mantener el menú activo
        boolean continuar = true;
        while (continuar) {
            // Mostrar menú y obtener la opción seleccionada
            int opcionSeleccionada = menuDemo.mostrarMenu(opcionesMenu.length, opcionesMenu);

            // Determinar la acción a realizar según la opción seleccionada
            switch (opcionSeleccionada) {
                case 1 -> menuDemo.accion1();  // Opción 1
                case 2 -> menuDemo.accion2();  // Opción 2
                case 3 -> {
                    menuDemo.salir();           // Salir
                    continuar = false;          // Romper el ciclo
                }
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }
}*/
    public static int mostrarMenu(String[] opciones) {

        int opcion = -1; // Inicializar con un valor inválido

        while (opcion < 1 || opcion > opciones.length) {
            System.out.println("-------------- MENU ----------------");
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ". " + opciones[i]);
            }
            System.out.println("------------------------------------");

            try {
                System.out.print("Selecciona una opciOn (1-" + opciones.length + "): ");
                opcion = sc.nextInt();

                if (opcion < 1 || opcion > opciones.length) {
                    System.out.println("Por favor, selecciona una opciOn válida (1-" + opciones.length + ").");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un numero.");
                sc.next(); // Limpiar la entrada no válida
            }
        }

        return opcion;
    }

}
