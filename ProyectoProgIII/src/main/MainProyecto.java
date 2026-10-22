package main;

import java.time.LocalDate;
import java.util.HashMap;

import domain.Audio;
import domain.Contenido;
import domain.Genero;
import domain.Idioma;
import domain.Libro;
import domain.Musica;
import domain.Pelicula;
import domain.Serie;
import gui.Ventana;



public class MainProyecto {

	public static void main(String[] args) {
		
		HashMap<Long, Contenido> mapaContenidos = generarContenidosPrueba();
		
		Ventana v = new Ventana();
	}
	
	
	
	public static HashMap<Long, Contenido> generarContenidosPrueba() {
		
		HashMap<Long, Contenido> mapaContenidos = new HashMap<Long, Contenido>();
		
		// Libros
		Libro l1 = new Libro(9781401308773L, "Dead Poets Society", "N.H. Kleinbaum", Genero.DRAMA, Idioma.INGLES, LocalDate.of(1989, 1, 1), "Hyperion", 166);
		Libro l2 = new Libro(34, "bap", "bip", null, null, null, null, 0);
		mapaContenidos.put(l1.getCodIdent(), l1);
		mapaContenidos.put(l2.getCodIdent(), l2);
		
		// Películas
		Pelicula p1 = new Pelicula(56, "La Bola Negra", "Los Javis", null, Idioma.CASTELLANO, null, 150, null);
		Pelicula p2 = new Pelicula(54, "Tomates Verdes Fritos", null, null, null, null, 0, null);
		mapaContenidos.put(p1.getCodIdent(), p1);
		mapaContenidos.put(p2.getCodIdent(), p2);
		
		// Series
		Serie s1 = new Serie(03, "hm", null, null, null, null, 0, null);
		Serie s2 = new Serie(04, "wow", null, null, null, null, 0, null);
		mapaContenidos.put(s1.getCodIdent(), s1);
		mapaContenidos.put(s2.getCodIdent(), s2);
		
		// Música
		Musica m1 = new Musica(05, "laaaaalalalalalalaaaaa", null, null, null, null, 0);
		Musica m2 = new Musica(06, "laaaaalalalalalaaaalaaaaaaaa", null, null, null, null, 0);
		mapaContenidos.put(m1.getCodIdent(), m1);
		mapaContenidos.put(m2.getCodIdent(), m2);
		
		// Audio
		Audio a1 = new Audio(7, "The Magnus Archives", "Jonathan Sims", Genero.TERROR, Idioma.INGLES, null, 5000);
		Audio a2 = new Audio(8, "The Magnus Protocol", "Jonathan Sims & Alexander J. Newall", Genero.TERROR, Idioma.INGLES, null, 1710);
		mapaContenidos.put(a1.getCodIdent(), a1);
		mapaContenidos.put(a2.getCodIdent(), a2);

		
		return mapaContenidos;
	}
	
	
}
