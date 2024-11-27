package revistaModa.clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class RevistaModa {
    private static ArrayList<Articulo> lArticulos = new ArrayList<>();
    private static List<Usuario> lUsuarios = new ArrayList<>();
    private static ArrayList<FotoArt> lFotos = new ArrayList<>();

    // Cargar los artículos con datos, incluyendo fotos, likes y valoraciones
    public static void cargarArticulos() {
        // Artículo 1
        Articulo a1 = new Articulo(1, "Tendencias de moda otoño 2024", "Ana González", "2024-09-15", "Tendencias",
                "RevistaModa/html/moda.html", getlFotos(), new HashSet<>(), new TreeMap<>());
        a1.getSetUsuariosLike().add("johndoe");
        a1.getSetUsuariosLike().add("janedoe");
        a1.getMapaUsuariosVal().put("johndoe", 5);
        a1.getMapaUsuariosVal().put("charliebrown", 4);

        // Artículo 2
        Articulo a2 = new Articulo(2, "La evolución del streetwear", "Carlos López", "2024-08-10", "Historia",
                "RevistaModa/html/moda.html", getlFotos(), new HashSet<>(), new TreeMap<>());
        a2.getSetUsuariosLike().add("janedoe");
        a2.getMapaUsuariosVal().put("alicewonder", 5);

        // Artículo 3
        Articulo a3 = new Articulo(3, "Accesorios que marcarán el 2024", "Lucía Martínez", "2024-10-01", "Accesorios",
                "RevistaModa/html/moda.html", getlFotos(), new HashSet<>(), new TreeMap<>());
        a3.getSetUsuariosLike().add("johndoe");
        a3.getSetUsuariosLike().add("janedoe");
        a3.getMapaUsuariosVal().put("charliebrown", 3);
        a3.getMapaUsuariosVal().put("bobsmith", 4);

        // Agregar los artículos a la lista
        lArticulos.add(a1);
        lArticulos.add(a2);
        lArticulos.add(a3);
    }

    // Cargar los usuarios
    public static void cargarUsuarios() {
        lUsuarios.add(new Usuario(1, "johndoe", "johndoe", "john.doe@example.com"));
        lUsuarios.add(new Usuario(2, "janedoe", "janedoe", "jane.doe@example.com"));
        lUsuarios.add(new Usuario(3, "alicewonder", "alicewonder", "alice.w@example.com"));
        lUsuarios.add(new Usuario(4, "bobsmith", "bobsmith", "bob.smith@example.com"));
        lUsuarios.add(new Usuario(5, "charliebrown", "charliebrown", "charlie.b@example.com"));
        lUsuarios.add(new Usuario(6, "admin", "admin", "admin@admin.com"));
    }

    // Cargar las fotos
    public static void cargarFotos() {
        FotoArt foto1 = new FotoArt(1, "Foto de portada otoño 2024", "RevistaModa/img/ropa1.jpeg");
        FotoArt foto2 = new FotoArt(2, "Accesorios de moda", "RevistaModa/img/ropa2.jpeg");
        FotoArt foto3 = new FotoArt(3, "Estilos streetwear 2024", "RevistaModa/img/ropa3.jpeg");
        FotoArt foto4 = new FotoArt(4, "Calzado tendencia 2024", "RevistaModa/img/ropa4.jpeg");

        lFotos.add(foto1);
        lFotos.add(foto2);
        lFotos.add(foto3);
        lFotos.add(foto4);
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

