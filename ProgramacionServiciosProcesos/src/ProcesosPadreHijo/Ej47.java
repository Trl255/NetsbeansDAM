package ProcesosPadreHijo;  // Declaración del paquete 'ejercicios'.

import java.io.File;  // Importación de la clase File para manejar archivos y directorios.
import java.io.InputStream;  // Importación de InputStream para leer datos de entrada.
import java.io.OutputStream;  // Importación de OutputStream para enviar datos de salida.

public class Ej47 {// Definición de la clase Ej47.

    public static void main(String[] args) {  // Método principal, punto de entrada de la aplicación.
        // Crea un objeto ProcessBuilder para ejecutar el programa Ej47_1.
        ProcessBuilder pb = new ProcessBuilder("java", "ejercicios.Ej47_1");

        // Establece el directorio donde se encuentran las clases compiladas (en este caso, para NetBeans).
        pb.directory(new File(".\\build\\classes"));

        // Redirige los errores del proceso hijo al flujo de salida estándar del padre.
        pb.redirectErrorStream(true);

        Process p = null;  // Inicializa la variable del proceso.

        try {
            p = pb.start();  // Inicia el proceso hijo, que ejecutará la clase Ej47_1.
            OutputStream os = p.getOutputStream();  // Obtiene el flujo de salida del proceso hijo.

            // Envía el nombre "Elena" al proceso hijo como un array de bytes.
            os.write("Elena".getBytes());
            os.close();  // Cierra el flujo de salida.

            // Espera a que el proceso hijo termine su ejecución.
            p.waitFor();
            /*La línea waitFor() es crucial para mantener el orden y la sincronización entre el proceso padre y el hijo
             Sin ella, el padre podría encontrarse intentando leer datos que aún no han sido generados por el hijo.Comentar o no esta línea afecta directamente cómo interactúan el proceso padre y el hijo y puede tener consecuencias significativas en el comportamiento de tu aplicación*/

            InputStream is = p.getInputStream();  // Obtiene el flujo de entrada del proceso hijo.

            // Lee carácter por carácter hasta que no haya más datos en el flujo de entrada.
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);  // Imprime cada carácter leído.
            }
        } catch (Exception e) {  // Manejo de excepciones en caso de errores durante la ejecución.
            e.printStackTrace();  // Imprime el seguimiento de la pila en caso de una excepción.
        }
    }
}
