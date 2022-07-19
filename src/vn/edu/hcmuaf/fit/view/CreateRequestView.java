package vn.edu.hcmuaf.fit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.RequestController;
import vn.edu.hcmuaf.fit.model.Patient;
import vn.edu.hcmuaf.fit.model.Request;

public class CreateRequestView extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea taProblem;

	private RequestController control;

	private JTable tblRequest;
	private DefaultTableModel dtm;
	private JButton btnAdd, btnRemove, btnUpdate, btnCancel_1, btnSubmit;
	private Request request;
	private List<Patient> patients;
	
	JTextField tfPhone, tfAddress;

	public CreateRequestView(RequestController controller) {
		this.control = controller;
	}

	public void createView() {
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 486, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHeader = new JLabel("Thông tin Request");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 22));

		// vị trị mà mình muốn đặt nó setBounds(x, y , width, height)
		// Hai đối số đầu tiên là tọa độ x và y của góc trên cùng bên trái của thành
		// phần, đối số thứ ba là chiều rộng của thành phần và đối số thứ tư là chiều
		// cao của thành phần.
		lblHeader.setBounds(0, 18, 470, 34);
		contentPane.add(lblHeader);

		//
		JLabel lblPatient = new JLabel("Patient Information:");
		lblPatient.setHorizontalAlignment(SwingConstants.LEFT);
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPatient.setBounds(30, 55, 470, 34);
		contentPane.add(lblPatient);
//		

		// sẽ có các cột nào
		String[] columnNames = { "CMND", "TEN", "TUOI", "GIOI TINH" };
		dtm = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		tblRequest = new JTable(dtm);
		tblRequest.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblRequest.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblRequest.getColumnModel().getColumn(1).setPreferredWidth(170);
		tblRequest.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblRequest.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblRequest.getColumnModel().getColumn(3).setPreferredWidth(60);
//				tblRequest.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblRequest.setBackground(new Color(204, 255, 255));
		tblRequest.setFont(new Font("Times New Roman", Font.PLAIN, 16));

//		redraw();

		JScrollPane scrollPane = new JScrollPane(tblRequest);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(0, 100, 470, 100);
		scrollPane.setBackground(new Color(204, 255, 255));
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		contentPane.add(scrollPane);

		//
		btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(15, 210, 115, 23);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
//		btnAdd.addActionListener(this);

		//
		btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.black);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBackground(Color.YELLOW);
		btnUpdate.setBounds(180, 210, 115, 23);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(this);

		//
		btnRemove = new JButton("Remove");
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRemove.setBackground(Color.RED);
		btnRemove.setBounds(340, 210, 115, 23);
		contentPane.add(btnRemove);
		btnRemove.addActionListener(this);

		//
		JLabel lblPhone = new JLabel("Phone: ");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(57, 260, 110, 27);
		contentPane.add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(224, 260, 188, 27);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		tfPhone.addActionListener(this);
		//
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(57, 300, 110, 27);
		contentPane.add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(224, 300, 188, 27);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		tfAddress.addActionListener(this);
		//
		JLabel lblProblem = new JLabel("Problem Description: ");
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProblem.setBounds(57, 350, 250, 27);
		contentPane.add(lblProblem);

		//
		taProblem = new JTextArea(1, 1);
		taProblem.setBounds(54, 380, 370, 100);
		contentPane.add(taProblem);

		//
		btnCancel_1 = new JButton("Huỷ");
		btnCancel_1.setForeground(Color.WHITE);
		btnCancel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel_1.setBackground(Color.RED);
		btnCancel_1.setBounds(80, 500, 115, 23);
		contentPane.add(btnCancel_1);
		btnCancel_1.addActionListener(this);

		//
		btnSubmit = new JButton("Xác nhận");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBackground(new Color(0, 255, 51));
		btnSubmit.setBounds(275, 500, 115, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(this);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void redraw(List<Patient> list) {
		// TODO Auto-generated method stub
		dtm.setRowCount(0);
		for (Patient p : list) {
			dtm.insertRow(dtm.getRowCount(),
					new Object[] { p.getId(), p.getFullname(), p.getAge(), p.isMale() ? "Nam" : "Nữ" });
		}
	}

	public static void main(String[] args) {
		new CreateRequestView(null);
	}

	public void showError(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.WARNING_MESSAGE, null);
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
		try {
			if (btnAdd.equals(source)) {
			control.getPatient(-1); // tao patient
			
		} else if (btnUpdate.equals(source)) {
			int row = tblRequest.getSelectedRow();
			if (row == -1)
				showError("Vui lòng chọn 1 dòng dữ liệu!");
			else {
				control.getPatient(row);
			}
		}else if(btnRemove.equals(source)) {
			int row = tblRequest.getSelectedRow();
			if (row == -1)
				showError("Vui lòng chọn 1 dòng dữ liệu!");
			else {
				String id = (String) dtm.getValueAt(row, 0); //lay ra id cua dong duoc chon
				control.deletePatient(id);
			}
		}else if(btnSubmit.equals(source)) {
			String phone = tfPhone.getText();
			String address = tfAddress.getText();
			String proDesc = taProblem.getText();
			
			control.CreateRquest(phone, address, proDesc);
			
		}else if (btnCancel_1.equals(source)) {
			dispose();
		}
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("Errror");
		}
		

	}
}
