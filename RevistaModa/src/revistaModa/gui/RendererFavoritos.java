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
				ImageIcon imagenSinEscalar = new ImageIcon(String.format("%s", value.toString()));
				
				int AncLabel = lbl.getWidth();
				int AltLabel = lbl.getHeight();
				
				Image imagen = imagenSinEscalar.getImage().getScaledInstance(AncLabel, AltLabel, Image.SCALE_SMOOTH);
				ImageIcon imagenEscalada = new ImageIcon(imagen);
				
				lbl.setIcon(imagenEscalada);
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
