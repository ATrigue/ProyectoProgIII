package gui;

import java.util.HashMap;

import domain.Contenido;
import domain.Musica;

public class PanelMusica extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelMusica(HashMap<Long, Contenido> mapa) {
		super(mapa, Musica.class, "Música popular esta semana");
	}
}