package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import domain.Musica;

public class PanelMusica {
	
	private static HashMap<Long, Contenido> mapaContenidos = new HashMap<>();

	public static void setMapaContenidos(HashMap<Long, Contenido> mapa) {
		mapaContenidos = mapa;
	}

	
	// ~~~~~~ PANEL PRINCIPAL ~~~~~~

	static JPanel crearPanelMusica() {

		JPanel panelMusica = new JPanel(new BorderLayout(0, 15));
		panelMusica.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

		// AÑADIR SUBPANELES AL PANEL PRINCIPAL

		panelMusica.add(crearPanelNorte(), BorderLayout.NORTH);
		panelMusica.add(crearPanelSouth(), BorderLayout.CENTER);

		return panelMusica;
	}

	
	// ~~~~~~ PANEL ZONA DE BUSQUEDA (ARRIBA) + FILTROS (DEBAJO) ~~~~~~

	private static JPanel crearPanelNorte() {

		JPanel panelNorte = new JPanel(new BorderLayout());

		panelNorte.add(crearPanelBusqueda(), BorderLayout.NORTH);
		panelNorte.add(crearPanelFiltros(), BorderLayout.CENTER);

		return panelNorte;
	}

	private static JPanel crearPanelBusqueda() {

		JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel etiqueta = new JLabel("Buscar 🔍");
		JTextField cajaBuscar = new JTextField(20);

		panelBusqueda.add(etiqueta);
		panelBusqueda.add(cajaBuscar);

		return panelBusqueda;
	}

	private static JPanel crearPanelFiltros() {

		JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));

		String[] filtros = { "Año", "Calificación", "Popularidad", "Género" };
		for (String filtro : filtros) {
			panelFiltros.add(new JButton(filtro));
		}

		return panelFiltros;
	}

	
	// ~~~~~~ PANEL MUSICA RECOMENDADA ~~~~~~

	private static JPanel crearPanelSouth() {

		JPanel panelSouth = new JPanel(new BorderLayout(0, 15));

		panelSouth.add(new JLabel("Música popular esta semana"), BorderLayout.NORTH);
		panelSouth.add(crearPanelTarjetas(), BorderLayout.CENTER);

		return panelSouth;
	}

	private static JPanel crearPanelTarjetas() {

		JPanel panelTarjetas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

		// BUSCA EN EL MAPA LOS CONTENIDOS DE TIPO "MUSICA" Y LOS METE EN LA LISTA EN ORDEN
		List<Musica> canciones = new ArrayList<>();
		for (Contenido c : mapaContenidos.values()) {
			if (c instanceof Musica m) {
				canciones.add(m);
			}
		}
		canciones.sort(Comparator.comparingLong(Musica::getCodIdent));

		for (Musica m : canciones) {
			panelTarjetas.add(crearTarjeta(m));
		}

		return panelTarjetas;
	}

	private static JPanel crearTarjeta(Musica m) {

		JPanel tarjeta = new JPanel(new BorderLayout(0, 5));
		tarjeta.setPreferredSize(new Dimension(105, 165));
		tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		String titulo = (m.getTitulo() != null) ? m.getTitulo() : "N/A";
		String autor = (m.getAutorDirector() != null) ? m.getAutorDirector() : "N/A";

		// PORTADA
		JLabel portada = new JLabel(escalar(m.getImgIcon(), 80, 80), SwingConstants.CENTER);
		tarjeta.add(portada, BorderLayout.CENTER);

		// TITULO Y AUTOR (SI NO CABEN LOS ACORTA CON "..."
		JPanel panelTexto = new JPanel(new GridLayout(2, 1));
		panelTexto.setOpaque(false);
		panelTexto.add(new JLabel(titulo, SwingConstants.CENTER));
		panelTexto.add(new JLabel(autor, SwingConstants.CENTER));
		tarjeta.add(panelTexto, BorderLayout.SOUTH);

		tarjeta.setToolTipText(titulo + " - " + autor); //FUNCION PARA PODER VER EL NOMBRE COMPLETO CUANDO PASA EL RATON POR ENCIMA

		return tarjeta;
	}

	private static ImageIcon escalar(ImageIcon icono, int ancho, int alto) {
		if (icono == null || icono.getIconWidth() <= 0) {
			return null; // SI LA IMAGEN QUE SE BUSCA NO EXISTE
		}
		return new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
	}
}