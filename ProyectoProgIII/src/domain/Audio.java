package domain;

import java.time.LocalDate;

public class Audio extends Contenido{

	//  ~~~~~~ ATRIBUTOS ~~~~~~

	
	// ~~~~~~ CONSTRUCTOR ~~~~~~
		
	public Audio(long codIdent, String titulo, String autor, Genero genero, Idioma idioma, LocalDate fechaPub, int duracion) {
		super(codIdent, titulo, autor, genero, idioma, fechaPub, duracion);
	}
}
