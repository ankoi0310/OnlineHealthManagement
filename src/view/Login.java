package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import handle.AppResult;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitle, lblPhone, lblPassword;
	private JTextField tfPhone;
	private JPasswordField pfPassword;
	private JButton btnRegister, btnLogin;
	
	private Registration registration;
	
	private UserController userController;
	
	private Login getCurrentFrame() {
		return this;
	}

	public Login() {
		
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
		
		lblPassword = new JLabel("Mật khẩu: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(60, 141, 136, 26);
		contentPane.add(lblPassword);
		
		tfPhone = new JTextField();
		tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPhone.setBounds(206, 89, 166, 26);
		tfPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfPassword.setBounds(206, 141, 166, 26);
		pfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		contentPane.add(pfPassword);
		
		btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userController = new UserController();
				
				String phone = tfPhone.getText();
				String password = String.valueOf(pfPassword.getPassword());
				AppResult<User> result = userController.checkLogin(phone, password);
				
				if (result.isSuccess()) {
					JOptionPane.showMessageDialog(getParent(), "Đăng nhập thành công!");
					dispose();
					Main main = new Main(result.getData());
					main.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(getParent(), result.getMessage());
				}
			}
		});
		btnLogin.setBackground(new Color(0, 204, 0));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(68, 197, 115, 23);
		contentPane.add(btnLogin);
		
		btnRegister = new JButton("Đăng ký");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				registration = new Registration();
				registration.setCurrentLoginFrame(getCurrentFrame());
				registration.setVisible(true);
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(0, 153, 255));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setBounds(251, 197, 115, 23);
		contentPane.add(btnRegister);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}