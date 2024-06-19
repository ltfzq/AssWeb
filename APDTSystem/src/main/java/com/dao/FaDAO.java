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
import com.model.Fa;

public class FaDAO {
    Connection connection = null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/apdtsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    
    private static final String INSERT_FA_SQL = "INSERT INTO fa(progcode, mqa02id, appid, irvid, status) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_FA_BY_ID = "select faid, progcode, mqa02id, appid, irvid, status from fa where faid=?";
    private static final String SELECT_ALL_FA = "select * from fa";
    private static final String DELETE_FA_SQL = "delete from fa where faid = ?;";
    private static final String UPDATE_FA_SQL = "update fa set progcode = ?, mqa02id= ?, appid= ?, irvid= ?, status= ?,  where faid = ?;";
    
    public FaDAO(){}
    
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
        
    public void insertFa(Fa fa) throws SQLException{
        System.out.println(INSERT_FA_SQL);
        try(Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FA_SQL)){
            preparedStatement.setString(1, fa.getProgcode());
            preparedStatement.setInt(2, fa.getMqa02id());
            preparedStatement.setInt(3, fa.getAppid());
            preparedStatement.setInt(4, fa.getIrvid());
            preparedStatement.setString(5, fa.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
                }
    }
    
    public Fa selectFa(int faid){
        Fa fa = null;
        // Step 1: Establishing a Connection
        try(Connection connection = getConnection();
            // Step 2: Create a statement using connection 
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FA_BY_ID);){
            preparedStatement.setInt(1, faid);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String progcode = rs.getString("progcode");
                int mqa02id = rs.getInt("mqa02id");
                int appid = rs.getInt("appid");
                int irvid = rs.getInt("irvid");
                String status = rs.getString("status");
                fa = new Fa(faid, progcode, mqa02id, appid, irvid, status);
            }
        }catch (SQLException e){
                printSQLException(e);
                }
        return fa;
    }
        
    public List < Fa > selectAllFa(){
        List <Fa> fas = new ArrayList <>();
        try (Connection connection = getConnection();
              
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FA);){
             System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             
             while(rs.next()){
                 int faid = rs.getInt("faid");
                 String progcode = rs.getString("progcode");
                 int mqa02id = rs.getInt("mqa02id");
                 int appid = rs.getInt("appid");
                 int irvid = rs.getInt("irvid");
                 String status = rs.getString("status");
                 fas.add(new Fa(faid, progcode, mqa02id, appid, irvid, status));
             }
        }catch(SQLException e){
            printSQLException(e);
        }
        return fas;      
    }
    
    public boolean deleteFa(int faid) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(DELETE_FA_SQL);){
            statement.setInt(1, faid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateFa(Fa fa) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(UPDATE_FA_SQL);){
            statement.setString(1, fa.getProgcode());
            statement.setInt(2, fa.getMqa02id());
            statement.setInt(3, fa.getAppid());
            statement.setInt(4, fa.getIrvid());
            statement.setString(3, fa.getStatus());
            statement.setInt(5, fa.getFaid());
            
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
