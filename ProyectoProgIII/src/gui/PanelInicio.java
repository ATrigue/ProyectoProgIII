package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Contenido;

public class PanelInicio {
	
	public static JPanel crearPanelInicio( HashMap<Long, Contenido> mapaContenidos ) {
		
		// Ordenar datos por fecha añadido
		//TreeMap<LocalDate, Contenido> datosPorFecha = new TreeMap<>();
		ArrayList<Contenido> datosPorFecha = new ArrayList<Contenido>();
		for ( long cod : mapaContenidos.keySet() ) {
			//datosPorFecha.put( mapaContenidos.get(cod).getFechaAnya() , mapaContenidos.get(cod) );
			datosPorFecha.add( mapaContenidos.get(cod) );
		}
		System.out.println( datosPorFecha );
		
		datosPorFecha.sort( new Comparator<Contenido>( ) {	// Ordena la lista de manera que esté ordenada por la fecha en la que se añadió el contenido.
			@Override
			public int compare(Contenido o1, Contenido o2) {
				return o1.getFechaAnya().compareTo(o2.getFechaAnya());
			}
		});
		System.out.println( datosPorFecha );
		
				
		JPanel panelInicio = new JPanel( new GridLayout( 2, 1, 15, 15 ) );
		
		// Apartado de contenidos añadidos recientemente
		JPanel panelAnyadido = new JPanel( new BorderLayout( 10, 10 ) );
		panelInicio.add( panelAnyadido );
		
		JPanel panelTituloAnya = new JPanel( new GridLayout( 1, 2, 5, 5 ) );
		panelAnyadido.add( panelTituloAnya, BorderLayout.NORTH );
		
		JLabel labelTituloAnya = new JLabel( "      Añadido recientemente", JLabel.LEFT );
		panelTituloAnya.add( labelTituloAnya );
		JLabel labelMasAnya = new JLabel( "Ver más      ", JLabel.RIGHT );
		labelMasAnya.setForeground( Color.BLUE );
		panelTituloAnya.add( labelMasAnya );
		
		JPanel panelDatosAnya = new JPanel( new GridLayout( 1, 6, 5, 5 ) );
		for ( int i = 0; i < 6; i ++ ) {
			JLabel lb = new JLabel( datosPorFecha.removeFirst().getImgIcon() );		// TODO Averiguar por qué se escalan así las imágenes y arreglar
			lb.setOpaque(true);
			lb.setBackground( Color.pink );
			panelDatosAnya.add(lb);
		}
		panelAnyadido.add(panelDatosAnya);		
		
		// Apartado de contenidos populares en este momento (tendencia)
		JPanel panelTendencia = new JPanel( );
		panelTendencia.setBackground( Color.BLUE);
		panelInicio.add( panelTendencia );
		
		return panelInicio;
		
	}
}
