package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
	public static HashMap<String, List<Articulo>> mapa = new HashMap<String, List<Articulo>>();

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
		// hiloBotones = new HiloBotones(pNorteBottom);
		hiloPortada.start();
		// hiloBotones.start();

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
	
	private JPanel cargarArticulos2(JPanel pCentro, String tipo) {
	    System.out.println("TAMAÑO:"+RevistaModa.getlArticulos().size());
	    int filaConResto = RevistaModa.getlArticulos().size() % 4;
	    int filas = 0;
	    if (filaConResto != 0) {
	        filas = RevistaModa.getlArticulos().size() / 4 + 1;
	    } else {
	        filas = RevistaModa.getlArticulos().size() / 4;
	    }

	    int columnas = 4;
	    pCentro.setLayout(new GridLayout(filas, columnas, 10, 10));

	    for (int i = 0; i < RevistaModa.getlArticulos().size(); i++) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new OverlayLayout(panel));
	        panel.setPreferredSize(new Dimension(250, 450));
	        JLabel lbl = null;

	        try {
	            lbl = new JLabel();
	            lbl.setSize(panel.getWidth(), panel.getHeight());
	            // ImageIcon icono = new ImageIcon("RevistaModa/img/" + tipo + i + ".jpeg");
	            ImageIcon icono = new ImageIcon(RevistaModa.getlArticulos().get(i).getlFotos().get(0).getRutaFoto());
	            icono.setDescription(RevistaModa.getlArticulos().get(i).getlFotos().get(0).getRutaFoto());
	            Image imagen = icono.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
	            lbl = new JLabel(new ImageIcon(imagen));
	            lbl.setAlignmentX(LEFT_ALIGNMENT);
	            lbl.setAlignmentY(TOP_ALIGNMENT);
	            ImageIcon imagenConDimensiones = new ImageIcon(icono.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
	            lbl.setIcon(imagenConDimensiones);
	        } catch (Exception u) {
	            System.out.println("No se ha podido cargar la imagen" + u.getMessage());
	        }

	        // Asociar la imagen con el artículo correspondiente
	        final int index = i; // Crear una variable final para acceder dentro del MouseListener

	       
	        
	        lbl.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                try {
	                    // Verificar si usuarioActual es null
	                    if (usuarioActual == null) {
	                        throw new Exception("Usuario no encontrado.");
	                    }
	                    
	                    // Obtener el artículo correspondiente
	                    Articulo articulo = RevistaModa.getlArticulos().get(index);
	                    
	                    // Abrir la ventana correspondiente para ese artículo
	                    new VentanaArticulo(articulo, usuarioActual);
	                } catch (Exception ex) {
	                    // Mostrar un JOptionPane si ocurre un error
	                	int option = JOptionPane.showOptionDialog(null, "Error: no está registrado", "Error de usuario", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {"Aceptar", "Registro"}, "Aceptar");

	                    if (option == JOptionPane.YES_OPTION) {
	                        System.out.println("Aceptar");
	                    } else if (option == JOptionPane.NO_OPTION) {
	                        abrirVentanaUsuario();
	                    	
	                    }
	                }
	            }
	        });


	        panel.add(lbl, BorderLayout.CENTER);
	        pCentro.add(panel);

	        JPanel panelCorazon = new JPanel();
	        panelCorazon.setLayout(null);
	        panelCorazon.setOpaque(false);
	        panelCorazon.setPreferredSize(new Dimension(250, 350));

	        ImageIcon iconoGris = new ImageIcon("RevistaModa/img/megusta1.png");
	        Image imgGris = iconoGris.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	        JButton btn = new JButton(new ImageIcon(imgGris));

	        btn.setMinimumSize(new Dimension(30, 30));
	        btn.setContentAreaFilled(false);
	        btn.setBorderPainted(false);
	        btn.setFocusPainted(false);
	        btn.setBounds(215, 320, 30, 30);

	        JPanel panelContador = new JPanel();
	        panelContador.setBackground(new Color(255, 255, 255, 180));
	        panelContador.setBounds(10, 320, 60, 30);
	        panelContador.setLayout(new FlowLayout(FlowLayout.CENTER));

	        JLabel contador = new JLabel("0");
	        contador.setForeground(Color.BLACK);
	        panelContador.add(contador);

	        panelCorazon.add(btn);
	        panelCorazon.add(panelContador);

	        btn.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
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

	            
	        });

	        panel.add(panelCorazon);
	        panel.add(lbl);

	        KeyListener clickEnEsc = new KeyListener() {
	            @Override
	            public void keyTyped(KeyEvent e) {}

	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                    volverInicio(pCentro);
	                    pCentro.revalidate();
	                    pCentro.repaint();
	                }
	            }

	            @Override
	            public void keyReleased(KeyEvent e) {}
	        };

	        pCentro.setFocusable(true);
	        pCentro.addKeyListener(clickEnEsc);
	        pCentro.requestFocusInWindow();
	    }

	    return pCentro;
	}
	
	private void abrirVentanaUsuario() {
        List<Usuario> lUsuarios = RevistaModa.getlUsuarios();
        new VentanaUsuario(lUsuarios);
        dispose();
    }



