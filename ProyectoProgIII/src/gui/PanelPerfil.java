package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import domain.Usuario;

public class PanelPerfil {
	
	private static JButton editarPerfil;
	
	private static JTextField nomUsuario;
	private static JTextArea descripcionUsuario;
	
	

	static JPanel crearPanelPerfil() {
		JPanel panelPerfil = new JPanel(new BorderLayout(0, 30));
		Usuario usuario = new Usuario();
		
		
		//----CREO EL PANEL CON FOTO, DATOS USUARIO Y AMIGOS---------------------------------------------------------
		JPanel panelNorte = new JPanel(new BorderLayout(15, 0));
		
		
		///CREO SUBPANEL FOTO
		JPanel panelFotoPerfil = new JPanel(new BorderLayout());
		
		//(me falta meter la imagen y calcular bien las proporciones que quiero que tenga)
		ImageIcon imgP = new ImageIcon("ProyectoProgIII/resources/images/question-mark-transparent-grey-5.png");
		Image imgPerfilEscalada = imgP.getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH);
		ImageIcon imgPerfil = new ImageIcon(imgPerfilEscalada);
		JLabel fotoPerfil = new JLabel(imgPerfil);
		fotoPerfil.setOpaque(true);
		
		editarPerfil = new JButton("Editar Perfil");
		editarPerfil.setBorderPainted(false);
		
		panelFotoPerfil.add(fotoPerfil, BorderLayout.CENTER);
		panelFotoPerfil.add(editarPerfil, BorderLayout.SOUTH);
		
		panelNorte.add(panelFotoPerfil, BorderLayout.WEST);
		
		
		/// CREO SUBPANEL NOMBRE Y DESCRIPCION
		JPanel panelDatos = new JPanel(new BorderLayout(0, 10));
		
		nomUsuario = new JTextField("[Nombre de usuario]");
		nomUsuario.setEditable(false);
		
		descripcionUsuario = new JTextArea(10, 10);
		descripcionUsuario.setEditable(false);
		descripcionUsuario.setLineWrap(true);
		descripcionUsuario.setWrapStyleWord(true);
		
		panelDatos.add(nomUsuario, BorderLayout.NORTH);
		panelDatos.add(descripcionUsuario, BorderLayout.CENTER);
		
		panelNorte.add(panelDatos, BorderLayout.CENTER);
		
		
		/// CREO SUBPANEL AMIGOS
		JPanel panelAmigos = new JPanel(new BorderLayout());
		panelAmigos.setBorder(BorderFactory.createBevelBorder(1));
		
		JPanel panelCabeceraAmigos = new JPanel(new BorderLayout(10, 0));
		
		JLabel labelAmigosX = new JLabel("Amigos (X)");
		JButton botonAmigo = new JButton("Añadir");
		
		panelCabeceraAmigos.add(labelAmigosX, BorderLayout.WEST);
		panelCabeceraAmigos.add(botonAmigo, BorderLayout.EAST);
		
		
		JPanel panelListaAmigos = new JPanel(new BorderLayout());
		
		panelListaAmigos.add(new JLabel("-Amigo 1"), BorderLayout.NORTH);
		panelListaAmigos.add(new JLabel("-Amigo 2"), BorderLayout.CENTER);
		panelListaAmigos.add(new JLabel("-Amigo 3"), BorderLayout.SOUTH);
		
		panelAmigos.add(panelCabeceraAmigos, BorderLayout.NORTH);
		panelAmigos.add(panelListaAmigos, BorderLayout.CENTER);
		panelNorte.add(panelAmigos, BorderLayout.EAST);
		
		
		
		//----CREO EL PANEL DE FAVORITOS------------------------------------------------------------------------------
		JPanel panelFav = new JPanel(new BorderLayout(0, 10));
		
		JLabel labelContenido = new JLabel("-Contenido favorito-", SwingUtilities.CENTER);
		
		
		JPanel subpanelContenido = new JPanel(new GridLayout(1, 5, 10, 0));
		
		String [] categorias = {
				"Películas", "Series", "Libros", "Música", "Audio"
		};
		
		for (String c : categorias) {
			JPanel columna = new JPanel(new BorderLayout(10, 0));
			
			columna.add(new JLabel(c, SwingUtilities.CENTER), BorderLayout.NORTH);
			
			ImageIcon img = new ImageIcon("ProyectoProgIII/resources/images/question-mark-transparent-grey-5.png");
			Image imgEs = img.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
			ImageIcon imgDatoEscalada = new ImageIcon(imgEs);
			columna.add(new JLabel(imgDatoEscalada), BorderLayout.CENTER);
			
			subpanelContenido.add(columna);
		}
		
		
		panelFav.add(labelContenido, BorderLayout.NORTH);
		panelFav.add(subpanelContenido, BorderLayout.CENTER);
		
		
		//----AÑADO TODO AL PANEL PRINCIPAL----------------------------------------------------------------------------
		panelPerfil.add(panelNorte, BorderLayout.NORTH);
		panelPerfil.add(panelFav, BorderLayout.CENTER);
		
		
		
		//----ACCION DE EDITAR PERFIL---------------------------------------------------------------------------------
		editarPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Necesito saber cual es el frame que le voy a pasar a la ventana de editar perfil
				Frame ventanaPrincipal = (Frame) SwingUtilities.getWindowAncestor(editarPerfil);
				
				VentanaEditarPerfil ventanaEP = new VentanaEditarPerfil(ventanaPrincipal, true);
				
				//Inicializo la pantalla desde aqui, sino lo hago no la visualizo
				ventanaEP.setVisible(true);
			}
		});
		
		return panelPerfil;
	}
}
