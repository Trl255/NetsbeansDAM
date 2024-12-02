/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService.Repartidor;

import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author josea
 */
public class RepartidorCallable implements Callable {

    private Repartidor repartidor;

    public RepartidorCallable(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    @Override
    public Long call() throws Exception {
        long tiempoInicio = System.currentTimeMillis();

        for (Pedido p : repartidor.getPedidos()) {
            System.out.println(repartidor.getNombre() + " esta entregando " + p.toString());
        }
        long tiempoFin = System.currentTimeMillis();
        return tiempoFin - tiempoInicio;
    }

}
