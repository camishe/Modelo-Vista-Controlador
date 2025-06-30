
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
public abstract class Persona {
    protected String nombre;
    protected String cedula;
    protected String correo;
    
    public Persona(String nombre, String cedula, String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getCorreo() { return correo; }
    
    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    // Método abstracto para polimorfismo
    public abstract String getTipo();
    public abstract String getInformacionEspecifica();
    
    @Override
    public String toString() {
        return nombre + "," + cedula + "," + correo;
    }
}
