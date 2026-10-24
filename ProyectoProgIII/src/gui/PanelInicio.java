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
		panelInicio.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

		
		// Apartado de contenidos añadidos recientemente
		JPanel panelAnyadido = new JPanel( );
		panelInicio.add( panelAnyadido );
		
		JPanel panelTituloAnya = new JPanel( new GridLayout( 1, 2, 5, 5 ) );	// Panel para la cabecera del apartado
		panelAnyadido.add( panelTituloAnya );
		
		JLabel labelTituloAnya = new JLabel( "Añadido recientemente", JLabel.LEFT );
		panelTituloAnya.add( labelTituloAnya );
		JLabel labelMasAnya = new JLabel( "Ver más", JLabel.RIGHT );
		labelMasAnya.setForeground( Color.BLUE );
		panelTituloAnya.add( labelMasAnya );
		
		JPanel panelDatosAnya = new JPanel( new GridLayout( 1, 6, 5, 5 ) );		// Panel para los contenidos del apartado
		panelDatosAnya.setBorder( BorderFactory.createEmptyBorder( 10, 15, 10, 15 ));
		for ( int i = 0; i < 6; i ++ ) {
//			JLabel lb = new JLabel( ordenarPorFechaAnya(mapaContenidos).removeFirst().getImgIcon() );		// TODO Averiguar por qué se escalan así las imágenes y arreglar
//			lb.setOpaque(true);
//			panelDatosAnya.add(lb);
			JPanel tarjeta = crearTarjeta( ordenarPorFechaAnya(mapaContenidos).removeFirst() );
			panelDatosAnya.add( tarjeta );
		}
		panelAnyadido.add(panelDatosAnya);		
				
		
		// Apartado de contenidos populares en este momento (tendencia)
		JPanel panelTendencia = new JPanel( );
		panelTendencia.setBackground( Color.BLUE);
		panelInicio.add( panelTendencia );
		
		JPanel panelTituloTend = new JPanel();
		
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

		// TITULO Y AUTOR (SI NO CABEN LOS ACORTA CON "..."
		JPanel panelTexto = new JPanel(new GridLayout(2, 1));
		panelTexto.setOpaque(false);
		panelTexto.add(new JLabel(titulo, SwingConstants.CENTER));
		panelTexto.add(new JLabel(autor, SwingConstants.CENTER));
		tarjeta.add(panelTexto, BorderLayout.SOUTH);

		tarjeta.setToolTipText(titulo + " - " + autor); //FUNCION PARA PODER VER EL NOMBRE COMPLETO CUANDO PASA EL RATON POR ENCIMA

		return tarjeta;
	}
	
	
	
}
