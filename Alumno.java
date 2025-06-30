
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
public class Alumno extends Persona{
    private String carrera;
    private int semestre;
    private double promedio;
    
    public Alumno(String nombre, String cedula, String correo, String carrera, int semestre, double promedio) {
        super(nombre, cedula, correo);
        this.carrera = carrera;
        this.semestre = semestre;
        this.promedio = promedio;
    }
    
    // Getters específicos
    public String getCarrera() { return carrera; }
    public int getSemestre() { return semestre; }
    public double getPromedio() { return promedio; }
    
    // Setters específicos
    public void setCarrera(String carrera) { this.carrera = carrera; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    public void setPromedio(double promedio) { this.promedio = promedio; }
    
    @Override
    public String getTipo() {
        return "Alumno";
    }
    
    @Override
    public String getInformacionEspecifica() {
        return "Carrera: " + carrera + ", Semestre: " + semestre + ", Promedio: " + promedio;
    }
    
    @Override
    public String toString() {
        return super.toString() + "," + carrera + "," + semestre + "," + promedio;
    }
}
