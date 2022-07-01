package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;

import constant.AppConstant;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitle, lblUsername, lblPassword;
	private JTextField tfUsername;
	private JPasswordField pfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		Border padding = BorderFactory.createEmptyBorder(10, 0, 10, 20);
		
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Vui loÌng ðãng nhâòp ðêÒ tiêìp tuòc");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitle.setBounds(0, 21, 434, 47);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		lblUsername = new JLabel("Tên ðãng nhâòp: ");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(60, 89, 136, 26);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Mâòt khâÒu: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(60, 141, 136, 26);
		contentPane.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsername.setBounds(206, 89, 166, 26);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfPassword.setBounds(206, 141, 166, 26);
		contentPane.add(pfPassword);
		
		JButton btnLogin = new JButton("Ðãng nhâòp");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText();
				String password = String.valueOf(pfPassword.getPassword());
				
				if (AppConstant.users.get(username) == null ) {
					JOptionPane.showMessageDialog(getParent(), "TaÌi khoaÒn không tôÌn taòi!");
				} else if (!AppConstant.users.get(username).equals(password)) {
					JOptionPane.showMessageDialog(getParent(), "Sai tên ðãng nhâòp hoãòc mâòt khâÒu!");
				} else {
					JOptionPane option = new JOptionPane();
					option.showMessageDialog(getParent(), "Ðãng nhâòp thaÌnh công!");
					dispose();
					Main main = new Main();
					main.setVisible(true);
				}
			}
		});
		btnLogin.setBackground(new Color(0, 204, 0));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(68, 197, 115, 23);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Ðãng kyì");
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(0, 153, 255));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setBounds(251, 197, 115, 23);
		contentPane.add(btnRegister);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
