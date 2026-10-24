package domain;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Usuario {
	private String nomUsuario;  //Nombre de usuario del usuario
	private ImageIcon fotoPerfil;   //Foto de perfil que desee poner el usuario
	private String descripcion;   //Descripcion de su perfil
	private ArrayList<Usuario> listaAmigos;   //Lista de amigos del usuario
	private ArrayList<Contenido> listaContenido;  //Lista de series, peliculas, libros, musica o audio del usuario
	
	
	//Constructores
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nomUsuario, ImageIcon fotoPerfil, String descripcion, ArrayList<Usuario> listaAmigos,
			ArrayList<Contenido> listaContenido) {
		super();
		this.nomUsuario = nomUsuario;
		this.fotoPerfil = fotoPerfil;
		this.descripcion = descripcion;
		this.listaAmigos = listaAmigos;
		this.listaContenido = listaContenido;
	}
	
	
	//Getters y setters
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
	public ArrayList<Usuario> getListaAmigos() {
		return listaAmigos;
	}
	public void setListaAmigos(ArrayList<Usuario> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}
	public ArrayList<Contenido> getListaContenido() {
		return listaContenido;
	}
	public void setListaContenido(ArrayList<Contenido> listaContenido) {
		this.listaContenido = listaContenido;
	}
	
	
	//To String
	@Override
	public String toString() {
		return "Usuario [nomUsuario=" + nomUsuario + ", fotoPerfil=" + fotoPerfil + ", descripcion=" + descripcion
				+ ", listaAmigos=" + listaAmigos + ", listaContenido=" + listaContenido + "]";
	}
	

}
