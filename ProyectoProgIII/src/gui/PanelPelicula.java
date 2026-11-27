package gui;

import java.util.TreeMap;

import domain.Contenido;

public class PanelPelicula extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelPelicula(TreeMap<Long, Contenido> mapa) {
		super(mapa, "Películas populares esta semana");
	}
}
