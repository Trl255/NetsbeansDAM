/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author josea
 */
public class Tarea30 implements Callable<List<String>> {

    private File fOrigen;
    private String dirDestino;

    public Tarea30(File fOrigen, String dirDestino) {
        this.fOrigen = fOrigen;
        this.dirDestino = dirDestino;
    }

    @Override
    public List<String> call() {
        String fDestino = dirDestino + "/" + "copia_"+fOrigen.getName();
        List<String> res = new ArrayList<>();
        
        
        try ( FileInputStream fis = new FileInputStream(fOrigen);  FileOutputStream fos = new FileOutputStream(fDestino)) {

            byte[] arrBytes = new byte[1024];
            int numBytes;
            while ((numBytes = fis.read(arrBytes)) > 0) {
                fos.write( arrBytes, 0, numBytes);
            }
            res.add(fOrigen.getName());
            res.add("Copia completada");
        } catch (Exception e) {
            res.add(fOrigen.getName());
            res.add("Error al copiar");
        }
        return res;
    }
}
