package hibernate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import dao.PersonaDAO;
import java.util.concurrent.atomic.AtomicBoolean;
import model.Persona;
import static util.Metodos.mostrarMenuRunnable;
import static util.Metodos.salirAtomicBoolean;

/**
 *
 * @author IFC_302
 */
public class Hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PersonaDAO modelPersonaDao = new PersonaDAO();
        AtomicBoolean salir = new AtomicBoolean(false);

        System.out.println("");
        String[] opcionesMenu = {
            "1-Mostrar listado de registros",
            "2-Insertar un persona",
            "3-Actualizar una persona",
            "4-Eliminar persona",
            "5-Buscar un persona",
            "6-Salir"
        };

        while (!salir.get()) {
            // Definir las acciones (Runnable)
            Runnable[] acciones = {
                // Acción 1 LISTAMOS A TODAS LAS PERSONAS.
                () -> {
                    PersonaDAO.listarPersonas();
                },
                // Acción 2 INSERTAR PERSONA
                () -> {

                    Persona persona = modelPersonaDao.newPersona();

                    System.out.println(persona != null ? "Se ha insertado Correctamente " + persona.toString() : "No se ha podido guardar en la Base de datos");

                },
                // Acción 3 ACTUALIZAR PERSONA
                () -> {
                    PersonaDAO.UpdatePersona();

                },
                // Acción 4 (ELIMINAR UN PERSONA)

                () -> {

                    modelPersonaDao.deletePersona();
                },
                // Acción 5 (BUSCAR UN PERSONA)

                () -> {

                    Persona persona = modelPersonaDao.findPersona();

                    System.out.println(persona == null
                            ? "No se ha encontrado registro"
                            : "Persona encontrada con ID " + persona.getId() + "\n" + persona.toString());
                },
                // Accion 6 (SALIR)
                () -> salirAtomicBoolean(salir)// Acción para salir
            };

            // Llamar al método mostrarMenuRunnable
            mostrarMenuRunnable(opcionesMenu.length, opcionesMenu, acciones);

        }

    }
}
