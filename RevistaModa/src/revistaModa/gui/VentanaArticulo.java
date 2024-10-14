package revistaModa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private JButton btnSend;

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

		lblTituloArt = new JLabel("<html><h1>" + art.getTitulo() + "</h1></html>");

		lblAutorFecha = new JLabel("<html><i>" + art.getAutor() + ", " + art.getFechaPublicacion() + "</i></html>");

		pNorte.setLayout(new BoxLayout(pNorte, BoxLayout.Y_AXIS));

		pNorte.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen de 20 píxeles en todos los lados

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
