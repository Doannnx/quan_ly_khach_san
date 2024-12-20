/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Employee;
import Model.Facilities;
import Model.FacilitiesDAO;
import Model.RoomDao;
import View.AddFacilititesToRoom;
import View.FacilitiesView;
import View.InformationFacilitiesView;
import View.TableFacilities;
import View.UpdateEmployee;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author quoct
 */
public class FacilitiesController {

    private FacilitiesView view;
    private FacilitiesDAO data;
    private TableFacilities model;
    private InformationFacilitiesView facilitiesView;
    private AddFacilititesToRoom addtoRoom;
    private RoomDao roomDao;
    public FacilitiesController(FacilitiesView view) {
        this.view = view;
        roomDao = new RoomDao();
        model = new TableFacilities();
        data = new FacilitiesDAO();
        setTable();
        view.clickInTable(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    view.showPopup(e);
                }
            }
        });
        view.informationClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facilitiesView = new InformationFacilitiesView();
                showInforFacilities(facilitiesView);
                Facilities getFacilities = setTableItemSelected();
                List<String> roomId = data.getRoom(getFacilities.getId());
                String room = "";
                for(String str : roomId){
                    room += str  + " ";
                }
                System.out.println(room);
                facilitiesView.setAllText(getFacilities, room);

            }
        });
        view.addFacilitiesClickListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addtoRoom = new AddFacilititesToRoom();
               showAddFacilitiesToRoom(addtoRoom);
               List<String> listRoom = roomDao.getListRoomToFacilities();
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                model.addElement(listRoom);
                addtoRoom.setComboBoxRoom(model);
            }
        });
        view.removeFacilitiesClickListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
//                SinhVienModel sv = view.getTextSv();
//                sv.setLop(lophoc);
                Facilities getFacilities = setTableItemSelected();

                if(data.deleteFacilitiesfromRoom(getFacilities.getId())){
                model.setData(data.getAllFacilities());
                setTable();
                JOptionPane.showMessageDialog(null, "xóa thành công");
               }else{
                   JOptionPane.showMessageDialog(null, "xóa thất bại");
               
                }
            }
        });
        view.setClickButtonEx( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Thiết bị " );
                XSSFRow headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("danh sách thiết bị " );

                XSSFRow faciHeaderRow = sheet.createRow(1);
                faciHeaderRow.createCell(0).setCellValue("Mã Thiết bị");
                faciHeaderRow.createCell(1).setCellValue("Tên tên thiết bị");
                faciHeaderRow.createCell(2).setCellValue("số lượng");
                faciHeaderRow.createCell(3).setCellValue("giá thành");
                faciHeaderRow.createCell(4).setCellValue("Ngày mua");
                faciHeaderRow.createCell(5).setCellValue("tình trạng");


                List<Facilities> listPrint = data.getAllFacilities();
                int rowIndex = 2;
                for (Facilities emp : listPrint) {
                    XSSFRow dataRow = sheet.createRow(rowIndex++);
                    dataRow.createCell(0).setCellValue(emp.getId());
                    dataRow.createCell(1).setCellValue(emp.getName());
                    dataRow.createCell(2).setCellValue(String.valueOf(emp.getQuantity()));
                    dataRow.createCell(3).setCellValue(String.valueOf(emp.getPrice()));
                    dataRow.createCell(4).setCellValue(String.valueOf(emp.getDate_buy()));
                    dataRow.createCell(5).setCellValue(emp.getStatus());

                }

                String filePath = "E:\\Demo ex\\thiết bị.xlsx"; // Đường dẫn tệp
//                String filePathAll = "E:\\Demo ex\\thiết bị.xlsx";

                FileOutputStream fis = null;
                FileOutputStream fisAll = null;

                try {
                    fis = new FileOutputStream(filePath);
                    workbook.write(fis);
                    JOptionPane.showMessageDialog(null, "xuất excel thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

//                    FileInputStream inputStream = new FileInputStream(filePathAll);
//                    XSSFWorkbook existingWorkbook = new XSSFWorkbook(inputStream);
//                    inputStream.close();

//                    XSSFSheet existingSheet = existingWorkbook.getSheetAt(0);
//
//                    int newRowNum = existingSheet.getLastRowNum() + 1;
//                    XSSFRow newRow = existingSheet.createRow(newRowNum);
//
//                    newRow.createCell(0).setCellValue("Mã nhân viên");
//                    newRow.createCell(1).setCellValue("Tên nhân viên");
//                    newRow.createCell(2).setCellValue("Tuổi");
//                    newRow.createCell(3).setCellValue("Giới tính");
//                    newRow.createCell(4).setCellValue("Số điện thoại");
//                    newRow.createCell(5).setCellValue("Vị trí");
//                    newRow.createCell(6).setCellValue("Lương");
//
//                    for (Row row : sheet) {
//                        Row existingRow = existingSheet.createRow(++newRowNum);
//                        for (int i = 0; i < row.getLastCellNum(); i++) {
//                            Cell existingCell = existingRow.createCell(i);
//                            existingCell.setCellValue(row.getCell(i).getStringCellValue());
//                        }
//                    }

//                    fisAll = new FileOutputStream(filePathAll);
//                    existingWorkbook.write(fisAll);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
//                        if (fisAll != null) {
//                            fisAll.close();
//                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        workbook.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void setTable() {
        List<Facilities> list = data.getAllFacilities();
        model.setData(list);
        view.setTable(model);
    }

    public void showInforFacilities(InformationFacilitiesView facilitiesView) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(550, 450);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Thông tin chi tiết thiết bị");
        jFrame.getContentPane().add(facilitiesView);
        jFrame.setVisible(true);
        facilitiesView.setClickButtonUpdate(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("Test upadte");
                model = new TableFacilities();
                data = new FacilitiesDAO();
                Facilities getData = facilitiesView.getAllText();
               if( data.updateFacilities(getData)){
                model.setData(data.getAllFacilities());
                JOptionPane.showMessageDialog(null, "sưa thành công");
                setTable();
                
               }else{
                   JOptionPane.showMessageDialog(null, "sưa thất bại");
               }
            }
        });
    }
      public void showAddFacilitiesToRoom(AddFacilititesToRoom add) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(312, 312);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Thêm thiết bị cho phòng");
        jFrame.getContentPane().add(add);
        jFrame.setVisible(true);
    }

    public Facilities setTableItemSelected() {
        int rowSelected = view.getRowSelected();
        int id = (int) model.getValueAt(rowSelected, 0);
        String name = (String) model.getValueAt(rowSelected, 1);
        double price = (double) model.getValueAt(rowSelected, 4);
        int quantity = (int) model.getValueAt(rowSelected, 2);
        Date date_buy = (Date) model.getValueAt(rowSelected, 3);
        String status = (String) model.getValueAt(rowSelected, 5);
        Facilities facilities = new Facilities(id, name, price, quantity, date_buy, status);
        return facilities;
    }
}
