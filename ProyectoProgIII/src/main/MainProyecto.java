package main;

import java.util.TreeMap;

import db.GestorBD;
import domain.Catalogo;
import domain.Usuario;
import gui.Ventana;


public class MainProyecto {

	public static void main(String[] args) {

		// Los contenidos se leen de la base de datos (resources/db/berenjenas.db)
		Catalogo catalogo = GestorBD.cargarCatalogo();
		TreeMap<Integer, Usuario> usuarios = GestorBD.cargarUsuarios();

		Ventana v = new Ventana( catalogo );
	}

}
