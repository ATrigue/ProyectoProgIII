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
        
        panelPestanas.addTab("Inicio", PanelInicio.crearPanelInicio( mapaContenidos ));
        panelPestanas.addTab("Peliculas", new PanelPelicula( mapaContenidos ));
        panelPestanas.addTab("Series", new PanelSeries( mapaContenidos ));   
        panelPestanas.addTab("Libros", new PanelLibros( mapaContenidos ));
        panelPestanas.addTab("Música", new PanelMusica( mapaContenidos ));
        panelPestanas.addTab("Audio", new PanelAudio( mapaContenidos ));
        panelPestanas.addTab("Perfil", PanelPerfil.crearPanelPerfil());
        
        add(panelPestanas);
		
		setVisible(true); //SIEMPRE TRUE
	}
}