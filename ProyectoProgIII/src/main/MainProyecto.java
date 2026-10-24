package main;

import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.ImageIcon;

import domain.Audio;
import domain.Contenido;
import domain.Genero;
import domain.GeneroMusical;
import domain.Idioma;
import domain.Libro;
import domain.Musica;
import domain.Pelicula;
import domain.Serie;
import gui.Ventana;


public class MainProyecto {

	public static void main(String[] args) {
		
		HashMap<Long, Contenido> mapaContenidos = generarContenidosPrueba();
		
		Ventana v = new Ventana( mapaContenidos );
	}
	
	
	
	public static HashMap<Long, Contenido> generarContenidosPrueba() {
		
		HashMap<Long, Contenido> mapaContenidos = new HashMap<Long, Contenido>();
		
		// Libros
		Libro l1 = new Libro(9781401308773L, "Dead Poets Society", "N.H. Kleinbaum", Genero.DRAMA, Idioma.INGLES, LocalDate.of(1989, 1, 1), "Hyperion", 166);
		Libro l2 = new Libro(9788491465843L, "Uzumaki", "Junji Ito", Genero.TERROR, Idioma.CASTELLANO, LocalDate.of(1998, 1, 19), "Planeta Manga", 656);
		l1.setContUser(10);
		l2.setContUser(32);
		l1.setImgIcon( new ImageIcon( "resources/images/dead-poets-society.jpg" ) );
		l2.setImgIcon( new ImageIcon( "resources/images/uzumaki.jpg" ) );

		mapaContenidos.put(l1.getCodIdent(), l1);
		mapaContenidos.put(l2.getCodIdent(), l2);
		
		// Películas
		Pelicula p1 = new Pelicula(01, "La Bola Negra", "Los Javis", Genero.DRAMA, Idioma.CASTELLANO, LocalDate.of(2026, 9, 25), 155, "Movistar+");
		Pelicula p2 = new Pelicula(02, "Tomates Verdes Fritos", "Jon Avnet", Genero.DRAMA, Idioma.CASTELLANO, LocalDate.of(1991, 11, 27), 136, "Universal Pictures");
		p1.setContUser(1937);
		p2.setContUser(20);
		p1.setImgIcon( new ImageIcon( "resources/images/la_bola_negra.jpg" ) );
		p2.setImgIcon( new ImageIcon( "resources/images/fried_green_tomatoes.jpg" ) );
		mapaContenidos.put(p1.getCodIdent(), p1);
		mapaContenidos.put(p2.getCodIdent(), p2);
		
		// Series
		Serie s1 = new Serie(03, "Good Omens", "Douglas Mackinnon", Genero.COMEDIA, Idioma.INGLES, LocalDate.of(2019, 5, 31), 340, "BBC Studios");
		Serie s2 = new Serie(04, "The Owl House", "Dana Terrace", Genero.FANTASIA, Idioma.INGLES, LocalDate.of(2020, 1, 10), 900, "Disney");
		s1.setImgIcon( new ImageIcon( "resources/images/good_omens.jpg" ) );
		s2.setImgIcon( new ImageIcon( "resources/images/the_owl_house.jpg" ) );
		mapaContenidos.put(s1.getCodIdent(), s1);
		mapaContenidos.put(s2.getCodIdent(), s2);
		
		// Música
		Musica m1 = new Musica(05, "Ferragamo", "Trigue", GeneroMusical.TRAP, Idioma.CASTELLANO, LocalDate.of(2000, 7, 1), 0);
		Musica m2 = new Musica(06, "laaaaalalalalalaaaalaaaaaaaa", "Oprheus", GeneroMusical.CLASICA, Idioma.INGLES, LocalDate.of(2016, 5, 6), 4);
		Musica m3 = new Musica(9, "La Nieve", "Guitarricadelafuente", GeneroMusical.CLASICA, Idioma.CASTELLANO, LocalDate.of(2026, 9, 18), 0);

		m1.setImgIcon(new ImageIcon("resources/images/ferragamo.jpeg"));
		m3.setImgIcon(new ImageIcon("resources/images/la_nieve.jpeg"));
		
		mapaContenidos.put(m1.getCodIdent(), m1);
		mapaContenidos.put(m2.getCodIdent(), m2);
		mapaContenidos.put(m3.getCodIdent(), m3);


		// Audio
		Audio a1 = new Audio(7, "The Magnus Archives", "Jonathan Sims", Genero.TERROR, Idioma.INGLES, LocalDate.of(2016, 3, 24), 5000);
		Audio a2 = new Audio(8, "The Magnus Protocol", "Jonathan Sims & Alexander J. Newall", Genero.TERROR, Idioma.INGLES, LocalDate.of(2024, 10, 18), 1710);
		a1.setContUser(58);
		a1.setImgIcon( new ImageIcon( "resources/images/magnus_archives.jpg" ) );
		a2.setImgIcon( new ImageIcon( "resources/images/magnus_protocol.jpg" ) );
		mapaContenidos.put(a1.getCodIdent(), a1);
		mapaContenidos.put(a2.getCodIdent(), a2);

		
		return mapaContenidos;
	}
	
	
}
