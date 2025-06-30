
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegistroWindow extends JDialog{
    private MainWindow parent;
    private Sesion sesion;
    private JTextField txtNombre, txtCedula, txtCorreo;
    private JComboBox<String> cmbTipo;
    private JPanel panelEspecifico;
    private JTextField txtMateria, txtDepartamento, txtSalario;
    private JTextField txtCarrera, txtSemestre, txtPromedio;
    private JButton btnGuardar, btnCancelar;
    
    public RegistroWindow(MainWindow parent, Sesion sesion) {
        super(parent, "Registrar Persona", true);
        this.parent = parent;
        this.sesion = sesion;
        initComponents();
        setupLayout();
        setupEvents();
    }
    
    private void initComponents() {
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        txtNombre = new JTextField(20);
        txtCedula = new JTextField(20);
        txtCorreo = new JTextField(20);
        cmbTipo = new JComboBox<>(new String[]{"Profesor", "Alumno"});
        
        // Campos específicos para Profesor
        txtMateria = new JTextField(20);
        txtDepartamento = new JTextField(20);
        txtSalario = new JTextField(20);
        
        // Campos específicos para Alumno
        txtCarrera = new JTextField(20);
        txtSemestre = new JTextField(20);
        txtPromedio = new JTextField(20);
        
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        
        panelEspecifico = new JPanel(new GridBagLayout());
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Campos comunes
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0; mainPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; mainPanel.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; mainPanel.add(new JLabel("Cédula:"), gbc);
        gbc.gridx = 1; mainPanel.add(txtCedula, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; mainPanel.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1; mainPanel.add(txtCorreo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; mainPanel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; mainPanel.add(cmbTipo, gbc);
        
        // Panel específico
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panelEspecifico, gbc);
        
        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Inicializar panel específico
        updateSpecificPanel();
    }
    
    private void setupEvents() {
        cmbTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSpecificPanel();
            }
        });
        
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPersona();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void updateSpecificPanel() {
        panelEspecifico.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        String tipo = (String) cmbTipo.getSelectedItem();
        
        if ("Profesor".equals(tipo)) {
            gbc.gridx = 0; gbc.gridy = 0; panelEspecifico.add(new JLabel("Materia:"), gbc);
            gbc.gridx = 1; panelEspecifico.add(txtMateria, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1; panelEspecifico.add(new JLabel("Departamento:"), gbc);
            gbc.gridx = 1; panelEspecifico.add(txtDepartamento, gbc);
            
            gbc.gridx = 0; gbc.gridy = 2; panelEspecifico.add(new JLabel("Salario:"), gbc);
            gbc.gridx = 1; panelEspecifico.add(txtSalario, gbc);
        } else if ("Alumno".equals(tipo)) {
            gbc.gridx = 0; gbc.gridy = 0; panelEspecifico.add(new JLabel("Carrera:"), gbc);
            gbc.gridx = 1; panelEspecifico.add(txtCarrera, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1; panelEspecifico.add(new JLabel("Semestre:"), gbc);
            gbc.gridx = 1; panelEspecifico.add(txtSemestre, gbc);
            
            gbc.gridx = 0; gbc.gridy = 2; panelEspecifico.add(new JLabel("Promedio:"), gbc);
            gbc.gridx = 1; panelEspecifico.add(txtPromedio, gbc);
        }
        
        panelEspecifico.revalidate();
        panelEspecifico.repaint();
    }
    
    private void guardarPersona() {
        try {
            String nombre = txtNombre.getText().trim();
            String cedula = txtCedula.getText().trim();
            String correo = txtCorreo.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();
            
            if (nombre.isEmpty() || cedula.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Persona persona = null;
            
            if ("Profesor".equals(tipo)) {
                String materia = txtMateria.getText().trim();
                String departamento = txtDepartamento.getText().trim();
                String salarioStr = txtSalario.getText().trim();
                
                if (materia.isEmpty() || departamento.isEmpty() || salarioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                double salario = Double.parseDouble(salarioStr);
                persona = new Profesor(nombre, cedula, correo, materia, departamento, salario);
                
            } else if ("Alumno".equals(tipo)) {
                String carrera = txtCarrera.getText().trim();
                String semestreStr = txtSemestre.getText().trim();
                String promedioStr = txtPromedio.getText().trim();
                
                if (carrera.isEmpty() || semestreStr.isEmpty() || promedioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int semestre = Integer.parseInt(semestreStr);
                double promedio = Double.parseDouble(promedioStr);
                persona = new Alumno(nombre, cedula, correo, carrera, semestre, promedio);
            }
            
            if (persona != null) {
                sesion.guardarPersona(persona);
                JOptionPane.showMessageDialog(this, "Persona registrada exitosamente");
                limpiarCampos();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valores numéricos inválidos", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtMateria.setText("");
        txtDepartamento.setText("");
        txtSalario.setText("");
        txtCarrera.setText("");
        txtSemestre.setText("");
        txtPromedio.setText("");
    }
}
