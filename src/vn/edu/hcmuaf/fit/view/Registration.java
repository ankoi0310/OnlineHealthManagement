package vn.edu.hcmuaf.fit.view;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.UserController;
import vn.edu.hcmuaf.fit.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.*;
import javax.swing.JRadioButton;

public class Registration extends JFrame implements ActionListener, KeyListener {
	private UserController controller;
	private User model;
	private JPanel contentPane;
	private JLabel lblTitle, lblFullname, lblId, lblAge, lblPhone, lblPassword, lblAddress;
	private JTextField tfFullname, tfId, tfAge, tfPhone, tfAddress;
	private JPasswordField pfPassword;
	private JButton btnCancel, btnRegister;
	private JRadioButton rdbtnMale, rdbtnFemale;

	public Registration(UserController controller, User model) {
		this.controller = controller;
		this.model = model;
	}

	public void createView() {
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

		lblAge = new JLabel("Tuổi: ");
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
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(0, 153, 255));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setBounds(327, 289, 115, 23);
		contentPane.add(btnRegister);

		btnCancel = new JButton("Huỷ");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(106, 289, 115, 23);
		contentPane.add(btnCancel);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(getParent(), message, "", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(getParent(), message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	public void close() {
		dispose();
		App.frames.remove(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source.equals(btnRegister)) {
			model.setId(tfId.getText());
			model.setFullname(tfFullname.getText());
			model.setAge(Integer.parseInt(tfAge.getText()));
			model.setPhone(tfPhone.getText());
			model.setAddress(tfAddress.getText());
			model.setPassword(pfPassword.getText());
			model.setMale(rdbtnMale.isSelected());

			controller.register(model);
		}

		if (source.equals(btnCancel)) {
			controller.unregister();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnRegister.doClick();
		}
	}
}
