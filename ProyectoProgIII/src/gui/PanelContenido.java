package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import domain.Audio;
import domain.Contenido;
import domain.Genero;
import domain.Musica;

public abstract class PanelContenido extends JPanel {

	private static final long serialVersionUID = 1L;

	private TreeMap<Long, Contenido> mapaContenidos;   // SOLO los contenidos de esta categoría, ordenados por código
	private String tituloSeccion;
	private JPanel panelTarjetas;
	private JLabel etiquetaResultados;

	// COMPONENTES DE BUSQUEDA Y FILTROS (ATRIBUTOS PARA PODER LEERLOS DESDE cargarTarjetas)
	private JTextField cajaBuscar;
	private JComboBox<String> comboGenero;
	private JComboBox<String> comboAnyo;
	private JComboBox<String> comboCalif;
	private JComboBox<String> comboOrden;
	private Object[] generos;   // GENEROS DE ESTA CATEGORIA, EN EL MISMO ORDEN QUE EN comboGenero (SIN CONTAR "Todos")


	// ~~~~~~ CONSTRUCTOR ~~~~~~

	protected PanelContenido(TreeMap<Long, Contenido> mapa, String tituloSeccion) {
		super(new BorderLayout(0, 15));
		this.mapaContenidos = mapa;
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
		cajaBuscar = new JTextField(20);

		// CADA VEZ QUE SE SUELTA UNA TECLA SE VUELVEN A CARGAR LAS TARJETAS CON EL TEXTO ESCRITO
		cajaBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cargarTarjetas();
			}
		});

		panelBusqueda.add(etiqueta);
		panelBusqueda.add(cajaBuscar);

		return panelBusqueda;
	}

	private JPanel crearPanelFiltros() {

		JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));

		// CADA VEZ QUE SE CAMBIA UN DESPLEGABLE SE VUELVEN A CARGAR LAS TARJETAS
		ActionListener alCambiar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTarjetas();
			}
		};

		// GENERO: LAS OPCIONES LAS DA getGeneros() (MUSICA LO SOBRESCRIBE CON SUS GENEROS MUSICALES)
		generos = getGeneros();
		comboGenero = new JComboBox<>();
		comboGenero.addItem("Todos");
		for (Object g : generos) {
			comboGenero.addItem(formatear(g.toString()));
		}

		// AÑO: SOLO LOS AÑOS QUE APARECEN EN ESTA CATEGORIA, DEL MAS RECIENTE AL MAS ANTIGUO
		TreeSet<Integer> anyos = new TreeSet<>();
		for (Contenido c : mapaContenidos.values()) {
			if (c.getFechaPub() != null) {
				anyos.add(c.getFechaPub().getYear());
			}
		}
		comboAnyo = new JComboBox<>();
		comboAnyo.addItem("Todos");
		for (Integer anyo : anyos.descendingSet()) {
			comboAnyo.addItem(String.valueOf(anyo));
		}

		// CALIFICACION MINIMA: LA POSICION EN EL DESPLEGABLE ES EL NUMERO DE ESTRELLAS MINIMO
		comboCalif = new JComboBox<>(new String[] { "Cualquiera", "1★ o más", "2★ o más", "3★ o más", "4★ o más", "5★" });

		// ORDEN (AQUI ENTRA LA POPULARIDAD)
		comboOrden = new JComboBox<>(new String[] { "Por defecto", "Más populares", "Mejor valorados", "Más recientes", "Título (A-Z)" });

		comboGenero.addActionListener(alCambiar);
		comboAnyo.addActionListener(alCambiar);
		comboCalif.addActionListener(alCambiar);
		comboOrden.addActionListener(alCambiar);

		// BOTON PARA QUITAR TODOS LOS FILTROS DE GOLPE
		JButton botonLimpiar = new JButton("Limpiar filtros");
		botonLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarFiltros();
			}
		});

		panelFiltros.add(new JLabel("Género:"));
		panelFiltros.add(comboGenero);
		panelFiltros.add(new JLabel("Año:"));
		panelFiltros.add(comboAnyo);
		panelFiltros.add(new JLabel("Calificación:"));
		panelFiltros.add(comboCalif);
		panelFiltros.add(new JLabel("Ordenar:"));
		panelFiltros.add(comboOrden);
		panelFiltros.add(botonLimpiar);

		return panelFiltros;
	}

	private void limpiarFiltros() {
		cajaBuscar.setText("");
		comboGenero.setSelectedIndex(0);
		comboAnyo.setSelectedIndex(0);
		comboCalif.setSelectedIndex(0);
		comboOrden.setSelectedIndex(0);
		cargarTarjetas();
	}


	// ~~~~~~ GENEROS (SE PUEDEN SOBRESCRIBIR EN LAS SUBCLASES) ~~~~~~

	// POR DEFECTO LOS CONTENIDOS USAN Genero. MUSICA USA GeneroMusical Y SOBRESCRIBE ESTOS DOS METODOS
	protected Object[] getGeneros() {
		return Genero.values();
	}

	protected Object getGeneroDe(Contenido c) {
		return c.getGenero();
	}

	// "HISTORICA" -> "Historica"
	private String formatear(String texto) {
		return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
	}


	// ~~~~~~ PANEL CONTENIDO RECOMENDADO ~~~~~~

	private JPanel crearPanelSur() {

		JPanel panelSur = new JPanel(new BorderLayout(0, 15));

		JPanel panelCabecera = new JPanel(new BorderLayout());
		panelCabecera.add(new JLabel(tituloSeccion), BorderLayout.WEST);
		etiquetaResultados = new JLabel();
		panelCabecera.add(etiquetaResultados, BorderLayout.EAST);
		panelSur.add(panelCabecera, BorderLayout.NORTH);

		panelTarjetas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panelSur.add(panelTarjetas, BorderLayout.CENTER);

		cargarTarjetas();   // AL INICIO SE CARGAN TODOS (NO HAY NINGUN FILTRO PUESTO)

		return panelSur;
	}

	private void cargarTarjetas() {

		panelTarjetas.removeAll();

		// 1. RECORRE EL MAPA DE ESTA CATEGORIA Y SE QUEDA CON LOS QUE CUMPLEN LA BUSQUEDA Y TODOS LOS FILTROS
		//    (EL TREEMAP YA DEVUELVE LOS VALORES ORDENADOS POR CODIGO IDENTIFICATIVO)
		List<Contenido> lista = new ArrayList<>();
		for (Contenido c : mapaContenidos.values()) {
			if (cumpleFiltros(c)) {
				lista.add(c);
			}
		}

		// 2. LOS ORDENA SEGUN LO ELEGIDO EN "Ordenar"
		ordenar(lista);

		// 3. CREA UNA TARJETA POR CADA UNO (O UN MENSAJE SI NO HAY NINGUNO)
		for (Contenido c : lista) {
			panelTarjetas.add(crearTarjeta(c));
		}
		if (lista.isEmpty()) {
			panelTarjetas.add(new JLabel("No se han encontrado resultados"));
		}
		etiquetaResultados.setText(lista.size() + " resultado(s)");

		panelTarjetas.revalidate();
		panelTarjetas.repaint();
	}

	// DEVUELVE true SI EL CONTENIDO PASA EL BUSCADOR Y TODOS LOS FILTROS. EN CUANTO FALLA UNO, DEVUELVE false
	private boolean cumpleFiltros(Contenido c) {

		// BUSCADOR: TITULO O AUTOR
		String t = cajaBuscar.getText().toLowerCase();
		boolean coincideTitulo = c.getTitulo().toLowerCase().contains(t);
		boolean coincideAutor = c.getAutorDirector().toLowerCase().contains(t);
		if (!coincideTitulo && !coincideAutor) {
			return false;
		}

		// GENERO (POSICION 0 = "Todos")
		int posGenero = comboGenero.getSelectedIndex();
		if (posGenero > 0) {
			Object generoElegido = generos[posGenero - 1];
			if (getGeneroDe(c) != generoElegido) {   // SON ENUMS, SE PUEDEN COMPARAR CON ==
				return false;
			}
		}

		// AÑO (POSICION 0 = "Todos")
		if (comboAnyo.getSelectedIndex() > 0) {
			int anyoElegido = Integer.parseInt((String) comboAnyo.getSelectedItem());
			if (c.getFechaPub() == null || c.getFechaPub().getYear() != anyoElegido) {
				return false;
			}
		}

		// CALIFICACION MINIMA (LA POSICION ES EL NUMERO DE ESTRELLAS; 0 = "Cualquiera")
		int estrellasMin = comboCalif.getSelectedIndex();
		if (estrellasMin > 0 && c.getCalif() < estrellasMin) {
			return false;
		}

		return true;
	}

	private void ordenar(List<Contenido> lista) {

		final int criterio = comboOrden.getSelectedIndex();
		if (criterio == 0) {
			return;   // "Por defecto": SE QUEDAN ORDENADOS POR CODIGO, COMO VIENEN DEL TREEMAP
		}

		Collections.sort(lista, new Comparator<Contenido>() {
			@Override
			public int compare(Contenido c1, Contenido c2) {
				switch (criterio) {
				case 1:   // MAS POPULARES PRIMERO (MAS USUARIOS LO TIENEN EN SU BIBLIOTECA)
					return Integer.compare(c2.getContUser(), c1.getContUser());
				case 2:   // MEJOR CALIFICACION PRIMERO
					return Float.compare(c2.getCalif(), c1.getCalif());
				case 3:   // MAS RECIENTES PRIMERO (LOS QUE NO TIENEN FECHA VAN AL FINAL)
					if (c1.getFechaPub() == null && c2.getFechaPub() == null) {
						return 0;
					} else if (c1.getFechaPub() == null) {
						return 1;
					} else if (c2.getFechaPub() == null) {
						return -1;
					}
					return c2.getFechaPub().compareTo(c1.getFechaPub());
				case 4:   // TITULO ALFABETICO
					return c1.getTitulo().compareToIgnoreCase(c2.getTitulo());
				default:
					return 0;
				}
			}
		});
	}

	// protected PARA QUE UNA SUBCLASE PUEDA SOBRESCRIBIRLA SI NECESITA UNA TARJETA DISTINTA
	protected JPanel crearTarjeta(Contenido c) {

		JPanel tarjeta = new JPanel(new BorderLayout(0, 5));
		tarjeta.setPreferredSize(new Dimension(105, 165));
		tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));   // MANITO AL PASAR EL RATON

		// PORTADA
		JLabel portada; 
		if ( c instanceof Musica || c instanceof Audio ) {
			portada = new JLabel(escalar(c.getImgIcon(), 80, 80), SwingConstants.CENTER);
		}
		else {
			portada = new JLabel(escalar(c.getImgIcon(), 80, 110), SwingConstants.CENTER);
		}
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
