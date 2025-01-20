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

public class RendererFavoritos extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		JLabel lbl = new JLabel();
		lbl.setSize(200,80);
		lbl.setBackground(table.getBackground());
		lbl.setHorizontalAlignment(JLabel.CENTER);

		if(column == 0) {

			lbl.setFont(new Font("COURIER",Font.BOLD,10));
			lbl.setText(value.toString());
			lbl.setBackground( new Color(255, 0, 0, 60));
			lbl.setToolTipText(value.toString());

		}
		if(column ==1) {
			lbl.setFont(new Font("COURIER",Font.BOLD,10));
			lbl.setText(value.toString());
			lbl.setBackground( new Color(0, 255, 0, 60));
			lbl.setToolTipText(value.toString());

		}if (column == 3) {
			lbl.setFont(new Font("COURIER",Font.BOLD,10));
			lbl.setText(value.toString());
			lbl.setBackground( new Color(0, 0, 255, 60));
			lbl.setToolTipText(value.toString());
		}


		if (column == 2) {
			ImageIcon imagenSinEscalar = null;

			if (value != null) {
				imagenSinEscalar = new ImageIcon(String.format("%s", value.toString()));

				if (imagenSinEscalar.getImageLoadStatus() != MediaTracker.COMPLETE) {
					imagenSinEscalar = new ImageIcon(); // Imagen por defecto si la carga falla
				}
			} else {
				imagenSinEscalar = new ImageIcon(); 
			}

			if (imagenSinEscalar != null && imagenSinEscalar.getImage() != null) {
				int imgWidth = imagenSinEscalar.getIconWidth();
				int imgHeight = imagenSinEscalar.getIconHeight();

				int labelWidth = lbl.getWidth();
				int labelHeight = lbl.getHeight();
				
				//IAG: ChatGPT 
				double scaleFactor = Math.min((double) labelWidth / imgWidth, (double) labelHeight / imgHeight);

				int newWidth = (int) (imgWidth * scaleFactor);
				int newHeight = (int) (imgHeight * scaleFactor);
				//Hasta aquí

				Image imagenEscalada = imagenSinEscalar.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
				ImageIcon imagenEscaladaIcon = new ImageIcon(imagenEscalada);

				lbl.setIcon(imagenEscaladaIcon);
			} else {
				lbl.setIcon(new ImageIcon());  
			}
		}


		if (column == 4) {
			JProgressBar progres = new JProgressBar(0, 5);

			int progresoActual;
			if (value instanceof Integer) {
				progresoActual = (Integer) value;
			} else {
				progresoActual = 0; 
			}

			progres.setValue(progresoActual);
			progres.setString(String.valueOf(progresoActual));
			progres.setStringPainted(true);
			progres.setVisible(true);

			return progres;
		}

		if (isSelected) {
			lbl.setBackground(Color.WHITE);
			lbl.setForeground(Color.black);

		}

		lbl.setOpaque(true);
		table.getTableHeader().setFont(new Font("Ariel",Font.BOLD,15));
		table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);
		return lbl;
	}





}
