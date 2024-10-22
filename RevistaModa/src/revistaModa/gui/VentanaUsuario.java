package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import revistaModa.clases.Usuario;

public class VentanaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel p1, p2, p22, p3;
	private JTextField txtUserName, txtCorreo;
	private JPasswordField contra;
	private JPanel pEste, pSur;

	private List<Usuario> lUsuarios;

	public  VentanaUsuario(List<Usuario> lUsuarios) {
		this.lUsuarios = lUsuarios;
		setBounds(100, 100, 1000, 600);
		setTitle("User");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pNorte = new JPanel(new BorderLayout());
		pEste = new JPanel();
		pEste.setLayout(new GridLayout(4, 1));
		pEste.setBorder(new EmptyBorder(90, 90, 90, 90));
		JPanel pOeste = new JPanel();
		pSur = new JPanel();

		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pNorte, BorderLayout.NORTH);

		txtUserName = new JTextField();

		txtUserName.setColumns(25);

		JLabel Usuario = new JLabel("Usuario:        ");
		Usuario.setVerticalAlignment(SwingConstants.EAST);
		Usuario.setFont(new Font("Arial", Font.BOLD, 12));
		Usuario.setPreferredSize(new Dimension(100, 20));

		p1 = new JPanel();
		p1.add(Usuario);
		p1.add(txtUserName);
		pEste.add(p1);

		contra = new JPasswordField();

		contra.setColumns(25);

		JLabel password = new JLabel("Contraseña:   ");
		password.setVerticalAlignment(SwingConstants.EAST);
		password.setFont(new Font("Arial", Font.BOLD, 12));
		password.setPreferredSize(new Dimension(100, 20));
		

		p2 = new JPanel();
		p2.add(password);
		p2.add(contra);
		pEste.add(p2);

		
		JLabel caracteristicas= new JLabel("<html>- La contraseña debe tener:<br>Una mayúscula y un número</html>");
		caracteristicas.setVerticalAlignment(SwingConstants.EAST);
		caracteristicas.setFont(new Font("Arial", Font.ITALIC,11));
		p22= new JPanel();
		p22.add(caracteristicas);
		pSur.add(p22);
		
		
		JButton btnEntrar = new JButton("Entrar");
		JButton btnRegistrarse = new JButton("Registráte!");
		p3 = new JPanel();
		p3.add(btnEntrar);
		p3.add(btnRegistrarse);
		pEste.add(p3);

		// al iniciar sesion en tu cuenta vuelves a aparecer con la ventana inicial
		// pero ahora te sale la opcion de entrar a tu cuenta
		btnEntrar.addActionListener((e) -> {
			boolean usuarioEncontrado = false;
			String Password = new String(contra.getPassword());

			for (Usuario user : lUsuarios) {
				if (txtUserName.getText().equals(user.getUsername())) {
					usuarioEncontrado = true;
					if (Password.equals(user.getContrasenya())) {
						new VentanaInicial(true);
						dispose();
						return;
					} else {
						JOptionPane.showConfirmDialog(null, "La contraseña no coincide", "Error password",
								JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			if (!usuarioEncontrado) {
				JOptionPane.showConfirmDialog(null, "No existe ese user", "Error user", JOptionPane.CANCEL_OPTION,
						JOptionPane.ERROR_MESSAGE);
				return;
			}

		});

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setSize(new Dimension(300, 300));

		// ATRAS lamda
		btnAtras.addActionListener((e) -> {
			new VentanaInicial(false);
			dispose();
		});

		JPanel p4 = new JPanel();
		p4.add(btnAtras);
		pNorte.add(p4, BorderLayout.WEST);

		
		ImageIcon iconoTitle = new ImageIcon("RevistaModa\\img\\Logo_UD_Vogue.png");
		Image fotoTitle = iconoTitle.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel lblTitle = new JLabel(new ImageIcon(fotoTitle));
		
//		JLabel title = new JLabel("Title");
//		title.setFont(new Font("Arial", Font.BOLD, 50));

		JPanel p5 = new JPanel();
		p5.add(lblTitle);
		pNorte.add(p5);

		// btn Registrarse
		btnRegistrarse.addActionListener((e) -> {
			mostrarPantallaRegistro();

		});

		JLabel lblimagen = new JLabel();
		try {
			ImageIcon iconoPortada = new ImageIcon("RevistaModa\\img\\Logo_UD_Vogue.png");
			Image imgPortada = iconoPortada.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
			lblimagen = new JLabel(new ImageIcon(imgPortada));
			pOeste.add(lblimagen);

		} catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen" + e.getMessage());
		}

		setVisible(true);
	}

	private void mostrarPantallaRegistro() {
		pEste.removeAll();

		JLabel mail = new JLabel("Correo: ");
		mail.setVerticalAlignment(SwingConstants.EAST);
		mail.setFont(new Font("Arial", Font.BOLD, 12));
		mail.setPreferredSize(new Dimension(100, 20));

		txtCorreo = new JTextField();
		txtCorreo.setColumns(25);

		JPanel pCorreo = new JPanel();
		pCorreo.add(mail);
		pCorreo.add(txtCorreo);

		JButton btnRegistrar = new JButton("Resgistrame");
		JButton btnIniSesion = new JButton("Iniciar Sesión");
		JPanel p9 = new JPanel();
		p9.add(btnRegistrar);
		p9.add(btnIniSesion);

		pEste.add(p1);
		pEste.add(p2);
		pEste.add(pCorreo);
		pEste.add(p9);
		pSur.add(p22);

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(contra.getPassword());
				String user = txtUserName.getText();
				String correo = txtCorreo.getText();
				boolean usuarioExiste = false;

				for (Usuario usuario : lUsuarios) {
					if (user.equals(usuario.getUsername())) {
						JOptionPane.showConfirmDialog(null, "Este user ya existe", "ERROR", JOptionPane.CANCEL_OPTION,
								JOptionPane.ERROR_MESSAGE);
						usuarioExiste = true;
						break;
					}
				}
				if (usuarioExiste) {
					return;
				}
				if (!validarContra(password)) {
					JOptionPane.showConfirmDialog(null,
							"La contraseña no cumple con las carcteristicas: \n Una mayuscula y un numero", "ERROR",
							JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
				Usuario u = new Usuario(lUsuarios.size() + 1, user, password, user);

				lUsuarios.add(u);
				System.out.println(lUsuarios);

				JOptionPane.showConfirmDialog(null, "has sido registrado con éxito", "RESGISTRO",
						JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				txtUserName.setText("");
				contra.setText("");
				txtCorreo.setText("");

			}
		});

		/* Metodo que vuelve a la pantalla de iniciar sesion */

		btnIniSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPantallaIniSesion();
			}
		});

		pEste.revalidate();
		pEste.repaint();
	}

	/* Método para validar Contraseña */
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

	private void mostrarPantallaIniSesion() {
		pEste.removeAll();

		pEste.add(p1);
		pEste.add(p2);
		pEste.add(p3);

		pSur.add(p22);
		pEste.revalidate();
		pEste.repaint();
	}
}