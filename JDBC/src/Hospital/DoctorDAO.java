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
        //SELECT `Doctor_No`, `Hospital_Cod`, `Apellido`, `Especialidad` FROM `doctor`

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

    public static Doctor find() {

        int idDoctor = Metodos.numberInteger(sc, "Introduzca el número de un Doctor");

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

}