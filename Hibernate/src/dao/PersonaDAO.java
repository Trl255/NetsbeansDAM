package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Persona;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.Metodos;

/**
 *
 * @author josea
 */
public class PersonaDAO {

    static Scanner sc = new Scanner(System.in);

    public void InsertarPersona(Persona persona) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(persona);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

    }

    public static List<Persona> listarPersonas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Persona> personas = session.createQuery("from Persona", Persona.class).list();
            for (Persona persona : personas) {
                System.out.println(persona);
            }
            return personas;
        }

    }

    public Persona newPersona() {
        Transaction transaction = null;

        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(Metodos.solicitudInformacion(sc, "Nombre de la persona.", 5).toUpperCase());
        nuevaPersona.setF_nac(Metodos.solicitudFecha(sc, "la fecha de nacimiento"));
        nuevaPersona.setAltura(Metodos.solicitudDoubleRango(sc, " altura ", 0.5, 3));
        nuevaPersona.setPeso(Metodos.solicitudDoubleRango(sc, "su peso", 30, 400));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(nuevaPersona);
            transaction.commit();

            System.out.println("Persona insertada con éxito.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nuevaPersona;
    }

    public static void UpdatePersona() {
        List<Persona> listaPersona = new ArrayList<>();
        Persona personaSeleccionada = null;

        boolean encontrado;

        listaPersona = listarPersonas();

        System.out.println("--MENU ACTUALIZAR PERSONA--\nLista de personatores disponibles para ACTUALIZAR\n");
        for (int i = 0; listaPersona.size() > i; i++) {
            System.out.println(i + 1 + "- " + listaPersona.get(i));
        }

        while (personaSeleccionada == null) {

            System.out.println("\n--OPCIONES---");
            int id = Metodos.numberInteger(sc, " codigo de persona ( O pulse \"0\" para salir)");
            if (id == 0) {
                System.out.println("Operacion Cancelada");
                return;
            }

            encontrado = false;

            for (Persona persona : listaPersona) {
                if (persona.getId() == id) {
                    personaSeleccionada = persona;
                    encontrado = true;
                    break;
                }

            }
            if (!encontrado) {

                System.out.println("No se ha encontrado ninguna persona con ese codigo: " + id);
                System.out.println("Lista de personatores disponibles para ACTUALIZAR\n");
                for (int i = 0; listaPersona.size() > i; i++) {
                    System.out.println(i + 1 + "- " + listaPersona.get(i));
                }

            } else {

                System.out.println("Registro encontrado: " + personaSeleccionada.toString());
                //Asignamos y validamos valores
                personaSeleccionada.setNombre(Metodos.solicitudInformacion(sc, "Nombre de la persona\n registro anterior " + personaSeleccionada.getNombre(), 5).toUpperCase());
                personaSeleccionada.setF_nac(Metodos.solicitudFecha(sc, "la fecha de nacimiento\n registro anterior " + personaSeleccionada.getF_nac()));
                personaSeleccionada.setAltura(Metodos.solicitudDoubleRango(sc, "altura de la persona\n registro anterior " + personaSeleccionada.getAltura(), 0.5, 3));
                personaSeleccionada.setPeso(Metodos.solicitudDoubleRango(sc, "peso \n registro anterior " + personaSeleccionada.getPeso(), 30, 400));

                //Una vez validado creamos conexión y enviamos datos.
                Transaction transaction = null;

                try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                    transaction = session.beginTransaction();
                    session.update(personaSeleccionada);
                    transaction.commit();

                    System.out.println("Persona Actualiza correctamente: " + personaSeleccionada.toString());

                } catch (Exception e) {

                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }

            }

        }
    }

    public Persona findPersona() {

        PersonaDAO personaDAO = new PersonaDAO(); // Inicializar el DAO
        Persona persona = null;
        int opcion;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Bucle para solicitar un ID válido
            while (persona == null) {
                opcion = Metodos.numberInteger(sc, "Introduzca el ID de la persona (0 para salir)");

                if (opcion == 0) {
                    System.out.println("Operación cancelada. Volviendo al menú...");
                    return persona;
                }

                persona = session.get(Persona.class, opcion);

                if (persona == null) {
                    System.out.println("No se encontro ninguna persona con ID " + opcion + ". Intente de nuevo.");
                }
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return persona;
    }

    public void deletePersona() {
        PersonaDAO personaDAO = new PersonaDAO(); // Inicializar el DAO
        Persona persona = null;
        int opcion;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Bucle para solicitar un ID válido
            while (persona == null) {
                opcion = Metodos.numberInteger(sc, "Introduzca el ID de la persona (0 para salir)");

                if (opcion == 0) {
                    System.out.println("Operación cancelada. Volviendo al menú...");
                    return;

                }

                persona = session.get(Persona.class,
                        opcion);

                if (persona == null) {
                    System.out.println("No se encontró ninguna persona con ID " + opcion + ". Intente de nuevo.");
                }
            }

            // Confirmar eliminación
            int confirmacion = Metodos.numberInteger(sc, "\n1 - Confirmar eliminacion del registro: " + persona.getId()+", "+persona.getNombre() + "\n0 - Cancelar");

            if (confirmacion == 1) {
                session.delete(persona);
                transaction.commit();
                System.out.println("Persona eliminada correctamente.");
            } else {
                System.out.println("Operación cancelada. Volviendo al menú...");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
