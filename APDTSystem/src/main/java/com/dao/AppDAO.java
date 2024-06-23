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
import com.model.App;

public class AppDAO {
    Connection connection = null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/apdtsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    
    private static final String INSERT_APP_SQL = "INSERT INTO app(progcode, appname, department, position) VALUES (?, ?, ?, ?);";
    private static final String SELECT_APP_BY_ID = "select appid, progcode, appname, department, position from app where appid=?";
    private static final String SELECT_ALL_APP = "select * from app";
    private static final String DELETE_APP_SQL = "delete from app where appid = ?;";
    private static final String UPDATE_APP_SQL = "update app set progcode = ?,appname= ?, department= ?, position= ? where appid = ?;";
    
    public AppDAO(){}
    
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
        
    public void insertApp(App app) throws SQLException{
        System.out.println(INSERT_APP_SQL);
        try(Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APP_SQL)){
            preparedStatement.setString(1, app.getProgcode());
            preparedStatement.setString(2, app.getAppname());
            preparedStatement.setString(3, app.getDepartment());
            preparedStatement.setString(4, app.getPosition());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
                }
    }
    
    public App selectApp(int appid){
        App app = null;
        // Step 1: Establishing a Connection
        try(Connection connection = getConnection();
            // Step 2: Create a statement using connection 
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APP_BY_ID);){
            preparedStatement.setInt(1, appid);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                String progcode = rs.getString("progcode");
                String appname = rs.getString("appname");
                String department = rs.getString("department");
                String position = rs.getString("position");
                app = new App(appid, progcode, appname, department, position);
            }
        }catch (SQLException e){
                printSQLException(e);
                }
        return app;
    }
        
    public List < App > selectAllApp(){
        List <App> apps = new ArrayList <>();
        try (Connection connection = getConnection();
              
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APP);){
             System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             
             while(rs.next()){
                 int appid = rs.getInt("appid");
                 String progcode = rs.getString("progcode");
                 String appname = rs.getString("appname");
                 String department = rs.getString("department");
                 String position = rs.getString("position");
                 apps.add(new App(appid, progcode, appname, department, position));
             }
        }catch(SQLException e){
            printSQLException(e);
        }
        return apps;      
    }
    
    public boolean deleteApp(int appid) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(DELETE_APP_SQL);){
            statement.setInt(1, appid);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateApp(App app) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection(); PreparedStatement statement = 
                connection.prepareStatement(UPDATE_APP_SQL);){
            statement.setString(1, app.getProgcode());
            statement.setString(2, app.getAppname());
            statement.setString(3, app.getDepartment());
            statement.setString(4, app.getPosition());
            statement.setInt(5, app.getAppid());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    public List<Integer> selectAllAppIds() {
        List<Integer> appIds = new ArrayList<>();
        String SELECT_ALL_APP_IDS = "SELECT appid FROM app";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APP_IDS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int appid = rs.getInt("appid");
                appIds.add(appid);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return appIds;
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
