/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

import java.util.List;

/**
 *
 * @author josea
 */
public class AppPrincipialHospital {
    public static void main(String[] args) {
        
    
      DoctorDAO doctorDAO = new DoctorDAO();

        List<Doctor> doctores = doctorDAO.mostrarDoctores();

        for (Doctor doctor : doctores) {
            System.out.println(doctor);
        }
        System.out.println("");
        String[] opcionesMenu = {
            "1-Insertar un doctor",
            "2-Actualizar Apellido y especialidad de un doctor",
            "3-Eliminar Doctor",
            "4-Mostrar todos los doctores",
            "5-Salir"
        };

        boolean salir = false;
        // System.out.println(opcionesMenu);
        
        Doctor doc;
        while (!salir) {
            // Definir las acciones (Runnable)
            Runnable[] acciones = {
                () -> System.out.println(DoctorDAO.find()==null?"No hay doctor con el registro introducido": DoctorDAO.find()), // Acción 1
                () -> Metodos.accion2(), // Acción 2
                () -> Metodos.accion3(), // 1Acción 2
                () -> Metodos.accion4(), // Acción 2
                () -> Metodos.salir()// Acción para salir
            };
            if (opcionesMenu.length == 4) {
                salir = true;
            }
            // Llamar al método mostrarMenuRunnable
            Metodos.mostrarMenuRunnable(opcionesMenu.length, opcionesMenu, acciones);

        }

    }
}