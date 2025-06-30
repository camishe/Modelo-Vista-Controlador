
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.io.IOException;
import javax.swing.UnsupportedLookAndFeelException;

public class Lab1U2AMNuniez {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("No se pudo configurar el Look and Feel: " + e.getMessage());
        }
        
        // Crear datos de prueba si no existen
        crearDatosPrueba();
        
        // Iniciar aplicación
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }
    
    private static void crearDatosPrueba() {
        try {
            Sesion sesionTemp = new Sesion();
            
            // Crear algunos profesores de prueba
            Profesor prof1 = new Profesor("Dr. Juan Pérez", "1234567890", 
                "juan.perez@universidad.edu", "Matemáticas", "Ciencias", 2500.00);
            Profesor prof2 = new Profesor("Dra. María García", "0987654321", 
                "maria.garcia@universidad.edu", "Física", "Ciencias", 2800.00);
            
            // Crear algunos alumnos de prueba
            Alumno alumno1 = new Alumno("Carlos López", "1122334455", 
                "carlos.lopez@estudiante.edu", "Ingeniería", 5, 8.5);
            Alumno alumno2 = new Alumno("Ana Rodríguez", "5544332211", 
                "ana.rodriguez@estudiante.edu", "Medicina", 3, 9.2);
            
            // Guardar datos de prueba
            sesionTemp.guardarPersona(prof1);
            sesionTemp.guardarPersona(prof2);
            sesionTemp.guardarPersona(alumno1);
            sesionTemp.guardarPersona(alumno2);
            
            System.out.println("Datos de prueba creados exitosamente");
            System.out.println("Usuarios disponibles:");
            System.out.println("Profesores: 1234567890 (Dr. Juan Pérez), 0987654321 (Dra. María García)");
            System.out.println("Alumnos: 1122334455 (Carlos López), 5544332211 (Ana Rodríguez)");
            
        } catch (IOException e) {
            System.err.println("Error al crear datos de prueba: " + e.getMessage());
        }
    }
}
