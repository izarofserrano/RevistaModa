package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public VentanaUsuario() {
		setBounds(100,100,900,600);
		setTitle("User");
		setLocationRelativeTo(null);
		
		JPanel pNorte = new JPanel();
		JPanel pEste = new JPanel();
		pEste.setLayout(new GridLayout(4,1));
		JPanel pOeste = new JPanel();
		JPanel pSur = new JPanel();
		
		getContentPane().add(pSur,BorderLayout.SOUTH);
		getContentPane().add(pOeste,BorderLayout.WEST);
		getContentPane().add(pEste,BorderLayout.EAST);
		getContentPane().add(pNorte,BorderLayout.NORTH);
		
		
		
		JTextField userName = new JTextField();
		userName.setSize(new Dimension(300,75));
		userName.setColumns(40);
		
		JLabel Usuario = new JLabel("Usuario: ");
		Usuario.setVerticalAlignment(SwingConstants.EAST);
		Usuario.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel  p1 = new JPanel();
		p1.add(Usuario);
		p1.add(userName);
		pEste.add(p1);
        
	
		
		JPasswordField contra = new JPasswordField();
		contra.setSize(new Dimension(300,75));
		contra.setColumns(40);
		
		JLabel password = new JLabel("Contraseña: ");
		password.setVerticalAlignment(SwingConstants.EAST);
		password.setFont(new Font("Arial", Font.BOLD, 18));
		
		JPanel  p2 = new JPanel();
		p2.add(password);
		p2.add(contra);
		pEste.add(p2);
		
		
		JButton btnEntrar = new JButton("Entrar");
		JPanel p3 = new JPanel();
		p3.add(btnEntrar);
		pEste.add(p3);
		
		
		JLabel title = new JLabel("Title");
		title.setFont(new Font("Arial", Font.BOLD, 50));
		pNorte.add(title);
		JPanel p4 = new JPanel();
		p4.add(title);
		pNorte.add(p4);
		
		JButton btnAtras= new JButton("Atrás");
		btnAtras.setSize(new Dimension(300,300));
		btnAtras.setAlignmentX(SwingConstants.WEST);
		//lamda
		btnAtras.addActionListener((e)->{
			new VentanaInicial();
		});
		JPanel p5 = new JPanel();
		p5.add(btnAtras);
		pNorte.add(p5);
		
		
		
		
		setVisible(true);
	}

}
