package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.TreeMap;

import javax.swing.ImageIcon;

import domain.Audio;
import domain.Catalogo;
import domain.Contenido;
import domain.Genero;
import domain.GeneroMusical;
import domain.Idioma;
import domain.Libro;
import domain.Musica;
import domain.Pelicula;
import domain.Serie;
import domain.Usuario;

public class GestorBD {

	//  ~~~~~~ CONSTANTES ~~~~~~

	private static final String URL_BD = "jdbc:sqlite:resources/db/berenjenas.db";
	private static final String IMG_DEFECTO = "resources/images/question-mark-transparent-grey-5.png";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );

	// ~~~~~~ CONEXIÓN ~~~~~~

	// ABRE CONEXIÓN CON BBDD
	
	public static Connection conectar() throws SQLException {
		Connection con = DriverManager.getConnection( URL_BD );
		Statement st = con.createStatement();
		st.execute( "PRAGMA foreign_keys = ON" ); //Hace que funcionen la FK
		st.close();
		return con;
	}
	

	// ~~~~~~ CARGA DE LOS CONTENIDOS ~~~~~~

	// CARGA CADA CATEGORIA EN SU CATALOGO
	
	public static Catalogo cargarCatalogo() {
		Catalogo catalogo = new Catalogo();

		try ( Connection con = conectar() ) {
			cargarLibros( con, catalogo.getLibros() );
			cargarPeliculas( con, catalogo.getPeliculas() );
			cargarSeries( con, catalogo.getSeries() );
			cargarMusica( con, catalogo.getMusica() );
			cargarAudios( con, catalogo.getAudios() );
		}
		catch ( SQLException e ) {
			System.err.println( "Error al cargar la base de datos: " + e.getMessage() );
			e.printStackTrace();
		}

		return catalogo;
	}


	private static void cargarLibros( Connection con, TreeMap<Long, Contenido> mapa ) throws SQLException {
		String sql = "SELECT l.cod_li, l.titulo, l.autor, l.fecha_pub, g.nom_gen, i.nom_id, "
				+ "l.editorial, l.num_pag, l.ruta_img, l.cont_user, l.calif "
				+ "FROM libro l "
				+ "LEFT JOIN genero g ON l.cod_gen = g.cod_gen "
				+ "LEFT JOIN idioma i ON l.cod_id = i.cod_id";

		try ( Statement st = con.createStatement(); ResultSet rs = st.executeQuery( sql ) ) {
			while ( rs.next() ) {
				Libro l = new Libro( rs.getLong( "cod_li" ), rs.getString( "titulo" ), rs.getString( "autor" ),
						leerGenero( rs.getString( "nom_gen" ) ), leerIdioma( rs.getString( "nom_id" ) ),
						leerFecha( rs.getString( "fecha_pub" ) ), rs.getString( "editorial" ), rs.getInt( "num_pag" ) );
				completar( l, rs );
				mapa.put( l.getCodIdent(), l );
			}
		}
	}


	private static void cargarPeliculas( Connection con, TreeMap<Long, Contenido> mapa ) throws SQLException {
		String sql = "SELECT p.cod_pe, p.titulo, p.autor, p.fecha_pub, p.duracion, g.nom_gen, i.nom_id, "
				+ "p.distribuidor, p.ruta_img, p.cont_user, p.calif "
				+ "FROM pelicula p "
				+ "LEFT JOIN genero g ON p.cod_gen = g.cod_gen "
				+ "LEFT JOIN idioma i ON p.cod_id = i.cod_id";

		try ( Statement st = con.createStatement(); ResultSet rs = st.executeQuery( sql ) ) {
			while ( rs.next() ) {
				Pelicula p = new Pelicula( rs.getLong( "cod_pe" ), rs.getString( "titulo" ), rs.getString( "autor" ),
						leerGenero( rs.getString( "nom_gen" ) ), leerIdioma( rs.getString( "nom_id" ) ),
						leerFecha( rs.getString( "fecha_pub" ) ), rs.getInt( "duracion" ), rs.getString( "distribuidor" ) );
				completar( p, rs );
				mapa.put( p.getCodIdent(), p );
			}
		}
	}


	private static void cargarSeries( Connection con, TreeMap<Long, Contenido> mapa ) throws SQLException {
		String sql = "SELECT s.cod_se, s.titulo, s.autor, s.fecha_pub, s.duracion, g.nom_gen, i.nom_id, "
				+ "s.distribuidor, s.ruta_img, s.cont_user, s.calif "
				+ "FROM serie s "
				+ "LEFT JOIN genero g ON s.cod_gen = g.cod_gen "
				+ "LEFT JOIN idioma i ON s.cod_id = i.cod_id";

		try ( Statement st = con.createStatement(); ResultSet rs = st.executeQuery( sql ) ) {
			while ( rs.next() ) {
				Serie s = new Serie( rs.getLong( "cod_se" ), rs.getString( "titulo" ), rs.getString( "autor" ),
						leerGenero( rs.getString( "nom_gen" ) ), leerIdioma( rs.getString( "nom_id" ) ),
						leerFecha( rs.getString( "fecha_pub" ) ), rs.getInt( "duracion" ), rs.getString( "distribuidor" ) );
				completar( s, rs );
				mapa.put( s.getCodIdent(), s );
			}
		}
	}


	private static void cargarMusica( Connection con, TreeMap<Long, Contenido> mapa ) throws SQLException {
		String sql = "SELECT m.cod_mu, m.titulo, m.autor, m.fecha_pub, m.duracion, gm.nom_gen_mus, i.nom_id, "
				+ "m.ruta_img, m.cont_user, m.calif "
				+ "FROM musica m "
				+ "LEFT JOIN genero_musical gm ON m.cod_gen_mus = gm.cod_gen_mus "
				+ "LEFT JOIN idioma i ON m.cod_id = i.cod_id";

		try ( Statement st = con.createStatement(); ResultSet rs = st.executeQuery( sql ) ) {
			while ( rs.next() ) {
				Musica m = new Musica( rs.getLong( "cod_mu" ), rs.getString( "titulo" ), rs.getString( "autor" ),
						leerGeneroMusical( rs.getString( "nom_gen_mus" ) ), leerIdioma( rs.getString( "nom_id" ) ),
						leerFecha( rs.getString( "fecha_pub" ) ), rs.getInt( "duracion" ) );
				completar( m, rs );
				mapa.put( m.getCodIdent(), m );
			}
		}
	}


	private static void cargarAudios( Connection con, TreeMap<Long, Contenido> mapa ) throws SQLException {
		String sql = "SELECT a.cod_au, a.titulo, a.autor, a.fecha_pub, a.duracion, g.nom_gen, i.nom_id, "
				+ "a.ruta_img, a.cont_user, a.calif "
				+ "FROM audio a "
				+ "LEFT JOIN genero g ON a.cod_gen = g.cod_gen "
				+ "LEFT JOIN idioma i ON a.cod_id = i.cod_id";

		try ( Statement st = con.createStatement(); ResultSet rs = st.executeQuery( sql ) ) {
			while ( rs.next() ) {
				Audio a = new Audio( rs.getLong( "cod_au" ), rs.getString( "titulo" ), rs.getString( "autor" ),
						leerGenero( rs.getString( "nom_gen" ) ), leerIdioma( rs.getString( "nom_id" ) ),
						leerFecha( rs.getString( "fecha_pub" ) ), rs.getInt( "duracion" ) );
				completar( a, rs );
				mapa.put( a.getCodIdent(), a );
			}
		}
	}



	//Rellena lo que es común a las 5 categorías y no va en el constructor:
	//imagen de portada, número de usuarios y calificación media.

	private static void completar( Contenido c, ResultSet rs ) throws SQLException {
		c.setImgIcon( leerImagen( rs.getString( "ruta_img" ) ) );
		c.setContUser( rs.getInt( "cont_user" ) );
		c.setCalif( rs.getFloat( "calif" ) );
	}




	// ~~~~~~ CONVERSIONES DE TEXTO DE LA BD A OBJETOS DE JAVA ~~~~~~

	//La BBDD guarda los géneros en minúsculas ("terror") y el enum está en mayúsculas (TERROR).

	private static Genero leerGenero( String nombre ) {
		if ( nombre == null ) {
			return null;
		}
		try {
			return Genero.valueOf( nombre.trim().toUpperCase( Locale.ROOT ) );
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( "Género desconocido en la BD: " + nombre );
			return null;
		}
	}

	private static GeneroMusical leerGeneroMusical( String nombre ) {
		if ( nombre == null ) {
			return null;
		}
		try {
			return GeneroMusical.valueOf( nombre.trim().toUpperCase( Locale.ROOT ) );
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( "Género musical desconocido en la BD: " + nombre );
			return null;
		}
	}

	private static Idioma leerIdioma( String nombre ) {
		if ( nombre == null ) {
			return null;
		}
		try {
			return Idioma.valueOf( nombre.trim().toUpperCase( Locale.ROOT ) );
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( "Idioma desconocido en la BD: " + nombre );
			return null;
		}
	}

		
	// Las fechas están guardadas como texto con formato dd-MM-yyyy.
	// Si una fecha está vacía o mal escrita se devuelve null en lugar de romper la carga.
	
	private static LocalDate leerFecha( String texto ) {
		if ( texto == null || texto.trim().isEmpty() ) {
			return null;
		}
		try {
			return LocalDate.parse( texto.trim(), FORMATO_FECHA );
		}
		catch ( DateTimeParseException e ) {
			System.err.println( "Fecha incorrecta en la BD: " + texto.trim() );
			return null;
		}
	}

	// Carga la portada. Si la ruta es null o el fichero no existe usa la imagen por defecto.

	private static ImageIcon leerImagen( String ruta ) {
		if ( ruta != null && !ruta.trim().isEmpty() ) {
			ImageIcon icono = new ImageIcon( ruta.trim() );
			if ( icono.getIconWidth() > 0 ) {
				return icono;
			}
			System.err.println( "No se encuentra la imagen: " + ruta.trim() );
		}
		return new ImageIcon( IMG_DEFECTO );
	}
	
	
	

	
	// ~~~~~~ Carga de los Usuarios ~~~~~
	
	public static TreeMap<Integer, Usuario> cargarUsuarios() {
		
		TreeMap<Integer, Usuario> usuarios = new TreeMap<Integer, Usuario>();

			try ( Connection con = conectar() ) {

				try ( Statement stmt = con.createStatement() ) {
					
				ResultSet rs = stmt.executeQuery( "SELECT * FROM usuario" );
					
					while ( rs.next() ) {
							
						int codigo = rs.getInt( "cod_usu" );
						String nomUsuario = rs.getString( "nom_usu" );
						ImageIcon img = leerImagen(rs.getString( "ruta_img" ) );
						String desc = rs.getString( "descripcion" );
							
						Usuario u = new Usuario(codigo, nomUsuario, img, desc);
						System.out.println(u);
						usuarios.put(codigo, u );
						
					}
					
				}
				

			} catch ( SQLException e ) {
				System.err.println( "Error al cargar los usuarios de la base de datos: " + e.getMessage() );
				e.printStackTrace();
			}

		return usuarios;
	
	}	

}
