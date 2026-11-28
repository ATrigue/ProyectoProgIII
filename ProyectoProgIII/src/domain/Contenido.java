package domain;

import java.time.LocalDate;

import javax.swing.ImageIcon;

public class Contenido {

	//  ~~~~~~ ATRIBUTOS ~~~~~~
	
	private long codIdent = 0;		// Código identificativo del contenido: es el ID de su tabla en la base de datos (se repite entre categorías, pero no dentro de una misma).
	private ImageIcon imgIcon = new ImageIcon( "resources/images/question-mark-transparent-grey-5.png" ); // Imagen default.
	private String titulo = "N/A";
	private String autorDirector = "N/A";	// En película y serie guarda el director
	private Genero genero;	// Se ignora en Música y se utiliza género musical en su lugar
	private Idioma idioma;
	private LocalDate fechaPub;
	private int duracion; 	// Se ignora en Libro 	
	private LocalDate fechaAnya; // Fecha en la que se añadió a la cuenta.
	private float calif = 0;	// Calificación media
	private int contUser = 0;	// Indica cuántos usuarios lo han añadido a su bilioteca virtual.
	
	
	
	
	// ~~~~~~ CONSTRUCTOR ~~~~~~
	
	public Contenido(long codIdent, String titulo, String autorDirector, Genero genero, Idioma idioma, LocalDate fechaPub, int duracion) {
		setCalif(codIdent);
		setTitulo(titulo);
		setAutorDirector(autorDirector);
		setGenero(genero);
		setIdioma(idioma);
		setFechaPub(fechaPub);
		setDuracion(duracion);
		fechaAnya = LocalDate.now();
	}	
	
	
	
	
	// ~~~~~~ GETTERS Y SETTERS ~~~~~~
		
	public long getCodIdent() {
		return codIdent;
	}
	public void setCodIdent(long codIdent) {
		this.codIdent = codIdent;
	}
	
	public ImageIcon getImgIcon() {
		return imgIcon;
	}
	public void setImgIcon(ImageIcon imgIcon) {
		this.imgIcon = imgIcon;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		if ( titulo == null ) {
			this.titulo = "N/A";
			return;
		}
		this.titulo = titulo;
	}
	public String getAutorDirector() {
		return autorDirector;
	}
	public void setAutorDirector(String autorDirector) {
		if ( autorDirector == null ) {
			this.autorDirector = "N/A";
			return;
		}
		this.autorDirector = autorDirector;
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
	public LocalDate getFechaAnya() {
		return fechaAnya;
	}
	public float getCalif() {
		return calif;
	}
	public void setCalif(float calif) {
		if ( calif > 0 && calif <= 5) {
			this.calif = calif;
		}
	}
	public int getContUser() {
		return contUser;
	}
	public void setContUser( int contUser ) {
		this.contUser = contUser;
	}
	public void incrementaContUser() {
		this.contUser++;
	}
	public void decrementaContUser() {
		this.contUser--;
	}

	
	
	// ~~~~~~ TOSTRING Y EQUALS ~~~~~~
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": codIdent=" + codIdent + ", titulo=" + titulo + ", genero=" + genero + ", idioma=" + idioma + ", fechaPub=" + fechaPub + ", fechaAnya=" + fechaAnya;
	}
		
		
	/**
	 * Dos contenidos son iguales si son de la misma categoría (libro, película...) y tienen el mismo código de identificación.
	 * Se compara también la categoría porque el código solo es único dentro de su categoría
	 * (puede existir un libro 1 y una película 1).
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj==null) {
			return false;
		} else if (obj instanceof Contenido) {
			Contenido c2 = (Contenido) obj;
			return  ( this.getClass() == c2.getClass() && codIdent == c2.getCodIdent() );
		} else {
			return false;
		}
	}

	/**
	 * Siempre que se sobrescribe equals hay que sobrescribir también hashCode, con los mismos datos.
	 */
	@Override
	public int hashCode() {
		return Long.hashCode( codIdent ) * 31 + this.getClass().getName().hashCode();
	}

}
