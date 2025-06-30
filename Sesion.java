
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
import java.io.*;
import java.util.*;

public class Sesion {
    private Persona usuarioActivo;
    private boolean sesionActiva;
    static final String ARCHIVO_USUARIOS = "usuarios.csv";
    
    public Sesion() {
        this.sesionActiva = false;
        this.usuarioActivo = null;
    }
    
    public boolean iniciarSesion(String cedula, String tipo) {
        try {
            List<Persona> usuarios = cargarUsuarios();
            for (Persona usuario : usuarios) {
                if (usuario.getCedula().equals(cedula) && usuario.getTipo().equals(tipo)) {
                    this.usuarioActivo = usuario;
                    this.sesionActiva = true;
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
        return false;
    }
    
    public void cerrarSesion() {
        this.usuarioActivo = null;
        this.sesionActiva = false;
    }
    
    public boolean verificarSesion() {
        return sesionActiva && usuarioActivo != null;
    }
    
    public Persona getUsuarioActivo() {
        return usuarioActivo;
    }
    
    public void guardarPersona(Persona persona) throws IOException {
        try (FileWriter writer = new FileWriter(ARCHIVO_USUARIOS, true)) {
            writer.write(persona.getTipo() + "," + persona.toString() + "\n");
        }
    }
    
    public List<Persona> cargarUsuarios() throws IOException {
        List<Persona> usuarios = new ArrayList<>();
        File archivo = new File(ARCHIVO_USUARIOS);
        
        if (!archivo.exists()) {
            return usuarios;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    String tipo = datos[0];
                    String nombre = datos[1];
                    String cedula = datos[2];
                    String correo = datos[3];
                    
                    if (tipo.equals("Profesor") && datos.length >= 7) {
                        String materia = datos[4];
                        String departamento = datos[5];
                        double salario = Double.parseDouble(datos[6]);
                        usuarios.add(new Profesor(nombre, cedula, correo, materia, departamento, salario));
                    } else if (tipo.equals("Alumno") && datos.length >= 7) {
                        String carrera = datos[4];
                        int semestre = Integer.parseInt(datos[5]);
                        double promedio = Double.parseDouble(datos[6]);
                        usuarios.add(new Alumno(nombre, cedula, correo, carrera, semestre, promedio));
                    }
                }
            }
        }
        return usuarios;
    }
    
    public List<Persona> obtenerPersonasPorTipo(String tipo) throws IOException {
        List<Persona> todasLasPersonas = cargarUsuarios();
        List<Persona> personasFiltradas = new ArrayList<>();
        
        for (Persona persona : todasLasPersonas) {
            if (persona.getTipo().equals(tipo)) {
                personasFiltradas.add(persona);
            }
        }
        return personasFiltradas;
    }
}
