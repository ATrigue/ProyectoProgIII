package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
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
	
	private JButton botonContenidoFav;
	
	private JButton guardaryCerrar;
	private JButton cancelar;
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
		JPanel panelCentro = new JPanel(new BorderLayout(0, 30));
		
		JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
		
		
		//----CREO EL LABEL DE LA PARTE DE ARRIBA
		JLabel labelEditar = new JLabel("Editar perfil", SwingUtilities.CENTER);
		
		
		//----CAMBIO LA IMAGEN
		imgUsuario = new ImageIcon(perfil.getImgPerfil().getImage());
		JPanel panelImagen = new JPanel(new BorderLayout(0, 0));
		panelImagen.setPreferredSize(new Dimension(150, 170));
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		//Le pongo un borde para que no se pegue a la izquierda
		panelImagen.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 15));
		panelImagen.add(new JLabel(imgUsuario));
		
//		modImg = new JButton(imgUsuario);
//		modImg.setPreferredSize(new Dimension(150, 170));
		
		panelNorte.add(panelImagen, BorderLayout.WEST);
		
		panelImagen.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );  // MANITO AL PASAR EL RATON POR ENCIMA
		
		
		//----CAMBIO EL NOMBRE Y LA DESCRIPCION
		JPanel panelDatos = new JPanel(new BorderLayout(10,  10));
		JPanel panelDatoUsuario = new JPanel(new BorderLayout(10,  10));
		JPanel panelBotones = new JPanel(new BorderLayout(10,  10));
		
		//Le pongo un borde para que no se pegue a la derecha
		panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 15));
		
		nomUsuario = new JTextField(perfil.getNomUsuario().getText());
		nomUsuario.setEditable(false);
		nomUsuario.setBorder(null);
		
		descUsuario = new JTextArea(perfil.getDescripcionUsuario().getText());
		descUsuario.setEditable(false);
		
		ImageIcon imgEditar = new ImageIcon("resources/images/lapiz_editar_perfil.png");
		Image imgesc = imgEditar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		modNombre = new JButton(new ImageIcon(imgesc));
		modDesc = new JButton(new ImageIcon(imgesc));
		modDesc.setPreferredSize(new Dimension(20, 20));
		modDesc.setPreferredSize(new Dimension(105, 45));
		
		panelDatoUsuario.add(nomUsuario, BorderLayout.NORTH);
//		panelBotones.add(modNombre, BorderLayout.NORTH);
		panelDatoUsuario.add(descUsuario, BorderLayout.CENTER);
		panelBotones.add(modDesc, BorderLayout.CENTER);
		
		panelDatos.add(panelDatoUsuario, BorderLayout.CENTER);
		panelDatos.add(panelBotones, BorderLayout.EAST);
		
		
		//----CAMBIO LOS CONTENIDOS FAVORITOS
		JPanel panelSur = new JPanel(new BorderLayout(0,  10));
		
		JLabel labelContenido = new JLabel("-Contenido favorito-", SwingUtilities.CENTER);
		
		
		JPanel subpanelContenido = new JPanel(new GridLayout(1, 5, 10, 0));
		
		String [] categorias = {
				"Películas", "Series", "Libros", "Música", "Audio"
		};
		
		for (String c : categorias) {
			JPanel columna = new JPanel(new BorderLayout(10, 0));
			
			columna.add(new JLabel(c, SwingUtilities.CENTER), BorderLayout.NORTH);
			
			ImageIcon img = new ImageIcon("resources/images/question-mark-transparent-grey-5.png");
			Image imgEs = img.getImage().getScaledInstance(170, 200, Image.SCALE_SMOOTH);
			ImageIcon imgDatoEscalada = new ImageIcon(imgEs);
			columna.add(new JLabel(imgDatoEscalada), BorderLayout.CENTER);
			
			subpanelContenido.add(columna);
		}
		
		
		panelSur.add(labelContenido, BorderLayout.NORTH);
		panelSur.add(subpanelContenido, BorderLayout.CENTER);
		
		
		
		//----CREO EL BOTON DE GUARDAR LOS DATOS Y CERRAR LA PESTAÑA-----------------------------------------------------------------------
		JPanel panelGuardarCancelar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		guardaryCerrar = new JButton("Guardar");
		guardaryCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardado = true;
				dispose();
			}
		});
		panelGuardarCancelar.add(guardaryCerrar);
		
		
		//----BOTON CANCELAR
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		panelGuardarCancelar.add(cancelar);
		
		
		//----AÑADO TODO EN LA VENTANA--------------------------------------------------------------------------------
		panelNorte.add(panelDatos, BorderLayout.CENTER);
		
		panelCentro.add(panelNorte, BorderLayout.NORTH);
		panelCentro.add(panelSur, BorderLayout.CENTER);
		
		panelEditar.add(labelEditar, BorderLayout.NORTH);
		panelEditar.add(panelCentro, BorderLayout.CENTER);
		
		panelEditar.add(panelGuardarCancelar, BorderLayout.SOUTH);
		
		add(panelEditar);
		
		
		//----ACTION LISTENERS---------------------------------------------------------------------------------------
//		modNombre.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				nomUsuario.setText("");
//				nomUsuario.setEditable(true);
//				nomUsuario.setBackground(null);
//			}
//		});
		
		modDesc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				descUsuario.setEditable(true);
				nomUsuario.setText("");
				nomUsuario.setEditable(true);
				nomUsuario.setBackground(null);
				
			}
		});
		
		panelImagen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
