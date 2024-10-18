package revistaModa.clases;

import java.util.ArrayList;
import java.util.List;


public class RevistaModa {
	private static ArrayList<Articulo> lArticulos = new ArrayList<>();
	 private static List<Usuario> lUsuarios = new ArrayList<>();
	

	public static void cargarArticulos() {
		Articulo a1 = new Articulo(1, "Tendencias de moda otoño 2024", "Ana González", "2024-09-15", "Tendencias", "/archivos/moda_otono_2024.pdf", "/fotos/autores/ana_gonzalez.jpg");
        a1.getSetUsuariosLike().add("usuario1"); 
        a1.getSetUsuariosLike().add("usuario2");
        a1.getMapaUsuariosVal().put("usuario1", 5); 
        a1.getMapaUsuariosVal().put("usuario2", 4);

        // Artículo 2
        Articulo a2 = new Articulo(2, "La evolución del streetwear", "Carlos López", "2024-08-10", "Historia", "/archivos/evolucion_streetwear.pdf", "/fotos/autores/carlos_lopez.jpg");
        a2.getSetUsuariosLike().add("usuario3");
        a2.getMapaUsuariosVal().put("usuario3", 5);

        // Artículo 3
        Articulo a3 = new Articulo(3, "Accesorios que marcarán el 2024", "Lucía Martínez", "2024-10-01", "Accesorios", "/archivos/accesorios_2024.pdf", "/fotos/autores/lucia_martinez.jpg");
        a3.getSetUsuariosLike().add("usuario2"); 
        a3.getSetUsuariosLike().add("usuario4");
        a3.getMapaUsuariosVal().put("usuario2", 3); 
        a3.getMapaUsuariosVal().put("usuario4", 4);

		lArticulos.add(a1);
		lArticulos.add(a2);
		lArticulos.add(a3);
		

	}
	
	public static ArrayList<Articulo> getlArticulos() {
		return lArticulos;
	}

	public static void cargarUsuarios() {

		lUsuarios.add(new Usuario(1, "johndoe", "johndoe", "john.doe@example.com"));
		lUsuarios.add(new Usuario(2, "janedoe", "janedoe", "jane.doe@example.com"));
		lUsuarios.add(new Usuario(3, "alicewonder", "alicewonder", "alice.w@example.com"));
		lUsuarios.add(new Usuario(4, "bobsmith", "bobsmith", "bob.smith@example.com"));
		lUsuarios.add(new Usuario(5, "charliebrown", "charliebrown", "charlie.b@example.com"));

	}

	public static List<Usuario> getlUsuarios() {
		return lUsuarios;
	}

	public static void setlUsuarios(List<Usuario> lUsuarios) {
		RevistaModa.lUsuarios = lUsuarios;
	}


}
