/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author josea
 */
public class DoctorDAO {

    public List<Doctor> mostrarDoctores() {
        //SELECT `Doctor_No`, `Hospital_Cod`, `Apellido`, `Especialidad` FROM `doctor`

        List<Doctor> doctores = new ArrayList<>();
        String sql = "SELECT Doctor_No, Hospital_Cod, Apellido,Especialidad "
                + "FROM doctor";

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("doctorNo"),
                        rs.getInt("hospitalCod"),
                        rs.getString("apellido"),
                        rs.getString("especialidad")
                );
                doctores.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctores;

    }

}
