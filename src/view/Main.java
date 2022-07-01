package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	private MainScreen mainScreen;

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Online Health Management System");
		setBounds(0, 0, 1800, 900);
		getContentPane().setLayout(new BorderLayout());
		
		mainScreen = new MainScreen();
		mainScreen.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(mainScreen);
		pack();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
