/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.RoomAddedListener;
import Model.Room;
import Model.RoomDao;
import View.DashBoard;
import View.RoomManagement;
import View.TableModelRoom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author quoct
 */
public class RoomCrudController {

    private RoomDao data;
    private TableModelRoom model;
    private RoomManagement view;
    private DashBoard layout;
    private DefaultComboBoxModel<String> modelRoomType;
    private DefaultComboBoxModel<String> modelStatus = new DefaultComboBoxModel<>();
    private boolean check = false;
    private RoomAddedListener roomAddedListener;

    public void setRoomAddedListener(RoomAddedListener listener) {
        this.roomAddedListener = listener;
    }

    public RoomCrudController(RoomManagement view) {

        this.view = view;
        this.data = new RoomDao();
        model = new TableModelRoom();
        modelRoomType = new DefaultComboBoxModel<>();
        setOnlyNumber();
        List<String> listComboBox = data.getRoomTypeId();
        for (String item : listComboBox) {
            modelRoomType.addElement(item);
        }
        view.setComboBox1(modelRoomType);
        comboBoxStatus();

        view.setClickButtonAddRoom(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room getData = view.getAllText();
                if(checkEmpty()){
                    JOptionPane.showMessageDialog(view, "Không được để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (data.checkExitst(getData.getIdRoom()) > 0) {
                    JOptionPane.showMessageDialog(view, "Phòng đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } 
                else {
                    view.setTextDefault();
                    data.addRoom(getData);
                    model.setData(data.getListRoom());
                    JOptionPane.showMessageDialog(view, "Phòng đã được thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    if(getData.getLocation().equals("Tầng 1")){
                        fireRoomAddedEvent();
                    }
                    else if(getData.getLocation().equals("Tầng 2")){
                        fireRoomAddedTwoFloorEvent();
                    }
                    
                }
            }
        });
        view.setClickButtonUpdateRoom(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room getData = view.getAllText();
                data.updateRoom(getData);
                model.setData(data.getListRoom());
            }
        });
        view.setClickButtonDeleteRoom(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room getData = view.getAllText();
                if (getData.getStatus() == "Có người ở") {
                    JOptionPane.showMessageDialog(view, "Phòng đang có người ở không thể xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    data.deleteRoom(getData);
                    model.setData(data.getListRoom());
                    view.setTextDefault();
                    fireRoomRemoveEvent();
                }

            }
        });
        view.setClickButtonCancelRoom(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setTextDefault();
            }
        });
        searchTextChange();
    }

    private void fireRoomAddedEvent() {
        if (roomAddedListener != null) {
            roomAddedListener.roomAdded();
        }
    }
    private void fireRoomAddedTwoFloorEvent() {
        if (roomAddedListener != null) {
            roomAddedListener.roomAddedTwoFloor();
        }
    }
     private void fireRoomRemoveEvent() {
        if (roomAddedListener != null) {
            roomAddedListener.roomRemove();
        }
    }

    public void comboBoxStatus() {
        modelStatus.addElement("Trống");
        modelStatus.addElement("Có người ở");
        modelStatus.addElement("Bảo trì");
        view.setComboBox2(modelStatus);
    }

    public void showTable() {
        List<Room> list = data.getListRoom();
        model.setData(list);
        view.setTableModel(model);
    }
    public boolean checkEmpty(){
        Room check = view.getAllText();
        return check.getNameRoom().isEmpty() || check.getArea() == -1 || String.valueOf(check.getDescriptionRoom()).isEmpty() || String.valueOf(check.getLocation()).isEmpty() || check.getPrice() == -1 || check.getQuantity() == -1 || check.getIdRoom() == -1;
    }
    public void setOnlyNumber(){
        view.setOnlyNumber(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    
                    e.consume();
                }
            }
    });
        view.setOnlyNumberArea(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
    });
        view.setOnlyNumberPrice(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
    });
        view.setOnlyNumberQuantity(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
    });
        
}
     public void searchTextChange() {
        view.getClickInTextFieldChange(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void handleTextChange() {
        String newText = view.textSearch();
        List<Room> list = data.getInformationRoomByName(newText);
        model.setData(list);
        view.setTableModel(model);
    }
}
