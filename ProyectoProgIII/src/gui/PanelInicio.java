package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Contenido;
import domain.Musica;

public class PanelInicio {
	
	public static JPanel crearPanelInicio( HashMap<Long, Contenido> mapaContenidos ) {
		
		// Crear panel inicio 
		JPanel panelInicio = new JPanel( new GridLayout( 2, 1, 15, 15 ) );
		panelInicio.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 20));

		
		// Apartado de contenidos añadidos recientemente
		JPanel panelAnyadido = new JPanel( new BorderLayout(0, 15) );
		panelInicio.add( panelAnyadido );
		
		JPanel panelTituloAnya = new JPanel( new GridLayout( 1, 2, 5, 5 ) );	// Panel para la cabecera del apartado
		panelAnyadido.add( panelTituloAnya, BorderLayout.NORTH);
		
		JLabel labelTituloAnya = new JLabel( "Añadido recientemente", JLabel.LEFT );
		panelTituloAnya.add( labelTituloAnya );
		JLabel labelMasAnya = new JLabel( "Ver más", JLabel.RIGHT );
		labelMasAnya.setForeground( Color.BLUE );
		panelTituloAnya.add( labelMasAnya );
		
		JPanel panelDatosAnya = new JPanel( new GridLayout( 1, 6, 5, 5 ) );		// Panel para los contenidos del apartado
		panelDatosAnya.setBorder( BorderFactory.createEmptyBorder( 10, 15, 10, 15 ));
		 ArrayList<Contenido> contenidosPorFechaAnya = ordenarPorFechaAnya(mapaContenidos);
		for ( int i = 0; i < 6; i ++ ) {
			JPanel tarjeta = crearTarjeta( contenidosPorFechaAnya.removeFirst() );
			panelDatosAnya.add( tarjeta );
		}
		panelAnyadido.add(panelDatosAnya);		
				
		
		// Apartado de contenidos populares en este momento (tendencia)
		JPanel panelTendencia = new JPanel( new BorderLayout(0, 15) );
		panelInicio.add( panelTendencia );
		
		JPanel panelTituloTend = new JPanel( new GridLayout( 1, 2, 5, 5 ) );
		panelTendencia.add( panelTituloTend, BorderLayout.NORTH );
		
		JLabel labelTituloTend = new JLabel( "Tendencia", JLabel.LEFT );
		panelTituloTend.add( labelTituloTend );
		JLabel labelMasTend = new JLabel( "Ver más", JLabel.RIGHT );
		labelMasTend.setForeground( Color.BLUE );
		panelTituloTend.add( labelMasTend );
		
		JPanel panelDatosTend = new JPanel( new GridLayout( 1, 6, 5, 5 ) );		// Panel para los contenidos del apartado
		panelDatosTend.setBorder( BorderFactory.createEmptyBorder( 10, 15, 10, 15 ));
		 ArrayList<Contenido> contenidosPorTend = ordenarPorTendencia(mapaContenidos);
		for ( int i = 0; i < 6; i ++ ) {
			JPanel tarjeta = crearTarjeta( contenidosPorTend.removeFirst() );
			panelDatosTend.add( tarjeta );
		}
		panelTendencia.add(panelDatosTend);	
		
		return panelInicio;
		
	}
	
	
	private static ArrayList<Contenido> ordenarPorFechaAnya( HashMap<Long, Contenido> mapaContenidos ) {
		
		// Ordenar datos por fecha añadido
		ArrayList<Contenido> datosPorFecha = new ArrayList<Contenido>();
		for ( long cod : mapaContenidos.keySet() ) {
			datosPorFecha.add( mapaContenidos.get(cod) );
		}
				
		datosPorFecha.sort( new Comparator<Contenido>( ) {	// Ordena la lista de manera que esté ordenada por la fecha en la que se añadió el contenido.
			@Override
			public int compare(Contenido o1, Contenido o2) {
				return o1.getFechaAnya().compareTo(o2.getFechaAnya());
			}
		});
		return datosPorFecha;
		
	}
	
	private static ArrayList<Contenido> ordenarPorTendencia( HashMap<Long, Contenido> mapaContenidos ) {
		
		// Ordenar datos por fecha añadido
		ArrayList<Contenido> datosPorTendencia = new ArrayList<Contenido>();
		for ( long cod : mapaContenidos.keySet() ) {
			datosPorTendencia.add( mapaContenidos.get(cod) );
		}
				
		datosPorTendencia.sort( new Comparator<Contenido>( ) {	// Ordena la lista de manera que esté ordenada por la fecha en la que se añadió el contenido.
			@Override
			public int compare(Contenido o1, Contenido o2) {
				if ( o1.getContUser() > o2 .getContUser() ) {
					return -1;
				}
				else if ( o1.getContUser() < o2 .getContUser() ) {
					return 1;
				}
				return 0;
			}
		});
		return datosPorTendencia;
		
	}
	
	private static JPanel crearTarjeta(  Contenido cont ) {

		JPanel tarjeta = new JPanel(new BorderLayout(0, 5));
		tarjeta.setPreferredSize(new Dimension(105, 165));
		tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		String titulo = (cont.getTitulo() != null) ? cont.getTitulo() : "N/A";
		String autor = (cont.getAutorDirector() != null) ? cont.getAutorDirector() : "N/A";

		// PORTADA
		ImageIcon img = new ImageIcon( "resources/images/question-mark-transparent-grey-5.png" );
		Image imgEs = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH );
		ImageIcon imgDatosEscalada = new ImageIcon( imgEs );
		JLabel portada = new JLabel( imgDatosEscalada );
		tarjeta.add(portada, BorderLayout.CENTER);

		// TITULO Y AUTOR "
		JPanel panelTexto = new JPanel(new GridLayout(2, 1));
		panelTexto.setOpaque(false);
		panelTexto.add(new JLabel(titulo, SwingConstants.CENTER));
		panelTexto.add(new JLabel(autor, SwingConstants.CENTER));
		tarjeta.add(panelTexto, BorderLayout.SOUTH);

		tarjeta.setToolTipText(titulo + " - " + autor); //PARA PODER VER EL NOMBRE COMPLETO CUANDO PASA EL RATON POR ENCIMA

		return tarjeta;
	}
	
	
	
}
