package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import revistaModa.clases.Articulo;
import revistaModa.clases.RevistaModa;
import revistaModa.clases.Usuario;

public class VentanaArticulo extends JFrame {
    private VentanaArticulo vActual;
    private JLabel lblTituloArt, lblAutorFecha;
    private JPanel pCentro, pNorte, pSur, pEste, pOeste, pSubNorte, pSlider, pSubSlider, pTitulo, pSubTitulo, pAutorFecha;
    private JEditorPane editorPane;
    private JSlider slValoracion;
    private JButton btnValorar, btnLike;
    private Set<String> setUsuariosLike;
    TreeMap<String,Integer> mapaUsuariosVal;
    
    //PRUEBA (NO SE DONDE PONER - MEJORAR)
    private List<Usuario> lUsu;

    private boolean likeFijo = false;  // Variable para mantener si la imagen se queda fija en "megusta2.png"

    public VentanaArticulo(Articulo art) {
        vActual = this;
        setBounds(100, 100, 1000, 600);
        setTitle("UDVogue");
        
        setUsuariosLike = art.getSetUsuariosLike();
        mapaUsuariosVal = art.getMapaUsuariosVal();
        lUsu = RevistaModa.getlUsuarios();
        

        pCentro = new JPanel();
        pNorte = new JPanel();
        pSur = new JPanel();
        pEste = new JPanel();
        pOeste = new JPanel();

        pCentro.setLayout(new BorderLayout());

        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pSur, BorderLayout.SOUTH);
        getContentPane().add(pEste, BorderLayout.EAST);
        getContentPane().add(pOeste, BorderLayout.WEST);
        getContentPane().add(pCentro, BorderLayout.CENTER);

        // Configuración del panel norte con GridLayout (1,2)
        pNorte.setLayout(new GridLayout(1, 2));
        pNorte.setBorder(new EmptyBorder(20, 20, 20, 20));  // Margen de 20 píxeles en todos los lados

        // Subpanel para el título y el autor (BoxLayout vertical)
        pSubNorte = new JPanel();
        pSubNorte.setLayout(new BoxLayout(pSubNorte, BoxLayout.Y_AXIS));
        lblTituloArt = new JLabel("<html><h1>" + art.getTitulo() + "</h1><</html>");

        // Imagen inicial del botón
        ImageIcon iconoLike = new ImageIcon("RevistaModa/img/megusta1.png");
        Image imagenLike = iconoLike.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnLike = new JButton(new ImageIcon(imagenLike));

        // Tamaño y configuración del botón
        btnLike.setPreferredSize(new Dimension(30, 30));
        btnLike.setContentAreaFilled(false);
        btnLike.setBorderPainted(false);
        btnLike.setFocusPainted(false);
        btnLike.setAlignmentX(RIGHT_ALIGNMENT);
        btnLike.setAlignmentY(BOTTOM_ALIGNMENT);

        // Cambiar la imagen temporalmente cuando el ratón pasa por encima
        btnLike.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cambiar a la nueva imagen sólo si no se ha hecho el doble clic
                if (!likeFijo) {
                    ImageIcon iconoLikeHover = new ImageIcon("RevistaModa/img/megusta2.png");
                    Image imageLikeHover = iconoLikeHover.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    btnLike.setIcon(new ImageIcon(imageLikeHover));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restaurar la imagen original sólo si no se ha hecho el doble clic
                if (!likeFijo) {
                    btnLike.setIcon(new ImageIcon(imagenLike));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            	// Si se hace doble clic, cambiar la imagen permanentemente
            	if(!setUsuariosLike.contains(lUsu.get(1).getUsername())) {
            		ImageIcon iconoLikeFixed = new ImageIcon("RevistaModa/img/megusta2.png");
            		Image imageLikeFixed = iconoLikeFixed.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            		btnLike.setIcon(new ImageIcon(imageLikeFixed));
            		likeFijo = true;  // Marcar que la imagen ahora es fija
            	}else {
                    JOptionPane.showMessageDialog(null, "No puedes volver a darle like");
            	}


            }
            
        });
        

        pTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pTitulo.add(lblTituloArt);
        pTitulo.add(btnLike);

        pSubNorte.add(pTitulo);

        // Crear un nuevo panel pAutorFecha con FlowLayout(FlowLayout.LEFT) para alinear a la izquierda
        pAutorFecha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblAutorFecha = new JLabel("<html><i>" + art.getAutor() + ", " + art.getFechaPublicacion() + "</i></html>");
        pAutorFecha.add(lblAutorFecha);

        // Añadir el panel pAutorFecha a pSubNorte
        pSubNorte.add(pAutorFecha);

        // Agregar el subpanel (con título y autor) a la primera columna del GridLayout
        pNorte.add(pSubNorte);  // Esto es la posición (1,1)

        // Panel para el slider y el botón de valoración
        pSlider = new JPanel(new BorderLayout());
        pSubSlider = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        slValoracion = new JSlider(0, 5, 0);
        slValoracion.setMajorTickSpacing(1);
        slValoracion.setPaintTicks(true);
        slValoracion.setPaintLabels(true);

        btnValorar = new JButton("Valorar");
        btnValorar.setPreferredSize(new Dimension(80, 25));
        
        
        
        btnValorar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!mapaUsuariosVal.containsKey(lUsu.get(1).getUsername())) {
					mapaUsuariosVal.put(lUsu.get(1).getUsername(), slValoracion.getValue());
				}
				System.out.println(mapaUsuariosVal);
				
				
				
			}
		});
        

        // Estilo del JSlider
        slValoracion.setForeground(Color.DARK_GRAY);
        slValoracion.setFont(new Font("Arial", Font.BOLD, 12));
        slValoracion.setPreferredSize(new Dimension(180, 40));

        pSubSlider.add(slValoracion);
        pSubSlider.add(btnValorar);
        pSlider.add(pSubSlider);

        // Agregar el panel del slider a la segunda columna del GridLayout
        pNorte.add(pSlider);

        // EditorPane para mostrar contenido HTML
        editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try {
            File archivoHtml = new File(art.getRutaArchivoArt());
            editorPane.setPage(archivoHtml.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            editorPane.setContentType("text/html");
            editorPane.setText("<html><body><h1>Error cargando el archivo HTML</h1></body></html>");
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);

        pCentro.add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}

