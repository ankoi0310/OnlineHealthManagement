package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class QLHangHoa extends JFrame implements ActionListener, MouseListener {
	JScrollPane sp;
	JPanel p, p1;
	JTable tb;
	DefaultTableModel model; // là cái Jtable dùng để hiện ra hoặc xóa đối tượng trực tiếp
	ArrayList<Request> list = ListRequest.getInstance().getRequestList();
	JButton bNew, bEdit, bDelete, bDetail, bThongKeTime, bTimKiem, bSapXep, bLamMoi;
	JLabel lableTK, lableSX, lableTimKiem, lableSapXep, tenSV;
	JComboBox comSX;
	JTextField tfTK, tfSX;
	int getRow = -1;

	public QLHangHoa(String s) {
		super(s);
		setLayout(new BorderLayout());

		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		JLabel img = new JLabel("");
		tenSV = new JLabel("Quan ly yeu cau kham benh");
		tenSV.setForeground(new Color(255, 0, 0));
		tenSV.setFont(new Font("Sitka Text", Font.BOLD, 24));

		p1.add(img, BorderLayout.WEST);
		p1.add(tenSV, BorderLayout.CENTER);
		p1.setBackground(new Color(224, 255, 255));

		this.add(p1, BorderLayout.NORTH);

		tb = new JTable();
		tb.addMouseListener(this);
		model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Sex");
		model.addColumn("Age");
		model.addColumn("CMND");
		model.addColumn("Address");
		model.addColumn("Reason");

		for (Request re : list) {
			model.addRow(new Object[] { re.getName(), re.getSex(), re.getAge(), re.getCMND(), re.getAddress(),
					re.getReason() });
		}
		tb.setModel(model);
		sp = new JScrollPane(tb);
		sp.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "DANH SACH YEU CAU  ", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.MAGENTA));
		this.add(sp, BorderLayout.CENTER);
		sp.setBackground(new Color(204, 255, 204));

		p = new JPanel();
		bNew = new JButton("Nhap Yeu Cau Moi");
		bNew.addActionListener(this);

		bDetail = new JButton("Chi Tiet Yeu Cau");
		bDetail.addActionListener(this);

		bEdit = new JButton("Chinh Sua Thong Tin");
		bEdit.addActionListener(this);

		bDelete = new JButton("Xoa Yeu Cau");
		bDelete.addActionListener(this);

		lableTK = new JLabel("Tim Kiem");
		lableTK.setHorizontalAlignment(SwingConstants.CENTER);
		lableTimKiem = new JLabel("Nhap Id hoac Ten");
		lableTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		tfTK = new JTextField("");
		bTimKiem = new JButton("Tim Kiem");
		bTimKiem.addActionListener(this);

		lableSX = new JLabel("Sap Xep");
		lableSX.setHorizontalAlignment(SwingConstants.CENTER);

		lableSapXep = new JLabel("Chon Thong Tin Muon Sap Xep");
		lableSapXep.setHorizontalAlignment(SwingConstants.CENTER);
		comSX = new JComboBox();
		comSX.addItem("Ten");
		comSX.addItem("Gia");
		comSX.addItem("So Luong");
		comSX.addItem("Ngay Nhap Kho");
		comSX.addItem("Ngay Xuat Kho");
		bSapXep = new JButton("Sap Xep");
		bSapXep.addActionListener(this);
		
		bLamMoi = new JButton("Lam Moi");
		bLamMoi.addActionListener(this);

		// Lam mau(màu cho các chữ và nút)
		bNew.setFont(new Font("Tahoma", Font.BOLD, 12));
		bNew.setBackground(new Color(153, 255, 102));
		bNew.setForeground(Color.BLACK);

		bDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		bDelete.setBackground(new Color(153, 255, 102));
		bDelete.setForeground(Color.BLACK);

		bEdit.setFont(new Font("Tahoma", Font.BOLD, 12));
		bEdit.setBackground(new Color(153, 255, 102));
		bEdit.setForeground(Color.BLACK);

		bDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		bDetail.setBackground(new Color(51, 255, 255));
		bDetail.setForeground(Color.BLACK);

		bTimKiem.setFont(new Font("Tahoma", Font.BOLD, 12));
		bTimKiem.setBackground(new Color(51, 255, 255));
		bTimKiem.setForeground(Color.BLACK);

		bSapXep.setFont(new Font("Tahoma", Font.BOLD, 12));
		bSapXep.setBackground(new Color(51, 255, 255));
		bSapXep.setForeground(Color.BLACK);

		lableTK.setFont(new Font("Tahoma", Font.BOLD, 14));
		lableTK.setForeground(Color.BLACK);
		lableSX.setFont(new Font("Tahoma", Font.BOLD, 14));
		lableSX.setForeground(Color.BLACK);

		lableTimKiem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lableTimKiem.setForeground(Color.BLACK);
		lableSapXep.setFont(new Font("Tahoma", Font.BOLD, 12));
		lableSapXep.setForeground(Color.BLACK);
		
		bLamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		bLamMoi.setBackground(new Color(51, 255, 255));
		bLamMoi.setForeground(Color.BLACK);

		// Add(các chức năng)
		p.setLayout(new GridLayout(5, 3));
		p.setBackground(new Color(204, 255, 204));
		p.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "CAC CHUC NANG  ", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.MAGENTA));
		p.add(bNew);
		p.add(lableTK);
		p.add(lableSX);

		p.add(bDetail);
		p.add(lableTimKiem);
		p.add(lableSapXep);

		p.add(bEdit);
		p.add(tfTK);
		p.add(comSX);

		p.add(bDelete);
