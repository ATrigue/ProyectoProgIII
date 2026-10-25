package gui;

import java.util.HashMap;

import domain.Audio;
import domain.Contenido;

public class PanelAudio extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelAudio(HashMap<Long, Contenido> mapa) {
		super(mapa, Audio.class, "Audio popular esta semana");
	}
}
