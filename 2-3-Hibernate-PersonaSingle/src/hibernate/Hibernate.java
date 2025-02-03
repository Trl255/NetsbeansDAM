/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hibernate;

import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

/**
 *
 * @author IFC_302
 */
public class Hibernate {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // Configuración de Hibernate
        SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml") // Cargar configuración desde el archivo hibernate.cfg.xml
            .addAnnotatedClass(Persona.class) // Indicar que Persona es una clase de entidad
            .buildSessionFactory();

        // Crear una nueva sesión
        Session session = factory.getCurrentSession();

        try {
            // Crear un nuevo objeto Persona
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Jose perez");
            nuevaPersona.setF_nac(Date.valueOf("1980-05-15"));
            nuevaPersona.setAltura(1.75);
            nuevaPersona.setPeso(70.5);

            // Iniciar una transacción
            session.beginTransaction();

            // Guardar el objeto Persona
            session.save(nuevaPersona);

            // Confirmar la transacción
            session.getTransaction().commit();

            System.out.println("¡Registro guardado con éxito! ID: " + nuevaPersona.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close(); // Cerrar la SessionFactory
        }
    }
}
