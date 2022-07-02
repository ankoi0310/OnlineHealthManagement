package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import constant.RequestStatusConstant;
import handle.WordWrapCellRenderer;
import model.Patient;
import model.Request;
import model.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable tblRequest;
	private JTextField tfSearch;
	private DefaultTableModel dtm;
	
	public static Map<Integer, String> requestStatus = new HashMap<>();
	public static Map<Integer, String> roles = new HashMap<>();
	public static List<User> users = new ArrayList<>();
	public static List<Request> requests = new ArrayList<>();
	public static List<Patient> patients = new ArrayList<>();
	
	public Main getMain() {
		return this;
	}
	
	public void redraw() {
		dtm.setRowCount(0);
		for (Request request : requests) {
			String patientInfo = "";
			for (Patient patient : request.getPatients()) {
				patientInfo += patient.getFullname() + " - " + patient.getAge() + " - " + (patient.isMale() ? "Nam" : "Nữ") + " - " + patient.getId() + "|\n";
			}
			
			String status = requestStatus.get(request.getStatus());
			System.out.println(dtm.getRowCount());
			dtm.insertRow(dtm.getRowCount(), new Object[] { request.getId(), patientInfo, 
					request.getPhone(), request.getProblemDescription(), status });
		}
	}

	public Main(User user) {
		setTitle("Online Health Management System");
		setBounds(0, 0, 1300, 700);
		getContentPane().setLayout(null);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		pnlHeader.setBackground(new Color(102, 153, 255));
		pnlHeader.setBounds(0, 0, 1284, 120);
		getContentPane().add(pnlHeader);
		pnlHeader.setLayout(new BorderLayout(0, 0));
		
		JLabel lblHeader = new JLabel("Online Health Management System");
		lblHeader.setForeground(new Color(255, 255, 255));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 36));
		pnlHeader.add(lblHeader, BorderLayout.CENTER);
		
		JPanel pnlBody = new JPanel();
		pnlBody.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		pnlBody.setBackground(new Color(153, 204, 255));
		pnlBody.setBounds(0, 120, 1284, 541);
		getContentPane().add(pnlBody);
		pnlBody.setLayout(null);
		
		String[] columnNames = { "ID", "Patient", "Phone", "Problem description", "Status" };
		dtm = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

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
		redraw();
		
		JScrollPane scrollPane = new JScrollPane(tblRequest);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(0, 55, 1028, 486);
		scrollPane.setBackground(new Color(204, 255, 255));
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		pnlBody.add(scrollPane);
		
		tfSearch = new JTextField();
		tfSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSearch.setBounds(749, 11, 170, 33);
		pnlBody.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(929, 11, 89, 33);
		pnlBody.add(btnSearch);
		
		JPanel pnlTool = new JPanel();
		pnlTool.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(0, 0, 0)));
		pnlTool.setBackground(new Color(153, 204, 255));
		pnlTool.setBounds(1028, 55, 256, 486);
		pnlBody.add(pnlTool);
		pnlTool.setLayout(null);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRefresh.setBackground(new Color(255, 215, 0));
		btnRefresh.setBounds(47, 135, 161, 40);
		pnlTool.add(btnRefresh);
		
		JButton btnUpdate = new JButton("Update Status");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblRequest.getSelectedRow();
				
				if (row < 0)
					JOptionPane.showInternalMessageDialog(null, "Vui lòng chọn 1 dòng dữ liệu!", "", JOptionPane.WARNING_MESSAGE, null);
				else {
					Long requestId = (Long) dtm.getValueAt(row, 0);
					
					UpdateRequestStatus frame = new UpdateRequestStatus(requestId);
					frame.setMain(getMain());
					frame.setVisible(true);
				}
			}
		});
		btnUpdate.setBackground(new Color(255, 215, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(47, 198, 161, 40);
		pnlTool.add(btnUpdate);
		
		JButton btnUpdateInfo = new JButton("Profile");
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdateInfo.setBackground(new Color(0, 204, 255));
		btnUpdateInfo.setForeground(Color.WHITE);
		btnUpdateInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdateInfo.setBounds(47, 325, 161, 40);
		pnlTool.add(btnUpdateInfo);
		
		JButton btn = new JButton("Remove");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(255, 0, 0));
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBounds(47, 260, 161, 40);
		pnlTool.add(btn);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(255, 0, 0));
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogout.setBounds(47, 392, 161, 40);
		pnlTool.add(btnLogout);
		
		JLabel lblName = new JLabel("Huỳnh Văn Hữu Ân");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(10, 39, 236, 31);
		pnlTool.add(lblName);
		
		JLabel lblRole = new JLabel("Admin");
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRole.setBounds(115, 76, 93, 31);
		pnlTool.add(lblRole);
		
		JLabel label = new JLabel("Role: ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(57, 76, 55, 31);
		pnlTool.add(label);
		
		JScrollBar bar = scrollPane.getVerticalScrollBar();
		bar.setPreferredSize(new Dimension(20, 0));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				lblName.setText(user.getFullname());
				lblRole.setText(roles.get(user.getRoleId()));
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
		});
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private static void initData() {
		// Tạo trạng thái
		requestStatus.put(0, "Chờ xử lý");
		requestStatus.put(1, "Đang xử lý");
		requestStatus.put(2, "Đang yêu cầu xe cấp cứu");
		requestStatus.put(3, "Xe cấp cứu đang di chuyển");
		requestStatus.put(4, "Yêu cầu hoàn thành");
		
		// Tạo phân quyền
		roles.put(1, "ADMIN");
		roles.put(2, "EMPLOYEE");
		roles.put(3, "HOSPITAL");
		roles.put(4, "USER");
		
		// Tạo thông tin người quản lý
		User admin = new User("1", "Admin", 19, true, "1", 
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", 1);
		users.add(admin);
		
		User employee = new User("2", "Employee", 19, true, "2", 
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", 2);
		users.add(employee);
		
		// Tạo thông tin người trực bệnh viện
		User hospital = new User("3", "Bệnh viện Trung ương III", 0, true, "3",
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "Địa chỉ bệnh viện", 3);
		users.add(hospital);
		
		// Tạo thông tin người dùng
		User user = new User("092343234234", "Nguyễn Văn A", 20, true, "4", 
				"8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92", "ABCXYZ", 4);
		users.add(user);
		
		// Tạo bệnh nhân
		Patient patient = new Patient("123456789012", "Nguyễn Văn B", 19, true);
		patients.add(patient);
		
		List<Patient> patients = new ArrayList<>();
		patients.add(patient);
		
		// Tạo yêu cầu
		Request request = new Request(1L, patients, user.getPhone(), user.getAddress(), 
				"Bệnh nhân đang trong tình trạng nguy cấp, cần xe cấp cứu ngay!", RequestStatusConstant.PENDING, user);
		requests.add(request);
	}

	public static void main(String args[]) {
		initData();
		new Login();
	}
}
