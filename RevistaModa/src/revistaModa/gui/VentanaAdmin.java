package revistaModa.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class VentanaAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaAdmin() {
		setBounds(100, 100, 1000, 600);
		setTitle("UDVogue_Admin");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		//IAG: ChatGPT
		JLabel lblTitulo = new JLabel("Crea un nuevo artículo");
		lblTitulo.setBounds(100, 50, 800, 40);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitulo.setForeground(Color.BLACK);
		add(lblTitulo);
		
		JTextArea textArea = new JTextArea();
        textArea.setBounds(100, 100, 800, 400); 
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true); // Ajusta las palabras al final de la línea
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Agrega borde para simular un área de edición

        
        add(textArea);
        
        
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(100, 520, 200, 40);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEnviar.setForeground(Color.BLACK);
        add(btnEnviar);
        
        
        btnEnviar.addActionListener(e->{
        	textArea.setText("");
        });
        
        setLayout(null);
	}
		    
	
	

}
