package domain;

import java.time.LocalDate;

public class Contenido {

	//  ~~~~~~ ATRIBUTOS ~~~~~~
	
	// FALTA UN ATRIBUTO PARA LA IMAGEN
	private long codIdent = 0;		// Código identificativo del contenido, como pueden ser el ISBN o el ISAN.
	private String titulo = "N/A";
	private String autorDirector = "N/A";	// En película y serie guarda el director
	private Genero genero;	// Se ignora en Música y se utiliza género musical en su lugar
	private Idioma idioma;
	private LocalDate fechaPub;
	private int duracion; 	// Se ignora en Libro 	
	
	
	
	
	// ~~~~~~ CONSTRUCTOR ~~~~~~
	
	public Contenido(long codIdent, String titulo, String autor, Genero genero, Idioma idioma, LocalDate fechaPub, int duracion) {
		setCodIdent(codIdent);
		setTitulo(titulo);
		setAutor(autor);
		setGenero(genero);
		setIdioma(idioma);
		setFechaPub(fechaPub);
		setDuracion(duracion);
	}	
	
	
	
	
	// ~~~~~~ GETTERS Y SETTERS ~~~~~~
		
	public long getCodIdent() {
		return codIdent;
	}
	public void setCodIdent(long codIdent) {
		this.codIdent = codIdent;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autorDirector;
	}
	public void setAutor(String autor) {
		this.autorDirector = autor;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	public LocalDate getFechaPub() {
		return fechaPub;
	}
	public void setFechaPub(LocalDate fechaPub) {
		this.fechaPub = fechaPub;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	
	
	// ~~~~~~ TOSTRING Y EQUALS ~~~~~~
	
	

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": codIdent=" + codIdent + ", titulo=" + titulo + ", genero=" + genero + ", idioma=" + idioma + ", fechaPub=" + fechaPub;
	}
		
		
	/**
	 * Dos productos son iguales si son iguales sus códigos de identificación.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj==null) {
			return false;
		} else if (obj instanceof Contenido) {
			Contenido c2 = (Contenido) obj;
			return  ( codIdent == c2.getCodIdent() );
		} else {
			return false;
		}
	}
	
}
