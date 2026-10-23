package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Ventana() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600); //TAMAÑO PANTALLA
		setLocationRelativeTo(null);
		
		setTitle("Mediateca"); //TITULO VENTANA
		
        //PESTAÑAS
        JTabbedPane panelPestanas = new JTabbedPane();
        
        panelPestanas.addTab("Inicio", PanelInicio.crearPanelInicio());
        panelPestanas.addTab("Peliculas", PanelPelicula.crearPanelInicio());
        panelPestanas.addTab("Series", PanelSeries.crearPanelSeries());   
        panelPestanas.addTab("Libros", PanelSeries.crearPanelSeries());
        panelPestanas.addTab("Música", PanelSeries.crearPanelSeries());
        panelPestanas.addTab("Audio", PanelSeries.crearPanelSeries());
        panelPestanas.addTab("Perfil", PanelSeries.crearPanelSeries());
        
        add(panelPestanas);
		
		setVisible(true); //SIEMPRE TRUE
	}
	


}
