package revistaModa.clases;

public class Usuario {
	private String username;
	private String contrasenya;
	private String email;
	
	public Usuario( String username, String contrasenya, String email) {
		super();

		this.username = username;
		this.contrasenya = contrasenya;
		this.email = email;
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
		return "Usuario [username=" + username + ", contrasenya=" + contrasenya + ", email="
				+ email + "]";
	}
	

}
