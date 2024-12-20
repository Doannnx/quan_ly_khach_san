/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haiminh2003
 */
public class RoomTypeDAO {
      private JavaConnection javaConnection;

    public RoomTypeDAO() {
        javaConnection = new JavaConnection();
    }

    public List<RoomType> getAllRoomType() {
        List<RoomType> list = new ArrayList<>();
        String query = "Select * from roomtype";
        Connection con = javaConnection.getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("RoomTypeId");
                String name = res.getString("Description");
                list.add(new RoomType(id, name));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            javaConnection.closeConnection(con);
        }
        return list;
    }
    
     public boolean insertRoomTye(RoomType roomType) {
        String query = "INSERT INTO roomtype (RoomTypeId, Description) "
                + "VALUES(?, ?)";
        Connection con = javaConnection.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, roomType.getId());
            statement.setString(2, roomType.getName());
            statement.executeUpdate();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            javaConnection.closeConnection(con);
        }
        return true;
    }

    public boolean deleteRoomType(int id) {
        String query = "DELETE FROM `roomtype` WHERE RoomTypeId = ?";
        Connection con = javaConnection.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            javaConnection.closeConnection(con);
        }
        return true;
    }

    public boolean updateRoomType(RoomType roomType) {
        String query = "Update RoomType set Description = ? where RoomTypeId = ? ";
        Connection con = javaConnection.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, roomType.getName());
            statement.setInt(2, roomType.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            javaConnection.closeConnection(con);
        }
        return true;
    }

    public RoomType findRoomTypeByID(int id) {
        String query = "SELECT * FROM `roomtype` WHERE RoomTypeId = ?";
        RoomType roomType = null;
        Connection con = javaConnection.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int roomTypeId = res.getInt("RoomTypeId");
                String name = res.getString("Description");
                return new RoomType(roomTypeId, name);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            javaConnection.closeConnection(con);
        }
        return null;
    }
}
