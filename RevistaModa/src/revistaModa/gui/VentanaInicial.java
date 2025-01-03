package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import revistaModa.clases.Articulo;
import revistaModa.clases.RevistaModa;
import revistaModa.clases.Usuario;


public class VentanaInicial extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnInicio, btnModa, btnBelleza, btnLogIn, btnAdmin, btnRecomendaciones;
	
	private JLabel   lblNuevoComponente,lblHeaderIco;
	private JPanel pCentro, pNorte, pSur, pEste, pOeste;
	private JTextField txtBuscador;
	private List<Usuario> lUsu;
	private Set<String> setUsuariosLike = new HashSet<>(); //almacena usuarios que han dado like 
	private int totalLikes = 0; //contador de likes totales
	private JFrame vActual;
	private HiloPortada hiloPortada;
	//private HiloBotones hiloBotones;
	private Usuario usuarioActual;
	private List<Articulo> articulos = new ArrayList<>();


	public VentanaInicial(boolean mostrarComponenteExtra, Usuario u) {
		vActual = this;
		lUsu = RevistaModa.getlUsuarios();
		this.usuarioActual = u;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setLocationRelativeTo(null);
		setTitle("UDVogue");

		ImageIcon imagen = new ImageIcon("RevistaModa/img/Logo_UD_Vogue_iconito.png");
		setIconImage(imagen.getImage());

		pCentro = new JPanel();
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();

		btnInicio = new JButton("INICIO");
		btnModa = new JButton("MODA");
		btnBelleza = new JButton("BELLEZA");
		btnLogIn = new JButton("Log In");
		btnAdmin = new JButton("Admin");
		btnRecomendaciones = new JButton ("Recomendaciones");

		Dimension buttonSize = new Dimension(100, 40);
		btnInicio.setPreferredSize(buttonSize);
		btnModa.setPreferredSize(buttonSize);
		btnBelleza.setPreferredSize(buttonSize);
		btnLogIn.setPreferredSize(buttonSize);
		btnAdmin.setPreferredSize(buttonSize);


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

		pNorte.setLayout(new BoxLayout(pNorte, BoxLayout.Y_AXIS));

		JPanel pNorteTop = new JPanel();
		pNorteTop.setLayout(new FlowLayout(FlowLayout.CENTER));

		lblHeaderIco = new JLabel();
		ImageIcon imgIco = new ImageIcon("RevistaModa/img/Logo_UD_Vogue.png");
		Image img = imgIco.getImage().getScaledInstance(imgIco.getIconWidth()/3, imgIco.getIconHeight()/3, Image.SCALE_SMOOTH);
		lblHeaderIco.setIcon(new ImageIcon(img));

		lblHeaderIco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				lblHeaderIco.setOpaque(false);
				lblHeaderIco.setBackground(vActual.getBackground());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblHeaderIco.setOpaque(true);
				lblHeaderIco.setBackground(new Color(237, 235, 231));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// Editar para que cuando se pulse se ponga la pantalla inicial
				pCentro.removeAll();

				volverInicio(pCentro);

				pCentro.revalidate();
				pCentro.repaint();

			}
		});


		pNorteTop.add(lblHeaderIco);	
		pNorte.add(pNorteTop);

		JPanel pNorteBottom = new JPanel();
		pNorteBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNorteBottom.add(btnInicio);
		pNorteBottom.add(btnModa);
		pNorteBottom.add(btnBelleza);
		pNorteBottom.add(btnLogIn);



		pSur.setLayout(new BorderLayout());
		
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(2, 1));
		JLabel lblContacto = new JLabel("Contacto: udVogue@deusto.es");
		JLabel lblUbi = new JLabel("Ubicación: Universidad de Deusto, Bilbao, Bizkaia, España");
		panelInfo.add(lblContacto);
		panelInfo.add(lblUbi);
		pSur.add(panelInfo, BorderLayout.WEST);
		
		JLabel etiquetaHora = new JLabel("", SwingConstants.RIGHT);
		etiquetaHora.setFont(new Font("Arial", Font.BOLD, 14));
		pSur.add(etiquetaHora, BorderLayout.EAST);

	        Thread hiloReloj = new Thread(() -> {
	            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	            while (true) {

	            	long tiempoActual = System.currentTimeMillis();
	            	Date fechaActual = new Date(tiempoActual);
	                String horaActual = formatoHora.format(fechaActual);
	                etiquetaHora.setText(horaActual); 

	                try {
	                    Thread.sleep(1000); 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	        hiloReloj.start();


		JPanel pBuscador = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		try {
			ImageIcon iconoLupa = new ImageIcon("RevistaModa/img/lupa.jpeg");
			Image imgLupa = iconoLupa.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			JLabel lblLupa = new JLabel(new ImageIcon(imgLupa));
			pBuscador.add(lblLupa);

		} catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen" + e.getMessage());
		}	

		if (u!=null && u.getUsername().equals("admin")) {
			pNorteBottom.add(btnAdmin);
		}
		pBuscador.add(txtBuscador);
		pNorteBottom.add(pBuscador);
		pNorte.add(pNorteBottom);
		

		if (mostrarComponenteExtra) {
			lblNuevoComponente = new JLabel(u.getUsername());
			lblNuevoComponente.setText("<html><u>" + u.getUsername() + "</u></html>");
			lblNuevoComponente.setFont(new Font("Arial", Font.ITALIC, 12));
			pNorteBottom.add(lblNuevoComponente);

			pNorte.remove(btnLogIn);

			lblNuevoComponente.addMouseListener(new MouseAdapter() {


				
				@Override
				public void mouseEntered(MouseEvent e) {//IAG:ChatGPT
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

		hiloPortada = new HiloPortada();
	//	hiloBotones = new HiloBotones(pNorteBottom);
		hiloPortada.start();
	//	hiloBotones.start();
		
		/* try {
			ImageIcon iconoPortada = new ImageIcon("RevistaModa/img/portada.jpeg");
			Image imgPortada = iconoPortada.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			lblImagenPortada = new JLabel(new ImageIcon(imgPortada));
			pCentro.add(lblImagenPortada);

		} catch (Exception e) {
			System.out.println("No se ha podido cargar la imagen" + e.getMessage());
		} */


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
				hiloPortada.detener();
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
				hiloPortada.detener();
				pCentro.removeAll();

				reloadModa(pCentro);


				pCentro.revalidate();
				pCentro.repaint();
			}
		});

		btnInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				volverInicio(pCentro);
			}
		});
		
		btnAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Bienvenido al panel de administración");
				
				new VentanaAdmin();
			}
		});	
		
		btnRecomendaciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarRecomendaciones();
				
			}
			
		});
		
		pNorteBottom.add(btnRecomendaciones);



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

	private JPanel volverInicio ( JPanel pCentro) {
		pCentro.removeAll();
		
		hiloPortada= new HiloPortada();
		hiloPortada.start();
		
		pCentro.validate();
		pCentro.repaint();
		return pCentro;

	}


	private JPanel cargarArticulos(JPanel pCentro, String tipo) {
		pCentro.setLayout(new GridLayout(2, 4, 10, 10));

		for (int i = 1; i <= 8; i++) {
			Articulo articuloActual = articulos.get(i);
			
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
			  

			    @Override
			    public void mouseClicked(MouseEvent e) { //IAG:ChatGPT
			        String username;
			        
			        if (usuarioActual != null) {
			        	username = usuarioActual.getUsername();
			        } else {
			        	username = null;
			        }
			        
			        if (username != null) {
			        	if (!setUsuariosLike.contains(username)) {
				            setUsuariosLike.add(username);
				            totalLikes++;
				            contador.setText(String.valueOf(totalLikes));
				            ImageIcon iconoLikeFixed = new ImageIcon("RevistaModa/img/megusta2.png");
				            Image imagenLikeFixed = iconoLikeFixed.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				            btn.setIcon(new ImageIcon(imagenLikeFixed));
				        
				            likeQueParpadea(btn);
				            
				            if (!usuarioActual.getFavoritos().contains(articuloActual)) {
				            	usuarioActual.getFavoritos().add(articuloActual);
				            }
				            
				        } else {
				            setUsuariosLike.remove(username);
				            totalLikes--;
				            contador.setText(String.valueOf(totalLikes));
				            btn.setIcon(new ImageIcon(imgGris));
				        }
				        
				    
				    
			        } else {
			        	int option = JOptionPane.showOptionDialog(null, "Error: no está registrado", "Error de usuario", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {"Aceptar", "Registro"}, "Aceptar");
				        	
				        if (option == JOptionPane.YES_OPTION) {
				        	System.out.println("Aceptar");
				        } else if (option == JOptionPane.NO_OPTION) {
				        	abrirVentanaUsuario();
				        		
				        }
			        }
			        	
			        
			       }

				private void abrirVentanaUsuario() {
					List<Usuario> lUsuarios = RevistaModa.getlUsuarios();
					new VentanaUsuario(lUsuarios);
					dispose();
					
				}
			    	

			        
			}); 


			panel.add(panelCorazon);
			panel.add(lbl);

			if (lbl != null) {
			    lbl.addMouseListener(new MouseAdapter() {
			        @Override
			        public void mouseClicked(MouseEvent e) {
			            List<Articulo> listaArticulos = RevistaModa.getlArticulos();
			            if (listaArticulos != null && listaArticulos.size() > 2) {
			                new VentanaArticulo(listaArticulos.get(2));
			            } else {
			                System.err.println("La lista de artículos está vacía o no tiene suficientes elementos.");
			                JOptionPane.showMessageDialog(null, 
			                    "No hay suficientes artículos para mostrar.", 
			                    "Error", 
			                    JOptionPane.WARNING_MESSAGE);
			            }
			        }
			    });
			}
			
			
			KeyListener clickEnEsc = new KeyListener() { //va un poco lento

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						volverInicio(pCentro);
						pCentro.revalidate();
						pCentro.repaint();
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					
				}
				
			};
			
			pCentro.setFocusable(true);
			pCentro.addKeyListener(clickEnEsc);
			pCentro.requestFocusInWindow(); 
		}

		return pCentro;
		
	}

	
	
	

	
	
	private void likeQueParpadea(JButton btn) {
		String[] colores = {
				"RevistaModa/img/megustaRosa.png",
		        "RevistaModa/img/megustaNaranja.png",
		        "RevistaModa/img/megustaVerde.png",
		        "RevistaModa/img/megustaAzul.png",
		        "RevistaModa/img/megustaMorado.png"
		    };
		
		
		Thread hiloLike = new Thread(() -> {
			try {
				for (int i = 0; i<colores.length; i++) {
					int colorIndex = i % colores.length;
					ImageIcon iconoColor = new ImageIcon(colores[colorIndex]);
					Image imgColor = iconoColor.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
					btn.setIcon(new ImageIcon(imgColor));
					Thread.sleep(300);
				}

				ImageIcon iconoOrig = new ImageIcon("RevistaModa/img/megusta2.png");
				Image imgOrig = iconoOrig.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				btn.setIcon(new ImageIcon(imgOrig));
				
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		
		hiloLike.start();
	}
	
	

	private JPanel reloadModa(JPanel pCentro) {
		return cargarArticulos(pCentro, "ropa");
	}

	private JPanel ReloadBelleza(JPanel pCentro) {
		return cargarArticulos(pCentro, "belleza"); 
	}
	
	private class HiloPortada extends Thread{
		//USO DE CHAT PARA MEJORAR CODIGO

		private volatile boolean running = true; 
		private JLabel[] imagenesAlLado = new JLabel[3];
		private int[] indices = {1, 2, 3};
	
		public HiloPortada() {
			SwingUtilities.invokeLater(() -> {
				pCentro.setLayout(new GridLayout(1, 3, 10, 10));
				for (int i = 0; i < imagenesAlLado.length; i++) {
					imagenesAlLado[i] = new JLabel();
					imagenesAlLado[i].setHorizontalAlignment(JLabel.CENTER);
					pCentro.add(imagenesAlLado[i]);
				}
			});
		}
		    @Override
		    public void run() { 

		        try {
		            while (running) {

		                SwingUtilities.invokeLater(() -> actualizamosPortada());
		              
		                Thread.sleep(2000); 

		                
		            }
		            
		        } catch (InterruptedException e) {
		           
		        	System.out.println("Hilo interrumpido correctamente.");
		       
		        } finally {
		           
		            System.out.println("Hilo terminado.");
		       
		        }
		    }
		    
		    private void actualizamosPortada() {
		    	 for (int i = 0; i < imagenesAlLado.length; i++) {
		             actualizamosImagen(imagenesAlLado[i], indices[i]);
		             indices[i] = (indices[i] % 10) + 1; // Ciclar índices entre 1 y 10
		         }
		         pCentro.revalidate();
		         pCentro.repaint();
		     }
		    

		    private void actualizamosImagen(JLabel label, int indice) {
		    	try {
		    		String ruta = "RevistaModa/img/Portada" + indice + ".jpeg";
		    		ImageIcon icono = new ImageIcon(ruta);
		    		
		    		int anchoDelPanel;
                    if (pCentro.getWidth()>0) {
                    	anchoDelPanel = pCentro.getWidth() / 3;
                    } else {
                    	anchoDelPanel = 850 / 3; // por si acaso 
                    }
                    
                    int anchoOrig = icono.getIconWidth();
                    int alturaOrig = icono.getIconHeight();
                    int alturaProp = (int) ((double) alturaOrig / anchoOrig * anchoDelPanel);
                    
                    Image img = icono.getImage().getScaledInstance(anchoDelPanel, alturaProp, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(img));
		    	}
		    	 catch (Exception e) {
		    		 System.out.println("Error al actualizar imagen en portada:" + e.getMessage());
		    	 }
		    }
		    
		    public void detener() {
		    	running = false;
		    	this.interrupt();
		    }
		    
		
	}
	
	private Set<Articulo> articulosSinLike = new HashSet<>();
	private void generarCombisRecursivas(List<Articulo> articulosSinLike, List<List<Articulo>> combinaciones, List<Articulo> combiActual, int inicio) {
		if(combiActual.size() == 3) {
			combinaciones.add(new ArrayList<>(combiActual));
			return;
		}
		
		for (int i = inicio; i<articulosSinLike.size(); i++) {
			combiActual.add(articulosSinLike.get(i));
			generarCombisRecursivas(articulosSinLike, combinaciones, combiActual, i+1);
			combiActual.remove(combiActual.size()-1);
		}
	}
	
	private void mostrarRecomendaciones() {
		List<Articulo> articulosSinLike = new ArrayList<>();
		
		for (Articulo articulo : RevistaModa.getlArticulos()) {
			if (!setUsuariosLike.contains(articulo.getIdArt())) {
				articulosSinLike.add(articulo);
			}
		}
		
		if (articulosSinLike.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No se han encontrado artículos para recomendar.");
			return;
		}
		
		List<List<Articulo>> combis = new ArrayList<>();
		generarCombisRecursivas(articulosSinLike, combis, new ArrayList<>(),0);
		
		if (combis.isEmpty()) {
			  JOptionPane.showMessageDialog(this, "No hay combinaciones posibles de artículos.");
		      return;
		}
		
		JFrame ventanaRecs = new JFrame("Recomendaciones");
		ventanaRecs.setBounds(200,200,800,600);
		ventanaRecs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel pCentroRecs = new JPanel();
		
		pCentroRecs.setLayout(new GridLayout(combis.size(), 1));
		
		for (List<Articulo> combinacion : combis) {
			JPanel panelCombi = new JPanel();
			panelCombi.setLayout(new FlowLayout());
			
			for(Articulo articulo : combinacion) {
				JLabel lblArticulo = new JLabel(articulo.getTitulo());
				panelCombi.add(lblArticulo);
			}
			pCentroRecs.add(panelCombi);
		}
		
		ventanaRecs.add(new JScrollPane(pCentroRecs));
		ventanaRecs.setVisible(true);
	}
	
	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	
}