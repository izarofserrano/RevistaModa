package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		
		
		JButton btnEntrar = new JButton("Entrar");

		JTextField userName = new JTextField();
		JTextField contra = new JTextField();
		
		pEste.add(userName);
		pEste.add(contra);
		pEste.add(btnEntrar);
		
		setVisible(true);
	}

}
