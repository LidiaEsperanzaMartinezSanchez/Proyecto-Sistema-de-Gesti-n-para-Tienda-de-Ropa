/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import CRUD.ConsultasVendedor;
import Entidades.Vendedor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author webon
 */
public class FrmVendedor extends JPanel {

    private final ConsultasVendedor dao = new ConsultasVendedor();

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtEmail = new JTextField(18);
    private final JTextField txtPuesto = new JTextField(15);

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Email", "Puesto"}, 0) {
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    };
    private final JTable tabla = new JTable(modelo);

    public FrmVendedor() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setBackground(BlokkTheme.NEGRO);

        JPanel form = new JPanel(new GridBagLayout());
        BlokkTheme.fondoNegro(form);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPuesto = new JLabel("Puesto:");
        for (JLabel l : new JLabel[]{lblId, lblNombre, lblEmail, lblPuesto}) {
            BlokkTheme.estilizarLabel(l);
        }
        for (JTextField t : new JTextField[]{txtId, txtNombre, txtEmail, txtPuesto}) {
            BlokkTheme.estilizarCampoTexto(t);
        }

        gc.gridx = 0; gc.gridy = 0; form.add(lblId, gc);
        gc.gridx = 1; txtId.setEditable(false); form.add(txtId, gc);

        gc.gridx = 2; gc.gridy = 0; form.add(lblNombre, gc);
        gc.gridx = 3; form.add(txtNombre, gc);

        gc.gridx = 0; gc.gridy = 1; form.add(lblEmail, gc);
        gc.gridx = 1; form.add(txtEmail, gc);

        gc.gridx = 2; gc.gridy = 1; form.add(lblPuesto, gc);
        gc.gridx = 3; form.add(txtPuesto, gc);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnGuardar = new JButton("Registrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnConsultar = new JButton("Consultar");

        BlokkTheme.estilizarBotonPrimario(btnGuardar);
        BlokkTheme.estilizarBotonSecundario(btnNuevo);
        BlokkTheme.estilizarBotonSecundario(btnActualizar);
        BlokkTheme.estilizarBotonSecundario(btnConsultar);
        BlokkTheme.estilizarBotonPeligro(btnEliminar);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        BlokkTheme.fondoNegro(botones);
        botones.add(btnNuevo);
        botones.add(btnGuardar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnConsultar);

        JPanel norte = new JPanel(new BorderLayout());
        BlokkTheme.fondoNegro(norte);
        norte.add(BlokkTheme.crearEncabezado("Vendedores", "GESTION DE VENDEDORES"), BorderLayout.NORTH);
        norte.add(form, BorderLayout.CENTER);
        norte.add(botones, BorderLayout.SOUTH);

        BlokkTheme.estilizarTabla(tabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        BlokkTheme.estilizarScroll(scrollTabla);

        add(norte, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        btnNuevo.addActionListener(e -> limpiarCampos());
        btnGuardar.addActionListener(e -> registrar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnConsultar.addActionListener(e -> buscar());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtId.setText(modelo.getValueAt(fila, 0).toString());
                txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                txtEmail.setText(modelo.getValueAt(fila, 2).toString());
                txtPuesto.setText(modelo.getValueAt(fila, 3).toString());
            }
        });

        cargarTabla();
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Vendedor> lista = dao.consultarVendedores();
        for (Vendedor v : lista) {
            modelo.addRow(new Object[]{v.getIdVendedor(), v.getNombre(), v.getEmail(), v.getPuesto()});
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String email = txtEmail.getText().trim();
        if (email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Ingresa un email valido.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtPuesto.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El puesto es obligatorio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void registrar() {
        if (!validarCampos()) {
            return;
        }
        try {
            Vendedor v = new Vendedor(0, txtNombre.getText().trim(), txtEmail.getText().trim(), txtPuesto.getText().trim());
            dao.insertarVendedor(v);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Vendedor registrado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla();
        } catch (Exception ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (txtId.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona un vendedor de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validarCampos()) {
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Vendedor v = new Vendedor(id, txtNombre.getText().trim(), txtEmail.getText().trim(), txtPuesto.getText().trim());
            dao.actualizarVendedor(v);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Vendedor actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla();
        } catch (Exception ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (txtId.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona un vendedor de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BlokkTheme.temaDialogo();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar al vendedor " + txtId.getText() + "?",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            dao.eliminarVendedor(id);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Vendedor eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla();
        } catch (Exception ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() {

        BlokkTheme.temaDialogo();
        String texto = JOptionPane.showInputDialog(
                this,
                "Ingrese el ID o el nombre del vendedor:");

        if (texto == null || texto.trim().isEmpty()) {
            cargarTabla();
            return;
        }

        modelo.setRowCount(0);

        List<Vendedor> lista = dao.buscarVendedor(texto.trim());

        for (Vendedor v : lista) {

            modelo.addRow(new Object[]{
                v.getIdVendedor(),
                v.getNombre(),
                v.getEmail(),
                v.getPuesto()
            });

        }

        if (lista.isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this,
                    "No se encontraron vendedores.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtPuesto.setText("");
        tabla.clearSelection();
    }

}
