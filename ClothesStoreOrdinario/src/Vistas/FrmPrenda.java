/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import CRUD.ConsultasCategoria;
import CRUD.ConsultasPrenda;
import Entidades.Categoria;
import Entidades.Prenda;
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
public class FrmPrenda extends JPanel {

    private final ConsultasPrenda dao = new ConsultasPrenda();
    private final ConsultasCategoria daoCategoria = new ConsultasCategoria();

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtTalla = new JTextField(6);
    private final JTextField txtColor = new JTextField(10);
    private final JTextField txtPrecio = new JTextField(8);
    private final JTextField txtStock = new JTextField(6);
    private final JComboBox<CategoriaItem> comboCategoria = new JComboBox<>();

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Talla", "Color", "Precio", "Stock", "ID Categoria"}, 0) {
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    };
    private final JTable tabla = new JTable(modelo);

    // Wrapper para mostrar el nombre de la categoria en el combo pero guardar su id
    private static class CategoriaItem {
        final int id;
        final String nombre;
        CategoriaItem(int id, String nombre) { this.id = id; this.nombre = nombre; }
        @Override public String toString() { return id + " - " + nombre; }
    }

    public FrmPrenda() {
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
        JLabel lblTalla = new JLabel("Talla:");
        JLabel lblColor = new JLabel("Color:");
        JLabel lblPrecio = new JLabel("Precio:");
        JLabel lblStock = new JLabel("Stock:");
        JLabel lblCategoria = new JLabel("Categoria:");
        for (JLabel l : new JLabel[]{lblId, lblNombre, lblTalla, lblColor, lblPrecio, lblStock, lblCategoria}) {
            BlokkTheme.estilizarLabel(l);
        }
        for (JTextField t : new JTextField[]{txtId, txtNombre, txtTalla, txtColor, txtPrecio, txtStock}) {
            BlokkTheme.estilizarCampoTexto(t);
        }
        BlokkTheme.estilizarCombo(comboCategoria);

        gc.gridx = 0; gc.gridy = 0; form.add(lblId, gc);
        gc.gridx = 1; txtId.setEditable(false); form.add(txtId, gc);

        gc.gridx = 2; gc.gridy = 0; form.add(lblNombre, gc);
        gc.gridx = 3; form.add(txtNombre, gc);

        gc.gridx = 0; gc.gridy = 1; form.add(lblTalla, gc);
        gc.gridx = 1; form.add(txtTalla, gc);

        gc.gridx = 2; gc.gridy = 1; form.add(lblColor, gc);
        gc.gridx = 3; form.add(txtColor, gc);

        gc.gridx = 0; gc.gridy = 2; form.add(lblPrecio, gc);
        gc.gridx = 1; form.add(txtPrecio, gc);

        gc.gridx = 2; gc.gridy = 2; form.add(lblStock, gc);
        gc.gridx = 3; form.add(txtStock, gc);

        gc.gridx = 0; gc.gridy = 3; form.add(lblCategoria, gc);
        gc.gridx = 1; gc.gridwidth = 3; form.add(comboCategoria, gc);

        JButton btnNuevo = new JButton("Nuevo");
        JButton btnGuardar = new JButton("Registrar");
        JButton btnActualizarStock = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnConsultar = new JButton("Consultar");

        BlokkTheme.estilizarBotonPrimario(btnGuardar);
        BlokkTheme.estilizarBotonSecundario(btnNuevo);
        BlokkTheme.estilizarBotonSecundario(btnActualizarStock);
        BlokkTheme.estilizarBotonSecundario(btnConsultar);
        BlokkTheme.estilizarBotonPeligro(btnEliminar);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        BlokkTheme.fondoNegro(botones);
        botones.add(btnNuevo);
        botones.add(btnGuardar);
        botones.add(btnActualizarStock);
        botones.add(btnEliminar);
        botones.add(btnConsultar);

        JPanel norte = new JPanel(new BorderLayout());
        BlokkTheme.fondoNegro(norte);
        norte.add(BlokkTheme.crearEncabezado("Prendas", "GESTION DE PRENDAS"), BorderLayout.NORTH);
        norte.add(form, BorderLayout.CENTER);
        norte.add(botones, BorderLayout.SOUTH);

        BlokkTheme.estilizarTabla(tabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        BlokkTheme.estilizarScroll(scrollTabla);

        add(norte, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        btnNuevo.addActionListener(e -> limpiarCampos());
        btnGuardar.addActionListener(e -> registrar());
        btnActualizarStock.addActionListener(e -> actualizarStock());
        btnEliminar.addActionListener(e -> eliminar());
        btnConsultar.addActionListener(e -> { cargarCategorias(); buscar(); });

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtId.setText(modelo.getValueAt(fila, 0).toString());
                txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                txtTalla.setText(modelo.getValueAt(fila, 2).toString());
                txtColor.setText(modelo.getValueAt(fila, 3).toString());
                txtPrecio.setText(modelo.getValueAt(fila, 4).toString());
                txtStock.setText(modelo.getValueAt(fila, 5).toString());
                seleccionarCategoria((int) modelo.getValueAt(fila, 6));
            }
        });

        cargarCategorias();
        cargarTabla();
    }

    private void cargarCategorias() {
        comboCategoria.removeAllItems();
        List<Categoria> lista = daoCategoria.consultarCategorias();
        for (Categoria c : lista) {
            comboCategoria.addItem(new CategoriaItem(c.getIdCategoria(), c.getNombre()));
        }
    }

    private void seleccionarCategoria(int idCategoria) {
        for (int i = 0; i < comboCategoria.getItemCount(); i++) {
            if (comboCategoria.getItemAt(i).id == idCategoria) {
                comboCategoria.setSelectedIndex(i);
                return;
            }
        }
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Prenda> lista = dao.consultarPrendas();
        for (Prenda p : lista) {
            modelo.addRow(new Object[]{p.getIdPrenda(), p.getNombre(), p.getTalla(), p.getColor(),
                    p.getPrecio(), p.getStock(), p.getIdCategoria()});
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtTalla.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "La talla es obligatoria.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtColor.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El color es obligatorio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            if (precio < 0) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El precio debe ser un numero valido.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            int stock = Integer.parseInt(txtStock.getText().trim());
            if (stock < 0) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "El stock no puede ser negativo.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El stock debe ser un numero entero valido.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (comboCategoria.getSelectedItem() == null) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona una categoria.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void registrar() {
        if (!validarCampos()) return;
        try {
            CategoriaItem cat = (CategoriaItem) comboCategoria.getSelectedItem();
            if (!daoCategoria.existeCategoria(cat.id)) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "La categoria seleccionada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Prenda p = new Prenda(0, txtNombre.getText().trim(), txtTalla.getText().trim(), txtColor.getText().trim(),
                    Double.parseDouble(txtPrecio.getText().trim()), Integer.parseInt(txtStock.getText().trim()), cat.id);
            dao.insertarPrenda(p);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Prenda registrada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla();
        } catch (Exception ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarStock() {
        if (txtId.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona una prenda de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int stock = Integer.parseInt(txtStock.getText().trim());
            if (stock < 0) {
                BlokkTheme.temaDialogo();
                JOptionPane.showMessageDialog(this, "El stock no puede ser negativo.", "Validacion", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.parseInt(txtId.getText().trim());
            dao.actualizarPrendaStock(id, stock);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Stock actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla();
        } catch (NumberFormatException ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El stock debe ser un numero entero valido.", "Validacion", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Error al actualizar stock: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (txtId.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Selecciona una prenda de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BlokkTheme.temaDialogo();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar la prenda " + txtId.getText() + "?",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            dao.eliminarPrenda(id);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Prenda eliminada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
                "Ingrese el ID o el nombre de la prenda:");

        if (texto == null || texto.trim().isEmpty()) {
            cargarTabla();
            return;
        }

        modelo.setRowCount(0);

        List<Prenda> lista = dao.buscarPrenda(texto.trim());

        for (Prenda p : lista) {

            modelo.addRow(new Object[]{
                p.getIdPrenda(),
                p.getNombre(),
                p.getTalla(),
                p.getColor(),
                p.getPrecio(),
                p.getStock(),
                p.getIdCategoria()
            });

        }

        if (lista.isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this,
                    "No se encontraron prendas.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtTalla.setText("");
        txtColor.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        if (comboCategoria.getItemCount() > 0) {
            comboCategoria.setSelectedIndex(0);
        }
        tabla.clearSelection();
    }

}
