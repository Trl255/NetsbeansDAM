/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HilosExcutorService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author josea
 */
public class Tarea16 implements Runnable{

	private File fOrigen;
	private String dirDestino;
	public Tarea16(File fOrigen, String dirDestino)
	{
		this.fOrigen=fOrigen;
		this.dirDestino=dirDestino;
	}
	
	@Override
	public void run() {
		String fDestino =dirDestino + "/" + fOrigen.getName();
		FileInputStream fis = null;
		FileOutputStream fos = null; 

		int numBytes=0;
		byte[] arrBytes = new byte[1024];
		try{
			fis = new FileInputStream(fOrigen);
			fos = new FileOutputStream(fDestino,false); 

			while ((numBytes=fis.read(arrBytes)) > 0) 
			{
				fos.write(arrBytes);
				Thread.sleep(1);
			}
		} 
		catch(Exception e) {
			  e.printStackTrace();
			}
		finally{
			try{
			    if(fis!=null)
			    {
			      fis.close();
			      fos.close();
			    }
			  }
			  catch(Exception e) {
			    e.printStackTrace();
			  }
			}
   

		
	}
	
	
}