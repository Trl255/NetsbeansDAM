/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instituto;

import java.util.List;

/**
 *
 * @author josea
 */
public class Profesor {

    private int id;
    private int edad;
    private String nombre;
    private String materia;
    private List<Curso> cursos;

    public Profesor() {
    }

    public Profesor(int id, int edad, String nombre, String materia, List<Curso> cursos) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.materia = materia;
        this.cursos = cursos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Profesor con nombre: " + nombre;
    }

}
