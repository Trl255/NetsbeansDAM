/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instituto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 *
 * @author josea
 */


public class JsonUtil {
    public static <T> T leerJson(String nombreArchivo, Type tipoClase) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(nombreArchivo)) {
            return gson.fromJson(reader, tipoClase);
        }
    }
}

