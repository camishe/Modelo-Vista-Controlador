
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
public class Profesor extends Persona{
    private String materia;
    private String departamento;
    private double salario;
    
    public Profesor(String nombre, String cedula, String correo, String materia, String departamento, double salario) {
        super(nombre, cedula, correo);
        this.materia = materia;
        this.departamento = departamento;
        this.salario = salario;
    }
    
    // Getters específicos
    public String getMateria() { return materia; }
    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }
    
    // Setters específicos
    public void setMateria(String materia) { this.materia = materia; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public void setSalario(double salario) { this.salario = salario; }
    
    @Override
    public String getTipo() {
        return "Profesor";
    }
    
    @Override
    public String getInformacionEspecifica() {
        return "Materia: " + materia + ", Departamento: " + departamento + ", Salario: $" + salario;
    }
    
    @Override
    public String toString() {
        return super.toString() + "," + materia + "," + departamento + "," + salario;
    }
}
