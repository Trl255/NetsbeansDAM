/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

/**
 *
 * @author josea SELECT `Doctor_No`, `Hospital_Cod`, `Apellido`, `Especialidad`
 * FROM `doctor` WHERE 1
 */
public class Doctor {

    private int doctorNo;
    private int hospitalCod;
    private String apellido;
    private String especialidad;

    public Doctor(int doctorNo, int hospitalCod, String apellido, String especialidad) {
        this.doctorNo = doctorNo;
        this.hospitalCod = hospitalCod;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public Doctor() {
    }
    

    public int getDoctorNo() {
        return doctorNo;
    }

    public int getHospitalCod() {
        return hospitalCod;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setDoctorNo(int doctorNo) {
        this.doctorNo = doctorNo;
    }

    public void setHospitalCod(int hospitalCod) {
        this.hospitalCod = hospitalCod;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doctor other = (Doctor) obj;
        if (this.doctorNo != other.doctorNo) {
            return false;
        }
        return this.hospitalCod == other.hospitalCod;
    }

    @Override
    public String toString() {
        return "Doctor " + doctorNo
                + "\nhospitalCod=" + hospitalCod
                + "\nApellido=" + apellido
                + "\nEspecialidad=" + especialidad + '}';

    }

}
