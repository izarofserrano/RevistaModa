package revistaModa.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String username;
	private String contrasenya;
	private String email;
	private List<Articulo> favoritos;
	
	public Usuario( String username, String contrasenya, String email) {
		super();

		this.username = username;
		this.contrasenya = contrasenya;
		this.email = email;
		this.favoritos = new ArrayList<>();
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
	
	
	public List<Articulo> getFavoritos() {
		if (favoritos == null) {
	        favoritos = new ArrayList<>();
	    }
	    return favoritos;
	}


	public void setFavoritos(List<Articulo> favoritos) {
		this.favoritos = favoritos;
	}


	@Override
	public String toString() {
		return "Usuario [username=" + username + ", contrasenya=" + contrasenya + ", email="
				+ email + "]";
	}
	

}
