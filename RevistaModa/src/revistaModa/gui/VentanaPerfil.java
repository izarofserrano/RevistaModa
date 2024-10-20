package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import revistaModa.clases.Articulo;

public class VentanaPerfil extends JFrame{
	private JPanel pCentro, pNorte, pSur, pEste,pOeste,pOesteSup,pOesteInf;
	private JButton btnEstadistica, btnInformacion, btnFavoritos, btnVistos;
	private JLabel lblFotoPerfil, lblTitulo, lblNombreUsuario;
	
	private JTable tablaEstadistica ;
	
	private JFrame vActual;
	private List<Articulo> articulos;
	
	public VentanaPerfil() {//Hace falta meter un usuario como 
							//parametro para poder ejercutar la foto de perfil y su nombre etc...
		vActual=this;
		
		setBounds(100, 100, 1000, 600);
		setLayout(new BorderLayout(10, 10));
		setTitle("Mi Perfil");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		pCentro = new JPanel(new FlowLayout());
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
		
		btnEstadistica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				estadisticas(pCentro);
				
			}
		});
		
		
		btnInformacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MiInformacion(pCentro);
				
			}
		});
			
		
		
		setVisible(true);
		
	}
	private JPanel estadisticas (JPanel panel) {
		panel.removeAll();
		
		tablaEstadistica = new JTable();
		tablaEstadistica.setModel(new ModeloEstadisticas(articulos));
		
		panel.add(new JScrollPane(tablaEstadistica), BorderLayout.CENTER);
		panel.validate();
		panel.repaint();
		
		return panel;
		
		
	}
	private JPanel MiInformacion (JPanel panel){
		panel.removeAll();
		panel.setLayout(new GridLayout(4,1,40,40));
		JLabel lbl1 = new JLabel("Nombre: ");
		lbl1.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		JLabel lbl2 = new JLabel("Correo: ");
		lbl2.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		JLabel lbl3 = new JLabel("Contraseña Actual: ");
		lbl3.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		JLabel lbl4 = new JLabel("Nueva Contraseña: ");
		lbl4.setHorizontalAlignment(((int) CENTER_ALIGNMENT));
		panel.add(lbl1);
		panel.add(lbl2);
		panel.add(lbl3);
		panel.add(lbl4);
		panel.validate();
		panel.repaint();
		return panel;
		
	}

}
