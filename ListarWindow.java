
package com.mycompany.lab1u2amnuniez;

/**
 *
 * @author mishe
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ListarWindow extends JDialog{
    private MainWindow parent;
    private Sesion sesion;
    private JComboBox<String> cmbFiltro;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar, btnCerrar;
    
    public ListarWindow(MainWindow parent, Sesion sesion) {
        super(parent, "Listar Personas", true);
        this.parent = parent;
        this.sesion = sesion;
        initComponents();
        setupLayout();
        setupEvents();
        cargarDatos();
    }
    
    private void initComponents() {
        setSize(700, 500);
        setLocationRelativeTo(parent);
        
        cmbFiltro = new JComboBox<>(new String[]{"Todos", "Profesor", "Alumno"});
        btnActualizar = new JButton("Actualizar");
        btnCerrar = new JButton("Cerrar");
        
        // Configurar tabla
        String[] columnas = {"Tipo", "Nombre", "Cédula", "Correo", "Información Específica"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel superior
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Filtrar por:"));
        topPanel.add(cmbFiltro);
        topPanel.add(btnActualizar);
        
        // Panel central con tabla
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Personas"));
        
        // Panel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnCerrar);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void setupEvents() {
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
        
        cmbFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
        
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void cargarDatos() {
        try {
            // Limpiar tabla
            modeloTabla.setRowCount(0);
            
            String filtro = (String) cmbFiltro.getSelectedItem();
            List<Persona> personas;
            
            if ("Todos".equals(filtro)) {
                personas = sesion.cargarUsuarios();
            } else {
                personas = sesion.obtenerPersonasPorTipo(filtro);
            }
            
            // Aplicar filtros de autorización según el usuario activo
            Persona usuarioActivo = sesion.getUsuarioActivo();
            if (usuarioActivo != null) {
                // Los profesores pueden ver todo
                // Los alumnos solo pueden ver otros alumnos
                if (usuarioActivo.getTipo().equals("Alumno")) {
                    personas.removeIf(p -> p.getTipo().equals("Profesor"));
                }
            }
            
            // Llenar tabla
            for (Persona persona : personas) {
                Object[] fila = {
                    persona.getTipo(),
                    persona.getNombre(),
                    persona.getCedula(),
                    persona.getCorreo(),
                    persona.getInformacionEspecifica()
                };
                modeloTabla.addRow(fila);
            }
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar datos: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
       }
    }
}
