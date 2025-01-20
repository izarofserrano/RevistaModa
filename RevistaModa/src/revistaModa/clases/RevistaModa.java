package revistaModa.clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import revistaModa.bd.GestorBD;

public class RevistaModa {
    private static ArrayList<Articulo> lArticulos = GestorBD.cargarArticulos();
    private static List<Usuario> lUsuarios =GestorBD.cargarUsuarios();
    private static ArrayList<FotoArt> lFotos = (ArrayList<FotoArt>) GestorBD.cargarFotos();
    private static ArrayList<Articulo> lArtBelleza;
    private static ArrayList<Articulo> lArtModa;

    

    
    
    public static void cargarUsuariosBD() {
    	lUsuarios = GestorBD.cargarUsuarios();
    }
    

    public static void cargarFotosArtBD() {
    	lFotos = (ArrayList<FotoArt>) GestorBD.cargarFotos();
    	
    }

    
    
    public static ArrayList<Articulo> getlArtBelleza() {
		return lArtBelleza;
	}


	public static void setlArtBelleza(ArrayList<Articulo> lArtBelleza) {
		RevistaModa.lArtBelleza = lArtBelleza;
	}


	public static ArrayList<Articulo> getlArtModa() {
		return lArtModa;
	}


	public static void setlArtModa(ArrayList<Articulo> lArtModa) {
		RevistaModa.lArtModa = lArtModa;
	}


	public static void separarArticulos() {
		lArtModa = new ArrayList<Articulo>();
		lArtBelleza = new ArrayList<Articulo>();
    	for(Articulo art : lArticulos) {
    		if(art.getTipoArt().equals("1")) {
    			lArtModa.add(art);
    		}else if(art.getTipoArt().equals("2")) {
    			lArtBelleza.add(art);
    		}
    	}    	
    }
    
    
    // Método para obtener las fotos asociadas a un artículo en particular
    public static ArrayList<FotoArt> getFotosArticulo(int idArticulo) {
        return lFotos;
    }

    // Obtener la lista de artículos
    public static ArrayList<Articulo> getlArticulos() {
        return lArticulos;
    }

    // Obtener la lista de usuarios
    public static List<Usuario> getlUsuarios() {
        return lUsuarios;
    }

    // Obtener la lista de fotos
    public static ArrayList<FotoArt> getlFotos() {
        return lFotos;
    }

    // Método para calcular la valoración media de un artículo
    public static float valoracionMedia(Articulo art) {
        int contador = 0;
        TreeMap<String, Integer> mapa = art.getMapaUsuariosVal();

        if (mapa.isEmpty()) {
            return 0;  // Evitar dividir por cero si no hay valoraciones
        }

        for (int valor : mapa.values()) {
            contador += valor;
        }

        return (float) contador / mapa.size();
    }
    
    
    
    
    
    
 
    
}

