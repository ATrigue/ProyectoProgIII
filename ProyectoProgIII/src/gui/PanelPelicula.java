package gui;

import java.util.HashMap;

import domain.Contenido;
import domain.Pelicula;

public class PanelPelicula extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelPelicula(HashMap<Long, Contenido> mapa) {
		super(mapa, Pelicula.class, "Películas populares esta semana");
	}
}