package PERSONAS;



import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;


public class JsonPersonasMain {

    public static void main(String[] args) throws FileNotFoundException {
        boolean repetir = true;
        try {
            //PREPARAMOS EL JSON
            Gson gson = new Gson();
            Reader file = new FileReader("personas.json");
            
            //OBTENEMOS LA INFORMACIÓN EN JSON
            List<Persona> listPersonas
                    = gson.fromJson(file, new TypeToken<List<Persona>>() {
                    }.getType());

            //RECORREMOS EL JSON QUE SON DISTINTOS DE NULL
            if (listPersonas != null) {
                for (Persona persona : listPersonas) {
                    // rellenar aqui
                    if (true) {
                        
                        System.out.println("\nAlumnos con color de ojo \"BROWN:\"");
                        repetir = false;
                    }
                    //1-  FILTRAMOS POR LOS OJOS DE COLOR BROWN
                    if (persona.getEyeColor().equalsIgnoreCase("BROWN")) {
                        System.out.println("Nombre: " + persona.getName() + "\n");

                    }
                    //ACCEDEMOS A LA LIST DE AMIGOS MEDIANTE UN NUEVO LIST Y OBTENEMOS EL OBJETO PERSONA MEDIANTE OBJECT.GETFRIENDS()
                    List<Friends> amigos = persona.getFriends();

                    //RECORREMOS LA LISTA AMIGOS QUE SEAN DISTINTAS A NULL
                    if (amigos != null) {
                        for (Friends amigo : amigos) {
                            
                            //2-  FILTRAMOS POR AQUELLOS QUE SEAN IGUAL A SEXO HOMBRES
                            if (amigo.getGender().equalsIgnoreCase("male")) {
                                System.out.println("Persona " + persona.getName() + " Amigos: " + amigo.getName() + " Sexo: " + amigo.getGender());
                            }

                        }

                    }

                }
            }

        } catch (JsonSyntaxException e) {
            System.err.println("JsonSyntaxException: " + e.getMessage());
        }

    }

}
