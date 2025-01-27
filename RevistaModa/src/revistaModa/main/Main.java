package revistaModa.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import revistaModa.bd.GestorBD;
import revistaModa.clases.Articulo;
import revistaModa.clases.FotoArt;
import revistaModa.clases.RevistaModa;
import revistaModa.gui.VentanaInicial;
import revistaModa.gui.VentanaPerfil;
import revistaModa.gui.VentanaUsuario;


public class Main {

	public static void main(String[] args) {
	
		GestorBD.initBD("RevistaModa\\db\\revistaModa.db");
		GestorBD.crearTablas();
		
		RevistaModa.cargarFotosArtBD();
		RevistaModa.cargarUsuariosBD();
		RevistaModa.separarArticulos();
		

		
		@SuppressWarnings("unused")
		VentanaInicial vInicial = new VentanaInicial(false,null);
		
		
		
		
		ArrayList<FotoArt> lArt = new ArrayList<FotoArt>();
		HashSet<String> sArt = new HashSet<String>();
		TreeMap<String,Integer> tArt = new TreeMap<String, Integer>();
		
		
	}

}