/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import CRUD.ConsultasDetalleVenta;
import CRUD.ConsultasPrenda;
import CRUD.ConsultasVenta;
import Entidades.DetalleVenta;
import Entidades.Prenda;
import Entidades.Venta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
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
public class FrmDetalleVenta extends JPanel {

    private final ConsultasDetalleVenta dao = new ConsultasDetalleVenta();
    private final ConsultasVenta daoVenta = new ConsultasVenta();
    private final ConsultasPrenda daoPrenda = new ConsultasPrenda();

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtCantidad = new JTextField(6);
    private final JTextField txtPrecioUnitario = new JTextField(8);
    private final JComboBox<DescItem> comboVenta = new JComboBox<>();
    private final JComboBox<DescItem> comboPrenda = new JComboBox<>();

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Cantidad", "Precio Unitario", "ID Venta", "ID Prenda"}, 0) {
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    };
    private final JTable tabla = new JTable(modelo);

    private static class DescItem {
        final int id;
        final String desc;
        DescItem(int id, String desc) { this.id = id; this.desc = desc; }
        @Override public String toString() { return id + " - " + desc; }
    }

    public FrmDetalleVenta() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setBackground(BlokkTheme.NEGRO);

        JPanel form = new JPanel(new GridBagLayout());
        BlokkTheme.fondoNegro(form);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        JLabel lblCantidad = new JLabel("Cantidad:");
        JLabel lblPrecioUnitario = new JLabel("Precio Unitario:");
        JLabel lblVenta = new JLabel("Venta:");
        JLabel lblPrenda = new JLabel("Prenda:");
        for (JLabel l : new JLabel[]{lblId, lblCantidad, lblPrecioUnitario, lblVenta, lblPrenda}) {
            BlokkTheme.estilizarLabel(l);
        }
        for (JTextField t : new JTextField[]{txtId, txtCantidad, txtPrecioUnitario}) {
            BlokkTheme.estilizarCampoTexto(t);
        }
        BlokkTheme.estilizarCombo(comboVenta);
        BlokkTheme.estilizarCombo(comboPrenda);

        gc.gridx = 0; gc.gridy = 0; form.add(lblId, gc);
        gc.gridx = 1; txtId.setEditable(false); form.add(txtId, gc);

        gc.gridx = 2; gc.gridy = 0; form.add(lblCantidad, gc);
        gc.gridx = 3; form.add(txtCantidad, gc);

        gc.gridx = 0; gc.gridy = 1; form.add(lblPrecioUnitario, gc);
        gc.gridx = 1; form.add(txtPrecioUnitario, gc);

        gc.gridx = 0; gc.gridy = 2; form.add(lblVenta, gc);
        gc.gridx = 1; gc.gridwidth = 3; form.add(comboVenta, gc);
        gc.gridwidth = 1;

        gc.gridx = 0; gc.gridy = 3; form.add(lblPrenda, gc);
        gc.gridx = 1; gc.gridwidth = 3; form.add(comboPrenda, gc);

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
        norte.add(BlokkTheme.crearEncabezado("Detalle de venta", "DETALLE DE VENTA"), BorderLayout.NORTH);
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
                txtCantidad.setText(modelo.getValueAt(fila, 1).toString());
                txtPrecioUnitario.setText(modelo.getValueAt(fila, 2).toString());
                seleccionar(comboVenta, (int) modelo.getValueAt(fila, 3));
                seleccionar(comboPrenda, (int) modelo.getValueAt(fila, 4));
            }
        });

        cargarCombos();
        cargarTabla();
    }

    private void cargarCombos() {
        comboVenta.removeAllItems();
        for (Venta v : daoVenta.consultarVentas()) {
            comboVenta.addItem(new DescItem(v.getIdVenta(), v.getFecha() + " - $" + v.getTotal()));
        }
        comboPrenda.removeAllItems();
        for (Prenda p : daoPrenda.consultarPrendas()) {
            comboPrenda.addItem(new DescItem(p.getIdPrenda(), p.getNombre() + " - $" + p.getPrecio()));
        }
    }

    private void seleccionar(JComboBox<DescItem> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).id == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<DetalleVenta> lista = dao.consultarDetalleVenta();
        for (DetalleVenta d : lista) {
            modelo.addRow(new Object[]{d.getIdDetalle(), d.getCantidad(), d.getPrecioUnitario(),
                d.getIdVenta(), d.getIdPrenda()});
        }
    }

    private boolean validarCampos() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad <= 0) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un numero entero valido.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            double precio = Double.parseDouble(txtPrecioUnitario.getText().trim());
            if (precio < 0) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "El precio unitario no puede ser negativo.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El precio unitario debe ser un numero valido.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (comboVenta.getSelectedItem() == null) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona una venta.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (comboPrenda.getSelectedItem() == null) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona una prenda.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void registrar() {
        if (!validarCampos()) {
            return;
        }
        try {
            DescItem venta = (DescItem) comboVenta.getSelectedItem();
            DescItem prenda = (DescItem) comboPrenda.getSelectedItem();
            DetalleVenta d = new DetalleVenta(0, Integer.parseInt(txtCantidad.getText().trim()),
                    Double.parseDouble(txtPrecioUnitario.getText().trim()), venta.id, prenda.id);
            dao.insertarDetalleVenta(d);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Detalle registrado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Selecciona un detalle de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validarCampos()) {
            return;
        }
        try {
            DescItem venta = (DescItem) comboVenta.getSelectedItem();
            DescItem prenda = (DescItem) comboPrenda.getSelectedItem();
            int id = Integer.parseInt(txtId.getText().trim());
            DetalleVenta d = new DetalleVenta(id, Integer.parseInt(txtCantidad.getText().trim()),
                    Double.parseDouble(txtPrecioUnitario.getText().trim()), venta.id, prenda.id);
            dao.actualizarDetalleVenta(d);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Detalle actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Selecciona un detalle de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BlokkTheme.temaDialogo();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar el detalle " + txtId.getText() + "?",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            dao.eliminarDetalleVenta(id);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Detalle eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
                "Ingrese el ID de detalle, venta o prenda:");

        if (texto == null || texto.trim().isEmpty()) {
            cargarTabla();
            return;
        }

        modelo.setRowCount(0);

        List<DetalleVenta> lista = dao.buscarDetalleVenta(texto.trim());

        for (DetalleVenta d : lista) {

            modelo.addRow(new Object[]{
                d.getIdDetalle(),
                d.getCantidad(),
                d.getPrecioUnitario(),
                d.getIdVenta(),
                d.getIdPrenda()
            });

        }

        if (lista.isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this,
                    "No se encontraron detalles de venta.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtCantidad.setText("");
        txtPrecioUnitario.setText("");
        if (comboVenta.getItemCount() > 0) {
            comboVenta.setSelectedIndex(0);
        }
        if (comboPrenda.getItemCount() > 0) {
            comboPrenda.setSelectedIndex(0);
        }
        tabla.clearSelection();
    }

}
