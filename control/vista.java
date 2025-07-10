package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class vista extends JFrame {

    public vista() {
        setTitle("Gestión de Hotel - Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("Clave:");
        JPasswordField txtClave = new JPasswordField();

        JButton btnAcceder = new JButton("Acceder");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblClave);
        panel.add(txtClave);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnAcceder);

        add(panel);

        // Acción del botón Acceder
        btnAcceder.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String clave = new String(txtClave.getPassword());

            // Aquí podrías validar usuario y clave (por ahora solo mostramos)
            if (usuario.equals("admin") && clave.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Acceso concedido", "Bienvenido Gerente",
                        JOptionPane.INFORMATION_MESSAGE);
                mostrarMenuPrincipalGerente(); // Aquí va el menú después del login
                this.dispose();
            } else if (usuario.equals("recepcion") && clave.equals("0809")) {
                JOptionPane.showMessageDialog(this, "Acceso concedido", "Bienvenido a la Recepción",
                        JOptionPane.INFORMATION_MESSAGE);
                mostrarMenuPrincipalRecepcion();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void mostrarMenuPrincipalGerente() {
        // Crear nueva ventana o reemplazar contenido
        JFrame menu = new JFrame("Menú Principal Gerente");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Menú Principal Gerente", SwingConstants.CENTER));

        JButton btnCRUDempleados = new JButton("CRUD Empleados");
        JButton btnCRUDservicios = new JButton("CRUD Servicios");

        panel.add(btnCRUDempleados);
        panel.add(btnCRUDservicios);

        btnCRUDempleados.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de empleados
            CRUDempleados();
            this.dispose();
        });

        btnCRUDservicios.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de servicios
            CRUDservicios();
        });

        menu.add(panel);
        menu.setVisible(true);
    }

    private void mostrarMenuPrincipalRecepcion() {
        // Crear nueva ventana o reemplazar contenido
        JFrame menu = new JFrame("Menú Principal Recepción");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Menú Principal Recepcion", SwingConstants.CENTER));

        JButton btnCRUDclientes = new JButton("CRUD Clientes");
        JButton btnConsultas = new JButton("Consultas");

        panel.add(btnCRUDclientes);
        panel.add(btnConsultas);

        btnCRUDclientes.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de empleados
            CRUDclientes();
        });

        btnConsultas.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de servicios
            Consultas();
        });

        menu.add(panel);
        menu.setVisible(true);
    }

    private void CRUDempleados() {
        JFrame menu = new JFrame("CRUD Empleados");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregar = new JButton("Agregar Empleado");
        JButton btnModificar = new JButton("Modificar Empleado");
        JButton btnEliminar = new JButton("Eliminar Empleado");
        JButton btnListar = new JButton("Listar Empleados");

        panel.add(btnAgregar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnListar);

        menu.add(panel);           // 🔧 Importante
        menu.setVisible(true);     // 🔧 Muy importante
    }

    private void CRUDservicios() {
        JFrame menu = new JFrame("CRUD Servicios");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregar = new JButton("Agregar Servicio");
        JButton btnModificar = new JButton("Modificar Servicio");
        JButton btnEliminar = new JButton("Eliminar Servicio");
        JButton btnListar = new JButton("Listar Servicios");

        panel.add(btnAgregar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnListar);

        menu.add(panel);           // Agrega el panel al frame
        menu.setVisible(true);     // Muestra la ventana
    }


    private void CRUDclientes() {
        JFrame menu = new JFrame("CRUD Clientes");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnModificar = new JButton("Modificar Cliente");
        JButton btnEliminar = new JButton("Eliminar Cliente");
        JButton btnListar = new JButton("Listar Clientes");

        panel.add(btnAgregar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnListar);

        menu.add(panel);           // Agrega el panel al frame
        menu.setVisible(true);     // Muestra la ventana
    }

    private void Consultas() {
        // Aquí podrías implementar las consultas
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new vista().setVisible(true);
        });
    }
}
