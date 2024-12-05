package revistaModa.clases;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Articulo {
	
	private int idArt;
	private String titulo;
	private String autor;
	private String fechaPublicacion;
	private String tipoArt;
	private String rutaArchivoArt;
	private ArrayList<FotoArt> lFotos;
	private Set<String> setUsuariosLike;
	private TreeMap<String,Integer> mapaUsuariosVal;
	
	/**
     * Constructor por defecto del objeto Articulo.
     */
	public Articulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor del Objeto Articulo
	 * @param idArt identificador único del artículo.
	 * @param titulo Título que tiene el artículo que será el encabezado de la página.
	 * @param autor Autor del artítuculo.
	 * @param fechaPublicacion fecha en el que el artículo fué publicado.
	 * @param tipoArt El artículo puede ser Belleza o Moda.
	 * @param rutaArchivoArt Ruta del archivo HTML que tiene el contenido del artículo.
	 * @param lFotos Lista de objetos Foto, esta fotos apareceran junto al archivo HTML. 
	 * @param setUsuariosLike Set para cargar los usuarios que han dado like e artículo.
	 * @param mapaUsuariosVal Mapa que tiene valor: username, Integer: valoración (obetnido en el JSlider)
	 */
	public Articulo(int idArt, String titulo, String autor, String fechaPublicacion, String tipoArt,
			String rutaArchivoArt, ArrayList<FotoArt> lFotos, Set<String> setUsuariosLike,
			TreeMap<String, Integer> mapaUsuariosVal) {
		super();
		this.idArt = idArt;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.tipoArt = tipoArt;
		this.rutaArchivoArt = rutaArchivoArt;
		this.lFotos = lFotos;
		this.setUsuariosLike = setUsuariosLike;
		this.mapaUsuariosVal = mapaUsuariosVal;
	}


	public Articulo(int idArt, String titulo, String autor, String fechaPublicacion, String tipoArt,
			String rutaArchivoArt) {
		super();
		this.idArt = idArt;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.tipoArt = tipoArt;
		this.rutaArchivoArt = rutaArchivoArt;
		this.lFotos = new ArrayList<>();
		this.setUsuariosLike = new TreeSet<>();
		this.mapaUsuariosVal = new TreeMap<>();
		
	}

	/**
	 * Método que devuelve el id del artículo.
	 * @return devuelve el id del artículo.
	 */
	public int getIdArt() {
		return idArt;
	}

	/**
	 * Método que cambia el Id del artículo.
	 * @param idArt Nuevo id del artículo.
	 */
	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}

	/**
	 * Método que devuelve el título del artículo.
	 * @return Título del artículo.
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Método que cambia el título del artículo por el título pasado por parametro .
	 * @param titulo Título nuevo del artículo.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Método que devuelve el autor del atículo.
	 * @return Nombre del autor.
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Método de cambia el autor del artículo por el nombre del autor pasado por parametro.
	 * @param autor Nombre del autor al que se quiere cambiar.
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
     * Método que devuelve la fecha de publicación del artículo.
     * @return Fecha de publicación del artículo en formato String.
     */
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	
	/**
     * Método que establece la fecha de publicación del artículo.
     * @param fechaPublicacion Nueva fecha de publicación del artículo.
     */
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	//IAG: (Herramienta: ChatGPT) (Algunos de los de abajo)
	/**
     * Método que devuelve el tipo del artículo.
     * @return Tipo del artículo (ej. Belleza o Moda).
     */
	public String getTipoArt() {
		return tipoArt;
	}

	 /**
     * Método que establece el tipo del artículo.
     * @param tipoArt Nuevo tipo del artículo.
     */
	public void setTipoArt(String tipoArt) {
		this.tipoArt = tipoArt;
	}

	/**
     * Método que devuelve la ruta del archivo HTML del artículo.
     * @return Ruta del archivo HTML del artículo.
     */
	public String getRutaArchivoArt() {
		return rutaArchivoArt;
	}

	/**
     * Método que establece la ruta del archivo HTML del artículo.
     * @param rutaArchivoArt Nueva ruta del archivo HTML.
     */
	public void setRutaArchivoArt(String rutaArchivoArt) {
		this.rutaArchivoArt = rutaArchivoArt;
	}

	/**
     * Método que devuelve la lista de fotos asociadas al artículo.
     * @return Lista de fotos del artículo.
     */
	public ArrayList<FotoArt> getlFotos() {
		return lFotos;
	}
	
	/**
     * Método que establece la lista de fotos asociadas al artículo.
     * @param lFotos Nueva lista de fotos del artículo.
     */
	public void setlFotos(ArrayList<FotoArt> lFotos) {
		this.lFotos = lFotos;
	}

	/**
     * Método que devuelve el conjunto de usuarios que dieron "like" al artículo.
     * @return Conjunto de usuarios que dieron "like".
     */
	public Set<String> getSetUsuariosLike() {
		return setUsuariosLike;
	}

	/**
     * Método que establece el conjunto de usuarios que dieron "like" al artículo.
     * @param setUsuariosLike Nuevo conjunto de usuarios con "like".
     */
	public void setSetUsuariosLike(Set<String> setUsuariosLike) {
		this.setUsuariosLike = setUsuariosLike;
	}
	
	/**
     * Método que devuelve el mapa de valoraciones de usuarios.
     * @return Mapa de usuarios y sus respectivas valoraciones.
     */
	public TreeMap<String, Integer> getMapaUsuariosVal() {
		return mapaUsuariosVal;
	}

	/**
     * Método que establece el mapa de valoraciones de usuarios.
     * @param mapaUsuariosVal Nuevo mapa de valoraciones de usuarios.
     */
	public void setMapaUsuariosVal(TreeMap<String, Integer> mapaUsuariosVal) {
		this.mapaUsuariosVal = mapaUsuariosVal;
	}

	/**
	 * Método que devuelve la cantidad de "likes" del artículo.
	 * @return Número de "likes" recibidos.
	 */
	public int getLikesCount() {
		return setUsuariosLike.size();
	}

	/**
     * Método que devuelve una representación en forma de cadena del artículo.
     * @return Representación del objeto Articulo en forma de String.
     */
	@Override
	public String toString() {
		return "Articulo [idArt=" + idArt + ", titulo=" + titulo + ", autor=" + autor + ", fechaPublicacion="
				+ fechaPublicacion + ", tipoArt=" + tipoArt + ", rutaArchivoArt=" + rutaArchivoArt + ", lFotos="
				+ lFotos + ", setUsuariosLike=" + setUsuariosLike + ", mapaUsuariosVal=" + mapaUsuariosVal + "]";
	}
	
}