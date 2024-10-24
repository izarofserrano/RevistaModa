package revistaModa.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
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
		lbl.setBackground(table.getBackground());
		lbl.setHorizontalAlignment(JLabel.CENTER);
		
		if(column == 0 || column == 1 || column == 3) {
			
			lbl.setFont(new Font("COURIER",Font.BOLD,10));
			lbl.setText(value.toString());
		}
		return lbl;
	}
	
	
	

}
