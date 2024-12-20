/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Room;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author quoct
 */
public class RoomManagement extends javax.swing.JPanel {

    /**
     * Creates new form ListRoom
     */
    public RoomManagement() {
        initComponents();
      
    }
    public void setOnlyNumber(KeyAdapter keyAdapter){
        jTextIdRoom.addKeyListener(keyAdapter);
    }
    public void setOnlyNumberArea(KeyAdapter keyAdapter){
        jTextArea.addKeyListener(keyAdapter);
    }
    public void setOnlyNumberQuantity(KeyAdapter keyAdapter){
        jTextQuantity.addKeyListener(keyAdapter);
    }
    public void setOnlyNumberPrice(KeyAdapter keyAdapter){
        jTextPrice.addKeyListener(keyAdapter);
    }
    public void setClickButtonAddRoom(ActionListener listener){
        jButtonAddRoom.addActionListener(listener);
    }
       public void setClickButtonUpdateRoom(ActionListener listener){
        jButtonUpdateRoom.addActionListener(listener);
    }
        public void setClickButtonDeleteRoom(ActionListener listener){
        jButtonDeleteRoom.addActionListener(listener);
    }
             public void setClickButtonCancelRoom(ActionListener listener){
        jButtonCancel.addActionListener(listener);
    }
        public void setTextDefault(){
            jTextIdRoom.setEnabled(true);
            jTextIdRoom.setText("");
            jTextArea.setText("");
            jTextNameRoom.setText("");
            jTextPrice.setText("");
            jTextQuantity.setText("");
         
        }
    public Room getAllText(){
        List<Room> list = new ArrayList<>();
        int roomTypeId;
        
          int idRoom = -1;
        if(jTextIdRoom.getText().isEmpty()){
            idRoom = -1;
        }
        else{
         idRoom = Integer.valueOf(jTextIdRoom.getText()); 
        }
        String nameRoom = jTextNameRoom.getText();
        String roomType = String.valueOf(jComboBoxRoomType.getSelectedItem());
        if(roomType.equals("Thường")){
            roomTypeId = 1;
        }
        else if(roomType.equals("Trung Bình")){
            roomTypeId = 2;
        }
        else{
            roomTypeId = 3;
        }
        String status = String.valueOf(jComboBoxStatus.getSelectedItem());
         int area = -1;
        if(jTextArea.getText().isEmpty()){
            area = -1;
        }
        else{
           area = Integer.valueOf(jTextArea.getText());  
        }
         int quantity = -1;
          if(jTextQuantity.getText().isEmpty()){
            quantity = -1;
        }
        else{
           quantity = Integer.valueOf(jTextQuantity.getText());
        }
          int price = -1;
          if(jTextPrice.getText().isEmpty()){
            price = -1;
        }
        else{
           price = Integer.valueOf(jTextPrice.getText());
        }
         String location = String.valueOf(jComboBoxLocation.getSelectedItem());
        Room room = new Room(idRoom, nameRoom, roomTypeId, status, quantity, area,price,location);
        return room;
    }
    public void setComboBox1(DefaultComboBoxModel<String> model) {
        jComboBoxRoomType.setModel(model);
    }

    public void setComboBox2(DefaultComboBoxModel<String> model) {
        jComboBoxStatus.setModel(model);
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        jTable1.getSelectionModel().addListSelectionListener(listener);
    }

    public void setTableModel(TableModelRoom table) {
        jTable1.setModel(table);
    }
     public String textSearch(){
        return jTextFieldSearch.getText();
    }
     public void getClickInTextFieldChange(DocumentListener listener) {
        jTextFieldSearch.getDocument().addDocumentListener(listener);
    }
    public void fillText() {
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            int roomID = (int) jTable1.getValueAt(row, 0);
            jTextIdRoom.setText(String.valueOf(roomID));
            String roomType = (String) jTable1.getValueAt(row, 2);
            jComboBoxRoomType.setSelectedItem(roomType);
            String roomStatus = (String) jTable1.getValueAt(row, 3);
            jComboBoxStatus.setSelectedItem(roomStatus);
            jTextNameRoom.setText((String) jTable1.getValueAt(row, 1));
            int quantity = (int) jTable1.getValueAt(row, 5);
            int area = (int) jTable1.getValueAt(row, 4);
            int price = (int) jTable1.getValueAt(row, 7);
            jTextArea.setText(String.valueOf(area));
            jTextQuantity.setText(String.valueOf(quantity));
            jTextPrice.setText(String.valueOf(price));
            String location = (String) jTable1.getValueAt(row, 6);
            jComboBoxLocation.setSelectedItem(location);

        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextIdRoom = new javax.swing.JTextField();
        jTextNameRoom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextArea = new javax.swing.JTextField();
        jTextQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextPrice = new javax.swing.JTextField();
        jComboBoxRoomType = new javax.swing.JComboBox<>();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxLocation = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonAddRoom = new javax.swing.JButton();
        jButtonUpdateRoom = new javax.swing.JButton();
        jButtonDeleteRoom = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(255, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý phòng");

        jLabel2.setText("Mã phòng");

        jLabel3.setText("Tên phòng");

        jLabel4.setText("Loại phòng");

        jTextIdRoom.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel5.setText("Trạng thái");

        jLabel6.setText("Diện Tích");

        jLabel7.setText("Số người ở");

        jLabel8.setText("Giá phòng");

        jLabel9.setText("Vị trí");

        jComboBoxLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tầng 1", "Tầng 2" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextIdRoom)
                    .addComponent(jTextNameRoom)
                    .addComponent(jTextArea)
                    .addComponent(jTextQuantity)
                    .addComponent(jComboBoxRoomType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxStatus, 0, 215, Short.MAX_VALUE)
                    .addComponent(jTextPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(jComboBoxLocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextIdRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextNameRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldSearch))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonAddRoom.setBackground(new java.awt.Color(0, 102, 204));
        jButtonAddRoom.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAddRoom.setText("Thêm");

        jButtonUpdateRoom.setBackground(new java.awt.Color(0, 102, 204));
        jButtonUpdateRoom.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdateRoom.setText("Sửa");

        jButtonDeleteRoom.setBackground(new java.awt.Color(0, 102, 204));
        jButtonDeleteRoom.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDeleteRoom.setText("Xóa");

        jButtonCancel.setBackground(new java.awt.Color(0, 102, 204));
        jButtonCancel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancel.setText("Clear");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButtonAddRoom)
                            .addGap(29, 29, 29)
                            .addComponent(jButtonUpdateRoom)
                            .addGap(43, 43, 43)
                            .addComponent(jButtonDeleteRoom)
                            .addGap(35, 35, 35)
                            .addComponent(jButtonCancel)
                            .addGap(65, 65, 65)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAddRoom)
                            .addComponent(jButtonUpdateRoom)
                            .addComponent(jButtonDeleteRoom)
                            .addComponent(jButtonCancel))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        fillText();
        jTextIdRoom.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddRoom;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDeleteRoom;
    private javax.swing.JButton jButtonUpdateRoom;
    private javax.swing.JComboBox<String> jComboBoxLocation;
    private javax.swing.JComboBox<String> jComboBoxRoomType;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextArea;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextIdRoom;
    private javax.swing.JTextField jTextNameRoom;
    private javax.swing.JTextField jTextPrice;
    private javax.swing.JTextField jTextQuantity;
    // End of variables declaration//GEN-END:variables
}