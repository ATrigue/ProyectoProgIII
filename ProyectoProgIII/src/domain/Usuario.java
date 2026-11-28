package domain;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;

public class Usuario {
	private int codigo;	// Código identificativo del usuario
	private String nomUsuario;  //Nombre de usuario del usuario
	private ImageIcon fotoPerfil;   //Foto de perfil que desee poner el usuario
	private String descripcion;   //Descripcion de su perfil
	private TreeMap<Long, Usuario> mapAmigos;
	private TreeMap<Long, Libro> mapLibros;
	private TreeMap<Long, Audio> mapAudios;
	private TreeMap<Long, Pelicula> mapPelis;
	private TreeMap<Long, Serie> mapSeries;
	private TreeMap<Long, Musica> mapMusica;
	private ArrayList<Contenido> listaFavoritos;
	
	
	//Constructores
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(int codigo, String nomUsuario, ImageIcon fotoPerfil, String descripcion) {
		super();
		this.codigo = codigo;
		this.nomUsuario = nomUsuario;
		this.fotoPerfil = fotoPerfil;
		this.descripcion = descripcion;
		this.mapAmigos = new TreeMap<Long, Usuario>();
		this.mapLibros = new TreeMap<Long, Libro>();
		this.mapAudios = new TreeMap<Long, Audio>();
		this.mapPelis = new TreeMap<Long, Pelicula>();
		this.mapSeries = new TreeMap<Long, Serie>();
		this.mapMusica = new TreeMap<Long, Musica>();
		listaFavoritos = new ArrayList<Contenido>();
	}
	
	
	//Getters y setters
	public int getCodigo() {
		return codigo;
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public ImageIcon getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(ImageIcon fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TreeMap<Long, Usuario> getMapAmigos() {
		return mapAmigos;
	}	
	
	public TreeMap<Long, Libro> getMapLibros() {
		return mapLibros;
	}
	public TreeMap<Long, Audio> getMapAudios() {
		return mapAudios;
	}
	public TreeMap<Long, Pelicula> getMapPelis() {
		return mapPelis;
	}
	public TreeMap<Long, Serie> getMapSeries() {
		return mapSeries;
	}
	public TreeMap<Long, Musica> getMapMusica() {
		return mapMusica;
	}
	public ArrayList<Contenido> getListaFavoritos() {
		return listaFavoritos;
	}

	
	
	//To String
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nomUsuario=" + nomUsuario + ", fotoPerfil=" + fotoPerfil + ", descripcion=" + descripcion
				+ ", mapAmigos=" + mapAmigos + ", mapLibros=" + mapLibros + ", mapAudios=" + mapAudios + ", mapPelis=" 
				+ mapPelis + ", mapSeries=" + mapSeries + ", mapMusica=" + mapMusica + ", listaFavoritos=" + listaFavoritos  + "]";
	}
	

}
