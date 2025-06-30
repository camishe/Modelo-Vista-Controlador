
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import com.mycompany.lab1u2amnunie.Sesion;
//import com.gestion.personas.Persona;

public class MainWindow extends JFrame{
    private Sesion sesion;
    private JLabel lblUsuario;
    private JButton btnRegistrar, btnListar, btnCerrarSesion;
    private JPanel contentPanel;
    
    public MainWindow(Sesion sesion) {
        this.sesion = sesion;
        initComponents();
        setupLayout();
        setupEvents();
        updateUserInfo();
    }
    
    private void initComponents() {
        setTitle("Sistema de Gestión - Panel Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        lblUsuario = new JLabel();
        btnRegistrar = new JButton("Registrar Persona");
        btnListar = new JButton("Listar Personas");
        btnCerrarSesion = new JButton("Cerrar Sesión");
        contentPanel = new JPanel(new BorderLayout());
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel superior - información del usuario
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(230, 230, 230));
        
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblUsuario, BorderLayout.WEST);
        topPanel.add(btnCerrarSesion, BorderLayout.EAST);
        
        // Panel de navegación
        JPanel navPanel = new JPanel(new FlowLayout());
        navPanel.add(btnRegistrar);
        navPanel.add(btnListar);
        
        // Panel principal
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel lblBienvenida = new JLabel("Seleccione una opción del menú superior");
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.ITALIC, 16));
        contentPanel.add(lblBienvenida, BorderLayout.CENTER);
        
        add(topPanel, BorderLayout.NORTH);
        add(navPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.SOUTH);
    }
    
    private void setupEvents() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistroWindow registroWindow = new RegistroWindow(MainWindow.this, sesion);
                registroWindow.setVisible(true);
            }
        });
        
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarWindow listarWindow = new ListarWindow(MainWindow.this, sesion);
                listarWindow.setVisible(true);
            }
        });
        
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(MainWindow.this, 
                    "¿Está seguro que desea cerrar sesión?", "Confirmar", 
                    JOptionPane.YES_NO_OPTION);
                
                if (opcion == JOptionPane.YES_OPTION) {
                    sesion.cerrarSesion();
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.setVisible(true);
                    dispose();
                }
            }
        });
    }
    
    private void updateUserInfo() {
        if (sesion.verificarSesion()) {
            Persona usuario = sesion.getUsuarioActivo();
            lblUsuario.setText("Usuario: " + usuario.getNombre() + " (" + usuario.getTipo() + ")");
        }
    }
}
