package project;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

//import com.toedter.calendar.JDateChooser;

public class ChinhSua extends JFrame implements ActionListener {
	JPanel p1, p2;
	JLabel l1, l2, l3, l4, l5, l6;
	JTextField t1, t2, t3, t4, tf5, t6;
	JComboBox t5;
//	JDateChooser ng;
	JButton b1, b2, bshow;
	DefaultTableModel dataModel;
	Request re;
	int row;

	public ChinhSua(String s, DefaultTableModel model, int row) {
		super(s);
		dataModel = model;
		
		this.row = row;
		re = ListRequest.getInstance().findRe(row);
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(6, 2));
		
		l1 = new JLabel("Name");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		t1 = new JTextField(re.getName());
//		t1.setText(String.valueOf(h.size()+1));
//		t1.setEnabled(false);
		p1.add(l1);
		p1.add(t1);
		l2 = new JLabel("Sex");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		t2 = new JTextField(re.getSex());
		p1.add(l2);
		p1.add(t2);
		l3 = new JLabel("Age");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		t3 = new JTextField(re.getAge()+"");
		p1.add(l3);
		p1.add(t3);
		l4 = new JLabel("CMND");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		t4 = new JTextField(re.getCMND());
		p1.add(l4);
		p1.add(t4);
		l5 = new JLabel("Address");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		tf5 = new JTextField(re.getAddress());
		p1.add(l5);
		p1.add(tf5);
		l6 = new JLabel("Reason");
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		t6 = new JTextField(re.getReason());
		p1.add(l6);
		p1.add(t6);
//		ng = new JDateChooser();
//		ng.setDateFormatString("dd-MM-yyyy");
//		

//		p1.add(l5);
//		p1.add(ng);
		this.add(p1, "North");

		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		b1 = new JButton("OK");
		b1.addActionListener(this);
		b2 = new JButton("Cancel");
		b2.addActionListener(this);
		bshow = new JButton("Show");
		bshow.addActionListener(this);
		p2.add(b1);
		p2.add(b2);
		p2.add(bshow);
		this.add(p2, "South");

		setSize(350, 250);
		setLocation(525, 350);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("OK")) {
//			String id=t1.getText();
//			String ten=t2.getText();
//			float gia=Float.valueOf(t3.getText());
//			int soLuong=Integer.valueOf(t4.getText());
			// String ngayNhap = t5.getSelectedItem().toString();
//			DateFormat gg = new SimpleDateFormat("dd-MM-yyyy");

//			String ngayNhap = gg.format(ng.getDate());
//			
//			HangHoa hanghoa = new HangHoa(id, ten, gia, soLuong, soLuong, ngayNhap, 0, "00-00-0000");
//			if((new JDBCConnection().addHangHoa(hanghoa))) {
//				hh.add(hanghoa);
//				dataModel.addRow(new Object[] {hanghoa.getId(),hanghoa.getTen(),hanghoa.getGia(),
//						hanghoa.getSoLuong(),hanghoa.getDaNhap(),hanghoa.getNgayNhap()
//						,hanghoa.getDaXuat(),hanghoa.getNgayXuat()
//				});
			String name = t1.getText();
			String sex = t2.getText();
			int age = Integer.parseInt(t3.getText());
			String CMND = t4.getText();
			String address = tf5.getText();
			String reason = t6.getText();

			Request re = new Request(name, sex, age, CMND, address, reason);
			ListRequest.getInstance().change(row, re); //thêm vào ở vị trí sau nó một cái mới vừa thay đổi và xóa cái cũ đi trong arraylist
			
			//thêm vào ở vị trí sau nó một cái mới vừa thay đổi và xóa cái cũ đi trong JTable
			this.dataModel.insertRow(row+1,new Object[] { re.getName(), re.getSex(), re.getAge(), re.getCMND(), re.getAddress(),
					re.getReason() });
			this.dataModel.removeRow(row);
			this.dataModel.fireTableDataChanged();
			
			JOptionPane.showMessageDialog(rootPane, "Chinh sua hang hoa thanh cong!");
			this.dispose();
		} else if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		} else if (e.getActionCommand().equals("Show")) {
			ListRequest.getInstance().print();
		}
	}

}
