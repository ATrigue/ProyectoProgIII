package gui;

import java.util.HashMap;

import domain.Contenido;
import domain.Serie;

public class PanelSeries extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelSeries(HashMap<Long, Contenido> mapa) {
		super(mapa, Serie.class, "Series populares esta semana");
	}
}