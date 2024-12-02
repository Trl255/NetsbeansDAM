/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author josea
 */
public class Ej30_B {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contFileCopy = 0;
        String respuesta = "";
        boolean pedirDirOrigen = true;
        boolean pedirCopiarCarpeta = true;

        String dirOrigen = "prueba";

        try {
            //Instanciamos a Tarea30 con una lista de listaTareas, asignamos el ArrayList de tipo "Tarea30"

            List<Tarea30> listaTareas = new ArrayList<Tarea30>();
            //Creamos un ExecutorService llamado pool y lo limitamos mediante Executors.newFixedThreadPool (5) limitandolo a 5
            ExecutorService pool = Executors.newFixedThreadPool(5);

            //Definimos un nombre a la carpeta destino, en este caso "copia_prueba" podría ser "respaldo + dirOrigen"
            if (pedirDirOrigen) {
                dirOrigen = Metodos.solicitudInformacion(sc, "nombre del archivo de origen", 4);

            }
            if (pedirCopiarCarpeta) {
                respuesta = Metodos.solicitudInformacion(sc, "Desea copiar las subcarpetas", 1).toUpperCase();
                System.out.println(respuesta);
            }

            String dirDestino = "Respaldo " + dirOrigen;

            // listamos el contenido de dirOrigen
            File origen = new File(dirOrigen);

            //Comprobamos si exsite el archivo de origen antes de continuar
            if (!origen.exists()) {
                System.out.println("Archivo no encontrado, compruebe el nombre introducido " + dirOrigen);

            } else {

                //Si el archivo de origen existe, continuamos...
                // validamos que el destino existe, de lo contrario lo creamos con MKDIR
                File destino = new File(dirDestino);
                if (!destino.exists()) {
                    destino.mkdir();
                }

                //Creamos un array de File, el archivo "origen" aplicamos "listFiles()" este es un metodo de la clase File, que retorna toda 
                //una lista de fichero que encuentra encuentra.
                
                
                File[] lFicheros = origen.listFiles();
                
                // Creamos una lista de futuros (Future) para almacenar los resultados
                List<Future<List<String>>> listaFuturos = new ArrayList<>();

                //Recorremos y validamos si es fichero, es caso afirmativo se instancia Tarea30, asignando al primer parametro el fichero y el segundo la ruta de destino
                for (File f : lFicheros) {
                    if (f.isFile()) {
                        // Usamos la función Copiar para crear y enviar la tarea al ExecutorService
                        Future<List<String>> futuro = Copiar(f, dirDestino, pool);
                        System.out.println("Lista de ficheros: "+f.getName());

                        // Añadimos el Future individual generado de la función Copiar a la lista de futuros
                        listaFuturos.add(futuro);
                    } else if (f.isDirectory() && respuesta.equalsIgnoreCase("SI")) {
                        File[] subFicheros = f.listFiles();

                        for (File subF : subFicheros) {
                            Future<List<String>> futuro = Copiar(subF, dirDestino + "/" + f.getName(), pool);
                        
                        listaFuturos.add(futuro);
                        }

                    }
                }

                ///SE PUEDE OMITIR LA SIGUIENTE LINEA PORQUE ANTERIORMENTE SE HACE USO DE LA FUNCION "COPIAR()" al final del codigo.
                //Ahora creamos una LISTA de tipo Future, que a su vez contiene otra lista de tipo STRING,                 
                // List<Future<List<String>>> listaFuturos = pool.invokeAll(listaTareas);
                // Se recorre la lista de "future" para comprobar si ha terminado 
                // la funcion ".get()" bloquea y espera hasta que el proceso haya terminado 
                for (Future<List<String>> fut : listaFuturos) {
                    try {
                        List<String> res = fut.get(); // Bloquea hasta que la tarea haya terminado

                        contFileCopy++;
                        System.out.println(contFileCopy + "- " + res.get(0) + " - " + res.get(1));
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println("Error al obtener el resultado de la tarea: " + e.getMessage());
                    }
                }

                System.out.println("Copia completada");
            }
        } catch (Exception e) {
            // Captura general para cualquier otra excepción inesperada
            System.out.println("Ocurrió un error: " + e.getMessage());
        }

    }

    public static Future<List<String>> Copiar(File fOrigen, String dirDestino, ExecutorService pool) {
        Tarea30 tarea = new Tarea30(fOrigen, dirDestino);
        Future<List<String>> f = pool.submit(tarea);
        return f;
    }

}
