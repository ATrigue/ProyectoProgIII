package gui;

import java.util.TreeMap;

import domain.Contenido;

public class PanelSeries extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelSeries(TreeMap<Long, Contenido> mapa) {
		super(mapa, "Series populares esta semana");
	}
}
