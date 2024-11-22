/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosLanzarTarea;

/**
 *
 * @author josea
 */
public class Tarea10 implements Runnable{

	int n;
	String nombre;
	public Tarea10(int n,String nombre)
	{
		this.n=n;
		this.nombre=nombre;
	}
	@Override
	public void run() {
		for(int i=n;i<101;i++)
			System.out.println(nombre + " " + i);
	}
	

}
