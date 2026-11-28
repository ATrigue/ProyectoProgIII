package gui;

import java.util.TreeMap;

import domain.Contenido;
import domain.GeneroMusical;
import domain.Musica;

public class PanelMusica extends PanelContenido {

	private static final long serialVersionUID = 1L;

	public PanelMusica(TreeMap<Long, Contenido> mapa) {
		super(mapa, "Música popular esta semana");
	}

	// LA MUSICA NO USA Genero SINO GeneroMusical, ASI QUE SE SOBRESCRIBEN LOS DOS METODOS DEL FILTRO DE GENERO

	@Override
	protected Object[] getGeneros() {
		return GeneroMusical.values();
	}

	@Override
	protected Object getGeneroDe(Contenido c) {
		return ((Musica) c).getGenMus();
	}
}
