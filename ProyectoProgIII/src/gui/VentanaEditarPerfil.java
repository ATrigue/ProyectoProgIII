package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.Flow;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.Usuario;

public class VentanaEditarPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;
	public static final int ALTURA = 600;
	public static final int ANCHURA = 800;
	
	private PanelPerfil perfil;
	private JTextField nomUsuario;
	private JTextArea descUsuario;
	private ImageIcon imgUsuario;
	
	private JButton modNombre;
	private JButton modDesc;
	private JButton modImg;
	
	private JButton guardaryCerrar;
	private boolean guardado = false;  //inicializo el guardado de los datos a falso
	
	public VentanaEditarPerfil(Frame parent, boolean modal) {
		//No puedo poner PanelPerfil porque es una clase con componente estatico-> tengo que poner un comp. de swing
		
		//----INVOCO AL CONSTRUCTOR PADRE
		super(parent, modal);
//		this.perfil = perfil;
		
		
		//----EDITO EL TAMAÑO
		setSize(ANCHURA, ALTURA);
		setLocationRelativeTo(null);
		
		
		//----CREO EL PANEL PRINCIPAL---------------------------------------------------------------------------------
		JPanel panelEditar = new JPanel(new BorderLayout(0, 0));
		JPanel panelCentro = new JPanel(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
		
		
		//----CREO EL LABEL DE LA PARTE DE ARRIBA
		JLabel labelEditar = new JLabel("Editar perfil", SwingUtilities.CENTER);
		
		
		//----CAMBIO LA IMAGEN
		imgUsuario = new ImageIcon(perfil.getImgPerfil().getImage());
		modImg = new JButton(imgUsuario);
		modImg.setPreferredSize(new Dimension(150, 170));
		panelNorte.add(modImg, BorderLayout.WEST);
		
		
		//----CAMBIO EL NOMBRE Y LA DESCRIPCION
		JPanel panelDatos = new JPanel(new GridLayout(2, 2, 10, 10));
		
		nomUsuario = new JTextField(perfil.getNomUsuario().getText());
		nomUsuario.setEditable(false);
		nomUsuario.setBorder(null);
		
		descUsuario = new JTextArea(perfil.getDescripcionUsuario().getText());
		descUsuario.setEditable(false);
		
		ImageIcon imgEditar = new ImageIcon("ProyectoProgIII/resources/images/lapiz_editar_perfil.png");
		Image imgesc = imgEditar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		modNombre = new JButton(new ImageIcon(imgesc));
		modDesc = new JButton(new ImageIcon(imgesc));
		
		panelDatos.add(nomUsuario);
		panelDatos.add(modNombre);
		panelDatos.add(descUsuario);
		panelDatos.add(modDesc);
		
		
		//----CAMBIO LOS CONTENIDOS FAVORITOS
		JPanel panelSur = new JPanel(new BorderLayout(10, 10));
		
		
		//----CREO EL BOTON DE GUARDAR LOS DATOS Y CERRAR LA PESTAÑA-----------------------------------------------------------------------
		JPanel panelGuardar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		guardaryCerrar = new JButton("Guardar");
		guardaryCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardado = true;
				dispose();
			}
		});
		panelGuardar.add(guardaryCerrar);
		
		
		
		//----AÑADO TODO EN LA VENTANA--------------------------------------------------------------------------------
		panelNorte.add(panelDatos, BorderLayout.CENTER);
		
		panelCentro.add(panelNorte, BorderLayout.NORTH);
		panelCentro.add(panelSur, BorderLayout.CENTER);
		
		panelEditar.add(labelEditar, BorderLayout.NORTH);
		panelEditar.add(panelCentro, BorderLayout.CENTER);
		
		panelEditar.add(panelGuardar, BorderLayout.SOUTH);
		
		add(panelEditar);
		
		
		//----ACTION LISTENERS---------------------------------------------------------------------------------------
		modNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nomUsuario.setEditable(true);
				nomUsuario.setBackground(null);
			}
		});
		
		modDesc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				descUsuario.setEditable(true);
				
			}
		});
		
		
		modImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirExplorador();
			}
		});
		
	}
	//----GETTERS Y SETTERS

	public boolean isGuardado() {
		return guardado;
	}

	public void setGuardado(boolean guardado) {
		this.guardado = guardado;
	}

	public JTextField getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(JTextField nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	
	public JButton getModImg() {
		return modImg;
	}

	public void setModImg(JButton modImg) {
		this.modImg = modImg;
	}

	public ImageIcon getImgUsuario() {
		return imgUsuario;
	}

	public void setImgUsuario(ImageIcon imgUsuario) {
		this.imgUsuario = imgUsuario;
	}
	
	public JTextArea getDescUsuario() {
		return descUsuario;
	}

	public void setDescUsuario(JTextArea descUsuario) {
		this.descUsuario = descUsuario;
	}

	
	
	//----ABRIR EXPLORADOR ARCHIVOS
	private void abrirExplorador() {
		JFileChooser selector = new JFileChooser();
		
		//Abro la ventana de archivos para ver las imagenes
		int result = selector.showOpenDialog(this);  //this toma el valor de filechooser para abrir archivos
		
		if (result == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionadoUsu = selector.getSelectedFile();
			ImageIcon imgNueva = new ImageIcon(archivoSeleccionadoUsu.getAbsolutePath());
			
			//Redimensiono la imagen para que tome el mismo tamaño que el boton
			Image imgEscalada = imgNueva.getImage().getScaledInstance(modImg.getWidth(), modImg.getHeight(), 
					Image.SCALE_SMOOTH);
			ImageIcon imgFinal = new ImageIcon(imgEscalada);
			
			imgUsuario.setImage(imgFinal.getImage());;
			modImg.setIcon(imgUsuario);
		}
	}
	
	
}
