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
import java.util.TreeSet;

import revistaModa.clases.Articulo;
import revistaModa.clases.FotoArt;
import revistaModa.clases.Usuario;

public class GestorBD {
	private static Connection con;

	public static void initBD(String nombreBD) {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			// Habilitar WAL
			try (Statement stmt = con.createStatement()) {
				stmt.execute("PRAGMA journal_mode = WAL;");
			}
			// Configurar el tiempo de espera para los bloqueos
			try (Statement stmt = con.createStatement()) {
				stmt.execute("PRAGMA busy_timeout = 3000;");  // 3 segundos de espera
			}
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

	public static boolean updateContrasenya(Usuario usu) {


		String sql = "UPDATE Usuario SET contrasenya = ? WHERE username = ?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, usu.getContrasenya());  // Nueva contraseña
			stmt.setString(2, usu.getUsername()); 

			int filasAfectadas = stmt.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	//IAG (herramienta: ChatGPT)
	public static void borrarArticulo(int idArt) {
		String sql = "DELETE FROM Articulo WHERE idArt = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idArt);
			ps.executeUpdate();
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


	public static void actualizarValoraciones(Map<String, Integer> valoraciones, Set<String> likes, int idArticulo) {
		try {
			// Usar la conexión ya abierta
			Connection conn = con;
			conn.setAutoCommit(false);

			String sqlUpdateFavArticulo = """ 
					    INSERT OR REPLACE INTO FavArticulo (username, idArt, like, valoracion) VALUES (?, ?, ?, ?); 
					""";
			try (PreparedStatement psFavArticulo = conn.prepareStatement(sqlUpdateFavArticulo)) {
				for (Map.Entry<String, Integer> entry : valoraciones.entrySet()) {
					String usuario = entry.getKey();
					int valoracion = entry.getValue();
					int like = (likes.contains(usuario)) ? 1 : 0;
					psFavArticulo.setString(1, usuario);
					psFavArticulo.setInt(2, idArticulo);
					psFavArticulo.setInt(3, like);
					psFavArticulo.setInt(4, valoracion);
					psFavArticulo.addBatch();
				}
				psFavArticulo.executeBatch();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public static HashMap<Articulo, ArrayList<Integer>> obtenerValLikeporUsu(Usuario usu) {
		HashMap<Articulo, ArrayList<Integer>> favUsu = new HashMap<>();

		String sql = """
				    SELECT a.idArt, a.titulo, a.autor, a.fechaPublicacion, a.tipoArt, a.rutaArchivoArt, f.like, f.valoracion
				    FROM FavArticulo f
				    JOIN Articulo a ON f.idArt = a.idArt
				    WHERE f.username = ? AND (f.like = 1 OR (f.valoracion >= 1 AND f.valoracion <= 5))
				""";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usu.getUsername());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Articulo articulo = new Articulo(
						rs.getInt("idArt"),
						rs.getString("titulo"),
						rs.getString("autor"),
						rs.getString("fechaPublicacion"),
						rs.getString("tipoArt"),
						rs.getString("rutaArchivoArt")
						);

				// Obtener fotos asociadas al artículo
				String sqlFotos = """
						    SELECT idFoto, descripcion, rutaFoto
						    FROM FotoArt
						    WHERE idArt = ?
						""";
				try (PreparedStatement psFotos = con.prepareStatement(sqlFotos)) {
					psFotos.setInt(1, articulo.getIdArt());
					ResultSet rsFotos = psFotos.executeQuery();

					while (rsFotos.next()) {
						FotoArt foto = new FotoArt(
								rsFotos.getInt("idFoto"),
								rsFotos.getString("descripcion"),
								rsFotos.getString("rutaFoto")
								);
						articulo.getlFotos().add(foto);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				ArrayList<Integer> valLike = new ArrayList<>(2);
				valLike.add(rs.getInt("like")); 
				valLike.add(rs.getInt("valoracion")); 

				favUsu.put(articulo, valLike);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return favUsu;
	}



	public static String buscarFotoPorArt(int idArt) {

		String rutaFoto = null;

		//IAG: ChatGPT (Solo la sentencia sql)
		String sql = """
				    SELECT rutaFoto
				    FROM FotoArt
				    WHERE idArt = ?
				    LIMIT 1
				""";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idArt);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				rutaFoto = rs.getString("rutaFoto");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rutaFoto;
	}



	public static Integer countLikes(int idArt) {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM FavArticulo WHERE idArt=? AND like=1";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idArt);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
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

	public static List<Articulo> cargarFavoritos(String u) {
		List<Articulo> sFav = new ArrayList<Articulo>();

		//IAG: ChatGPT (Solo la sentencia sql)
		String sql = """
				    SELECT a.idArt, a.titulo, a.autor, a.fechaPublicacion, a.tipoArt, a.rutaArchivoArt
				    FROM FavArticulo f
				    JOIN Articulo a ON f.idArt = a.idArt
				    WHERE f.username = ?
				""";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idArt = rs.getInt("idArt");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String fechaPublicacion = rs.getString("fechaPublicacion");
				String tipoArt = rs.getString("tipoArt");
				String rutaArchivoArt = rs.getString("rutaArchivoArt");

				Articulo articulo = new Articulo(idArt, titulo, autor, fechaPublicacion, tipoArt, rutaArchivoArt);
				sFav.add(articulo);
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sFav;
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


				String sql3 = String.format("SELECT * FROM FavArticulo where idArt=%d", idArt);
				Statement stmt3 = con.createStatement();
				ResultSet rs3 = stmt3.executeQuery(sql3);
				Set<String> u = new HashSet();
				while(rs3.next()) {
					String s = rs3.getString("username");
					u.add(s);
				}
				rs3.close();
				stmt3.close();
				a.setSetUsuariosLike(u);
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


	public static Float calcularNotaMedia(int idArt){
		String sql = "SELECT AVG(valoracion) AS nota_media FROM FavArticulo WHERE idArt = ?";
	    Float notaMedia = 0.f;

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, idArt); 
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                notaMedia = rs.getFloat("nota_media");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al calcular la nota media");
	    }
	    return notaMedia;
	}


	//Consulta a fuentes, ejemplos externos
	//Para aclcular nota media de TODOS los artículos con una consulta
	public static Map<Integer, Double> CalcularNotasMedias(){
		String sql = "SELECT idArt, AVG(valoracion) AS nota_media FROM favArt GROUP BY idArt";
		Map<Integer, Double> notasMedias = new HashMap<>();

		try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int idArt = rs.getInt("idArt");
				double notaMedia = rs.getDouble("nota_media");
				notasMedias.put(idArt, notaMedia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al calcular la nota media");
		}
		return notasMedias;
	}




}
