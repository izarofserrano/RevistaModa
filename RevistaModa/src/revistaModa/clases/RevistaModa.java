package revistaModa.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class RevistaModa {
	private static ArrayList<Articulo> lArticulos = new ArrayList<>();
	 private static List<Usuario> lUsuarios = new ArrayList<>();
	

	public static void cargarArticulos() {
		Articulo a1 = new Articulo(1, "Tendencias de moda otoño 2024", "Ana González", "2024-09-15", "Tendencias", "RevistaModa/html/moda.html", "/fotos/autores/ana_gonzalez.jpg");
        a1.getSetUsuariosLike().add("johndoe"); 
        a1.getSetUsuariosLike().add("janedoe");
        a1.getMapaUsuariosVal().put("johndoe", 5); 
        a1.getMapaUsuariosVal().put("charliebrown", 4);

        // Artículo 2
        Articulo a2 = new Articulo(2, "La evolución del streetwear", "Carlos López", "2024-08-10", "Historia", "RevistaModa/html/moda.html", "/fotos/autores/carlos_lopez.jpg");
        a2.getSetUsuariosLike().add("janedoe");
        a2.getMapaUsuariosVal().put("alicewonder", 5);

        // Artículo 3
        Articulo a3 = new Articulo(3, "Accesorios que marcarán el 2024", "Lucía Martínez", "2024-10-01", "Accesorios", "RevistaModa/html/moda.html", "/fotos/autores/lucia_martinez.jpg");
        a3.getSetUsuariosLike().add("johndoe"); 
        a3.getSetUsuariosLike().add("janedoe");
        a3.getMapaUsuariosVal().put("charliebrown", 3); 
        a3.getMapaUsuariosVal().put("bobsmith", 4);

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
		lUsuarios.add(new Usuario(6, "admin", "admin", "admin@admin.com"));
	}

	public static List<Usuario> getlUsuarios() {
		return lUsuarios;
	}

	public static void setlUsuarios(List<Usuario> lUsuarios) {
		RevistaModa.lUsuarios = lUsuarios;
	}

	public  static float valoracionMedia(Articulo art) {
		
		int contador = 0;
		TreeMap<String,Integer> mapa =art.getMapaUsuariosVal();
		
		for(Usuario u : lUsuarios) {
			if(mapa.containsKey(u)) {
				contador = contador + mapa.get(u);
			}else {
				
		}}
		float result = contador/mapa.size();
		
		
		return result;
		
		
	

}
}

