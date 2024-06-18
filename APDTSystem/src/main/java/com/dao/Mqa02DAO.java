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
import com.model.Mqa02;

public class Mqa02DAO {
    Connection connection = null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/apdtsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    
    private static final String INSERT_MQA02_SQL = "INSERT INTO mqa02(progcode, docid, status, notes) VALUES (?, ?, ?, ?);";
    private static final String SELECT_MQA02_BY_ID = "select mqa02id, progcode, docid, status, notes from mqa02 where mqa02id=?";
    private static final String SELECT_ALL_MQA02 = "select * from mqa02";
    private static final String DELETE_MQA02_SQL = "delete from mqa02 where mqa02id = ?;";
    private static final String UPDATE_MQA02_SQL = "update mqa02 set progcode = ?,docid= ?, status= ?, notes= ? where mqa02id = ?;";
    
    public Mqa02DAO(){}
    
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
        
    public void insertMqa02(Mqa02 mqa02) throws SQLException{
        System.out.println(INSERT_MQA02_SQL);
        try(Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MQA02_SQL)){
            preparedStatement.setString(1, mqa02.getProgcode());
            preparedStatement.setInt(2, mqa02.getDocid());
            preparedStatement.setString(3, mqa02.getStatus());
            preparedStatement.setString(4, mqa02.getNotes());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
                }
    }
    
    public Mqa02 selectMqa02(int mqa02id){
        Mqa02 mqa02 = null;
        // Step 1: Establishing a Connection
        try(Connection connection = getConnection();
            // Step 2: Create a statement using connection 
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MQA02_BY_ID);){
            preparedStatement.setInt(1, mqa02id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String progcode = rs.getString("progcode");
                int docid = rs.getInt("docid");
                String status = rs.getString("status");
                String notes = rs.getString("notes");
                mqa02 = new Mqa02(mqa02id, progcode, docid, status, notes);
            }
        }catch (SQLException e){
                printSQLException(e);
                }
        return mqa02;
    }
        
    public List < Mqa02 > selectAllMqa02(){
        List <Mqa02> mqa02s = new ArrayList <>();
        try (Connection connection = getConnection();
              
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MQA02);){
             System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             
             while(rs.next()){
                 int mqa02id = rs.getInt("mqa02id");
                 String progcode = rs.getString("progcode");
                 int docid = rs.getInt("docid");
                 String status = rs.getString("status");
                 String notes = rs.getString("notes");
                 mqa02s.add(new Mqa02(mqa02id, progcode, docid, status, notes));
             }
        }catch(SQLException e){
            printSQLException(e);
        }
        return mqa02s;      
    }
    
    public boolean deleteMqa02(int mqa02id) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(DELETE_MQA02_SQL);){
            statement.setInt(1, mqa02id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateMqa02(Mqa02 mqa02) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(UPDATE_MQA02_SQL);){
            statement.setString(1, mqa02.getProgcode());
            statement.setInt(2, mqa02.getDocid());
            statement.setString(3, mqa02.getStatus());
            statement.setString(4, mqa02.getNotes());
            statement.setInt(5, mqa02.getMqa02id());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
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
