package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
	private Usuario usuario;

	private JTable tablaEstadistica , tablaFavoritos;

	@SuppressWarnings("unused")
	private JFrame vActual;
	private List<Articulo> articulos = GestorBD.cargarArticulos();

	public VentanaPerfil(Usuario u) {//Hace falta meter un usuario como 
		//parametro para poder ejercutar la foto de perfil y su nombre etc...
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
	

		lblFotoPerfil = new JLabel();
		lblTitulo = new JLabel("MI PERFIL");
		lblNombreUsuario = new JLabel("Usuario prueba");


		ImageIcon iconoBienvenida = new ImageIcon("RevistaModa/img/imagenBienvenidaPerfil.jpeg");
		Image imgBienvenida = iconoBienvenida.getImage().getScaledInstance(700, 600, Image.SCALE_SMOOTH);
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
				// TODO Auto-generated method stub
				String password = txtNuevaContra.getText();

				if(password.equals("")|password.equals(" ")) {
					JOptionPane.showConfirmDialog(null,"Debes de introducir una nueva contraseña","Error al cambiar Contraseña" ,JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
				}

				else if (!validarContra(password)) {
					JOptionPane.showConfirmDialog(null,"Debes de introducir al menos una Mayuscula y un numero","Error al cambiar Contraseña" ,JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

				}

				else {
					u.setContrasenya(txtNuevaContra.getText());
					JOptionPane.showConfirmDialog(null,"Contrasela cambiada con exito","Update Contraseña" ,JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					System.out.println(u.getContrasenya());
					txtNuevaContra.setText("");				
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
	      //  tablaEstadistica.getColumnModel().getColumn(i).setCellRenderer(renderer);
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

	    tablaFavoritos = new JTable();

	    tablaFavoritos.setModel(new ModeloEstadisticas(articulosfavortios(usuario, articulos)));
	   

	    for (int i = 0; i < tablaFavoritos.getColumnModel().getColumnCount(); i++) {
	    	tablaFavoritos.getColumnModel().getColumn(i).setCellRenderer(renderer);
	        tablaFavoritos.getColumnModel().getColumn(i).setPreferredWidth(200);
	    }
	    tablaFavoritos.setRowHeight(80);
	    tablaFavoritos.getTableHeader().setReorderingAllowed(false);

	    panel.add(new JScrollPane(tablaFavoritos), BorderLayout.CENTER);

	    panel.validate();
	    panel.repaint();

	    return panel;
	}

	private JPanel MiInformacion (JPanel panel,Usuario u){

		panel.removeAll();
		panel.setLayout(new GridLayout(4,2,40,40));
		
		
		
		

		JLabel lbl1 = new JLabel("Nombre: ");
		lbl1.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		JLabel lbl11 = new JLabel(u.getUsername());


		JLabel lbl2 = new JLabel("Correo: ");
		lbl2.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		JLabel lbl22 = new JLabel(u.getEmail());

		JLabel lbl3 = new JLabel("Contraseña Actual: ");
		lbl3.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		JLabel lbl33 = new JLabel(u.getContrasenya());

		JLabel lbl4 = new JLabel("Nueva Contraseña: ");
		lbl4.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		txtNuevaContra = new JTextField();
		


		panel.add(lbl1);
		panel.add(lbl11);
		panel.add(lbl2);
		panel.add(lbl22);
		panel.add(lbl3);
		panel.add(lbl33);
		panel.add(lbl4);
		panel.add(txtNuevaContra);
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

}
