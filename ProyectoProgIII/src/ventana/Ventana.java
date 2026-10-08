package ventana;

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
        
        panelPestanas.addTab("Inicio", VentanaInicio.crearPanelInicio());
        panelPestanas.addTab("Peliculas", VentanaPelicula.crearPanelInicio());
        panelPestanas.addTab("Series", VentanaSeries.crearPanelSeries());   
        panelPestanas.addTab("Libros", VentanaSeries.crearPanelSeries());
        panelPestanas.addTab("Música", VentanaSeries.crearPanelSeries());
        panelPestanas.addTab("Audio", VentanaSeries.crearPanelSeries());
        panelPestanas.addTab("Perfil", VentanaSeries.crearPanelSeries());
        
        add(panelPestanas, BorderLayout.NORTH);
		
		setVisible(true); //SIEMPRE TRUE
	}
	


	public static void main(String[] args) {
		new Ventana();
	}

}
