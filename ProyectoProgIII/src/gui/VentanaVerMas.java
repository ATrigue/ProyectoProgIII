package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import domain.Audio;
import domain.Contenido;
import domain.Musica;

public class VentanaVerMas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Para elegir cual de las dos opciones se quiere mostrar.
	public static final int POR_ANYADIDO_RECIENTEMENTE = 0;
	public static final int POR_TENDENCIA = 1;

	public VentanaVerMas( ArrayList<Contenido> contenidosOrdenados, int modo ) {
		
		if ( modo < 0 || modo > 1 ) {
			return;
		}
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setSize( 800, 600 );
		setLocationRelativeTo( null );
		
		
		JPanel panel = new JPanel( new BorderLayout( 15, 15 ) );
		this.add(panel, BorderLayout.NORTH);
		
		JLabel labelTitulo;		
		if ( modo == POR_ANYADIDO_RECIENTEMENTE ) {
			setTitle( "Añadido recientemente" );
			labelTitulo = new JLabel( "Últimos 30 contenidos añadidos:", JLabel.LEFT );
		}
		else {
			setTitle( "Tendencia" );
			labelTitulo = new JLabel( "30 contenidos más consumidos en este momento:", JLabel.LEFT );
		}
		labelTitulo.setBorder( BorderFactory.createEmptyBorder(10, 15, 10, 15) );
		panel.add( labelTitulo, BorderLayout.CENTER );
		
		//Botón "Volver"
		JButton botonVolver = new JButton("Volver");
		
		botonVolver.addActionListener(new ActionListener() {   //Cierra la ventana actual
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(botonVolver, BorderLayout.WEST);

		JPanel panelDatosAnyadido = new JPanel( new GridLayout( 6, 5, 5, 5 ) );		// Panel para los contenidos del apartado
		panelDatosAnyadido.setBorder( BorderFactory.createEmptyBorder( 10, 15, 10, 15 ));

		for ( int i = 0; i < 30 && !contenidosOrdenados.isEmpty(); i ++ ) {
			JPanel tarjeta = crearTarjeta( contenidosOrdenados.removeFirst() );
			panelDatosAnyadido.add( tarjeta );
		}
		
		JScrollPane scrollPane = new JScrollPane( panelDatosAnyadido );
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		setVisible( true );
		
		
	}
	
	private static JPanel crearTarjeta(  Contenido cont ) {

		JPanel tarjeta = new JPanel(new BorderLayout(0, 5));
		tarjeta.setPreferredSize(new Dimension(105, 165));
		tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		String titulo = (cont.getTitulo() != null) ? cont.getTitulo() : "N/A";
		String autor = (cont.getAutorDirector() != null) ? cont.getAutorDirector() : "N/A";

		// PORTADA
		ImageIcon img = cont.getImgIcon();
		Image imgEs;
		if ( cont instanceof Musica || cont instanceof Audio ) {
			imgEs = img.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH );
		}
		else {
			imgEs = img.getImage().getScaledInstance(90, 125, Image.SCALE_SMOOTH );
		}
		ImageIcon imgDatosEscalada = new ImageIcon( imgEs );
		JLabel portada = new JLabel( imgDatosEscalada );
		
		portada.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );	// MANITO AL PASAR EL RATON POR ENCIMA
		portada.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new VentanaMostrarContenido(cont);
			}
		});
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
