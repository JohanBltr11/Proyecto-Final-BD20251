package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DAO.*;
import modelo.*;
import java.sql.*;
import java.util.List;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;

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
                mostrarMenuPrincipalGerente();
                this.dispose();
            } else if (usuario.equals("recepcion") && clave.equals("0809")) {
                JOptionPane.showMessageDialog(this, "Acceso concedido", "Bienvenido a la Recepción",
                        JOptionPane.INFORMATION_MESSAGE);
                mostrarMenuPrincipalRecepcion();
                this.dispose();
            } else if (usuario.equals("personalS") && clave.equals("8833")) {
                JOptionPane.showMessageDialog(this, "Acceso concedido", "Bienvenido Personal de Servicios",
                        JOptionPane.INFORMATION_MESSAGE);
                mostrarMenuPrincipalPersonalS();
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
        JButton btnConsumo = new JButton("Consumo de Servicios");
        JButton btnCRUDclientes = new JButton("CRUD Clientes");

        panel.add(btnCRUDempleados);
        panel.add(btnCRUDservicios);
        panel.add(btnConsumo);
        panel.add(btnCRUDclientes);

        btnCRUDempleados.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de empleados
            CRUDEmpleadosGerente();
        });

        btnCRUDservicios.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de servicios
            CRUDservicios();
        });

        btnConsumo.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el consumo de servicios
            ConsumoServicios();
        });

        btnCRUDclientes.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de clientes
            CRUDclientesGerente();
        });

        menu.add(panel);
        menu.setVisible(true);
    }

    private void CRUDEmpleadosGerente() {
        JFrame menu = new JFrame("CRUD Empleados");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
        JButton btnModificar = new JButton("Modificar Empleado");
        JButton btnEliminar = new JButton("Eliminar Empleado");
        JButton btnListar = new JButton("Listar Empleados");

        panel.add(btnAgregarEmpleado);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnListar);

        menu.add(panel);
        menu.setVisible(true);

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        PersonaDAO personaDAO = new PersonaDAO();

        // Acción: Agregar Empleado
        btnAgregarEmpleado.addActionListener(e -> {
            JTextField cedula = new JTextField();
            JTextField primerN = new JTextField();
            JTextField segundoN = new JTextField();
            JTextField primerA = new JTextField();
            JTextField segundoA = new JTextField();
            JTextField carrera = new JTextField();
            JTextField calle = new JTextField();
            JTextField numero = new JTextField();
            JComboBox<String> cargo = new JComboBox<>(new String[] {
                    "Recepcionista", "Conserje", "Personal de Cocina",
                    "Personal de Limpieza", "Gerente de Hotel", "Entrenador de Deportes"
            });
            JTextField idArea = new JTextField();

            Object[] inputs = {
                    "Cédula:", cedula,
                    "Primer Nombre:", primerN,
                    "Segundo Nombre:", segundoN,
                    "Primer Apellido:", primerA,
                    "Segundo Apellido:", segundoA,
                    "Carrera:", carrera,
                    "Calle:", calle,
                    "Número:", numero,
                    "Cargo:", cargo,
                    "ID Área:", idArea
            };

            int result = JOptionPane.showConfirmDialog(null, inputs, "Agregar Empleado", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    long ced = Long.parseLong(cedula.getText());
                    String car = (String) cargo.getSelectedItem();
                    int areaId = Integer.parseInt(idArea.getText());

                    // Crear Persona
                    Persona persona = new Persona();
                    persona.setCedula(ced);
                    persona.setPrimerN(primerN.getText());
                    persona.setSegundoN(segundoN.getText());
                    persona.setPrimerA(primerA.getText());
                    persona.setSegundoA(segundoA.getText());
                    persona.setCarrera(carrera.getText());
                    persona.setCalle(calle.getText());
                    persona.setNumero(numero.getText());

                    personaDAO.insertar(persona);

                    // Crear Empleado
                    Area area = new Area();
                    area.setIdArea(areaId);
                    Empleado empleado = new Empleado(ced, car, area);

                    empleadoDAO.insertar(empleado);
                    JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "⚠️ Datos numéricos inválidos.");
                }
            }
        });

        // Acción: modificcar empleado
        btnModificar.addActionListener(e -> {
            String inputCedula = JOptionPane.showInputDialog(null, "Ingrese la cédula del empleado a modificar:");
            if (inputCedula == null || inputCedula.isEmpty())
                return;

            try {
                long cedula = Long.parseLong(inputCedula);

                // Obtener datos existentes
                Persona persona = personaDAO.obtenerPersonaPorCedula(cedula);
                Empleado empleado = empleadoDAO.buscarPorCedula(cedula);

                if (persona == null || empleado == null) {
                    JOptionPane.showMessageDialog(null, "❌ Empleado no encontrado.");
                    return;
                }

                // Crear campos prellenados
                JTextField primerN = new JTextField(persona.getPrimerN());
                JTextField segundoN = new JTextField(persona.getSegundoN());
                JTextField primerA = new JTextField(persona.getPrimerA());
                JTextField segundoA = new JTextField(persona.getSegundoA());
                JTextField carrera = new JTextField(persona.getCarrera());
                JTextField calle = new JTextField(persona.getCalle());
                JTextField numero = new JTextField(persona.getNumero());
                JComboBox<String> cargo = new JComboBox<>(new String[] {
                        "Recepcionista", "Conserje", "Personal de Cocina",
                        "Personal de Limpieza", "Gerente de Hotel", "Entrenador de Deportes"
                });
                cargo.setSelectedItem(empleado.getCargo());

                JTextField idArea = new JTextField(String.valueOf(empleado.getIdArea()));

                Object[] inputs = {
                        "Primer Nombre:", primerN,
                        "Segundo Nombre:", segundoN,
                        "Primer Apellido:", primerA,
                        "Segundo Apellido:", segundoA,
                        "Carrera:", carrera,
                        "Calle:", calle,
                        "Número:", numero,
                        "Cargo:", cargo,
                        "ID Área:", idArea
                };

                int result = JOptionPane.showConfirmDialog(null, inputs, "Modificar Empleado",
                        JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Actualizar Persona
                    persona.setPrimerN(primerN.getText());
                    persona.setSegundoN(segundoN.getText());
                    persona.setPrimerA(primerA.getText());
                    persona.setSegundoA(segundoA.getText());
                    persona.setCarrera(carrera.getText());
                    persona.setCalle(calle.getText());
                    persona.setNumero(numero.getText());

                    personaDAO.actualizar(persona);

                    // Actualizar Empleado
                    empleado.setCargo((String) cargo.getSelectedItem());

                    Area nuevaArea = new Area();
                    nuevaArea.setIdArea(Integer.parseInt(idArea.getText()));
                    empleado.setArea(nuevaArea);

                    empleadoDAO.actualizar(empleado);
                    JOptionPane.showMessageDialog(null, "Empleado modificafo .");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "⚠️ Cédula o ID Área inválidos.");
            }
        });

        // Acción: eliminar empleado
        btnEliminar.addActionListener(e -> {
            String inputCedula = JOptionPane.showInputDialog(null, "Ingrese la cédula del empleado a eliminar:");
            if (inputCedula == null || inputCedula.isEmpty())
                return;

            try {
                long cedula = Long.parseLong(inputCedula);

                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro de eliminar al empleado con cédula " + cedula + "?", "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION)
                    return;

                // Eliminar de Empleado
                empleadoDAO.eliminar(cedula);

                // Eliminar de Persona
                personaDAO.eliminar(cedula);

                JOptionPane.showMessageDialog(null, "✅ Empleado y persona eliminados correctamente.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "⚠️ Cédula inválida.");
            }
        });
        // Listar empleados
        btnListar.addActionListener(e -> {
            List<Empleado> empleados = empleadoDAO.listarTodos();

            if (empleados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "⚠️ No hay empleados registrados.");
                return;
            }

            String[] columnas = {
                    "Cédula", "Primer Nombre", "Primer Apellido",
                    "Cargo", "Área", "Carrera", "Calle", "Número"
            };

            Object[][] datos = new Object[empleados.size()][columnas.length];

            for (int i = 0; i < empleados.size(); i++) {
                Empleado emp = empleados.get(i);
                Persona p = emp.getPersona();
                Area a = emp.getArea();

                datos[i][0] = emp.getCedula();
                datos[i][1] = p != null ? p.getPrimerN() : "—";
                datos[i][2] = p != null ? p.getPrimerA() : "—";
                datos[i][3] = emp.getCargo();
                datos[i][4] = a != null ? a.getNombreArea() : "—";
                datos[i][5] = p != null ? p.getCarrera() : "—";
                datos[i][6] = p != null ? p.getCalle() : "—";
                datos[i][7] = p != null ? p.getNumero() : "—";
            }

            JTable tabla = new JTable(datos, columnas);
            JScrollPane scrollPane = new JScrollPane(tabla);

            // ✅ Crear un JFrame para mostrar la tabla con tamaño personalizado
            JFrame ventanaTabla = new JFrame("Lista de Empleados");
            ventanaTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaTabla.setSize(1000, 400); // ⬅️ Aquí defines el tamaño que quieras
            ventanaTabla.setLocationRelativeTo(null); // Centrado en pantalla
            ventanaTabla.add(scrollPane);
            ventanaTabla.setVisible(true);
        });

    }

    private void ConsumoServicios() {
        JFrame menu = new JFrame("Consumo de Servicios");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnRegistrarConsumo = new JButton("Registrar Consumo de Servicio");
        JButton btnListarConsumos = new JButton("Listar Consumos de Servicios");

        panel.add(btnRegistrarConsumo);
        panel.add(btnListarConsumos);

        btnRegistrarConsumo.addActionListener(e -> {
            registrarConsumo();
        });

        btnListarConsumos.addActionListener(e -> {
            listarConsumos();
        });

        menu.add(panel); // Agrega el panel al frame
        menu.setVisible(true); // Muestra la ventana
    }

    private void registrarConsumo() {

        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarClientes();
        List<Servicio> servicios = new ServicioDAO().listarTodos();

        // Crear array de cédulas para el combo
        Long[] cedulas = clientes.stream().map(Cliente::getCedula).toArray(Long[]::new);
        JComboBox<Long> comboCedula = new JComboBox<>(cedulas);

        JComboBox<Servicio> comboServicio = new JComboBox<>(servicios.toArray(new Servicio[0]));
        comboServicio.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Servicio) {
                    setText(((Servicio) value).getNombreDescriptivoServicio());
                }
                return this;
            }
        });

        JDialog dialog = new JDialog(this, "Registrar Consumo", true);
        dialog.setSize(350, 250);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblCedula = new JLabel("Cédula:");

        JLabel lblIdServicio = new JLabel("ID Servicio:");
        JTextField txtIdServicio = new JTextField();

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        dialog.add(lblCedula);
        dialog.add(comboCedula);
        dialog.add(lblIdServicio);
        dialog.add(comboServicio);
        dialog.add(new JLabel());
        dialog.add(new JLabel());
        dialog.add(btnRegistrar);
        dialog.add(btnCancelar);

        // Acción botón Registrar
        btnRegistrar.addActionListener(e -> {
            try {
                long cedula = (Long) comboCedula.getSelectedItem();
                Servicio servicio = (Servicio) comboServicio.getSelectedItem();

                Consumo consumo = new Consumo();
                consumo.setCedula(cedula);
                consumo.setIdServicio(servicio.getIdServicio());
                consumo.setFechaHora(LocalDateTime.now());

                ConsumoDAO dao = new ConsumoDAO();
                dao.insertar(consumo);

                JOptionPane.showMessageDialog(dialog, "✅ Consumo registrado exitosamente.");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "⚠️ Ingrese valores numéricos válidos.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "❌ Error al registrar consumo: " + ex.getMessage());
            }
        });

        // Acción botón Cancelar
        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void listarConsumos() {
        ConsumoDAO dao = new ConsumoDAO();
        List<Consumo> lista = dao.listarTodos();

        // Columnas de la tabla
        String[] columnas = { "Cédula", "ID Servicio", "Fecha y Hora" };

        // Convertir lista a matriz de objetos
        Object[][] datos = new Object[lista.size()][3];
        for (int i = 0; i < lista.size(); i++) {
            Consumo c = lista.get(i);
            datos[i][0] = c.getCedula();
            datos[i][1] = c.getIdServicio();
            datos[i][2] = c.getFechaHora().toString();
        }

        // Crear tabla
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Crear y mostrar diálogo
        JDialog dialog = new JDialog(this, "Listado de Consumos", true);
        dialog.setSize(500, 300);
        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialog.dispose());
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnCerrar);

        dialog.add(panelInferior, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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

        btnAgregar.addActionListener(e -> {
            agregarServicio();
        });
        btnModificar.addActionListener(e -> {
            modificarServicio();
        });
        btnEliminar.addActionListener(e -> {
            eliminarServicio();
        });
        btnListar.addActionListener(e -> {
            listarServicios();
        });

        menu.add(panel); // Agrega el panel al frame
        menu.setVisible(true); // Muestra la ventana
    }

    private void agregarServicio() {
        JFrame frame = new JFrame("Agregar Servicio");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panelito = new JPanel(new GridLayout(5, 2, 10, 10));
        panelito.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblIdServicio = new JLabel("Identificador del servicio:");
        JTextField txtIdServicio = new JTextField();

        JLabel lblCosto = new JLabel("Costo:");
        JTextField txtCosto = new JTextField();

        JLabel lblNombre = new JLabel("Nombre descriptivo:");
        JTextField txtNombre = new JTextField();

        JLabel lblDetalles = new JLabel("Detalles:");
        JTextField txtDetalles = new JTextField();

        JButton btnGuardar = new JButton("Guardar");

        panelito.add(lblIdServicio);
        panelito.add(txtIdServicio);
        panelito.add(lblCosto);
        panelito.add(txtCosto);
        panelito.add(lblNombre);
        panelito.add(txtNombre);
        panelito.add(lblDetalles);
        panelito.add(txtDetalles);
        panelito.add(new JLabel());
        panelito.add(btnGuardar);

        frame.add(panelito);
        frame.setVisible(true);

        // Aquí podrías agregar la lógica para guardar el servicio (DAO)
        btnGuardar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdServicio.getText());
                int costo = Integer.parseInt(txtCosto.getText());
                String nombre = txtNombre.getText();
                String detalles = txtDetalles.getText();

                Servicio s = new Servicio();
                s.setIdServicio(id);
                s.setCosto(costo);
                s.setNombreDescriptivoServicio(nombre);
                s.setDetalleServicio(detalles);

                ServicioDAO dao = new ServicioDAO();
                dao.insertar(s); // Asegúrate de que exista este método en tu DAO

                JOptionPane.showMessageDialog(frame, "✅ Servicio guardado correctamente.");
                frame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "⚠️ Datos inválidos. Verifica los campos numéricos.");
            }
        });
    }

    private void modificarServicio() {
        String inputId = JOptionPane.showInputDialog(null, "Ingrese el ID del servicio a modificar:");
        if (inputId == null || inputId.isEmpty())
            return;

        try {
            int idServicio = Integer.parseInt(inputId);
            ServicioDAO dao = new ServicioDAO();
            Servicio servicio = dao.buscarPorId(idServicio); // Asegúrate de que este método exista

            if (servicio == null) {
                JOptionPane.showMessageDialog(null, "❌ Servicio no encontrado.");
                return;
            }

            JTextField txtCosto = new JTextField(String.valueOf(servicio.getCosto()));
            JTextField txtNombre = new JTextField(servicio.getNombreDescriptivoServicio());
            JTextField txtDetalles = new JTextField(servicio.getDetalleServicio());

            Object[] inputs = {
                    "Costo:", txtCosto,
                    "Nombre descriptivo:", txtNombre,
                    "Detalles:", txtDetalles
            };

            int result = JOptionPane.showConfirmDialog(null, inputs, "Modificar Servicio",
                    JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                servicio.setCosto(Integer.parseInt(txtCosto.getText()));
                servicio.setNombreDescriptivoServicio(txtNombre.getText());
                servicio.setDetalleServicio(txtDetalles.getText());

                dao.actualizar(servicio); // Asegúrate de tener este método en tu ServicioDAO
                JOptionPane.showMessageDialog(null, "✅ Servicio modificado correctamente.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "⚠️ ID o campos numéricos inválidos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "❌ Error al modificar el servicio: " + ex.getMessage());
        }
    }

    private void eliminarServicio() {
        String inputId = JOptionPane.showInputDialog(null, "Ingrese el ID del servicio a eliminar:");
        if (inputId == null || inputId.isEmpty())
            return;

        try {
            int idServicio = Integer.parseInt(inputId);

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro de eliminar el servicio con ID " + idServicio + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION)
                return;

            ServicioDAO dao = new ServicioDAO();
            dao.eliminar(idServicio);

            JOptionPane.showMessageDialog(null, "✅ Servicio eliminado correctamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "⚠️ ID inválido.");
        }
    }

    private void listarServicios() {
        ServicioDAO dao = new ServicioDAO();
        List<Servicio> servicios = dao.listarTodos();

        if (servicios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ No hay servicios registrados.");
            return;
        }

        // Encabezados de columna
        String[] columnas = { "ID", "Costo", "Nombre Descriptivo", "Detalle" };

        // Datos para la tabla
        Object[][] datos = new Object[servicios.size()][4];
        for (int i = 0; i < servicios.size(); i++) {
            Servicio s = servicios.get(i);
            datos[i][0] = s.getIdServicio();
            datos[i][1] = s.getCosto();
            datos[i][2] = s.getNombreDescriptivoServicio();
            datos[i][3] = s.getDetalleServicio();
        }

        // Crear tabla
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Mostrar en un JFrame
        JFrame frame = new JFrame("Lista de Servicios");
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.add(scrollPane);
        frame.setVisible(true);
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
        JButton btnEstadohabitaciones = new JButton("Estado de Habitaciones");

        panel.add(btnCRUDclientes);
        panel.add(btnEstadohabitaciones);

        btnCRUDclientes.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de empleados
            CRUDclientes();
        });

        btnEstadohabitaciones.addActionListener(e -> {
            Estadohabitaciones();
        });

        menu.add(panel);
        menu.setVisible(true);
    }

    private void CRUDclientesGerente() {
        JFrame menu = new JFrame("CRUD Clientes");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        JButton btnListarClientes = new JButton("Listar Clientes");
        JButton btnEliminarCliente = new JButton("Eliminar Cliente");

        panel.add(btnAgregarCliente);
        panel.add(btnModificarCliente);
        panel.add(btnListarClientes);
        panel.add(btnEliminarCliente);

        menu.add(panel); // Agrega el panel al frame
        menu.setVisible(true); // Muestra la ventana

        ClienteDAO dao = new ClienteDAO();

        btnAgregarCliente.addActionListener(e -> {
            // Campos Persona
            JTextField cedula = new JTextField();
            JTextField primerN = new JTextField();
            JTextField segundoN = new JTextField();
            JTextField primerA = new JTextField();
            JTextField segundoA = new JTextField();
            JTextField carrera = new JTextField();
            JTextField calle = new JTextField();
            JTextField numero = new JTextField();

            // Campo Cliente
            JTextField correo = new JTextField();

            Object[] inputs = {
                    "Cédula:", cedula,
                    "Primer Nombre:", primerN,
                    "Segundo Nombre:", segundoN,
                    "Primer Apellido:", primerA,
                    "Segundo Apellido:", segundoA,
                    "Carrera:", carrera,
                    "Calle:", calle,
                    "Número:", numero,
                    "Correo Electrónico:", correo
            };

            int result = JOptionPane.showConfirmDialog(null, inputs, "Agregar Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    long ced = Long.parseLong(cedula.getText());

                    // Crear objeto Persona
                    Persona persona = new Persona();
                    persona.setCedula(ced);
                    persona.setPrimerN(primerN.getText());
                    persona.setSegundoN(segundoN.getText());
                    persona.setPrimerA(primerA.getText());
                    persona.setSegundoA(segundoA.getText());
                    persona.setCarrera(carrera.getText());
                    persona.setCalle(calle.getText());
                    persona.setNumero(numero.getText());

                    // Crear objeto Cliente
                    Cliente cliente = new Cliente();
                    cliente.setCedula(ced);
                    cliente.setCorreoElectronico(correo.getText());

                    // DAO
                    PersonaDAO personaDAO = new PersonaDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();

                    personaDAO.insertar(persona);
                    clienteDAO.insertarCliente(cliente);

                    JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + ex.getMessage());
                }
            }
        });

        btnModificarCliente.addActionListener(e -> {
            String cedulaStr = JOptionPane.showInputDialog("Ingrese la cédula del cliente a modificar:");
            if (cedulaStr != null) {
                try {
                    long cedula = Long.parseLong(cedulaStr);
                    PersonaDAO personaDAO = new PersonaDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();

                    Persona persona = personaDAO.obtenerPersonaPorCedula(cedula);
                    Cliente cliente = clienteDAO.obtenerClientePorCedula(cedula);

                    if (persona == null || cliente == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        return;
                    }

                    // Campos para editar
                    JTextField primerN = new JTextField(persona.getPrimerN());
                    JTextField segundoN = new JTextField(persona.getSegundoN());
                    JTextField primerA = new JTextField(persona.getPrimerA());
                    JTextField segundoA = new JTextField(persona.getSegundoA());
                    JTextField carrera = new JTextField(persona.getCarrera());
                    JTextField calle = new JTextField(persona.getCalle());
                    JTextField numero = new JTextField(persona.getNumero());
                    JTextField correo = new JTextField(cliente.getCorreoElectronico());

                    Object[] inputs = {
                            "Primer Nombre:", primerN,
                            "Segundo Nombre:", segundoN,
                            "Primer Apellido:", primerA,
                            "Segundo Apellido:", segundoA,
                            "Carrera:", carrera,
                            "Calle:", calle,
                            "Número:", numero,
                            "Correo Electrónico:", correo
                    };

                    int result = JOptionPane.showConfirmDialog(null, inputs, "Modificar Cliente",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        persona.setPrimerN(primerN.getText());
                        persona.setSegundoN(segundoN.getText());
                        persona.setPrimerA(primerA.getText());
                        persona.setSegundoA(segundoA.getText());
                        persona.setCarrera(carrera.getText());
                        persona.setCalle(calle.getText());
                        persona.setNumero(numero.getText());
                        cliente.setCorreoElectronico(correo.getText());

                        personaDAO.actualizar(persona);
                        clienteDAO.actualizarCliente(cliente);

                        JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnListarClientes.addActionListener(e -> {
            ClienteDAO clienteDAO = new ClienteDAO();
            List<Object[]> datos = clienteDAO.listarClientesParaTabla();

            if (datos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "⚠️ No hay clientes registrados.");
                return;
            }

            String[] columnas = { "Cédula", "Nombre", "Apellido", "Correo" };
            Object[][] tablaDatos = new Object[datos.size()][4];
            for (int i = 0; i < datos.size(); i++) {
                tablaDatos[i] = datos.get(i);
            }

            JTable tabla = new JTable(tablaDatos, columnas);
            JScrollPane scrollPane = new JScrollPane(tabla);

            JFrame tablaFrame = new JFrame("Lista de Clientes");
            tablaFrame.setSize(600, 300);
            tablaFrame.setLocationRelativeTo(null);
            tablaFrame.add(scrollPane);
            tablaFrame.setVisible(true);
        });
        btnEliminarCliente.addActionListener(e -> {
            String cedulaStr = JOptionPane.showInputDialog("Ingrese la cédula del cliente a eliminar:");
            if (cedulaStr != null) {
                try {
                    long cedula = Long.parseLong(cedulaStr);
                    dao.eliminarClienteTotal(cedula);
                    JOptionPane.showMessageDialog(null, "✅ Cliente eliminado correctamente.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "❌ Cédula inválida.");
                }
            }
        });

    }

    private void CRUDclientes() {
        JFrame menu = new JFrame("CRUD Clientes");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        JButton btnModificarCliente = new JButton("Modificar Cliente");
        JButton btnListarClientes = new JButton("Listar Clientes");

        panel.add(btnAgregarCliente);
        panel.add(btnModificarCliente);
        panel.add(btnListarClientes);

        menu.add(panel); // Agrega el panel al frame
        menu.setVisible(true); // Muestra la ventana

        ClienteDAO dao = new ClienteDAO();

        btnAgregarCliente.addActionListener(e -> {
            // Campos Persona
            JTextField cedula = new JTextField();
            JTextField primerN = new JTextField();
            JTextField segundoN = new JTextField();
            JTextField primerA = new JTextField();
            JTextField segundoA = new JTextField();
            JTextField carrera = new JTextField();
            JTextField calle = new JTextField();
            JTextField numero = new JTextField();

            // Campo Cliente
            JTextField correo = new JTextField();

            Object[] inputs = {
                    "Cédula:", cedula,
                    "Primer Nombre:", primerN,
                    "Segundo Nombre:", segundoN,
                    "Primer Apellido:", primerA,
                    "Segundo Apellido:", segundoA,
                    "Carrera:", carrera,
                    "Calle:", calle,
                    "Número:", numero,
                    "Correo Electrónico:", correo
            };

            int result = JOptionPane.showConfirmDialog(null, inputs, "Agregar Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    long ced = Long.parseLong(cedula.getText());

                    // Crear objeto Persona
                    Persona persona = new Persona();
                    persona.setCedula(ced);
                    persona.setPrimerN(primerN.getText());
                    persona.setSegundoN(segundoN.getText());
                    persona.setPrimerA(primerA.getText());
                    persona.setSegundoA(segundoA.getText());
                    persona.setCarrera(carrera.getText());
                    persona.setCalle(calle.getText());
                    persona.setNumero(numero.getText());

                    // Crear objeto Cliente
                    Cliente cliente = new Cliente();
                    cliente.setCedula(ced);
                    cliente.setCorreoElectronico(correo.getText());

                    // DAO
                    PersonaDAO personaDAO = new PersonaDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();

                    personaDAO.insertar(persona);
                    clienteDAO.insertarCliente(cliente);

                    JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + ex.getMessage());
                }
            }
        });

        btnModificarCliente.addActionListener(e -> {
            String cedulaStr = JOptionPane.showInputDialog("Ingrese la cédula del cliente a modificar:");
            if (cedulaStr != null) {
                try {
                    long cedula = Long.parseLong(cedulaStr);
                    PersonaDAO personaDAO = new PersonaDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();

                    Persona persona = personaDAO.obtenerPersonaPorCedula(cedula);
                    Cliente cliente = clienteDAO.obtenerClientePorCedula(cedula);

                    if (persona == null || cliente == null) {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                        return;
                    }

                    // Campos para editar
                    JTextField primerN = new JTextField(persona.getPrimerN());
                    JTextField segundoN = new JTextField(persona.getSegundoN());
                    JTextField primerA = new JTextField(persona.getPrimerA());
                    JTextField segundoA = new JTextField(persona.getSegundoA());
                    JTextField carrera = new JTextField(persona.getCarrera());
                    JTextField calle = new JTextField(persona.getCalle());
                    JTextField numero = new JTextField(persona.getNumero());
                    JTextField correo = new JTextField(cliente.getCorreoElectronico());

                    Object[] inputs = {
                            "Primer Nombre:", primerN,
                            "Segundo Nombre:", segundoN,
                            "Primer Apellido:", primerA,
                            "Segundo Apellido:", segundoA,
                            "Carrera:", carrera,
                            "Calle:", calle,
                            "Número:", numero,
                            "Correo Electrónico:", correo
                    };

                    int result = JOptionPane.showConfirmDialog(null, inputs, "Modificar Cliente",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        persona.setPrimerN(primerN.getText());
                        persona.setSegundoN(segundoN.getText());
                        persona.setPrimerA(primerA.getText());
                        persona.setSegundoA(segundoA.getText());
                        persona.setCarrera(carrera.getText());
                        persona.setCalle(calle.getText());
                        persona.setNumero(numero.getText());
                        cliente.setCorreoElectronico(correo.getText());

                        personaDAO.actualizar(persona);
                        clienteDAO.actualizarCliente(cliente);

                        JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnListarClientes.addActionListener(e -> {
            ClienteDAO clienteDAO = new ClienteDAO();
            List<Object[]> datos = clienteDAO.listarClientesParaTabla();

            if (datos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "⚠️ No hay clientes registrados.");
                return;
            }

            String[] columnas = { "Cédula", "Nombre", "Apellido", "Correo" };
            Object[][] tablaDatos = new Object[datos.size()][4];
            for (int i = 0; i < datos.size(); i++) {
                tablaDatos[i] = datos.get(i);
            }

            JTable tabla = new JTable(tablaDatos, columnas);
            JScrollPane scrollPane = new JScrollPane(tabla);

            JFrame tablaFrame = new JFrame("Lista de Clientes");
            tablaFrame.setSize(600, 300);
            tablaFrame.setLocationRelativeTo(null);
            tablaFrame.add(scrollPane);
            tablaFrame.setVisible(true);
        });

    }

    private void Estadohabitaciones() {
        JFrame menu = new JFrame("Estado de Habitaciones");
        menu.setSize(400, 300);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menu.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnHabitacionesLibres = new JButton("Habitaciones Libres");
        JButton btnOcupadas = new JButton("Habitaciones ocupadas");
        JButton btnmantenimiento = new JButton("Habitaciones en Mantenimiento");

        panel.add(btnHabitacionesLibres);
        panel.add(btnOcupadas);
        panel.add(btnmantenimiento);

        menu.add(panel); // Agrega el panel al frame
        menu.setVisible(true); // Muestra la ventana

        btnHabitacionesLibres.addActionListener(e -> {
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            List<Habitacion> libres = habitacionDAO.obtenerHabitacionesLibres();

            StringBuilder mensaje = new StringBuilder("Habitaciones Libres:\n\n");
            if (libres.isEmpty()) {
                mensaje.append("No hay habitaciones libres disponibles.");
            } else {
                for (Habitacion h : libres) {
                    mensaje.append("ID: ").append(h.getIdHabitacion())
                            .append(" - Tipo: ").append(h.getTipoHabitacion())
                            .append(" - Precio/Noche: $").append(h.getPrecioNoche())
                            .append("\n");
                }
            }

            JOptionPane.showMessageDialog(null, mensaje.toString(), "Habitaciones Libres",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        btnOcupadas.addActionListener(e -> {
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            List<Habitacion> ocupadas = habitacionDAO.obtenerHabitacionesOcupadas();

            StringBuilder mensaje = new StringBuilder("Habitaciones Ocupadas:\n\n");
            if (ocupadas.isEmpty()) {
                mensaje.append("No hay habitaciones ocupadas actualmente.");
            } else {
                for (Habitacion h : ocupadas) {
                    mensaje.append("ID: ").append(h.getIdHabitacion())
                            .append(" - Tipo: ").append(h.getTipoHabitacion())
                            .append(" - Precio/Noche: $").append(h.getPrecioNoche())
                            .append("\n");
                }
            }

            JOptionPane.showMessageDialog(null, mensaje.toString(), "Habitaciones Ocupadas",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        btnmantenimiento.addActionListener(e -> {
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            List<Habitacion> enMantenimiento = habitacionDAO.obtenerHabitacionesEnMantenimiento();

            StringBuilder mensaje = new StringBuilder("Habitaciones en Mantenimiento:\n\n");
            if (enMantenimiento.isEmpty()) {
                mensaje.append("No hay habitaciones en mantenimiento actualmente.");
            } else {
                for (Habitacion h : enMantenimiento) {
                    mensaje.append("ID: ").append(h.getIdHabitacion())
                            .append(" - Tipo: ").append(h.getTipoHabitacion())
                            .append(" - Precio/Noche: $").append(h.getPrecioNoche())
                            .append("\n");
                }
            }

            JOptionPane.showMessageDialog(null, mensaje.toString(), "Habitaciones en Mantenimiento",
                    JOptionPane.INFORMATION_MESSAGE);
        });

    }

    private void mostrarMenuPrincipalPersonalS() {
        // Crear nueva ventana o reemplazar contenido
        JFrame menup = new JFrame("Menú Principal Personal de Servicio");
        menup.setSize(400, 300);
        menup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menup.setLocationRelativeTo(null);

        JPanel panel3 = new JPanel(new GridLayout(4, 1, 10, 10));
        panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel3.add(new JLabel("Menú Principal Personal de Servicio", SwingConstants.CENTER));

        JButton btnEstdHab = new JButton("Consultar Estado Habitaciones");
        JButton btnActHHab = new JButton("Actualizar Estado Habitación");
        JButton btnConsumo = new JButton("Registrar Consumo");

        panel3.add(btnEstdHab);
        panel3.add(btnActHHab);
        panel3.add(btnConsumo);

        btnEstdHab.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de empleados
            btnEstdHab();
        });

        btnActHHab.addActionListener(e -> {
            btnActHHab();
        });
        btnConsumo.addActionListener(e -> {
            // Aquí podrías abrir una nueva ventana o panel para el CRUD de servicios
            ConsumoServicios();
        });
        menup.add(panel3);
        menup.setVisible(true);
    }

    private void btnActHHab() {
        JFrame ventana = new JFrame("Actualizar Estado de Habitación");
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblId = new JLabel("ID Habitación:");
        JTextField txtId = new JTextField();

        JLabel lblTipo = new JLabel("Tipo:");
        JTextField txtTipo = new JTextField();
        txtTipo.setEditable(false);

        JLabel lblPrecio = new JLabel("Precio/Noche:");
        JTextField txtPrecio = new JTextField();
        txtPrecio.setEditable(false);

        JCheckBox chkMantenimiento = new JCheckBox("En mantenimiento");
        JCheckBox chkDisponible = new JCheckBox("Disponible");

        JButton btnBuscar = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar");

        ventana.add(lblId);
        ventana.add(txtId);
        ventana.add(new JLabel());
        ventana.add(btnBuscar);
        ventana.add(lblTipo);
        ventana.add(txtTipo);
        ventana.add(lblPrecio);
        ventana.add(txtPrecio);
        ventana.add(chkMantenimiento);
        ventana.add(chkDisponible);
        ventana.add(new JLabel());
        ventana.add(btnActualizar);

        HabitacionDAO dao = new HabitacionDAO();

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Habitacion h = dao.obtenerHabitacionPorId(id);
                if (h != null) {
                    txtTipo.setText(h.getTipoHabitacion());
                    txtPrecio.setText(String.valueOf(h.getPrecioNoche()));
                    chkMantenimiento.setSelected(h.isEstadoMantenimiento());
                    chkDisponible.setSelected(h.isEstadoDisponibilidad());
                } else {
                    JOptionPane.showMessageDialog(ventana, "❌ Habitación no encontrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "⚠️ ID inválido.");
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Habitacion h = dao.obtenerHabitacionPorId(id);

                if (h != null) {
                    h.setEstadoMantenimiento(chkMantenimiento.isSelected());
                    h.setEstadoDisponibilidad(chkDisponible.isSelected());

                    if (dao.actualizarHabitacion(h)) {
                        JOptionPane.showMessageDialog(ventana, "✅ Estado actualizado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(ventana, "❌ Error al actualizar estado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(ventana, "❌ Habitación no encontrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "⚠️ ID inválido.");
            }
        });

        ventana.setVisible(true);
    }

    private void btnEstdHab() {
        JFrame ventana = new JFrame("Estado de Todas las Habitaciones");
        ventana.setSize(700, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        HabitacionDAO dao = new HabitacionDAO();
        List<Habitacion> habitaciones = dao.listarTodas(); // 🔁 Aquí el cambio

        if (habitaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ No hay habitaciones registradas.");
            return;
        }

        String[] columnas = { "ID", "Tipo", "Precio por Noche", "Mantenimiento", "Disponible" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Habitacion h : habitaciones) {
            modelo.addRow(new Object[] {
                    h.getIdHabitacion(),
                    h.getTipoHabitacion(),
                    h.getPrecioNoche(),
                    h.isEstadoMantenimiento() ? "Sí" : "No",
                    h.isEstadoDisponibilidad() ? "Sí" : "No"
            });
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        ventana.add(scroll, BorderLayout.CENTER);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new vista().setVisible(true);
        });
    }
}
