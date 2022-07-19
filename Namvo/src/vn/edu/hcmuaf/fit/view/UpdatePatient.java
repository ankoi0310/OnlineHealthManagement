package vn.edu.hcmuaf.fit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.PatientController;
import vn.edu.hcmuaf.fit.model.Patient;

public class UpdatePatient extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfFullname, tfCMND;
	private JTextField tfAge;
	private JButton btnCancel_1,btnSubmit;
	private PatientController controller;
	private JRadioButton rdbtnMale;
	private List<Patient> patients = new ArrayList<Patient>();
	Patient patient;
	int row ;
	
	public UpdatePatient(PatientController control, Patient p, int row) {
		// TODO Auto-generated constructor stub
		this.controller = control;
		patient = p;
		this.row = row;
	}
	public void createView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 370);
		contentPane = new JPanel();
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
		
		tfCMND = new JTextField(patient.getId());
		tfCMND.setBounds(224, 94, 188, 27);
		contentPane.add(tfCMND);
		tfCMND.setColumns(10);
		tfCMND.addActionListener(this);
		//
		JLabel lblFullname = new JLabel("Họ tên: ");
		lblFullname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFullname.setBounds(57, 134, 110, 27);
		contentPane.add(lblFullname);
		
		tfFullname = new JTextField(patient.getFullname());
		tfFullname.setBounds(224, 134, 188, 27);
		contentPane.add(tfFullname);
		tfFullname.setColumns(10);
		tfFullname.addActionListener(this);
		
		JLabel lblAge = new JLabel("Tuổi: ");
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAge.setBounds(57, 172, 110, 27);
		contentPane.add(lblAge);
		
		tfAge = new JTextField(patient.getAge()+"");
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
		if(!patient.isMale()) {
			rdbtnFemale.setSelected(true);
		}
		contentPane.add(rdbtnFemale);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFemale);
		bg.add(rdbtnMale);
		
		btnCancel_1 = new JButton("Huỷ");
		btnCancel_1.setForeground(Color.WHITE);
		btnCancel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel_1.setBackground(Color.RED);
		btnCancel_1.setBounds(80, 260, 115, 23);
		contentPane.add(btnCancel_1);
		btnCancel_1.addActionListener(this);
		
		btnSubmit = new JButton("Xác nhận");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBackground(new Color(0, 255, 51));
		btnSubmit.setBounds(275, 260, 115, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(this);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public void close() {
		dispose();
		App.frames.remove(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (btnSubmit.equals(source)) {
			String id = tfCMND.getText();
			String name = tfFullname.getText();
			int age = Integer.parseInt(tfAge.getText());
			
			boolean male = rdbtnMale.isSelected();
			
			patient = new Patient(id, name, age, male);
//			patients.add(patient);
			controller.updatePatient(patient, row);
			
		} else if (btnCancel_1.equals(source)) {
			for(Patient p: patients) {
				System.out.println(p);
			}
			dispose();
		}
	}
	
	public static void main(String[] args) {
//		UpdatePatient form = new UpdatePatient(null, null);
//		form.createView();
	}
}
