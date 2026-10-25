package gui;

import java.util.HashMap;

import domain.Contenido;
import domain.Libro;

public class PanelLibros extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelLibros(HashMap<Long, Contenido> mapa) {
		super(mapa, Libro.class, "Libros populares esta semana");
	}
}