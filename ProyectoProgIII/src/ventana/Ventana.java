package ventana;

import javax.swing.JFrame;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Ventana() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320, 240); //TAMAÑO PANTALLA
		
		setTitle("ProyectoIII"); //TITULO VENTANA
		
		setVisible(true); //SIEMPRE TRUE
	}

	public static void main(String[] args) {

	}

}
