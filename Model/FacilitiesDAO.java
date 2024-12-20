/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quoct
 */
public class FacilitiesDAO {

    private JavaConnection javaConnection;

    public FacilitiesDAO() {
        javaConnection = new JavaConnection();
    }

    public List<Facilities> getAllFacilities() {
        Connection con = javaConnection.getConnection();
        List<Facilities> list = new ArrayList<>();
        String query = "Select * from  facilities";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                double price = res.getDouble("price");
                int quantity = res.getInt("quantity");
                Date date = res.getDate("date_buy");
                String status = res.getString("status");
                list.add(new Facilities(id, name, price, quantity,  date, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            javaConnection.closeConnection(con);
        }
        return list;
    }

    public List<String> getRoom(int id) {
        Connection con = javaConnection.getConnection();
        List<String> list = new ArrayList<>();
        String query = "Select RoomId from room_facilities where facilitiesID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                list.add(String.valueOf(res.getInt("RoomId")));
      
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            javaConnection.closeConnection(con);
        }
        return list;
    }
    public List<String> getNameFacilities(){
        Connection con = javaConnection.getConnection();
        List<String> list = new ArrayList<>();
        String query = "Select name from facilities ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                list.add(res.getString("name"));
      
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            javaConnection.closeConnection(con);
        }
        return list;
    }
    public boolean checkExits(int idRoom,int idFacilities){
        Connection con = javaConnection.getConnection();
        String query = "Select count(*) as total from  room_facilities where RoomId = ? and facilitiesID = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idRoom);
            preparedStatement.setInt(2, idFacilities);
            ResultSet res  = preparedStatement.executeQuery();
            while(res.next()){
                int total = res.getInt("total");
                if(total > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            javaConnection.closeConnection(con);
        }
        return false;
    }
    public boolean addFacilities(int idRoom,int idFacilities){
        Connection con = javaConnection.getConnection();
        String query = "Insert into room_facilities(RoomId,facilitiesID) values (?,?)";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idRoom);
            preparedStatement.setInt(2, idFacilities);
            int res = preparedStatement.executeUpdate();
            if(res > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            javaConnection.closeConnection(con);
        }
        return false;
    }
//    public boolean deleteFacilitiesfromRoom(int idRoom,int idFacilities){
//        Connection con = javaConnection.getConnection();
//        String query = "Insert into room_facilities(RoomId,facilitiesID) values (?,?)";
//        try{
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1, idRoom);
//            preparedStatement.setInt(2, idFacilities);
//            int res = preparedStatement.executeUpdate();
//            if(res > 0){
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//        catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            javaConnection.closeConnection(con);
//        }
//        return false;
    
    public boolean updateFacilities(Facilities e) {
        Connection con = javaConnection.getConnection();
        String query = "UPDATE facilities Set price = ?,Quantity=?, status = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setDouble(1,  e.getPrice());

            preparedStatement.setInt(2, e.getQuantity());
            preparedStatement.setString(3, e.getStatus());
            preparedStatement.setDouble(4, e.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            javaConnection.closeConnection(con);
        }
    }
    public boolean deleteFacilitiesfromRoom(int id) {
        Connection con = javaConnection.getConnection();
        String query = "DELETE FROM room_facilities WHERE facilitiesID =? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            javaConnection.closeConnection(con);
        }
    }
    public boolean deleteFacilities(int id) {
        Connection con = javaConnection.getConnection();
        String query = "Delete from facilities where id = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            javaConnection.closeConnection(con);
        }
    }
    public static void main(String[] args) {
     FacilitiesDAO svdao= new FacilitiesDAO();
        for(Facilities sv: svdao.getAllFacilities()){
            System.out.println(sv);
        }
        
//        for(Category cat: categoryDao.getAll()){
//            System.out.println(cat);
//        }
//        System.out.println("test update");
//        Facilities catInsert= new Facilities(8,"ấm siêu tốc",200.0,2,new java.sql.Date(2024, 4, 30),"cũ");
//        System.out.println(svdao.updateFacilities(catInsert));
//       Category catUpdate= new Category( "lớp 12",5);
//       System.out.println(categoryDao.update(catUpdate));
//        for(Category cat: categoryDao.getAll()){
//            System.out.println(cat);
//        }
    }
    
}
