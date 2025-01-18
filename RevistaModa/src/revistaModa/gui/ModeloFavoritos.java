package revistaModa.gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import revistaModa.clases.Articulo;
import revistaModa.clases.RevistaModa;

public class ModeloFavoritos extends DefaultTableModel{
	
	private List<Articulo> articulos;
	private final List<String> cabecero = Arrays.asList(
			"Titulo",
			"Autor",
			"Imagen",
			"Likes",
			"Valorancion Media"
			);
	
	public ModeloFavoritos(List<Articulo> articulos) {
		
		this.articulos = articulos;
	}

	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cabecero.get(column);
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(articulos != null) {
			return articulos.size();
			
		}else {
			return 0;
		}
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cabecero.size();
		}


	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Object getValueAt(int row, int column) {
		Articulo art = articulos.get(row);
		int cont = 0;
		switch(column){
		case 0:return art.getTitulo();
		case 1:return art.getAutor();
		case 2: 
			if (art.getlFotos() != null && !art.getlFotos().isEmpty()) {
                return art.getlFotos().get(0).getRutaFoto();
            } else {
                return null;  // O podrías retornar un valor por defecto o una imagen predeterminada
            }
			
		case 3:return art.getLikesCount();
		case 4:return (RevistaModa.valoracionMedia(art)) ;
		default:return null;
		}
		
		
	}


}
