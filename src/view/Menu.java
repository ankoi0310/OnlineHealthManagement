package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAdd, btnEdit, btnLogout;


	/**
	 * Create the panel.
	 */
	public Menu() {
		setLayout(new GridLayout(0, 3, 30, 0));
		
		btnAdd = new JButton("Add");
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		add(btnEdit);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnLogout);
	}
}
