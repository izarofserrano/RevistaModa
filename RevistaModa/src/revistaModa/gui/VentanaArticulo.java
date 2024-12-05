package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.OverlayLayout;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import revistaModa.bd.GestorBD;
import revistaModa.clases.Articulo;
import revistaModa.clases.FotoArt;
import revistaModa.clases.RevistaModa;
import revistaModa.clases.Usuario;

public class VentanaArticulo extends JFrame {
    private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private VentanaArticulo vActual;
    private JLabel lblTituloArt, lblAutorFecha;
    private JPanel pCentro, pNorte, pSur, pSubNorte, pSlider, pSubSlider, pTitulo, pAutorFecha;
    private JEditorPane editorPane;
    private JSlider slValoracion;
    private JButton btnValorar, btnLike;
    private Set<String> setUsuariosLike;
    TreeMap<String,Integer> mapaUsuariosVal;
    
    private List<Usuario> lUsu;
    private boolean likeFijo = false,likeFijoFt = false;  
    private JPanel panelDerecho;

    public VentanaArticulo(Articulo art) {
        vActual = this;
        setBounds(100, 100, 1000, 600);
        setTitle("UDVogue");
        setLocationRelativeTo(null);
       
        setUsuariosLike = GestorBD.cargarLikes(art.getIdArt());
        mapaUsuariosVal = GestorBD.cargarValoraciones(art.getIdArt());
        lUsu = RevistaModa.getlUsuarios();
    
        // Configuración de los paneles
        pCentro = new JPanel();
        pNorte = new JPanel();
        pSur = new JPanel();


        // Configuración del Layout
        pCentro.setLayout(new BorderLayout()); 

        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pSur, BorderLayout.SOUTH);
        getContentPane().add(pCentro, BorderLayout.CENTER); 
        
        pCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));        
        pNorte.setLayout(new GridLayout(1, 2));
        pNorte.setBorder(new EmptyBorder(20, 20, 20, 20)); 

        // Panel para título y like
        pSubNorte = new JPanel();
        pSubNorte.setLayout(new BoxLayout(pSubNorte, BoxLayout.Y_AXIS));
        
        lblTituloArt = new JLabel("<html><h1>" + art.getTitulo() + "</h1></html>"); //IAG (Herramienta: ChatGPT) Sin cambios

        ImageIcon iconoLike = new ImageIcon("RevistaModa/img/megusta1.png");
        Image imagenLike = iconoLike.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnLike = new JButton(new ImageIcon(imagenLike));

        //IAG: ChatGPT
        btnLike.setPreferredSize(new Dimension(30, 30));
        btnLike.setContentAreaFilled(false);
        btnLike.setBorderPainted(false);
        btnLike.setFocusPainted(false);
        btnLike.setAlignmentX(RIGHT_ALIGNMENT);
        btnLike.setAlignmentY(BOTTOM_ALIGNMENT);
        //SIN CAMBIOS
        
        // Evento de ratón para el botón de "Like"
        btnLike.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!likeFijo) {
                    ImageIcon iconoLikeHover = new ImageIcon("RevistaModa/img/megusta2.png");
                    Image imageLikeHover = iconoLikeHover.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    btnLike.setIcon(new ImageIcon(imageLikeHover));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!likeFijo) {
                    btnLike.setIcon(new ImageIcon(imagenLike));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                String username = lUsu.get(2).getUsername();  

                if (!setUsuariosLike.contains(username)) {
                    setUsuariosLike.add(username);
                    ImageIcon iconoLikeFixed = new ImageIcon("RevistaModa/img/megusta2.png");
                    Image imageLikeFixed = iconoLikeFixed.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    btnLike.setIcon(new ImageIcon(imageLikeFixed));
                    likeFijo = true;  
                } else {
                    setUsuariosLike.remove(username);
                    btnLike.setIcon(new ImageIcon(imagenLike));  
                    likeFijo = false;  
                }
            }
        });

        pTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pTitulo.add(lblTituloArt);
        pTitulo.add(btnLike);
        pSubNorte.add(pTitulo);

        // IAG: (Herramienta: ChatGPT)
        pAutorFecha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblAutorFecha = new JLabel("<html><i>" + art.getAutor() + ", " + art.getFechaPublicacion() + "</i></html>");
        pAutorFecha.add(lblAutorFecha);
        pSubNorte.add(pAutorFecha);

        // Añadir los subpaneles a pNorte
        pNorte.add(pSubNorte);

        // Panel para el slider y valoración
        pSlider = new JPanel(new BorderLayout());
        pSubSlider = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        slValoracion = new JSlider(0, 5, 0);
        slValoracion.setMajorTickSpacing(1);
        slValoracion.setPaintTicks(true);
        slValoracion.setPaintLabels(true);

        btnValorar = new JButton("Valorar");
        btnValorar.setPreferredSize(new Dimension(80, 25));

        btnValorar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (!mapaUsuariosVal.containsKey(lUsu.get(1).getUsername())) {
        			mapaUsuariosVal.put(lUsu.get(1).getUsername(), slValoracion.getValue());
        		} else {
        			mapaUsuariosVal.put(lUsu.get(1).getUsername(), slValoracion.getValue());
        			JOptionPane.showMessageDialog(null, "Tu valoración se ha actualizado");
        		}
        		System.out.println(mapaUsuariosVal);
        	}
        });

        pSubSlider.add(slValoracion);
        pSubSlider.add(btnValorar);
        pSlider.add(pSubSlider);

        // Añadir el panel del slider a pNorte
        pNorte.add(pSlider);

        // EditorPane para mostrar contenido HTML
        editorPane = new JEditorPane();
        editorPane.setEditable(false);

        //IAG: (Herramienta: ChatGPT)
        try {
        	File archivoHtml = new File(art.getRutaArchivoArt());
        	editorPane.setPage(archivoHtml.toURI().toURL());
        } catch (IOException e) {
        	e.printStackTrace();
        	editorPane.setContentType("text/html");
        	editorPane.setText("<html><body><h1>Error cargando el archivo HTML</h1></body></html>");
        }
        //Sin cambios

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        JScrollPane scrollIzquierdo = new JScrollPane(editorPane);
        panelIzquierdo.add(scrollIzquierdo, BorderLayout.CENTER);


        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS)); // Usar BoxLayout para organizar verticalmente
        //panelDerecho.setPreferredSize(new Dimension(250,0));

        
        for(FotoArt fArt : art.getlFotos()) {
            JPanel panelFoto = new JPanel();
            panelFoto.setLayout(new OverlayLayout(panelFoto)); // Cambiado a OverlayLayout para superponer componentes
            panelFoto.setPreferredSize(new Dimension(250, 300));
            
            JLabel foto = new JLabel();
            try {
                ImageIcon imgIco = new ImageIcon(fArt.getRutaFoto());
                Image img = imgIco.getImage().getScaledInstance(panelFoto.getPreferredSize().width, panelFoto.getPreferredSize().height, Image.SCALE_SMOOTH);
                foto.setIcon(new ImageIcon(img));
                panelFoto.add(foto); // Añadir la imagen al panel
            } catch (Exception e) {
                System.err.println("La foto del artículo no se ha podido cargar");
                foto.setText("Error foto");
                foto.setPreferredSize(new Dimension(250,450));
                panelFoto.add(foto);
            }

            foto.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    foto.setToolTipText(null);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    foto.setToolTipText(fArt.getDescripción());
                }
            });

            // Crear el botón de "Me gusta" sobre la imagen
            //IAG: (Herramienta: ChatGPT) 
            //No funciona
            ImageIcon iconoGris = new ImageIcon("RevistaModa/img/megusta1.png");
            Image imgGris = iconoGris.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            JButton btnLikeFt = new JButton(new ImageIcon(imgGris));

            btnLikeFt.setContentAreaFilled(false);
            btnLikeFt.setBorderPainted(false);
            btnLikeFt.setFocusPainted(false);
            btnLikeFt.setPreferredSize(new Dimension(25, 25));
            
            // Añadir el botón de "Me gusta" sobre la imagen en la parte inferior
            
            JPanel panelCorazon = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            panelCorazon.setOpaque(false);
            panelCorazon.setPreferredSize(new Dimension(250, 25));
            panelCorazon.add(btnLikeFt);
            panelFoto.add(Box.createVerticalGlue());
            panelFoto.add(panelCorazon);
            
            //Algunos cambios
            btnLikeFt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!likeFijoFt) {
                        ImageIcon iconoLikeHover2 = new ImageIcon("RevistaModa/img/megusta2.png");
                        Image imageLikeHover2 = iconoLikeHover2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                        btnLikeFt.setIcon(new ImageIcon(imageLikeHover2));
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!likeFijoFt) {
                        btnLikeFt.setIcon(new ImageIcon(imgGris));
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    String username = lUsu.get(2).getUsername();
                    if (!setUsuariosLike.contains(username)) {
                        setUsuariosLike.add(username);
                        ImageIcon iconoLikeFixed2 = new ImageIcon("RevistaModa/img/megusta2.png");
                        Image imageLikeFixed2 = iconoLikeFixed2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                        btnLikeFt.setIcon(new ImageIcon(imageLikeFixed2));
                        likeFijoFt = true;
                    } else {
                        setUsuariosLike.remove(username);
                        btnLikeFt.setIcon(new ImageIcon(imgGris));
                        likeFijoFt = false;
                    }
                }
            });

            panelDerecho.add(panelFoto);
        }


        JScrollPane spDerecha = new JScrollPane(panelDerecho);
        spDerecha.setPreferredSize(new Dimension(250, 0));

        spDerecha.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //IAG (herramienta: ChatGPT)
        spDerecha.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //IAG (herramienta: ChatGPT)

        pCentro.add(panelIzquierdo,BorderLayout.CENTER);  
        pCentro.add(spDerecha,BorderLayout.EAST);     

        // Forzar actualización del panel
        panelDerecho.revalidate();
        panelDerecho.repaint();
        
        
        //Redimensionar para adaptarse bien al tamaño de pantalla
        addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				ajustarBien();

			}
        	
        });
        

        setVisible(true);
        
        //IAG: (Herramienta: CHATGPT)
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
            	//SIN CAMBIAR
                GestorBD.actualizarValoraciones(mapaUsuariosVal, setUsuariosLike, art.getIdArt());
                dispose();  
            }
        });
    }
    
    private void ajustarBien() {
    	int anchoV = getWidth();
    	int altoV = getHeight();
    	int tamanoTit = Math.max(20, anchoV / 50); //Hacemos /50 para que el tamaño sea proporcional (se hace más ancha y el tit se agranda). Se asegura que tamaño de texto no sea < 20. IAG (herramienta: ChatGPT)
    	int tamanoAutor = Math.max(14, anchoV / 70); //IAG (herramienta: ChatGPT)
    	
        lblTituloArt.setFont(new Font("SansSerif", Font.BOLD, tamanoTit));
        lblAutorFecha.setFont(new Font("SansSerif", Font.ITALIC, tamanoAutor));
        cambiarTexto(editorPane, Math.max(12, anchoV / 95));

        for (Component c : panelDerecho.getComponents()) { //IAG (herramienta: ChatGPT)
        	if (c instanceof JLabel) {
        		JLabel label = (JLabel) c; //accedemos a los métodos específicos de JLabel tratando label como JLabel
        		ImageIcon original = (ImageIcon) label.getIcon(); //icono actual
        		Image escalada = original.getImage().getScaledInstance(anchoV / 5, altoV / 5, Image.SCALE_SMOOTH); //Escalar imagen actual. IAG (herramienta: ChatGPT)
        		label.setIcon(new ImageIcon (escalada));
        	}
        }
    
        /*Image imagenLike = new ImageIcon("RevistaModa/img/megusta1.png").getImage().getScaledInstance(anchoV / 30, altoV / 30, Image.SCALE_SMOOTH); //IAG (herramienta: ChatGPT)
        btnLike.setIcon(new ImageIcon(imagenLike));*/
    }
    
    private void cambiarTexto(JEditorPane pane, int tam) {
        String contenidoHTML = "<html><head><style>body { font-size: " + tam + "px; }</style></head><body>" + pane.getText() + "</body></html>"; //IAG (herramienta: ChatGPT)
        pane.setText(contenidoHTML);
    }
    
  
}
