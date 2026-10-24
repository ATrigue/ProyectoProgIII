package gui;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import domain.Contenido;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	public static final int ALTURA = 600;
	public static final int ANCHURA = 800;
	
	public Ventana( HashMap<Long, Contenido> mapaContenidos ) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(ANCHURA, ALTURA); //TAMAÑO PANTALLA
		setLocationRelativeTo(null);
		
		setTitle("Mediateca"); //TITULO VENTANA
		
        //PESTAÑAS
        JTabbedPane panelPestanas = new JTabbedPane();
        
        PanelMusica.setMapaContenidos(mapaContenidos);
        
        panelPestanas.addTab("Inicio", PanelInicio.crearPanelInicio( mapaContenidos ));
        panelPestanas.addTab("Peliculas", PanelPelicula.crearPanelInicio());
        panelPestanas.addTab("Series", PanelSeries.crearPanelSeries());   
        panelPestanas.addTab("Libros", PanelLibros.crearPanelLibros());
        panelPestanas.addTab("Música", PanelMusica.crearPanelMusica());
        panelPestanas.addTab("Audio", PanelAudio.crearPanelAudio());
        panelPestanas.addTab("Perfil", PanelPerfil.crearPanelPerfil());
        
        add(panelPestanas);
		
		setVisible(true); //SIEMPRE TRUE
	}
	


}
