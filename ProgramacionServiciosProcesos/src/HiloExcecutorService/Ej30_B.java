/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HiloExcecutorService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author josea
 */
public class Ej30_B {

    public static void main(String[] args) {
        try {
            String nombreCarpetaMover="prueba";

            List<Tarea30> listaTareas = new ArrayList<Tarea30>();
            ExecutorService pool = Executors.newFixedThreadPool(5);

            // Paso 1: Obtener la ruta de la carpeta "prueba" dentro de "user.dir"
            String dirOrigen = System.getProperty("user.dir") + File.separator + nombreCarpetaMover;  // Ruta donde está la carpeta "prueba"
            String dirDestino = System.getProperty("user.dir") + File.separator + "Respaldo";  // Nueva carpeta "Respaldo"

            // Paso 2: Verificar si la carpeta "prueba" existe
            File destino = new File(dirDestino);
            if (!destino.exists()) {
                destino.mkdir();
            }

            // listamos el contenido de dirOrigen
            File origen = new File(dirOrigen);
            File[] lFicheros = origen.listFiles();
            for (File f : lFicheros) {
                if (f.isFile()) {
                    Tarea30 tarea = new Tarea30(f, dirDestino);
                    listaTareas.add(tarea);
                }
            }

            List<Future<List<String>>> listaFuturos = pool.invokeAll(listaTareas);
            // vaya mirando si han acabado
            // el que no haya acabado lo indico
            boolean terminado = true;
            do {
                terminado = true;
                for (Future<List<String>> fut : listaFuturos) {
                    if (!fut.isDone()) {
                        terminado = false;
                    }
                }
            } while (!terminado);

            for (Future<List<String>> fut : listaFuturos) {
                List<String> res = (List<String>) fut.get();
                System.out.println(res.get(0) + " - " + res.get(1));
            }

            System.out.println("Copia completada");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static Future<List<String>> Copiar(File fOrigen, String dirDestino, ExecutorService pool) {
        Tarea30 tarea = new Tarea30(fOrigen, dirDestino);
        Future<List<String>> f = pool.submit(tarea);
        return f;
    }

}
