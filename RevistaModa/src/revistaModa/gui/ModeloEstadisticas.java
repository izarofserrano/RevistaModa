package revistaModa.gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import revistaModa.clases.Articulo;
import revistaModa.clases.RevistaModa;

public class ModeloEstadisticas extends DefaultTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Articulo> articulos;
	private final List<String> cabecero = Arrays.asList(
			"Titulo",
			"Autor",
			"Imagen",
			"Likes",
			"Valorancion Media"
			);
	
	public ModeloEstadisticas(List<Articulo> articulos) {
		super();
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
		
		switch(column){
		case 0:return art.getTitulo();
		case 1:return art.getAutor();
		case 2:return art.getlFotos().get(row).getRutaFoto();
		case 3:return art.getLikesCount();
		case 4:return (RevistaModa.valoracionMedia(art)) ;
		default:return null;
		}
		
		
	}

	

}
