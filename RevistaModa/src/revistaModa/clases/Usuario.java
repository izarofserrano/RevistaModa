package revistaModa.clases;

public class Usuario {
	private int idUsu;
	private String username;
	private String contrasenya;
	private String email;
	
	public Usuario(int idUsu, String username, String contrasenya, String email) {
		super();
		this.idUsu = idUsu;
		this.username = username;
		this.contrasenya = contrasenya;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [idUsu=" + idUsu + ", username=" + username + ", contrasenya=" + contrasenya + ", email="
				+ email + "]";
	}

	
	
	

}
