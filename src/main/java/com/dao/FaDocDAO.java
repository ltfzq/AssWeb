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
import com.model.FaDoc;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FaDocDAO {
    Connection connection = null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/apdtsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    
    private static final String INSERT_FADOC_SQL = "INSERT INTO fadoc(progcode, docname, docfile, date) VALUES (?, ?, ?, ?);";
    private static final String SELECT_FADOC_BY_ID = "select docid,progcode,docname,docfile, date from fadoc where docid=?";
    private static final String SELECT_ALL_FADOC = "select * from fadoc";
    private static final String DELETE_FADOC_SQL = "delete from fadoc where docid = ?;";
    private static final String UPDATE_FADOC_SQL = "update fadoc set progcode = ?,docname= ?, docfile= ?, date= ? where docid = ?;";
    
    public FaDocDAO(){}
    
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
        
    public void insertFaDoc(FaDoc fadoc) throws SQLException{
        System.out.println(INSERT_FADOC_SQL);
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = 
                connection.prepareStatement(INSERT_FADOC_SQL)){
            InputStream inputStream = new ByteArrayInputStream(fadoc.getDocfile());
            
            preparedStatement.setString(1, fadoc.getProgcode());
            preparedStatement.setString(2, fadoc.getDocname());
            preparedStatement.setBinaryStream(3, inputStream, fadoc.getDocfile().length);
            preparedStatement.setString(4, fadoc.getDate());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
                }
    }
    
    public FaDoc selectFaDoc(int docid) {
        FaDoc fadoc = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FADOC_BY_ID);) {
            preparedStatement.setInt(1, docid);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String progcode = rs.getString("progcode");
                String docname = rs.getString("docname");
                byte[] docfile = rs.getBytes("docfile");
                String date = rs.getString("date");
                fadoc = new FaDoc(docid, progcode, docname, docfile, date);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return fadoc;
    }

        
    public List < FaDoc > selectAllFaDoc(){
        List < FaDoc > fadoc = new ArrayList < > ();
        try (Connection connection = getConnection();
              
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FADOC);){
             System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             
             while(rs.next()){
                 int docid = rs.getInt("docid");
                 String progcode = rs.getString("progcode");
                 String docname = rs.getString("docname");
                 byte[] docfile = rs.getBytes("docfile");
                 String date = rs.getString("date");
                 fadoc.add(new FaDoc(docid, progcode, docname, docfile, date));
             }
        }catch(SQLException e){
            printSQLException(e);
        }
        return fadoc;      
    }
    
    public boolean deleteFaDoc(int docid) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(DELETE_FADOC_SQL);){
            statement.setInt(1, docid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateFaDoc(FaDoc fadoc) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); 
             PreparedStatement statement = connection.prepareStatement(UPDATE_FADOC_SQL)) {

            statement.setString(1, fadoc.getProgcode());
            statement.setString(2, fadoc.getDocname());
            statement.setBinaryStream(3, new ByteArrayInputStream(fadoc.getDocfile()), fadoc.getDocfile().length);
            statement.setString(4, fadoc.getDate());
            statement.setInt(5, fadoc.getDocid());

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
