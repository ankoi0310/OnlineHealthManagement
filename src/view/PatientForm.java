package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfFullname;
	private JTextField tfAge;
	
	public PatientForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeader = new JLabel("Thông tin bệnh nhân");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHeader.setBounds(0, 26, 470, 34);
		contentPane.add(lblHeader);
		
		JLabel lblFullname = new JLabel("Họ tên: ");
		lblFullname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFullname.setBounds(57, 94, 110, 27);
		contentPane.add(lblFullname);
		
		tfFullname = new JTextField();
		tfFullname.setBounds(224, 94, 188, 27);
		contentPane.add(tfFullname);
		tfFullname.setColumns(10);
		
		JLabel lblAge = new JLabel("Tuổi: ");
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(57, 132, 110, 27);
		contentPane.add(lblAge);
		
		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(224, 132, 188, 27);
		contentPane.add(tfAge);
		
		JLabel lblGender = new JLabel("Giới tính: ");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGender.setBounds(57, 170, 110, 27);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Nam");
		rdbtnMale.setSelected(true);
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnMale.setBounds(224, 170, 59, 26);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Nữ");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFemale.setBounds(301, 170, 46, 26);
		contentPane.add(rdbtnFemale);
		
		JButton btnCancel_1 = new JButton("Huỷ");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel_1.setForeground(Color.WHITE);
		btnCancel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel_1.setBackground(Color.RED);
		btnCancel_1.setBounds(80, 215, 115, 23);
		contentPane.add(btnCancel_1);
		
		JButton btnSubmit = new JButton("Xác nhận");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBackground(new Color(0, 255, 51));
		btnSubmit.setBounds(275, 215, 115, 23);
		contentPane.add(btnSubmit);
	}
}
