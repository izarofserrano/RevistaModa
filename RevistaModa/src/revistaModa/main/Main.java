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
	
		RevistaModa.cargarFotos();
		RevistaModa.cargarArticulos();
		RevistaModa.cargarUsuarios();
		
		@SuppressWarnings("unused")
		VentanaInicial vInicial = new VentanaInicial(false,null);
		
		
		GestorBD.initBD("RevistaModa/db/revistaModa.db");
		GestorBD.crearTablas();
		ArrayList<FotoArt> lArt = new ArrayList<FotoArt>();
		HashSet<String> sArt = new HashSet<String>();
		TreeMap<String,Integer> tArt = new TreeMap<String, Integer>();
		Articulo art = new Articulo(1, "a", "a", "12/12/2012", "a", "a", lArt, sArt, tArt);
		GestorBD.insertarArticulo(art);
		
		


		//VentanaArticulo vArt = new VentanaArticulo(RevistaModa.getlArticulos().get(1));
		//VentanaPerfil vPer = new VentanaPerfil(RevistaModa.getlUsuarios().get(0));
		//VentanaUsuario vUser= new VentanaUsuario(RevistaModa.getlUsuarios());
	}

}