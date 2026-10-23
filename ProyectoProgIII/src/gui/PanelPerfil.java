package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelPerfil {
	
	private static JButton editarPerfil;
	
	private static JTextField nomUsuario;
	private static JTextArea descripcionUsuario;

	static JPanel crearPanelPerfil() {
		JPanel panelPerfil = new JPanel(new BorderLayout());
		
		
		//----CREO EL PANEL CON FOTO, DATOS USUARIO Y AMIGOS--
		JPanel panelNorte = new JPanel();
		
		
		///CREO SUBPANEL FOTO
		JPanel panelFotoPerfil = new JPanel();
		
		//(me falta meter la imagen y calcular bien las proporciones que quiero que tenga)
		ImageIcon imgPerfil = new ImageIcon("/proyecto_grupo_perfil/src/spiderman.png");
		JLabel fotoPerfil = new JLabel(imgPerfil);
		
		editarPerfil = new JButton("Editar Perfil");
		
		panelFotoPerfil.add(fotoPerfil, BorderLayout.NORTH);
		panelFotoPerfil.add(editarPerfil, BorderLayout.SOUTH);
		
		panelNorte.add(panelFotoPerfil);
		
		
		/// CREO SUBPANEL NOMBRE Y DESCRIPCION
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new GridLayout(2, 1, 0, 0));
		
		nomUsuario = new JTextField("[Nombre de usuario]");
		nomUsuario.setEditable(false);
		
		descripcionUsuario = new JTextArea(10, 50);
		descripcionUsuario.setLineWrap(true);
		
		panelDatos.add(nomUsuario);
		panelDatos.add(descripcionUsuario);
		
		panelNorte.add(panelDatos);
		
		/// CREO SUBPANEL AMIGOS
		JPanel panelAmigos = new JPanel();
		panelAmigos.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		//----CREO EL PANEL DE FAVORITOS--
		JPanel panelFav = new JPanel();
		
		
		//----AÑADO TODO AL PANEL PRINCIPAL--
		panelPerfil.add(panelNorte, BorderLayout.NORTH);
		panelPerfil.add(panelFav, BorderLayout.SOUTH);
		
		return panelPerfil;
	}
}
