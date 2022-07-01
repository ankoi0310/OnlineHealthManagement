package view;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ListView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblRequest;
	private JScrollPane scrollPane;

	public ListView() {
		String[] columnNames = { "ID", "Title", "Description", "Status", "User" };
		
		tblRequest = new JTable(new Object[][] {}, columnNames);
		tblRequest.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Description", "Status", "User"
			}
		));
		tblRequest.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		scrollPane = new JScrollPane(tblRequest);
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		JScrollBar bar = scrollPane.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(20, 0));
		
		add(scrollPane);
	}
}
