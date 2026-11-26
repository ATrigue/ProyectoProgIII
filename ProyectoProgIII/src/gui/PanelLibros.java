package gui;

import java.util.TreeMap;

import domain.Contenido;

public class PanelLibros extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelLibros(TreeMap<Long, Contenido> mapa) {
		super(mapa, "Libros populares esta semana");
	}
}
