package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPerfil extends JFrame{
	private JPanel pCentro, pNorte, pSur, pEste,pOeste,pOesteSup,pOesteInf;
	private JButton btnEstadistica, btnInformacion, btnFavoritos, btnVistos;
	private JLabel lblFotoPerfil, lblTitulo, lblNombreUsuario;
	
	private JFrame vActual;
	
	public VentanaPerfil() {//Hace falta meter un usuario como 
							//parametro para poder ejercutar la foto de perfil y su nombre etc...
		vActual=this;
		
		setBounds(100, 100, 1000, 600);
		setLayout(new BorderLayout(10, 10));
		setTitle("Mi Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
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
		btnVistos = new JButton("Vistos Recientemente");
		
		lblFotoPerfil = new JLabel();
		lblTitulo = new JLabel("MI PERFIL");
		lblNombreUsuario = new JLabel("Usuario prueba");
		
	
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
		pOesteInf.add(btnVistos);
		pOesteInf.add(btnEstadistica);
		
		
		
			
		
		
		setVisible(true);
		
	}

}
