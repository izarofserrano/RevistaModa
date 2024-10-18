package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;

import revistaModa.clases.RevistaModa;


public class VentanaInicial extends JFrame {
	private JButton btnInicio, btnModa, btnBelleza, btnLogIn;
	private JLabel lblTitulo, lblImagenPortada, lblContacto, lblUbi, lblLupa;
	private JPanel pCentro, pNorte, pSur, pEste, pOeste,pBelleza,pModa;
	private JTextField txtBuscador;
	
	private JFrame vActual;
	
	
	public VentanaInicial() {
		vActual = this;
		
		setBounds(1000, 1000, 10000, 6000);
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
			ImageIcon iconoLupa = new ImageIcon("img/lupa.jpeg");
			Image imgLupa = iconoLupa.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			JLabel lblLupa = new JLabel(new ImageIcon(imgLupa));
			pBuscador.add(lblLupa);
			
		} catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen" + e.getMessage());
		}	
		
		pBuscador.add(txtBuscador);
		pNorte.add(pBuscador);
		
		
		try {
			ImageIcon iconoPortada = new ImageIcon("RevistaModa\\img\\portada.jpeg");
			Image imgPortada = iconoPortada.getImage().getScaledInstance(getWidth(), 600, Image.SCALE_SMOOTH);
			lblImagenPortada = new JLabel(new ImageIcon(imgPortada));
			pCentro.add(lblImagenPortada);
		
		} catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen" + e.getMessage());
		}
		
		
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VentanaUsuario();
				dispose();
				
			}
			
		});
		
		btnBelleza.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pCentro.removeAll();
				
			//Codigo de la de Belleza, lista de belleza...	
				
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
	
	private JPanel reloadModa(JPanel pCentro) {
		pCentro.setLayout(new GridLayout(2,4,10,10));

		for (int i = 1; i <= 8; i++) { 
			JPanel panel = new JPanel();
			panel.setLayout(new OverlayLayout(panel));
			panel.setPreferredSize(new Dimension(250, 450));
		
			JLabel lbl = null;

			try {
				ImageIcon icono = new ImageIcon("RevistaModa/img/ropa"+i+".jpeg");
				Image imagen = icono.getImage().getScaledInstance(250 ,350, Image.SCALE_SMOOTH);
				lbl = new JLabel(new ImageIcon(imagen));
				lbl.setAlignmentX(LEFT_ALIGNMENT);
				lbl.setAlignmentY(TOP_ALIGNMENT);
			
			} catch (Exception u) {
				System.out.println("No se ha podido cargar la imagen" + u.getMessage());
			}
			
			JPanel panelCorazon = new JPanel();
			panelCorazon.setLayout(null);
			panelCorazon.setOpaque(false);
			panelCorazon.setPreferredSize(new Dimension(250, 350));
			
			
			ImageIcon iconoGris = new ImageIcon("RevistaModa/img/megusta1.png");
			Image imgGris = iconoGris.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			JButton btn = new JButton(new ImageIcon(imgGris));
			
			
			btn.setContentAreaFilled(false);
			btn.setBorderPainted(false);    
			btn.setFocusPainted(false);  
			btn.setBounds(215, 320, 30, 30);

			JLabel contador = new JLabel("Fav");
			contador.setBounds(195, 320, 60, 30);
			
			panelCorazon.add(btn);
			panelCorazon.add(contador);
			
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
					if (e.getClickCount() == 1) {
						if(!like) { //si no like, añadimos
							like = true;
							contador.setText(String.valueOf(Integer.parseInt(contador.getText())+1));
							ImageIcon iconoLikeSelected = new ImageIcon("RevistaModa/img/megusta2.png");
							Image imagenLikeSelected = iconoLikeSelected.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
							btn.setIcon(new ImageIcon(imagenLikeSelected));
						}
						
					} else { //si like, quitamos
						like = false;
						contador.setText(String.valueOf(Integer.parseInt(contador.getText())-1));
						btn.setIcon(new ImageIcon(imgGris));
					}
				}
				
			}
				);
			
			
			panel.add(panelCorazon);
			panel.add(lbl);
			
			
			btn.addActionListener(new ActionListener() {
				int contador2 =0;
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Has clicado");
					contador2++;
					contador.setText(String.valueOf(contador2));
					
					
				
				}
			});
			pCentro.add(panel);
			
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
}
