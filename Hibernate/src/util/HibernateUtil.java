/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static SessionFactory sessionFactory;

    static {
        try {
            // Configuramos Hibernate
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");  // Asegúrate de que el archivo esté en el lugar correcto

            // Aquí obtenemos el SessionFactory a partir de la configuración
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            // Manejamos excepciones en caso de error
            throw new ExceptionInInitializerError(ex);
        }
        
    }
    
    
    
    

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    
}