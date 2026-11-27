package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import domain.Catalogo;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	public static final int ALTURA = 600;
	public static final int ANCHURA = 800;

	public Ventana( Catalogo catalogo ) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(ANCHURA, ALTURA); //TAMAÑO PANTALLA
		setLocationRelativeTo(null);

		setTitle("Mediateca"); //TITULO VENTANA

        //PESTAÑAS
        JTabbedPane panelPestanas = new JTabbedPane();


        panelPestanas.addTab("Inicio", PanelInicio.crearPanelInicio( catalogo ));
        panelPestanas.addTab("Peliculas", new PanelPelicula( catalogo.getPeliculas() ));
        panelPestanas.addTab("Series", new PanelSeries( catalogo.getSeries() ));
        panelPestanas.addTab("Libros", new PanelLibros( catalogo.getLibros() ));
        panelPestanas.addTab("Música", new PanelMusica( catalogo.getMusica() ));
        panelPestanas.addTab("Audio", new PanelAudio( catalogo.getAudios() ));
        panelPestanas.addTab("Perfil", PanelPerfil.crearPanelPerfil());
        panelPestanas.addTab("Ajustes", PanelAjustes.crearPanelAjustes(panelPestanas));

        add(panelPestanas);

		setVisible(true); //SIEMPRE TRUE
	}



}
