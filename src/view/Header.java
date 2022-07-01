package view;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Header extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Header() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblHeader = new JLabel("Online Health Management System");
		lblHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 36));
		add(lblHeader);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Menu menu = new Menu();
		menu.setPreferredSize(new Dimension(400, 40));
		menu.setBorder(new EmptyBorder(4, 4, 4, 4));
		panel.add(menu);
		menu.setLayout(new GridLayout(1, 0, 30, 0));
		
	}
}
