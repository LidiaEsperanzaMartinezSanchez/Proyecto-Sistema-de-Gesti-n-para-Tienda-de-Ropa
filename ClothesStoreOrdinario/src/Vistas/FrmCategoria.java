/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import CRUD.ConsultasCategoria;
import Entidades.Categoria;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
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
public class FrmCategoria extends JPanel {

    private final ConsultasCategoria dao = new ConsultasCategoria();

    private final JTextField txtId = new JTextField(5);
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtDescripcion = new JTextField(20);

    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Descripcion"}, 0) {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);

    public FrmCategoria() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setBackground(BlokkTheme.NEGRO);

        // ---- Formulario ----
        JPanel form = new JPanel(new GridBagLayout());
        BlokkTheme.fondoNegro(form);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblId = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblDescripcion = new JLabel("Descripcion:");
        BlokkTheme.estilizarLabel(lblId);
        BlokkTheme.estilizarLabel(lblNombre);
        BlokkTheme.estilizarLabel(lblDescripcion);

        BlokkTheme.estilizarCampoTexto(txtId);
        BlokkTheme.estilizarCampoTexto(txtNombre);
        BlokkTheme.estilizarCampoTexto(txtDescripcion);

        gc.gridx = 0;
        gc.gridy = 0;
        form.add(lblId, gc);
        gc.gridx = 1;
        txtId.setEditable(false);
        form.add(txtId, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        form.add(lblNombre, gc);
        gc.gridx = 1;
        form.add(txtNombre, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        form.add(lblDescripcion, gc);
        gc.gridx = 1;
        form.add(txtDescripcion, gc);

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
        norte.add(BlokkTheme.crearEncabezado("Categorias", "GESTION DE CATEGORIAS"), BorderLayout.NORTH);
        norte.add(form, BorderLayout.CENTER);
        norte.add(botones, BorderLayout.SOUTH);

        BlokkTheme.estilizarTabla(tabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        BlokkTheme.estilizarScroll(scrollTabla);

        add(norte, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        // ---- Eventos ----
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
                txtDescripcion.setText(modelo.getValueAt(fila, 2).toString());
            }
        });

        cargarTabla();
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Categoria> lista = dao.consultarCategorias();
        for (Categoria c : lista) {
            modelo.addRow(new Object[]{c.getIdCategoria(), c.getNombre(), c.getDescripcion()});
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtDescripcion.getText().trim().isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "La descripcion es obligatoria.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void registrar() {
        if (!validarCampos()) {
            return;
        }
        try {
            Categoria c = new Categoria(0, txtNombre.getText().trim(), txtDescripcion.getText().trim());
            dao.insertarCategoria(c);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Categoria registrada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Selecciona una categoria de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validarCampos()) {
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Categoria c = new Categoria(id, txtNombre.getText().trim(), txtDescripcion.getText().trim());
            dao.actualizarCategoria(c);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Categoria actualizada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Selecciona una categoria de la tabla.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BlokkTheme.temaDialogo();
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar la categoria " + txtId.getText() + "?",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            dao.eliminarCategoria(id);
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this, "Categoria eliminada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
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
                "Ingrese el ID o el nombre:");

        if (texto == null || texto.trim().isEmpty()) {
            cargarTabla();
            return;
        }

        modelo.setRowCount(0);

        List<Categoria> lista = dao.buscarCategoria(texto);

        for (Categoria c : lista) {
            modelo.addRow(new Object[]{
                c.getIdCategoria(),
                c.getNombre(),
                c.getDescripcion()
            });
        }

        if (lista.isEmpty()) {
            BlokkTheme.temaDialogo();
            JOptionPane.showMessageDialog(this,
                    "No se encontró ninguna categoría.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        tabla.clearSelection();
    }

}
