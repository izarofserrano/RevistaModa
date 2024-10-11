package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VentanaInicial extends JFrame {
	private JButton btnInicio, btnModa, btnBelleza, btnLogIn, btnBuscador;
	private JLabel lblTitulo;
	private JPanel pCentro, pNorte, pSur, pEste, pOeste;

	
	private JFrame vActual;
	
	public VentanaInicial() {
		vActual = this;
		
		setBounds(100, 100, 1000, 600);
		
		setTitle("VOGUE");
		
		pCentro = new JPanel();
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		btnInicio = new JButton("INICIO");
		btnModa = new JButton("MODA");
		btnBelleza = new JButton("BELLEZA");
		btnLogIn = new JButton("Log In");
		
		Dimension buttonSize = new Dimension(100, 40);
		btnInicio.setPreferredSize(buttonSize);
		btnModa.setPreferredSize(buttonSize);
		btnBelleza.setPreferredSize(buttonSize);
		btnLogIn.setPreferredSize(buttonSize);
		
		btnBuscador = new JButton();
		btnBuscador.setPreferredSize(new Dimension(40,40));
		btnBuscador.setToolTipText("Buscar");
		
		ImageIcon imagen = new ImageIcon("img/lupa.png");
		setIconImage(imagen.getImage());
		
		pNorte.add(btnInicio);
		pNorte.add(btnModa);
		pNorte.add(btnBelleza);
		pNorte.add(btnLogIn);
		pNorte.add(btnBuscador);
		
		
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VentanaUsuario();
				
			}
			
		});
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaInicial v = new VentanaInicial();
	}

}
