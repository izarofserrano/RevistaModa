package revistaModa.gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

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
		                ImageIcon imagenSinEscalar = new ImageIcon(value.toString());
		                Image imagen = imagenSinEscalar.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
		                lbl.setIcon(new ImageIcon(imagen));
		            } else if (column == 4) { // Barra de progreso
		                JProgressBar progres = new JProgressBar(0, 5);
		                int progresoActual = Math.round((float) value);
		                progres.setValue(progresoActual);
		                progres.setString(String.valueOf(progresoActual));
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
