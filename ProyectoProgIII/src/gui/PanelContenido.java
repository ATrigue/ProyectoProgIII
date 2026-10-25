package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import domain.Contenido;

public abstract class PanelContenido extends JPanel {

	private static final long serialVersionUID = 1L;

	private HashMap<Long, Contenido> mapaContenidos;
	private Class<? extends Contenido> tipo;   // Musica.class, Pelicula.class...
	private String tituloSeccion;
	private JPanel panelTarjetas;


	// ~~~~~~ CONSTRUCTOR ~~~~~~

	protected PanelContenido(HashMap<Long, Contenido> mapa, Class<? extends Contenido> tipo, String tituloSeccion) {
		super(new BorderLayout(0, 15));
		this.mapaContenidos = mapa;
		this.tipo = tipo;
		this.tituloSeccion = tituloSeccion;

		setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

		add(crearPanelNorte(), BorderLayout.NORTH);
		add(crearPanelSur(), BorderLayout.CENTER);
	}


	// ~~~~~~ PANEL ZONA DE BUSQUEDA (ARRIBA) + FILTROS (DEBAJO) ~~~~~~

	private JPanel crearPanelNorte() {

		JPanel panelNorte = new JPanel(new BorderLayout());

		panelNorte.add(crearPanelBusqueda(), BorderLayout.NORTH);
		panelNorte.add(crearPanelFiltros(), BorderLayout.CENTER);

		return panelNorte;
	}

	private JPanel crearPanelBusqueda() {

		JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel etiqueta = new JLabel("Buscar 🔍");
		JTextField cajaBuscar = new JTextField(20);

		// CADA VEZ QUE SE SUELTA UNA TECLA SE VUELVEN A CARGAR LAS TARJETAS CON EL TEXTO ESCRITO
		cajaBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cargarTarjetas(cajaBuscar.getText());
			}
		});

		panelBusqueda.add(etiqueta);
		panelBusqueda.add(cajaBuscar);

		return panelBusqueda;
	}

	private JPanel crearPanelFiltros() {

		JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));

		String[] filtros = { "Año", "Calificación", "Popularidad", "Género" };
		for (String filtro : filtros) {
			panelFiltros.add(new JButton(filtro));
		}

		return panelFiltros;
	}


	// ~~~~~~ PANEL CONTENIDO RECOMENDADO ~~~~~~

	private JPanel crearPanelSur() {

		JPanel panelSur = new JPanel(new BorderLayout(0, 15));

		panelSur.add(new JLabel(tituloSeccion), BorderLayout.NORTH);

		panelTarjetas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panelSur.add(panelTarjetas, BorderLayout.CENTER);

		cargarTarjetas("");   // AL INICIO SE CARGAN TODOS (TEXTO VACIO)

		return panelSur;
	}

	private void cargarTarjetas(String texto) {

		panelTarjetas.removeAll();
		String t = texto.toLowerCase();

		// 1. BUSCA EN EL MAPA LOS CONTENIDOS DEL TIPO CORRECTO QUE COINCIDAN CON LA BUSQUEDA
		List<Contenido> lista = new ArrayList<>();
		for (Contenido c : mapaContenidos.values()) {
			if (tipo.isInstance(c) && c.getTitulo().toLowerCase().contains(t)) {
				lista.add(c);
			}
		}

		// 2. LOS ORDENA POR CODIGO IDENTIFICATIVO
		lista.sort(Comparator.comparingLong(Contenido::getCodIdent));

		// 3. CREA UNA TARJETA POR CADA UNO
		for (Contenido c : lista) {
			panelTarjetas.add(crearTarjeta(c));
		}

		panelTarjetas.revalidate();
		panelTarjetas.repaint();
	}

	// protected PARA QUE UNA SUBCLASE PUEDA SOBRESCRIBIRLA SI NECESITA UNA TARJETA DISTINTA
	protected JPanel crearTarjeta(Contenido c) {

		JPanel tarjeta = new JPanel(new BorderLayout(0, 5));
		tarjeta.setPreferredSize(new Dimension(105, 165));
		tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));   // MANITO AL PASAR EL RATON

		// PORTADA
		JLabel portada = new JLabel(escalar(c.getImgIcon(), 80, 80), SwingConstants.CENTER);
		tarjeta.add(portada, BorderLayout.CENTER);

		// TITULO Y AUTOR
		JPanel panelTexto = new JPanel(new GridLayout(2, 1));
		panelTexto.setOpaque(false);
		panelTexto.add(new JLabel(c.getTitulo(), SwingConstants.CENTER));
		panelTexto.add(new JLabel(c.getAutorDirector(), SwingConstants.CENTER));
		tarjeta.add(panelTexto, BorderLayout.SOUTH);

		tarjeta.setToolTipText(c.getTitulo() + " - " + c.getAutorDirector());   // NOMBRE COMPLETO AL PASAR EL RATON

		// AL HACER CLICK EN LA TARJETA SE ABRE LA VENTANA CON LOS DATOS DEL CONTENIDO
		tarjeta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new VentanaMostrarContenido(c);
			}
		});

		return tarjeta;
	}

	private ImageIcon escalar(ImageIcon icono, int ancho, int alto) {
		if (icono == null || icono.getIconWidth() <= 0) {
			return null;   // SI LA IMAGEN QUE SE BUSCA NO EXISTE
		}
		return new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
	}
}
