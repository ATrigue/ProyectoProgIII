package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import domain.Contenido;

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
		
		setTitle("Editar contenido"); //TITULO VENTANA
		
		ModeloTabla modelo = new ModeloTabla();
		
		JTable tabla = new JTable(modelo);
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBackground(Color.RED);
		
		add(scrollPane);
		
		JPanel panelBoton = new JPanel();
		JButton botonVolver = new JButton("Volver");
		
		botonVolver.addActionListener(new ActionListener() {   //Cierra la ventana actual
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			if (columnIndex == 0) {
	            if (rowIndex == 0) 
	            	return "Código Identificativo";
	            else if (rowIndex == 1) 
	            	return "Titulo";
	            else if (rowIndex == 2)
	            	return "Autor/Director";
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
	            else if (rowIndex == 3) 
	            	return c.getGenero();
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
				if (column == 0) return "Detalle";
				else return "Valor";
			}
	}
}
