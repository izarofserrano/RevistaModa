package revistaModa.clases;

public class Usuario {
	private int idUsu;
	private String username;
	private String contrasenya;
	private String nombreUsu;
	private String apellidoUsu;
	private String email;
	private int tlf;
	public Usuario(int idUsu, String username, String contrasenya, String nombreUsu, String apellidoUsu, String email,
			int tlf) {
		super();
		this.idUsu = idUsu;
		this.username = username;
		this.contrasenya = contrasenya;
		this.nombreUsu = nombreUsu;
		this.apellidoUsu = apellidoUsu;
		this.email = email;
		this.tlf = tlf;
	}
	
	public int getIdUsu() {
		return idUsu;
	}
	public void setIdUsu(int idUsu) {
		this.idUsu = idUsu;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public String getNombreUsu() {
		return nombreUsu;
	}
	public void setNombreUsu(String nombreUsu) {
		this.nombreUsu = nombreUsu;
	}
	public String getApellidoUsu() {
		return apellidoUsu;
	}
	public void setApellidoUsu(String apellidoUsu) {
		this.apellidoUsu = apellidoUsu;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTlf() {
		return tlf;
	}
	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	
	
	

}
