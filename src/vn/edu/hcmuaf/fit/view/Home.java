package vn.edu.hcmuaf.fit.view;

import java.util.List;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.admin.HomeController;
import vn.edu.hcmuaf.fit.database.DbManager;
import vn.edu.hcmuaf.fit.handle.WordWrapCellRenderer;
import vn.edu.hcmuaf.fit.model.*;

public class Home extends JFrame implements WindowListener, ActionListener, KeyListener {
	private final HomeController controller;
	private final User user;
	private JPanel pnlHeader, pnlBody, pnlTool;
	private JTable tblRequest;
	private JLabel lblHeader, lblName, lblRoleTitle, lblRole;
	private JTextField tfSearch;
	private JButton btnSearch, btnUpdate, btnUpdateInfo, btnRemove, btnLogout;
	private JScrollPane scrollPane;
	private JScrollBar scrollBar;
	private DefaultTableModel dtm;

	public Home(HomeController controller, User user) {
		this.controller = controller;
		this.user = user;
	}

	public void createView() {
		setTitle("Online Health Management System");
		setBounds(0, 0, 1300, 700);
		getContentPane().setLayout(null);

		pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(2, 2, 0, 2, new Color(0, 0, 0)));
		pnlHeader.setBackground(new Color(102, 153, 255));
		pnlHeader.setBounds(0, 0, 1284, 120);
		getContentPane().add(pnlHeader);
		pnlHeader.setLayout(new BorderLayout(0, 0));

		lblHeader = new JLabel("Online Health Management System");
		lblHeader.setForeground(new Color(255, 255, 255));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 36));
		pnlHeader.add(lblHeader, BorderLayout.CENTER);

		pnlBody = new JPanel();
		pnlBody.setBorder(new MatteBorder(2, 2, 0, 2, new Color(0, 0, 0)));
		pnlBody.setBackground(new Color(153, 204, 255));
		pnlBody.setBounds(0, 120, 1284, 541);
		getContentPane().add(pnlBody);
		pnlBody.setLayout(null);

		String[] columnNames = { "ID", "Patient", "Phone", "Problem description", "Status" };
		dtm = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		tblRequest = new JTable(dtm);
		tblRequest.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblRequest.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblRequest.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblRequest.getColumnModel().getColumn(2).setPreferredWidth(75);
		tblRequest.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblRequest.getColumnModel().getColumn(3).setCellRenderer(new WordWrapCellRenderer());
		tblRequest.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblRequest.setBackground(new Color(204, 255, 255));
		tblRequest.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		scrollPane = new JScrollPane(tblRequest);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(0, 55, 1028, 486);
		scrollPane.setBackground(new Color(204, 255, 255));
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		pnlBody.add(scrollPane);

		tfSearch = new JTextField();
		tfSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSearch.setBounds(749, 11, 170, 33);
		tfSearch.setColumns(10);
		tfSearch.addKeyListener(this);
		pnlBody.add(tfSearch);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(929, 11, 89, 33);
		pnlBody.add(btnSearch);

		pnlTool = new JPanel();
		pnlTool.setBorder(new MatteBorder(0, 0, 2, 2, new Color(0, 0, 0)));
		pnlTool.setBackground(new Color(153, 204, 255));
		pnlTool.setBounds(1028, 55, 256, 486);
		pnlBody.add(pnlTool);
		pnlTool.setLayout(null);

		btnUpdate = new JButton("Update Status");
		btnUpdate.setBackground(new Color(255, 215, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(47, 198, 161, 40);
		btnUpdate.addActionListener(this);
		pnlTool.add(btnUpdate);

		btnUpdateInfo = new JButton("Profile");
		btnUpdateInfo.setBackground(new Color(0, 204, 255));
		btnUpdateInfo.setForeground(Color.WHITE);
		btnUpdateInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdateInfo.setBounds(47, 325, 161, 40);
		btnUpdateInfo.addActionListener(this);
		pnlTool.add(btnUpdateInfo);

		btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(255, 0, 0));
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemove.setBounds(47, 260, 161, 40);
		btnRemove.addActionListener(this);
		pnlTool.add(btnRemove);

		btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(255, 0, 0));
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogout.setBounds(47, 392, 161, 40);
		btnLogout.addActionListener(this);
		pnlTool.add(btnLogout);

		lblName = new JLabel("Huỳnh Văn Hữu Ân");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 39, 236, 31);
		pnlTool.add(lblName);

		lblRoleTitle = new JLabel("Role: ");
		lblRoleTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoleTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRoleTitle.setBounds(57, 76, 55, 31);
		pnlTool.add(lblRoleTitle);

		lblRole = new JLabel("Admin");
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRole.setBounds(115, 76, 93, 31);
		pnlTool.add(lblRole);

		scrollBar = scrollPane.getVerticalScrollBar();
		scrollBar.setPreferredSize(new Dimension(20, 0));

		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
	}

	public void redraw(List<Request> requests) {
		dtm.setRowCount(0);
		for (Request request : requests) {
			StringBuilder patientInfo = new StringBuilder();
			for (Patient patient : request.getPatients()) {
				patientInfo.append(patient.getFullname())
						.append(" - ").append(patient.getAge())
						.append(" - ").append(patient.isMale() ? "Nam" : "Nữ")
						.append(" - ").append(patient.getId()).append("|\n");
			}

			String status = DbManager.requestStatus.get(request.getStatus());
			dtm.insertRow(dtm.getRowCount(), new Object[] { request.getId(), patientInfo.toString(),
					request.getPhone(), request.getProblemDescription(), status });
		}
	}

	public void showMessage(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE, null);
	}

	public void showError(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.WARNING_MESSAGE, null);
	}

	public void close() {
		dispose();
		App.frames.remove(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (btnSearch.equals(source)) controller.search();
		else if (btnLogout.equals(source)) controller.logout();
		else if (btnUpdate.equals(source)) {
			int row = tblRequest.getSelectedRow();
			if (row == -1)
				showError("Vui lòng chọn 1 dòng dữ liệu!");
			else {
				Long requestId = (Long) dtm.getValueAt(row, 0);
				controller.getUpdateRequestStatus(requestId);
			}
		}
		else if (btnUpdateInfo.equals(source)) controller.updateInfo();
		else if (btnRemove.equals(source)) {
			int row = tblRequest.getSelectedRow();
			if (row == -1)
				showError("Vui lòng chọn 1 dòng dữ liệu!");
			else {
				int result = JOptionPane.showInternalConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					Long requestId = (Long) dtm.getValueAt(row, 0);
					controller.removeRequest(requestId);
				}
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		lblName.setText(user.getFullname());
		lblRole.setText(user.getRole().getName());
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát ứng dụng?", "",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

		switch (result) {
			case JOptionPane.OK_OPTION:
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		App.frames.remove(this);
	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
