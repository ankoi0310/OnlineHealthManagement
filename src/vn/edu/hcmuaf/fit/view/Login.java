package vn.edu.hcmuaf.fit.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.UserController;
import vn.edu.hcmuaf.fit.dto.UserLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JPasswordField;

public class Login extends JFrame implements ActionListener, KeyListener {
	private UserController controller;
	private UserLogin model;
	private JPanel contentPane;
	private JLabel lblTitle, lblPhone, lblPassword;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JButton btnRegister, btnLogin;

	public Login(UserController controller, UserLogin model) {
		this.controller = controller;
        this.model = model;
	}

	public void createView() {
		setTitle("Đăng nhập");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitle = new JLabel("Vui lòng đăng nhập để tiếp tục");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitle.setBounds(0, 21, 434, 47);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);

		lblPhone = new JLabel("Số điện thoại: ");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(60, 89, 136, 26);
		contentPane.add(lblPhone);

		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsername.setBounds(206, 89, 166, 26);
		tfUsername.setColumns(10);
		tfUsername.addKeyListener(this);
		contentPane.add(tfUsername);

		lblPassword = new JLabel("Mật khẩu: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(60, 141, 136, 26);
		contentPane.add(lblPassword);

		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfPassword.setBounds(206, 141, 166, 26);
		pfPassword.addKeyListener(this);
		contentPane.add(pfPassword);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(new Color(0, 204, 0));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(68, 197, 115, 23);
		btnLogin.addActionListener(this);
		contentPane.add(btnLogin);

		btnRegister = new JButton("Đăng ký");
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(0, 153, 255));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setBounds(251, 197, 115, 23);
		btnRegister.addActionListener(this);
		contentPane.add(btnRegister);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	public void close() {
		if (App.frames.size() == 1) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		dispose();
		App.frames.remove(this);
	}

	public void on() {
		setVisible(true);
	}

	public void off() {
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source.equals(btnLogin)) {
			String username = tfUsername.getText();
			String password = String.valueOf(pfPassword.getPassword());
			controller.login(this, username, password);
		}

		if (source.equals(btnRegister)) {
			controller.getRegister(this);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String username = tfUsername.getText();
			String password = String.valueOf(pfPassword.getPassword());
			controller.login(this, username, password);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}