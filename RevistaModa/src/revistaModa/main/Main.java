package revistaModa.main;

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


		//VentanaArticulo vArt = new VentanaArticulo(RevistaModa.getlArticulos().get(1));
		//VentanaPerfil vPer = new VentanaPerfil(RevistaModa.getlUsuarios().get(0));
		//VentanaUsuario vUser= new VentanaUsuario(RevistaModa.getlUsuarios());
	}

}