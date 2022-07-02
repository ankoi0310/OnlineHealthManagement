package view;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import dto.user.UserRegister;
import handle.AppBaseResult;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Registration extends JFrame {
	private static final long serialVersionUID = 1L;
	private UserController userController;
	
	private JPanel contentPane;
	private JLabel lblTitle, lblFullname, lblPhone, lblPassword, lblAddress;
	private JTextField tfFullname, tfAge, tfPhone, tfAddress;
	private JPasswordField pfPassword;
	private JButton btnCancel, btnRegister;
	private JRadioButton rdbtnMale, rdbtnFemale;
	
	private Login currentLoginFrame;
	private JLabel lblId;
	private JTextField tfId;
	
	public void setCurrentLoginFrame(Login login) {
		currentLoginFrame = login;
	}

	public Registration() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegister.doClick();
				}
			}
		});
		setTitle("Đăng ký");
		setBounds(100, 100, 565, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Đăng ký");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitle.setBounds(0, 18, 549, 33);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		lblFullname = new JLabel("Họ tên: ");
		lblFullname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFullname.setBounds(75, 69, 71, 26);
		contentPane.add(lblFullname);
		
		tfFullname = new JTextField();
		tfFullname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfFullname.setColumns(10);
		tfFullname.setBounds(145, 69, 196, 26);
		contentPane.add(tfFullname);
		
		JLabel lblAge = new JLabel("Tuổi: ");
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(382, 69, 46, 26);
		contentPane.add(lblAge);
		
		tfAge = new JTextField();
		tfAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfAge.setBounds(426, 69, 78, 26);
		contentPane.add(tfAge);
		tfAge.setColumns(10);
		
		lblId = new JLabel("CMND/CCCD: ");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(31, 113, 115, 26);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfId.setColumns(10);
		tfId.setBounds(145, 113, 196, 26);
		contentPane.add(tfId);
		
		rdbtnMale = new JRadioButton("Nam");
		rdbtnMale.setSelected(true);
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnMale.setBounds(367, 113, 59, 26);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Nữ");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFemale.setBounds(438, 113, 46, 26);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnMale);
		bg.add(rdbtnFemale);
		
		lblPhone = new JLabel("Số điện thoại: ");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(10, 157, 136, 26);
		contentPane.add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPhone.setBounds(145, 157, 196, 26);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		lblPassword = new JLabel("Mật khẩu: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 201, 136, 26);
		contentPane.add(lblPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfPassword.setBounds(145, 201, 196, 26);
		contentPane.add(pfPassword);
		
		lblAddress = new JLabel("Địa chỉ: ");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(10, 245, 136, 26);
		contentPane.add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfAddress.setColumns(10);
		tfAddress.setBounds(145, 245, 359, 26);
		contentPane.add(tfAddress);
		
		btnRegister = new JButton("Đăng ký");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userController = new UserController();
					
					String fullname = tfFullname.getText();
					String id = tfId.getText();
					String phone = tfPhone.getText();
					int age = Integer.parseUnsignedInt(tfAge.getText());
					boolean male = rdbtnMale.isSelected();
					String password = String.valueOf(pfPassword.getPassword());
					String address = tfAddress.getText();
					
					UserRegister newUser = new UserRegister(id, fullname, age, male, phone, password, address);
					AppBaseResult result = userController.register(newUser);
					
					if (result.isSuccess()) {
						JOptionPane.showMessageDialog(getParent(), result.getMessage(), "Đăng ký thành công", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						Login login = new Login();
						login.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(getParent(), result.getMessage(), "Đăng ký không thành công", JOptionPane.WARNING_MESSAGE);
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getParent(), "Tuổi không hợp lệ");
				}
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(0, 153, 255));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setBounds(327, 289, 115, 23);
		contentPane.add(btnRegister);
		
		btnCancel = new JButton("Huỷ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				currentLoginFrame.setVisible(true);
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(106, 289, 115, 23);
		contentPane.add(btnCancel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
