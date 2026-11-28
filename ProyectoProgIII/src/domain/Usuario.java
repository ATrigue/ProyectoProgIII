package domain;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;

public class Usuario {
	private int codigo;	// Código identificativo del usuario
	private String nomUsuario;  //Nombre de usuario del usuario
	private ImageIcon fotoPerfil;   //Foto de perfil que desee poner el usuario
	private String descripcion;   //Descripcion de su perfil
	private TreeMap<Integer, Usuario> mapAmigos;
	private TreeMap<Integer, Libro> mapLibros;
	private TreeMap<Integer, Audio> mapAudios;
	private TreeMap<Integer, Pelicula> mapPelis;
	private TreeMap<Integer, Serie> mapSeries;
	private TreeMap<Integer, Musica> mapMusica;
	private ArrayList<Contenido> listaFavoritos;
	
	
	//Constructores
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(int codigo, String nomUsuario, ImageIcon fotoPerfil, String descripcion) {
		super();
		this.nomUsuario = nomUsuario;
		this.fotoPerfil = fotoPerfil;
		this.descripcion = descripcion;
		this.mapAmigos = new TreeMap<Integer, Usuario>();
		this.mapLibros = new TreeMap<Integer, Libro>();
		this.mapAudios = new TreeMap<Integer, Audio>();
		this.mapPelis = new TreeMap<Integer, Pelicula>();
		this.mapSeries = new TreeMap<Integer, Serie>();
		this.mapMusica = new TreeMap<Integer, Musica>();
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
	public TreeMap<Integer, Usuario> getMapAmigos() {
		return mapAmigos;
	}
	public void setMapAmigos(TreeMap<Integer, Usuario> mapAmigos) {
		this.mapAmigos = mapAmigos;
	}	
	
	public TreeMap<Integer, Libro> getMapLibros() {
		return mapLibros;
	}
	public TreeMap<Integer, Audio> getMapAudios() {
		return mapAudios;
	}
	public TreeMap<Integer, Pelicula> getMapPelis() {
		return mapPelis;
	}
	public TreeMap<Integer, Serie> getMapSeries() {
		return mapSeries;
	}
	public TreeMap<Integer, Musica> getMapMusica() {
		return mapMusica;
	}
	public ArrayList<Contenido> getListaFavoritos() {
		return listaFavoritos;
	}

	
	
	//To String
	@Override
	public String toString() {
		return "Usuario [nomUsuario=" + nomUsuario + ", fotoPerfil=" + fotoPerfil + ", descripcion=" + descripcion
				+ ", mapAmigos=" + mapAmigos + ", mapLibros=" + mapLibros + ", mapAudios=" + mapAudios + ", mapPelis=" 
				+ mapPelis + ", mapSeries=" + mapSeries + ", mapMusica=" + mapMusica + ", listaFavoritos=" + listaFavoritos  + "]";
	}
	

}
