package revistaModa.clases;



public class FotoArt {
	
	private int idFoto;
	private String descripción;
	private String rutaFoto;
	
	
	public FotoArt(int idFoto, String descripción, String rutaFoto) {
		super();
		this.idFoto = idFoto;
		this.descripción = descripción;
		this.rutaFoto = rutaFoto;
	}
	public int getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	public String getRutaFoto() {
		return rutaFoto;
	}
	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
	@Override
	public String toString() {
		return "FotoArt [idFoto=" + idFoto + ", descripción=" + descripción + ", rutaFoto=" + rutaFoto + "]";
	}
	
	
	
	
	

}
