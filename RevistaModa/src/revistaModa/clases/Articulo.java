package revistaModa.clases;

import java.sql.Date;

public class Articulo {
	
	private static int contadorId = 1;
	private int id;
	private String titulo;
	private String autor;
	private Date fechaPublicacion;
	private TipoArticulo tipoArt;
	private String rutaArchivoArt;
	
	public Articulo(int id, String titulo, String autor, Date fechaPublicacion, TipoArticulo tipoArt,
			String rutaArchivoArt) {
		super();
		this.id = Articulo.contadorId++;  
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.tipoArt = tipoArt;
		this.rutaArchivoArt = rutaArchivoArt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public TipoArticulo getTipoArt() {
		return tipoArt;
	}

	public void setTipoArt(TipoArticulo tipoArt) {
		this.tipoArt = tipoArt;
	}

	public String getRutaArchivoArt() {
		return rutaArchivoArt;
	}

	public void setRutaArchivoArt(String rutaArchivoArt) {
		this.rutaArchivoArt = rutaArchivoArt;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", fechaPublicacion="
				+ fechaPublicacion + ", tipoArt=" + tipoArt + ", rutaArchivoArt=" + rutaArchivoArt + "]";
	}
	
}
