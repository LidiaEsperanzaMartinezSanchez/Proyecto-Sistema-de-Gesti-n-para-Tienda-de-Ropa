/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author webon
 */
public class BlokkTheme {
    private BlokkTheme() {
        // No instanciable, solo constantes y metodos estaticos
    }

    // ====== PALETA DE COLORES (tomada de BLOKK.) ======
    public static final Color NEGRO   = new Color(0x0D, 0x0D, 0x0D);
    public static final Color MORADO  = new Color(0x8B, 0x2B, 0xE2);
    public static final Color LILA    = new Color(0xB0, 0x66, 0xFF);
    public static final Color GRIS1   = new Color(0x3A, 0x3A, 0x3A); // paneles/fondos secundarios
    public static final Color GRIS2   = new Color(0x88, 0x88, 0x80); // texto secundario
    public static final Color GRIS3   = new Color(0xD4, 0xD0, 0xC8); // texto terciario claro
    public static final Color CREMA   = new Color(0xF2, 0xF0, 0xEA); // texto principal sobre negro
    public static final Color ROJO_ERROR = new Color(0xFF, 0x6B, 0x6B);

    // Fila alterna en tabla (zebra striping) sobre fondo negro
    public static final Color FILA_PAR   = new Color(0x16, 0x16, 0x16);
    public static final Color FILA_IMPAR = new Color(0x0D, 0x0D, 0x0D);
    public static final Color FILA_SELECCION = MORADO;

    // ====== TIPOGRAFIA (fuentes de sistema, sin dependencias externas) ======
    // "Bebas Neue" no esta disponible sin cargar .ttf, asi que usamos
    // SansSerif en BOLD y mayusculas para imitar el espiritu "display" condensado.
    public static final Font FUENTE_DISPLAY  = new Font("SansSerif", Font.BOLD, 22);
    public static final Font FUENTE_TITULO   = new Font("SansSerif", Font.BOLD, 15);
    public static final Font FUENTE_BOTON    = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FUENTE_LABEL    = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_CAMPO    = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_TABLA    = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_TABLA_HEADER = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FUENTE_TAB      = new Font("Segoe UI", Font.BOLD, 13);

    // ====== HELPERS DE ESTILO: BOTONES ======

