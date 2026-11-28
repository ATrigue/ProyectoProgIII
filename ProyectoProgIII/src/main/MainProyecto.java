package main;

import db.GestorBD;
import domain.Catalogo;
import gui.Ventana;


public class MainProyecto {

	public static void main(String[] args) {

		// Los contenidos se leen de la base de datos (resources/db/berenjenas.db)
		Catalogo catalogo = GestorBD.cargarCatalogo();

		Ventana v = new Ventana( catalogo ); // CREACION DE LA VENTANA
	}

}
