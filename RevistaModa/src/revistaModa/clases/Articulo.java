package revistaModa.clases;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Articulo {
	
	private int idArt;
	private String titulo;
	private String autor;
	private String fechaPublicacion;
	private String tipoArt;
	private String rutaArchivoArt;
	private String rutaFotoPerfil;
	private Set<String> setUsuariosLike;
	private TreeMap<String,Integer> mapaUsuariosVal;
	
	public Articulo(int idArt, String titulo, String autor, String fechaPublicacion, String tipoArt,
			String rutaArchivoArt, String rutaFotoPerfil) {
		super();
		this.idArt = idArt;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.tipoArt = tipoArt;
		this.rutaArchivoArt = rutaArchivoArt;
		this.rutaFotoPerfil = rutaFotoPerfil;
		setUsuariosLike = new HashSet<String>();
		mapaUsuariosVal = new TreeMap<String, Integer>();
				
	}
	public int getIdArt() {
		return idArt;
	}
	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getTipoArt() {
		return tipoArt;
	}
	public void setTipoArt(String tipoArt) {
		this.tipoArt = tipoArt;
	}
	public String getRutaArchivoArt() {
		return rutaArchivoArt;
	}
	public void setRutaArchivoArt(String rutaArchivoArt) {
		this.rutaArchivoArt = rutaArchivoArt;
	}
	public String getRutaFotoPerfil() {
		return rutaFotoPerfil;
	}
	public void setRutaFotoPerfil(String rutaFotoPerfil) {
		this.rutaFotoPerfil = rutaFotoPerfil;
	}
	
	public Set<String> getSetUsuariosLike() {
		return setUsuariosLike;
	}
	public void setSetUsuariosLike(Set<String> setUsuariosLike) {
		this.setUsuariosLike = setUsuariosLike;
	}
	public TreeMap<String, Integer> getMapaUsuariosVal() {
		return mapaUsuariosVal;
	}
	public void setMapaUsuariosVal(TreeMap<String, Integer> mapaUsuariosVal) {
		this.mapaUsuariosVal = mapaUsuariosVal;
	}
	@Override
	public String toString() {
		return "Articulo [idArt=" + idArt + ", titulo=" + titulo + ", autor=" + autor + ", fechaPublicacion="
				+ fechaPublicacion + ", tipoArt=" + tipoArt + ", rutaArchivoArt=" + rutaArchivoArt + ", rutaFotoPerfil="
				+ rutaFotoPerfil + ", setUsuariosLike=" + setUsuariosLike + ", mapaUsuariosVal=" + mapaUsuariosVal
				+ "]";
	}
	
}