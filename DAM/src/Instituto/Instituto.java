/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instituto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josea
 */
public class Instituto {

    private String instituto;

    private List<Profesor> profesores;

    public Instituto() {
    }

    public Instituto(String instituto, List<Profesor> profesores) {
        this.instituto = instituto;
        this.profesores = new ArrayList<>();

    }

    public Instituto(String instituto) {
        this.instituto = instituto;
    }

   public String getInstituto() {
        return instituto;
    }

    public void setInstituto(String instituto) {
        this.instituto = instituto;
    }

    // Cambiar a plural para reflejar que devuelve múltiples profesores
    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }
}