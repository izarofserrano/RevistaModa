package revistaModa.main;

import revistaModa.clases.RevistaModa;
import revistaModa.gui.VentanaArticulo;
import revistaModa.gui.VentanaInicial;
import revistaModa.gui.VentanaPerfil;
import revistaModa.gui.VentanaUsuario;

public class Main {

	public static void main(String[] args) {
		System.out.println("He borrado todo.");
		VentanaInicial vInicial = new VentanaInicial(false,null);
		
		//RevistaModa.cargarFotos();
		RevistaModa.cargarArticulos();
		RevistaModa.cargarUsuarios();
		

		VentanaArticulo vArt = new VentanaArticulo(RevistaModa.getlArticulos().get(1));
		VentanaPerfil vPer = new VentanaPerfil(RevistaModa.getlUsuarios().get(0));
		VentanaUsuario vUser= new VentanaUsuario(RevistaModa.getlUsuarios());
	}

}