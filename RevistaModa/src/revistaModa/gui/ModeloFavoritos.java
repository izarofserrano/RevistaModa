package revistaModa.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import revistaModa.bd.GestorBD;
import revistaModa.clases.Articulo;
import revistaModa.clases.FotoArt;

public class ModeloFavoritos extends DefaultTableModel {

    private List<Articulo> articulos;
    private HashMap<Articulo, ArrayList<Integer>> favData;
    private final List<String> cabecero = Arrays.asList(
            "Título",
            "Autor",
            "Imagen",
            "Like",
            "Valoración"
    );

    // Constructor que acepta el HashMap devuelto por obtenerValLikeporUsu
    public ModeloFavoritos(HashMap<Articulo, ArrayList<Integer>> favData) {
        this.favData = favData;
        if (favData != null) {
            this.articulos = new ArrayList<>(favData.keySet()); // Extraer las claves (Artículos) del HashMap
        } else {
            this.articulos = new ArrayList<>();
        }
              
    }

    @Override
    public String getColumnName(int column) {
        return cabecero.get(column);
    }

    @Override
    public int getRowCount() {
        if (articulos != null) {
            return articulos.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return cabecero.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Articulo art = articulos.get(row);
        ArrayList<Integer> valLike = favData.get(art); 
        
        switch (column) {
            case 0:
                return art.getTitulo(); 
            case 1:
                return art.getAutor(); 
            case 2:
            	return art.getlFotos().getFirst().getRutaFoto();
                
            case 3:
                if (valLike.get(0) == 1) {
                    return "Sí";
                } else {
                    return "No";
                }
            case 4:
                if (valLike.get(1) != null) {
                    return valLike.get(1);
                } else {
                    return 0; // Valor predeterminado si no hay valoración
                }
            default:
                return null;
        }
    }
}
