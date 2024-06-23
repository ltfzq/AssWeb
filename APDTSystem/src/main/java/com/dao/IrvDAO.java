/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

/**
 *
 * @author Lenovo
 */
import java.sql.*;
import java.sql.SQLException;
import java.util.*;
import com.model.Irv;

public class IrvDAO {
    Connection connection = null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/apdtsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    
    private static final String INSERT_IRV_SQL = "INSERT INTO irv(progcode, date, status, docid, notes) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_IRV_BY_ID = "select irvid, progcode, date, status, docid, notes from irv where irvid=?";
    private static final String SELECT_ALL_IRV = "select * from irv";
    private static final String DELETE_IRV_SQL = "delete from irv where irvid = ?;";
    private static final String UPDATE_IRV_SQL = "update irv set progcode = ?,date= ?, status= ?, ,docid= ?, notes= ? where irvid = ?;";
    
    public IrvDAO(){}
    
        protected Connection getConnection(){
            Connection connection = null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                System.out.println("Database connected!");
            }catch(SQLException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            return connection;
        }
        
    public void insertIrv(Irv irv) throws SQLException{
        System.out.println(INSERT_IRV_SQL);
        try(Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_IRV_SQL)){
            preparedStatement.setString(1, irv.getProgcode());
            preparedStatement.setString(2, irv.getDate());
            preparedStatement.setString(3, irv.getStatus());
            preparedStatement.setInt(4, irv.getDocid());
            preparedStatement.setString(5, irv.getNotes());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
                }
    }
    
    public Irv selectIrv(int irvid){
        Irv irv = null;
        // Step 1: Establishing a Connection
        try(Connection connection = getConnection();
            // Step 2: Create a statement using connection 
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IRV_BY_ID);){
            preparedStatement.setInt(1, irvid);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String progcode = rs.getString("progcode");
                String date = rs.getString("date");
                String status = rs.getString("status");
                int docid = rs.getInt("docid");
                String notes = rs.getString("notes");
                irv = new Irv(irvid, progcode, date, status, docid, notes);
            }
        }catch (SQLException e){
                printSQLException(e);
                }
        return irv;
    }
        
    public List < Irv > selectAllIrv(){
        List <Irv> irvs = new ArrayList <>();
        try (Connection connection = getConnection();
              
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_IRV);){
             System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             
             while(rs.next()){
                 int irvid = rs.getInt("irvid");
                 String progcode = rs.getString("progcode");
                 String date = rs.getString("date");
                 String status = rs.getString("status");
                 int docid = rs.getInt("docid");
                 String notes = rs.getString("notes");
                 irvs.add(new Irv(irvid, progcode, date, status, docid, notes));
             }
        }catch(SQLException e){
            printSQLException(e);
        }
        return irvs;      
    }
    
    public boolean deleteIrv(int irvid) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(DELETE_IRV_SQL);){
            statement.setInt(1, irvid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateIrv(Irv irv) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(UPDATE_IRV_SQL);){
            statement.setString(1, irv.getProgcode());
            statement.setString(2, irv.getDate());
            statement.setString(3, irv.getStatus());
            statement.setInt(4, irv.getDocid());
            statement.setString(5, irv.getNotes());
            statement.setInt(5, irv.getIrvid());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    public List<Integer> selectAllIrvIds() {
        List<Integer> irvIds = new ArrayList<>();
        String SELECT_ALL_IRV_IDS = "SELECT irvid FROM irv";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_IRV_IDS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int irvid = rs.getInt("irvid");
                irvIds.add(irvid);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return irvIds;
    }
    
    private void printSQLException(SQLException ex){
        for(Throwable e: ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
}
