package vn.edu.hcmuaf.fit.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.admin.HomeController;
import vn.edu.hcmuaf.fit.model.Request;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateRequestStatus extends JFrame implements ActionListener {
	private final HomeController controller;
	private final Request model;
	private JLabel lblHeader;

	private JPanel contentPane;
	private JComboBox<String> cbStatus;
	private JButton btnCancel, btnSubmit;

	public UpdateRequestStatus(HomeController controller, Request model) {
		this.controller = controller;
		this.model = model;
	}

	public void createView() {
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblHeader = new JLabel("Cập nhật trạng thái yêu cầu");
		lblHeader.setBounds(5, 5, 424, 27);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblHeader);

		cbStatus = new JComboBox<>();
		cbStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {"Chờ xử lý", "Đang xử lý", "Đang yêu cầu xe cấp cứu", "Xe cấp cứu đang di chuyển", "Yêu cầu hoàn thành"}));
		cbStatus.setBounds(83, 53, 268, 32);
		contentPane.add(cbStatus);

		btnCancel = new JButton("Huỷ");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(67, 110, 115, 23);
		btnCancel.addActionListener(this);
		contentPane.add(btnCancel);

		btnSubmit = new JButton("Xác nhận");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBackground(new Color(0, 255, 51));
		btnSubmit.setBounds(250, 110, 115, 23);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void getStatus() {
		cbStatus.setSelectedItem(model.getStatus());
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	public void close() {
		dispose();
		App.frames.remove(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (btnSubmit.equals(source)) {
			int status = cbStatus.getSelectedIndex();
			controller.updateRequestStatus(model.getId(), status);
		} else if (btnCancel.equals(source)) {
			close();
		}
	}
}
