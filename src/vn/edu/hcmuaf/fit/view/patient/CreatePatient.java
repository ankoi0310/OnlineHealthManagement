package vn.edu.hcmuaf.fit.view.patient;

import vn.edu.hcmuaf.fit.controller.PatientController;
import vn.edu.hcmuaf.fit.model.Patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreatePatient extends JFrame implements ActionListener {
	private final PatientController controller;
	private final Patient patient;
	private JTextField tfFullname, tfCMND;
	private JTextField tfAge;
	private JButton btnCancel_1, btnSubmit;
	private JRadioButton rdbtnMale;

	public CreatePatient(PatientController controller, Patient patient) {
		this.controller = controller;
		this.patient = patient;
	}

	public void createView() {
		setTitle("Thêm bệnh nhân");
		setBounds(100, 100, 486, 370);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeader = new JLabel("Thông tin bệnh nhân");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHeader.setBounds(0, 26, 470, 34);
		contentPane.add(lblHeader);

		JLabel lblCMND = new JLabel("CMND: ");
		lblCMND.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCMND.setBounds(57, 94, 110, 27);
		contentPane.add(lblCMND);

		tfCMND = new JTextField();
		tfCMND.setBounds(224, 94, 188, 27);
		contentPane.add(tfCMND);
		tfCMND.setColumns(10);
		tfCMND.addActionListener(this);

		JLabel lblFullname = new JLabel("Họ tên: ");
		lblFullname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFullname.setBounds(57, 134, 110, 27);
		contentPane.add(lblFullname);

		tfFullname = new JTextField();
		tfFullname.setBounds(224, 134, 188, 27);
		contentPane.add(tfFullname);
		tfFullname.setColumns(10);
		tfFullname.addActionListener(this);

		JLabel lblAge = new JLabel("Tuổi: ");
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(57, 172, 110, 27);
		contentPane.add(lblAge);

		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(224, 172, 188, 27);
		contentPane.add(tfAge);
		tfAge.addActionListener(this);

		JLabel lblGender = new JLabel("Giới tính: ");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGender.setBounds(57, 210, 110, 27);
		contentPane.add(lblGender);

		rdbtnMale = new JRadioButton("Nam");
		rdbtnMale.setSelected(true);
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnMale.setBounds(224, 210, 59, 26);
		contentPane.add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Nữ");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFemale.setBounds(301, 210, 46, 26);
		contentPane.add(rdbtnFemale);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFemale);
		bg.add(rdbtnMale);

		btnCancel_1 = new JButton("Huỷ");
		btnCancel_1.setForeground(Color.WHITE);
		btnCancel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel_1.setBackground(Color.RED);
		btnCancel_1.setBounds(80, 260, 115, 23);
		btnCancel_1.addActionListener(this);
		contentPane.add(btnCancel_1);

		btnSubmit = new JButton("Xác nhận");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBackground(new Color(0, 255, 51));
		btnSubmit.setBounds(275, 260, 115, 23);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void showMessage(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE, null);
	}

	public void showError(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.WARNING_MESSAGE, null);
	}


	public void close() {
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (btnSubmit.equals(source)) {
			try {
				patient.setAge(Integer.parseInt(tfAge.getText()));
			} catch (Exception ex) {
				showError("Tuổi không hợp lệ");
				return;
			}

			patient.setId(tfCMND.getText());
			patient.setFullname(tfFullname.getText());
			patient.setMale(rdbtnMale.isSelected());

			controller.createPatient(patient);
		} else if (btnCancel_1.equals(source)) {
			close();
		}
	}
}
