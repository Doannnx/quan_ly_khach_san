/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Service;
import Model.ServiceDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author haiminh2003
 */
public class ServiceManagement extends JFrame implements ActionListener {

    private static final int ADD = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;

    private JTextField txtCode, txtName, txtPrice, txtQuantity, txtDescription;
    private JButton btnAdd, btnUpdate, btnDelete, btnReset;
    private JTable table;
    private DefaultTableModel tableModel;
    private ServiceDAO serviceDAO;
    private String oldId;
    
 

    public ServiceManagement() {
        setTitle("Product Management");
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Tiêu đề "Quản Lý Dịch Vụ"
        JLabel titleLabel = new JLabel("Quản Lý Dịch Vụ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng và thêm vào panel
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã");
        tableModel.addColumn("Tên");
        tableModel.addColumn("Giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Mô tả");
        table = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;

        };
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        displayAllService();

        // Vô hiệu hóa sửa trực tiếp
        // Vô hiệu hóa nhập thông tin khi chọn ô
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String id = tableModel.getValueAt(selectedRow, 0).toString();
                        oldId = new String(id);
                        String name = tableModel.getValueAt(selectedRow, 1).toString();
                        String price = tableModel.getValueAt(selectedRow, 2).toString();
                        String quantity = tableModel.getValueAt(selectedRow, 3).toString();
                        String description = tableModel.getValueAt(selectedRow, 4).toString();

                        // Hiển thị thông tin từ hàng được chọn trong các trường nhập liệu
                        txtCode.setText(id);
                        txtName.setText(name);
                        txtPrice.setText(price);
                        txtQuantity.setText(quantity);
                        txtDescription.setText(description);
                    }
                }
            }
        });

        // Panel chứa form và nút
        JPanel formButtonPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Tạo form
        formPanel.add(new JLabel("Mã:"), gbc);
        gbc.gridx++;
        txtCode = new JTextField(20);
        formPanel.add(txtCode, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Tên:"), gbc);
        gbc.gridx++;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Giá:"), gbc);
        gbc.gridx++;
        txtPrice = new JTextField(20);
        txtPrice.setText("1");

        txtPrice.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            public void changedUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            private void checkPriceValidity() {
                if (!isNumeric(txtPrice.getText())) {
                    txtPrice.setForeground(Color.RED);
                } else {
                    txtPrice.setForeground(Color.BLACK);
                }
            }
        });
        formPanel.add(txtPrice, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx++;
        txtQuantity = new JTextField(20);
        txtQuantity.setText("1");
        txtQuantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            public void changedUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            private void checkPriceValidity() {
                if (!isNumeric(txtQuantity.getText())) {
                    txtQuantity.setForeground(Color.RED);
                } else {
                    txtQuantity.setForeground(Color.BLACK);
                }
            }
        });
        formPanel.add(txtQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Mô tả:"), gbc);
        gbc.gridx++;
        txtDescription = new JTextField(20);
        formPanel.add(txtDescription, gbc);

        // Panel chứa nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(this);

        btnUpdate = new JButton("Sửa");
        btnUpdate.addActionListener(this);

        btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.addActionListener(this);

        Dimension buttonSize = new Dimension(100, 30);
        btnAdd.setPreferredSize(buttonSize);
        btnUpdate.setPreferredSize(buttonSize);
        btnDelete.setPreferredSize(buttonSize);
        btnReset.setPreferredSize(buttonSize);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReset);

        // Đặt form và nút vào panel
        formButtonPanel.add(formPanel, BorderLayout.CENTER);
        formButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Đặt panel chứa form và nút vào panel chính
        panel.add(formButtonPanel, BorderLayout.SOUTH);

        // Đặt panel chính vào frame
        add(panel);
        setVisible(true);
    }

    private void binding(int val) {
        switch (val) {
            case ADD:
                txtCode.setEditable(true);
                txtDescription.setEditable(true);
                txtName.setEditable(true);
                txtQuantity.setEditable(true);
                txtPrice.setEditable(true);
                break;
            case UPDATE:
                txtCode.setEditable(false);
                txtDescription.setEditable(true);
                txtName.setEditable(true);
                txtQuantity.setEditable(true);
                txtPrice.setEditable(true);
                break;
            case DELETE:
                txtCode.setEditable(false);
                txtDescription.setEditable(false);
                txtName.setEditable(false);
                txtQuantity.setEditable(false);
                txtPrice.setEditable(false);
                break;
            default:
                txtCode.setEditable(true);
                txtDescription.setEditable(true);
                txtName.setEditable(true);
                txtQuantity.setEditable(true);
                txtPrice.setEditable(true);
                break;
        }
    }

    private void displayAllService() {
        tableModel.setRowCount(0);
        serviceDAO = new ServiceDAO();
        List<Service> services = serviceDAO.getAllService();
        for (Service service : services) {
            Object[] rowData = {service.getServiceID(), service.getNameService(), service.getPrice(), service.getQuantity(),
                service.getDescription()};
            tableModel.addRow(rowData);
        }
    }


    private int countId(String serviceId) {
        int count = 0;
        List<Service> services = serviceDAO.getAllService();
        for (Service service : services) {
            //System.out.println(service.getServiceID());
            count += (service.getServiceID().equals(serviceId) ? 1 : 0);
        }
        return count;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInfor() {
        String id = txtCode.getText();
        if (id.equals("")) {
            return false;
        }

        String name = txtName.getText();
        if (name.equals("")) {
            return false;
        }

//        String description = txtDescription.getText();
//        if (description.equals("")) {
//            return false;
//        }
        String price = txtPrice.getText();
        if (price.equals("") || !isNumeric(price)) {
            return false;
        }

        String quantity = txtQuantity.getText();
        if (quantity.equals("") || !isNumeric(quantity)) {
            return false;
        }

        return true;
    }

    public void dialogError() {
        JOptionPane.showMessageDialog(this,
                "Vui nhập thông tin đầy đủ và đúng định dạng!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
    }

    private void clear() {
        txtCode.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtDescription.setText("");
    }

    // Phương thức để thêm dịch vụ
    private void addService() {
        if (!isValidInfor()) {
            dialogError();
            return;
        }

        // Lấy thông tin từ các trường nhập liệu
        String id = txtCode.getText();
        String name = txtName.getText();
        int price = Integer.valueOf(txtPrice.getText());
        int quantity = Integer.valueOf(txtQuantity.getText());
        String description = txtDescription.getText();

        // System.out.println(id + ": " + countId(id));
        if (countId(id) > 0) {
            JOptionPane.showMessageDialog(this,
                    "San pham co Id nay da ton tai!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn thêm?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            // Thêm dịch vụ vào cơ sở dữ liệu và cập nhật bảng
            Service newService = new Service(id, name, description, price, quantity);
            serviceDAO.insertService(newService);
            displayAllService();
            clear();
        }

    }

// Phương thức để cập nhật dịch vụ
    private void updateService() {
        if (!isValidInfor()) {
            dialogError();
            return;
        }
        // Kiểm tra xem người dùng đã chọn một hàng trong bảng chưa
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dịch vụ để sửa đổi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin từ các trường nhập liệu
        String id = txtCode.getText();
        String name = txtName.getText();
        int price = Integer.valueOf(txtPrice.getText());
        int quantity = Integer.valueOf(txtQuantity.getText());
        String description = txtDescription.getText();

        if (!oldId.equals(id) && countId(id) > 0) {
            JOptionPane.showMessageDialog(this,
                    "San pham co Id nay da ton tai!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cập nhật thông tin của dịch vụ được chọn
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn sửa?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            Service updatedService = new Service(id, name, description, price, quantity);
            serviceDAO.updateService(updatedService);
            displayAllService();
            clear();
        }
    }

// Phương thức để xóa dịch vụ
    private void deleteService() {
        if (!isValidInfor()) {
            dialogError();
            return;
        }
        // Kiểm tra xem người dùng đã chọn một hàng trong bảng chưa
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dịch vụ để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin từ các trường nhập liệu
        String id = txtCode.getText();
        String name = txtName.getText();
        int price = Integer.valueOf(txtPrice.getText());
        int quantity = Integer.valueOf(txtQuantity.getText());
        String description = txtDescription.getText();

        // Lấy ID của dịch vụ được chọn và xóa nó khỏi cơ sở dữ liệu
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            serviceDAO.deleteService(id);
            displayAllService();
            clear();
        }
    }

// Phương thức để tìm kiếm dịch vụ
    private void searchService() {
        // Thực hiện tìm kiếm và hiển thị kết quả
//        String keyword = JOptionPane.showInputDialog(null, "Nhập từ khóa tìm kiếm:");
//        if (keyword != null && !keyword.isEmpty()) {
//            List<Service> searchResult = serviceDAO.searchService(keyword);
//            tableModel.setRowCount(0);
//            for (Service service : searchResult) {
//                Object[] rowData = {service.getServiceID(), service.getNameService(), service.getPrice(), service.getQuantity(),
//                    service.getDescription()};
//                tableModel.addRow(rowData);
//            }
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == btnAdd) {
            addService();
        } else if (e.getSource() == btnUpdate) {
            updateService();
        } else if (e.getSource() == btnDelete) {
            deleteService();
        } else if (e.getSource() == btnReset) {
            clear();
        } else {

        }
    }
}
