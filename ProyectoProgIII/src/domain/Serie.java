package domain;

import java.time.LocalDate;

public class Serie extends Contenido {
	
	//  ~~~~~~ ATRIBUTOS ~~~~~~

	private String distibuidor = "N/A";
	
	
	// ~~~~~~ CONSTRUCTOR ~~~~~~
		
	public Serie(long codIdent, String titulo, String autor, Genero genero, Idioma idioma, LocalDate fechaPub, int duracion, String distribuidor) {
		super(codIdent, titulo, autor, genero, idioma, fechaPub, duracion);
	}

		
	// ~~~~~~ GETTERS Y SETTERS ~~~~~~
	public String getDistibuidor() {
		return distibuidor;
	}


	public void setDistibuidor(String distibuidor) {
		this.distibuidor = distibuidor;
	}
	
}
