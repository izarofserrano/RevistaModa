package revistaModa.clases;


public class Articulo {
	
	private int idArt;
	private String titulo;
	private String autor;
	private String fechaPublicacion;
	private String tipoArt;
	private int cantLikes;
	private String rutaArchivoArt;
	private String rutaFotoPerfil;


	public Articulo(int idArt, String titulo, String autor, String fechaPublicacion, String tipoArt, int cantLikes,
			String rutaArchivoArt, String rutaFotoPerfil) {
		super();
		this.idArt = idArt;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.tipoArt = tipoArt;
		this.cantLikes = cantLikes;
		this.rutaArchivoArt = rutaArchivoArt;
		this.rutaFotoPerfil = rutaFotoPerfil;
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
	


	public int getCantLikes() {
		return cantLikes;
	}


	public void setCantLikes(int cantLikes) {
		this.cantLikes = cantLikes;
	}
	


	public String getRutaFotoPerfil() {
		return rutaFotoPerfil;
	}


	public void setRutaFotoPerfil(String rutaFotoPerfil) {
		this.rutaFotoPerfil = rutaFotoPerfil;
	}


	@Override
	public String toString() {
		return "Articulo [idArt=" + idArt + ", titulo=" + titulo + ", autor=" + autor + ", fechaPublicacion="
				+ fechaPublicacion + ", tipoArt=" + tipoArt + ", rutaArchivoArt=" + rutaArchivoArt + "]";
	}
	
	
	
}
