package ProcesosPadreHijo;// Declaración del paquete 'ejercicios', que organiza las clases.

import java.io.File;
import java.io.InputStream;

import java.io.File;  // Importación de la clase File para manejar archivos y directorios.
import java.io.InputStream;  // Importación de InputStream para leer datos de entrada.

public class Ej40 {  // Definición de la clase Ej40.

    public static void main(String[] args) {  // Método principal, punto de entrada de la aplicación.
        // Se crea un objeto ProcessBuilder para ejecutar el programa Ej40_1.
        ProcessBuilder pb = new ProcessBuilder("java", "ejercicios.Ej40_1");

        // Establece el directorio donde se encuentran las clases compiladas (en este caso, se usa ".\\build\\classes").
        pb.directory(new File(".\\build\\classes"));

        // Redirige los errores del proceso hijo al flujo de salida estándar del padre.
        pb.redirectErrorStream(true);

        Process p = null;  // Inicializa la variable del proceso, que será utilizada más adelante.

        try {
            // Inicia el proceso hijo, que ejecutará la clase Ej40_1.
            p = pb.start();

            // Espera a que el proceso hijo termine su ejecución.
            p.waitFor();
            //La línea waitFor() es fundamental para asegurar que el padre y el hijo se sincronicen adecuadamente. Sin ella, podrías enfrentar problemas relacionados con la lectura de la salida del proceso hijo.
            //Al comentar o no esta línea, cambias el comportamiento del proceso, lo que puede tener un impacto significativo en cómo se ejecuta tu aplicación.

            // Verifica el código de salida del proceso hijo; si no es cero, se imprime un mensaje de error.
            if (p.exitValue() != 0) {
                System.out.println("Error " + p.exitValue());
            }

            // Obtiene el flujo de entrada del proceso hijo, donde se puede leer la salida del programa ejecutado.
            InputStream is = p.getInputStream();

            // Se declara una variable para almacenar los caracteres leídos del flujo de entrada.
            int c;

            // Lee carácter por carácter hasta que no haya más datos disponibles en el flujo de entrada.
            while ((c = is.read()) != -1) {
                System.out.print((char) c);  // Imprime cada carácter leído.
            }
        } catch (Exception e) {  // Manejo de excepciones en caso de errores durante la ejecución.
            e.printStackTrace();  // Imprime el seguimiento de la pila en caso de una excepción.
        }
    }
}
