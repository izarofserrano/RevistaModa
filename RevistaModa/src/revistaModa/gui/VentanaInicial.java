package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;

import revistaModa.clases.Articulo;
import revistaModa.clases.RevistaModa;
import revistaModa.clases.Usuario;


public class VentanaInicial extends JFrame {
	private JButton btnInicio, btnModa, btnBelleza, btnLogIn;
	private JLabel lblTitulo, lblImagenPortada, lblContacto, lblUbi, lblLupa, lblNuevoComponente;
	private JPanel pCentro, pNorte, pSur, pEste, pOeste,pBelleza,pModa;
	private JTextField txtBuscador;
	private List<Usuario> lUsu;
	private Set<String> setUsuariosLike = new HashSet<>(); //almacena usuarios que han dado like 
	private int totalLikes = 0; //contador de likes totales
	private JFrame vActual;
	
	
	public VentanaInicial(boolean mostrarComponenteExtra, Usuario u) {
		vActual = this;
		lUsu = RevistaModa.getlUsuarios();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setTitle("VOGUE");
		
		pCentro = new JPanel();
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		
		btnInicio = new JButton("INICIO");
		btnModa = new JButton("MODA");
		btnBelleza = new JButton("BELLEZA");
		btnLogIn = new JButton("Log In");
		
		Dimension buttonSize = new Dimension(100, 40);
		btnInicio.setPreferredSize(buttonSize);
		btnModa.setPreferredSize(buttonSize);
		btnBelleza.setPreferredSize(buttonSize);
		btnLogIn.setPreferredSize(buttonSize);
		
		
		txtBuscador = new JTextField(15);
		txtBuscador.setPreferredSize(new Dimension(150, 30));
		txtBuscador.setText("Buscar");
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(pCentro);
		scrollPane.setPreferredSize(new Dimension(1000, 500));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		pNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNorte.add(btnInicio);
		pNorte.add(btnModa);
		pNorte.add(btnBelleza);
		pNorte.add(btnLogIn);
		
		
		
		pSur.setLayout(new GridLayout(2,1));
		JLabel lblContacto = new JLabel("Contacto: a.ezkurdia@opendeusto.es");
		JLabel lblUbi = new JLabel("Ubicación: Universidad de Deusto, Bilbao, Bizkaia, España");
		pSur.add(lblContacto);
		pSur.add(lblUbi);
		
		
		JPanel pBuscador = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		try {
			ImageIcon iconoLupa = new ImageIcon("RevistaModa/img/lupa.jpeg");
			Image imgLupa = iconoLupa.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			JLabel lblLupa = new JLabel(new ImageIcon(imgLupa));
			pBuscador.add(lblLupa);
			
		} catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen" + e.getMessage());
		}	
		
		pBuscador.add(txtBuscador);
		pNorte.add(pBuscador);
		
		if (mostrarComponenteExtra) {
            lblNuevoComponente = new JLabel(u.getUsername());
            lblNuevoComponente.setText("<html><u>" + u.getUsername() + "</u></html>");
            lblNuevoComponente.setFont(new Font("Arial", Font.ITALIC, 12));
            pNorte.add(lblNuevoComponente);
            
            pNorte.remove(btnLogIn);
            
            lblNuevoComponente.addMouseListener(new MouseAdapter() {
			
				
				@Override
				public void mouseEntered(MouseEvent e) {
					//Cambia el cursor a una mano al pasar por encima
					lblNuevoComponente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					new VentanaPerfil(u);
				}
			});
        }
		pNorte.revalidate();
		pNorte.repaint();
		
