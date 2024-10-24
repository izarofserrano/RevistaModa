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
import java.io.File;
import java.io.IOException;
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
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import revistaModa.clases.Articulo;
import revistaModa.clases.FotoArt;
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
    
    private List<Usuario> lUsu;
    private boolean likeFijo = false;  

    public VentanaArticulo(Articulo art) {
        vActual = this;
        setBounds(100, 100, 1000, 600);
        setTitle("UDVogue");
        setLocationRelativeTo(null);
       
        setUsuariosLike = art.getSetUsuariosLike();
        mapaUsuariosVal = art.getMapaUsuariosVal();
        lUsu = RevistaModa.getlUsuarios();
    
        // Configuración de los paneles
        pCentro = new JPanel();
        pNorte = new JPanel();
        pSur = new JPanel();
        pEste = new JPanel();
        pOeste = new JPanel();

        // Configuración del Layout
        pCentro.setLayout(new BorderLayout()); // Cambiar a GridLayout con 1 fila y 2 columnas

        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pSur, BorderLayout.SOUTH);
        getContentPane().add(pCentro, BorderLayout.CENTER); // Añadir pCentro con GridLayout al centro

        pNorte.setLayout(new GridLayout(1, 2));
        pNorte.setBorder(new EmptyBorder(20, 20, 20, 20));  // Margen de 20 píxeles en todos los lados

        // Panel para título y like
        pSubNorte = new JPanel();
        pSubNorte.setLayout(new BoxLayout(pSubNorte, BoxLayout.Y_AXIS));
        lblTituloArt = new JLabel("<html><h1>" + art.getTitulo() + "</h1></html>");

        ImageIcon iconoLike = new ImageIcon("RevistaModa/img/megusta1.png");
        Image imagenLike = iconoLike.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnLike = new JButton(new ImageIcon(imagenLike));

        btnLike.setPreferredSize(new Dimension(30, 30));
        btnLike.setContentAreaFilled(false);
        btnLike.setBorderPainted(false);
        btnLike.setFocusPainted(false);
        btnLike.setAlignmentX(RIGHT_ALIGNMENT);
        btnLike.setAlignmentY(BOTTOM_ALIGNMENT);

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

        // Panel para autor y fecha
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

        try {
            File archivoHtml = new File(art.getRutaArchivoArt());
            editorPane.setPage(archivoHtml.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            editorPane.setContentType("text/html");
            editorPane.setText("<html><body><h1>Error cargando el archivo HTML</h1></body></html>");
        }

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        JScrollPane scrollIzquierdo = new JScrollPane(editorPane);
        panelIzquierdo.add(scrollIzquierdo, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS)); // Usar BoxLayout para organizar verticalmente
        panelDerecho.setPreferredSize(new Dimension(250,0));
        for (FotoArt f : art.getlFotos()) {
        	System.out.println(f.toString());
            ImageIcon icono = new ImageIcon(f.getRutaFoto());
            
            Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
            JLabel label = new JLabel(new ImageIcon(img));
            label.setHorizontalAlignment(JLabel.CENTER);
            panelDerecho.add(label); 
        }

        pCentro.add(panelIzquierdo,BorderLayout.CENTER);  
        pCentro.add(panelDerecho,BorderLayout.EAST);     

        // Forzar actualización del panel
        panelDerecho.revalidate();
        panelDerecho.repaint();
        
        

        setVisible(true);
    }
}
