package vn.edu.hcmuaf.fit.view.request;

import vn.edu.hcmuaf.fit.App;
import vn.edu.hcmuaf.fit.controller.RequestController;
import vn.edu.hcmuaf.fit.dto.Role;
import vn.edu.hcmuaf.fit.model.Patient;
import vn.edu.hcmuaf.fit.model.Request;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateRequest extends JFrame implements ActionListener {
    private final RequestController controller;
    private final Request model;
    private JTextArea taProblem;
    private JTextField tfPhone, tfAddress;
    private JTable tbRequest;
    private DefaultTableModel dtm;
    private JButton btnAdd, btnRemove, btnUpdate, btnCancel_1, btnSubmit;

    public CreateRequest(RequestController controller, Request model) {
        this.controller = controller;
        this.model = model;
    }

    public void createView() {
        setBounds(500, 200, 486, 600);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHeader = new JLabel("Thông tin Request");
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblHeader.setBounds(0, 18, 470, 34);
        contentPane.add(lblHeader);

        JLabel lblPatient = new JLabel("Patient Information:");
        lblPatient.setHorizontalAlignment(SwingConstants.LEFT);
        lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPatient.setBounds(30, 55, 470, 34);
        contentPane.add(lblPatient);

        String[] columnNames = { "CMND", "TEN", "TUOI", "GIOI TINH" };
        dtm = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false
                return false;
            }
        };
        tbRequest = new JTable(dtm);
        tbRequest.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbRequest.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbRequest.getColumnModel().getColumn(1).setPreferredWidth(170);
        tbRequest.getColumnModel().getColumn(2).setPreferredWidth(50);
        tbRequest.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbRequest.getColumnModel().getColumn(3).setPreferredWidth(60);
        tbRequest.setBackground(new Color(204, 255, 255));
        tbRequest.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(tbRequest);
        scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        scrollPane.setBounds(0, 100, 470, 100);
        scrollPane.setBackground(new Color(204, 255, 255));
        scrollPane.setPreferredSize(new Dimension(1000, 600));
        contentPane.add(scrollPane);

        btnAdd = new JButton("Add");
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnAdd.setBackground(Color.GREEN);
        btnAdd.setBounds(15, 210, 115, 23);
        btnAdd.addActionListener(this);
        contentPane.add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setForeground(Color.black);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnUpdate.setBackground(Color.YELLOW);
        btnUpdate.setBounds(180, 210, 115, 23);
        btnUpdate.addActionListener(this);
        contentPane.add(btnUpdate);

        btnRemove = new JButton("Remove");
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnRemove.setBackground(Color.RED);
        btnRemove.setBounds(340, 210, 115, 23);
        btnRemove.addActionListener(this);
        contentPane.add(btnRemove);

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

        JLabel lblProblem = new JLabel("Problem Description: ");
        lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblProblem.setBounds(57, 350, 250, 27);
        contentPane.add(lblProblem);

        taProblem = new JTextArea(1, 1);
        taProblem.setBounds(54, 380, 370, 100);
        contentPane.add(taProblem);

        btnCancel_1 = new JButton("Huỷ");
        btnCancel_1.setForeground(Color.WHITE);
        btnCancel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCancel_1.setBackground(Color.RED);
        btnCancel_1.setBounds(80, 500, 115, 23);
        contentPane.add(btnCancel_1);
        btnCancel_1.addActionListener(this);

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

    public void redraw(List<Patient> patients) {
        model.setPatients(patients);
        dtm.setRowCount(0);
        for (Patient patient : patients) {
            dtm.insertRow(dtm.getRowCount(),
                    new Object[] { patient.getId(), patient.getFullname(), patient.getAge(), patient.isMale() ? "Nam" : "Nữ" });
        }
    }

    public void showMessage(String message) {
        JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE, null);
    }

    public void showError(String message) {
        JOptionPane.showInternalMessageDialog(null, message, "", JOptionPane.WARNING_MESSAGE, null);
    }

    public void close(Role role) {
        dispose();
        App.frameMap.get(role).remove(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (btnAdd.equals(source)) controller.getCreatePatient(true);
        else if (btnUpdate.equals(source)) {
            int row = tbRequest.getSelectedRow();
            if (row == -1)
                showError("Vui lòng chọn 1 dòng dữ liệu!");
            else controller.getUpdatePatient(true, dtm.getValueAt(row, 0).toString());
        } else if (btnRemove.equals(source)) {
            int row = tbRequest.getSelectedRow();
            if (row == -1)
                showError("Vui lòng chọn 1 dòng dữ liệu!");
            else {
                int result = JOptionPane.showInternalConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xóa",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    String id = (String) dtm.getValueAt(row, 0);
                    controller.removePatient(id, true);
                }
            }
        } else if (btnSubmit.equals(source)) {
            model.setPhone(tfPhone.getText());
            model.setAddress(tfAddress.getText());
            model.setProblemDescription(taProblem.getText());

            controller.createRequest(model);
        } else if (btnCancel_1.equals(source)) {
            controller.cancel(true);
            close(model.getUser().getRole());
        }
    }
}
