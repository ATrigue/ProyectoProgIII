package domain;

import java.time.LocalDate;

public class Serie extends Contenido {

	//  ~~~~~~ ATRIBUTOS ~~~~~~

	private String distribuidor = "N/A";


	// ~~~~~~ CONSTRUCTOR ~~~~~~

	public Serie(long codIdent, String titulo, String autor, Genero genero, Idioma idioma, LocalDate fechaPub, int duracion, String distribuidor) {
		super(codIdent, titulo, autor, genero, idioma, fechaPub, duracion);
		setDistribuidor(distribuidor);
	}


	// ~~~~~~ GETTERS Y SETTERS ~~~~~~
	public String getDistribuidor() {
		return distribuidor;
	}


	public void setDistribuidor(String distribuidor) {
		if ( distribuidor == null ) {
			this.distribuidor = "N/A";
			return;
		}
		this.distribuidor = distribuidor;
	}

}
