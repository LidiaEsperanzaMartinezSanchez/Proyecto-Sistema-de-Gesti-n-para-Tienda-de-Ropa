package clothesstoreordinario;

import Vistas.VentanaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ClothesStoreOrdinario {

    public static void main(String[] args) {
        // Lanza la interfaz grafica (Swing) de la Tienda de Ropa.
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Si falla, se usa el look and feel por defecto
            }
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
