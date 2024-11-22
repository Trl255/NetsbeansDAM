package HilosLanzarTarea;



import HilosLanzarTarea.Tarea10;

public class Ej10 {

	public static void main(String[] args) {
		try {
			//lanzo 5 tareas
			for(int i=1;i<6;i++)
			{
				Tarea10 tarea =new Tarea10(i*10,"Tarea "+i);
				Thread t = new Thread(tarea);
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
