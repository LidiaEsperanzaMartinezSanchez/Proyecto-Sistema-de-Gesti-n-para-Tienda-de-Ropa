/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author webon
 */
public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("BLOKK. — Sistema de Gestion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BlokkTheme.NEGRO);

        // ---- Tabs de navegacion (equivalente a .nav-links) ----
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Categorias", new FrmCategoria());
        tabs.addTab("Prendas", new FrmPrenda());
        tabs.addTab("Clientes", new FrmCliente());
        tabs.addTab("Vendedores", new FrmVendedor());
        tabs.addTab("Ventas", new FrmVenta());
        tabs.addTab("Detalle de venta", new FrmDetalleVenta());

        BlokkTheme.estilizarTabs(tabs);

        // Al cambiar de pestana, recargamos cada panel para reflejar cambios recientes
        tabs.addChangeListener(e -> {
            Component seleccionado = tabs.getSelectedComponent();
            if (seleccionado instanceof JPanel) {
                seleccionado.revalidate();
                seleccionado.repaint();
            }
        });

        add(tabs, BorderLayout.CENTER);

        // ---- Menu superior oscuro ----
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> {
            BlokkTheme.temaDialogo();
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?",
                    "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });
        menuArchivo.add(itemSalir);
        menuBar.add(menuArchivo);
        BlokkTheme.estilizarMenuBar(menuBar);
        setJMenuBar(menuBar);
    }
}