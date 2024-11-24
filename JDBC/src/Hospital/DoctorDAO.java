/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class DoctorDAO {

    static Scanner sc = new Scanner(System.in);

    public List<Doctor> mostrarDoctores() {
        //SELECT Doctor_No, Hospital_Cod, Apellido, Especialidad FROM doctor

        List<Doctor> doctores = new ArrayList<>();
        String sql = "SELECT Doctor_No, Hospital_Cod, Apellido,Especialidad "
                + "FROM doctor";

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("Doctor_No"),
                        rs.getInt("Hospital_Cod"),
                        rs.getString("Apellido"),
                        rs.getString("Especialidad")
                );
                doctores.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctores;

    }

    public static List<Integer> mostrarCodigoHospitales() {
        //SELECT Doctor_No, Hospital_Cod, Apellido, Especialidad FROM doctor
        List<Integer> codigosHospitales = new ArrayList<>();

        String sql = "SELECT DISTINCT Hospital_Cod "
                + "FROM doctor";

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                codigosHospitales.add(rs.getInt("Hospital_Cod"));

            }
            if (!codigosHospitales.isEmpty()) {
                System.out.println("Códigos de Hospitales dispinibles");
                codigosHospitales.forEach(codigo -> System.out.println(codigo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigosHospitales;
    }

    public static List<Doctor> showinsertedDoctors() {
        List<Doctor> doctores = new ArrayList<>();
        String sql = "SELECT Doctor_No, Hospital_Cod, Apellido, Especialidad "
                + "  FROM doctor "
                + "  WHERE Doctor_No>982";

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                Doctor doc = null;
                while (rs.next()) {
                    doc = new Doctor(
                            rs.getInt("Doctor_No"),
                            rs.getInt("Hospital_Cod"),
                            rs.getString("Apellido"),
                            rs.getString("Especialidad"));

                    doctores.add(doc);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return doctores;

    }

    public static void updatedDoctor() {
        int filasActualizada = 0;
        List<Doctor> doctores = new ArrayList<>();
        Doctor doc = new Doctor();

        doctores = showinsertedDoctors();
        System.out.println("--MENU ACTUALIZAR DOCTOR--\nLista de doctores disponibles para ACTUALIZAR\n");
        for (int i = 0; doctores.size() > i; i++) {
            System.out.println(i + 1 + "- " + doctores.get(i));
        }

        Doctor doctorSeleccionado = null;

        while (doctorSeleccionado == null) {

            System.out.println("\n--OPCIONES---");
            int doctorNo = Metodos.numberInteger(sc, " numero de doctor ( O pulse \"0\" para salir)");
            if (doctorNo == 0) {
                System.out.println("Operacion Cancelada");
                return;
            }

            for (Doctor doctor : doctores) {
                if (doctor.getDoctorNo() == doctorNo) {
                    doctorSeleccionado = doctor;
                    break;
                }

            }
            if (doctorSeleccionado == null) {

                System.out.println("No se ha encontrado ningún doctor con el codigo: " + doctorNo);
                System.out.println("Lista de doctores disponibles para ACTUALIZAR\n");
                for (int i = 0; doctores.size() > i; i++) {
                    System.out.println(i + 1 + "- " + doctores.get(i));
                }

            } else {
                doc.setApellido(Metodos.solicitudInformacion(sc, "Apellido del doctor", 5).toUpperCase());
                doc.setEspecialidad(Metodos.solicitudInformacion(sc, "Especialidad del doctor", 5).toUpperCase());

                System.out.println("Introduzca uno de los siguientes codigos de hospitales ");
                List<Integer> listaCodigosHosp = mostrarCodigoHospitales();
                int codHospUsuario = 0;
                boolean codigoHospEsvalido = false;

                while (codigoHospEsvalido == false) {
                    codHospUsuario = Metodos.numberInteger(sc, "CODIGO DE HOPITAL");
                    if (listaCodigosHosp.contains(codHospUsuario)) {
                        doc.setHospitalCod(codHospUsuario);

                        codigoHospEsvalido = true;
                    }
                }

                //               doc.setHospitalCod(Metodos.numberInteger(sc, "Codigo del hospital"));
                String sql = "UPDATE doctor "
                        + "SET Hospital_Cod=?, "
                        + "Apellido=?, "
                        + "Especialidad=? "
                        + "WHERE Doctor_No= ? ";

                try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                    // Establecer los parámetros en el PreparedStatement
                    stmt.setInt(1, doc.getHospitalCod());
                    stmt.setString(2, doc.getApellido());
                    stmt.setString(3, doc.getEspecialidad());
                    stmt.setInt(4, doctorSeleccionado.getDoctorNo());

                    filasActualizada = stmt.executeUpdate(); // Aquí no necesitas un ResultSet

                    // Ejecutar la actualización
                    if (filasActualizada > 0) {
                        // Si se realizó correctamente el insert, obtener las claves generadas
                        /*try (ResultSet rs = stmt.getGeneratedKeys()) {
                            if (rs.next()) {
                                int generatedKey = rs.getInt(1); // Obtiene la primera clave generada
                                System.out.println("Clave generada: " + generatedKey);
                            }
                        }
                    } else {
                        System.out.println("No se insertaron filas.");
                        doc = null;*/
                        System.out.println("Actualización realizada");
                    } else {
                        System.out.println("No se ha podido Actualizar");
                    }

                } catch (SQLException e) {
                    //e.printStackTrace();
                    System.out.println("No se ha podido Guardar, disculpe las molestias");
                }

            }

        }

    }

    public static Doctor newDoctor() {

        int filasActualizada = 0;
        Doctor doc = new Doctor();
        boolean codigoEquivocado = false;
        boolean codigoHospEsvalido = false;

        doc.setApellido(Metodos.solicitudInformacion(sc, "Nombre del doctor", 5).toUpperCase());
        doc.setEspecialidad(Metodos.solicitudInformacion(sc, "Especialidad del doctor", 4).toUpperCase());

        System.out.println("Introduzca uno de los siguientes codigos de hospitales ");
        List<Integer> listaCodigosHosp = mostrarCodigoHospitales();
        int codHospUsuario = 0;

        while (codigoHospEsvalido == false) {
            if (codigoEquivocado == false) {

                codHospUsuario = Metodos.numberInteger(sc, "Codigo del hospital");
                codigoEquivocado = true;

            } else if (codigoEquivocado == true) {
                codHospUsuario = Metodos.numberInteger(sc, "por favor elija un codigo de hospital de la lista anterior");

            }
            if (listaCodigosHosp.contains(codHospUsuario)) {
                doc.setHospitalCod(codHospUsuario);

                codigoHospEsvalido = true;
            }
        }

        //doc.setHospitalCod(Metodos.numberInteger(sc, "Código del hospital"));
        String sql = "INSERT INTO doctor("
                + "Hospital_Cod, "
                + "Apellido, "
                + "Especialidad) "
                + "VALUES (?,?,?) ";

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros en el PreparedStatement
            stmt.setInt(1, doc.getHospitalCod());
            stmt.setString(2, doc.getApellido());
            stmt.setString(3, doc.getEspecialidad());

            filasActualizada = stmt.executeUpdate(); //EJECUTO EL SQL, RETORNA FILAS AFECTADAS EN SQL

            // Ejecutar la actualización
            if (filasActualizada > 0) {
                // Si se realizó correctamente el insert, obtener las claves generadas
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedKey = rs.getInt(1); // Obtiene la primera clave generada
                        System.out.println("Clave generada: " + generatedKey);

                        doc.setDoctorNo(generatedKey);
                    }
                }
            } else {
                System.out.println("No se insertaron filas.");

            }

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("No se ha podido Guardar, disculpe las molestias");
        }

        return doc;
    }

    public Doctor find(Integer idDoctor) {

        if (idDoctor == null) {
            idDoctor = Metodos.numberInteger(sc, "el numero de un Doctor");

        }
        

        String sql = "SELECT Doctor_No, Hospital_Cod, Apellido, Especialidad "
                + "  FROM doctor "
                + "  WHERE Doctor_No= ?";

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer el valor del parámetro (?)
            stmt.setInt(1, idDoctor); // El índice 1 representa el primer parámetro (Doctor_No)

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                Doctor doc = null;
                if (rs.next()) {
                    doc = new Doctor();
                    doc.setDoctorNo(rs.getInt("Doctor_No"));
                    doc.setHospitalCod(rs.getInt("Hospital_Cod"));
                    doc.setApellido(rs.getString("Apellido"));
                    doc.setEspecialidad(rs.getString("Especialidad"));
                }

                return doc;

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public int deleteDoctor() {
        Doctor doc = null;
        int confirmacion;
        int filasAfectadas = 0;

        do {
            // Buscar el doctor

            confirmacion = Metodos.numberInteger(sc, "Introduzca el numero del doctor \"0\" para cancelar");
            doc = find(confirmacion);

            if (doc != null) {
                // Mostrar información del doctor encontrado
                System.out.println("Se encontró el siguiente doctor: " + doc.toString());
                confirmacion = Metodos.numberInteger(sc, "\n1-Para confirmar eliminación.\n0-Para cancelar");

                if (confirmacion == 1) {
                    break;
                } else if (confirmacion == 0) {
                    System.out.println("Operación cancelada. Volviendo al menú....");
                    return 0;

                }
            } else if (confirmacion == 0) {
                System.out.println("Operación cancelada. Volviendo al menú....");
                return 0;

            } else {
                // Si no se encuentra, solicitar nuevamente
                //  confirmacion = Metodos.numberInteger(sc, "No se ha encontrado el doctor. Introduzca '0' para salir o cualquier otro número para intentar de nuevo.");
                System.out.println("Doctor no encontrado. ");
            }
        } while (true);

        String sql = "DELETE "
                + "FROM DOCTOR "
                + "WHERE Doctor_No=?";

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            {
                stmt.setInt(1, doc.getDoctorNo());

                filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Se ha eliminado correctamente " + doc.getDoctorNo());
                } else {
                    System.out.println("Error al intentar eliminar " + doc.getDoctorNo());
                }

            }

        } catch (Exception ex) {

        }
        return filasAfectadas;

    }
}
