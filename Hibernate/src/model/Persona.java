/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.*;

/**
 *
 * @author josea SELECT `Doctor_No`, `Hospital_Cod`, `Apellido`, `Especialidad`
 * FROM `doctor` WHERE 1
 */
@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "f_nac")
    private java.sql.Date f_nac;

    @Column(name = "altura")
    private double altura;

    @Column(name = "peso")
    private double peso;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public java.sql.Date getF_nac() {
        return f_nac;
    }

    public void setF_nac(java.sql.Date f_nac) {
        this.f_nac = f_nac;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre
                + "\nFecha Nac: " + f_nac + ", Altura: " + altura
                + ", Peso: " + peso+"\n";
    }
}
