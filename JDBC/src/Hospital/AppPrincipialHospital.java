/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author josea
 */
public class AppPrincipialHospital {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res = "";
        AtomicBoolean salir = new AtomicBoolean(false);
        DoctorDAO modelDoctorDao = new DoctorDAO();
        List<Doctor> doctores = new ArrayList<>();

        doctores = modelDoctorDao.mostrarDoctores();

        for (Doctor doctor : doctores) {
            System.out.println(doctor);
        }

        System.out.println("");
        String[] opcionesMenu = {
            "1-Insertar un doctor",
            "2-Actualizar Apellido y especialidad de un doctor",
            "3-Eliminar Doctor",
            "4-Buscar un doctor",
            "5-Salir"
        };

        // System.out.println(opcionesMenu);
        while (!salir.get()) {
            // Definir las acciones (Runnable)
            Runnable[] acciones = {
                // Acción 1 INSERTAR DOCTOR
                () -> {
                    Doctor doc = DoctorDAO.newDoctor();
                    System.out.println(doc != null ? "Se ha insertado Correctamente " + doc.toString() : "No se ha podido guardar en la Base de datos");

                },
                // Acción 2 ACTUALIZAR DOCTOR
                () -> {

                    //DoctorDAO.mostrarCodigoHospitales();
                    DoctorDAO.updatedDoctor();

                },
                // Acción 3 (ELIMINAR UN DOCTOR)
                () -> {

                    modelDoctorDao.deleteDoctor();

                },
                // Acción 4 (BUSCAR UN DOCTOR)

                () -> {
                    int entrada = 0;
                    Doctor doc = null; // Usa la instancia

                    do {
                        entrada = Metodos.numberInteger(sc, "Introduzca el codigo del doctor ( o pulse \"0\" para salir.)");

                        if (entrada == 0) {
                            System.out.println("Operacion cancelada");
                            break;

                        } else {

                            doc = modelDoctorDao.find(entrada);

                            if (doc != null) {
                                System.out.println("Se ha encontrado registro del doctor " + doc.getDoctorNo() + "\n" + doc.toString());
                            } else {
                                System.out.println("No se ha encontrado registro del codigo introducido " + "\"" + entrada + "\"");
                            }
                        }

                    } while (doc == null);

                },
                () -> Metodos.salirAtomicBoolean(salir)// Acción para salir
            };

            // Llamar al método mostrarMenuRunnable
            Metodos.mostrarMenuRunnable(opcionesMenu.length, opcionesMenu, acciones);

        }

    }
}
