/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import CRUD.ConsultasCliente;
import CRUD.ConsultasVendedor;
import CRUD.ConsultasVenta;
import Entidades.Cliente;
import Entidades.Vendedor;
import Entidades.Venta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class FrmVenta extends JPanel {

    private final ConsultasVenta dao = new ConsultasVenta();
    private final ConsultasCliente daoCliente = new ConsultasCliente();
    private final ConsultasVendedor daoVendedor = new ConsultasVendedor();

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtFecha = new JTextField(10);
    private final JTextField txtTotal = new JTextField(8);
    private final JTextField txtMetodoPago = new JTextField(10);
    private final JComboBox<NombreItem> comboCliente = new JComboBox<>();
    private final JComboBox<NombreItem> comboVendedor = new JComboBox<>();

    private static final Pattern FECHA_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Fecha", "Total", "Metodo Pago", "ID Cliente", "ID Vendedor"}, 0) {
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    };
    private final JTable tabla = new JTable(modelo);

    private static class NombreItem {
        final int id;
        final String nombre;
        NombreItem(int id, String nombre) { this.id = id; this.nombre = nombre; }
        @Override public String toString() { return id + " - " + nombre; }
    }

    public FrmVenta() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setBackground(BlokkTheme.NEGRO);

        JPanel form = new JPanel(new GridBagLayout());
        BlokkTheme.fondoNegro(form);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        JLabel lblTotal = new JLabel("Total:");
        JLabel lblMetodoPago = new JLabel("Metodo de pago:");
        JLabel lblCliente = new JLabel("Cliente:");
        JLabel lblVendedor = new JLabel("Vendedor:");
        for (JLabel l : new JLabel[]{lblId, lblFecha, lblTotal, lblMetodoPago, lblCliente, lblVendedor}) {
            BlokkTheme.estilizarLabel(l);
        }
        for (JTextField t : new JTextField[]{txtId, txtFecha, txtTotal, txtMetodoPago}) {
            BlokkTheme.estilizarCampoTexto(t);
        }
        BlokkTheme.estilizarCombo(comboCliente);
        BlokkTheme.estilizarCombo(comboVendedor);

        gc.gridx = 0; gc.gridy = 0; form.add(lblId, gc);
        gc.gridx = 1; txtId.setEditable(false); form.add(txtId, gc);

        gc.gridx = 2; gc.gridy = 0; form.add(lblFecha, gc);
        gc.gridx = 3; form.add(txtFecha, gc);

        gc.gridx = 0; gc.gridy = 1; form.add(lblTotal, gc);
        gc.gridx = 1; form.add(txtTotal, gc);

        gc.gridx = 2; gc.gridy = 1; form.add(lblMetodoPago, gc);
        gc.gridx = 3; form.add(txtMetodoPago, gc);

        gc.gridx = 0; gc.gridy = 2; form.add(lblCliente, gc);
        gc.gridx = 1; gc.gridwidth = 3; form.add(comboCliente, gc);
        gc.gridwidth = 1;

        gc.gridx = 0; gc.gridy = 3; form.add(lblVendedor, gc);
        gc.gridx = 1; gc.gridwidth = 3; form.add(comboVendedor, gc);

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
        norte.add(BlokkTheme.crearEncabezado("Ventas", "GESTION DE VENTAS"), BorderLayout.NORTH);
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
        btnConsultar.addActionListener(e -> { cargarCombos(); buscar(); });

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtId.setText(modelo.getValueAt(fila, 0).toString());
                txtFecha.setText(modelo.getValueAt(fila, 1).toString());
                txtTotal.setText(modelo.getValueAt(fila, 2).toString());
                txtMetodoPago.setText(modelo.getValueAt(fila, 3).toString());
                seleccionar(comboCliente, (int) modelo.getValueAt(fila, 4));
                seleccionar(comboVendedor, (int) modelo.getValueAt(fila, 5));
            }
        });

        cargarCombos();
        cargarTabla();
    }

    private void cargarCombos() {
        comboCliente.removeAllItems();
        for (Cliente c : daoCliente.consultarClientes()) {
            comboCliente.addItem(new NombreItem(c.getIdCliente(), c.getNombre()));
        }
        comboVendedor.removeAllItems();
        for (Vendedor v : daoVendedor.consultarVendedores()) {
            comboVendedor.addItem(new NombreItem(v.getIdVendedor(), v.getNombre()));
        }
    }

    private void seleccionar(JComboBox<NombreItem> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).id == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Venta> lista = dao.consultarVentas();
        for (Venta v : lista) {
            modelo.addRow(new Object[]{v.getIdVenta(), v.getFecha(), v.getTotal(), v.getMetodoPago(),
                    v.getIdCliente(), v.getIdVendedor()});
        }
    }

    private boolean validarCampos() {
        if (!FECHA_PATTERN.matcher(txtFecha.getText().trim()).matches()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "La fecha debe tener formato YYYY-MM-DD.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            double total = Double.parseDouble(txtTotal.getText().trim());
            if (total < 0) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "El total no puede ser negativo.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El total debe ser un numero valido.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtMetodoPago.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El metodo de pago es obligatorio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (comboCliente.getSelectedItem() == null) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona un cliente.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (comboVendedor.getSelectedItem() == null) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona un vendedor.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void registrar() {
        if (!validarCampos()) return;
        try {
            NombreItem cli = (NombreItem) comboCliente.getSelectedItem();
            NombreItem ven = (NombreItem) comboVendedor.getSelectedItem();
            if (!daoCliente.existeCliente(cli.id)) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "El cliente seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!daoVendedor.existeVendedor(ven.id)) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "El vendedor seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Venta v = new Venta(0, txtFecha.getText().trim(), Double.parseDouble(txtTotal.getText().trim()),
                    txtMetodoPago.getText().trim(), cli.id, ven.id);
            dao.insertarVenta(v);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Venta registrada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Selecciona una venta de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validarCampos()) return;
        try {
            NombreItem cli = (NombreItem) comboCliente.getSelectedItem();
            NombreItem ven = (NombreItem) comboVendedor.getSelectedItem();
            int id = Integer.parseInt(txtId.getText().trim());
            Venta v = new Venta(id, txtFecha.getText().trim(), Double.parseDouble(txtTotal.getText().trim()),
                    txtMetodoPago.getText().trim(), cli.id, ven.id);
            dao.actualizarVenta(v);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Venta actualizada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Selecciona una venta de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BlokkTheme.temaDialogo();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar la venta " + txtId.getText() + "?",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            dao.eliminarVenta(id);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Venta eliminada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
                "Ingrese el ID de la venta:");

        if (texto == null || texto.trim().isEmpty()) {
            cargarTabla();
            return;
        }

        modelo.setRowCount(0);

        List<Venta> lista = dao.buscarVenta(texto.trim());

        for (Venta v : lista) {

            modelo.addRow(new Object[]{
                v.getIdVenta(),
                v.getFecha(),
                v.getTotal(),
                v.getMetodoPago(),
                v.getIdCliente(),
                v.getIdVendedor()
            });

        }

        if (lista.isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this,
                    "No se encontraron ventas.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtFecha.setText("");
        txtTotal.setText("");
        txtMetodoPago.setText("");
        if (comboCliente.getItemCount() > 0) {
            comboCliente.setSelectedIndex(0);
        }
        if (comboVendedor.getItemCount() > 0) {
            comboVendedor.setSelectedIndex(0);
        }
        tabla.clearSelection();
    }

}