		try {
			ImageIcon iconoPortada = new ImageIcon("RevistaModa/img/portada.jpeg");
			Image imgPortada = iconoPortada.getImage().getScaledInstance(getWidth(), 600, Image.SCALE_SMOOTH);
			lblImagenPortada = new JLabel(new ImageIcon(imgPortada));
			pCentro.add(lblImagenPortada);
		
		} catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen" + e.getMessage());
		}
		
		
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Usuario> lUsuarios = RevistaModa.getlUsuarios();
				new VentanaUsuario(lUsuarios);
				dispose();
				
			}
		
		});
	
		btnBelleza.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCentro.removeAll();
				
				ReloadBelleza(pCentro);
				
				pCentro.revalidate();
				pCentro.repaint();
				
			}
		});
		
		btnModa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCentro.removeAll();
			
				reloadModa(pCentro);
				
				
				pCentro.revalidate();
				pCentro.repaint();
			}
		});
		
		
		
		txtBuscador.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(txtBuscador.getText().equals("Buscar")) {
					txtBuscador.setText("");
				}
			}
		});
		
		setVisible(true);
	}
	
	
	private JPanel cargarArticulos(JPanel pCentro, String tipo) {
	    pCentro.setLayout(new GridLayout(2, 4, 10, 10));

	    for (int i = 1; i <= 8; i++) {
	    	
	    	final int index = i;
	    	
	        JPanel panel = new JPanel();
	        panel.setLayout(new OverlayLayout(panel));
	        panel.setPreferredSize(new Dimension(250, 450));
	        JLabel lbl = null;

	        try {
	        	lbl = new JLabel();
				lbl.setSize(panel.getWidth(),panel.getHeight());
				ImageIcon icono = new ImageIcon("RevistaModa/img/" + tipo + i + ".jpeg");
				Image imagen = icono.getImage().getScaledInstance(250 ,350, Image.SCALE_SMOOTH);
				lbl = new JLabel(new ImageIcon(imagen));
				lbl.setAlignmentX(LEFT_ALIGNMENT);
				lbl.setAlignmentY(TOP_ALIGNMENT);
				ImageIcon imagenConDimensiones = new ImageIcon(icono.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
				lbl.setIcon(imagenConDimensiones);
	        } catch (Exception u) {
	            System.out.println("No se ha podido cargar la imagen" + u.getMessage());
	        }

	        panel.add(lbl, BorderLayout.CENTER);
			pCentro.add(panel);
			
			
			JPanel panelCorazon = new JPanel();
			panelCorazon.setLayout(null);
			panelCorazon.setOpaque(false);
			panelCorazon.setPreferredSize(new Dimension(250, 350));
			
			
			ImageIcon iconoGris = new ImageIcon("RevistaModa/img/megusta1.png");
			Image imgGris = iconoGris.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			JButton btn = new JButton(new ImageIcon(imgGris));
			
			btn.setMinimumSize(new Dimension(30,30));
			btn.setContentAreaFilled(false);
			btn.setBorderPainted(false);    
			btn.setFocusPainted(false); 
			btn.setBounds(215, 320, 30, 30);

			JPanel panelContador = new JPanel();
			panelContador.setBackground(new Color (255, 255, 255, 180));
			panelContador.setBounds(10, 320, 60, 30);
			panelContador.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			JLabel contador = new JLabel("0");
			contador.setForeground(Color.BLACK);
			panelContador.add(contador);
			
			panelCorazon.add(btn);
			panelCorazon.add(panelContador);
			
			btn.addMouseListener(new MouseAdapter() {
				boolean like = false;

				@Override
				public void mouseEntered(MouseEvent e) {
					if (!like) {
						ImageIcon iconoLikeHover = new ImageIcon("RevistaModa/img/megusta2.png");
						Image imagenLikeHover = iconoGris.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
						btn.setIcon(new ImageIcon(imagenLikeHover));
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (!like) {
						btn.setIcon(new ImageIcon(imgGris));
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					String username = lUsu.get(2).getUsername();
					
					if(!setUsuariosLike.contains(username)) {
						setUsuariosLike.add(username);
						totalLikes++;
						contador.setText(String.valueOf(totalLikes));
						ImageIcon iconoLikeFixed = new ImageIcon("RevistaModa/img/megusta2.png");
						Image imagenLikeFixed = iconoLikeFixed.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
						btn.setIcon(new ImageIcon(imagenLikeFixed));
					} else { 
						setUsuariosLike.remove(username);
						totalLikes--;
						contador.setText(String.valueOf(totalLikes));
						btn.setIcon(new ImageIcon(imgGris));
					}
					
				}
			
			});
			
			panel.add(panelCorazon);
			panel.add(lbl);
			
			if (lbl != null) {
	            lbl.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    new VentanaArticulo(RevistaModa.getlArticulos().get(2));
	                }
	            });
	        }
		}
		
		return pCentro;
	}

	private JPanel reloadModa(JPanel pCentro) {
	    return cargarArticulos(pCentro, "ropa");
	}

	private JPanel ReloadBelleza(JPanel pCentro) {
	    return cargarArticulos(pCentro, "belleza"); 
	}
}