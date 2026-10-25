package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import domain.Audio;
import domain.Contenido;
import domain.Musica;

public class VentanaMostrarContenido extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int ALTURA = 600;
	public static final int ANCHURA = 800;
	
	private Contenido c;
	
	public VentanaMostrarContenido(Contenido cont) {
		this.c = cont;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(ANCHURA, ALTURA); //TAMAÑO PANTALLA
		setLocationRelativeTo(null);
		
		setTitle(c.getTitulo()); //TITULO VENTANA
		
		//Tabla de detalles que aparece al clickar en la portada de un contenido
		ModeloTabla modelo = new ModeloTabla();
		
		setLayout(new BorderLayout());
		
		JTable tabla = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 150)); //Limita el tamaño vertical del scrollpane
		
		JPanel panelDetalles = new JPanel(new BorderLayout(5, 5));
		panelDetalles.add(scrollPane, BorderLayout.NORTH);
		
		//Imágenes de portada
		ImageIcon img = cont.getImgIcon();
		Image imgEs; 
		if ( cont instanceof Musica || cont instanceof Audio ) {
			imgEs = img.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); //Escala las portadas
		}
		else {
			imgEs = img.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH); //Escala las portadas
		}
		ImageIcon imgDatosEscalada = new ImageIcon(imgEs);
		JLabel portada = new JLabel(imgDatosEscalada);
		
		panelDetalles.add(portada);
		add(panelDetalles, BorderLayout.CENTER); 
		
		//Botón para volver
		JPanel panelBoton = new JPanel();
		JButton botonVolver = new JButton("Volver");
		
		botonVolver.addActionListener(new ActionListener() {   //Cierra la ventana actual
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panelBoton.add(botonVolver);
		add(panelBoton, BorderLayout.SOUTH);
		
		setVisible(true); //SIEMPRE TRUE
	}
	
	class ModeloTabla extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		
		@Override
		public int getRowCount() {
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
	            if (rowIndex == 0) 
	            	return "Código Identificativo";
	            else if (rowIndex == 1) 
	            	return "Titulo";
	            else if (rowIndex == 2)
	            	return "Autor(es)/Director(es)";
	            else if (rowIndex == 3) 
	            	return "Género";
	            else if (rowIndex == 4) 
	            	return "Idioma";
	            else if (rowIndex == 5) 
	            	return "Fecha de Publicación";
	            else if (rowIndex == 6) 
	            	return "Duración";
	            else return "Fecha de Añadido";
			} else {
				if (rowIndex == 0) 
	            	return c.getCodIdent();
	            else if (rowIndex == 1) 
	            	return c.getTitulo();
	            else if (rowIndex == 2)
	            	return c.getAutorDirector();
	            else if (rowIndex == 3) {
	            	if (c instanceof Musica) {
	            		Musica m = (Musica) c;
	            		return m.getGenMus();    // HACE QUE EN EL CASO DE QUE SEA MUSICA, DIGA EL GENERO, SI NO SALÍA COMO NULL
	            	} else {
	            		return c.getGenero();
	            	}
	            }
	            else if (rowIndex == 4) 
	            	return c.getIdioma();
	            else if (rowIndex == 5) 
	            	return c.getFechaPub();
	            else if (rowIndex == 6) 
	            	return c.getDuracion();
	            else return c.getFechaAnya();
			}
		
		}
		@Override
			public String getColumnName(int column) {
				if (column == 0) return "Detalles";
				else return "";
			}
	}
}