//		p.add(bLamMoi);
		p.add(bTimKiem);
		p.add(bSapXep);
		
		p.add(bLamMoi);

		this.add(p, BorderLayout.SOUTH);
		setLocation(300, 100);
		setSize(800, 500);
		setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		getRow = Integer.valueOf(tb.getSelectedRow()); // lay ra dong duoc chon . Dong dau la 0
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Nhap Yeu Cau Moi")) {
			new NhapKho("Yeu cau moi", model);
			
		} else if (e.getActionCommand().equals("Xuat Hang Hoa")) {
//			new XuatKho("Xuat Hang Hoa", list, model);
			
		} else if (e.getActionCommand().equals("Chinh Sua Thong Tin")) {
			if (getRow != -1) {
				new ChinhSua("Chinh Sua", model, getRow);
			} else {
				JOptionPane.showMessageDialog(rootPane, "Chua Chon Yeu Cau");
			}

		} else if (e.getActionCommand().equals("Xoa Yeu Cau")) {
			ListRequest.getInstance().deleteRe(getRow); // xóa trên mảng trong ListRequest
			model.removeRow(getRow); // xóa trên JTable

		} else if (e.getActionCommand().equals("Lam Moi")) {
			//xoa het lai khi tim kiem
			int rows = model.getRowCount();
			for (int i = rows - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			//them lai cac yeu cau trong arrayList
			for (Request re : list) {
				model.addRow(new Object[] { re.getName(), re.getSex(), re.getAge(), re.getCMND(), re.getAddress(),
						re.getReason() });
			}
			
		} else if (e.getActionCommand().equals("Tim Kiem")) {
			Request request = ListRequest.getInstance().findReWithName(tfTK.getText());

			//xoa cac yeu cau hien luc truoc de hien cac yeu cau da tim duoc
			int rows = model.getRowCount();
			for (int i = rows - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			//them cac yeu cau da tim duoc
			model.addRow(new Object[] { request.getName(), request.getSex(), request.getAge(), request.getCMND(),
					request.getAddress(), request.getReason() });
			
		} else if (e.getActionCommand().equals("Sap Xep")) {
			String clone = comSX.getSelectedItem().toString();
//			new SapXep("Sap Xep", list, clone);

		} else if (e.getActionCommand().equals("Xoa Hang Hoa")) {
//			new XoaHangHoa(list, model, getRow);		
		}

	}

	public static void main(String[] args) {
		new QLHangHoa("Quan Ly Hang Hoa Xuat Nhap Kho");
	}
}
