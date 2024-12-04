package revistaModa.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println("Conexion establecida");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Clase no encontrada en bd");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error sql");
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
	
	
	//IAG (herramientas: ChatGPT)
	public static void crearTablas() {
	    try {
	        Statement stmt = con.createStatement();

	        // Crear tabla Articulo
	        String sqlArticulo = """
	            CREATE TABLE IF NOT EXISTS Articulo (
	                idArt INTEGER PRIMARY KEY,
	                titulo TEXT NOT NULL,
	                autor TEXT NOT NULL,
	                fechaPublicacion TEXT NOT NULL,
	                tipoArt TEXT NOT NULL,
	                rutaArchivoArt TEXT NOT NULL
	            );
	        """;
	        stmt.executeUpdate(sqlArticulo);

	        // Crear tabla FotoArt con clave foránea
	        String sqlFotoArt = """
	            CREATE TABLE IF NOT EXISTS FotoArt (
	                idFoto INTEGER PRIMARY KEY,
	                descripcion TEXT NOT NULL,
	                rutaFoto TEXT NOT NULL,
	                idArt INTEGER NOT NULL,
	                FOREIGN KEY (idArt) REFERENCES Articulo(idArt) ON DELETE CASCADE
	            );
	        """;
	        stmt.executeUpdate(sqlFotoArt);

	        // Crear tabla Usuario
	        String sqlUsuario = """
	            CREATE TABLE IF NOT EXISTS Usuario (
	                username TEXT PRIMARY KEY,
	                contrasenya TEXT NOT NULL,
	                email TEXT NOT NULL
	            );
	        """;
	        stmt.executeUpdate(sqlUsuario);

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//SIN CAMBIOS
	
	
	
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
	
	
	public static void insertarFotoArt(FotoArt fArt, int idArt) {
	    String sql = "INSERT INTO FotoArt VALUES (?, ?, ?, ?)";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, fArt.getIdFoto());
	        ps.setString(2, fArt.getDescripción());
	        ps.setString(3, fArt.getRutaFoto());
	        ps.setInt(4, idArt); // Clave foránea
	        ps.execute();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insertarArticuloConFotos(Articulo art) {
	    insertarArticulo(art); // Insertar el artículo primero
	    for (FotoArt foto : art.getlFotos()) {
	        insertarFotoArt(foto, art.getIdArt()); // Insertar cada foto con el id del artículo
	    }
	}


	
	public static void insertarUsuario(Usuario usu) {
		String sql = "INSERT INTO Usuario VALUES (?,?,?)"; 
		try(PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(2, usu.getUsername());
			ps.setString(3, usu.getContrasenya());
			ps.setString(4, usu.getEmail());
			ps.execute();

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
	
	
	
	public static List<Usuario> cargarUsuarios() {
	    String sql = "SELECT * FROM Usuario";
	    List<Usuario> usuarios = new ArrayList<>();
	    
	    try (PreparedStatement ps = con.prepareStatement(sql); 
	         ResultSet rs = ps.executeQuery()) { 
	        while (rs.next()) { 
	            String username = rs.getString(1);
	            String email = rs.getString(2);
	            String contrasenya = rs.getString(3);
	            
	            
	            Usuario u = new Usuario(username, email, contrasenya);
	            usuarios.add(u);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        System.out.println("ERROR CARGANDO LA LISTA DE USUARIOS");
	    }
	    
	    return usuarios; 
	}

	public static List<FotoArt> cargarFotos() {
	    String sql = "SELECT * FROM FotoArt";
	    List<FotoArt> fotos = new ArrayList<>();
	    
	    try (PreparedStatement ps = con.prepareStatement(sql); 
	         ResultSet rs = ps.executeQuery()) { 
	        while (rs.next()) { 
	            int id = rs.getInt(1);
	            String descripcion = rs.getString(2);
	            String rutaFoto = rs.getString(3);
	            FotoArt f = new FotoArt(id, descripcion, rutaFoto);
	            fotos.add(f);
	                 
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        System.out.println("ERROR CARGANDO LA LISTA DE FOTOS");
	    }
	    
	    return fotos; 
	}
	
	//IAG (Herramienta: Chat GPT)
	public static Usuario BuscarUsuario(String nickname, String password) throws Exception {
		   String sql = "SELECT * FROM Usuario WHERE username = ? AND contrasenya = ?";
		   Usuario u = null; 
		    
		    try (PreparedStatement ps = con.prepareStatement(sql)) {  
		        ps.setString(1, nickname); 
		        ps.setString(2, password); 
		        
		        try { 
		        	ResultSet rs = ps.executeQuery();
		            if (rs.next()) { 
		                String username = rs.getString(1);
		                String email = rs.getString(2);
		                String contrasenya = rs.getString(3);

		            }
		      }  
		   catch (Exception e) {
		        e.printStackTrace(); 
		        System.out.println("ERROR CARGANDO LOS DATOS");
		    }  }
		    
		    return u; 
		
	}	    
	
	
	
	
	
	
	

}
