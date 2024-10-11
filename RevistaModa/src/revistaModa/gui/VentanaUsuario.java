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
		setBounds(100,100,1000,500);
		setTitle("User");
		
		JPanel pNorte = new JPanel();
		JPanel pDrcha = new JPanel();
		JPanel pIzq = new JPanel();
		pIzq.setLayout(new GridLayout(4,1));
		JPanel pSur = new JPanel();
		
		getContentPane().add(pSur,BorderLayout.SOUTH);
		getContentPane().add(pDrcha,BorderLayout.WEST);
		getContentPane().add(pIzq,BorderLayout.EAST);
		getContentPane().add(pNorte,BorderLayout.NORTH);
		
		
		JButton btnEntrar = new JButton("Entrar");

		JTextField userName = new JTextField();
		JTextField contra = new JTextField();
		
		
		
		setVisible(true);
	}

}
