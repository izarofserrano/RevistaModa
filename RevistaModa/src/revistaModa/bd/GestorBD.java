package revistaModa.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
					idArt INTEGER,
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

			String sqlFavArticulo = """
					    CREATE TABLE IF NOT EXISTS FavArticulo (
					        username TEXT NOT NULL,
					        idArt INTEGER NOT NULL,
					        like INTEGER NOT NULL,
					        valoracion INTEGER NOT NULL,
					        PRIMARY KEY (username, idArt),
					        FOREIGN KEY (username) REFERENCES Usuario(username) ON DELETE SET NULL,
					        FOREIGN KEY (idArt) REFERENCES Articulo(idArt) ON DELETE SET NULL
					    );
					""";
			stmt.executeUpdate(sqlFavArticulo);
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

			ps.setString(1, usu.getUsername());
			ps.setString(2, usu.getContrasenya());
			ps.setString(3, usu.getEmail());
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
		try (PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, idFoto);
			ps.executeUpdate();
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


	public static void actualizarValoraciones(TreeMap<String, Integer> mapaUsuariosVal, Set<String> setUsuariosLike, int idArticulo) {
		//IAG (herramienta: CHATGPT) //PREGUNTA: El chat me ha recomendado hacer con Batch y AutoCommit esta bien?
		String sqlActualizarLike = "INSERT INTO FavArticulo (username, idArt, like, valoracion) VALUES (?, ?, ?, ?) " +
				"ON CONFLICT (username, idArt) DO UPDATE SET like = ?, valoracion = ?"; 

		String sqlActualizarValoracion = "INSERT INTO FavArticulo (username, idArt, like, valoracion) VALUES (?, ?, ?, ?) " +
				"ON CONFLICT (username, idArt) DO UPDATE SET valoracion = ?"; 

		// Usamos una transacción para garantizar que ambas operaciones se realicen correctamente
		try (PreparedStatement psLike = con.prepareStatement(sqlActualizarLike);
				PreparedStatement psValoracion = con.prepareStatement(sqlActualizarValoracion)) {

			con.setAutoCommit(false);  // Desactivamos autocommit para manejar la transacción

			// Primero, actualizar los "likes" (usuarios que le dieron like al artículo)
			for (String username : setUsuariosLike) {
				psLike.setString(1, username);  // Username
				psLike.setInt(2, idArticulo);   // ID del artículo
				psLike.setInt(3, 1);            // El "like" es 1, porque el usuario le dio like
				psLike.setInt(4, 0);            // Valoración predeterminada (si no se especifica) es 0

				psLike.setInt(5, 0);            // En caso de conflicto, actualizamos el "like" a 0S
				psLike.setInt(6, 0);            // Y la valoración a 0
				psLike.addBatch();              // Añadimos a batch
			}

			// Luego, actualizar las valoraciones (con el TreeMap)
			for (Map.Entry<String, Integer> entry : mapaUsuariosVal.entrySet()) {
				String username = entry.getKey();
				Integer valoracion = entry.getValue();

				psValoracion.setString(1, username);    // Username
				psValoracion.setInt(2, idArticulo);     // ID del artículo
				psValoracion.setInt(3, 0);              // El like predeterminado es 0 si no se especifica
				psValoracion.setInt(4, valoracion);     // La valoración especificada

				psValoracion.setInt(5, valoracion);     // Si hay conflicto, actualizamos la valoración
				psValoracion.addBatch();                // Añadimos a batch
			}

			// Ejecutar las actualizaciones
			psLike.executeBatch();                    // Ejecutar los likes
			psValoracion.executeBatch();              // Ejecutar las valoraciones

			con.commit();  // Confirmamos la transacción

		} catch (SQLException e) {
			try {
				con.rollback();  // Revertimos en caso de error
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);  // Restauramos el autocommit
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static TreeMap<String, Integer> cargarValoraciones(int idArt){
		TreeMap<String, Integer> mVal = new TreeMap<String, Integer>();


		String sql = "SELECT username, valoracion FROM FavArticulo WHERE idArt=?";
		try {
			PreparedStatement ps =  con.prepareStatement(sql);
			ps.setInt(1, idArt);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				String username = rs.getString("username");
				int val = rs.getInt("valoracion");
				mVal.put(username, val);

			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mVal;

	}

	public static Set<String> cargarLikes(int idArt){
		Set<String> sLikes = new HashSet<String>();		
		String sql = "SELECT username FROM FavArticulo WHERE idArt=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idArt);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				sLikes.add(rs.getString("username"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sLikes;

	}
	
	public static ArrayList<Articulo> cargarArticulos() {
		ArrayList<Articulo> lArts = new ArrayList<Articulo>();
		String sql = "SELECT * FROM Articulo";
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int idArt = rs.getInt("idArt");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String fecha = rs.getString("titulo");
				String tipoArt = rs.getString("tipoArt");
				String ruta = rs.getString("rutaArchivoArt");
				
				Articulo a= new Articulo(idArt,titulo,autor,fecha,tipoArt,ruta);
				String sql2 = String.format("Select*From fotoArt where idArt=%d",idArt);
				
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					int idFoto = rs2.getInt("idFoto");
					String descripcion = rs2.getString("descripcion");
					String rutaFoto = rs2.getString("rutaFoto");
					FotoArt f = new FotoArt(idFoto, descripcion, rutaFoto);
					a.getlFotos().add(f);
				}
                rs2.close();
                stmt2.close();
   
				lArts.add(a);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return lArts;
		
	}
	



	//IAG (Herramienta: Chat GPT)
	public static Usuario BuscarUsuario(String nickname) throws Exception {
		String sql = "SELECT * FROM Usuario WHERE username = ?";
		Usuario u = null; 

		try (PreparedStatement ps = con.prepareStatement(sql)) {  
			ps.setString(1, nickname); 


			ResultSet rs = ps.executeQuery();
			while (rs.next()) { 
				String username = rs.getString(1);
				String email = rs.getString(2);
				String contrasenya = rs.getString(3);
				u = new Usuario(username, email, contrasenya);
			}
			rs.close();
			ps.close();
		}
		catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("ERROR CARGANDO LOS DATOS");
		}  
		return u; 
	}



	public static void vaciarTablaUsuarios() {
		String sql = "DELETE FROM Usuario";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.executeUpdate();
			System.out.println("Todos los datos de la tabla Usuario han sido eliminados.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al vaciar la tabla Usuario.");
		}
	}

	/*
	//Para calcular nota media de un artículo
	public static double calcularNotaMedia(Connection con, int idArt) throws Exception {
		String sql = "SELECT ABG(valoracion) AS nota_media FROM favArt WHERE idArt = ?";
		
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idArt);
			
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					return rs.getDouble("nota_media");
				}
			}
		}
		return 0.0; //si no hay valoracioens
	} */
	
	
	//Consulta a fuentes, ejemplos externos
	//Para aclcular nota media de TODOS los artículos con una consulta
	public static Map<Integer, Double> CalcularNotasMedias(Connection con) throws Exception {
		String sql = "SELECT idArt, AVG(valoracion) AS nota_media FROM favArt GROUP BY idArt";
		Map<Integer, Double> notasMedias = new HashMap<>();
	
		try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int idArt = rs.getInt("idArt");
					double notaMedia = rs.getDouble("nota_media");
					notasMedias.put(idArt, notaMedia);
				}
			}
		return notasMedias;
	}
	



}
