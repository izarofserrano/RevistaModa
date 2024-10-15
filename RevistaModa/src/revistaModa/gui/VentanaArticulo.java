package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import revistaModa.clases.Articulo;

public class VentanaArticulo extends JFrame {
	private VentanaArticulo vActual;
	private JLabel lblTituloArt, lblAutorFecha;
	private JPanel pCentro, pNorte, pSur, pEste, pOeste, pCentroTexto, pCentroImagen;
	private JEditorPane editorPane;
	private JSlider slValoracion;
	private JButton btnValorar;

	public VentanaArticulo(Articulo art) {

		vActual = this;
		setBounds(100, 100, 1000, 600);
		setTitle("UDVogue");

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

		
		pNorte.setLayout(new BoxLayout(pNorte, BoxLayout.Y_AXIS));

		pNorte.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen de 20 píxeles en todos los lados

		JPanel pSlider = new JPanel(new FlowLayout(FlowLayout.LEFT));
		slValoracion = new JSlider(0, 5, 0);
		slValoracion.setMajorTickSpacing(1);
		slValoracion.setPaintTicks(true);
		slValoracion.setPaintLabels(true);
		
		btnValorar = new JButton("Valorar");
		
		// Estilo del JSlider
        slValoracion.setBackground(Color.LIGHT_GRAY);  // Cambiar el fondo
        slValoracion.setForeground(Color.DARK_GRAY);   // Cambiar el color de las marcas y etiquetas
        slValoracion.setFont(new Font("Arial", Font.BOLD, 14));  // Cambiar la fuente de las etiquetas
        slValoracion.setPreferredSize(new Dimension(300, 60));  // Tamaño preferido
		
		pSlider.add(slValoracion);
		pSlider.add(btnValorar);
		
		
		pNorte.add(pSlider);
		
		lblTituloArt = new JLabel("<html><h1>" + art.getTitulo() + "</h1></html>");
		lblAutorFecha = new JLabel("<html><i>" + art.getAutor() + ", " + art.getFechaPublicacion() + "</i></html>");

		
		pNorte.add(lblTituloArt); 
		pNorte.add(lblAutorFecha); 

		
		editorPane = new JEditorPane();
		editorPane.setEditable(false); 

		try {
			System.out.println(art.getRutaArchivoArt());
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
