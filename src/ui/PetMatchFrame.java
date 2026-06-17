package ui;

import models.Mascota;
import services.PetMatchService;

import javax.swing.*;
import java.awt.*;

public class PetMatchFrame extends JFrame {

    private PetMatchService service;
    private JTextArea areaResultado;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtEspecie;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JTextField txtZona;
    private JTextField txtUrgencia;
    private JTextField txtDias;

    private JComboBox<String> cmbEstado;

    private JTextField txtZonaAlerta;
    private JTextField txtDescripcionAlerta;
    private JTextField txtPrioridadAlerta;

    private JTextField txtIdBusquedaRapida;

    private JTextField txtMascotaAvistamiento;
    private JTextField txtUltimaZona;
    private JTextField txtPersonaReporta;
    private JTextField txtComentarioAvistamiento;

    public PetMatchFrame() {
        service = new PetMatchService();

        setTitle("PetMatch Rescue - Entrega 2");
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane pestanas = new JTabbedPane();

        pestanas.addTab("Reportes", crearPanelReportes());
        pestanas.addTab("Alertas urgentes", crearPanelAlertas());
        pestanas.addTab("Historial", crearPanelHistorial());
        pestanas.addTab("Centro de Busqueda", crearPanelCentroBusqueda());
        pestanas.addTab("Organizacion de Reportes", crearPanelOrganizacionReportes());
        pestanas.addTab("Seguimiento de Avistamientos", crearPanelSeguimientoAvistamientos());
        pestanas.addTab("Usuarios del Sistema", crearPanelUsuariosSistema());

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setPreferredSize(new Dimension(1000, 240));

        add(pestanas, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        areaResultado.setText(service.mostrarReportes());

        setVisible(true);
    }

    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtEspecie = new JTextField();
        txtRaza = new JTextField();
        txtEdad = new JTextField();
        txtZona = new JTextField();
        txtUrgencia = new JTextField();
        txtDias = new JTextField();

        cmbEstado = new JComboBox<>(new String[] { "Perdida", "Encontrada" });

        agregarCampo(panel, gbc, 0, "ID:", txtId);
        agregarCampo(panel, gbc, 1, "Nombre:", txtNombre);
        agregarCampo(panel, gbc, 2, "Especie:", txtEspecie);
        agregarCampo(panel, gbc, 3, "Raza:", txtRaza);
        agregarCampo(panel, gbc, 4, "Edad (ej: 8 meses, 2 anios):", txtEdad);
        agregarCampo(panel, gbc, 5, "Estado:", cmbEstado);
        agregarCampo(panel, gbc, 6, "Zona:", txtZona);
        agregarCampo(panel, gbc, 7, "Urgencia 1 a 10:", txtUrgencia);
        agregarCampo(panel, gbc, 8, "Dias desaparecida:", txtDias);

        JButton btnRegistrar = new JButton("Registrar reporte");
        JButton btnBuscar = new JButton("Buscar por ID");
        JButton btnEliminar = new JButton("Eliminar reporte");
        JButton btnMostrar = new JButton("Mostrar reportes");

        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.weightx = 1;
        panel.add(btnRegistrar, gbc);

        gbc.gridx = 1;
        panel.add(btnBuscar, gbc);

        gbc.gridy = 10;
        gbc.gridx = 0;
        panel.add(btnEliminar, gbc);

        gbc.gridx = 1;
        panel.add(btnMostrar, gbc);

        btnRegistrar.addActionListener(e -> registrarReporte());
        btnBuscar.addActionListener(e -> buscarReporte());
        btnEliminar.addActionListener(e -> eliminarReporte());
        btnMostrar.addActionListener(e -> areaResultado.setText(service.mostrarReportes()));

        return panel;
    }

    private JPanel crearPanelAlertas() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtZonaAlerta = new JTextField();
        txtDescripcionAlerta = new JTextField();
        txtPrioridadAlerta = new JTextField();

        agregarCampo(panel, gbc, 0, "Zona de alerta:", txtZonaAlerta);
        agregarCampo(panel, gbc, 1, "Descripcion:", txtDescripcionAlerta);
        agregarCampo(panel, gbc, 2, "Prioridad 1 a 10:", txtPrioridadAlerta);

        JButton btnRegistrarAlerta = new JButton("Registrar alerta");
        JButton btnAtenderAlerta = new JButton("Atender alerta");
        JButton btnMostrarAlertas = new JButton("Mostrar alertas");

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weightx = 1;
        panel.add(btnRegistrarAlerta, gbc);

