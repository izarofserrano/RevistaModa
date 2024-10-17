package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel p1, p2, p3; 
	private JTextField txtUserName, txtCorreo; 
	private JPasswordField contra;
	private JPanel pEste;

	public VentanaUsuario() {
		setBounds(100, 100, 900, 600);
		setTitle("User");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pNorte = new JPanel();
		pEste = new JPanel();
		pEste.setLayout(new GridLayout(4, 1));
		JPanel pOeste = new JPanel();
		JPanel pSur = new JPanel();

		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pNorte, BorderLayout.NORTH);

		txtUserName = new JTextField();
		txtUserName.setSize(new Dimension(300, 75));
		txtUserName.setColumns(30);

		JLabel Usuario = new JLabel("Usuario: ");
		Usuario.setVerticalAlignment(SwingConstants.EAST);
		Usuario.setFont(new Font("Arial", Font.BOLD, 18));

		p1 = new JPanel();
		p1.add(Usuario);
		p1.add(txtUserName);
		pEste.add(p1);

		contra = new JPasswordField();
		contra.setSize(new Dimension(300, 75));
		contra.setColumns(30);

		JLabel password = new JLabel("Contraseña: ");
		password.setVerticalAlignment(SwingConstants.EAST);
		password.setFont(new Font("Arial", Font.BOLD, 18));

		p2 = new JPanel();
		p2.add(password);
		p2.add(contra);
		pEste.add(p2);

		JButton btnEntrar = new JButton("Entrar");
		JButton btnRegistrarse = new JButton("Registráte!");
		p3 = new JPanel();
		p3.add(btnEntrar);
		p3.add(btnRegistrarse);
		pEste.add(p3);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.setSize(new Dimension(300, 300));
		btnAtras.setAlignmentX(SwingConstants.WEST);

		// ATRAS lamda
		btnAtras.addActionListener((e) -> {
			new VentanaInicial();
			dispose();
		});

		JPanel p4 = new JPanel();
		p4.add(btnAtras);
		pNorte.add(p4);

		JLabel title = new JLabel("Title");
		title.setFont(new Font("Arial", Font.BOLD, 50));

		JPanel p5 = new JPanel();
		p5.add(title);
		pNorte.add(p5);

		// btn Registrarse
		btnRegistrarse.addActionListener((e) -> {
			mostrarPantallaRegistro();
		
		});
		

		JLabel lblimagen = new JLabel();
		try {
			ImageIcon iconoPortada = new ImageIcon("RevistaModa\\img\\SesionInicioImagen.jpg");
			Image imgPortada = iconoPortada.getImage().getScaledInstance(360, 500, Image.SCALE_SMOOTH);
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
		mail.setFont(new Font("Arial", Font.BOLD, 18));
		
		txtCorreo = new JTextField();
		txtCorreo.setSize(new Dimension(300, 75));
		txtCorreo.setColumns(30);
		
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

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/* TEMPORAL HASTA LA BDD */
				String password = new String(contra.getPassword());
				System.out.println(txtUserName.getText() + "-" + password + "-" + txtCorreo.getText());
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

		private void mostrarPantallaIniSesion() {
			pEste.removeAll();

			pEste.add(p1);
			pEste.add(p2);
			pEste.add(p3);

			pEste.revalidate();
			pEste.repaint();
		}
	
}