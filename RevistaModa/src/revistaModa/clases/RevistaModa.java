package revistaModa.clases;

import java.util.ArrayList;


public class RevistaModa {
	private static ArrayList<Articulo> lArticulos = new ArrayList<>();
	
	
	public static void cargarArticulos() {
		Articulo a1 = new Articulo(1, "La belleza natural", "au1", "2024", "Belleza", 45, "desktop", "desktop");
		Articulo a2 = new Articulo(2, "Tecnología en 2024", "au2", "2024", "Tecnología", 120, "C:/Users/izaro/OneDrive/Escritorio/HTMLProgra/moda.html", "techProfile");
		Articulo a3 = new Articulo(3, "El futuro del transporte", "au3", "2023", "Innovación", 85, "futuroTransporte", "transporteProfile");
		Articulo a4 = new Articulo(4, "Cocina saludable", "au4", "2022", "Gastronomía", 60, "cocinaSaludable", "cocinaProfile");
		Articulo a5 = new Articulo(5, "Avances en medicina", "au5", "2024", "Salud", 150, "medicinaAvances", "medicinaProfile");
		Articulo a6 = new Articulo(6, "Los secretos del marketing digital", "au6", "2023", "Marketing", 200, "marketing2023", "marketingProfile");
		Articulo a7 = new Articulo(7, "Cultura pop y su influencia", "au7", "2021", "Cultura", 75, "culturaPop", "culturaProfile");
		Articulo a8 = new Articulo(8, "Viajes sostenibles", "au8", "2023", "Turismo", 95, "viajesSostenibles", "turismoProfile");
		Articulo a9 = new Articulo(9, "Educación en la era digital", "au9", "2024", "Educación", 130, "educacionDigital", "educacionProfile");
		Articulo a10 = new Articulo(10, "El impacto del cambio climático", "au10", "2022", "Medio ambiente", 180, "cambioClimatico", "medioAmbienteProfile");

		lArticulos.add(a1);
		lArticulos.add(a2);
		lArticulos.add(a3);
		lArticulos.add(a4);
		lArticulos.add(a5);
		lArticulos.add(a6);
		lArticulos.add(a7);
		lArticulos.add(a8);
		lArticulos.add(a9);
		lArticulos.add(a10);

	}
	
	public static ArrayList<Articulo> getlArticulos() {
		return lArticulos;
	}

	public static void cargarUsuarios() {

	}



}
