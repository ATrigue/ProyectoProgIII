package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Usuario;

public class VentanaEditarPerfil extends JDialog{
	
	private static final long serialVersionUID = 1L;
	public static final int ALTURA = 600;
	public static final int ANCHURA = 800;
	
	private JButton guardaryCerrar;
	
	private boolean guardado = false;  //inicializo el guardado de los datos a falso
	
	private String nomUsuario;
	
	public VentanaEditarPerfil(Frame parent, boolean modal) {
		//No puedo poner PanelPerfil porque es una clase con componente estatico-> tengo que poner un comp. de swing
		
		//----INVOCO AL CONSTRUCTOR PADRE
		super(parent, modal);
		
		
		//----EDITO EL TAMAÑO
		setSize(ANCHURA, ALTURA);
		setLocationRelativeTo(null);
		
		
		//----CREO EL PANEL PRINCIPAL---------------------------------------------------------------------------------
		JPanel panelEditar = new JPanel(new BorderLayout());
		
		
		//----CREO EL LABEL DE LA PARTE DE ARRIBA
		JLabel labelEditar = new JLabel("Editar perfil");
		
		panelEditar.add(labelEditar, BorderLayout.NORTH);
		
		
		//----CAMBIO LA IMAGEN
		
		
		
		//----CAMBIO EL NOMBRE Y LA DESCRIPCION
		
		
		
		//----CAMBIO LOS CONTENIDOS FAVORITOS
		
		
		
		//----CREO EL BOTON DE GUARDAR LOS DATOS Y CERRAR LA PESTAÑA-----------------------------------------------------------------------
		guardaryCerrar = new JButton("Guardar");
		guardaryCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardado = true;
				dispose();
			}
		});
		
		
		
		//----AÑADO TODO EN LA VENTANA--------------------------------------------------------------------------------
		JPanel panelBotones = new JPanel(new FlowLayout());  //Flow porque solo necesito una linea (izq o dcha)
		panelEditar.add(guardaryCerrar);
		
		
		add(panelEditar);
	}
}
