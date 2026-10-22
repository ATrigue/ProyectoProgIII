package domain;

import java.time.LocalDate;

public class Musica extends Contenido {
	
	//  ~~~~~~ ATRIBUTOS ~~~~~~

	private GeneroMusical genMus;
	
	
	// ~~~~~~ CONSTRUCTOR ~~~~~~
		
	public Musica(long codIdent, String titulo, String autor, GeneroMusical genMus, Idioma idioma, LocalDate fechaPub, int duracion) {
		super(codIdent, titulo, autor, null, idioma, fechaPub, duracion);
		setGenMus(genMus);
	}

	
	
	
	// ~~~~~~ GETTERS Y SETTERS ~~~~~~
	
	public GeneroMusical getGenMus() {
		return genMus;
	}


	public void setGenMus(GeneroMusical genMus) {
		this.genMus = genMus;
	}
	
}
