package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class vista extends JFrame {

    public vista() {
        setTitle("Gestión de Hotel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // Aumentamos a 5 filas

        JLabel label = new JLabel("Menú Principal", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);

        JButton btnClientes = new JButton("Gestionar Clientes");
        JButton btnHabitaciones = new JButton("Gestionar Habitaciones");
        JButton btnReservas = new JButton("Gestionar Reservas");
        JButton btnRegistrarCliente = new JButton("Registrar Cliente");

        panel.add(btnClientes);
        panel.add(btnHabitaciones);
        panel.add(btnReservas);
        panel.add(btnRegistrarCliente);

        add(panel);

        // Acción para el botón de clientes
        btnClientes.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Aquí iría la gestión de clientes.");
        });

        // Acción para el botón registrar cliente
        btnRegistrarCliente.addActionListener(e -> mostrarFormularioRegistro());
    }

    private void mostrarFormularioRegistro() {
        JDialog dialogo = new JDialog(this, "Registrar Cliente", true);
        dialogo.setSize(400, 400);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(8, 2, 5, 5));

        JTextField cedula = new JTextField();
        JTextField primerN = new JTextField();
        JTextField segundoN = new JTextField();
        JTextField primerA = new JTextField();
        JTextField segundoA = new JTextField();
        JTextField carrera = new JTextField();
        JTextField calle = new JTextField();
        JTextField numero = new JTextField();

        formulario.add(new JLabel("Cédula:"));
        formulario.add(cedula);
        formulario.add(new JLabel("Primer Nombre:"));
        formulario.add(primerN);
        formulario.add(new JLabel("Segundo Nombre:"));
        formulario.add(segundoN);
        formulario.add(new JLabel("Primer Apellido:"));
        formulario.add(primerA);
        formulario.add(new JLabel("Segundo Apellido:"));
        formulario.add(segundoA);
        formulario.add(new JLabel("Carrera:"));
        formulario.add(carrera);
        formulario.add(new JLabel("Calle:"));
        formulario.add(calle);
        formulario.add(new JLabel("Número:"));
        formulario.add(numero);

        dialogo.add(formulario, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            // Aquí podrías guardar los datos, por ahora solo los mostramos
            String datos = String.format(
                "Cédula: %s\nPrimer Nombre: %s\nSegundo Nombre: %s\nPrimer Apellido: %s\nSegundo Apellido: %s\nCarrera: %s\nCalle: %s\nNúmero: %s",
                cedula.getText(), primerN.getText(), segundoN.getText(),
                primerA.getText(), segundoA.getText(), carrera.getText(),
                calle.getText(), numero.getText()
            );
            JOptionPane.showMessageDialog(dialogo, datos, "Datos del Cliente", JOptionPane.INFORMATION_MESSAGE);
            dialogo.dispose();
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);
        dialogo.add(panelBoton, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new vista().setVisible(true);
        });
    }
}
