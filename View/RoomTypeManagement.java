/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.RoomType;
import Model.RoomTypeDAO;
import Model.Service;
import Model.ServiceDAO;
import static View.ServiceManagement.isNumeric;
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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author haiminh2003
 */
public class RoomTypeManagement extends JFrame implements ActionListener {

    private static final int ADD = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;

    private JTextField txtId, txtName;
    private JButton btnAdd, btnUpdate, btnDelete, btnReset;
    private JTable table;
    private DefaultTableModel tableModel;
    private RoomTypeDAO roomTypeDao;
    private int oldId;

    public RoomTypeManagement() {
        setTitle("Room Type Management");
        setSize(800, 600);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Tiêu đề "Quản Lý Dịch Vụ"
        JLabel titleLabel = new JLabel("Quản Lý loại phòng", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng và thêm vào panel
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã");
        tableModel.addColumn("Tên");
        table = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;

        };
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        displayAllRoomType();

        // Vô hiệu hóa sửa trực tiếp
        // Vô hiệu hóa nhập thông tin khi chọn ô
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String id = tableModel.getValueAt(selectedRow, 0).toString();
                        oldId = Integer.parseInt(id);
                        String name = tableModel.getValueAt(selectedRow, 1).toString();

                        // Hiển thị thông tin từ hàng được chọn trong các trường nhập liệu
                        txtId.setText(id);
                        txtName.setText(name);
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
        txtId = new JTextField(20);
        txtId.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPriceValidity();
            }

            private void checkPriceValidity() {
                if (!isNumeric(txtId.getText())) {
                    txtId.setForeground(Color.RED);
                } else {
                    txtId.setForeground(Color.BLACK);
                }
            }
        });
        formPanel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Tên:"), gbc);
        gbc.gridx++;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

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

//    private void binding(int val) {
//        switch (val) {
//            case ADD:
//                txtCode.setEditable(true);
//                txtDescription.setEditable(true);
//                txtName.setEditable(true);
//                txtQuantity.setEditable(true);
//                txtPrice.setEditable(true);
//                break;
//            case UPDATE:
//                txtCode.setEditable(false);
//                txtDescription.setEditable(true);
//                txtName.setEditable(true);
//                txtQuantity.setEditable(true);
//                txtPrice.setEditable(true);
//                break;
//            case DELETE:
//                txtCode.setEditable(false);
//                txtDescription.setEditable(false);
//                txtName.setEditable(false);
//                txtQuantity.setEditable(false);
//                txtPrice.setEditable(false);
//                break;
//            default:
//                txtCode.setEditable(true);
//                txtDescription.setEditable(true);
//                txtName.setEditable(true);
//                txtQuantity.setEditable(true);
//                txtPrice.setEditable(true);
//                break;
//        }
//    }
    private void displayAllRoomType() {
        tableModel.setRowCount(0);
        roomTypeDao = new RoomTypeDAO();
        List<RoomType> roomTypes = roomTypeDao.getAllRoomType();
        for (RoomType roomType : roomTypes) {
            Object[] rowData = {roomType.getId(), roomType.getName()};
            tableModel.addRow(rowData);
        }
    }

    private int countId(int id) {
        int count = 0;
        List<RoomType> roomTypes = roomTypeDao.getAllRoomType();
        for (RoomType roomType : roomTypes) {
            //System.out.println(service.getServiceID());
            count += (roomType.getId() == id ? 1 : 0);
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
        String id = txtId.getText();
        if (id.equals("") || !isNumeric(id)) {
            return false;
        }

        String name = txtName.getText();
        if (name.equals("")) {
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
        txtId.setText("");
        txtName.setText("");
    }

    // Phương thức để thêm dịch vụ
    private void addRoomType() {
        if (!isValidInfor()) {
            dialogError();
            return;
        }

        // Lấy thông tin từ các trường nhập liệu
        int id = Integer.parseInt(txtId.getText().trim());
        String name = txtName.getText().trim();

        // System.out.println(id + ": " + countId(id));
        if (countId(id) > 0) {
            JOptionPane.showMessageDialog(this,
                    "Id nay da ton tai!",
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
            RoomType newRoomType = new RoomType(id, name);
            roomTypeDao.insertRoomTye(newRoomType);
            displayAllRoomType();
            clear();
        }

    }

// Phương thức để cập nhật dịch vụ
    private void updateRoomType() {
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
        int id = Integer.parseInt(txtId.getText().trim());
        String name = txtName.getText().trim();

        if (oldId != id && countId(id) > 0) {
            JOptionPane.showMessageDialog(this,
                    "Id nay da ton tai!",
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
            RoomType updateRoomType = new RoomType(id, name);
            roomTypeDao.updateRoomType(updateRoomType);
            displayAllRoomType();
            clear();
        }
    }

// Phương thức để xóa dịch vụ
    private void deleteRoomType() {
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
        int id = Integer.parseInt(txtId.getText().trim());
        String name = txtName.getText().trim();
        // Lấy ID của dịch vụ được chọn và xóa nó khỏi cơ sở dữ liệu
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            roomTypeDao.deleteRoomType(id);
            displayAllRoomType();
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
            addRoomType();
        } else if (e.getSource() == btnUpdate) {
            updateRoomType();
        } else if (e.getSource() == btnDelete) {
            deleteRoomType();
        } else if (e.getSource() == btnReset) {
            clear();
        } else {

        }
    }
}