/*
	private JPanel cargarArticulos(JPanel pCentro, String tipo) {
		pCentro.setLayout(new GridLayout(2, 4, 10, 10));

		for (int i = 1; i <= 8; i++) {


			JPanel panel = new JPanel();
			panel.setLayout(new OverlayLayout(panel));
			panel.setPreferredSize(new Dimension(250, 450));
			JLabel lbl = null;

			try {
				lbl = new JLabel();
				lbl.setSize(panel.getWidth(),panel.getHeight());
				ImageIcon icono = new ImageIcon("RevistaModa/img/" + tipo + i + ".jpeg");
				icono.setDescription("RevistaModa/img/" + tipo + i + ".jpeg");
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
						new VentanaArticulo(RevistaModa.getlArticulos().get(2));
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
*/






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
		return cargarArticulos2(pCentro, "ropa");
	}

	private JPanel ReloadBelleza(JPanel pCentro) {
		return cargarArticulos2(pCentro, "belleza");
	}

	private class HiloPortada extends Thread {
	    private volatile boolean running = true; // Control del hilo
	    private JLabel[] imagenesAlLado = new JLabel[3]; // Tres imágenes visibles a la vez
	    private List<String> rutasImagenes; // Lista de todas las imágenes disponibles
	    private int indiceActual = 0; // Índice del inicio de las imágenes visibles

	    public HiloPortada() {
	        rutasImagenes = cargarRutasImagenes(); // Obtener todas las rutas de imágenes

	        SwingUtilities.invokeLater(() -> {
	            pCentro.setLayout(new GridLayout(1, 3, 10, 10)); // Configura un GridLayout
	            for (int i = 0; i < imagenesAlLado.length; i++) {
	                imagenesAlLado[i] = new JLabel();
	                imagenesAlLado[i].setHorizontalAlignment(JLabel.CENTER);
	                pCentro.add(imagenesAlLado[i]);
	            }
	            cargarImagenesIniciales();
	        });
	    }

	    private List<String> cargarRutasImagenes() {
	        List<String> rutas = new ArrayList<>();
	        int i = 1;
	        while (true) {
	            String ruta = "RevistaModa/img/portada" + i + ".jpeg";
	            if (new File(ruta).exists()) {
	                rutas.add(ruta);
	                i++;
	            } else {
	                break; // Detener al no encontrar más imágenes
	            }
	        }
	        return rutas;
	    }

	    private void cargarImagenesIniciales() {
	        for (int i = 0; i < imagenesAlLado.length; i++) {
	            int indice = (indiceActual + i) % rutasImagenes.size();
	            actualizarImagen(imagenesAlLado[i], rutasImagenes.get(indice));
	        }
	    }

	    private void actualizarImagen(JLabel label, String rutaImagen) {
	        try {
	            ImageIcon icono = new ImageIcon(rutaImagen);
	            Image img = icono.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
	            label.setIcon(new ImageIcon(img));
	        } catch (Exception e) {
	            label.setText("Error al cargar imagen");
	        }
	    }

	    @Override
	    public void run() {
	        while (running) {
	            try {
	                indiceActual = (indiceActual + 1) % rutasImagenes.size(); // Avanza el índice

	                SwingUtilities.invokeLater(() -> {
	                    for (int i = 0; i < imagenesAlLado.length; i++) {
	                        int indice = (indiceActual + i) % rutasImagenes.size();
	                        actualizarImagen(imagenesAlLado[i], rutasImagenes.get(indice));
	                    }
	                });

	                Thread.sleep(3000); // Intervalo entre cambios
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void detener() {
	        running = false; // Detiene el bucle del hilo
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

	    // Filtramos los artículos que no tienen "like" del usuario
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
	    generarCombisRecursivas(articulosSinLike, combis, new ArrayList<>(), 0);

	    if (combis.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "No hay combinaciones posibles de artículos.");
	        return;
	    }

	    // Seleccionamos la primera combinación posible
	    List<Articulo> combinacionSeleccionada = combis.get(0);

	    // Crear la ventana para las recomendaciones
	    JFrame ventanaRecs = new JFrame("Recomendaciones");
	    ventanaRecs.setBounds(200, 200, 800, 600);
	    ventanaRecs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JPanel pCentroRecs = new JPanel();
	    pCentroRecs.setLayout(new GridLayout(1, 3, 10, 10)); // 1 fila, 3 columnas con un espacio de 10px entre ellos

	    // Recorremos los 3 artículos de la combinación seleccionada
	    for (Articulo articulo : combinacionSeleccionada) {
	        // Crear un panel para cada artículo
	        JPanel panelArticulo = new JPanel();
	        panelArticulo.setLayout(new BorderLayout());

	        // Cargar la imagen de la portada
	        JLabel lblPortada = new JLabel();
	        try {
	            ImageIcon iconoPortada = new ImageIcon(articulo.getlFotos().get(0).getRutaFoto());
	            Image portadaEscalada = iconoPortada.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
	            lblPortada.setIcon(new ImageIcon(portadaEscalada));
	        } catch (Exception e) {
	            lblPortada.setText("No imagen disponible");
	        }

	        // Etiqueta para el título
	        JLabel lblTitulo = new JLabel(articulo.getTitulo(), SwingConstants.CENTER);
	        lblTitulo.setForeground(Color.BLACK);

	        // Agregar el listener para abrir el artículo cuando se haga clic en la portada
	        lblPortada.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (usuarioActual != null) {
	                    new VentanaArticulo(articulo, usuarioActual);
	                } else {
	                	int option = JOptionPane.showOptionDialog(null, "Error: no está registrado", "Error de usuario", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {"Aceptar", "Registro"}, "Aceptar");

	                    if (option == JOptionPane.YES_OPTION) {
	                        System.out.println("Aceptar");
	                    } else if (option == JOptionPane.NO_OPTION) {
	                        abrirVentanaUsuario();
	                    	
	                    }
	                    
	                }
	            }
	        });

	        // Añadir la portada y el título al panel
	        panelArticulo.add(lblPortada, BorderLayout.CENTER);
	        panelArticulo.add(lblTitulo, BorderLayout.SOUTH);

	        // Añadir el panel de cada artículo al panel principal
	        pCentroRecs.add(panelArticulo);
	    }

	    ventanaRecs.add(pCentroRecs);
	    ventanaRecs.setVisible(true);
	}







	public JButton getBtnLogIn() {
		return btnLogIn;
	}


}