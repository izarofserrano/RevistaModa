package revistaModa.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import revistaModa.clases.Articulo;
import revistaModa.clases.FotoArt;
import revistaModa.clases.Usuario;

public class GestorBD {
	private static Connection con;

	public static void initBD(String nombreBD)  {
		con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeBD() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas() {
		String sql = "CREATE TABLE IF NOT EXISTS Articulo (idArt int, titulo String, autor String, fechaPublicacion String, tipoArt String, rutaArchivoArt String)";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			sql = "CREATE TABLE IF NOT EXISTS Usuario(idUsu int, username String, contrasenya String, email String)";
			stmt.executeUpdate(sql);
			stmt.close();
			sql = "CREATE TABLE IF NOT EXISTS FotoArt(idFoto int, descripcion String, rutaFoto String)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void insertarArticulo(Articulo art) {
		String sql = "INSERT INTO Articulo VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, art.getIdArt());
			ps.setString(2, art.getTitulo());
			ps.setString(3, art.getAutor());
			ps.setString(4, art.getFechaPublicacion());
			ps.setString(5, art.getTipoArt());
			ps.setString(6, art.getRutaArchivoArt());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void insertarFotoArt(FotoArt fArt) {
		String sql = "INSERT INTO FotoArt VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, fArt.getIdFoto());
			ps.setString(2, fArt.getDescripción());
			ps.setString(3, fArt.getRutaFoto());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insertarUsuario(Usuario usu) {
		String sql = "INSERT INTO Usuario VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, usu.getIdUsu());
			ps.setString(2, usu.getUsername());
			ps.setString(3, usu.getContrasenya());
			ps.setString(3, usu.getEmail());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//IAG (herramienta: ChatGPT)
	public static void borrarArticulo(int idArt) {
	    String sql = "DELETE FROM Articulo WHERE idArt = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idArt);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void borrarFotoArt(int idFoto) {
	    String sql = "DELETE FROM FotoArt WHERE idFoto = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idFoto);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void borrarUsuario(int idUsu) {
	    String sql = "DELETE FROM Usuario WHERE idUsu = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idUsu);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	//SIN CAMBIOS 

	
	
	
	
	
	



}
