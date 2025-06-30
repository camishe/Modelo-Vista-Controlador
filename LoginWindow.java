
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame{
    private JTextField txtCedula;
    private JComboBox<String> cmbTipo;
    private JButton btnLogin;
    private Sesion sesion;
    
    public LoginWindow() {
        sesion = new Sesion();
        initComponents();
        setupLayout();
        setupEvents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestión - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        txtCedula = new JTextField(15);
        cmbTipo = new JComboBox<>(new String[]{"Profesor", "Alumno"});
        btnLogin = new JButton("Iniciar Sesión");
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Título
        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN DE PERSONAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainPanel.add(lblTitulo, gbc);
        
        // Cédula
        gbc.gridwidth = 1; gbc.insets = new Insets(10, 0, 10, 10);
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Cédula:"), gbc);
        gbc.gridx = 1; gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(txtCedula, gbc);
        
        // Tipo
        gbc.gridx = 0; gbc.gridy = 2; gbc.insets = new Insets(10, 0, 10, 10);
        mainPanel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; gbc.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(cmbTipo, gbc);
        
        // Botón
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(btnLogin, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupEvents() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText().trim();
                String tipo = (String) cmbTipo.getSelectedItem();
                
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginWindow.this, 
                        "Por favor ingrese la cédula", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (sesion.iniciarSesion(cedula, tipo)) {
                    JOptionPane.showMessageDialog(LoginWindow.this, 
                        "Bienvenido " + sesion.getUsuarioActivo().getNombre());
                    
                    MainWindow mainWindow = new MainWindow(sesion);
                    mainWindow.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, 
                        "Usuario no encontrado o tipo incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