        gbc.gridx = 1;
        panel.add(btnAtenderAlerta, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(btnMostrarAlertas, gbc);

        gbc.gridx = 1;
        panel.add(new JLabel(""), gbc);

        btnRegistrarAlerta.addActionListener(e -> registrarAlerta());
        btnAtenderAlerta.addActionListener(e -> areaResultado.setText(service.atenderAlerta()));
        btnMostrarAlertas.addActionListener(e -> areaResultado.setText(service.mostrarAlertas()));

        return panel;
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JButton btnVerHistorial = new JButton("Ver historial");
        JButton btnUltimaAccion = new JButton("Ver ultima accion");
        JButton btnDeshacer = new JButton("Deshacer ultima accion");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnVerHistorial, gbc);

        gbc.gridx = 1;
        panel.add(btnUltimaAccion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(btnDeshacer, gbc);

        btnVerHistorial.addActionListener(e -> areaResultado.setText(service.mostrarHistorial()));
        btnUltimaAccion.addActionListener(e -> areaResultado.setText(service.verUltimaAccion()));
        btnDeshacer.addActionListener(e -> areaResultado.setText(service.deshacerUltimaAccion()));

        return panel;
    }

    private JPanel crearPanelCentroBusqueda() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtIdBusquedaRapida = new JTextField();

        JLabel titulo = new JLabel("Centro de busqueda rapida de reportes");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridwidth = 1;

        agregarCampo(panel, gbc, 1, "ID del reporte:", txtIdBusquedaRapida);

        JButton btnBuscarRapido = new JButton("Buscar reporte rapido");
        JButton btnOrdenadosId = new JButton("Reportes ordenados por ID");
        JButton btnVistaJerarquica = new JButton("Vista jerarquica de reportes");
        JButton btnVistaProcesamiento = new JButton("Vista de procesamiento");

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(btnBuscarRapido, gbc);

        gbc.gridx = 1;
        panel.add(btnOrdenadosId, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(btnVistaJerarquica, gbc);

        gbc.gridx = 1;
        panel.add(btnVistaProcesamiento, gbc);

        btnBuscarRapido.addActionListener(e -> buscarReporteRapido());
        btnOrdenadosId.addActionListener(e -> areaResultado.setText(service.mostrarArbolInOrder()));
        btnVistaJerarquica.addActionListener(e -> areaResultado.setText(service.mostrarArbolPreOrder()));
        btnVistaProcesamiento.addActionListener(e -> areaResultado.setText(service.mostrarArbolPostOrder()));

        return panel;
    }

    private JPanel crearPanelOrganizacionReportes() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel titulo = new JLabel("Organizacion de reportes");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnOrdenarUrgencia = new JButton("Ordenar por urgencia");
        JButton btnOrdenarDias = new JButton("Ordenar por dias desaparecida");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(btnOrdenarUrgencia, gbc);

        gbc.gridx = 1;
        panel.add(btnOrdenarDias, gbc);

        btnOrdenarUrgencia.addActionListener(e -> areaResultado.setText(service.ordenarPorUrgencia()));
        btnOrdenarDias.addActionListener(e -> areaResultado.setText(service.ordenarPorDiasDesaparecida()));

