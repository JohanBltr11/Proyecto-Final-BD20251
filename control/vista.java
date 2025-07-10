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
                JOptionPane.showMessageDialog(this, "Acceso concedido", "Bienvenido Gerente", JOptionPane.INFORMATION_MESSAGE);
                mostrarMenuPrincipalGerente(); // Aquí va el menú después del login
            } else if(usuario.equals("recepcion") && clave.equals("0809")){
                JOptionPane.showMessageDialog(this, "Acceso concedido", "Bienvenido a la Recepción", JOptionPane.INFORMATION_MESSAGE);
                mostrarMenuPrincipalRecepcion();
            } else{
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
            JOptionPane.showMessageDialog(menu, "Abrir CRUD Empleados", "Información", JOptionPane.INFORMATION_MESSAGE);
        });

        btnCRUDservicios.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de servicios
            JOptionPane.showMessageDialog(menu, "Abrir CRUD Servicios", "Información", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(menu, "Abrir CRUD Clientes", "Información", JOptionPane.INFORMATION_MESSAGE);
        });

        btnConsultas.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de servicios
            JOptionPane.showMessageDialog(menu, "Mostrar Consultas", "Información", JOptionPane.INFORMATION_MESSAGE);
        });

    

        menu.add(panel);
        menu.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new vista().setVisible(true);
        });
    }
}
