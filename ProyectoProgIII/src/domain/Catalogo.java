package domain;

import java.util.ArrayList;
import java.util.TreeMap;

public class Catalogo {

	//  ~~~~~~ ATRIBUTOS ~~~~~~

	private TreeMap<Long, Contenido> libros = new TreeMap<Long, Contenido>();
	private TreeMap<Long, Contenido> peliculas = new TreeMap<Long, Contenido>();
	private TreeMap<Long, Contenido> series = new TreeMap<Long, Contenido>();
	private TreeMap<Long, Contenido> musica = new TreeMap<Long, Contenido>();
	private TreeMap<Long, Contenido> audios = new TreeMap<Long, Contenido>();




	// ~~~~~~ GETTERS ~~~~~~

	public TreeMap<Long, Contenido> getLibros() {
		return libros;
	}
	public TreeMap<Long, Contenido> getPeliculas() {
		return peliculas;
	}
	public TreeMap<Long, Contenido> getSeries() {
		return series;
	}
	public TreeMap<Long, Contenido> getMusica() {
		return musica;
	}
	public TreeMap<Long, Contenido> getAudios() {
		return audios;
	}




	// ~~~~~~ MÉTODOS ~~~~~~

	public ArrayList<Contenido> getTodos() {
		ArrayList<Contenido> todos = new ArrayList<Contenido>();
		todos.addAll( libros.values() );
		todos.addAll( peliculas.values() );
		todos.addAll( series.values() );
		todos.addAll( musica.values() );
		todos.addAll( audios.values() );
		return todos;
	}

}