    /** Boton principal de accion (equivalente a .btn-primary): fondo morado, texto blanco. */
    public static void estilizarBotonPrimario(AbstractButton btn) {
        estilizarBotonBase(btn);
        btn.setBackground(MORADO);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));

        btn.getModel().addChangeListener(e -> {
            ButtonModel m = btn.getModel();
            btn.setBackground(m.isRollover() ? LILA : MORADO);
        });
    }

    /** Boton secundario (equivalente a .btn): fondo transparente, borde gris, texto crema. */
    public static void estilizarBotonSecundario(AbstractButton btn) {
        estilizarBotonBase(btn);
        btn.setBackground(NEGRO);
        btn.setForeground(CREMA);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GRIS1, 1),
                BorderFactory.createEmptyBorder(8, 17, 8, 17)
        ));

        btn.getModel().addChangeListener(e -> {
            ButtonModel m = btn.getModel();
            btn.setForeground(m.isRollover() ? LILA : CREMA);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(m.isRollover() ? LILA : GRIS1, 1),
                    BorderFactory.createEmptyBorder(8, 17, 8, 17)
            ));
        });
    }

    /** Boton de accion destructiva (Eliminar): borde y texto en rojo. */
    public static void estilizarBotonPeligro(AbstractButton btn) {
        estilizarBotonBase(btn);
        btn.setBackground(NEGRO);
        btn.setForeground(ROJO_ERROR);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x5A, 0x2A, 0x2A), 1),
                BorderFactory.createEmptyBorder(8, 17, 8, 17)
        ));

        btn.getModel().addChangeListener(e -> {
            ButtonModel m = btn.getModel();
            btn.setBackground(m.isRollover() ? new Color(0x2A, 0x12, 0x12) : NEGRO);
        });
    }

    private static void estilizarBotonBase(AbstractButton btn) {
        btn.setFont(FUENTE_BOTON);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorderPainted(true);
        btn.setContentAreaFilled(true);
        // En Windows el LaF nativo a veces ignora setBackground en JButton
        // si el boton usa el pintado nativo del sistema. Forzamos UI tipo
        // "BasicButtonUI" (la base de Swing) para que respete nuestros colores.
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }

    // ====== HELPERS DE ESTILO: CAMPOS Y LABELS ======

    /** Aplica estilo BLOKK a un JTextField: fondo gris oscuro, texto crema, borde sutil. */
    public static void estilizarCampoTexto(JTextField campo) {
        campo.setBackground(GRIS1);
        campo.setForeground(CREMA);
        campo.setCaretColor(CREMA);
        campo.setFont(FUENTE_CAMPO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x44, 0x44, 0x44), 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }

    /** Aplica estilo BLOKK a un JLabel de formulario. */
    public static void estilizarLabel(JLabel label) {
        label.setForeground(GRIS3);
        label.setFont(FUENTE_LABEL);
    }

    /** Aplica estilo BLOKK a un JLabel tipo titulo de seccion (equivalente a .section-label). */
    public static void estilizarLabelSeccion(JLabel label) {
        label.setForeground(MORADO);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setText(label.getText().toUpperCase());
    }

    /**
     * Aplica estilo BLOKK a un JComboBox: fondo gris oscuro, texto crema,
     * lista desplegable tambien oscura con seleccion morada.
     */
    public static <T> void estilizarCombo(JComboBox<T> combo) {
        combo.setBackground(GRIS1);
        combo.setForeground(CREMA);
        combo.setFont(FUENTE_CAMPO);
        combo.setBorder(BorderFactory.createLineBorder(new Color(0x44, 0x44, 0x44), 1));
        combo.setFocusable(true);

        combo.setRenderer(new BasicComboBoxRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(isSelected ? MORADO : GRIS1);
                c.setForeground(isSelected ? Color.WHITE : CREMA);
                ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                return c;
            }
        });
    }

    // ====== HELPERS DE ESTILO: TABLA ======

    /**
     * Aplica el estilo completo de tabla BLOKK: fondo oscuro, zebra striping,
     * header negro con texto morado, seleccion en morado con texto blanco.
     */
    public static void estilizarTabla(JTable tabla) {
        tabla.setBackground(NEGRO);
        tabla.setForeground(CREMA);
        tabla.setFont(FUENTE_TABLA);
        tabla.setRowHeight(30);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionBackground(FILA_SELECCION);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(GRIS1);

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(NEGRO);
        header.setForeground(LILA);
        header.setFont(FUENTE_TABLA_HEADER);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, MORADO));
        header.setReorderingAllowed(false);

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s,
                    boolean f, int r, int c) {
                Component comp = super.getTableCellRendererComponent(t, v, s, f, r, c);
                comp.setForeground(s ? Color.WHITE : CREMA);
                if (!s) {
                    comp.setBackground(r % 2 == 0 ? FILA_PAR : FILA_IMPAR);
                } else {
                    comp.setBackground(FILA_SELECCION);
                }
                ((JLabel) comp).setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return comp;
            }
        });
    }

    /** Aplica fondo y borde estandar de panel BLOKK a un JScrollPane (envoltura de tabla). */
    public static void estilizarScroll(JScrollPane scroll) {
        scroll.setBackground(NEGRO);
        scroll.getViewport().setBackground(NEGRO);
        scroll.setBorder(BorderFactory.createLineBorder(GRIS1, 1));
    }

    // ====== HELPERS DE ESTILO: CONTENEDORES ======

    /** Panel base con fondo negro BLOKK y padding estandar. */
    public static JPanel panelBase() {
        JPanel panel = new JPanel();
        panel.setBackground(NEGRO);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        return panel;
    }

    /** Aplica el fondo negro estandar a cualquier contenedor (paneles internos). */
    public static void fondoNegro(JComponent comp) {
        comp.setBackground(NEGRO);
    }

    /** Crea un divisor visual morado tipo ".divider" (linea corta de 40px). */
    public static JComponent crearDivisor() {
        JPanel divisor = new JPanel();
        divisor.setBackground(MORADO);
        divisor.setPreferredSize(new Dimension(40, 2));
        divisor.setMaximumSize(new Dimension(40, 2));
        divisor.setOpaque(true);
        JPanel contenedor = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contenedor.setBackground(NEGRO);
        contenedor.add(divisor);
        return contenedor;
    }

    /**
     * Crea el bloque de encabezado estandar de cada pantalla, estilo BLOKK
     * (equivalente al "page-hero" de la web: section-label + titulo + divider).
     */
    public static JPanel crearEncabezado(String etiqueta, String titulo) {
        JLabel lblSeccion = new JLabel(etiqueta);
        estilizarLabelSeccion(lblSeccion);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(CREMA);
        lblTitulo.setFont(FUENTE_DISPLAY);

        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        fondoNegro(encabezado);
        encabezado.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        lblSeccion.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        encabezado.add(lblSeccion);
        encabezado.add(lblTitulo);
        encabezado.add(Box.createVerticalStrut(8));
        encabezado.add(crearDivisor());
        return encabezado;
    }

    // ====== HELPERS DE ESTILO: VENTANA PRINCIPAL ======

    /**
     * Aplica el tema oscuro BLOKK a un JTabbedPane (navegacion entre formularios).
     *
     * IMPORTANTE: en Windows, el Look and Feel nativo (WindowsLookAndFeel) pinta
     * las pestañas con su propio codigo nativo y ignora casi todos los valores
     * de UIManager.put(). Por eso forzamos aqui una UI base de Swing puro
     * (BasicTabbedPaneUI) que SI respeta los colores que le damos.
     */
    public static void estilizarTabs(JTabbedPane tabs) {
        tabs.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                    int x, int y, int w, int h, boolean isSelected) {
                g.setColor(isSelected ? MORADO : new Color(0x16, 0x16, 0x16));
                g.fillRect(x, y, w, h);
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                    int x, int y, int w, int h, boolean isSelected) {
                g.setColor(isSelected ? LILA : GRIS1);
                g.drawRect(x, y, w - 1, h - 1);
            }

            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                // Sin borde de contenido adicional, el panel interno ya tiene su propio fondo
            }

            @Override
            protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
                return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) + 10;
            }
        });

        tabs.setBackground(NEGRO);
        tabs.setForeground(CREMA);
        tabs.setFont(FUENTE_TAB);
        tabs.setOpaque(true);

        // Forzamos tambien el color de texto de cada pestaña individualmente,
        // ya que algunos LaF lo vuelven a pisar al repintar.
        for (int i = 0; i < tabs.getTabCount(); i++) {
            tabs.setForegroundAt(i, CREMA);
            tabs.setBackgroundAt(i, new Color(0x16, 0x16, 0x16));
        }
    }

    /** Aplica el tema oscuro BLOKK a la barra de menu superior (Archivo, etc.). */
    public static void estilizarMenuBar(JMenuBar menuBar) {
        menuBar.setBackground(NEGRO);
        menuBar.setForeground(CREMA);
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, GRIS1));
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            estilizarMenu(menuBar.getMenu(i));
        }
    }

    /** Aplica estilo BLOKK a un JMenu individual y a sus JMenuItem hijos. */
    public static void estilizarMenu(JMenu menu) {
        if (menu == null) return;
        menu.setBackground(NEGRO);
        menu.setForeground(CREMA);
        menu.setFont(FUENTE_LABEL);
        menu.getPopupMenu().setBackground(GRIS1);
        menu.getPopupMenu().setBorder(BorderFactory.createLineBorder(MORADO, 1));
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem item = menu.getItem(i);
            if (item != null) {
                item.setBackground(GRIS1);
                item.setForeground(CREMA);
                item.setFont(FUENTE_LABEL);
            }
        }
    }

    /** Configura un JOptionPane para que tome el tema oscuro antes de mostrar un dialogo. */
    public static void temaDialogo() {
        UIManager.put("OptionPane.background", NEGRO);
        UIManager.put("Panel.background", NEGRO);
        UIManager.put("OptionPane.messageForeground", CREMA);
        UIManager.put("Button.background", GRIS1);
        UIManager.put("Button.foreground", CREMA);
    }
}
