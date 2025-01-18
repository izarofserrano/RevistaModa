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


		if(column==2) {
			ImageIcon imagenSinEscalar = null;

			if (value != null) {
				// Verifica si la ruta de la imagen es válida
				imagenSinEscalar = new ImageIcon(String.format("%s", value.toString()));

				// Comprueba si la imagen se ha cargado correctamente
				if (imagenSinEscalar.getImageLoadStatus() != MediaTracker.COMPLETE) {
					imagenSinEscalar = new ImageIcon(); // Imagen por defecto si la carga falla
				}
			} else {
				imagenSinEscalar = new ImageIcon(); // Imagen por defecto si no hay valor
			}

			// Asegúrate de que la imagen no sea nula
			if (imagenSinEscalar != null && imagenSinEscalar.getImage() != null) {
				int AncLabel = lbl.getWidth();
				int AltLabel = lbl.getHeight();

				// Escalar la imagen
				Image imagen = imagenSinEscalar.getImage().getScaledInstance(AncLabel, AltLabel, Image.SCALE_SMOOTH);
				ImageIcon imagenEscalada = new ImageIcon(imagen);
				lbl.setIcon(imagenEscalada);
			} else {
				// Si la imagen no se cargó correctamente, establecer un icono vacío o una imagen predeterminada
				lbl.setIcon(new ImageIcon());  // Establece una imagen por defecto si no se cargó
			}
		}


		if(column == 4) {
			JProgressBar progres = new JProgressBar(0,5);
			int progresoActual = Math.round((float) value);
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
