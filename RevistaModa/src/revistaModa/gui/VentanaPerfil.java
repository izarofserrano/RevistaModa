package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import revistaModa.bd.GestorBD;
import revistaModa.clases.Articulo;
import revistaModa.clases.RevistaModa;
import revistaModa.clases.Usuario;

public class VentanaPerfil extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pNorte, pSur, pEste,pOeste,pOesteSup,pOesteInf;
	private JButton btnEstadistica, btnInformacion, btnFavoritos, btnCambiarContra;
	private JLabel lblFotoPerfil, lblTitulo, lblNombreUsuario,lblImagenBienvenido;
	private JTextField txtNuevaContra;
	private RendererEstadistica renderer;
	private RendererFavoritos renderer2;
	private Usuario usuario;
	private HashMap<Articulo, ArrayList<Integer>> favUsu;
    private Font textFont;


	private JTable tablaEstadistica , tablaFavoritos;

	@SuppressWarnings("unused")
	private JFrame vActual;
	private List<Articulo> articulos = GestorBD.cargarArticulos();

	public VentanaPerfil(Usuario u) {//Hace falta meter un usuario como 
		vActual=this;
		usuario = u;

		setBounds(100, 100, 1000, 600);
		setLayout(new BorderLayout(10, 10));
		setTitle("Mi Perfil");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("UDVogue_Perfil");

		ImageIcon imagen = new ImageIcon("RevistaModa/img/Logo_UD_Vogue_iconito.png");
		setIconImage(imagen.getImage());

		pCentro = new JPanel();
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel(new GridLayout(2,1));
		pOesteInf = new JPanel(new GridLayout(4,1,0,20));
		pOesteSup = new JPanel(new GridLayout(2,1));

		btnEstadistica = new JButton("Estadisticas");
		btnInformacion = new JButton("Mi información");
		btnFavoritos = new JButton("Mis Favoritos");
		btnCambiarContra = new JButton("Cambiar Contraseña");
		btnCambiarContra.setEnabled(false);

		textFont = new Font("Arial", Font.PLAIN, 14);
		txtNuevaContra = new JTextField(); // Aquí lo inicializas como un campo de clase
	    txtNuevaContra.setFont(textFont);
	    txtNuevaContra.setPreferredSize(new Dimension(200, 25));

		lblFotoPerfil = new JLabel();
		lblTitulo = new JLabel("MI PERFIL");
		lblNombreUsuario = new JLabel("Usuario "+u.getUsername());


		ImageIcon iconoBienvenida = new ImageIcon("RevistaModa/img/ImagenBienvenidaPerfil.jpeg");
		Image imgBienvenida = iconoBienvenida.getImage();//.getScaledInstance(700, 600, Image.SCALE_SMOOTH);
		lblImagenBienvenido = new JLabel(new ImageIcon(imgBienvenida));
		pCentro.add(lblImagenBienvenido);
		pCentro.validate();
		pCentro.repaint();



		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNorte.add(lblTitulo);

		pOeste.add(pOesteSup);
		pOeste.add(pOesteInf);

		pOesteSup.add(lblNombreUsuario);
		pOesteSup.add(lblFotoPerfil);
		pOesteInf.add(btnInformacion);
		pOesteInf.add(btnFavoritos);
		pOesteInf.add(btnEstadistica);
		pOesteInf.add(btnCambiarContra);

		btnFavoritos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				favoritos(pCentro);
				pCentro.revalidate(); 
				pCentro.repaint();
			}
		});


		btnEstadistica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				estadisticas(pCentro);

			}
		});


		btnInformacion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MiInformacion(pCentro, u);


			}
		});


		btnCambiarContra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, 
						"¿Estás seguro de que quieres cambiar tu contraseña?", 
						"Confirmación de cambio de contraseña", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					String password = txtNuevaContra.getText();

					if (password.equals("") || password.equals(" ")) {
						JOptionPane.showMessageDialog(null, 
								"Debes de introducir una nueva contraseña", 
								"Error al cambiar Contraseña", 
								JOptionPane.ERROR_MESSAGE);
					} else if (!validarContra(password)) {
						JOptionPane.showMessageDialog(null, 
								"Debes de introducir al menos una Mayúscula y un número", 
								"Error al cambiar Contraseña", 
								JOptionPane.ERROR_MESSAGE);
					} else {
						u.setContrasenya(password);
						boolean cambiado = GestorBD.updateContrasenya(u);

						if (cambiado) {
							JOptionPane.showMessageDialog(null, 
									"Contraseña cambiada con éxito", 
									"Update Contraseña", 
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, 
									"Hubo un error al cambiar la contraseña", 
									"Error al cambiar Contraseña", 
									JOptionPane.ERROR_MESSAGE);
						}

						txtNuevaContra.setText("");                
					}
				}
			}
		});

		txtNuevaContra.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				habilitarBoton();
			}

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        habilitarBoton();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        habilitarBoton();
		    }

		    // Método para habilitar/deshabilitar el botón
		    private void habilitarBoton() {
		        // Si el campo de texto no está vacío, habilitar el botón
		        if (!txtNuevaContra.getText().trim().isEmpty()) {
		            btnCambiarContra.setEnabled(true);
		        } else {
		            // Si está vacío, deshabilitar el botón
		            btnCambiarContra.setEnabled(false);
		        }
		    }
		});
		setVisible(true);


	}

	private JPanel estadisticas(JPanel panel) {
		panel.removeAll();
		panel.setLayout(new BorderLayout());

		tablaEstadistica = new JTable();

		tablaEstadistica.setModel(new ModeloEstadisticas(articulos));
		renderer = new RendererEstadistica();

		for (int i = 0; i < tablaEstadistica.getColumnModel().getColumnCount(); i++) {
			tablaEstadistica.getColumnModel().getColumn(i).setCellRenderer(renderer);
			tablaEstadistica.getColumnModel().getColumn(i).setPreferredWidth(200);
		}
		tablaEstadistica.setRowHeight(80);
		tablaEstadistica.getTableHeader().setReorderingAllowed(false);

		panel.add(new JScrollPane(tablaEstadistica), BorderLayout.CENTER);

		panel.validate();
		panel.repaint();

		return panel;
	}

	private JPanel favoritos(JPanel panel) {
		panel.removeAll();
		panel.setLayout(new BorderLayout());

		favUsu = GestorBD.obtenerValLikeporUsu(usuario);

		List<Articulo> articulosFavoritos = GestorBD.cargarFavoritos(usuario.getUsername());

		if (articulosFavoritos == null || articulosFavoritos.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No tienes artículos favoritos", "Favoritos vacíos", JOptionPane.INFORMATION_MESSAGE);
			return panel;
		}

		tablaFavoritos = new JTable(new ModeloFavoritos(favUsu));
		renderer2 = new RendererFavoritos();

		for (int i = 0; i < tablaFavoritos.getColumnModel().getColumnCount(); i++) {
			tablaFavoritos.getColumnModel().getColumn(i).setCellRenderer(renderer2);
			tablaFavoritos.getColumnModel().getColumn(i).setPreferredWidth(200);
		}

		tablaFavoritos.setRowHeight(80);
		tablaFavoritos.getTableHeader().setReorderingAllowed(false);

		panel.add(new JScrollPane(tablaFavoritos), BorderLayout.CENTER);
		panel.validate();
		panel.repaint();

		return panel;
	}
	private JPanel MiInformacion(JPanel panel, Usuario u) {
		panel.removeAll();
	    panel.setLayout(new GridLayout(5, 2, 20, 20));

	    Font labelFont = new Font("Arial", Font.BOLD, 14);
	    textFont = new Font("Arial", Font.PLAIN, 14);

	    JLabel lbl1 = new JLabel("Nombre: ");
	    lbl1.setFont(labelFont);
	    JLabel lbl11 = new JLabel(u.getUsername());
	    lbl11.setFont(textFont);

	    JLabel lbl2 = new JLabel("Correo: ");
	    lbl2.setFont(labelFont);
	    JLabel lbl22 = new JLabel(u.getEmail());
	    lbl22.setFont(textFont);

	    JLabel lbl3 = new JLabel("Contraseña Actual: ");
	    lbl3.setFont(labelFont);
	    String contrasenaOculta = u.getContrasenya().replaceAll(".", "*");  // Reemplaza todos los caracteres por asteriscos

	    // Mostrar la contraseña oculta en un JLabel
	    JLabel lbl33 = new JLabel(contrasenaOculta);
	    lbl33.setFont(textFont);

	    JLabel lbl4 = new JLabel("Nueva Contraseña: ");
	    lbl4.setFont(labelFont);

	    

	    panel.add(lbl1);
	    panel.add(lbl11);
	    panel.add(lbl2);
	    panel.add(lbl22);
	    panel.add(lbl3);
	    panel.add(lbl33);
	    panel.add(lbl4);
	    panel.add(txtNuevaContra);

	    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

	    lbl1.setHorizontalAlignment(JLabel.RIGHT);
	    lbl2.setHorizontalAlignment(JLabel.RIGHT);
	    lbl3.setHorizontalAlignment(JLabel.RIGHT);
	    lbl4.setHorizontalAlignment(JLabel.RIGHT);
	    lbl11.setHorizontalAlignment(JLabel.LEFT);
	    lbl22.setHorizontalAlignment(JLabel.LEFT);
	    lbl33.setHorizontalAlignment(JLabel.LEFT);
	    txtNuevaContra.setHorizontalAlignment(JLabel.LEFT);

	    panel.validate();
	    panel.repaint();

	    return panel;
	}


	public boolean validarContra(String password) {
		boolean Mayus = false;
		boolean Num = false;
		for (Character c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				Mayus = true;
			}
			if (Character.isDigit(c)) {
				Num = true;
			}

			// Op AND
		}
		return Mayus && Num;
	}
	public List<Articulo> articulosfavortios (Usuario u,List<Articulo> arts){
		List<Articulo> Uarts = new ArrayList();
		for(Articulo a : arts) {
			for(String usuario : a.getSetUsuariosLike()) {
				if(u.getUsername().equals(usuario)) {
					Uarts.add(a);
				}else {
					continue;
				}
			}
		}
		return Uarts;
	}

	/*private class ModeloEstadisticas extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private List<Articulo> articulos;
		private String[] columnNames = {"ID", "Título", "Autor", "Imagen", "Progreso"};

		public ModeloEstadisticas(List<Articulo> articulos) {
			this.articulos = articulos;
		}

		@Override
		public int getRowCount() {
			return articulos.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Articulo articulo = articulos.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return articulo.getIdArt();
			case 1:
				return articulo.getTitulo();
			case 2:
				return articulo.getAutor();

			default:
				return null;
			}
		}

		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
	}*/

}
