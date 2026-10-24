package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import domain.Usuario;

public class PanelPerfil {
	
	private static JButton editarPerfil;
	
	private static JTextField nomUsuario;
	private static JTextArea descripcionUsuario;
	
	

	static JPanel crearPanelPerfil() {
		JPanel panelPerfil = new JPanel(new BorderLayout());
		Usuario usuario = new Usuario();
		
		
		//----CREO EL PANEL CON FOTO, DATOS USUARIO Y AMIGOS---------------------------------------------------------
		JPanel panelNorte = new JPanel(new BorderLayout());
		
		
		///CREO SUBPANEL FOTO
		JPanel panelFotoPerfil = new JPanel(new BorderLayout());
		
		//(me falta meter la imagen y calcular bien las proporciones que quiero que tenga)
		ImageIcon imgPerfil = new ImageIcon("/proyecto_grupo_perfil/src/spiderman.png");
		JLabel fotoPerfil = new JLabel(imgPerfil);
		fotoPerfil.setOpaque(true);
		
		editarPerfil = new JButton("Editar Perfil");
		
		panelFotoPerfil.add(fotoPerfil, BorderLayout.CENTER);
		panelFotoPerfil.add(editarPerfil, BorderLayout.SOUTH);
		
		panelNorte.add(panelFotoPerfil, BorderLayout.WEST);
		
		
		/// CREO SUBPANEL NOMBRE Y DESCRIPCION
		JPanel panelDatos = new JPanel(new BorderLayout());
		panelDatos.setLayout(new GridLayout(2, 1, 0, 0));
		
		nomUsuario = new JTextField("[Nombre de usuario]");
		nomUsuario.setEditable(false);
		
		descripcionUsuario = new JTextArea(10, 40);
		descripcionUsuario.setLineWrap(true);
		
		panelDatos.add(nomUsuario);
		panelDatos.add(descripcionUsuario);
		
		panelNorte.add(panelDatos, BorderLayout.CENTER);
		
		
		/// CREO SUBPANEL AMIGOS
		JPanel panelAmigos = new JPanel(new BorderLayout());
		panelAmigos.setBorder(BorderFactory.createBevelBorder(1));
		
		JPanel panelCabeceraAmigos = new JPanel(new BorderLayout());
		
		JLabel labelAmigosX = new JLabel("Amigos (X)");
		JButton botonAmigo = new JButton("Añadir");
		
		panelCabeceraAmigos.add(labelAmigosX, BorderLayout.WEST);
		panelCabeceraAmigos.add(botonAmigo, BorderLayout.EAST);
		
		
		JPanel panelListaAmigos = new JPanel(new BorderLayout());
		
		panelListaAmigos.add(new JLabel("-Amigo 1"));
		panelListaAmigos.add(new JLabel("-Amigo 2"));
		panelListaAmigos.add(new JLabel("-Amigo 3"));
		
		panelAmigos.add(panelCabeceraAmigos, BorderLayout.NORTH);
		panelAmigos.add(panelListaAmigos, BorderLayout.CENTER);
		panelNorte.add(panelAmigos, BorderLayout.EAST);
		
		
		
		//----CREO EL PANEL DE FAVORITOS------------------------------------------------------------------------------
		JPanel panelFav = new JPanel(new BorderLayout());
		
		JLabel labelContenido = new JLabel("-Contenido favorito-");
		
		
		JPanel subpanelContenido = new JPanel(new GridLayout(1, 5, 0, 0));
		
		String [] categorias = {
				"Películas", "Series", "Libros", "Música", "Audio"
		};
		
		for (String c : categorias) {
			JPanel columna = new JPanel(new BorderLayout());
			
			columna.add(new JLabel(c), BorderLayout.NORTH);
			
			ImageIcon img = new ImageIcon("/ProyectoProgIII/ProyectoProgIII/resources/images/question-mark-transparent-grey-5.png");
			columna.add(new JLabel(img), BorderLayout.CENTER);
			
			subpanelContenido.add(columna);
		}
		
		
		panelFav.add(labelContenido, BorderLayout.NORTH);
		panelFav.add(subpanelContenido, BorderLayout.CENTER);
		
		
		//----AÑADO TODO AL PANEL PRINCIPAL----------------------------------------------------------------------------
		panelPerfil.add(panelNorte, BorderLayout.NORTH);
		panelPerfil.add(panelFav, BorderLayout.CENTER);
		
		return panelPerfil;
	}
}
