package gui;

import java.util.TreeMap;

import domain.Contenido;

public class PanelAudio extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelAudio(TreeMap<Long, Contenido> mapa) {
		super(mapa, "Audio popular esta semana");
	}
}
