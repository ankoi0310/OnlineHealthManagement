package vn.edu.hcmuaf.fit.view;

import vn.edu.hcmuaf.fit.controller.UserController;
import vn.edu.hcmuaf.fit.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class UpdateProfile extends JFrame implements ActionListener, KeyListener, WindowListener {
    private UserController controller;
    private User model;
    private JPanel contentPane;
    private JLabel lblTitle, lblFullname, lblId, lblAge, lblPhone, lblAddress;
    private JTextField tfFullname, tfId, tfAge, tfPhone, tfAddress;
    private JButton btnCancel, btnUpdate;
    private JRadioButton rdbtnMale, rdbtnFemale;

    public UpdateProfile(UserController controller, User model) {
        this.controller = controller;
        this.model = model;
    }

    public void createView() {
        setTitle("Cập nhật thông tin");
        setBounds(100, 100, 565, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblTitle = new JLabel("Cập nhật thông tin");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblTitle.setBounds(0, 18, 549, 33);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle);

        lblFullname = new JLabel("Họ tên: ");
        lblFullname.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFullname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFullname.setBounds(75, 69, 71, 26);
        contentPane.add(lblFullname);

        tfFullname = new JTextField();
        tfFullname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfFullname.setColumns(10);
        tfFullname.setBounds(145, 69, 196, 26);
        tfFullname.addKeyListener(this);
        contentPane.add(tfFullname);

        lblAge = new JLabel("Tuổi: ");
        lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAge.setBounds(382, 69, 46, 26);
        contentPane.add(lblAge);

        tfAge = new JTextField();
        tfAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfAge.setBounds(426, 69, 78, 26);
        tfAge.setColumns(10);
        tfAge.addKeyListener(this);
        contentPane.add(tfAge);

        lblId = new JLabel("CMND/CCCD: ");
        lblId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblId.setBounds(31, 113, 115, 26);
        contentPane.add(lblId);

        tfId = new JTextField();
        tfId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfId.setColumns(10);
        tfId.setBounds(145, 113, 196, 26);
        tfId.setEnabled(false);
        tfId.setDisabledTextColor(Color.GRAY);
        contentPane.add(tfId);

        rdbtnMale = new JRadioButton("Nam");
        rdbtnMale.setSelected(true);
        rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnMale.setBounds(367, 113, 59, 26);
        contentPane.add(rdbtnMale);

        rdbtnFemale = new JRadioButton("Nữ");
        rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnFemale.setBounds(438, 113, 46, 26);
        contentPane.add(rdbtnFemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnMale);
        bg.add(rdbtnFemale);

        lblPhone = new JLabel("Số điện thoại: ");
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPhone.setBounds(10, 157, 136, 26);
        contentPane.add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfPhone.setBounds(145, 157, 196, 26);
        tfPhone.setColumns(10);
        tfPhone.addKeyListener(this);
        contentPane.add(tfPhone);

        lblAddress = new JLabel("Địa chỉ: ");
        lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddress.setBounds(10, 201, 136, 26);
        contentPane.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfAddress.setColumns(10);
        tfAddress.setBounds(145, 201, 359, 26);
        tfAddress.addKeyListener(this);
        contentPane.add(tfAddress);

        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(0, 153, 255));
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnUpdate.setBounds(327, 289, 115, 23);
        btnUpdate.addActionListener(this);
        contentPane.add(btnUpdate);

        btnCancel = new JButton("Huỷ");
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCancel.setBackground(Color.RED);
        btnCancel.setBounds(106, 289, 115, 23);
        btnCancel.addActionListener(this);
        contentPane.add(btnCancel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(this);
        setLocationRelativeTo(null);
    }

    public void setProfile() {
        tfId.setText(model.getId());
        tfFullname.setText(model.getFullname());
        tfAge.setText(String.valueOf(model.getAge()));
        tfPhone.setText(model.getPhone());
        tfAddress.setText(model.getAddress());
        if (model.isMale()) rdbtnMale.setSelected(true);
        else rdbtnFemale.setSelected(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(getParent(), message, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(getParent(), message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public void close() {
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(btnUpdate)) {
            try {
                model.setAge(Integer.parseInt(tfAge.getText()));
            } catch (NumberFormatException e1) {
                showError("Tuổi không hợp lệ");
                return;
            }
            model.setId(tfId.getText());
            model.setFullname(tfFullname.getText());
            model.setPhone(tfPhone.getText());
            model.setAddress(tfAddress.getText());
            model.setMale(rdbtnMale.isSelected());

            controller.updateProfile(model);
        } else if (source.equals(btnCancel)) close();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnUpdate.doClick();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
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
}
