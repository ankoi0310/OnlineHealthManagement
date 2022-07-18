package vn.edu.hcmuaf.fit.handle;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
	public WordWrapCellRenderer(Font font) {
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(font);
        setBorder(new CompoundBorder(new EmptyBorder(new Insets(1,4,1,4)), getBorder()));
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		setBackground(new Color(204, 255, 255));
		setText(value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        if (table.getRowHeight(row) < getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height + 5);
        }
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        // set the alignment of the cell
        return this;
	}
}
