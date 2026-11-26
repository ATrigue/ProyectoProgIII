package gui;

import java.util.TreeMap;

import domain.Contenido;

public class PanelMusica extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelMusica(TreeMap<Long, Contenido> mapa) {
		super(mapa, "Música popular esta semana");
	}
}
