package domain;

import java.time.LocalDate;

public class Libro extends Contenido {

	//  ~~~~~~ ATRIBUTOS ~~~~~~

	private String editorial = "N/A";
	private int paginas;
	
	
	// ~~~~~~ CONSTRUCTOR ~~~~~~
		
	public Libro(long codIdent, String titulo, String autor, Genero genero, Idioma idioma, LocalDate fechaPub, String editorial, int paginas) {
		super(codIdent, titulo, autor, genero, idioma, fechaPub, 0);
		setEditorial(editorial);
		setPaginas(paginas);
	}


	
	
	
	// ~~~~~~ GETTERS Y SETTERS ~~~~~~
	
	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public int getPaginas() {
		return paginas;
	}


	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	

}