        return panel;
    }

    private JPanel crearPanelSeguimientoAvistamientos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtMascotaAvistamiento = new JTextField();
        txtUltimaZona = new JTextField();
        txtPersonaReporta = new JTextField();
        txtComentarioAvistamiento = new JTextField();

        JLabel titulo = new JLabel("Seguimiento de avistamientos");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridwidth = 1;

        agregarCampo(panel, gbc, 1, "Mascota reportada:", txtMascotaAvistamiento);
        agregarCampo(panel, gbc, 2, "Ultima zona donde fue vista:", txtUltimaZona);
        agregarCampo(panel, gbc, 3, "Persona que reporta:", txtPersonaReporta);
        agregarCampo(panel, gbc, 4, "Comentario:", txtComentarioAvistamiento);

        JButton btnRegistrarAvistamiento = new JButton("Registrar ultimo avistamiento");
        JButton btnVerSeguimiento = new JButton("Ver seguimiento");

        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(btnRegistrarAvistamiento, gbc);

        gbc.gridx = 1;
        panel.add(btnVerSeguimiento, gbc);

        btnRegistrarAvistamiento.addActionListener(e -> registrarAvistamiento());
        btnVerSeguimiento.addActionListener(e -> areaResultado.setText(service.mostrarSeguimientoAvistamientos()));

        return panel;
    }

    private JPanel crearPanelUsuariosSistema() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel titulo = new JLabel("Usuarios del sistema");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnMostrarUsuarios = new JButton("Mostrar usuarios registrados");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(btnMostrarUsuarios, gbc);

        btnMostrarUsuarios.addActionListener(e -> areaResultado.setText(service.mostrarUsuariosSistema()));

        return panel;
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent componente) {
        gbc.gridy = fila;

        gbc.gridx = 0;
        gbc.weightx = 0.2;
        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        panel.add(componente, gbc);
    }

    private void registrarReporte() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String especie = txtEspecie.getText();
            String raza = txtRaza.getText();
            String edad = txtEdad.getText();
            String estado = cmbEstado.getSelectedItem().toString();
            String zona = txtZona.getText();
            int urgencia = Integer.parseInt(txtUrgencia.getText());
            int dias = Integer.parseInt(txtDias.getText());

            Mascota mascota = new Mascota(id, nombre, especie, raza, edad, estado, zona, urgencia, dias);
            service.registrarMascota(mascota);

            areaResultado.setText("Reporte registrado correctamente.\n\n" + service.mostrarReportes());
            limpiarCamposReporte();

        } catch (Exception e) {
            areaResultado.setText(
                    "Error: revisa que ID, urgencia y dias sean numeros. La edad puede ser texto, por ejemplo: 8 meses o 2 anios.");
        }
    }

    private void buscarReporte() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Mascota mascota = service.buscarMascota(id);

            if (mascota != null) {
                areaResultado.setText("Reporte encontrado en la lista de reportes:\n" + mascota);
            } else {
                areaResultado.setText("No se encontro un reporte con ese ID.");
            }

        } catch (Exception e) {
            areaResultado.setText("Error: ingresa un ID valido.");
        }
    }

    private void buscarReporteRapido() {
        try {
            int id = Integer.parseInt(txtIdBusquedaRapida.getText());
            Mascota mascota = service.buscarMascotaEnArbol(id);

            if (mascota != null) {
                areaResultado.setText("Reporte encontrado rapidamente:\n" + mascota);
            } else {
                areaResultado.setText("No se encontro un reporte con ese ID.");
            }

        } catch (Exception e) {
            areaResultado.setText("Error: ingresa un ID valido para la busqueda.");
        }
    }

    private void eliminarReporte() {
        try {
            int id = Integer.parseInt(txtId.getText());

            if (service.eliminarReporte(id)) {
                areaResultado.setText("Reporte eliminado correctamente.\n\n" + service.mostrarReportes());
            } else {
                areaResultado.setText("No se encontro un reporte con ese ID.");
            }

        } catch (Exception e) {
            areaResultado.setText("Error: ingresa un ID valido.");
        }
    }

    private void registrarAlerta() {
        try {
            String zona = txtZonaAlerta.getText();
            String descripcion = txtDescripcionAlerta.getText();
            int prioridad = Integer.parseInt(txtPrioridadAlerta.getText());

            service.registrarAlerta(zona, descripcion, prioridad);
            areaResultado.setText("Alerta registrada correctamente.\n\n" + service.mostrarAlertas());

            txtZonaAlerta.setText("");
            txtDescripcionAlerta.setText("");
            txtPrioridadAlerta.setText("");

        } catch (Exception e) {
            areaResultado.setText("Error: ingresa una prioridad valida.");
        }
    }

    private void registrarAvistamiento() {
        String mascota = txtMascotaAvistamiento.getText();
        String ultimaZona = txtUltimaZona.getText();
        String persona = txtPersonaReporta.getText();
        String comentario = txtComentarioAvistamiento.getText();

        if (mascota.isEmpty() || ultimaZona.isEmpty() || persona.isEmpty() || comentario.isEmpty()) {
            areaResultado.setText("Error: completa todos los campos del avistamiento.");
            return;
        }

        service.registrarAvistamiento(mascota, ultimaZona, persona, comentario);

        areaResultado.setText("Avistamiento registrado correctamente.\n\n"
                + service.mostrarSeguimientoAvistamientos());

        txtMascotaAvistamiento.setText("");
        txtUltimaZona.setText("");
        txtPersonaReporta.setText("");
        txtComentarioAvistamiento.setText("");
    }

    private void limpiarCamposReporte() {
        txtId.setText("");
        txtNombre.setText("");
        txtEspecie.setText("");
        txtRaza.setText("");
        txtEdad.setText("");
        txtZona.setText("");
        txtUrgencia.setText("");
        txtDias.setText("");
        cmbEstado.setSelectedIndex(0);
    }
}