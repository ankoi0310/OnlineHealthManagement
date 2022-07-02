package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RequestController;
import handle.AppBaseResult;
import model.Request;

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

public class UpdateRequestStatus extends JFrame {
	private static final long serialVersionUID = 1L;
	private RequestController requestController;
	
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	private JPanel contentPane;
	public UpdateRequestStatus(Long requestId) {
		Request request = Main.requests.stream().filter(item -> item.getId().equals(requestId)).findFirst().orElse(null);
		
		if (request == null) {
			JOptionPane.showInternalMessageDialog(null, "Không tìm thấy yêu cầu", "", JOptionPane.WARNING_MESSAGE);
			dispose();
			return;
		}
		
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeader = new JLabel("Cập nhật trạng thái yêu cầu");
		lblHeader.setBounds(5, 5, 424, 27);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(lblHeader);
		
		JComboBox<String> cbStatus = new JComboBox<>();
		cbStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbStatus.setModel(new DefaultComboBoxModel<>(new String[] {"Chờ xử lý", "Đang xử lý", "Đang yêu cầu xe cấp cứu", "Xe cấp cứu đang di chuyển", "Yêu cầu hoàn thành"}));
		cbStatus.setBounds(83, 53, 268, 32);
		contentPane.add(cbStatus);
		
		JButton btnCancel = new JButton("Huỷ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBackground(Color.RED);
		btnCancel.setBounds(67, 110, 115, 23);
		contentPane.add(btnCancel);
		
		JButton btnSubmit = new JButton("Xác nhận");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestController = new RequestController();

				int status = cbStatus.getSelectedIndex();
				AppBaseResult result = requestController.updateRequestStatus(request, status);
				
				JOptionPane.showInternalMessageDialog(null, result.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				
				main.redraw();
			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBackground(new Color(0, 255, 51));
		btnSubmit.setBounds(250, 110, 115, 23);
		contentPane.add(btnSubmit);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}
