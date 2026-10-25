package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelAjustes {

    public static JPanel crearPanelAjustes(JTabbedPane panelPestanas) {
        JPanel panelAjustes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JCheckBox modoOscuro = new JCheckBox("Modo Oscuro");

        modoOscuro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (modoOscuro.isSelected()) {
                    // Modo Oscuro
                    for (int i = 0; i < panelPestanas.getTabCount(); i++) {
                        Component comp = panelPestanas.getComponentAt(i);
                        aplicarColorInterno(comp, Color.DARK_GRAY, Color.WHITE);
                    }

                } else {
                    // Modo Claro
                    for (int i = 0; i < panelPestanas.getTabCount(); i++) {
                        Component comp = panelPestanas.getComponentAt(i);
                        aplicarColorInterno(comp, Color.WHITE, Color.BLACK);
                    }
                }

                panelPestanas.repaint();
            }
        });

        panelAjustes.add(modoOscuro);
        return panelAjustes;
    }

    // Metodo auxiliar recursivo que cambia el color de todos los elementos dentro de la pestaña
    private static void aplicarColorInterno(Component comp, Color fondo, Color texto) {
        // Le cambiamos el fondo a cualquier componente siempre
        comp.setBackground(fondo);

        // Si es un JPanel se recorren los elementos internos
        if (comp instanceof JPanel) {
            JPanel panel = (JPanel) comp;
            for (Component hijo : panel.getComponents()) {
                aplicarColorInterno(hijo, fondo, texto);
            }
        } 
        // 
        else {
            comp.setForeground(texto);
        }
    }
}