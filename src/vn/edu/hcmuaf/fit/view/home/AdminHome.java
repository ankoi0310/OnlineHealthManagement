package vn.edu.hcmuaf.fit.view.home;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.AdminHomeController;
import vn.edu.hcmuaf.fit.dto.Role;
import vn.edu.hcmuaf.fit.handle.TableCellRenderer;
import vn.edu.hcmuaf.fit.handle.WordWrapCellRenderer;
import vn.edu.hcmuaf.fit.model.*;

import static vn.edu.hcmuaf.fit.constant.RequestStatusConstant.*;

public class AdminHome extends JFrame implements WindowListener, ActionListener, KeyListener {
	AdminHomeController controller;
	public User user;
	private JPanel pnlHeader, pnlBody, pnlTool;
	private JTable tbRequest;
	private JLabel lblHeader, lblName, lblRoleTitle, lblRole;
	private JTextField tfSearch;
	private JButton btnSearch, btnRefresh, btnUpdate, btnUpdateInfo, btnRemove, btnLogout;
	private DefaultTableModel dtm;

	public AdminHome(AdminHomeController controller, User user) {
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
		Font font = new Font("Times New Roman", Font.PLAIN, 16);
		tbRequest = new JTable(dtm);
		tbRequest.setFont(font);
		tbRequest.getColumnModel().getColumn(0).setPreferredWidth(10);
		tbRequest.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer(SwingConstants.CENTER, font));
		tbRequest.getColumnModel().getColumn(1).setPreferredWidth(220);
		tbRequest.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer(font));
		tbRequest.getColumnModel().getColumn(2).setPreferredWidth(75);
		tbRequest.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer(SwingConstants.CENTER, font));
		tbRequest.getColumnModel().getColumn(3).setPreferredWidth(175);
		tbRequest.getColumnModel().getColumn(3).setCellRenderer(new WordWrapCellRenderer(font));
		tbRequest.getColumnModel().getColumn(4).setPreferredWidth(150);
		tbRequest.getColumnModel().getColumn(4).setCellRenderer(new TableCellRenderer(SwingConstants.CENTER, font));
		tbRequest.setBackground(new Color(204, 255, 255));
		tbRequest.setSelectionBackground(new Color(102, 153, 255));

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbRequest.getModel());
		tbRequest.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);

		JScrollPane scrollPane = new JScrollPane(tbRequest);
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

		btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(new Color(70, 227, 22));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRefresh.setBounds(47, 131, 161, 40);
		btnRefresh.addActionListener(this);
		pnlTool.add(btnRefresh);

		btnUpdate = new JButton("Update Status");
		btnUpdate.setBackground(new Color(255, 215, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(47, 198, 161, 40);
		btnUpdate.addActionListener(this);
		pnlTool.add(btnUpdate);

		btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(255, 0, 0));
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemove.setBounds(47, 265, 161, 40);
		btnRemove.addActionListener(this);
		pnlTool.add(btnRemove);

		btnUpdateInfo = new JButton("Profile");
		btnUpdateInfo.setBackground(new Color(0, 204, 255));
		btnUpdateInfo.setForeground(Color.WHITE);
		btnUpdateInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdateInfo.setBounds(47, 332, 161, 40);
		btnUpdateInfo.addActionListener(this);
		pnlTool.add(btnUpdateInfo);

		btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(255, 0, 0));
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogout.setBounds(47, 399, 161, 40);
		btnLogout.addActionListener(this);
		pnlTool.add(btnLogout);

		lblName = new JLabel();
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 39, 236, 31);
		pnlTool.add(lblName);

		lblRoleTitle = new JLabel("Role: ");
		lblRoleTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoleTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRoleTitle.setBounds(57, 76, 55, 31);
		pnlTool.add(lblRoleTitle);

		lblRole = new JLabel();
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRole.setBounds(115, 76, 93, 31);
		pnlTool.add(lblRole);

		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
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
						.append(" - ").append(patient.getId()).append("\t");
			}

			String status = switch (request.getStatus()) {
				case 0 -> PENDING.message();
				case 1 -> SUBMITTED.message();
				case 2 -> REQUEST_AMBULANCE.message();
				case 3 -> AMBULANCE_MOVING.message();
				case 4 -> AMBULANCE_ARRIVED.message();
				case 5 -> COMPLETED.message();
				default -> null;
			};

			dtm.insertRow(dtm.getRowCount(), new Object[] { request.getId(), patientInfo,
					request.getPhone(), request.getProblemDescription(), status });
		}
	}

	public void loadData() {
		lblName.setText(user.getFullname());
		lblRole.setText(user.getRole().name());
	}

	public void showMessage(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE, null);
	}

	public void showError(String message) {
		JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.WARNING_MESSAGE, null);
	}

	public void close(Role role) {
		dispose();
		App.frames.remove(this);
		App.frameMap.get(role).remove(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (btnSearch.equals(source)) controller.search(tfSearch.getText());
		else if (btnLogout.equals(source)) controller.logout();
		else if (btnRefresh.equals(source)) controller.refresh();
		else if (btnUpdate.equals(source)) {
			int row = tbRequest.getSelectedRow();
			if (row == -1)
				showError("Vui lòng chọn 1 dòng dữ liệu!");
			else {
				Long requestId = (Long) dtm.getValueAt(row, 0);
				controller.getUpdateRequestStatus(requestId);
			}
		}
		else if (btnUpdateInfo.equals(source)) controller.getUpdateProfile();
		else if (btnRemove.equals(source)) {
			int row = tbRequest.getSelectedRow();
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

	}

	@Override
	public void windowClosing(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát ứng dụng?", "",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

		switch (result) {
			case JOptionPane.OK_OPTION:
				if (App.frames.size() == 1) {
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		App.frameMap.get(user.getRole()).remove(this);
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
		controller.search(tfSearch.getText());
	}
}
