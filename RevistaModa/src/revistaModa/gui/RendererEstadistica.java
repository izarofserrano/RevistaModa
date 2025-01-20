package revistaModa.gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererEstadistica extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		JLabel lbl = new JLabel();
		lbl.setSize(200,80);
		lbl.setBackground(table.getBackground());
		lbl.setHorizontalAlignment(JLabel.CENTER);

		try {
			if (value != null) {
				if (column == 0 || column == 1 || column == 3) { // Texto
					lbl.setFont(new Font("COURIER", Font.BOLD, 10));
					lbl.setText(value.toString());
					lbl.setBackground(new Color(255, 0, 0, 60));
					lbl.setToolTipText(value.toString());
				} else if (column == 2) { // Imagen
					ImageIcon imagenSinEscalar = null;

					imagenSinEscalar = new ImageIcon(String.format("%s", value.toString()));

					// Si la carga de la imagen falla, usar una imagen por defecto
					if (imagenSinEscalar.getImageLoadStatus() != MediaTracker.COMPLETE) {
						imagenSinEscalar = new ImageIcon(); 
					}

					if (imagenSinEscalar != null && imagenSinEscalar.getImage() != null) {
						int imgWidth = imagenSinEscalar.getIconWidth();
						int imgHeight = imagenSinEscalar.getIconHeight();

						int labelWidth = lbl.getWidth();
						int labelHeight = lbl.getHeight();

						// Calcular el factor de escala
						double scaleFactor = Math.min((double) labelWidth / imgWidth, (double) labelHeight / imgHeight);

						int newWidth = (int) (imgWidth * scaleFactor);
						int newHeight = (int) (imgHeight * scaleFactor);

						// Escalar la imagen
						Image imagenEscalada = imagenSinEscalar.getImage()
								.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
						ImageIcon imagenEscaladaIcon = new ImageIcon(imagenEscalada);

						lbl.setIcon(imagenEscaladaIcon);
					}
				} else if (column == 4) { // Barra de progreso
					JProgressBar progres = new JProgressBar(0, 50); // Escalar rango a 0-50 para simular decimales
					float valorDecimal = (float) value; 
					int progresoActual = Math.round(valorDecimal * 10); 
					progres.setValue(progresoActual);
					progres.setString(String.format("%.1f", valorDecimal)); 
					progres.setStringPainted(true);
					return progres;
				}
			} else {
				lbl.setText("N/A");
			}
		} catch (Exception e) {
			lbl.setText("Error");
		}

		if (isSelected) {
			lbl.setBackground(Color.WHITE);
			lbl.setForeground(Color.BLACK);
		}

		lbl.setOpaque(true);
		table.getTableHeader().setFont(new Font("Ariel", Font.BOLD, 15));
		table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
		table.getTableHeader().setBackground(Color.WHITE);
		return lbl;
	}

}
